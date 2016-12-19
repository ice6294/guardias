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
package jsonsmcreator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFileChooser;

/**
 * @author luis
 * @date 13-12-2016
 */
public class Utils {
	
	// METHODS
	// <editor-fold desc="<------------------->">
	public static boolean tryParseInt(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch(NumberFormatException ex) {
			return false;
		}
	}
	
	public static List<Integer> toIntArray(String string) {
		List<Integer> result = new ArrayList();
		if (string == null)
			return result;
		string = string.replaceAll("\\s+","").replaceAll("\\[", "").replaceAll("\\]","");
		List<String> array = Arrays.asList(string.split(","));
		for (String str : array) {
			if (tryParseInt(str)) {
				result.add(Integer.parseInt(str));
			}
		}
		return result;
	}
	
	public static List<String> toStrArray(String string) {
		if (string == null)
			return new ArrayList();
		return Arrays.asList(string.split(","));
	}
	
	public static String selectPath() {
		JFileChooser chooser = new JFileChooser();
		int retrival = chooser.showSaveDialog(null);
		if (retrival == JFileChooser.APPROVE_OPTION) {
			return (chooser.getSelectedFile().toString());
		}
		return "";
	}

	public static  String selectPathSMC() {
		String path = selectPath();
		if (path.equals("")) {
			return "";
		}
		if (!path.endsWith(".smc")) {
			return (path + ".smc");
		}
		return path;

	}

	public static String selectPathJSON() {
		String path = selectPath();
		if (path.equals("")) {
			return "";
		}
		if (!path.endsWith(".json")) {
			return (path + ".json");
		}
		return path;
	}
	// </editor-fold>
	
}
