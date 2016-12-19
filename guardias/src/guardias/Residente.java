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

import java.io.Serializable;
import java.util.Objects;

/**
 * @version v1.0
 * @author luis
 */
public class Residente implements Cloneable, Serializable {

	// ATTRIBUTES
	// <editor-fold desc="<------------------->">
	private Integer id;
	private Integer rol;

	private String name;
	private String resident;
	// </editor-fold>

	// CONSTRUCTOR
	// <editor-fold desc="<------------------->">
	public Residente() {
	}

	public Residente(String name, Integer id, Integer rol) {
		this.name = name;
		this.id = id;
		this.setResident(rol);
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

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getRol() {
		return rol;
	}

	public String getResident() {
		return resident;
	}

	public final void setResident(Integer rol) {
		this.rol = (rol > 5) ? 0 : rol;
		resident = "R" + this.rol;
	}
	// </editor-fold>

	// METHODS
	// <editor-fold desc="<------------------->">
	public boolean isHigher() {
		return (this.rol > 2);
	}

	public boolean isMinor() {
		return (this.rol < 4 && this.rol > 0);
	}

	public boolean isBoth() {
		return (this.rol == 3);
	}
	
	public boolean isRotante() {
		return (this.id > 10);
	}
	
	public boolean isRol(int n) {
		return (this.rol == n);
	}
	// </editor-fold>

	// TO STRING METHODS
	// <editor-fold desc="<------------------->">
	@Override
	public String toString() {
		String str = "[" + this.getId() + "] " + this.getName() + " (" + this.getResident() + ")";
		if (this.isRotante()) {
			str += " [ROTANTE]";
		}
		return str;
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
