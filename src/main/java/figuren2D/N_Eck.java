/**
 * @author rollins
 */
package figuren2D;

import figuren.TwoDFig;

public class N_Eck extends Figur2D {

	private double seitenLaenge;
	private int n;

	public int getN() {
		return n;
	}

	public N_Eck(double seitenLaenge, int anzahl) throws IllegalArgumentException {

		if (anzahl > 2) {
			setSeitenLaenge(seitenLaenge);
			this.n = anzahl;
		} else
			throw new IllegalArgumentException("Ein N-Eck brauch mindestens 3 Seiten.");
	}

	@Override
	public String name() {
		return "N-Eck mit " + this.n + " Seiten der Laenge " + this.seitenLaenge;
	}

	@Override
	public double umfang() {

		return n * seitenLaenge;
	}

	public double getSeitenLaenge() {
		return seitenLaenge;
	}

	public void setSeitenLaenge(double seitenLaenge) throws IllegalArgumentException {
		if (seitenLaenge > 0)
			this.seitenLaenge = seitenLaenge;
		else
			throw new IllegalArgumentException("Die Seitenlaenge eines NEcks muss positiv sein.");
	}

	public double innenKreisRadius() {
		return (this.seitenLaenge / 2) * (1 / Math.tan(Math.PI / this.n));
	}

	public double aussenKreisRadius() {
		return (this.seitenLaenge / 2) * (1 / (Math.sin(Math.PI / this.n)));
	}

	@Override
	public double flaeche() {
		double seite = this.aussenKreisRadius();
		Dreieck d = new Dreieck(seite, seite, seitenLaenge);
		return d.flaeche() * n;
	}


	@Override
	public String toCSVString() {
		String result = TwoDFig.POLYGON+ ";";
		result += this.seitenLaenge + ";";
		result += this.n + ";";
		return result;
	}
}
