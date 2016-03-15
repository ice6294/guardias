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
 * @param <F>
 * @param <S>
 */
public class Pair<F, S> {

	// ATTRIBUTES
	private F first;
    private S second;

	// CONSTRUCTOR
    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

	// GETTERS & SETTERS
    public void setFirst(F first) {
        this.first = first;
    }
	
	public F getFirst() {
        return first;
    }
	
	public F getKey() {
        return first;
    }

    public void setSecond(S second) {
        this.second = second;
    }

    public S getSecond() {
        return second;
    }
	
	public S getValue() {
        return second;
    }
	
	// OVERRIDE METHODS
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
		final Pair<F,S> other = (Pair) obj;
		if (!Objects.equals(this.first, other.first)) {
			return false;
		}
		return Objects.equals(this.second, other.second);
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 97 * hash + Objects.hashCode(this.first);
		hash = 97 * hash + Objects.hashCode(this.second);
		return hash;
	}
	
}
