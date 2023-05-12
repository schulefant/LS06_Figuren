/**
 * @author rollins
 */
package figuren3D;
import java.util.Comparator;
import figuren.ICSVString;
import figuren.INamed;

public abstract class Figur3D implements Comparable<Figur3D>, INamed, ICSVString{
	protected Figur3D(){}
	
	public abstract double oberflaeche();
	public abstract double volumen();

	public int compareTo(Figur3D f) {
		
		return ((Double)this.volumen()).compareTo(f.volumen()) ;
	}

	public class SurfaceComparator implements Comparator<Figur3D>{

		@Override
		public int compare(Figur3D o1, Figur3D o2) {
		
				return ((Double)o1.oberflaeche()).compareTo(o2.oberflaeche()) ;
		}
	}
}
