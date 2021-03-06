package guardias;

import static guardias.Utils.*;
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
	private String id;

	private Integer year;
	private Integer month;
	private Integer seed;

	private SortedMap<Integer, Dia> calendar;
	private List<String> table;

	private String string;

	// CONSTRUCTORS
	// <editor-fold desc="<------------------->">
	public Calendario() {
	}

	public Calendario(Integer year, Integer month) {
		this.year = year;
		this.month = month;
		this.calendar = new TreeMap();
		this.initializate();
	}

	public Calendario(Integer year, Integer month, Integer seed) {
		this(year, month);
		this.seed = seed;
	}
	// </editor-fold>

	// GETTERS & SETTERS
	// <editor-fold desc="<------------------->">
	public String getId() {
		setId();
		return id;
	}

	public void setId() {
		id = year + "_" + month + "_" + seed;
	}

	public Integer getYear() {
		return year;
	}

	public Integer getMonth() {
		return month;
	}

	public Integer getSeed() {
		return seed;
	}

	public void setSeed(Integer seed) {
		this.seed = seed;
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
				if (dia > end) {
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
	// </editor-fold>

	// BASIC METHODS
	// <editor-fold desc="<------------------->">
	public final void initializate() {
		Calendar cal = new GregorianCalendar(this.year, this.month, 1);
		int day_name = cal.get(Calendar.DAY_OF_WEEK) - 2;	// why???
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

	public boolean isDay(Integer day) {
		return (day < this.calendar.size()) && (day > -1);
	}

	public boolean hasNext(Integer day) {
		return (day < this.calendar.size() - 1) && (day > -1);
	}

	public Dia next(Integer day) {
		if (this.hasNext(day)) {
			return this.calendar.get(day + 1);
		}
		return null;
	}
	// </editor-fold>

	// ADD METHODS
	// <editor-fold desc="<------------------->">
	public boolean addException(Residente resident, Integer day) {
		if (isDay(day)) {
			this.calendar.get(day).addException(resident);
			return true;
		}
		return false;
	}

	public boolean addException_urg(Residente resident, Integer day) {
		if (isDay(day)) {
			this.calendar.get(day).addException_urg(resident);
			return true;
		}
		return false;
	}

	public boolean addAbsent(Residente resident, Integer day) {
		if (isDay(day)) {
			if (isSaturday(day) && hasPrev(day)) {
				this.calendar.get(day-1).addAbsent(resident);
			} else if (isSunday(day) && hasPrev(day-1)) {
				this.calendar.get(day-2).addAbsent(resident);
			}
			this.calendar.get(day).addAbsent(resident);
			return true;
		}
		return false;
	}

	public boolean addSelectedURG_higher(Residente resident, Integer day) {
		if (isDay(day)) {
			this.calendar.get(day).setURG_higher(resident);
			if (day > 0) {
				if (isFriday(day)) {
					addPrevFridaysException_urg(resident, day);
					addNextFridaysException_urg(resident, day);
					this.calendar.get(day - 1).addAbsent(resident);
				} else if (isSaturday(day) && hasPrev(day)) {
					addSelectedTX_higher(resident, day - 1);
				} else if (isSunday(day) && hasPrev(day - 1)) {
					addSelectedURG_higher(resident, day - 2);
				} else {
					this.calendar.get(day - 1).addAbsent(resident);
				}
			}
			return true;
		}
		return false;
	}

	public boolean addSelectedURG_minor(Residente resident, Integer day) {
		if (isDay(day)) {
			this.calendar.get(day).setURG_minor(resident);
			if (day > 0) {
				if (isFriday(day)) {
					addPrevFridaysException_urg(resident, day);
					addNextFridaysException_urg(resident, day);
					this.calendar.get(day - 1).addAbsent(resident);
				} else if (isSaturday(day) && hasPrev(day)) {
					addSelectedTX_minor(resident, day - 1);
				} else if (isSunday(day) && hasPrev(day - 1)) {
					addSelectedURG_minor(resident, day - 2);
				} else {
					this.calendar.get(day - 1).addAbsent(resident);
				}
			}
			return true;
		}
		return false;
	}

	public boolean addSelectedTX_higher(Residente resident, Integer day) {
		if (isDay(day)) {
			this.calendar.get(day).setTX_higher(resident);
			return true;
		}
		return false;
	}

	public boolean addSelectedTX_minor(Residente resident, Integer day) {
		if (isDay(day)) {
			this.calendar.get(day).setTX_minor(resident);
			return true;
		}
		return false;
	}
	// </editor-fold>

	// FRIDAY METHODS
	// <editor-fold desc="<------------------->">
	// TODO pasar estos 3 a dia
	public boolean isFriday(Integer day) {
		if (isDay(day)) {
			return this.calendar.get(day).getWeek_day() == 4;
		}
		return false;
	}
	
	public boolean isSaturday(Integer day) {
		if (isDay(day)) {
			return this.calendar.get(day).getWeek_day() == 5;
		}
		return false;
	}
	
	public boolean isSunday(Integer day) {
		if (isDay(day)) {
			return this.calendar.get(day).getWeek_day() == 6;
		}
		return false;
	}
	
	public boolean hasPrevFriday(Integer day) {
		if (isFriday(day)) {
			return isDay(day - 7);
		}
		return false;
	}
	
	public void addPrevFridaysException_urg(Residente res, Integer day) {
		Dia d;
		while (hasPrevFriday(day)) {
			d = prevFriday(day);
			d.addException_urg(res);
			day -= 7;
		}
	}
	
	public Dia prevFriday(Integer day) {
		if (hasPrevFriday(day)) {
			return this.calendar.get(day - 7);
		}
		return null;
	}

	public boolean hasNextFriday(Integer day) {
		if (isFriday(day)) {
			return isDay(day + 7);
		}
		return false;
	}
	
	public boolean hasPrev(Integer day) {
		return isDay(day-1);
	}

	public Dia nextFriday(Integer day) {
		if (hasNextFriday(day)) {
			return this.calendar.get(day + 7);
		}
		return null;
	}

	public void addNextFridaysException_urg(Residente res, Integer day) {
		Dia d;
		while (hasNextFriday(day)) {
			d = nextFriday(day);
			d.addException_urg(res);
			day += 7;
		}
	}
	// </editor-fold>

	// TO STRING METHODS
	// <editor-fold desc="<------------------->">
	public String monthName() {
		String[] names = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Nobiembre", "Diciembre"};
		return names[this.month];
	}

	public String calendarName() {
		return year.toString() + " - " + monthName();
	}
	// </editor-fold>

	// OVERRIDE METHODS
	// <editor-fold desc="<------------------->">
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
		bar += addChars(WIDTH, '-') + "+"; // L
		bar += addChars(WIDTH, '-') + "+"; // M
		bar += addChars(WIDTH, '-') + "+"; // X
		bar += addChars(WIDTH, '-') + "+"; // J
		bar += addChars(WIDTH, '-') + "+"; // V
		bar += addChars(WIDTH, '-') + "+"; // S
		bar += addChars(WIDTH, '-') + "+"; // D
		bar += "\r\n";

		this.string = bar + "|            |";
		this.string += addSpaces(WIDTH, "L") + "|";
		this.string += addSpaces(WIDTH, "M") + "|";
		this.string += addSpaces(WIDTH, "X") + "|";
		this.string += addSpaces(WIDTH, "J") + "|";
		this.string += addSpaces(WIDTH, "V") + "|";
		this.string += addSpaces(WIDTH, "S") + "|";
		this.string += addSpaces(WIDTH, "D") + "|";
		this.string += "\r\n" + bar;

		String URJ_mayor = "| URG mayor: |";
		String URJ_peque = "| URG peque: |";
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
				this.string += "\r\n";
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
			System.out.println("# ERROR: (Calendario) no se puede duplicar: + " + ex);
		}
		return obj;
	}
	// </editor-fold>

}
