/**
 *                ███
 *                ███                     ██
 *                ███                     ██
 *                ███                     ██
 *                ███                     ██
 *                ███   █  █  █  █        ██
 *                ███    ▀▀ ▀▀ ▀▀█        ██
 * ▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄███▄▄▄▄▄▄▄▄▄▄▄▄█▄▄▄▄▄▄▄▄██▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄ ▄
 *     ▀▀▄▄       ███            █        ██
 *        ▀▀▄▄   ███▀            █        ██
 *           ▀▀███▀              █        ██
 *                               █▄▄▄▄▄▄▄▄██▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄ ▄
 *      Copyright (c) 2016       █  ▀▀▄▄  ██
 *      All right reserved       █     ▀▀██▀
 *                               ▀
 */
package guardias;

import static guardias.Utils.showResidents;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import static guardias.Utils.addChars;
import static guardias.Utils.print;
import static guardias.Utils.println;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @version v1.0
 * @author luis
 */
public class Asignar {

	// PUBLIC ATTRIBUTES
	// <editor-fold desc="<------------------->">
	public static Integer SEED = 360;
	public static Integer DIF = 2;
	public static List<Residente> RESIDENTES = new ArrayList();
	public static boolean stop = false;
	// </editor-fold>

	// MASTER METHOD
	// <editor-fold desc="<------------------->">
	public static Calendario cthulhu(Integer year, Integer month, Integer seed,
			Map<Integer, Residente> residentes,
			List<Pair<Integer, Residente>> ausentes,
			List<Pair<Integer, Residente>> obligatorios) {

		// Actualizamos las variables globales
		SEED = seed;
		RESIDENTES = new ArrayList(residentes.values());

		// Creamos Calendario
		Calendario cal = new Calendario(year, month, seed);

		// Agregamos ausentes y obligatorios
		for (Pair<Integer, Residente> p : ausentes) {
			cal.addAbsent(p.getValue(), p.getKey());
		}
		for (Pair<Integer, Residente> p : obligatorios) {
			if (p.getValue().isHigher()) {
				cal.addSelectedURG_higher(p.getValue(), p.getKey());
			} else {
				cal.addSelectedURG_minor(p.getValue(), p.getKey());
			}
		}

		// Creamos random
		Random random = new Random(SEED);

		// Creamos los listas auxiliares
		List<Residente> aux_high = new ArrayList();
		List<Residente> aux_min = new ArrayList();
		for (Residente res : residentes.values()) {
			if (res.isHigher()) {
				aux_high.add(res);
			}
			if (res.isMinor()) {
				aux_min.add(res);
			}
		}

		// Creamos los pulls de residentes
		Queue<Residente> mayores = new ArrayDeque();
		Queue<Residente> menores = new ArrayDeque();

		// Randomizamos pulls
		while (!aux_high.isEmpty()) {
			int n = (int) (random.nextDouble() * aux_high.size());
			mayores.add(aux_high.remove(n));
		}
		while (!aux_min.isEmpty()) {
			int n = (int) (random.nextInt(aux_min.size()));
			menores.add(aux_min.remove(n));
		}

		// Creamos lista de asignaciones
		Map<Integer, Integer> asignaciones = new HashMap();
		Map<Integer, Integer> asignaciones_urg = new HashMap();
		Map<Integer, Integer> asignaciones_tx = new HashMap();
		for (Residente res : residentes.values()) {
			asignaciones.put(res.getId(), 0);
			asignaciones_urg.put(res.getId(), 0);
			asignaciones_tx.put(res.getId(), 0);
		}

		// Hacemos la asignacion
		boolean asignado = false;
		try {
			asignado = probarOpciones(cal, cal.getCalendar().get(0), menores, mayores, asignaciones, asignaciones_urg, asignaciones_tx);
		} catch (CloneNotSupportedException | InterruptedException | NullPointerException exc) {
			System.err.printf("# ERROR (main): " + exc + "\r\n# " + cal.getYear() + " - " + cal.monthName() + " [SEED: " + SEED + " ]\r\n");
		}

		stop = false;

		if (asignado) {
			println(Utils.INTRO);
			showResidents(residentes, ausentes, obligatorios);
			//showAssignments(residentes, asignaciones, asignaciones_urg, asignaciones_tx);
			Utils.showAssignmentsInParts(residentes, asignaciones, asignaciones_urg, asignaciones_tx);

			println(" 5. CALENDARIO");
			println(addChars(91, " " + cal.monthName().toUpperCase() + " ", '~'));
			println(cal.toString());
			return cal;
		} else {
			println("\r\n\r\n # ERROR. No se pudieron hacer las asignaciones");
			return null;
		}
	}
	// </editor-fold>

