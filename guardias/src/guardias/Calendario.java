package guardias;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 * @author luis
 */
public class Calendario implements Cloneable {

	// ATTRIBUTES
	private Integer year;
	private Integer month;

	private SortedMap<Integer, Dia> calendar;

	// CONSTRUCTOR
	public Calendario() {
		this(null, null);
	}

	public Calendario(Integer year, Integer month) {
		this.year = year;
		this.month = month;
		this.calendar = new TreeMap();
		this.initializate();
	}

	// GETTERS & SETTERS
	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Map<Integer, Dia> getCalendar() {
		return calendar;
	}

	public void setCalendar(SortedMap<Integer, Dia> calendar) {
		this.calendar = calendar;
	}

	// METHODS
	public final void initializate() {
		Calendar cal = new GregorianCalendar(this.year, this.month, 1);
		int day_name = cal.get(Calendar.DAY_OF_WEEK) - 2;
		day_name = (day_name == -1) ? 6 : day_name;
		day_name = (day_name == -2) ? 5 : day_name;

		int num_days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		for (int i = 0; i < num_days; i++) {
			Dia day = new Dia(i, day_name);
			day_name++;
			day_name %= 7;
			calendar.put(i, day);
		}
	}

	public boolean addException(Residente resident, Integer day) {
		if (day > this.calendar.size()) {
			return false;
		}
		this.calendar.get(day).addException(resident);
		return true;
	}

	public boolean addAbsent(Residente resident, Integer day) {
		if (day > this.calendar.size()) {
			return false;
		}
		this.calendar.get(day).addAbsent(resident);
		return true;
	}

	public boolean hasNext(Integer day) {
		return day < this.calendar.size() - 1;
	}

	public Dia next(Integer day) {
		if (this.hasNext(day)) {
			return this.calendar.get(day + 1);
		}
		return null;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Object obj = null;
		try {
			obj = super.clone();
		} catch (CloneNotSupportedException ex) {
			System.out.println(" no se puede duplicar");
		}
		return obj;
	}

	@Override
	public String toString() {
		String result = new String();
		for (Entry<Integer, Dia> e : this.getCalendar().entrySet()) {
			result += e.getKey() + ": " + e.getValue();
			result += "\n    · URG mayor: ";
			if (e.getValue().getURG_higher() != null) {
				result += e.getValue().getURG_higher().getNumber();
				result += " (" + e.getValue().getURG_higher().getResident() + ")";
				result += " - " + e.getValue().getURG_higher().getName();
			} else {
				result += "[NULL]";
			}
			result += "\n    · URG pequeño: ";
			if (e.getValue().getURG_minor() != null) {
				result += e.getValue().getURG_minor().getNumber();
				result += " (" + e.getValue().getURG_minor().getResident() + ")";
				result += " - " + e.getValue().getURG_minor().getName();
			} else {
				result += "[NULL]";
			}
			result += "\n    · TX mayor: ";
			if (e.getValue().getTX_higher() != null) {
				result += e.getValue().getTX_higher().getNumber();
				result += " (" + e.getValue().getTX_higher().getResident() + ")";
				result += " - " + e.getValue().getTX_higher().getName();
			} else {
				result += "[NULL]";
			}
			result += "\n    · TX pequeño: ";
			if (e.getValue().getTX_minor() != null) {
				result += e.getValue().getTX_minor().getNumber();
				result += " (" + e.getValue().getTX_minor().getResident() + ")";
				result += " - " + e.getValue().getTX_minor().getName();
			} else {
				result += "[NULL]";
			}
			result += "\n\n";
		}
		return result;
	}

	public String monthName() {
		String[] names = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Nobiembre", "Diciembre"};
		return names[this.month];
	}

	public String calendarName() {
		return year.toString() + " - " + monthName();
	}

