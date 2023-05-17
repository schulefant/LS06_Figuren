/**
 * @author rollins
 */
package figuren2D;
import figuren.ICSVString;
import figuren.INamed;


public abstract class Figur2D implements INamed, Comparable<Figur2D>,ICSVString{
	public abstract double flaeche();
	public abstract double umfang();
	@Override
	public int compareTo(Figur2D fig) {
		return (int)(this.flaeche()-fig.flaeche());
		
	}

	public abstract String toCSVString();
	//public abstract double show();
}
