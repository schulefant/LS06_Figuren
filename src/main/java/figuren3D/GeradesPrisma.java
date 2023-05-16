/**
 * @author rollins
 */
package figuren3D;

import figuren.ThreeDFig;
import figuren.TwoDFig;
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

	@Override
	public String toCSVString() {
		String result = "";
		if (this.getGrund().getType() == TwoDFig.POLYGON)
			result += ThreeDFig.REGULARPRISM + ";";
		else if (this.getGrund().getType() == TwoDFig.CIRCLE)
			result += ThreeDFig.CONE + ";";
		else
			result += ThreeDFig.PRISM + ";";
		result += super.toCSVString();
		return result;
	}
}
