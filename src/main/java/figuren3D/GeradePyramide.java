/**
 * @author rollins
 */
package figuren3D;
import figuren.ThreeDFig;
import figuren2D.*;

public abstract class GeradePyramide<F extends Figur2D> extends FigurMitGrundflaecheUndHoehe<F> {

	public double oberflaeche() {
		return this.getGrund().flaeche() + mantel();
	}
	
	//template Methode = Platzhalter fuer die noch nicht bekannte Berechnung
	public abstract double mantel();

	public double volumen() {
		
		return (1/3.0 )* this.getHoehe() * this.getGrund().flaeche() ;
	}
	protected GeradePyramide(double hoehe, F grund) throws IllegalArgumentException{
		super(hoehe, grund);
	}
}
