package guardias;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
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
	private List<String> table;

	private String string;

	// CONSTRUCTOR
	public Calendario() {
		//this(null, null);
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

	public Integer getMonth() {
		return month;
	}
	
	public Dia getDia(Integer d) {
		if (d > 0 || d < calendar.lastKey()) {
			return calendar.get(d);
		}
		return null;
	}

	public Map<Integer, Dia> getCalendar() {
		return calendar;
	}

	public void setCalendar(SortedMap<Integer, Dia> calendar) {
		this.calendar = calendar;
	}

	public void setTable() {
		if (calendar.isEmpty()) {
			return;
		}
		this.table = new ArrayList();

		// rellenamos la tabla con espacios
		for (int i = 0; i < 210; i++) {
			table.add(Utils.addSpaces(Utils.WIDTH));
		}

		Integer primer = calendar.get(0).getWeek_day() - 1;	// primer día de la semana
		Integer end = calendar.size();	// último dia del mes

		int count = 0;
		Integer dia = 0;
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
					this.table.set(jl, Utils.addSpaces(Utils.WIDTH, dia.toString()));
				}
				// si el contador está a 0 se deja el espacio
				if (dia > 0 && calendar.get(dia - 1) != null) {
					if (calendar.get(dia - 1).getURG_higher() != null) {
						this.table.set(jl + 7, Utils.addSpaces(Utils.WIDTH, calendar.get(dia - 1).getURG_higher().getName()));
					}
					if (calendar.get(dia - 1).getURG_minor() != null) {
						this.table.set(jl + 14, Utils.addSpaces(Utils.WIDTH, calendar.get(dia - 1).getURG_minor().getName()));
					}
					if (calendar.get(dia - 1).getTX_higher() != null) {
						this.table.set(jl + 21, Utils.addSpaces(Utils.WIDTH, calendar.get(dia - 1).getTX_higher().getName()));
					}
					if (calendar.get(dia - 1).getTX_minor() != null) {
						this.table.set(jl + 28, Utils.addSpaces(Utils.WIDTH, calendar.get(dia - 1).getTX_minor().getName()));
					}
				}
				count++;
			}
		}
	}

	// METHODS
	public final void initializate() {
		Calendar cal = new GregorianCalendar(this.year, this.month, 1);
		int day_name = cal.get(Calendar.DAY_OF_WEEK) - 2;	// why???
		day_name = (day_name == -1) ? 6 : day_name;
		day_name = (day_name == -2) ? 5 : day_name;

		int num_days = cal.getActualMaximum(Calendar.DAY_OF_MONTH) + 1;
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

	public boolean addException_urg(Residente resident, Integer day) {
		if (day > this.calendar.size()) {
			return false;
		}
		this.calendar.get(day).addException_urg(resident);
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

	public String monthName() {
		String[] names = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Nobiembre", "Diciembre"};
		return names[this.month];
	}

	public String calendarName() {
		return year.toString() + " - " + monthName();
	}

	@Override
	public String toString() {
		if (this.string != null) {
			return this.string;
		}
		if (this.calendar == null) {
			return "#Error: Calendar not created";
		} else if (this.table == null) {
			this.setTable();
		}
		String bar = "+------------+";
		bar += Utils.addChars(Utils.WIDTH, '-') + "+"; // L
		bar += Utils.addChars(Utils.WIDTH, '-') + "+"; // M
		bar += Utils.addChars(Utils.WIDTH, '-') + "+"; // X
		bar += Utils.addChars(Utils.WIDTH, '-') + "+"; // J
		bar += Utils.addChars(Utils.WIDTH, '-') + "+"; // V
		bar += Utils.addChars(Utils.WIDTH, '-') + "+"; // S
		bar += Utils.addChars(Utils.WIDTH, '-') + "+"; // D
		bar += "\n";

		this.string = bar + "|            |";
		this.string += Utils.addSpaces(Utils.WIDTH, "L") + "|";
		this.string += Utils.addSpaces(Utils.WIDTH, "M") + "|";
		this.string += Utils.addSpaces(Utils.WIDTH, "X") + "|";
		this.string += Utils.addSpaces(Utils.WIDTH, "J") + "|";
		this.string += Utils.addSpaces(Utils.WIDTH, "V") + "|";
		this.string += Utils.addSpaces(Utils.WIDTH, "S") + "|";
		this.string += Utils.addSpaces(Utils.WIDTH, "D") + "|";
		this.string += "\n" + bar;

		String URJ_mayor = "| URJ mayor: |";
		String URJ_peque = "| URJ peque: |";
		String TX_mayor = "| TX  mayor: |";           // aquí va la tabla
		String TX_peque = "| TX  peque: |";
		String nada = "|            |";
		String sep = "|";

		int count = 0;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				this.string += (j == 0) ? nada : "";
				this.string += (j == 1) ? bar + URJ_mayor : "";
				this.string += (j == 2) ? URJ_peque : "";
				this.string += (j == 3) ? TX_mayor : "";
				this.string += (j == 4) ? TX_peque : "";
				for (int k = 0; k < 7; k++) {
					this.string += this.table.get(count) + sep;
					count++;
				}
				this.string += "\n";
			}
			this.string += bar;
		}
		return this.string;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Calendario obj = new Calendario(this.year, this.month);
		try {
			SortedMap<Integer, Dia> _calendar = new TreeMap();
			for (int i = 0; i < this.calendar.size(); i++) {
				Dia d = (Dia) this.calendar.get(i).clone();
				_calendar.put(i, d);
			}
			obj.setCalendar(_calendar);
			obj = (Calendario) (Object) super.clone();
		} catch (CloneNotSupportedException ex) {
			System.out.println(" no se puede duplicar");
		}
		return obj;
	}

}