	// METHODS
	// <editor-fold desc="<------------------->">
	private static boolean probarOpciones(Calendario calendario, Dia dia,
			Queue<Residente> menores, Queue<Residente> mayores,
			Map<Integer, Integer> asignaciones,
			Map<Integer, Integer> asignaciones_urg,
			Map<Integer, Integer> asignaciones_tx)
			throws CloneNotSupportedException, InterruptedException {

		// Hacemos copia de todo
		Calendario _calendario = (Calendario) calendario.clone();
		Map<Integer, Integer> _asignaciones = cloneMap(asignaciones);
		Map<Integer, Integer> _asignaciones_urg = cloneMap(asignaciones);
		Map<Integer, Integer> _asignaciones_tx = cloneMap(asignaciones);

		// Copiamos mayores
		Queue<Residente> _mayores = cloneQueue(mayores);

		// Copiamos menores
		Queue<Residente> _menores = cloneQueue(menores);

		boolean asignado = false;
		boolean is_saturday;
		int it = 0;
		int jt = 0;
		try {
			while (!asignado && !stop) {
				//println("probarOpciones(" + dia.getWeekDayName() + ", " + (dia.getDay()+1) + ")");
				// i -> mayores / j -> menores
				// Rotamos mayores
				for (int i = 0; i < it; i++) {
					Residente aux = mayores.poll();
					mayores.add(aux);
				}
				// Rotamos menores
				for (int j = 0; j < jt; j++) {
					Residente aux = menores.poll();
					menores.add(aux);
				}
				// Probamos combinacion
				// Si es Sabado y existe Domingo y Lunes, saltamos directamente al Lunes
				is_saturday = (dia.getWeek_day() == 5);
				is_saturday &= (calendario.next(dia.getDay()) != null);
				is_saturday = (is_saturday && (calendario.next(calendario.next(dia.getDay()).getDay()) != null));	// no evalua el segundo parametro si no se cumple el anterior
				if (is_saturday) {
					dia = calendario.next(calendario.next(dia.getDay()).getDay());
				}
				// Ejecutamos combinacion
				asignado = asignar(calendario, dia, menores, mayores, asignaciones, asignaciones_urg, asignaciones_tx);

				// Si se ha asignado, probamos opciones en el día siguiente
				if (asignado) {
					if (calendario.hasNext(dia.getDay())) {
						//println((dia.getDay() + 1) + ") IMPORTANTE!!!!! [" + jt + " " + it + "]\n\n");
						//println("probamos la siguiente combinación ...\n\n");
						//Thread.sleep(500);
						asignado = probarOpciones(calendario, calendario.next(dia.getDay()), menores, mayores, asignaciones, asignaciones_urg, asignaciones_tx);
					}
				}

				// Si no se ha podido asignar el día siguiente, se reinicia la cuenta
				if (!asignado) {
					//println("Dia: " + dia.getWeekDayName() + " " + (dia.getDay() + 1));
					//println("___________________________________________________________________________________________________________");
					//println("______________________________________________ [" + jt + " "+ it + "] ______________________________________________________");
					//println("__________________________________________________________________________________________________________");
					//println("| | | Mayores         : " + mayores);
					//println("| | | Menores         : " + menores);
					//println("__________________________________________________________________________________________________________");
					//println("| | | Excepciones     : " + dia.getExceptions());
					//println("| | | Excepciones URG : " + dia.getExceptions_urg());
					//println("| | | Excepciones  TX : " + dia.getExceptions_tx());
					//println("__________________________________________________________________________________________________________");
					//println("\n\n");
					calendario = (Calendario) _calendario.clone();
					mayores = cloneQueue(_mayores);
					menores = cloneQueue(_menores);
					asignaciones = cloneMap(_asignaciones);
					asignaciones_tx = cloneMap(_asignaciones_tx);
					asignaciones_urg = cloneMap(_asignaciones_urg);
				}

				// Si superamos el número de intentos, salimos
				it++;
				if (it > (mayores.size() / ((dia.getDay() / 6) + 1))) {
					it = 0;
					jt++;
					if (jt > (menores.size() / ((dia.getDay() / 6) + 1))) {
						return false;
					}
				}
			}
		} catch (CloneNotSupportedException | InterruptedException | NullPointerException exc) {
			System.err.printf("# ERROR (probarOpciones): " + exc + "\r\n# " + calendario.getYear() + " - " + calendario.monthName() + " [SEED: " + SEED + " ] ~ Intento: " + jt + " " + it);
			asignado = false;
		}
		System.gc();
		if (stop) {
			asignado = false;
		}
		return (asignado && (!poda(calendario, dia.getDay())));
	}

