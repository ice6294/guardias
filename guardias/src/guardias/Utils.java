package guardias;

/**
 *
 * @author luis
 */
public class Utils {

	public static final int WIDTH = 10;
	
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
}
