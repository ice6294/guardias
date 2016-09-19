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

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import javax.print.PrintException;

/**
 * @version v1.0
 * @author luis
 */
public class Utils {

	// ATRIBUTES
	// <editor-fold desc="<------------------->">
	public static final int WIDTH = 12;
	public static final int WIDTH2 = 10;

	public static final String PATH = System.getProperty("user.dir");

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	public static final String ANSI_BLACK = "\u001B[30m";

	public static final String INTRO
			= "\t\t\t    /~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/\r\n"
			+ "\t\t\t   /~~~~~~~~~       GUARDIAS        ~~~~~~~~~~~~/\r\n"
			+ "\t\t\t  /~~~~~~~~~~   RESIDENTES - CGD    ~~~~~~~~~~~/\r\n"
			+ "\t\t\t /~~~~~~~~~~~   PUERTA DEL HIERRO   ~~~~~~~~~~/\r\n"
			+ "\t\t\t/~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/\r\n"
			+ "\t\t\t                  Versión: 1.2\r\n";

	public static final String HELP
			= "\r\n\r\n" + INTRO
			+ "\r\n\r\n\t    AYUDA\r\n\r\n"
			+ "\t1. Aplicación\r\n\r\n"
			+ "    La versión actual es una beta, por lo que es probable que pueda surgir algún bug.\r\n"
			+ "  Si es así podéis enviarme un mensaje con la información sobre algún bug que podáis\r\n"
			+ "  encontrar a mi dirección de correo electrónico: luigi6294@gmail.com\r\n\r\n\r\n"
			+ "\t2. Interfaz\r\n\r\n"
			+ "    La interfaz consta de 3 partes:\r\n\r\n"
			+ "\t\t2.1 Parte superior:\r\n\r\n"
			+ "\t    · Botón RESIDENTES: muestra en el TextArea todos los residentes, con\r\n"
			+ "\t  las ausencias y obligatorias que se han agregado hasta el momento.\r\n\r\n"
			+ "\t    · Botón EDITAR residentes: muestra una ventana emergente con todos los\r\n"
			+ "\t  residentes. En la columna de la izquierda se encuentran los residentes fijos\r\n"
			+ "\t  y en la columna de la derecha los rotantes. Si la casilla se encuentra vacía\r\n"
			+ "\t  se considera que no hay residente.\r\n\r\n"
			+ "\t    · Desplegable residentes: permite seleccionar un residente en concreto,\r\n"
			+ "\t  mostrándose en el TextArea toda la información sobre dicho residente\r\n\r\n"
			+ "\t    · Campo Ausencias: cuando seleccionas un residente, puedes agregar en\r\n"
			+ "\t  este campo los días que estará ausente en el mes. Para agregarlas hay\r\n"
			+ "\t  que pulsar el botón \"+\".\r\n\r\n"
			+ "\t    Los números permitidos van del 1 al 31. Se pueden agregar los días de\r\n"
			+ "\t  uno en uno, o de varios en varios. Ejemplo: \"3, 6, 17, 28\". También se\r\n"
			+ "\t  pueden agregar conjuntos de días. Ejemplo: \"22-24\", \"3,7,25-28\". Para\r\n"
			+ "\t  eliminar algún día agregado basta con escribir dicho día y darle al botón \"-\".\r\n"
			+ "\t  Las reglas para eliminar días siguen las mismas que para agregarlas\r\n\r\n"
			+ "\t    · Campo Obligatorias: cuando seleccionas un residente, puedes agregar en este\r\n"
			+ "\t  campo las urgencias que quieres que el residente haga un día. Sigue las mismas\r\n"
			+ "\t  reglas que el campo Ausencias.\r\n\r\n"
			+ "\t    · Campo Year: para seleccionar el año del calendario.\r\n\r\n"
			+ "\t    · Campo Mes: para seleccionar el mes del calendario.\r\n\r\n"
			+ "\t    · Campo Seed: para seleccionar el origen aleatorio de la asignación. Cada\r\n"
			+ "\t  vez que se realiza una asignación se hace de una manera relativamente aleatoria.\r\n"
			+ "\t  Se parte de este número para hacer la asignación. Así, si una asignación de\r\n"
			+ "\t  calendario gusta, se puede volver a utilizar la misma seed para que salga\r\n"
			+ "\t  el mismo resultado. El rango de valores abarca todos los números positivos.\r\n\r\n\r\n"
			+ "\t\t2.2 TextArea:\r\n\r\n"
			+ "\t    · Este area es donde se imprime toda la información. Se puede escribir sobre\r\n"
			+ "\t  él, pero lo que se escriba no se podrá utilizar para añadir ausencias, obligatorios\r\n"
			+ "\t  o asignaciones. Es decir, solo sirve para mostrar información y para escribir\r\n"
			+ "\t  anotaciones o hacer cambios en un calendario ya asignado, pues cuando se le de a\r\n"
			+ "\t  \"guardar\" estos cambios se guardan.\r\n\r\n\r\n"
			+ "\t\t2.3 Parte inferior:\r\n\r\n"
			+ "\t    · Botón salir: para salir de la aplicación.\r\n\r\n"
			+ "\t    · Botón limpiar: limpia el TextArea.\r\n\r\n"
			+ "\t    · Botón reiniciar: vacía las listas de ausencias y obligaciones agregadas.\r\n\r\n"
			+ "\t    · Botón guardar: guarda el contenido del TextArea en un documento \".txt\".\r\n"
			+ "\t    · Botón ayuda: muestra en TextArea esta ayuda.\r\n\r\n\r\n"
			+ "\t3. Bugs\r\n\r\n"
			+ "\t    a) Si se intenta asignar en un sábado a una persona que de por si la seed ya\r\n"
			+ "\t  lo va a colocar el viernes-domingo, el programa asigna todo el fin de semana\r\n"
			+ "\t  a esa persona. Habría que intentar con otra seed.\r\n\r\n"
			+ "\t    b) Seleccionar días obligatorios, cuando son más de una persona en días\r\n"
			+ "\t  consecutivos puede dar a lugar a fallos en la asignación.\r\n\r\n"
			+ "\t    Si un mes empieza en sábado o domingo, estas casillas aparecerán vacías, y se\r\n"
			+ "\t  debe asignar manualmente según el mes anterior.\r\n"
			+ "\t    Están pendientes agregar otras funcionalidades como por ejemplo un desplegable\r\n"
			+ "\t  para los meses, opción para seleccionar un máximo de asignaciones por residente,\r\n"
			+ "\t  poder agregar guardias de trasplante, etc.\r\n"
			+ "\t  De momento espero que sirva para facilitar el trabajo de asignación ^^\r\n\r\n\r\n"
			+ "\t5. Funcionalidades\r\n\r\n"
			+ "\t    Cuando se abre por primera vez la aplicación, esta carga una lista de residentes\r\n"
			+ "\t  por defecto. Cuando se cierra la aplicación, las modificaciones sobre los residentes,\r\n"
			+ "\t  las ausencias y los obligatorios se guardan en 3 ficheros que serán cargadas la\r\n"
			+ "\t  próxima vez que se inicie la aplicación.\r\n\r\n\r\n"
			+ "\t6. Créditos\r\n\r\n"
			+ "\t    Luis León Gámez - luigi6294@gmail.com\r\n";
	// </editor-fold>

