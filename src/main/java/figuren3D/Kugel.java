/**
 * @author rollins
 */
package figuren3D;

import figuren.ThreeDFig;

public class Kugel extends Figur3D {
	private double radius;
	
	public Kugel(double radius) {
		this.setRadius(radius);
	}
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		if(radius>0)
			this.radius = radius;
		else throw new IllegalArgumentException("Radius muss positiv sein.");
	}
	public double oberflaeche() {

		return 4 * Math.PI * Math.pow(radius, 2);
	}
	public double volumen() {

		return 4/3.0 * Math.PI * Math.pow(radius, 3);
	}
	@Override
	public String name() {
		return "Kugel mit Radius " + this.getRadius() + " Radius.";
	}
	@Override
	public String toCSVString() {
		return ThreeDFig.SPHERE+ ";"+radius + ";";
	}
}
