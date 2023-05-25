/**
 * @author rollins
 */
package figuren3D;
import figuren.ThreeDFig;
import figuren2D.*;

public class KreisKegel extends GeradePyramide<Kreis> {

	public KreisKegel(double hoehe, Kreis fig) {
		super(hoehe, fig);
	}
	public KreisKegel(double hoehe, double radius) {
		super(hoehe, new Kreis(radius));
	}
	@Override
	public double mantel() {

		Kreis grundKreis = getGrund();
		//Pythagoras
		double seitenLaenge = Math.sqrt( Math.pow(getHoehe(), 2) + Math.pow(grundKreis.getRadius(), 2));
		Kreis mantelKreis = new Kreis(seitenLaenge); 
		double kreisVerhaeltnis = grundKreis.umfang()/mantelKreis.umfang();
		
		return kreisVerhaeltnis * mantelKreis.flaeche();
	}
	@Override
	public String name() {
		return "Kreiskegel mit Hoehe " + this.getHoehe() + " und " + this.getGrund().getRadius() + " Radius.";
	}
	@Override
	public String toCSVString() {
		String result = ThreeDFig.CONE + ";";
		result += super.toCSVString();
		return result;
	}
}
