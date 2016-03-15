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
import java.util.List;
import java.util.Objects;

/**
 *
 * @version v1.0
 * @author luis
 */
public class Dia implements Cloneable {

	// ATTRIBUTES
	private Integer day;
	private Integer week_day;

	private List<Residente> absents;
	private List<Residente> exceptions;
	private List<Residente> exceptions_urg;

	private Residente URG_higher;
	private Residente URG_minor;
	private Residente TX_higher;
	private Residente TX_minor;

	// CONSTRUCTOR
	// <editor-fold desc="<------------------->">
	public Dia() {
	}

	public Dia(Integer day, Integer week_day) {
		this.day = day;
		this.week_day = week_day;
		this.absents = new ArrayList();
		this.exceptions = new ArrayList();
		this.exceptions_urg = new ArrayList();
		this.URG_higher = null;
		this.URG_minor = null;
		this.TX_higher = null;
		this.TX_minor = null;
	}
	// </editor-fold>

	// GETTERS & SETTERS
	// <editor-fold desc="<------------------->">
	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Integer getWeek_day() {
		return week_day;
	}

	public void setWeek_day(Integer week_day) {
		this.week_day = week_day;
	}

	public List<Residente> getAbsents() {
		return absents;
	}

	public void setAbsents(List<Residente> absents) {
		this.absents = absents;
	}

	public List<Residente> getExceptions() {
		return exceptions;
	}

	public void setExceptions(List<Residente> exceptions) {
		this.exceptions = exceptions;
	}

	public List<Residente> getExceptions_urg() {
		return exceptions_urg;
	}

	public void setExceptions_urg(List<Residente> exceptions_urg) {
		this.exceptions_urg = exceptions_urg;
	}

	public Residente getURG_higher() {
		return URG_higher;
	}

	public void setURG_higher(Residente URG_higher) {
		this.URG_higher = URG_higher;
	}

	public boolean hasURG_higher() {
		return this.URG_higher != null;
	}

	public Residente getURG_minor() {
		return URG_minor;
	}

	public void setURG_minor(Residente URG_minor) {
		this.URG_minor = URG_minor;
	}

	public boolean hasURG_minor() {
		return this.URG_minor != null;
	}

	public Residente getTX_higher() {
		return TX_higher;
	}

	public void setTX_higher(Residente TX_higher) {
		this.TX_higher = TX_higher;
	}

	public boolean hasTX_higher() {
		return this.TX_higher != null;
	}

	public Residente getTX_minor() {
		return TX_minor;
	}

	public void setTX_minor(Residente TX_minor) {
		this.TX_minor = TX_minor;
	}

	public boolean hasTX_minor() {
		return this.TX_minor != null;
	}
	// </editor-fold>

	// METHODS
	// <editor-fold desc="<------------------->">
	public String getWeekDayName() {
		String[] aux = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
		return aux[this.week_day];
	}

	public void addException(Residente resident) {
		if (!exceptions.contains(resident)) {
			this.exceptions.add(resident);
		}
	}

	public void addException_urg(Residente resident) {
		if (!exceptions_urg.contains(resident)) {
			this.exceptions_urg.add(resident);
		}
	}

	public void addAbsent(Residente resident) {
		if (!absents.contains(resident)) {
			this.absents.add(resident);
		}
	}

	public void clear() {
		this.setURG_higher(null);
		this.setURG_minor(null);
		this.setTX_higher(null);
		this.setTX_minor(null);
	}
	// </editor-fold>

	// OVERRIDE METHODS
	// <editor-fold desc="<------------------->">
	@Override
	public String toString() {
		return "Day: " + (this.getDay() + 1) + ". Week_Day: " + this.getWeekDayName();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Dia obj = new Dia(this.day, this.week_day);
		try {
			// copiamos listas
			List<Residente> _absents = new ArrayList();
			for (int i = 0; i < this.absents.size(); i++) {
				_absents.add((Residente) this.absents.get(i).clone());
			}
			List<Residente> _exceptions = new ArrayList();
			for (int i = 0; i < this.exceptions.size(); i++) {
				_exceptions.add((Residente) this.exceptions.get(i).clone());
			}
			List<Residente> _exceptions_urg = new ArrayList();
			for (int i = 0; i < this.exceptions_urg.size(); i++) {
				_exceptions_urg.add((Residente) this.exceptions_urg.get(i).clone());
			}

			// guardamos listas
			obj.setAbsents(_absents);
			obj.setURG_higher(this.URG_higher);
			obj.setURG_minor(this.URG_minor);
			obj.setTX_higher(this.TX_higher);
			obj.setTX_minor(this.TX_minor);

			obj = (Dia) super.clone();
		} catch (CloneNotSupportedException ex) {
			System.err.println("# ERROR: (Dia) no se puede duplicar: " + ex);
		}
		return obj;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Dia other = (Dia) obj;
		if (!Objects.equals(this.day, other.day)) {
			return false;
		}
		return Objects.equals(this.week_day, other.week_day);
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 53 * hash + Objects.hashCode(this.day);
		hash = 53 * hash + Objects.hashCode(this.week_day);
		return hash;
	}
	// </editor-fold>

}