	// CELLS METHODS
	// <editor-fold desc="<------------------->">
	// solo espacios
	public static String addSpaces(int size) {
		return addChars(size, ' ');
	}

	// espacios + nombre
	public static String addSpaces(int size, String name) {
		return addChars(size, name, ' ');
	}

	public static String addChars(int size, char c) {
		String aux = "";
		for (int i = 0; i < size; i++) {
			aux += c;
		}
		return aux;
	}

	public static String addChars(int size, String name, char c) {
		String result = "";
		int aux = size - name.length();
		if (aux > 0) {
			result = "";
			if (aux % 2 == 0) {
				aux = Math.round(aux / 2);
				for (int i = 0; i < aux; i++) {
					result += c;
				}
			} else {
				aux = Math.round(aux / 2);
				for (int i = 0; i < aux + 1; i++) {
					result += c;
				}
			}
			result += name;
			for (int i = 0; i < aux; i++) {
				result += c;
			}
		} else {
			int i = 0;
			for (char car : name.toCharArray()) {
				result += (i < size) ? car : "";
				i++;
			}
		}
		return result;
	}
	// </editor-fold>

	// PRINT METHODS
	// <editor-fold desc="<------------------->">
	public static void println(String s) {
		System.out.println(s);
	}

	public static void println() {
		System.out.println();
	}

	public static void print(String s) {
		System.out.print(s);
	}

