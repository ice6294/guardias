package guardias;

/**
 *
 * @author luis
 */
public class Residente {

    // ATTRIBUTES
    private String name;
    private Integer number;

    private String resident;

    // CONSTRUCTOR
    public Residente() {
	this(null, null);
    }

    public Residente(String name, Integer number) {
	this.name = name;
	this.setNumber(number);
	this.setResident();
    }

    // GETTERS & SETTERS
    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Integer getNumber() {
	return number;
    }

    public final void setNumber(Integer number) {
	if (number > 9) {
	    this.number = 10;
	} else {
	    this.number = number;
	}
    }

    public String getResident() {
	return resident;
    }

    public final void setResident() {
	int n;
	switch (this.number) {
	    case 0:
	    case 1: {
		n = 5;
		break;
	    }
	    case 2:
	    case 3: {
		n = 4;
		break;
	    }
	    case 4:
	    case 5: {
		n = 3;
		break;
	    }
	    case 6:
	    case 7: {
		n = 2;
		break;
	    }
	    case 8:
	    case 9: {
		n = 1;
		break;
	    }
	    default: {
		n = 0;
	    }
	}
	this.resident = "R" + n;
    }

    // METHODS
    public boolean isHigher() {
	return this.number < 7;
    }

    public boolean isMinor() {
	return !this.isHigher();
    }

    @Override
    public String toString() {
	return "Name: " + this.getName() + " (" + this.getResident() + ")";
    }

}
