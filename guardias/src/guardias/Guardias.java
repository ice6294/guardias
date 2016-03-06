package guardias;

import static guardias.Utils.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Random;

/**
 *
 * @author luis
 */
public class Guardias {

	// GLOBAL ATTRIBUTES
	public static List<Calendario> resultados = new ArrayList();
	public static List<Residente> residentes = new ArrayList();
	public static int num_residentes = 0;

	public static final int YEAR = 2016;
	public static final int MONTH = 3;
	public static final int SEED = 360;
	public static final int DIF = 4;

	/**
	 * @param args the command line arguments
	 * @throws java.lang.InterruptedException
	 * @throws java.lang.CloneNotSupportedException
	 */
	public static void main(String[] args) throws InterruptedException, CloneNotSupportedException {
		
		MenuPrincipal mp = new MenuPrincipal();

		// Inicializar calendario
		Calendario cal = new Calendario(YEAR, MONTH, SEED);

		// <editor-fold desc="// Inicializar residentes">
		Residente res0 = new Residente("Elena", 0); // R5
		Residente res1 = new Residente("Natalia", 1); // R5
		Residente res2 = new Residente("Javi", 2); // R4
		Residente res3 = new Residente("Pablo", 3); // R4
		Residente res4 = new Residente("Rosaura", 4); // R3
		Residente res5 = new Residente("Joaquin", 5); // R3
		Residente res6 = new Residente("Dani", 6); // R2
		Residente res7 = new Residente("Pau", 7); // R2
		Residente res8 = new Residente("Laura", 8); // R1
		Residente res9 = new Residente("Carmen", 9); // R1
		// </editor-fold>

		// <editor-fold desc="// Comprobando calendario">
		println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ CALENDARIO ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		for (Entry<Integer, Dia> e : cal.getCalendar().entrySet()) {
			println(e.getKey() + ": " + e.getValue() + " [" + e.getValue().getWeek_day() + "]");
		}
		// Thread.sleep(1000);
		// </editor-fold>

		// <editor-fold desc="// Agregamos Ausentes">
		cal.addAbsent(res1, 17);
		cal.addAbsent(res1, 18);
		//cal.addAbsent(res1, 19);
		//cal.addAbsent(res1, 20);
		cal.addAbsent(res1, 21);
		//cal.addAbsent(res1, 22);
		//cal.addAbsent(res1, 23);
		cal.addAbsent(res1, 24);
		cal.addAbsent(res1, 25);
		cal.addAbsent(res1, 26);
		//cal.addAbsent(res1, 27);

		cal.addAbsent(res2, 7);
		cal.addAbsent(res2, 8);
		cal.addAbsent(res2, 9);

		cal.addAbsent(res3, 13);
		cal.addAbsent(res3, 14);
		cal.addAbsent(res3, 18);
		cal.addAbsent(res3, 20);

		cal.addAbsent(res5, 0);
		cal.addAbsent(res5, 1);

		cal.addAbsent(res7, 23);
		cal.addAbsent(res7, 24);
		cal.addAbsent(res7, 25);

		cal.addAbsent(res9, 14);
		// </editor-fold>

		// <editor-fold desc="// Creamos obligatorios">
		cal.addSelectedURG_minor(res9, 22);
		cal.addSelectedURG_minor(res9, 24);

		cal.addSelectedTX_minor(res9, 21);
		cal.addSelectedTX_minor(res9, 23);
		// </editor-fold>

		// <editor-fold desc="// Creamos lista de residentes">
		residentes.add(res0);
		residentes.add(res1);
		residentes.add(res2);
		residentes.add(res3);
		residentes.add(res4);
		residentes.add(res5);
		residentes.add(res6);
		residentes.add(res7);
		residentes.add(res8);
		residentes.add(res9);
		// </editor-fold>

		// Randomize with seed
		Random random = new Random(SEED);

		// <editor-fold desc="// RESIDENTES">
		println();
		println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ RESIDENTES ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		// Comprobamos lista de residentes
		int pos = 1;
		for (Residente r : residentes) {
			println(pos + ". " + r.toString());
			pos++;
		}
		// Thread.sleep(1000);
		// </editor-fold>

		// <editor-fold desc="// MAYORES">
		println();
		println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ MAYORES ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		// Creamos cola de residentes mayores
		Queue<Residente> mayores = new ArrayDeque();
		List<Residente> aux = new ArrayList();
		for (int i = 0; i < 6; i++) {
			aux.add(residentes.get(i));
		}

		// Comprobamos lista de residentes mayores
		int j = 0;
		for (Residente r : mayores) {
			println(j + ": " + r.toString());
			j++;
		}
		// Thread.sleep(1000);
		// Randomizamos la cola mayores
		while (!aux.isEmpty()) {
			int n = (int) (random.nextDouble() * aux.size());
			println("Random: " + n + " - " + aux.get(n).toString());
			mayores.add(aux.remove(n));
		}
		// </editor-fold>

		// <editor-fold desc="// PEQUEÑOS">
		println();
		println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ PEQUEÑOS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		// Creamos cola de residentes pequeños
		Queue<Residente> menores = new ArrayDeque();
		List<Residente> aux2 = new ArrayList();
		for (int i = 4; i < residentes.size(); i++) {
			aux2.add(residentes.get(i));
		}

		// Comprobamos lista de residentes pequeños
		j = 0;
		for (Residente r : menores) {
			println(j + ": " + r.toString());
			j++;
		}
		// Thread.sleep(1000);
		// Aleatorizamos cola de residentes pequeños
		while (!aux2.isEmpty()) {
			//int n = (int) (Math.random() * aux2.size());
			int n = (int) (random.nextDouble() * aux2.size());
			println("Random: " + n + " - " + aux2.get(n).toString());
			menores.add(aux2.remove(n));
		}
		println();
		// </editor-fold>

		// <editor-fold desc="// Creamos lista de asignaciones">
		num_residentes = residentes.size();
		Integer[] asignaciones = new Integer[num_residentes];
		Integer[] asignaciones_urg = new Integer[num_residentes];
		Integer[] asignaciones_tx = new Integer[num_residentes];
		for (int i = 0; i < num_residentes; i++) {
			asignaciones[i] = 0;
			asignaciones_urg[i] = 0;
			asignaciones_tx[i] = 0;
		}
		// </editor-fold>

		// <editor-fold desc="// ASIGNACIÓN">
		println("\n\n");
		println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ ASIGNAR ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ ");
		//if (asignar(cal, cal.getCalendar().get(0), menores, mayores, asignaciones, asignaciones_urg, asignaciones_tx)) {
		boolean asignado = false;
		try {
			asignado = probarOpciones(cal, cal.getCalendar().get(0), menores, mayores, asignaciones, asignaciones_urg, asignaciones_tx);
		} catch (CloneNotSupportedException | InterruptedException | NullPointerException exc) {
			System.err.printf("# ERROR (main): " + exc + "\n# " + cal.getYear() + " - " + cal.monthName() + " [SEED: " + SEED + " ]\n");
		}
		if (asignado) {
			println("\n\n");
			println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ " + cal.monthName().toUpperCase() + " ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			println(cal.toString());
			println("Nº de asignaciones totales: " + Arrays.toString(asignaciones));
			println("Nº de asignaciones urg:     " + Arrays.toString(asignaciones_urg));
			println("Nº de asignaciones tx:      " + Arrays.toString(asignaciones_tx));
			println("\n\n");
		} else {
			println("\n\n ERROR. No se pudieron hacer las asignaciones");
		}
		// </editor-fold>

	}

