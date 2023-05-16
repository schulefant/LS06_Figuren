package figuren;

import java.util.ArrayList;
import java.util.List;

public enum TwoDFig {
	TRIANGLE, CIRCLE, RECTANGLE, POLYGON, STAR, STICKFIGURE;

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
		case STAR:
			l.add("Breite eines Zackens");
			l.add("Anzahl Zacken");
			l.add("Seitenlänge eines Zackens");
			break;
		case STICKFIGURE:
			l.add("Radius des Kopfes");
			l.add("Armlänge");
			l.add("Armbreite");
			l.add("Beinlänge");
			l.add("Beinbreite");
			l.add("Körperseite");
			l.add("Körperbreite");
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
		case 4:
			return STAR;
		case 5:
			return STICKFIGURE;
		default:
			return CIRCLE;
		}
	}
}
