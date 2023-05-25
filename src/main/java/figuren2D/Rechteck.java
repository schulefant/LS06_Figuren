/**
 * @author rollins
 */
package figuren2D;

import figuren.TwoDFig;

public class Rechteck extends Figur2D {
	private double hoehe;
	private double breite;

//Default Konstruktor wurde bewusst weggelassen
	public Rechteck(double hoehe, double breite) throws IllegalArgumentException {
		setHoehe(hoehe);
		setBreite(breite);
	}

	// Kopierkonstruktor
	public Rechteck(Rechteck r) {
		// Aufruf eines anderen Konstruktors im Konstruktor
		this(r.getHoehe(), r.getBreite());
	}

	@Override
	public String name() {
		return "Rechteck mit Hoehe " + this.getHoehe() + " und Breite " + this.getBreite();
	}

	@Override
	public double flaeche() {
		return breite * hoehe;
	}

	@Override
	public double umfang() {
		return 2 * (breite + hoehe);
	}

	public double getHoehe() {
		return this.hoehe;
	}

	public double getBreite() {
		return this.breite;
	}

	/*
	 * Anwendung der Kapselung Es duerfen keine unzulaessigen Werte gesetzt werden
	 */
	public void setHoehe(double h) throws IllegalArgumentException {
		if (h <= 0)
			throw new IllegalArgumentException("Rechteck ist mit der Hoehe nicht valide.");
		else {
			this.hoehe = h;
		}
	}

	public void setBreite(double b) throws IllegalArgumentException {
		if (b <= 0)
			throw new IllegalArgumentException("Rechteck ist mit der Breite nicht valide.");
		else {
			this.breite = b;
		}
	}

	public void show() {
		System.out.print("Hoehe: " + hoehe);
		System.out.print(", Breite: " + breite);
		System.out.print(", Flaeche: " + flaeche());
		System.out.println(", Umfang: " + umfang());
	}

	@Override
	public String toCSVString() {
		String result = TwoDFig.RECTANGLE+ ";";
		result += this.hoehe + ";";
		result += this.breite + ";";
		return result;
	}

	@Override
	public TwoDFig getType() {
		return TwoDFig.RECTANGLE ;
	}
}