	public static void printlnColor(String color, String s) {
		switch (color) {
			case "GREEN":
			case "green":
			case "1": {
				System.out.println(ANSI_GREEN + s + ANSI_RESET);
				break;
			}
			case "RED":
			case "red":
			case "2": {
				System.out.println(ANSI_RED + s + ANSI_RESET);
				break;
			}
			case "YELLOW":
			case "yellow":
			case "3": {
				System.out.println(ANSI_YELLOW + s + ANSI_RESET);
				break;
			}
			case "BLUE":
			case "blue":
			case "4": {
				System.out.println(ANSI_BLUE + s + ANSI_RESET);
				break;
			}
			case "PURPLE":
			case "purple":
			case "5": {
				System.out.println(ANSI_PURPLE + s + ANSI_RESET);
				break;
			}
			case "CYAN":
			case "cyan":
			case "6": {
				System.out.println(ANSI_CYAN + s + ANSI_RESET);
				break;
			}
			case "WHITE":
			case "white":
			case "7": {
				System.out.println(ANSI_WHITE + s + ANSI_RESET);
				break;
			}
			case "BLACK":
			case "black":
			case "8": {
				System.out.println(ANSI_BLACK + s + ANSI_RESET);
				break;
			}
			default: {
				System.out.println(s);
				break;
			}
		}
	}

	public static void printColor(String color, String s) {
		switch (color) {
			case "GREEN":
			case "green":
			case "1": {
				System.out.print(ANSI_GREEN + s + ANSI_RESET);
				break;
			}
			case "RED":
			case "red":
			case "2": {
				System.out.print(ANSI_RED + s + ANSI_RESET);
				break;
			}
			case "YELLOW":
			case "yellow":
			case "3": {
				System.out.print(ANSI_YELLOW + s + ANSI_RESET);
				break;
			}
			case "BLUE":
			case "blue":
			case "4": {
				System.out.print(ANSI_BLUE + s + ANSI_RESET);
				break;
			}
			case "PURPLE":
			case "purple":
			case "5": {
				System.out.print(ANSI_PURPLE + s + ANSI_RESET);
				break;
			}
			case "CYAN":
			case "cyan":
			case "6": {
				System.out.print(ANSI_CYAN + s + ANSI_RESET);
				break;
			}
			case "WHITE":
			case "white":
			case "7": {
				System.out.print(ANSI_WHITE + s + ANSI_RESET);
				break;
			}
			case "BLACK":
			case "black":
			case "8": {
				System.out.print(ANSI_BLACK + s + ANSI_RESET);
				break;
			}
			default: {
				System.out.print(s);
				break;
			}
		}
	}
	// </editor-fold>

	// OTHER METHODS
	// <editor-fold desc="<------------------->">
	public static boolean isMonth(String s) {
		List<String> months = new ArrayList();
		months.add("Enero");
		months.add("Febrero");
		months.add("Marzo");
		months.add("Abril");
		months.add("Mayo");
		months.add("Junio");
		months.add("Julio");
		months.add("Agosto");
		months.add("Septiembre");
		months.add("Octubre");
		months.add("Noviembre");
		months.add("Diciembre");
		return months.contains(s);
	}

	public static Integer getMonth(String s) {
		String[] names = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Nobiembre", "Diciembre"};
		int i;
		for (i = 0; i < names.length; i++) {
			if (names[i].equals(s)) {
				return i;
			}
		}
		return i;
	}

	public static void showResidents(Map<Integer, Residente> residentes,
			List<Pair<Integer, Residente>> ausencias,
			List<Pair<Integer, Residente>> obligatorios) {

		int i = 0;
		println(" 1. RESIDENTES");
		List<Residente> list = sortMap(residentes);
		for (Residente r : list) {
			println("    " + (i + 1) + ". " + r.toString());
			i++;
		}

		println("\r\n 2. AUSENCIAS");
		int dia = -1;
		sortPairList(ausencias);
		for (Pair<Integer, Residente> p : ausencias) {
			int _dia = p.getKey();
			if (dia == _dia) {
				println("           - " + p.getValue().toString());
			} else {
				println("    Dia: " + (p.getKey() + 1) + " - " + p.getValue().toString());
				dia = _dia;
			}
		}

		dia = -1;
		println("\r\n 3. OBLIGATORIOS");
		sortPairList(obligatorios);
		for (Pair<Integer, Residente> p : obligatorios) {
			int _dia = p.getKey();
			if (dia == _dia) {
				println("           - " + p.getValue().toString());
			} else {
				println("    Dia: " + (p.getKey() + 1) + " - " + p.getValue().toString());
				dia = _dia;
			}
		}

	}