	private static boolean probarOpciones(Calendario calendario, Dia dia,
			Queue<Residente> menores, Queue<Residente> mayores,
			Integer[] asignaciones, Integer[] asignaciones_urg, Integer[] asignaciones_tx)
			throws CloneNotSupportedException, InterruptedException {

		// Hacemos copia de todo
		Calendario _calendario = (Calendario) calendario.clone();
		Integer[] _asignaciones = asignaciones.clone();
		Integer[] _asignaciones_urg = asignaciones_urg.clone();
		Integer[] _asignaciones_tx = asignaciones_tx.clone();

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
		boolean primer_intento = false;
		int it = 0;
		int jt = 0;
		while (!asignado) {
			// i -> mayores / j -> menores
			println("\n");
			printlnColor("GREEN", "Dia: " + dia.toString() + " ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			printlnColor("YELLOW", "Vuelta: " + jt + " - " + it);
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
			// Probamos combinación
			if (primer_intento) {
				printlnColor("RED", "################# Probamos siguiente combinación ...");
				println("   > Mayores: " + _mayores.toString());
				println("   > Menores: " + _menores.toString());
				println();
				//Thread.sleep(2000);
			} else {
				primer_intento = true;
			}
			// Si es Sábado pero existe Domingo y Lunes, saltamos directamente al Lunes
			if (dia.getWeek_day() == 5
					&& calendario.next(dia.getDay()) != null
					&& calendario.next(calendario.next(dia.getDay()).getDay()) != null) {
				dia = calendario.next(calendario.next(dia.getDay()).getDay());
			}
			// Ejecutamos combinación
			try {
				asignado = asignar(calendario, dia, menores, mayores, asignaciones, asignaciones_urg, asignaciones_tx);
			} catch (CloneNotSupportedException | InterruptedException | NullPointerException exc) {
				System.err.printf("# ERROR (probarOpciones): " + exc + "\n# " + calendario.getYear() + " - " + calendario.monthName() + " [SEED: " + SEED + " ] ~ Intento: " + jt + " " + it);
				asignado = false;
			}
			if (!asignado) {
				println();
				printlnColor("RED", "_________________ Comprobamos:");
				println("> dia : " + dia.toString());
				println("> asignaciones : " + Arrays.toString(asignaciones) + "\t ---------+");
				println("> _asignaciones: " + Arrays.toString(_asignaciones) + "\t <--------+");
				println("   > Mayores: " + _mayores.toString());
				println("   > Menores: " + _menores.toString());
				println();

				calendario = _calendario;
				menores = _menores;
				mayores = _mayores;
				asignaciones = _asignaciones;
				asignaciones_urg = _asignaciones_urg;
				asignaciones_tx = _asignaciones_tx;
			}

			it++;
			if (it > mayores.size()) {
				it = 0;
				jt++;
				if (jt > menores.size()) {
					println("NADA. HABRÁ QUE VOLVER UN PASO ATRÁS!!!!!!!!!!!!!!!!!!!!!!!!!");
					println();
					//Thread.sleep(2000);
					return false;
				}
			}
		}
		return asignado;
	}

	// args: calendario, dia, residentes menores, residentes mayores y lista asignaciones
	public static boolean asignar(Calendario calendario, Dia dia,
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
			println("    · URG mayor asignado    : " + dia.getURG_higher());
			print("\t· Ausentes          : ");
			printlnColor("PURPLE", dia.getAbsents().toString());
			print("\t· Excepciones       : ");
			printlnColor("YELLOW", dia.getExceptions().toString());
			print("\t· Excepciones URG   : ");
			printlnColor("YELLOW", dia.getExceptions().toString());
			println();
			//Thread.sleep(100);

			// URG_MENOR
			if (!asignarURG_menor(calendario, dia, menores, asignaciones, asignaciones_urg)) {
				return false;
			}
			println("    · URG pequeño asignado  : " + dia.getURG_minor());
			print("\t· Ausentes          : ");
			printlnColor("PURPLE", dia.getAbsents().toString());
			print("\t· Excepciones       : ");
			printlnColor("YELLOW", dia.getExceptions().toString());
			print("\t· Excepciones URG   : ");
			printlnColor("YELLOW", dia.getExceptions().toString());
			println();
			//Thread.sleep(100);

			// TX_MAYOR
			if (!asignarTX_mayor(calendario, dia, mayores, asignaciones, asignaciones_tx)) {
				return false;
			}
			println("    · TX mayor asignado  : " + dia.getTX_higher());
			print("\t· Ausentes         : ");
			printlnColor("PURPLE", dia.getAbsents().toString());
			print("\t· Excepciones      : ");
			printlnColor("YELLOW", dia.getExceptions().toString());
			print("\t· Excepciones URG  : ");
			printlnColor("YELLOW", dia.getExceptions().toString());
			println();
			//Thread.sleep(100);

			// TX_MENOR
			if (!asignarTX_menor(calendario, dia, menores, asignaciones, asignaciones_tx)) {
				return false;
			}
			println("    · TX pequeño asignado  : " + dia.getTX_minor());
			print("\t· Ausentes         : ");
			printlnColor("PURPLE", dia.getAbsents().toString());
			print("\t· Excepciones      : ");
			printlnColor("YELLOW", dia.getExceptions().toString());
			print("\t· Excepciones URG  : ");
			printlnColor("YELLOW", dia.getExceptions().toString());
			println();
			//Thread.sleep(100);

			// VIERNES | SÁBADO | DOMINGO
			if (dia.getWeek_day() == 4) {
				asignarFinde(calendario, dia, asignaciones, asignaciones_tx, asignaciones_urg);
			}
		}
		println(Arrays.toString(asignaciones));
		println();
		if (calendario.hasNext(dia.getDay())) {
			return probarOpciones(calendario, calendario.next(dia.getDay()), menores, mayores, asignaciones, asignaciones_urg, asignaciones_tx);
		} else {
			println("\n");
			printlnColor("GREEN", "> FIN de asignación");
			return true;
		}
	}

