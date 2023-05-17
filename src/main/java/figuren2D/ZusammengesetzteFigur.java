/**
 * @author rollins
 */
package figuren2D;

import java.util.ArrayList;
import java.util.List;

public abstract class ZusammengesetzteFigur extends Figur2D {

	private ArrayList <Figur2D> teile = new ArrayList<>();
	
	public List<Figur2D> getTeile() {
		return teile;
	}
	@Override
	public double flaeche() {
		double flaeche =0;
		for( Figur2D f : teile){
			flaeche+= f.flaeche();
		}
		return flaeche;
	}
	public void addTeilfigur(Figur2D neuesTeil){
		teile.add(neuesTeil);
	}
	public Figur2D getTeilfigur(int index){
		return teile.get(index);
	}
	@Override
	public String toCSVString() {
		String result = "";
		for(Figur2D f: teile)
			result += f.toCSVString();
		return result;
	}
}