	private static boolean asignar(Calendario calendario, Dia dia,
			Queue<Residente> menores,
			Queue<Residente> mayores,
			Map<Integer, Integer> asignaciones,
			Map<Integer, Integer> asignaciones_urg,
			Map<Integer, Integer> asignaciones_tx)
			throws InterruptedException, CloneNotSupportedException {

		// Si es un dia fuera de rango (solo por seguridad)
		if (!calendario.getCalendar().containsKey(dia.getDay())) {
			return true;
		}
		if (dia.getWeek_day() < 5) {

			//println("  asignar(" + dia.getWeekDayName() + ", " + (dia.getDay()+1) + ")");
			// LUNES | MARTES | MIÉRCOLES | JUEVES | VIERNES
			// URG_MAYOR
			if (!asignarURG_mayor(calendario, dia, mayores, asignaciones, asignaciones_urg)) {
				//println("    (X) # ERROR URG mayor!\n\n");
				return false;
			}

			// URG_MENOR
			if (!asignarURG_menor(calendario, dia, menores, asignaciones, asignaciones_urg)) {
				//println("    (X) # ERROR URG menor!\n\n");
				return false;
			}

			// TX_MAYOR
			if (!asignarTX_mayor(dia, mayores, asignaciones, asignaciones_tx)) {
				//println("    (X) # ERROR TX mayor!\n\n");
				return false;
			}

			// TX_MENOR
			if (!asignarTX_menor(dia, menores, asignaciones, asignaciones_tx)) {
				//println("    (X) # ERROR TX menor!\n\n");
				return false;
			}

			// VIERNES | SÁBADO | DOMINGO
			if (dia.getWeek_day() == 4) {
				asignarFinde(calendario, dia, asignaciones, asignaciones_tx, asignaciones_urg);
			}

		}
		//println("\n\n");
		return true;
	}