	public List<String> table() {
		List<String> tabla = new ArrayList();
		int longest = 10;

		String bar = "+------------+";
		bar += addChars(longest,'-'); // L
		bar += "+";
		bar += addChars(longest,'-'); // M
		bar += "+";
		bar += addChars(longest,'-'); // X
		bar += "+";
		bar += addChars(longest,'-'); // J
		bar += "+";
		bar += addChars(longest,'-'); // V
		bar += "+";
		bar += addChars(longest,'-'); // S
		bar += "+";
		bar += addChars(longest,'-'); // D
		bar += "+" + "\n";
		//String result = bar + "|    TIPO    |  L   |  M   |  X   |  J   |  V   |  S   |  D   |\n" + bar;
		String result = bar + "|            |";
		result += addSpaces(longest, "L") + "|";
		result += addSpaces(longest, "M") + "|";
		result += addSpaces(longest, "X") + "|";
		result += addSpaces(longest, "J") + "|";
		result += addSpaces(longest, "V") + "|";
		result += addSpaces(longest, "S") + "|";
		result += addSpaces(longest, "D") + "|";
		result += "\n" + bar;

		String URJ_mayor = "| URJ mayor: |";
		String URJ_peque = "| URJ peque: |";
		String TX_mayor = "| TX  mayor: |";           // aquí va la tabla
		String TX_peque = "| TX  peque: |";
		String nada = "|            |";
		String sep = "|";

		// rellenamos la tabla con espacios
		for (int i = 0; i < 210; i++) {
			tabla.add(addSpaces(longest));
		}

		Integer primer = calendar.get(0).getWeek_day() - 1;	// primer día dela semana
		Integer end = calendar.size();

		System.out.println(calendarName());
		System.out.println("Primer dia: " + calendar.get(0).getWeekDayName()
				+ "(" + primer.toString() + ")\nUltimo num: " + end.toString());

		int count = 0;
		Integer dia = 0;

		System.out.println("\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("\t\t\t\tCREANDO TABLA\n");
		for (int i = 0; i < 6; i++) {
			int il = i * 35;
			for (int j = 0; j < 7; j++) {
				int jl = il + j;
				// el contador dia no se inicia hasta que empieza el dia 1
				dia += (count > primer) ? 1 : 0;
				if (dia > end - 1) {
					break;
				}
				//dia = (dia > end - 1) ? 0 : dia;
				if (dia != 0) {
					tabla.set(jl, addSpaces(longest, dia.toString()));
				}
				// si el contador está a 0 se deja el espacio
				if (dia > 0) {
					tabla.set(jl + 7, addSpaces(longest, calendar.get(dia - 1).getURG_higher().getName()));
					tabla.set(jl + 14, addSpaces(longest, calendar.get(dia - 1).getURG_minor().getName()));
					tabla.set(jl + 21, addSpaces(longest, calendar.get(dia - 1).getTX_higher().getName()));
					tabla.set(jl + 28, addSpaces(longest, calendar.get(dia - 1).getTX_minor().getName()));
				}
				count++;
			}
		}

		count = 0;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				result += (j == 0) ? nada : "";
				result += (j == 1) ? bar + URJ_mayor : "";
				result += (j == 2) ? URJ_peque : "";
				result += (j == 3) ? TX_mayor : "";
				result += (j == 4) ? TX_peque : "";
				for (int k = 0; k < 7; k++) {
					result += tabla.get(count) + sep;
					count++;
				}
				result += "\n";
			}
			result += bar;
		}

		System.out.println(result);

		return tabla;
	}

	// solo espacios
	public String addSpaces(int size) {
		return addChars(size, ' ');
	}

	// espacios + nombre
	public String addSpaces(int size, String name) {
		return addChars(size, name, ' ');
	}

	public String addChars(int size, char c) {
		String aux = "";
		for (int i = 0; i < size; i++) {
			aux += c;
		}
		return aux;
	}
	
	public String addChars(int size, String name, char c) {
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
