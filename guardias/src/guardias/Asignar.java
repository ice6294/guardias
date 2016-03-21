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

import static guardias.Utils.showAssignments;
import static guardias.Utils.showResidents;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import static guardias.Utils.addChars;
import static guardias.Utils.println;

/**
 * @version v1.0
 * @author luis
 */
public class Asignar {

	// PUBLIC ATTRIBUTES
	// <editor-fold desc="<------------------->">
	public static Integer SEED = 360;
	public static Integer DIF = 3;
	public static List<Residente> RESIDENTES = new ArrayList();
	// </editor-fold>

	// MASTER METHOD
	// <editor-fold desc="<------------------->">
	public static Calendario cthulhu(Integer year, Integer month, Integer seed,
			List<Residente> residentes,
			List<Pair<Integer, Residente>> ausentes,
			List<Pair<Integer, Residente>> obligatorios) {

		// Actualizamos las variables globales
		SEED = seed;
		RESIDENTES = residentes;

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

		// Creamos cola de residentes mayores
		Queue<Residente> mayores = new ArrayDeque();
		List<Residente> aux = new ArrayList();
		for (int i = 0; i < 6; i++) {
			aux.add(residentes.get(i));
		}
		// Randomizamos la cola mayores
		while (!aux.isEmpty()) {
			int n = (int) (random.nextDouble() * aux.size());
			mayores.add(aux.remove(n));
		}

		// Creamos cola de residentes menores
		Queue<Residente> menores = new ArrayDeque();
		List<Residente> aux2 = new ArrayList();
		int res_size = residentes.size();
		for (int i = 4; i < res_size; i++) {
			aux2.add(residentes.get(i));
		}
		// Randomizamos cola menores
		while (!aux2.isEmpty()) {
			int n = (int) (random.nextDouble() * aux2.size());
			menores.add(aux2.remove(n));
		}

		// Creamos lista de asignaciones
		int num_residentes = residentes.size();
		Integer[] asignaciones = new Integer[num_residentes];
		Integer[] asignaciones_urg = new Integer[num_residentes];
		Integer[] asignaciones_tx = new Integer[num_residentes];
		for (int i = 0; i < num_residentes; i++) {
			asignaciones[i] = 0;
			asignaciones_urg[i] = 0;
			asignaciones_tx[i] = 0;
		}

		// Hacemos la asignacion
		boolean asignado = false;
		try {
			asignado = probarOpciones(cal, cal.getCalendar().get(0), menores, mayores, asignaciones, asignaciones_urg, asignaciones_tx);
		} catch (CloneNotSupportedException | InterruptedException | NullPointerException exc) {
			System.err.printf("# ERROR (main): " + exc + "\r\n# " + cal.getYear() + " - " + cal.monthName() + " [SEED: " + SEED + " ]\r\n");
		}

		if (asignado) {
			println(Utils.INTRO);
			showResidents(residentes, ausentes, obligatorios);
			showAssignments(residentes, asignaciones, asignaciones_urg, asignaciones_tx);
			
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
			Integer[] asignaciones, Integer[] asignaciones_urg, Integer[] asignaciones_tx)
			throws CloneNotSupportedException, InterruptedException {

		// Hacemos copia de todo
		Calendario _calendario = (Calendario) calendario.clone();

		// Copiamos mayores
		Queue<Residente> _mayores = new ArrayDeque();
		for (Residente r : mayores) {
			_mayores.add((Residente) r.clone());
		}
		// Copiamos menores
		Queue<Residente> _menores = new ArrayDeque();
		for (Residente r : menores) {
			_menores.add((Residente) r.clone());
		}

		boolean asignado = false;
		boolean is_saturday;
		int it = 0;
		int jt = 0;
		while (!asignado) {
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
			try {
				asignado = asignar(calendario, dia, menores, mayores, asignaciones, asignaciones_urg, asignaciones_tx);
			} catch (CloneNotSupportedException | InterruptedException | NullPointerException exc) {
				System.err.printf("# ERROR (probarOpciones): " + exc + "\r\n# " + calendario.getYear() + " - " + calendario.monthName() + " [SEED: " + SEED + " ] ~ Intento: " + jt + " " + it);
				asignado = false;
			}
			if (!asignado) {
				calendario = _calendario;
				menores = _menores;
				mayores = _mayores;
			}

			it++;
			if (it > mayores.size()) {
				it = 0;
				jt++;
				if (jt > menores.size()) {
					return false;
				}
			}
		}
		return asignado;
	}

	private static boolean asignar(Calendario calendario, Dia dia,
			Queue<Residente> menores, Queue<Residente> mayores,
			Integer[] asignaciones, Integer[] asignaciones_urg, Integer[] asignaciones_tx)
			throws InterruptedException, CloneNotSupportedException {

		// Si es un dia fuera de rango (solo por seguridad)
		if (!calendario.getCalendar().containsKey(dia.getDay())) {
			return true;
		}
		if (dia.getWeek_day() < 5) {

			// LUNES | MARTES | MIÉRCOLES | JUEVES | VIERNES
			// URG_MAYOR
			if (!asignarURG_mayor(calendario, dia, mayores, asignaciones, asignaciones_urg)) {
				return false;
			}

			// URG_MENOR
			if (!asignarURG_menor(calendario, dia, menores, asignaciones, asignaciones_urg)) {
				return false;
			}
			// TX_MAYOR
			if (!asignarTX_mayor(dia, mayores, asignaciones, asignaciones_tx)) {
				return false;
			}

			// TX_MENOR
			if (!asignarTX_menor(dia, menores, asignaciones, asignaciones_tx)) {
				return false;
			}

			// VIERNES | SÁBADO | DOMINGO
			if (dia.getWeek_day() == 4) {
				asignarFinde(calendario, dia, asignaciones, asignaciones_tx, asignaciones_urg);
			}
		}
		if (calendario.hasNext(dia.getDay())) {
			return probarOpciones(calendario, calendario.next(dia.getDay()), menores, mayores, asignaciones, asignaciones_urg, asignaciones_tx);
		}
		return true;
	}

	private static boolean asignarURG_mayor(Calendario calendario, Dia dia,
			Queue<Residente> mayores, Integer[] asignaciones, Integer[] asignaciones_urg)
			throws InterruptedException {

		boolean asignado = (dia.hasURG_higher());
		int intento = 0;
		int dif = 0;
		while (!asignado) {
			// Sacamos primer residente en cola
			Residente mayor = mayores.poll();
			// Comprobamos si la asignacion es correcta
			asignado = !dia.getAbsents().contains(mayor);
			asignado &= !dia.getExceptions().contains(mayor);
			asignado &= !dia.getExceptions_urg().contains(mayor);
			asignado &= (asignaciones_urg[mayor.getId()] <= media(asignaciones_urg) + dif);
			// si la asignacion es correcta lo agregamos
			if (asignado) {
				dia.setURG_higher(mayor);
			}
			// Metemos residente en cola
			mayores.add(mayor);
			intento++;
			if (intento > mayores.size()) {
				intento = 0;
				if (dif > DIF) {
					return false;
				}
			}
			dif++;
		}
		Residente mayor = dia.getURG_higher();
		asignaciones[mayor.getId()]++;
		asignaciones_urg[mayor.getId()]++;
		dia.addException(mayor);
		calendario.addNextFridaysException_urg(mayor, dia.getDay());
		if (calendario.hasNext(dia.getDay())) {
			calendario.next(dia.getDay()).addException_urg(mayor);
		}
		// Si es R3 el otro no puede estar como menor
		if (mayor.getId() == 4) {
			dia.addException_urg(RESIDENTES.get(5));
		} else if (mayor.getId() == 5) {
			dia.addException_urg(RESIDENTES.get(4));
		}
		return true;
	}

	private static boolean asignarURG_menor(Calendario calendario, Dia dia,
			Queue<Residente> menores, Integer[] asignaciones, Integer[] asignaciones_urg)
			throws InterruptedException {

		boolean asignado = (dia.hasURG_minor());
		int intento = 0;
		int dif = 0;
		while (!asignado) {
			// Sacamos primer residente en cola
			Residente menor = menores.poll();
			// Comprobamos si la asignacion es correcta
			asignado = !dia.getAbsents().contains(menor);
			asignado &= !dia.getExceptions().contains(menor);
			asignado &= !dia.getExceptions_urg().contains(menor);
			asignado &= (asignaciones_urg[menor.getId()] <= media(asignaciones_urg) + dif);
			// si la asignacion es correcta lo agregamos
			if (asignado) {
				dia.setURG_minor(menor);
			}
			// Metemos residente en cola
			menores.add(menor);
			intento++;
			if (intento > menores.size()) {
				intento = 0;
				if (dif > DIF) {
					return false;
				}
			}
			dif++;
		}
		Residente menor = dia.getURG_minor();
		asignaciones[menor.getId()]++;
		asignaciones_urg[menor.getId()]++;
		dia.addException(menor);
		calendario.addNextFridaysException_urg(menor, dia.getDay());
		if (calendario.hasNext(dia.getDay())) {
			calendario.next(dia.getDay()).addException_urg(menor);
		}
		return true;
	}

	private static boolean asignarTX_mayor(Dia dia,
			Queue<Residente> mayores, Integer[] asignaciones, Integer[] asignaciones_tx)
			throws InterruptedException {

		boolean asignado = (dia.hasTX_higher());
		int intento = 0;
		int dif = 0;
		while (!asignado) {
			// Sacamos primer residente en cola
			Residente mayor = mayores.poll();
			// Comprobamos si la asignacion es correcta
			asignado = !dia.getAbsents().contains(mayor);
			asignado &= !dia.getExceptions().contains(mayor);
			asignado &= (asignaciones_tx[mayor.getId()] <= media(asignaciones_tx) + dif);
			// si la asignacion es correcta lo agregamos
			if (asignado) {
				dia.setTX_higher(mayor);
			}
			// Metemos residente en cola
			mayores.add(mayor);
			intento++;
			if (intento > mayores.size()) {
				intento = 0;
				if (dif > DIF) {
					return false;
				}
			}
			dif++;
		}
		Residente mayor = dia.getTX_higher();
		asignaciones[mayor.getId()]++;
		asignaciones_tx[mayor.getId()]++;
		dia.addException(mayor);
		// Si es R3 el otro no puede estar como menor
		if (mayor.getId() == 4) {
			dia.addException(RESIDENTES.get(5));
		} else if (mayor.getId() == 5) {
			dia.addException(RESIDENTES.get(4));
		}
		return true;
	}

	private static boolean asignarTX_menor(Dia dia,
			Queue<Residente> menores, Integer[] asignaciones, Integer[] asignaciones_tx)
			throws InterruptedException {

		boolean asignado = (dia.hasTX_minor());
		int intento = 0;
		int dif = 0;
		while (!asignado) {
			// Sacamos primer residente en cola
			Residente menor = menores.poll();
			// Comprobamos si la asignacion es correcta
			asignado = !dia.getAbsents().contains(menor);
			asignado &= !dia.getExceptions().contains(menor);
			asignado &= (asignaciones_tx[menor.getId()] <= media(asignaciones_tx) + dif);
			// si la asignacion es correcta lo agregamos
			if (asignado) {
				dia.setTX_minor(menor);
			}
			// Metemos residente en cola
			menores.add(menor);
			intento++;
			if (intento > menores.size()) {
				intento = 0;
				if (dif > DIF) {
					return false;
				}
			}
			dif++;
		}
		Residente menor = dia.getTX_minor();
		asignaciones[menor.getId()]++;
		asignaciones_tx[menor.getId()]++;
		dia.addException(menor);
		return true;
	}

	private static void asignarFinde(Calendario calendario, Dia dia,
			Integer[] asignaciones, Integer[] asignaciones_tx, Integer[] asignaciones_urg)
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
			asignaciones[urg_mayor.getId()]++;
			asignaciones[urg_menor.getId()]++;
			asignaciones[tx_mayor.getId()]++;
			asignaciones[tx_menor.getId()]++;
			// aumentamos asignaciones_urg
			asignaciones_urg[tx_mayor.getId()]++;
			asignaciones_urg[tx_menor.getId()]++;
			// aumentamos asignaciones_tx
			asignaciones_tx[urg_mayor.getId()]++;
			asignaciones_tx[urg_menor.getId()]++;

			if (calendario.hasNext(sabado.getDay())) {
				Dia domingo = calendario.next(sabado.getDay());
				// seleccionamos residentes al domingo
				domingo.setURG_higher(urg_mayor);
				domingo.setURG_minor(urg_menor);
				domingo.setTX_higher(tx_mayor);
				domingo.setTX_minor(tx_menor);

				// aumentamos asignaciones
				asignaciones[urg_mayor.getId()]++;
				asignaciones[urg_menor.getId()]++;
				asignaciones[tx_mayor.getId()]++;
				asignaciones[tx_menor.getId()]++;
				// aumentamos asignaciones_urg
				asignaciones_urg[urg_mayor.getId()]++;
				asignaciones_urg[urg_menor.getId()]++;
				// aumentamos asignaciones_tx
				asignaciones_tx[tx_mayor.getId()]++;
				asignaciones_tx[tx_menor.getId()]++;

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

	private static int media(Integer[] asignaciones) {
		int media = 0;
		for (Integer asign : asignaciones) {
			media += asign;
		}
		media /= asignaciones.length;
		return media;
	}
	// </editor-fold>

}
