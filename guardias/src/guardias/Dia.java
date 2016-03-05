package guardias;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luis
 */
public class Dia {

    // ATTRIBUTES
    private Integer day;
    private Integer week_day;

    private List<Residente> absents;
    private List<Residente> exceptions;

    private Residente URG_higher;
    private Residente URG_minor;
    private Residente TX_higher;
    private Residente TX_minor;

    // CONSTRUCTOR
    public Dia() {
	this(null, null);
    }

    public Dia(Integer day, Integer week_day) {
	this.day = day;
	this.week_day = week_day;
	this.absents = new ArrayList();
	this.exceptions = new ArrayList();
	this.URG_higher = null;
	this.URG_minor = null;
	this.TX_higher = null;
	this.TX_minor = null;
    }

    // GETTERS & SETTERS
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

    public Residente getURG_higher() {
	return URG_higher;
    }

    public void setURG_higher(Residente URG_higher) {
	this.URG_higher = URG_higher;
    }

    public Residente getURG_minor() {
	return URG_minor;
    }

    public void setURG_minor(Residente URG_minor) {
	this.URG_minor = URG_minor;
    }

    public Residente getTX_higher() {
	return TX_higher;
    }

    public void setTX_higher(Residente TX_higher) {
	this.TX_higher = TX_higher;
    }

    public Residente getTX_minor() {
	return TX_minor;
    }

    public void setTX_minor(Residente TX_minor) {
	this.TX_minor = TX_minor;
    }

    // METHODS
    public String getWeekDayName() {
	String[] aux = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
	return aux[this.week_day];
    }

    public void addException(Residente resident) {
	this.exceptions.add(resident);
    }

    public void addAbsent(Residente resident) {
	this.absents.add(resident);
    }

    public void clear() {
	this.setURG_higher(null);
	this.setURG_minor(null);
	this.setTX_higher(null);
	this.setTX_minor(null);
    }

    @Override
    public String toString() {
	return "Day: " + this.getDay() + ". Week_Day: " + this.getWeekDayName();
    }

}
