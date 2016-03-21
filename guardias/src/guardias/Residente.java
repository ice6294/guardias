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

import java.util.Objects;

/**
 * @version v1.0
 * @author luis
 */
public class Residente implements Cloneable {

	// ATTRIBUTES
	// <editor-fold desc="<------------------->">
	private Integer id;
	
	private String name;
	private String resident;
	// </editor-fold>

	// CONSTRUCTOR
	// <editor-fold desc="<------------------->">
	public Residente() {
	}

	public Residente(String name, Integer number) {
		this.name = name;
		this.setId(number);
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

	public Integer getId() {
		return id;
	}

	public final void setId(Integer id) {
		if (id > 9) {
			this.id = 10;
		} else {
			this.id = id;
		}
	}

	public String getResident() {
		return resident;
	}

	public final void setResident() {
		int n;
		switch (id) {
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
				break;
			}
		}
		resident = "R" + n;
	}
	// </editor-fold>

	// METHODS
	// <editor-fold desc="<------------------->">
	public boolean isHigher() {
		return this.id < 7;
	}

	public boolean isMinor() {
		return !this.isHigher();
	}
	// </editor-fold>

	// TO STRING METHODS
	// <editor-fold desc="<------------------->">
	@Override
	public String toString() {
		return this.getName() + " (" + this.getResident() + ")";
	}
	// </editor-fold>
	
	// OVERRIDE METHODS
	// <editor-fold desc="<------------------->">
	@Override
	public final Object clone() throws CloneNotSupportedException {
		Object obj = null;
		try {
			obj = super.clone();
		} catch (CloneNotSupportedException ex) {
			System.err.println("# ERROR: (Residente) no se puede clonar: " + ex);
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
		return Objects.equals(this.id, other.id);
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 31 * hash + Objects.hashCode(this.name);
		hash = 31 * hash + Objects.hashCode(this.id);
		hash = 31 * hash + Objects.hashCode(this.resident);
		return hash;
	}
	// </editor-fold>

}