	private static boolean asignarURG_mayor(Calendario calendario, Dia dia,
			Queue<Residente> mayores,
			Map<Integer, Integer> asignaciones,
			Map<Integer, Integer> asignaciones_urg)
			throws InterruptedException {

		boolean asignado = (dia.hasURG_higher());
		int intento = 0;
		int dif = 0;
		while (!asignado) {
			// Sacamos primer residente en cola
			Residente mayor = mayores.poll();
			//println("    probando " + mayor + " ... ____________________________");
			// Comprobamos si la asignacion es correcta
			asignado = !dia.getAbsents().contains(mayor);
			asignado &= !dia.getExceptions().contains(mayor);
			asignado &= !dia.getExceptions_urg().contains(mayor);
			asignado &= (asignaciones_urg.get(mayor.getId()) < media(asignaciones_urg) + dif);
			//println("      \\__ asignaciones (" + mayor + ") = " + asignaciones_urg.get(mayor.getId()) + " < "
					//+ (media(asignaciones_urg) + dif) + " [" + media(asignaciones_urg) + " + " + dif + "] __/\n");
			// si la asignacion es correcta lo agregamos
			if (asignado) {
				//println("        URG Mayor -> " + mayor);
				dia.setURG_higher(mayor);
			}
			// Metemos residente en cola
			mayores.add(mayor);
			intento++;
			if (!asignado && intento > mayores.size()) {
				intento = 0;
				dif++;
				if (dif > DIF) {
					//println("    xxxxxxxxxxxxxxxxxxxxxxxxxxx probadas todas las opciones D:");
					return false;
				}
			}
		}
		Residente mayor = dia.getURG_higher();
		next(asignaciones, mayor.getId());
		next(asignaciones_urg, mayor.getId());
		//print("\n                Asignaciones: ");
		//printAssigns(asignaciones);
		// Agregar excpeciones
		dia.addException(mayor);
		calendario.addNextFridaysException_urg(mayor, dia.getDay());
		if (calendario.hasNext(dia.getDay())) {
			calendario.next(dia.getDay()).addException_urg(mayor);
		}
		// Si es R3 el otro no puede estar como menor
		if (mayor.isBoth()) {
			for (Residente r : RESIDENTES) {
				if (r.isBoth() && !r.equals(mayor)) {
					dia.addException_urg(r);
				}
			}
		}
		return true;
	}

	private static boolean asignarURG_menor(Calendario calendario, Dia dia,
			Queue<Residente> menores,
			Map<Integer, Integer> asignaciones,
			Map<Integer, Integer> asignaciones_urg)
			throws InterruptedException {

		boolean asignado = (dia.hasURG_minor());
		int intento = 0;
		int dif = 0;
		while (!asignado) {
			// Sacamos primer residente en cola
			Residente menor = menores.poll();
			//println("    probando " + menor + " ... ____________________________");
			// Comprobamos si la asignacion es correcta
			asignado = !dia.getAbsents().contains(menor);
			asignado &= !dia.getExceptions().contains(menor);
			asignado &= !dia.getExceptions_urg().contains(menor);
			asignado &= (asignaciones_urg.get(menor.getId()) < media(asignaciones_urg) + dif);
			//println("      \\__ asignaciones (" + menor + ") = " + asignaciones_urg.get(menor.getId()) + " < "
					//+ (media(asignaciones_urg) + dif) + " [" + media(asignaciones_urg) + " + " + dif + "] __/\n");
			// si la asignacion es correcta lo agregamos
			if (asignado) {
				//println("        URG Menor -> " + menor);
				dia.setURG_minor(menor);
			}
			// Metemos residente en cola
			menores.add(menor);
			intento++;
			if (!asignado && intento > menores.size()) {
				intento = 0;
				dif++;
				if (dif > DIF) {
					//println("    xxxxxxxxxxxxxxxxxxxxxxxxxxx probadas todas las opciones D:");
					return false;
				}
			}
		}
		Residente menor = dia.getURG_minor();
		next(asignaciones, menor.getId());
		next(asignaciones_urg, menor.getId());
		//print("\n                Asignaciones: ");
		//printAssigns(asignaciones);
		// Agregar excpeciones
		dia.addException(menor);
		calendario.addNextFridaysException_urg(menor, dia.getDay());
		if (calendario.hasNext(dia.getDay())) {
			calendario.next(dia.getDay()).addException_urg(menor);
		}
		return true;
	}