	public static void showResident(String residente,
			List<Pair<Integer, Residente>> ausencias,
			List<Pair<Integer, Residente>> obligatorios) {

		println(residente);

		println("\r\n 1. AUSENCIAS");
		sortPairList(ausencias);
		for (Pair<Integer, Residente> p : ausencias) {
			if (p.getValue().toString() == null ? residente == null : p.getValue().toString().equals(residente)) {
				println("    Dia: " + (p.getKey() + 1));
			}
		}

		println("\r\n 2. OBLIGATORIOS");
		sortPairList(obligatorios);
		for (Pair<Integer, Residente> p : obligatorios) {
			if (p.getValue().toString() == null ? residente == null : p.getValue().toString().equals(residente)) {
				println("    Dia: " + (p.getKey() + 1));
			}
		}

	}

	public static void showAssignments(Map<Integer, Residente> res,
			Map<Integer,Integer> asignaciones,
			Map<Integer,Integer> asignaciones_urg,
			Map<Integer,Integer> asignaciones_tx) {

		List<Residente> resis = sortMap(res);
		
		int n = resis.size();
		String aux = "+--------+";
		println("\r\n 4. ASIGNACIONES\r\n");
		print(aux);
		for (int i = 0; i < n; i++) {
			print(addChars(WIDTH2, '-') + "+");
		}
		print("\r\n");

		print("|        |");
		for (Residente r : resis) {
			print(addSpaces(WIDTH2, r.getName()) + "|");
		}
		print("\r\n");

		print(aux);
		for (int i = 0; i < n; i++) {
			print(addChars(WIDTH2, '-') + "+");
		}
		print("\r\n");

		print("|    URG |");
		for (Residente r : resis) {
			print(addSpaces(WIDTH2, asignaciones_urg.get(r.getId()) + "|"));
		}
		print("\r\n");

		print(aux);
		for (int i = 0; i < n; i++) {
			print(addChars(WIDTH2, '-') + "+");
		}
		print("\r\n");

		print("|     TX |");
		for (Residente r : resis) {
			print(addSpaces(WIDTH2, asignaciones_tx.get(r.getId()) + "|"));
		}
		print("\r\n");

		print(aux);
		for (int i = 0; i < n; i++) {
			print(addChars(WIDTH2, '-') + "+");
		}
		print("\r\n");

		print("|  TOTAL |");
		for (Residente r : resis) {
			print(addSpaces(WIDTH2, asignaciones.get(r.getId()) + "|"));
		}
		print("\r\n");

		print(aux);
		for (int i = 0; i < n; i++) {
			print(addChars(WIDTH2, '-') + "+");
		}
		print("\r\n");
	}
	
	public static void showAssignmentsInParts(Map<Integer, Residente> res,
			Map<Integer,Integer> asignaciones,
			Map<Integer,Integer> asignaciones_urg,
			Map<Integer,Integer> asignaciones_tx) {
		
		println("\r\n 4. ASIGNACIONES\r\n");
		showAssignmentsR1toR2(res, asignaciones, asignaciones_urg, asignaciones_tx);
		print("\n");
		showAssignmentsR3toR5(res, asignaciones, asignaciones_urg, asignaciones_tx);
		
	}
	
	public static void showAssignmentsR1toR2(Map<Integer, Residente> res,
			Map<Integer,Integer> asignaciones,
			Map<Integer,Integer> asignaciones_urg,
			Map<Integer,Integer> asignaciones_tx) {
		
		List<Residente> resis = new ArrayList();
		for (Residente r : res.values()) {
			if (r.isRol(1) || r.isRol(2)) {
				resis.add(r);
			}
		}
		Collections.sort(resis, new Comparator<Residente>() {
			@Override
			public int compare(Residente o1, Residente o2) {
				return o1.getRol().compareTo(o2.getRol());
			}
		});
		
		int n = resis.size();
		String aux = "+--------+";
		print(aux);
		for (int i = 0; i < n; i++) {
			print(addChars(WIDTH2, '-') + "+");
		}
		print("\r\n");

		print("| R1  R2 |");
		for (Residente r : resis) {
			print(addSpaces(WIDTH2, r.getName()) + "|");
		}
		print("\r\n");

		print(aux);
		for (int i = 0; i < n; i++) {
			print(addChars(WIDTH2, '-') + "+");
		}
		print("\r\n");

		print("|    URG |");
		for (Residente r : resis) {
			print(addSpaces(WIDTH2, asignaciones_urg.get(r.getId()).toString()) + "|");
		}
		print("\r\n");

		print(aux);
		for (int i = 0; i < n; i++) {
			print(addChars(WIDTH2, '-') + "+");
		}
		print("\r\n");

		print("|     TX |");
		for (Residente r : resis) {
			print(addSpaces(WIDTH2, asignaciones_tx.get(r.getId()).toString()) + "|");
		}
		print("\r\n");

		print(aux);
		for (int i = 0; i < n; i++) {
			print(addChars(WIDTH2, '-') + "+");
		}
		print("\r\n");

		print("|  TOTAL |");
		for (Residente r : resis) {
			print(addSpaces(WIDTH2, asignaciones.get(r.getId()).toString()) + "|");
		}
		print("\r\n");

		print(aux);
		for (int i = 0; i < n; i++) {
			print(addChars(WIDTH2, '-') + "+");
		}
		print("\r\n");
		
	}
	
