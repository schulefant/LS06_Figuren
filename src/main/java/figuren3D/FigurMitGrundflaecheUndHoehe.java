/**
 * @author rollins
 */
package figuren3D;

import figuren2D.Figur2D;

public abstract class FigurMitGrundflaecheUndHoehe<F extends Figur2D> extends Figur3D {

	private F grund; // Die Typisierung erledigt den Fehlerabfang
	private double hoehe;

	public FigurMitGrundflaecheUndHoehe(double hoehe, F grund) {
		this.grund = grund;
		this.setHoehe(hoehe);
	}
	@Override
	public String toCSVString() {
		String result = hoehe + ";" + grund.toCSVString();
		return result;
	}
//	private String csvMinusBase() {
//		String str = "";
//		String[] strArr = grund.toCSVString().split(";");
//		for(int i = 1; i <strArr.length; i++) {
//			str+= strArr[i] + ";";
//		}
//		return str;
//	}

	/**
	 * 
	 * @return die Basis der dreidimensionalen Figur wird zurueckgegeben
	 * Es gibt keinen Setter, da die Grundfigur nachtraeglich nicht in eine andere Figur umgewandelt werden soll. 
	 * Die Groesse kann mit getGrund() und den Methoden der zweidimensionalen Figuren veraendert werden.
	 */
	public F getGrund() {
		return grund;
	}

	public double getHoehe() {
		return hoehe;
	}

	/**
	 * Die Hoehe muss positiv sein. 
	 */
	public void setHoehe(double hoehe) throws IllegalArgumentException {
		if (hoehe <= 0)
			throw new IllegalArgumentException("Ein gerades Prisma ohne positive Hoehe ist nicht valide.");
		this.hoehe = hoehe;
	}
}
