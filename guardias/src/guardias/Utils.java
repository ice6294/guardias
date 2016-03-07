package guardias;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javafx.util.Pair;

/**
 *
 * @author luis
 */
public class Utils {

	// ATRIBUTES
	public static final int WIDTH = 10;

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	public static final String ANSI_BLACK = "\u001B[30m";

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
		months.add("Nombiembre");
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

	public static void showResidents(List<Residente> residentes,
			List<Pair<Integer, Residente>> ausencias,
			List<Pair<Integer, Residente>> obligatorios) {

		int i = 0;
		println("RESIDENTES");
		for (Residente r : residentes) {
			println(i + ". " + r.toString());
			i++;
		}

		println("\nAUSENCIAS");
		for (Pair<Integer, Residente> p : ausencias) {
			println("Dia: " + (p.getKey() + 1) + " - " + p.getValue().toString());
		}

		println("\nOBLIGATORIOS");
		for (Pair<Integer, Residente> p : obligatorios) {
			println("Dia: " + (p.getKey() + 1) + " - " + p.getValue().toString());
		}

	}

	public static void showResident(String residente,
			List<Pair<Integer, Residente>> ausencias,
			List<Pair<Integer, Residente>> obligatorios) {

		println(residente);
		
		println("\nAUSENCIAS");
		for (Pair<Integer, Residente> p : ausencias) {
			if (p.getValue().toString() == null ? residente == null : p.getValue().toString().equals(residente)) {
				println("Dia: " + (p.getKey() + 1));
			}
		}

		println("\nOBLIGATORIOS");
		for (Pair<Integer, Residente> p : obligatorios) {
			if (p.getValue().toString() == null ? residente == null : p.getValue().toString().equals(residente)) {
				println("Dia: " + (p.getKey() + 1));
			}
		}

	}
	
	public static Residente getResidentFromToString(List<Residente> residentes, String residente) {
		Residente result = new Residente();
		for (Residente r : residentes) {
			if (r.toString() == null ? residente == null : r.toString().equals(residente)) {
				result = r;
				break;
			}
		}
		return result;
	}
	
	public static void sortPairList(List<Pair<Integer, Residente>> residentes) {
		Collections.sort(residentes, new Comparator<Pair<Integer, Residente>>() {
			@Override
			public int compare(Pair<Integer, Residente> o1, Pair<Integer, Residente> o2) {
				if (o1.getKey() < o2.getKey()) {
					return -1;
				} else if (o1.getKey() > o2.getKey()) {
					return 1;
				} else {
					return 0;
				}
			}
		});
	}
	// </editor-fold>

}