	private static boolean asignarURG_mayor(Calendario calendario, Dia dia,
			Queue<Residente> mayores, Integer[] asignaciones, Integer[] asignaciones_urg)
			throws InterruptedException {

		print("  URG_Mayor AUSENCIAS       : ");
		printlnColor("BLUE", dia.getAbsents().toString());
		print("  URG_Mayor EXCEPCIONES     : ");
		printlnColor("CYAN", dia.getExceptions().toString());
		print("  URG_Mayor EXCEPCIONES_URG : ");
		printlnColor("CYAN", dia.getExceptions().toString());
		println();

		boolean asignado = (dia.hasURG_higher());
		int intento = 0;
		int dif = 0;
		while (!asignado) {
			// Sacamos primer residente en cola
			Residente mayor = mayores.poll();
			// Comprobamos si la asignación es correcta
			asignado = !dia.getAbsents().contains(mayor);
			asignado &= !dia.getExceptions().contains(mayor);
			asignado &= !dia.getExceptions_urg().contains(mayor);
			asignado &= (asignaciones_urg[mayor.getNumber()] < media(asignaciones_urg) + dif);
			// si la asignación es correcta lo agregamos
			if (asignado) {
				dia.setURG_higher(mayor);
			}
			// Metemos residente en cola
			mayores.add(mayor);
			intento++;
			if (intento > mayores.size() && dif > DIF) {
				printlnColor("RED", "# Por aquí no!!! (asignarURG_mayor)");
				//Thread.sleep(1000);
				return false;
			}
			dif++;
		}
		Residente mayor = dia.getURG_higher();
		asignaciones[mayor.getNumber()]++;
		asignaciones_urg[mayor.getNumber()]++;
		dia.addException(mayor);
		calendario.addNextFridaysException_urg(mayor, dia.getDay()); // NEW <----------
		if (calendario.hasNext(dia.getDay())) {
			calendario.next(dia.getDay()).addException_urg(mayor);
		}
		// Si es R3 el otro no puede estar como pequeño
		if (mayor.getNumber() == 4) {
			dia.addException_urg(residentes.get(5));
		} else if (mayor.getNumber() == 5) {
			dia.addException_urg(residentes.get(4));
		}
		return true;
	}

