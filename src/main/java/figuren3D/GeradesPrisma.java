/**
 * @author rollins
 */
package figuren3D;

import figuren2D.*;

public class GeradesPrisma<F extends Figur2D> extends FigurMitGrundflaecheUndHoehe<F> {

	public GeradesPrisma(double hoehe, F grund) throws IllegalArgumentException {
		super(hoehe, grund);
	}


	public double oberflaeche() {
		double o = 2 * getGrund().flaeche() + getGrund().umfang() * this.getHoehe();
		return o;
	}

	public double volumen() {

		return this.getHoehe() * getGrund().flaeche();
	}


	@Override
	public String name() {
		return "Gerades Prisma mit Hoehe " + this.getHoehe() + " und als Grundflaeche " + this.getGrund().name() + ".";
	}
}