	public static void showAssignmentsR3toR5(Map<Integer, Residente> res,
			Map<Integer,Integer> asignaciones,
			Map<Integer,Integer> asignaciones_urg,
			Map<Integer,Integer> asignaciones_tx) {
		
		List<Residente> resis = new ArrayList();
		for (Residente r : res.values()) {
			if (r.isRol(3) || r.isRol(4) || r.isRol(5)) {
				resis.add(r);
			}
		}
		Collections.sort(resis, new Comparator<Residente>() {
			@Override
			public int compare(Residente o1, Residente o2) {
				return o1.getRol().compareTo(o2.getRol());
			}
		});
		
		int n = resis.size();
		String aux = "+--------+";
		print(aux);
		for (int i = 0; i < n; i++) {
			print(addChars(WIDTH2, '-') + "+");
		}
		print("\r\n");

		print("|R3 R4 R5|");
		for (Residente r : resis) {
			print(addSpaces(WIDTH2, r.getName()) + "|");
		}
		print("\r\n");

		print(aux);
		for (int i = 0; i < n; i++) {
			print(addChars(WIDTH2, '-') + "+");
		}
		print("\r\n");

		print("|    URG |");
		for (Residente r : resis) {
			print(addSpaces(WIDTH2, asignaciones_urg.get(r.getId()).toString()) + "|");
		}
		print("\r\n");

		print(aux);
		for (int i = 0; i < n; i++) {
			print(addChars(WIDTH2, '-') + "+");
		}
		print("\r\n");

		print("|     TX |");
		for (Residente r : resis) {
			print(addSpaces(WIDTH2, asignaciones_tx.get(r.getId()).toString()) + "|");
		}
		print("\r\n");

		print(aux);
		for (int i = 0; i < n; i++) {
			print(addChars(WIDTH2, '-') + "+");
		}
		print("\r\n");

		print("|  TOTAL |");
		for (Residente r : resis) {
			print(addSpaces(WIDTH2, asignaciones.get(r.getId()).toString()) + "|");
		}
		print("\r\n");

		print(aux);
		for (int i = 0; i < n; i++) {
			print(addChars(WIDTH2, '-') + "+");
		}
		print("\r\n");
		
	}

	public static Residente getResidentFromToString(Map<Integer, Residente> residentes, String residente) {
		Residente result = new Residente();
		for (Residente r : residentes.values()) {
			if (r.toString() == null ? residente == null : r.toString().equals(residente)) {
				result = r;
				break;
			}
		}
		return result;
	}
	
	public static List<Residente> sortMap(Map<Integer, Residente> residentes) {
		List<Residente> resis = new ArrayList(residentes.values());
		Collections.sort(resis, new Comparator<Residente>() {
			@Override
			public int compare(Residente o1, Residente o2) {
				return -(o1.getRol().compareTo(o2.getRol()));
			}
		});
		return resis;
	}

	public static void sortPairList(List<Pair<Integer, Residente>> residentes) {
		Collections.sort(residentes, new Comparator<Pair<Integer, Residente>>() {
			@Override
			public int compare(Pair<Integer, Residente> o1, Pair<Integer, Residente> o2) {
				return o1.getKey().compareTo(o2.getKey());
			}
		});
	}

	public static void createTxt(String filename, String content) throws IOException, PrintException {
		try (PrintWriter out = new PrintWriter(PATH + filename + ".txt")) {
			out.println(content);
		}
	}
// </editor-fold>

}
