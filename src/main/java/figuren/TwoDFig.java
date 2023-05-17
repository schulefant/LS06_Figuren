package figuren;

import java.util.ArrayList;
import java.util.List;

public enum TwoDFig {
	TRIANGLE, CIRCLE, RECTANGLE, POLYGON;

	public List<String> dimensionNames() {
		List<String> l = new ArrayList<>();
		switch (this) {
		case TRIANGLE:
			l.add("Seite A");
			l.add("Seite B");
			l.add("Seite C");
			break;
		case CIRCLE:
			l.add("Radius");
			break;
		case RECTANGLE:
			l.add("Höhe");
			l.add("Breite");
			break;
		case POLYGON:
			l.add("Seitenlänge");
			l.add("Anzahl Seiten");
			break;
		}
		return l;
	}

	public static TwoDFig intToEnum(int i) {
		switch (i) {
		case 0:
			return TRIANGLE;
		case 1:
			return CIRCLE;
		case 2:
			return RECTANGLE;
		case 3:
			return POLYGON;
		default:
			return CIRCLE;
		}
	}
}
