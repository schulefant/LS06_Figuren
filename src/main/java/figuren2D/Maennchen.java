/**
 * @author rollins
 */
package figuren2D;

import figuren.TwoDFig;

public class Maennchen extends ZusammengesetzteFigur {

	/**
	 * Zugriffe auf die Extremitaeten als Eigenschaften in der Klasse, um den Zugriff
	 * auf einzelne Koerperteile zu erleichtern. Erstellung der Instanzen jedoch nur
	 * 1x
	 */
	private Kreis kopf;
	private Dreieck koerper;
	private Rechteck[] armBein = new Rechteck[4];

	public Maennchen(double kopfRadius, double koerperseite, double koerperBreite, double armBeinLaenge,
			double armBeinBreite) throws IllegalArgumentException {

		kopf = new Kreis(kopfRadius);
		this.getTeile().add(kopf);
		koerper = new Dreieck(koerperseite, koerperseite, koerperBreite);
		this.getTeile().add(koerper);

		for (int i = 0; i < 4; i++) {
			armBein[i] = new Rechteck(armBeinLaenge, armBeinBreite);
			this.getTeile().add(armBein[i]);
		}
	}

	public Maennchen(Kreis kopf, Dreieck koerper, Rechteck armBein) {
		this.kopf = kopf;
		this.getTeile().add(this.kopf);
		this.koerper = koerper;
		this.getTeile().add(koerper);

		for (int i = 0; i < 4; i++) {
			// Es wird nur ein Rechteck uebergeben, benoetigt werden vier, daher
			// Kopierkonstruktor
			this.armBein[i] = new Rechteck(armBein);
			this.getTeile().add(this.armBein[i]);
		}
	}

	@Override
	public String name() {
		return "Maennchen";
	}

	@Override
	public double umfang() {

		double umfang = kopf.umfang() + koerper.umfang();

		for (int i = 0; i < armBein.length; i++) {
			umfang += armBein[i].umfang();
			// abzueglich der Beruehrungsflaechen von Armen und Beinen:
			umfang -= armBein[i].getBreite() * 2;
		}
		return umfang;
	}

	@Override
	public String toCSVString() {
		return TwoDFig.STICKFIGURE + ";" + super.toCSVString();
	}
}
