/**
 * @author rollins
 */
package figuren3D;

import figuren2D.Dreieck;
import figuren2D.N_Eck;

//Jede Seite ist gleich lang
public class RegelmaessigeGeradePyramide  extends GeradePyramide<N_Eck> {

	public RegelmaessigeGeradePyramide(double hoehe, N_Eck fig) {
		super(hoehe, fig);
	}
	public RegelmaessigeGeradePyramide(double hoehe, double kante, int anzahlSeiten) throws IllegalArgumentException{
		this(hoehe, new N_Eck(kante, anzahlSeiten));
	}

	@Override
	public double mantel() {
		return this.getSeitenDreieck().flaeche()*this.getAnzahlSeiten();
	}
	public double getGrundKantenLaenge(){
		return getGrund().getSeitenLaenge();
	}
	public int getAnzahlSeiten() {
		return this.getGrund().getN();
	}
	public double getSeitenKantenLaenge(){
		return Math.hypot(this.getGrund().aussenKreisRadius(),this.getHoehe()) ;
	}
	public Dreieck getSeitenDreieck() {
		return new Dreieck( this.getSeitenKantenLaenge(), this.getSeitenKantenLaenge(),this.getGrundKantenLaenge());
	} 
	public double getSeitenDreieckHoehe(){
		return Math.hypot(this.getGrund().innenKreisRadius(),this.getHoehe()) ;
	}
	@Override
	public String name() {
		return "Regelmaessige gerade Pyramide mit Hoehe " + this.getHoehe() + " und " + this.getAnzahlSeiten() + " Seiten.";
	}
}
