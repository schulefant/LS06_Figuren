package figuren;

import java.util.ArrayList;
import java.util.List;

public enum ThreeDFig {
	SPHERE, PRISM, CYLINDER, REGULARPRISM, REGULARPYRAMID, CONE;

	public List<String> dimensionNames() {
		List<String> l = new ArrayList<>();
		switch (this) {
		case SPHERE:
			l.add("Radius");
			break;
		case CYLINDER:
			l.add("Radius");
			l.add("Höhe");
			break;
		case PRISM:
			l.add("Höhe");
			break;
		case REGULARPRISM:
		case REGULARPYRAMID:
			l.add("Höhe");
			l.addAll(TwoDFig.POLYGON.dimensionNames());
			break;
		case CONE:
			l.add("Höhe");
			l.add("Radius des Grundkreises");
			break;
		}
		return l;
	}
//	Ursprünglich unter der Annahme geschreiben, dass bei Enums der Index gespreichert wird
	public static ThreeDFig intToEnum(int i) {
		switch (i) {
		case 0:
			return SPHERE;
		case 1:
			return PRISM;
		case 2:
			return CYLINDER;
		case 3:
			return REGULARPRISM;
		case 4:
			return REGULARPYRAMID;
		case 5:
			return CONE;
		default:
			return SPHERE;
		}
	}
}
