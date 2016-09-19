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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import static guardias.Utils.addChars;
import static guardias.Utils.addSpaces;

/**
 * @version v1.0
 * @author luis
 */
public class Calendario implements Cloneable {

	// ATTRIBUTES
	// <editor-fold desc="<------------------->">
	private String id;

	private Integer year;
	private Integer month;
	private Integer seed;

	private SortedMap<Integer, Dia> calendar;
	private List<String> table;

	private String string;
	// </editor-fold>

	// CONSTRUCTORS
	// <editor-fold desc="<------------------->">
	public Calendario() {
		calendar = new TreeMap();
	}

	public Calendario(Integer year, Integer month) {
		this();
		this.year = year;
		this.month = month;
		this.initializate();
	}

	public Calendario(Integer year, Integer month, Integer seed) {
		this(year, month);
		this.seed = seed;
		this.setId();
	}
	// </editor-fold>

	// GETTERS & SETTERS
	// <editor-fold desc="<------------------->">
	public String getId() {
		return id;
	}

	public final void setId() {
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

	public List<String> getTable() {
		return table;
	}

	public void setTable() {
		if (!calendar.isEmpty()) {
			this.createTable();
		}
	}
	// </editor-fold>

	// BASIC METHODS
	// <editor-fold desc="<------------------->">
	public final void initializate() {
		Calendar cal = new GregorianCalendar(year, month, 1);
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

	public void createTable() {
		table = new ArrayList();

		// rellenamos la tabla con espacios
		for (int i = 0; i < 210; i++) {
			table.add(addSpaces(Utils.WIDTH));
		}

		Integer primer = calendar.get(0).getWeek_day() - 1;	// primer dia de la semana
		Integer end = calendar.size();	// ultimo dia del mes

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
					table.set(jl, addSpaces(Utils.WIDTH, dia.toString()));
				}
				// si el contador esta a 0 se deja el espacio
				if (dia > 0 && calendar.get(dia - 1) != null) {
					if (calendar.get(dia - 1).getURG_higher() != null) {
						table.set(jl + 7, addSpaces(Utils.WIDTH, calendar.get(dia - 1).getURG_higher().getName()));
					}
					if (calendar.get(dia - 1).getURG_minor() != null) {
						table.set(jl + 14, addSpaces(Utils.WIDTH, calendar.get(dia - 1).getURG_minor().getName()));
					}
					if (calendar.get(dia - 1).getTX_higher() != null) {
						table.set(jl + 21, addSpaces(Utils.WIDTH, calendar.get(dia - 1).getTX_higher().getName()));
					}
					if (calendar.get(dia - 1).getTX_minor() != null) {
						table.set(jl + 28, addSpaces(Utils.WIDTH, calendar.get(dia - 1).getTX_minor().getName()));
					}
				}
				count++;
			}
		}
	}

	public boolean isDay(Integer day) {
		return (day < calendar.size()) && (day > -1);
	}

	public boolean hasNext(Integer day) {
		return (isDay(day) && isDay(day + 1));
	}

	public Dia next(Integer day) {
		if (hasNext(day)) {
			return calendar.get(day + 1);
		}
		return null;
	}

	public boolean hasPrev(Integer day) {
		return (isDay(day) && isDay(day - 1));
	}

	public Dia prev(Integer day) {
		if (hasPrev(day)) {
			return calendar.get(day - 1);
		}
		return null;
	}
	
	public boolean isViable(Integer d) {
		Dia dia = this.getDia(d);
		List<Residente> resis_high = new ArrayList();
		List<Residente> resis_min = new ArrayList();
		for (Residente res : dia.getExceptions_urg()) {
			if (res.isHigher()) {
				resis_high.add(res);
			}
			if (res.isMinor()) {
				resis_min.add(res);
			}
		}
		return (resis_high.size() < 7 || resis_min.size() < 7);
	}
	// </editor-fold>

	// ADD METHODS
	// <editor-fold desc="<------------------->">
	public boolean addException(Residente resident, Integer day) {
		if (isDay(day)) {
			calendar.get(day).addException(resident);
			return true;
		}
		return false;
	}

	public boolean addException_urg(Residente resident, Integer day) {
		if (isDay(day)) {
			calendar.get(day).addException_urg(resident);
			return true;
		}
		return false;
	}

	public boolean addException_tx(Residente resident, Integer day) {
		if (isDay(day)) {
			calendar.get(day).addException_tx(resident);
			return true;
		}
		return false;
	}

	public boolean addAbsent(Residente resident, Integer day) {
		if (isDay(day)) {
			if (isSaturday(day) && hasPrev(day)) {
				calendar.get(day - 1).addAbsent(resident);
			} else if (isSunday(day) && hasPrev(day - 1)) {
				calendar.get(day - 2).addAbsent(resident);
			}
			this.calendar.get(day).addAbsent(resident);
			return true;
		}
		return false;
	}

	public boolean addSelectedURG_higher(Residente resident, Integer day) {
		if (isDay(day)) {
			calendar.get(day).setURG_higher(resident);
			if (day > 0) {
				if (isFriday(day)) {
					addPrevFridaysException_urg(resident, day);
					addNextFridaysException_urg(resident, day);
					calendar.get(day - 1).addAbsent(resident);
				} else if (isSaturday(day) && hasPrev(day)) {
					addSelectedTX_higher(resident, day - 1);
				} else if (isSunday(day) && hasPrev(day - 1)) {
					addSelectedURG_higher(resident, day - 2);
				} else {
					calendar.get(day - 1).addAbsent(resident);
				}
			}
			return true;
		}
		return false;
	}

	public boolean addSelectedURG_minor(Residente resident, Integer day) {
		if (isDay(day)) {
			calendar.get(day).setURG_minor(resident);
			if (day > 0) {
				if (isFriday(day)) {
					addPrevFridaysException_urg(resident, day);
					addNextFridaysException_urg(resident, day);
					calendar.get(day - 1).addAbsent(resident);
				} else if (isSaturday(day) && hasPrev(day)) {
					addSelectedTX_minor(resident, day - 1);
				} else if (isSunday(day) && hasPrev(day - 1)) {
					addSelectedURG_minor(resident, day - 2);
				} else {
					calendar.get(day - 1).addAbsent(resident);
				}
			}
			return true;
		}
		return false;
	}

	public boolean addSelectedTX_higher(Residente resident, Integer day) {
		if (isDay(day)) {
			calendar.get(day).setTX_higher(resident);
			calendar.get(day).addException_urg(resident);
			return true;
		}
		return false;
	}

	public boolean addSelectedTX_minor(Residente resident, Integer day) {
		if (isDay(day)) {
			calendar.get(day).setTX_minor(resident);
			calendar.get(day).addException_urg(resident);
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
			return calendar.get(day).getWeek_day() == 4;
		}
		return false;
	}

	public boolean isSaturday(Integer day) {
		if (isDay(day)) {
			return calendar.get(day).getWeek_day() == 5;
		}
		return false;
	}

	public boolean isSunday(Integer day) {
		if (isDay(day)) {
			return calendar.get(day).getWeek_day() == 6;
		}
		return false;
	}

	public boolean hasPrevFriday(Integer day) {
		if (isFriday(day)) {
			return isDay(day - 7);
		}
		return false;
	}

	public Dia prevFriday(Integer day) {
		if (hasPrevFriday(day)) {
			return calendar.get(day - 7);
		}
		return null;
	}

	public void addPrevFridaysException_urg(Residente res, Integer day) {
		Dia d;
		boolean has = hasPrevFriday(day);
		while (has) {
			d = prevFriday(day);
			d.addException_urg(res);
			day -= 7;
			has = hasPrevFriday(day);
		}
	}

	public boolean hasNextFriday(Integer day) {
		if (isFriday(day)) {
			return isDay(day + 7);
		}
		return false;
	}

	public Dia nextFriday(Integer day) {
		if (hasNextFriday(day)) {
			return calendar.get(day + 7);
		}
		return null;
	}

	public void addNextFridaysException_urg(Residente res, Integer day) {
		Dia d;
		boolean has = hasNextFriday(day);
		while (has) {
			d = nextFriday(day);
			d.addException_urg(res);
			day += 7;
			has = hasNextFriday(day);
		}
	}
	// </editor-fold>

	// TO STRING METHODS
	// <editor-fold desc="<------------------->">
	@Override
	public String toString() {
		if (this.string != null) {
			return string;
		}
		if (this.calendar == null) {
			return "#Error: Calendar not created";
		} else if (this.table == null) {
			setTable();
		}
		String bar = "+------------+";
		bar += addChars(Utils.WIDTH, '-') + "+"; // L
		bar += addChars(Utils.WIDTH, '-') + "+"; // M
		bar += addChars(Utils.WIDTH, '-') + "+"; // X
		bar += addChars(Utils.WIDTH, '-') + "+"; // J
		bar += addChars(Utils.WIDTH, '-') + "+"; // V
		bar += addChars(Utils.WIDTH, '-') + "+"; // S
		bar += addChars(Utils.WIDTH, '-') + "+"; // D
		bar += "\r\n";

		this.string = bar + "|            |";
		this.string += addSpaces(Utils.WIDTH, "L") + "|";
		this.string += addSpaces(Utils.WIDTH, "M") + "|";
		this.string += addSpaces(Utils.WIDTH, "X") + "|";
		this.string += addSpaces(Utils.WIDTH, "J") + "|";
		this.string += addSpaces(Utils.WIDTH, "V") + "|";
		this.string += addSpaces(Utils.WIDTH, "S") + "|";
		this.string += addSpaces(Utils.WIDTH, "D") + "|";
		this.string += "\r\n" + bar;

		String URJ_mayor = "| URG mayor: |";
		String URJ_peque = "| URG peque: |";
		String TX_mayor = "| TX  mayor: |";
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

	public String monthName() {
		String[] names = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Nobiembre", "Diciembre"};
		return names[month];
	}

	public String calendarName() {
		return year.toString() + " - " + monthName();
	}
	// </editor-fold>

	// OVERRIDE METHODS
	// <editor-fold desc="<------------------->">
	@Override
	public final Object clone() throws CloneNotSupportedException {
		Calendario obj = new Calendario(this.year, this.month);
		try {
			SortedMap<Integer, Dia> _calendar = new TreeMap();
			for (int i = 0; i < this.calendar.size(); i++) {
				Dia d = (Dia) this.calendar.get(i).clone();
				_calendar.put(i, d);
			}
			obj.setCalendar(_calendar);
			obj = (Calendario) super.clone();
		} catch (CloneNotSupportedException ex) {
			System.err.println("# ERROR: (Calendario) no se puede duplicar: + " + ex);
		}
		return obj;
	}
	// </editor-fold>

}
