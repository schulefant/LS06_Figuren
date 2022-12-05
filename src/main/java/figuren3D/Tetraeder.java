/**
 * @author rollins
 */
package figuren3D;
import figuren2D.N_Eck;

//Ein Tetraeder hat vier gleiche gleichseitige Dreiecke
public class Tetraeder extends RegelmaessigeGeradePyramide<N_Eck> {

	public Tetraeder(double kante) {
		super(calcHoehe(kante), new N_Eck(kante,3));
	}

	//Hilfsmethode calcHoehe muss static sein, da sie im Konstruktor eingesetzt wird 
	//und die Instanz im Konstruktor noch nicht fertig aufgebaut ist
	private static double calcHoehe(double kante){

		//Strecke zum Mittelpunkt
		double sm= new N_Eck(kante,3).aussenKreisRadius()/3;
		//Pythagoras
		return Math.sqrt(kante*kante-sm*sm );
	}
	public double getKante(){
		return this.getGrundKantenLaenge() ;
	}
	@Override
	public String name() {
		return "Tetraeder mit Kante " + this.getGrundKantenLaenge() + ".";
	}


}
