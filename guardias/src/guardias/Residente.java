package guardias;

import java.util.Objects;

/**
 *
 * @author luis
 */
public class Residente implements Cloneable {

	// ATTRIBUTES
	private String name;	// TODO agregar ID
	private Integer number;

	private String resident;

	// CONSTRUCTOR
	// <editor-fold desc="<------------------->">
	public Residente() {
	}

	public Residente(String name, Integer number) {
		this.name = name;
		this.setNumber(number);
		this.setResident();
	}
	// </editor-fold>

	// GETTERS & SETTERS
	// <editor-fold desc="<------------------->">
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
	// </editor-fold>

	// METHODS
	// <editor-fold desc="<------------------->">
	public boolean isHigher() {
		return this.number < 7;
	}

	public boolean isMinor() {
		return !this.isHigher();
	}
	// </editor-fold>

	// OVERRIDE METHODS
	// <editor-fold desc="<------------------->">
	@Override
	public String toString() {
		return this.getName() + " (" + this.getResident() + ")";
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Object obj = null;
		try {
			obj = super.clone();
		} catch (CloneNotSupportedException ex) {
			System.err.println("# ERROR: (Residente) no se puede duplicar: " + ex);
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
		final Residente other = (Residente) obj;
		if (!Objects.equals(this.name, other.name)) {
			return false;
		}
		if (!Objects.equals(this.resident, other.resident)) {
			return false;
		}
		return Objects.equals(this.number, other.number);
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 31 * hash + Objects.hashCode(this.name);
		hash = 31 * hash + Objects.hashCode(this.number);
		hash = 31 * hash + Objects.hashCode(this.resident);
		return hash;
	}
	// </editor-fold>

}
