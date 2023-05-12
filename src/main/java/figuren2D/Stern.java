/**
 * @author rollins
 */
package figuren2D;

import figuren.TwoDFig;

public class Stern extends ZusammengesetzteFigur{

	public Stern(double zackenbreite, int anzahlZacken, double zackenlaenge )throws IllegalArgumentException {
		N_Eck zentrum = new N_Eck(zackenbreite,anzahlZacken);
		super.addTeilfigur(zentrum);
		for( int i=1; i < anzahlZacken; i++){
			addTeilfigur( new Dreieck( zackenlaenge, zackenlaenge, zackenbreite));
		}
	}
	protected N_Eck getZentrum() {
		return (N_Eck) this.getTeilfigur(0);
	}
	protected Dreieck getZacke() {
		return ((Dreieck)this.getTeilfigur(1));
	}
	@Override
	public String name() {return "Stern  mit "+ this.getZentrum().getN() + " Zacken " + " der Laenge " + getZacke().hoehe();}
	@Override
	public double umfang() {
		double u =0;
		for( Figur2D f : getTeile())
			u+= f.umfang();

		return u - 2*getTeilfigur(0).umfang();
	}
	@Override
	public String toCSVString() {
		return TwoDFig.STAR + ";" + csvMinusFigType(this.getZentrum()) +this.getZacke().getSeiteC() + ";" ;
	}
}