	private static boolean asignarURG_menor(Calendario calendario, Dia dia,
			Queue<Residente> menores, Integer[] asignaciones, Integer[] asignaciones_urg)
			throws InterruptedException {

		print("  URG_Menor AUSENCIAS       : ");
		printlnColor("BLUE", dia.getAbsents().toString());
		print("  URG_Menor EXCEPCIONES     : ");
		printlnColor("CYAN", dia.getExceptions().toString());
		print("  URG_Menor EXCEPCIONES_URG : ");
		printlnColor("CYAN", dia.getExceptions().toString());
		println();

		boolean asignado = (dia.hasURG_minor());
		int intento = 0;
		int dif = 0;
		while (!asignado) {
			// Sacamos primer residente en cola
			Residente menor = menores.poll();
			// Comprobamos si la asignación es correcta
			asignado = !dia.getAbsents().contains(menor);
			asignado &= !dia.getExceptions().contains(menor);
			asignado &= !dia.getExceptions_urg().contains(menor);
			asignado &= (asignaciones_urg[menor.getNumber()] < media(asignaciones_urg) + dif);
			// si la asignación es correcta lo agregamos
			if (asignado) {
				dia.setURG_minor(menor);
			}
			// Metemos residente en cola
			menores.add(menor);
			intento++;
			if (intento > menores.size() && dif > DIF) {
				printlnColor("RED", "# Por aquí no!!! (asignarURG_menor)\n");
				//Thread.sleep(1000);
				return false;
			}
			dif++;
		}
		Residente menor = dia.getURG_minor();
		asignaciones[menor.getNumber()]++;
		asignaciones_urg[menor.getNumber()]++;
		dia.addException(menor);
		calendario.addNextFridaysException_urg(menor, dia.getDay()); // NEW <----------
		if (calendario.hasNext(dia.getDay())) {
			calendario.next(dia.getDay()).addException_urg(menor);
		}
		return true;
	}

