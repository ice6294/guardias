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
	private List<Residente> exceptions_tx;

	private Residente URG_higher;
	private Residente URG_minor;
	private Residente TX_higher;
	private Residente TX_minor;

	// CONSTRUCTOR
	// <editor-fold desc="<------------------->">
	public Dia() {
		this.absents = new ArrayList();
		this.exceptions = new ArrayList();
		this.exceptions_urg = new ArrayList();
		this.exceptions_tx = new ArrayList();
		this.URG_higher = null;
		this.URG_minor = null;
		this.TX_higher = null;
		this.TX_minor = null;
	}

	public Dia(Integer day, Integer week_day) {
		this();
		this.day = day;
		this.week_day = week_day;
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
	
	public List<Residente> getExceptions_tx() {
		return exceptions_tx;
	}

	public void setExceptions_tx(List<Residente> exceptions_tx) {
		this.exceptions_tx = exceptions_tx;
	}

	// URG mayor
	public Residente getURG_higher() {
		return URG_higher;
	}

	public void setURG_higher(Residente URG_higher) {
		this.URG_higher = URG_higher;
	}

	public boolean hasURG_higher() {
		return URG_higher != null;
	}

	// URG menor
	public Residente getURG_minor() {
		return URG_minor;
	}

	public void setURG_minor(Residente URG_minor) {
		this.URG_minor = URG_minor;
	}

	public boolean hasURG_minor() {
		return URG_minor != null;
	}

	// TX mayor
	public Residente getTX_higher() {
		return TX_higher;
	}

	public void setTX_higher(Residente TX_higher) {
		this.TX_higher = TX_higher;
	}

	public boolean hasTX_higher() {
		return TX_higher != null;
	}

	// TX menor
	public Residente getTX_minor() {
		return TX_minor;
	}

	public void setTX_minor(Residente TX_minor) {
		this.TX_minor = TX_minor;
	}

	public boolean hasTX_minor() {
		return TX_minor != null;
	}
	// </editor-fold>

	// METHODS
	// <editor-fold desc="<------------------->">
	public String getWeekDayName() {
		String[] aux = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
		return aux[week_day];
	}

	public void addException(Residente resident) {
		if (!exceptions.contains(resident)) {
			exceptions.add(resident);
		}
	}

	public void addException_urg(Residente resident) {
		if (!exceptions_urg.contains(resident)) {
			exceptions_urg.add(resident);
		}
	}

	public void addException_tx(Residente resident) {
		if (!exceptions_tx.contains(resident)) {
			exceptions_tx.add(resident);
		}
	}
	
	public void addAbsent(Residente resident) {
		if (!absents.contains(resident)) {
			absents.add(resident);
		}
	}

	public void clear() {
		setURG_higher(null);
		setURG_minor(null);
		setTX_higher(null);
		setTX_minor(null);
	}
	// </editor-fold>
	
	// TO STRING METHODS
	// <editor-fold desc="<------------------->">
	@Override
	public String toString() {
		return "Day: " + (getDay() + 1) + ". Week_Day: " + getWeekDayName();
	}
	// </editor-fold>
	
	// OVERRIDE METHODS
	// <editor-fold desc="<------------------->">
	@Override
	public final Object clone() throws CloneNotSupportedException {
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
			List<Residente> _exceptions_tx = new ArrayList();
			for (int i = 0; i < this.exceptions_tx.size(); i++) {
				_exceptions_tx.add((Residente) this.exceptions_tx.get(i).clone());
			}
			// guardamos listas
			obj.setAbsents(_absents);
			obj.setExceptions(_exceptions);
			obj.setExceptions_urg(_exceptions_urg);
			obj.setExceptions_tx(_exceptions_tx);
			// guardamos asignados
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
