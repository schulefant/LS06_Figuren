package figuren;

import java.util.List;

import figuren2D.*;
import figuren3D.*;

public class ShapeFactory {
	public enum TwoDFig {TRIANGLE, CIRCLE, RECTANGLE, POLYGON, STAR}
	public enum ThreeDFig {SPHERE, PYRAMID,REGULARPRISM}
	
	public static Figur2D create2DShape(TwoDFig type, List<Double> dimensions) throws IllegalArgumentException{
		switch( type ) {
		case TRIANGLE: 
			if(dimensions.size()>=3)
				return new Dreieck(dimensions.get(0),dimensions.get(1),dimensions.get(2));
			break;
		case CIRCLE: 
			if(!dimensions.isEmpty())
				return new Kreis(dimensions.get(0));
			break;
		case RECTANGLE: 
			if(dimensions.size()>=2)
				return new Rechteck(dimensions.get(0),dimensions.get(1));
			break;
		case POLYGON: 
			if(dimensions.size()>=2) {
				double anzahl = dimensions.get(1);
				return new N_Eck(dimensions.get(0),(int)anzahl );
			}
			break;
		case STAR:
			if(dimensions.size()>=3) {
				double anzahl = dimensions.get(2);				
				return new Stern(dimensions.get(0),dimensions.get(1),(int)anzahl);
			}
			break;
		}
		throw new IllegalArgumentException();
	}
	public static Figur3D create3DShape(ThreeDFig type, List<Double> dimensions) throws IllegalArgumentException{
		double anzahl=0;

		switch( type ) {
		case SPHERE: 
			if(!dimensions.isEmpty())
				return new Kugel(dimensions.get(0));
			break;
		case REGULARPRISM: 
			if(dimensions.size()>2) {
				anzahl = dimensions.get(2);
				return new GeradesPrisma<N_Eck>(dimensions.get(0),new N_Eck(dimensions.get(1), (int)anzahl));
			}
			break;
		case PYRAMID: 
			if(dimensions.size()>2) {
				anzahl = dimensions.get(2);
				return new RegelmaessigeGeradePyramide<N_Eck>(dimensions.get(0),new N_Eck(dimensions.get(1), (int)anzahl));
			}
			break;
		}
		throw new IllegalArgumentException();
	}
}