	private static boolean asignarTX_mayor(Calendario calendario, Dia dia,
			Queue<Residente> mayores, Integer[] asignaciones, Integer[] asignaciones_tx)
			throws InterruptedException {

		print("  TX_Mayor AUSENCIAS       : ");
		printlnColor("BLUE", dia.getAbsents().toString());
		print("  TX_Mayor EXCEPCIONES     : ");
		printlnColor("CYAN", dia.getExceptions().toString());
		print("  TX_Mayor EXCEPCIONES_URG : ");
		printlnColor("CYAN", dia.getExceptions().toString());
		println();

		boolean asignado = (dia.hasTX_higher());
		int intento = 0;
		int dif = 0;
		while (!asignado) {
			// Sacamos primer residente en cola
			Residente mayor = mayores.poll();
			// Comprobamos si la asignación es correcta
			asignado = !dia.getAbsents().contains(mayor);
			asignado &= !dia.getExceptions().contains(mayor);
			asignado &= (asignaciones_tx[mayor.getNumber()] < media(asignaciones_tx) + dif);
			// si la asignación es correcta lo agregamos
			if (asignado) {
				dia.setTX_higher(mayor);
			}
			// Metemos residente en cola
			mayores.add(mayor);
			intento++;
			if (intento > mayores.size() && dif > DIF) {
				printlnColor("RED", "# Por aquí no!!! (asignarTX_mayor)");
				//Thread.sleep(1000);
				return false;
			}
			dif++;
		}
		Residente mayor = dia.getTX_higher();
		asignaciones[mayor.getNumber()]++;
		asignaciones_tx[mayor.getNumber()]++;
		dia.addException(mayor);
		// Si es R3 el otro no puede estar como pequeño
		if (mayor.getNumber() == 4) {
			dia.addException(residentes.get(5));		// TODO crear en calendario lista excepciones_tx!!!?!?!?!?!!
		} else if (mayor.getNumber() == 5) {
			dia.addException(residentes.get(4));
		}
		return true;
	}

