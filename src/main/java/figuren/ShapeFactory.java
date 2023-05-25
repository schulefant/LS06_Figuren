package figuren;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import figuren2D.*;
import figuren3D.*;

public class ShapeFactory {

	public static Figur2D create2DShape(TwoDFig type, List<Double> dimensions) throws IllegalArgumentException {
		switch (type) {
		case TRIANGLE:
			if (dimensions.size() >= 3)
				return new Dreieck(dimensions.get(0), dimensions.get(1), dimensions.get(2));
			break;
		case CIRCLE:
			if (!dimensions.isEmpty())
				return new Kreis(dimensions.get(0));
			break;
		case RECTANGLE:
			if (dimensions.size() >= 2)
				return new Rechteck(dimensions.get(0), dimensions.get(1));
			break;
		case POLYGON:
			if (dimensions.size() >= 2) {
				return new N_Eck(dimensions.get(0), dimensions.get(1).intValue());
			}
			break;
		}
		throw new IllegalArgumentException();
	}

	public static Figur3D create3DShape(ThreeDFig type, List<Double> dimensions) throws IllegalArgumentException {
		double anzahl = 0;

		switch (type) {
		case SPHERE:
			if (!dimensions.isEmpty())
				return new Kugel(dimensions.get(0));
			break;
		case CYLINDER:
			if (dimensions.size() > 1) {
				return new GeradesPrisma<Kreis>(dimensions.get(0), new Kreis(dimensions.get(1)));
			}
			break;
		case PRISM:
			if (dimensions.size() > 2) {
				return new GeradesPrisma<N_Eck>(dimensions.get(0), new N_Eck(dimensions.get(1),dimensions.get(2).intValue()));
			}
			break;
		case REGULARPRISM:
			if (dimensions.size() > 2) {
				return new GeradesPrisma<N_Eck>(dimensions.get(0), new N_Eck(dimensions.get(1), dimensions.get(2).intValue()));
			}
			break;
		case REGULARPYRAMID:
			if (dimensions.size() > 2) {
				return new RegelmaessigeGeradePyramide(dimensions.get(0), new N_Eck(dimensions.get(1), dimensions.get(2).intValue()));
			}
			break;
		case CONE:
			if (dimensions.size() > 1) {
				return new KreisKegel(dimensions.get(0), dimensions.get(1));
			}
			break;
		default:
			break;
		}
		throw new IllegalArgumentException();
	}

	public static Figur3D create3DShapeWithBase(ThreeDFig type, Figur2D base, List<Double> dimensions)//throws IllegalArgumentException 
	{
		switch (type) {
		case PRISM:
			if (dimensions.size() == 1) {
				return new GeradesPrisma<Figur2D>(dimensions.get(0), base);
			}
			break;
		case CONE:
			if (dimensions.size() == 1 && base.getClass().equals(Kreis.class)) {
				return new KreisKegel(dimensions.get(0), (Kreis) base);
			}
			break;
		case REGULARPRISM:
			if (dimensions.size() == 1 && base.getClass().equals(N_Eck.class)) {
				return new GeradesPrisma<N_Eck>(dimensions.get(0), (N_Eck) base);
			}
			break;
		case REGULARPYRAMID:
			if (dimensions.size() == 1 && base.getClass().equals(N_Eck.class)) {
				return new RegelmaessigeGeradePyramide(dimensions.get(0), (N_Eck) base);
			}
			break;
		case CYLINDER:
			if (dimensions.size() == 1 && base.getClass().equals(Kreis.class)) {
				return new GeradesPrisma<Kreis>(dimensions.get(0), (Kreis) base);
			}
			break;
		default:
			break;
		}
//		throw new IllegalArgumentException();
		return null;
		
	}

	public static void appendToCSVFile(ICSVString fig, Path p) {

		try (BufferedWriter bw = Files.newBufferedWriter(p, StandardOpenOption.APPEND)) {

			bw.write(fig.toCSVString());
			bw.write("\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeAllToCSVFile(List<? extends ICSVString> figs, Path p) {

		/* StandardOpenOption.CREATE löscht und truncated die Datei nicht. Wenn 
		 * aus dem letzen Speichern etwas übrig bleibt, weil Neuspeicherung kürzer, 
		 * dann komm es zu Fehlern beim Einlesen.
		 */
		try {
			Files.deleteIfExists(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try (BufferedWriter bw = Files.newBufferedWriter(p, StandardOpenOption.CREATE)) {

			for (ICSVString f : figs) {
				bw.write(f.toCSVString());
				bw.write("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<Figur2D> readAllTwoDShapes(Path p) {
		List<Figur2D> l = new ArrayList<>();
		List<String> strl;
		try {
			strl = Files.readAllLines(p);
			for (String str : strl) {
				String[] dims = str.split(";");
				TwoDFig tdf = TwoDFig.valueOf(dims[0]);
				List<String> strDim = Arrays.asList(dims);
				List<Double> dblL = new ArrayList<>();
				strDim = strDim.subList(1, strDim.size());
				for (String s : strDim)
					dblL.add(Double.parseDouble(s));
				l.add(create2DShape(tdf, dblL));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}

	public static List<Figur3D> readAllThreeDShapes(Path p) throws IOException {
		List<Figur3D> l = new ArrayList<>();
		List<String> strl = Files.readAllLines(p);
		for (String str : strl) {
			String[] dims = str.split(";");
			ThreeDFig tdf = ThreeDFig.valueOf(dims[0]);
			TwoDFig baseType = null;
			List<Double> dblL = new ArrayList<>();

			for (int i = 1; i < dims.length; i++) {
				try {
					dblL.add(Double.parseDouble(dims[i]));
				} catch (NumberFormatException e) {
					baseType = TwoDFig.valueOf(dims[i]);
				}
			}
			if (baseType == null)
				l.add(create3DShape(tdf, dblL));
			else
				l.add(create3DShapeWithBase(tdf, create2DShape(baseType, dblL.subList(1, dblL.size())),dblL.subList(0,1)));
		}
		return l;
	}
}
