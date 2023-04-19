/**
 * @author rollins
 */
package figuren2D;

import figuren.INamed;


public abstract class Figur2D implements INamed, Comparable<Figur2D>{
	public abstract double flaeche();
	public abstract double umfang();
	@Override
	public int compareTo(Figur2D fig) {
		return (int)(this.flaeche()-fig.flaeche());
		
	}
	//public abstract double show();
}