	private static boolean asignarTX_mayor(Dia dia,
			Queue<Residente> mayores,
			Map<Integer, Integer> asignaciones,
			Map<Integer, Integer> asignaciones_tx)
			throws InterruptedException {

		boolean asignado = (dia.hasTX_higher());
		int intento = 0;
		int dif = 0;
		while (!asignado) {
			// Sacamos primer residente en cola
			Residente mayor = mayores.poll();
			//println("    probando " + mayor + " ... ____________________________");
			// Comprobamos si la asignacion es correcta
			asignado = !dia.getAbsents().contains(mayor);
			asignado &= !dia.getExceptions().contains(mayor);
			asignado &= (asignaciones_tx.get(mayor.getId()) < media(asignaciones_tx) + dif);
			//println("      \\__ asignaciones (" + mayor + ") = " + asignaciones_tx.get(mayor.getId()) + " < "
					//+ (media(asignaciones_tx) + dif) + " [" + media(asignaciones_tx) + " + " + dif + "] __/\n");
			// si la asignacion es correcta lo agregamos
			if (asignado) {
				//println("         TX Mayor -> " + mayor);
				dia.setTX_higher(mayor);
			}
			// Metemos residente en cola
			mayores.add(mayor);
			intento++;
			if (!asignado && intento > mayores.size()) {
				intento = 0;
				dif++;
				if (dif > DIF) {
					//println("    xxxxxxxxxxxxxxxxxxxxxxxxxxx probadas todas las opciones D:");
					return false;
				}
			}
		}
		Residente mayor = dia.getTX_higher();
		next(asignaciones, mayor.getId());
		next(asignaciones_tx, mayor.getId());
		//print("\n                Asignaciones: ");
		//printAssigns(asignaciones);
		// Agregar excpeciones
		dia.addException(mayor);
		// Si es R3 el otro no puede estar como menor
		if (mayor.isBoth()) {
			for (Residente r : RESIDENTES) {
				if (r.isBoth() && !r.equals(mayor)) {
					dia.addException(r);
				}
			}
		}
		return true;
	}

	private static boolean asignarTX_menor(Dia dia,
			Queue<Residente> menores,
			Map<Integer, Integer> asignaciones,
			Map<Integer, Integer> asignaciones_tx)
			throws InterruptedException {

		boolean asignado = (dia.hasTX_minor());
		int intento = 0;
		int dif = 0;
		while (!asignado) {
			// Sacamos primer residente en cola
			Residente menor = menores.poll();
			//println("    probando " + menor + " ... ____________________________");
			// Comprobamos si la asignacion es correcta
			asignado = !dia.getAbsents().contains(menor);
			asignado &= !dia.getExceptions().contains(menor);
			asignado &= (asignaciones_tx.get(menor.getId()) < media(asignaciones_tx) + dif);
			//println("      \\__ asignaciones (" + menor + ") = " + asignaciones_tx.get(menor.getId()) + " < "
					//+ (media(asignaciones_tx) + dif) + " [" + media(asignaciones_tx) + " + " + dif + "] __/\n");
			// si la asignacion es correcta lo agregamos
			if (asignado) {
				//println("         TX Menor -> " + menor);
				dia.setTX_minor(menor);
			}
			// Metemos residente en cola
			menores.add(menor);
			intento++;
			if (!asignado && intento > menores.size()) {
				intento = 0;
				dif++;
				if (dif > DIF) {
					//println("    xxxxxxxxxxxxxxxxxxxxxxxxxxx probadas todas las opciones D:");
					return false;
				}
			}
		}
		Residente menor = dia.getTX_minor();
		next(asignaciones, menor.getId());
		next(asignaciones_tx, menor.getId());
		//print("\n                Asignaciones: ");
		//printAssigns(asignaciones);
		// Agregar excpeciones
		dia.addException(menor);
		return true;
	}

