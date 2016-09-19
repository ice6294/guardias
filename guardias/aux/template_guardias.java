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

/**
 * @version v1.0
 * @author luis
 */
public class Template {
	
	// ATTRIBUTES
	// <editor-fold desc="<------------------->">
	// </editor-fold>
	
	// CONSTRUCTOR
	// <editor-fold desc="<------------------->">
	public Template() {
	}
	// </editor-fold>
	
	// GETTERS & SETTERS
	// <editor-fold desc="<------------------->">
	// </editor-fold>
	
	// METHODS
	// <editor-fold desc="<------------------->">
	// </editor-fold>
	
	// TO STRING METHODS
	// <editor-fold desc="<------------------->">
	@Override
	public String toString() {
		return "";
	}
	// </editor-fold>

	// EQUAL METHODS
	// <editor-fold desc="<------------------->">
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
		final Template other = (Template) obj;
		if (!Objects.equals(this.atr, other.atr)) {
			return false;
		}
		return Objects.equals(this.atr, other.atr);
	}
	// </editor-fold>
	
	// OVERRIDE METHODS
	// <editor-fold desc="<------------------->">
	// </editor-fold>
	
}
