package guardias;

import static guardias.Utils.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import javafx.util.Pair;

/**
 *
 * @author luis
 */
public class Asignar {

	// PUBLIC ATTRIBUTES
	public static Integer SEED = 360;
	public static Integer DIF = 0;
	public static List<Residente> RESIDENTES;

	// MASTER METHOD
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

		// Creamos cola de residentes pequeños
		Queue<Residente> menores = new ArrayDeque();
		List<Residente> aux2 = new ArrayList();
		for (int i = 4; i < residentes.size(); i++) {
			aux2.add(residentes.get(i));
		}
		// Randomizamos cola pequeños
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

		// Hacemos la asignación
		boolean asignado = false;
		try {
			asignado = probarOpciones(cal, cal.getCalendar().get(0), menores, mayores, asignaciones, asignaciones_urg, asignaciones_tx);
		} catch (CloneNotSupportedException | InterruptedException | NullPointerException exc) {
			System.err.printf("# ERROR (main): " + exc + "\n# " + cal.getYear() + " - " + cal.monthName() + " [SEED: " + SEED + " ]\n");
		}

		if (asignado) {
			println(INTRO);
			showResidents(residentes, ausentes, obligatorios);
			showAssignments(residentes, asignaciones, asignaciones_urg, asignaciones_tx);
			
			println(" 5. CALENDARIO");
			println(addChars(91, " " + cal.monthName().toUpperCase() + " ", '~'));
			println(cal.toString());
			return cal;
		} else {
			println("\n\n # ERROR. No se pudieron hacer las asignaciones");
			return null;
		}

	}

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
		boolean primer_intento = false;
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
			// Probamos combinación
			if (primer_intento) {
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
			if (!asignarTX_mayor(calendario, dia, mayores, asignaciones, asignaciones_tx)) {
				return false;
			}

			// TX_MENOR
			if (!asignarTX_menor(calendario, dia, menores, asignaciones, asignaciones_tx)) {
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
				return false;
			}
			dif++;
		}
		Residente mayor = dia.getURG_higher();
		asignaciones[mayor.getNumber()]++;
		asignaciones_urg[mayor.getNumber()]++;
		dia.addException(mayor);
		calendario.addNextFridaysException_urg(mayor, dia.getDay());
		if (calendario.hasNext(dia.getDay())) {
			calendario.next(dia.getDay()).addException_urg(mayor);
		}
		// Si es R3 el otro no puede estar como pequeño
		if (mayor.getNumber() == 4) {
			dia.addException_urg(RESIDENTES.get(5));
		} else if (mayor.getNumber() == 5) {
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
				return false;
			}
			dif++;
		}
		Residente menor = dia.getURG_minor();
		asignaciones[menor.getNumber()]++;
		asignaciones_urg[menor.getNumber()]++;
		dia.addException(menor);
		calendario.addNextFridaysException_urg(menor, dia.getDay());
		if (calendario.hasNext(dia.getDay())) {
			calendario.next(dia.getDay()).addException_urg(menor);
		}
		return true;
	}

	private static boolean asignarTX_mayor(Calendario calendario, Dia dia,
			Queue<Residente> mayores, Integer[] asignaciones, Integer[] asignaciones_tx)
			throws InterruptedException {

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
			dia.addException(RESIDENTES.get(5));
		} else if (mayor.getNumber() == 5) {
			dia.addException(RESIDENTES.get(4));
		}
		return true;
	}

	private static boolean asignarTX_menor(Calendario calendario, Dia dia,
			Queue<Residente> menores, Integer[] asignaciones, Integer[] asignaciones_tx)
			throws InterruptedException {

		boolean asignado = (dia.hasTX_minor());
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

}
