/**
 * @author rollins
 */
package figuren2D;

import figuren.TwoDFig;

public class Dreieck extends Figur2D{
	private double seiteA;
	private double seiteB; // ist Basis
	private double seiteC;

	// Default Konstruktor wurde bewusst weggelassen
	public Dreieck(double a, double b, double c) throws IllegalArgumentException {
		if (istKonstruierbar(a, b, c)) {
			this.seiteA = a;
			this.seiteB = b;
			this.seiteC = c;
		} else {
			throw new IllegalArgumentException("Dreieck ist nicht valide. Es wurde nicht erstellt.");
		}
	}

	// Kopierkonstruktor
	public Dreieck(Dreieck original) {
		// Konstruktor mit Wertuebergabe wird genutzt
		this(original.getSeiteA(), original.getSeiteB(), original.getSeiteC());
	}

	@Override
	public String name() {
		return "Dreieck mit den Seiten A: " + this.seiteA + " B: " + this.seiteB + " C: " + this.seiteC;
	}

	public double getSeiteA() {
		return seiteA;
	}

	public void setSeiteA(double seiteA) throws IllegalArgumentException {

		if (istKonstruierbar(seiteA, this.seiteB, this.seiteC))
			this.seiteA = seiteA;
		else
			throw new IllegalArgumentException("Seitenaenderung nicht moeglich - Dreieck waere nicht valide");
	}

	public double getSeiteB() throws IllegalArgumentException {
		return seiteB;
	}

	public void setSeiteB(double seiteB) throws IllegalArgumentException {

		if (istKonstruierbar(this.seiteA, seiteB, this.seiteC))
			this.seiteB = seiteB;
		else
			throw new IllegalArgumentException("Seitenaenderung nicht moeglich, da Dreieck dann nicht valide");
	}

	public double getSeiteC() {
		return seiteC;
	}

	public void setSeiteC(double seiteC) throws IllegalArgumentException {

		if (istKonstruierbar(this.seiteA, this.seiteB, seiteC))
			this.seiteC = seiteC;
		else
			throw new IllegalArgumentException("Seitenaenderung nicht moeglich, da Dreieck dann nicht valide");
	}

	/**
	 * 
	 * @return Hoehe des Dreiecks zur Basis SeiteC
	 */

	public double hoehe() {
		return this.flaeche() * 2 / this.seiteC;
	}

	/**
	 * 
	 * @return Hoehe des Dreiecks zur angegebenen Basis
	 */

	public double hoehe(char seiteBasis) {
		double s = 0;

		if (seiteBasis == 'a' || seiteBasis == 'A')
			s = this.seiteA;
		else if (seiteBasis == 'b' || seiteBasis == 'B')
			s = this.seiteB;
		else if (seiteBasis == 'c' || seiteBasis == 'C')
			s = this.seiteC;

		return this.flaeche() * 2 / s;
	}

	@Override
	public double flaeche() {
		// Anwendung der Heronschen Formel
		double s = (seiteA + seiteB + seiteC) / 2;
		return Math.sqrt(s * (s - seiteA) * (s - seiteB) * (s - seiteC));
	}

	@Override
	public double umfang() {
		return this.seiteA + this.seiteB + this.seiteC;
	}

	public static boolean istKonstruierbar(double a, double b, double c) {
		boolean machbar = false;

		if (c + b > a && b + a > c && c + a > b)
			machbar = true;
		return machbar;
	}

	public String toString() {
		return "Seite A: " + seiteA + ", Seite B: " + seiteB + ", Seite C: " + seiteC + ", Flaeche: " + flaeche()
				+ ", Umfang: " + umfang();
	}

	@Override
	public boolean equals(Object ob) {
		if (ob != null && ob.getClass() == Dreieck.class) {
			Dreieck o = (Dreieck) (ob);
			return this.seiteA == o.seiteA && this.seiteB == o.seiteB && this.seiteC == o.seiteC;
		}
		return false;
	}

	@Override
	public String toCSVString() {
		String result = TwoDFig.TRIANGLE+ ";";
		result += seiteA + ";";
		result += this.seiteB + ";";
		result += this.seiteC + ";";
		return result;
	}
}
