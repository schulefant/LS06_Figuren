/**
 * @author rollins
 */
package figuren2D;

import figuren.TwoDFig;

public class Maennchen extends ZusammengesetzteFigur {

	/**
	 * Zugriffe auf die Extremitaeten als Eigenschaften in der Klasse, um den
	 * Zugriff auf einzelne Koerperteile zu erleichtern. Erstellung der Instanzen
	 * jedoch nur 1x
	 */
	private Kreis kopf;
	private Dreieck koerper;
	private Rechteck[] arme = new Rechteck[2];
	private Rechteck[] beine = new Rechteck[2];

	public Maennchen(double kopfRadius, double koerperseitenLaenge, double koerperBreite, double armLaenge,
			double armBreite, double beinLaenge, double beinBreite) throws IllegalArgumentException {

		kopf = new Kreis(kopfRadius);
		this.getTeile().add(kopf);
		koerper = new Dreieck(koerperseitenLaenge, koerperseitenLaenge, koerperBreite);
		this.getTeile().add(koerper);
		for (int i = 0; i < 2; i++) {
			arme[i] = new Rechteck(armLaenge, armBreite);
			this.getTeile().add(arme[i]);
		}
		for (int i = 0; i < 2; i++) {
			beine[i] = new Rechteck(beinLaenge, beinBreite);
			this.getTeile().add(beine[i]);
		}

	}

	public Maennchen(Kreis kopf, Dreieck koerper, Rechteck arm, Rechteck bein) {
		this.kopf = kopf;
		this.getTeile().add(this.kopf);
		this.koerper = koerper;
		this.getTeile().add(koerper);

		for (int i = 0; i < 2; i++) {
			// Es wird nur ein Rechteck uebergeben, benoetigt werden vier, daher
			// Kopierkonstruktor
			this.arme[i] = new Rechteck(arm);
			this.getTeile().add(this.arme[i]);
			this.beine[i] = new Rechteck(bein);
			this.getTeile().add(this.beine[i]);
		}
	}

	@Override
	public String name() {
		return "Maennchen";
	}

	@Override
	public double umfang() {

		double umfang = kopf.umfang() + koerper.umfang();

		umfang += 2 * arme[0].umfang();
		// abzueglich der Beruehrungsflaechen von Armen und Beinen:
		umfang -= 4 * arme[0].getBreite();

		umfang += 2 * beine[0].umfang();
		// abzueglich der Beruehrungsflaechen von Armen und Beinen:
		umfang -= 4 * beine[0].getBreite();
		return umfang;
	}

	@Override
	public String toCSVString() {
		return TwoDFig.STICKFIGURE + ";" + ZusammengesetzteFigur.csvMinusFigType(kopf)
				+ koerper.getSeiteA()+ ";" + koerper.getSeiteC()+ ";"+ ZusammengesetzteFigur.csvMinusFigType(arme[0])
				+ ZusammengesetzteFigur.csvMinusFigType(beine[0]);
	}
}
