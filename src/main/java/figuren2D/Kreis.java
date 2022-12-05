/**
 * @author rollins
 */
package figuren2D;

public class Kreis extends Figur2D {
	private double radius;

	public Kreis(double r)throws IllegalArgumentException  {
		this.setRadius(r);
	}

	public Kreis(Kreis original) {
		this.radius = original.radius;
	}
	@Override
	public String name() {return "Kreis mit Radius " + this.radius;}

	public void setRadius(double radius) throws IllegalArgumentException {
		if (radius > 0) {
			this.radius = radius;
		} else throw new IllegalArgumentException("Der Radius muss positiv sein.") ;
	}

	public double getRadius() {
		return this.radius;
	}

	public double flaeche() {
		double flaeche = this.radius * this.radius * Math.PI;
		return flaeche;
	}

	public double umfang() {
		return Math.PI * 2 * radius;
	}

}
