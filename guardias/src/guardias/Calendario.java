package guardias;

import java.util.Calendar;
import java.util.GregorianCalendar;
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
        this.calendar = new TreeMap() {
        };
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
        int day_name = cal.get(Calendar.DAY_OF_WEEK);
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
            } else {
                result += "[NULL]";
            }
            result += "\n    · URG pequeño: ";
            if (e.getValue().getURG_minor() != null) {
                result += e.getValue().getURG_minor().getNumber();
            } else {
                result += "[NULL]";
            }
            result += "\n    · TX mayor: ";
            if (e.getValue().getTX_higher() != null) {
                result += e.getValue().getTX_higher().getNumber();
            } else {
                result += "[NULL]";
            }
            result += "\n    · TX pequeño: ";
            if (e.getValue().getTX_minor() != null) {
                result += e.getValue().getTX_minor().getNumber();
            } else {
                result += "[NULL]";
            }
            result += "\n";
        }
        return result;
    }

}