	private static void asignarFinde(Calendario calendario, Dia dia,
			Map<Integer, Integer> asignaciones,
			Map<Integer, Integer> asignaciones_tx,
			Map<Integer, Integer> asignaciones_urg)
			throws InterruptedException {

		if (calendario.hasNext(dia.getDay())) {
			Dia sabado = calendario.next(dia.getDay());
			// cogemos a los asignados del viernes:
			Residente urg_mayor = dia.getURG_higher();
			Residente urg_menor = dia.getURG_minor();
			Residente tx_mayor = dia.getTX_higher();
			Residente tx_menor = dia.getTX_minor();

			// seleccionamos residentes al sabado
			sabado.setURG_higher(tx_mayor);
			sabado.setURG_minor(tx_menor);
			sabado.setTX_higher(urg_mayor);
			sabado.setTX_minor(urg_menor);

			// aumentamos asignaciones
			next(asignaciones, urg_mayor.getId());
			next(asignaciones, urg_menor.getId());
			next(asignaciones, tx_mayor.getId());
			next(asignaciones, tx_menor.getId());
			// aumentamos asignaciones_urg
			next(asignaciones_urg, tx_mayor.getId());
			next(asignaciones_urg, tx_menor.getId());
			// aumentamos asignaciones_tx
			next(asignaciones_tx, urg_mayor.getId());
			next(asignaciones_tx, urg_menor.getId());

			if (calendario.hasNext(sabado.getDay())) {
				Dia domingo = calendario.next(sabado.getDay());
				// seleccionamos residentes al domingo
				domingo.setURG_higher(urg_mayor);
				domingo.setURG_minor(urg_menor);
				domingo.setTX_higher(tx_mayor);
				domingo.setTX_minor(tx_menor);

				// aumentamos asignaciones
				next(asignaciones, urg_mayor.getId());
				next(asignaciones, urg_menor.getId());
				next(asignaciones, tx_mayor.getId());
				next(asignaciones, tx_menor.getId());
				// aumentamos asignaciones_urg
				next(asignaciones_urg, urg_mayor.getId());
				next(asignaciones_urg, urg_menor.getId());
				// aumentamos asignaciones_tx
				next(asignaciones_tx, tx_mayor.getId());
				next(asignaciones_tx, tx_menor.getId());

				if (calendario.hasNext(domingo.getDay())) {
					Dia lunes = calendario.next(domingo.getDay());
					// el lunes no pueden tener guardia de urgencias
					lunes.addException_urg(urg_mayor);
					lunes.addException_urg(urg_menor);
					lunes.addException_urg(tx_mayor);
					lunes.addException_urg(tx_menor);
				}
			}
		}
	}

	private static Map<Integer, Integer> cloneMap(Map<Integer, Integer> m) {
		Map<Integer, Integer> n = new HashMap();
		for (Entry<Integer, Integer> i : m.entrySet()) {
			n.put(i.getKey(), i.getValue());
		}
		return n;
	}

	private static Queue<Residente> cloneQueue(Queue<Residente> queue) throws CloneNotSupportedException {
		Queue<Residente> clon = new ArrayDeque();
		for (Residente r : queue) {
			clon.add((Residente) r.clone());
		}
		return clon;
	}

	private static boolean poda(Calendario cal, int d) {
		while (cal.hasNext(d)) {
			if (!cal.isViable(d)) {
				return true;
			}
			d++;
		}
		return false;
	}

	private static void printAssigns(Map<Integer, Integer> m) {
		print("[");
		for (Integer i : m.values()) {
			print(i + " ");
		}
		print("] ... media = " + media(m));
		println();
	}

	private static void next(Map<Integer, Integer> m, Integer i) {
		m.put(i, m.get(i) + 1);
	}

	private static int media(Map<Integer, Integer> asignaciones) {
		int media = 0;
		for (Integer asign : asignaciones.values()) {
			media += asign;
		}
		media /= asignaciones.size();
		return media;
	}
	// </editor-fold>

}
