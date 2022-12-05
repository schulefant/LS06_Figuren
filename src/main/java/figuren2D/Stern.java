/**
 * @author rollins
 */
package figuren2D;

public class Stern extends ZusammengesetzteFigur{

	public Stern(double zackenbreite, double zackenlaenge, int anzahlZacken)throws IllegalArgumentException {
		N_Eck zentrum = new N_Eck(zackenbreite,anzahlZacken);
		super.addTeilfigur(zentrum);
		for( int i=1; i < anzahlZacken; i++){
			addTeilfigur( new Dreieck( zackenbreite, zackenlaenge, zackenlaenge));
		}
		
	}
	protected N_Eck getZentrum() {
		return (N_Eck) this.getTeilfigur(0);
	}
	@Override
	public String name() {return "Stern  mit "+ this.getZentrum().getN() + " Zacken " + " der Laenge " + ((Dreieck)this.getTeilfigur(1)).getHoehe();}
	@Override
	public double umfang() {
		double u =0;
		for( Figur2D f : getTeile())
			u+= f.umfang();

		return u - 2*getTeilfigur(0).umfang();
	}

}
