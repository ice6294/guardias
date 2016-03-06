package guardias;

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
	public static final int YEAR = 2016;
	public static final int MONTH = 10;
	public static final int SEED = 456;
	public static final int DIF = 4;

	/**
	 * @param args the command line arguments
	 * @throws java.lang.InterruptedException
	 * @throws java.lang.CloneNotSupportedException
	 */
	public static void main(String[] args) throws InterruptedException, CloneNotSupportedException {

		// Inicializar residentes
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

		// Inicializar calendario
		Calendario cal = new Calendario(YEAR, MONTH); // Julio 2015 <---------------

		// Comprobando calendario
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ CALENDARIO ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		for (Entry<Integer, Dia> e : cal.getCalendar().entrySet()) {
			System.out.println(e.getKey() + ": " + e.getValue() + " [" + e.getValue().getWeek_day() + "]");
		}
		// Thread.sleep(1000);

		// Agregamos Ausentes
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

		// Creamos lista de residentes
		List<Residente> residentes = new ArrayList();
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

		// SEED
		Random random = new Random(SEED);

		// RESIDENTES
		System.out.println();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ RESIDENTES ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		// Comprobamos lista de residentes
		int pos = 1;
		for (Residente r : residentes) {
			System.out.println(pos + ". " + r.toString());
			pos++;
		}
		// Thread.sleep(1000);

		// MAYORES
		System.out.println();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ MAYORES ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		// Creamos cola de residentes mayores
		Queue<Residente> mayores = new ArrayDeque();
		List<Residente> aux = new ArrayList();
		for (int i = 0; i < 6; i++) {
			aux.add(residentes.get(i));
		}

		// Comprobamos lista de residentes mayores
		int j = 0;
		for (Residente r : mayores) {
			System.out.println(j + ": " + r.toString());
			j++;
		}
		// Thread.sleep(1000);
		// Randomizamos la cola mayores
		while (!aux.isEmpty()) {
			int n = (int) (random.nextDouble() * aux.size());
			System.out.println("Random: " + n + " - " + aux.get(n).toString());
			mayores.add(aux.remove(n));
		}

		// PEQUEÑOS
		System.out.println();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ PEQUEÑOS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		// Creamos cola de residentes pequeños
		Queue<Residente> menores = new ArrayDeque();
		List<Residente> aux2 = new ArrayList();
		for (int i = 4; i < residentes.size(); i++) {
			aux2.add(residentes.get(i));
		}

		// Comprobamos lista de residentes pequeños
		j = 0;
		for (Residente r : menores) {
			System.out.println(j + ": " + r.toString());
			j++;
		}
		// Thread.sleep(1000);
		// Aleatorizamos cola de residentes pequeños
		while (!aux2.isEmpty()) {
			//int n = (int) (Math.random() * aux2.size());
			int n = (int) (random.nextDouble() * aux2.size());
			System.out.println("Random: " + n + " - " + aux2.get(n).toString());
			menores.add(aux2.remove(n));
		}
		System.out.println();

		// Creamos lista de asignaciones
		Integer[] asignaciones = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		Integer[] asignaciones_urg = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		Integer[] asignaciones_tx = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

		// Asignar
		System.out.println("\n\n");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ ASIGNAR ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ ");
		//if (asignar(cal, cal.getCalendar().get(0), menores, mayores, asignaciones, asignaciones_urg, asignaciones_tx)) {
		boolean asignado = false;
		try {
			asignado = probarOpciones(cal, cal.getCalendar().get(0), menores, mayores, asignaciones, asignaciones_urg, asignaciones_tx);
		} catch (CloneNotSupportedException | InterruptedException | NullPointerException exc) {
			System.err.printf("# ERROR (main): " + exc + "\n# " + cal.getYear() + " - " + cal.monthName() + " [SEED: " + SEED + " ]\n");
		}
		if (asignado) {
			System.out.println("\n\n");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ " + cal.monthName().toUpperCase() + " ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println(cal);
			System.out.println("Nº de asignaciones totales: " + Arrays.toString(asignaciones));
			System.out.println("Nº de asignaciones urg:     " + Arrays.toString(asignaciones_urg));
			System.out.println("Nº de asignaciones tx:      " + Arrays.toString(asignaciones_tx));
			System.out.println("\n\n");
		} else {
			System.out.println("\n\n ERROR. No se pudieron hacer las asignaciones");
		}

	}

	private static boolean probarOpciones(Calendario calendario, Dia dia,
			Queue<Residente> menores, Queue<Residente> mayores,
			Integer[] asignaciones, Integer[] asignaciones_urg, Integer[] asignaciones_tx)
			throws CloneNotSupportedException, InterruptedException {

		// Hacemos copia de todo
		Calendario _calendario = (Calendario) calendario.clone();
		//Dia _dia = (Dia) dia.clone();
		//Integer _dia = dia.getDay();
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
			System.out.println("\u001B[32mDia: " + dia.toString() + "\u001B[0m");
			System.out.println("\u001B[33mVuelta: " + jt + " - " + it + "\u001B[0m");
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
				System.out.println("################# Probamos siguiente combinación ...");
				System.out.println("   > Mayores: " + _mayores.toString());
				System.out.println("   > Menores: " + _menores.toString());
				System.out.println();
				Thread.sleep(2000);
			} else {
				primer_intento = true;
			}
			if (dia.getWeek_day() == 5
					&& calendario.next(dia.getDay()) != null
					&& calendario.next(calendario.next(dia.getDay()).getDay()) != null) {
				dia = calendario.next(calendario.next(dia.getDay()).getDay());
			}
			try {
				asignado = asignar(calendario, dia, menores, mayores, asignaciones, asignaciones_urg, asignaciones_tx);
			} catch (CloneNotSupportedException | InterruptedException | NullPointerException exc) {
				System.err.printf("# ERROR (probarOpciones): " + exc + "\n# " + calendario.getYear() + " - " + calendario.monthName() + " [SEED: " + SEED + " ] ~ Intento: " + jt + " " + it);
				asignado = false;
			}
			if (!asignado) {
				System.out.println("");
				System.out.println("_________________ Comprobamos:");
				System.out.println("> dia : " + dia.toString());
				System.out.println("> asignaciones : " + Arrays.toString(asignaciones) + "\t ---------+");
				System.out.println("> _asignaciones: " + Arrays.toString(_asignaciones) + "\t <--------+");
				System.out.println("   > Mayores: " + _mayores.toString());
				System.out.println("   > Menores: " + _menores.toString());
				System.out.println("");

				calendario = _calendario;
				//dia = calendario.getDia(_dia);//_dia;
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
					System.out.println("NADA. HABRÁ QUE VOLVER UN PASO ATRÁS!!!!!!!!!!!!!!!!!!!!!!!!!");
					System.out.println("");
					Thread.sleep(2000);
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

		System.out.println("[" + dia + "] ");
		// Si es un dia fuera de rango (solo por seguridad)
		if (!calendario.getCalendar().containsKey(dia.getDay())) {
			return true;
		}
		if (dia.getWeek_day() < 5) {
			// Lunes, Martes, Miércoles, Jueves ó Viernes
			// URG_MAYOR
			if (!asignarURG_mayor(calendario, dia, mayores, asignaciones, asignaciones_urg)) {
				return false;
			}
			System.out.println("    · URG mayor asignado: " + dia.getURG_higher());
			System.out.println("\t· Ausentes       : " + dia.getAbsents()+ "\n");
			System.out.println("\t· Excepciones    : " + dia.getExceptions() + "\n");
			System.out.println("\t· Excepciones URG: " + dia.getExceptions_urg() + "\n");
			//Thread.sleep(100);

			// URG_MENOR
			if (!asignarURG_menor(calendario, dia, menores, asignaciones, asignaciones_urg)) {
				return false;
			}
			System.out.println("    · URG pequeño asignado: " + dia.getURG_minor());
			System.out.println("\t· Ausentes       : " + dia.getAbsents()+ "\n");
			System.out.println("\t· Excepciones    : " + dia.getExceptions() + "\n");
			System.out.println("\t· Excepciones URG: " + dia.getExceptions_urg() + "\n");
			//Thread.sleep(100);

			// TX_MAYOR
			if (!asignarTX_mayor(calendario, dia, mayores, asignaciones, asignaciones_tx)) {
				return false;
			}
			System.out.println("    · TX mayor asignado: " + dia.getTX_higher());
			System.out.println("\t· Ausentes       : " + dia.getAbsents()+ "\n");
			System.out.println("\t· Excepciones    : " + dia.getExceptions() + "\n");
			System.out.println("\t· Excepciones URG: " + dia.getExceptions_urg() + "\n");
			//Thread.sleep(100);

			// TX_MENOR
			if (!asignarTX_menor(calendario, dia, menores, asignaciones, asignaciones_tx)) {
				return false;
			}
			System.out.println("    · TX pequeño asignado: " + dia.getTX_minor());
			System.out.println("\t· Ausentes       : " + dia.getAbsents()+ "\n");
			System.out.println("\t· Excepciones    : " + dia.getExceptions() + "\n");
			System.out.println("\t· Excepciones URG: " + dia.getExceptions_urg() + "\n");
			//Thread.sleep(100);

			// Viernes, Sabado ó Domingo
			if (dia.getWeek_day() == 4) {
				asignarFinde(calendario, dia, asignaciones, asignaciones_tx, asignaciones_urg);
			}
		}
		System.out.println(Arrays.toString(asignaciones) + "\n");
		if (calendario.hasNext(dia.getDay())) {
			return probarOpciones(calendario, calendario.next(dia.getDay()), menores, mayores, asignaciones, asignaciones_urg, asignaciones_tx);
			//return asignar(calendario, calendario.next(dia.getDay()), menores, mayores, asignaciones, asignaciones_urg, asignaciones_tx);
		} else {
			System.out.println("FIN de asignación\n");
			return true;
		}
	}

	private static boolean asignarURG_mayor(Calendario calendario, Dia dia,
			Queue<Residente> mayores, Integer[] asignaciones, Integer[] asignaciones_urg)
			throws InterruptedException {

		int intento = 0;
		int dif = 0;
		System.out.println("urgMayor EXCEPCIONES    : \u001B[36m" + dia.getExceptions() + "\u001B[0m");
		System.out.println("urgMayor EXCEPCIONES URG: \u001B[36m" + dia.getExceptions_urg() + "\u001B[0m");
		while (dia.getURG_higher() == null) {
			Residente mayor = mayores.poll();
			if (!dia.getAbsents().contains(mayor)
					&& !dia.getExceptions().contains(mayor)
					&& !dia.getExceptions_urg().contains(mayor)
					&& asignaciones_urg[mayor.getNumber()] < media(asignaciones_urg) + dif) { // + comparar numero de asignaciones con la media
				dia.setURG_higher(mayor);
				asignaciones[mayor.getNumber()]++;
				asignaciones_urg[mayor.getNumber()]++;
				dia.addException(mayor);
				if (calendario.hasNext(dia.getDay())) {
					calendario.next(dia.getDay()).addException_urg(mayor);
				}
			}
			mayores.add(mayor);
			intento++;
			if (intento > mayores.size() && dif > DIF) {
				System.out.println("#Por aquí no!!! (asignarURG_mayor)");
				//Thread.sleep(1000);
				return false;
			}
			dif++;
		}
		return true;
	}

	private static boolean asignarURG_menor(Calendario calendario, Dia dia,
			Queue<Residente> menores, Integer[] asignaciones, Integer[] asignaciones_urg)
			throws InterruptedException {

		int intento = 0;
		int dif = 0;
		System.out.println("urgMenor EXCEPCIONES    : \u001B[36m" + dia.getExceptions() + "\u001B[0m");
		System.out.println("urgMenor EXCEPCIONES URG: \u001B[36m" + dia.getExceptions_urg() + "\u001B[0m");
		while (dia.getURG_minor() == null) {
			Residente menor = menores.poll();
			if (!dia.getAbsents().contains(menor)
					&& !dia.getExceptions().contains(menor)
					&& !dia.getExceptions_urg().contains(menor)
					&& asignaciones_urg[menor.getNumber()] < media(asignaciones_urg) + dif) { // + comparar numero de asignaciones con la media
				dia.setURG_minor(menor);
				asignaciones[menor.getNumber()]++;
				asignaciones_urg[menor.getNumber()]++;
				dia.addException(menor);
				if (calendario.hasNext(dia.getDay())) {
					calendario.next(dia.getDay()).addException_urg(menor);
				}
			}
			menores.add(menor);
			intento++;
			if (intento > menores.size() && dif > DIF) {
				System.out.println("# Por aquí no!!! (asignarURG_menor)");
				//Thread.sleep(1000);
				return false;
			}
			dif++;
		}
		return true;
	}

	private static boolean asignarTX_mayor(Calendario calendario, Dia dia,
			Queue<Residente> mayores, Integer[] asignaciones, Integer[] asignaciones_tx)
			throws InterruptedException {

		int intento = 0;
		int dif = 0;
		System.out.println("txMayor EXCEPCIONES    : \u001B[36m" + dia.getExceptions() + "\u001B[0m");
		System.out.println("txMayor EXCEPCIONES URG: \u001B[36m" + dia.getExceptions_urg() + "\u001B[0m");
		while (dia.getTX_higher() == null) {
			Residente mayor = mayores.poll();
			if (!dia.getAbsents().contains(mayor)
					&& !dia.getExceptions().contains(mayor)
					&& asignaciones_tx[mayor.getNumber()] < media(asignaciones_tx) + dif) { // + comparar numero de asignaciones con la media
				dia.setTX_higher(mayor);
				asignaciones[mayor.getNumber()]++;
				asignaciones_tx[mayor.getNumber()]++;
				dia.addException(mayor);
				// crear excepcionesTX si al dia siguiente no puede estar de TX!!!!?!?!?!?!?!
				if (calendario.hasNext(dia.getDay())) {
					//calendario.next(dia.getDay()).addException(mayor);
				}
			}
			mayores.add(mayor);
			intento++;
			if (intento > mayores.size() && dif > DIF) {
				System.out.println("# Por aquí no!!! (asignarTX_mayor)");
				//Thread.sleep(1000);
				return false;
			}
			dif++;
		}
		return true;
	}

	private static boolean asignarTX_menor(Calendario calendario, Dia dia,
			Queue<Residente> menores, Integer[] asignaciones, Integer[] asignaciones_tx)
			throws InterruptedException {

		int intento = 0;
		int dif = 0;
		System.out.println("txMmenor EXCEPCIONES    : \u001B[36m" + dia.getExceptions() + "\u001B[0m");
		System.out.println("txMenor EXCEPCIONES URG: \u001B[36m" + dia.getExceptions_urg() + "\u001B[0m");
		while (dia.getTX_minor() == null) {
			Residente menor = menores.poll();
			if (!dia.getAbsents().contains(menor)
					&& !dia.getExceptions().contains(menor)
					&& asignaciones_tx[menor.getNumber()] < media(asignaciones_tx) + dif) { // + comparar numero de asignaciones con la media
				dia.setTX_minor(menor);
				asignaciones[menor.getNumber()]++;
				asignaciones_tx[menor.getNumber()]++;
				dia.addException(menor);
				if (calendario.hasNext(dia.getDay())) {
					//calendario.next(dia.getDay()).addException(menor);
				}
			}
			menores.add(menor);
			intento++;
			if (intento > menores.size() && dif > DIF) {
				System.out.println("# Por aquí no!!! (asignarTX_menor)");
				//Thread.sleep(1000);
				return false;
			}
			dif++;
		}
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
