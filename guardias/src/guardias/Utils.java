package guardias;

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

	// METHODS
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

	// PRINT METHODS
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
}
