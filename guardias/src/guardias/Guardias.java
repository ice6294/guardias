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
	public static final int DIF = 1;

	/**
	 * @param args the command line arguments
	 * @throws java.lang.InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {

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
		Calendario cal = new Calendario(2016, 2); // Julio 2015 <---------------

		// Comprobando calendario
		System.out.println("~~~~~~~~~~~~~~~~~~ CALENDARIO ~~~~~~~~~~~~~~~~~~");
		for (Entry<Integer, Dia> e : cal.getCalendar().entrySet()) {
			System.out.println(e.getKey() + ": " + e.getValue() + " [" + e.getValue().getWeek_day() + "]");
		}
		// Thread.sleep(1000);

		// Agregamos Ausentes
		cal.addAbsent(res1, 17);
		cal.addAbsent(res1, 18);
		cal.addAbsent(res1, 19);
		cal.addAbsent(res1, 20);
		cal.addAbsent(res1, 21);
		//cal.addAbsent(res1, 22);
		//cal.addAbsent(res1, 23);
		//cal.addAbsent(res1, 24);
		//cal.addAbsent(res1, 25);
		//cal.addAbsent(res1, 26);
		//cal.addAbsent(res1, 27);

		cal.addAbsent(res2, 7);
		cal.addAbsent(res2, 8);
		cal.addAbsent(res2, 9);

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
		int seed = 360;
		Random random = new Random(seed);

		// Comprobamos lista de residentes
		System.out.println();
		System.out.println("~~~~~~~~~~~~~~~~~~ RESIDENTES ~~~~~~~~~~~~~~~~~~");
		for (Residente r : residentes) {
			System.out.println(r.toString());
		}
		// Thread.sleep(1000);

		// Creamos cola de residentes mayores
		Queue<Residente> mayores = new ArrayDeque();
		List<Residente> aux = new ArrayList();
		for (int i = 0; i < 6; i++) {
			aux.add(residentes.get(i));
		}
		while (!aux.isEmpty()) {
			int n = (int) (random.nextDouble() * aux.size());
			System.out.println("Random: " + n);
			mayores.add(aux.remove(n));
		}

		// Comprobamos lista de residentes mayores
		System.out.println();
		System.out.println("~~~~~~~~~~~~~~~~~~ MAYORES ~~~~~~~~~~~~~~~~~~");
		int j = 0;
		for (Residente r : mayores) {
			System.out.println(j + ": " + r.toString());
			j++;
		}
		// Thread.sleep(1000);

		// Creamos cola de residentes pequeños
		Queue<Residente> menores = new ArrayDeque();
		List<Residente> aux2 = new ArrayList();
		for (int i = 4; i < residentes.size(); i++) {
			aux2.add(residentes.get(i));
		}

		// Aleatorizamos cola de residentes
		while (!aux2.isEmpty()) {
			//int n = (int) (Math.random() * aux2.size());
			int n = (int) (random.nextDouble() * aux2.size());
			System.out.println("Random: " + n);
			menores.add(aux2.remove(n));
		}
		System.out.println();

		// Comprobamos lista de residentes pequeños
		System.out.println();
		System.out.println("~~~~~~~~~~~~~~~~~~ PEQUEÑOS ~~~~~~~~~~~~~~~~~~");
		j = 0;
		for (Residente r : menores) {
			System.out.println(j + ": " + r.toString());
			j++;
		}
		// Thread.sleep(1000);

		// Creamos lista de asignaciones
		int[] asignaciones = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

		// Asignar
		System.out.println("\n\n");
		System.out.println("~~~~~~~~~~~~~~~~~~ ASIGNAR ~~~~~~~~~~~~~~~~~~");
		if (asignar(cal, cal.getCalendar().get(0), menores, mayores, asignaciones)) {
			System.out.println("\n\n");
			System.out.println("~~~~~~~~~~~~~~~~~~ RESULTADO ~~~~~~~~~~~~~~~~~~");
			System.out.println(cal);
			System.out.println("Nº de asignaciones: " + Arrays.toString(asignaciones));
			System.out.println("\n\n");
			Thread.sleep(1000);
			System.out.println("~~~~~~~~~~~~~~~~~~~ TABLA ~~~~~~~~~~~~~~~~~~~");
			System.out.println(cal.table());
		} else {
			System.out.println("\n\n ERROR. No se pudieron hacer las asignaciones");
		}

	}

	// args: calendario, dia, residentes menores, residentes mayores y lista asignaciones
	public static boolean asignar(Calendario calendario, Dia dia, Queue<Residente> menores, Queue<Residente> mayores, int[] asignaciones) throws InterruptedException {
		System.out.println("[" + dia + "] ");
		// No es último día del mes
		if (!calendario.getCalendar().containsKey(dia.getDay())) {
			return true;
		}
		if (dia.getWeek_day() < 5) {
			// <editor-fold desc="// Lunes, Martes, Miércoles ó Jueves">
			// <editor-fold desc="//    URG Mayor">
			int intento = 0;
			int dif = 0;
			while (dia.getURG_higher() == null) {
				Residente mayor = mayores.poll();
				if (!dia.getAbsents().contains(mayor)
						&& !dia.getExceptions().contains(mayor)
						&& asignaciones[mayor.getNumber()] < media(asignaciones) + dif) { // + comparar numero de asignaciones con la media
					dia.setURG_higher(mayor);
					asignaciones[mayor.getNumber()]++;
					dia.addException(mayor);
					if(calendario.hasNext(dia.getDay())) {
						calendario.next(dia.getDay()).addException(mayor);
					}
				}
				mayores.add(mayor);
				intento++;
				if (intento > menores.size() && dif > DIF) {
					return false;
				}
				dif++;
			}
			System.out.println("    · URG mayor asignado: " + dia.getURG_higher());
			// </editor-fold>
			// Thread.sleep(500);
			// <editor-fold desc="//    URG Menor">
			intento = 0;
			dif = 0;
			while (dia.getURG_minor() == null) {
				Residente menor = menores.poll();
				if (!dia.getAbsents().contains(menor)
						&& !dia.getExceptions().contains(menor)
						&& asignaciones[menor.getNumber()] < media(asignaciones) + dif) { // + comparar numero de asignaciones con la media
					dia.setURG_minor(menor);
					asignaciones[menor.getNumber()]++;
					dia.addException(menor);
					if(calendario.hasNext(dia.getDay())) {
						calendario.next(dia.getDay()).addException(menor);
					}
				}
				menores.add(menor);
				intento++;
				if (intento > menores.size() && dif > DIF) {
					return false;
				}
				dif++;
			}
			System.out.println("    · URG pequeño asignado: " + dia.getURG_minor());
			// </editor-fold>
			// Thread.sleep(500);
			// <editor-fold desc="//    TX Mayor">
			intento = 0;
			dif = 0;
			while (dia.getTX_higher() == null) {
				Residente mayor = mayores.poll();
				if (!dia.getAbsents().contains(mayor)
						//&& !dia.getExceptions().contains(mayor)
						&& asignaciones[mayor.getNumber()] < media(asignaciones) + dif) { // + comparar numero de asignaciones con la media
					dia.setTX_higher(mayor);
					asignaciones[mayor.getNumber()]++;
					dia.addException(mayor);
				}
				mayores.add(mayor);
				intento++;
				if (intento > menores.size() && dif > DIF) {
					return false;
				}
				dif++;
			}
			System.out.println("    · TX mayor asignado: " + dia.getTX_higher());
			// </editor-fold>
			// Thread.sleep(500);
			// <editor-fold desc="//    TX Menor">
			intento = 0;
			dif = 0;
			while (dia.getTX_minor() == null) {
				Residente menor = menores.poll();
				if (!dia.getAbsents().contains(menor)
						//&& !dia.getExceptions().contains(menor)
						&& asignaciones[menor.getNumber()] < media(asignaciones) + dif) { // + comparar numero de asignaciones con la media
					dia.setTX_minor(menor);
					asignaciones[menor.getNumber()]++;
					dia.addException(menor);
				}
				menores.add(menor);
				intento++;
				if (intento > menores.size() && dif > DIF) {
					return false;
				}
				dif++;
			}
			System.out.println("    · TX pequeño asignado: " + dia.getTX_minor());
			// </editor-fold>
			// Thread.sleep(500);
			// </editor-fold>
			// <editor-fold desc="// Viernes, Sabado ó Domingo">
			if (dia.getWeek_day() == 4) {
				if (calendario.hasNext(dia.getDay())) {
					Dia sabado = calendario.next(dia.getDay());
					asignaciones[dia.getURG_higher().getNumber()]++;
					asignaciones[dia.getURG_minor().getNumber()]++;
					asignaciones[dia.getTX_higher().getNumber()]++;
					asignaciones[dia.getTX_minor().getNumber()]++;
					sabado.setURG_higher(dia.getTX_higher());
					sabado.setURG_minor(dia.getTX_minor());
					sabado.setTX_higher(dia.getURG_higher());
					sabado.setTX_minor(dia.getURG_minor());
					if (calendario.hasNext(sabado.getDay())) {
						Dia domingo = calendario.next(sabado.getDay());
						asignaciones[dia.getURG_higher().getNumber()]++;
						asignaciones[dia.getURG_minor().getNumber()]++;
						asignaciones[dia.getTX_higher().getNumber()]++;
						asignaciones[dia.getTX_minor().getNumber()]++;
						domingo.setURG_higher(sabado.getTX_higher());
						domingo.setURG_minor(sabado.getTX_minor());
						domingo.setTX_higher(sabado.getURG_higher());
						domingo.setTX_minor(sabado.getURG_minor());
					}
				}
			}
			// </editor-fold>
		}
		System.out.println(Arrays.toString(asignaciones) + "\n");
		if (calendario.hasNext(dia.getDay())) {
			return asignar(calendario, calendario.next(dia.getDay()), menores, mayores, asignaciones);
		} else {
			System.out.println("FIN de asignación");
			return true;
		}
	}

	public static int media(int[] asignaciones) {
		int media = 0;
		for (int i = 0; i < asignaciones.length; i++) {
			media += asignaciones[i];
		}
		media /= asignaciones.length;
		return media;
	}

	public static int mediana(int[] asignaciones) {
		Arrays.sort(asignaciones);
		return asignaciones[asignaciones.length / 2];
	}

	public static void menuCrearCalendario() {
		boolean exit = false;
		String in = "";
		while (!exit) {
			System.out.println("Menu:");
			System.out.println("\t1) Agregar ausencia");
			System.out.println("\t2) Eliminar ausencia");
			System.out.println("\t<exit>");
		}

	}

}