	private static boolean asignarTX_menor(Calendario calendario, Dia dia,
			Queue<Residente> menores, Integer[] asignaciones, Integer[] asignaciones_tx)
			throws InterruptedException {

		print("  TX_Minor AUSENCIAS       : ");
		printlnColor("BLUE", dia.getAbsents().toString());
		print("  TX_Minor EXCEPCIONES     : ");
		printlnColor("CYAN", dia.getExceptions().toString());
		print("  TX_Minor EXCEPCIONES_URG : ");
		printlnColor("CYAN", dia.getExceptions().toString());
		println();

		boolean asignado = (dia.hasTX_minor());	// NEW
		int intento = 0;
		int dif = 0;
		while (!asignado) {
			// Sacamos primer residente en cola
			Residente menor = menores.poll();
			// Comprobamos si la asignación es correcta
			asignado = !dia.getAbsents().contains(menor);
			asignado &= !dia.getExceptions().contains(menor);
			asignado &= (asignaciones_tx[menor.getNumber()] < media(asignaciones_tx) + dif);
			// si la asignación es correcta lo agregamos
			if (asignado) {
				dia.setTX_minor(menor);
			}
			// Metemos residente en cola
			menores.add(menor);
			intento++;
			if (intento > menores.size() && dif > DIF) {
				println("# Por aquí no!!! (asignarTX_menor)");
				//Thread.sleep(1000);
				return false;
			}
			dif++;
		}
		Residente menor = dia.getTX_minor();
		asignaciones[menor.getNumber()]++;
		asignaciones_tx[menor.getNumber()]++;
		dia.addException(menor);
		return true;
	}

	private static void asignarFinde(Calendario calendario, Dia dia,
			Integer[] asignaciones, Integer[] asignaciones_tx, Integer[] asignaciones_urg)
			throws InterruptedException {

		if (calendario.hasNext(dia.getDay())) {
			Dia sabado = calendario.next(dia.getDay());
			// del viernes:
			Residente urg_mayor = dia.getURG_higher();
			Residente urg_menor = dia.getURG_minor();
			Residente tx_mayor = dia.getTX_higher();
			Residente tx_menor = dia.getTX_minor();

			// seleccionamos residentes al sábado
			sabado.setURG_higher(tx_mayor);
			sabado.setURG_minor(tx_menor);
			sabado.setTX_higher(urg_mayor);
			sabado.setTX_minor(urg_menor);

			// aumentamos asignaciones
			asignaciones[urg_mayor.getNumber()]++;
			asignaciones[urg_menor.getNumber()]++;
			asignaciones[tx_mayor.getNumber()]++;
			asignaciones[tx_menor.getNumber()]++;
			// aumentamos asignaciones_urg
			asignaciones_urg[tx_mayor.getNumber()]++;
			asignaciones_urg[tx_menor.getNumber()]++;
			// aumentamos asignaciones_tx
			asignaciones_tx[urg_mayor.getNumber()]++;
			asignaciones_tx[urg_menor.getNumber()]++;

			if (calendario.hasNext(sabado.getDay())) {
				Dia domingo = calendario.next(sabado.getDay());
				// seleccionamos residentes al domingo
				domingo.setURG_higher(urg_mayor);
				domingo.setURG_minor(urg_menor);
				domingo.setTX_higher(tx_mayor);
				domingo.setTX_minor(tx_menor);

				// aumentamos asignaciones
				asignaciones[urg_mayor.getNumber()]++;
				asignaciones[urg_menor.getNumber()]++;
				asignaciones[tx_mayor.getNumber()]++;
				asignaciones[tx_menor.getNumber()]++;
				// aumentamos asignaciones_urg
				asignaciones_urg[urg_mayor.getNumber()]++;
				asignaciones_urg[urg_menor.getNumber()]++;
				// aumentamos asignaciones_tx
				asignaciones_tx[tx_mayor.getNumber()]++;
				asignaciones_tx[tx_menor.getNumber()]++;

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

	private static int mediana(Integer[] asignaciones) {
		Integer[] nuevo = asignaciones.clone();
		Arrays.sort(nuevo);
		return nuevo[nuevo.length / 2];
	}

}
