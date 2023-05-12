
/**
 * @author rollins
 */
import figuren3D.*;
import figuren2D.*;
import figuren.*;
import figuren.ThreeDFig;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MainFigurenWithFactory {
	private static Scanner scanner = new Scanner(System.in);
	private static Random rnd = new Random();
	private static List<Figur2D> formen2D = new ArrayList<>();
	private static List<Figur3D> formen3D = new ArrayList<>();
	private static List<GeradesPrisma<? extends Figur2D>> geradePrismen = new ArrayList<>();

	private static MaterialCalculator matCalc = new MaterialCalculator(Paths.get("Materialpreise Oberflaechen.csv"),
			Paths.get("Materialpreise Fuellung.csv"));

	public static void main(String[] args) {
		init2D();
		ShapeFactory.writeAll(formen2D, Paths.get("figuren2D.csv"));
		ausgabe(formen2D);
		formen2D.clear();
		System.out.println("Nach dem Wieder einlesen:");
		formen2D = ShapeFactory.readAllTwoDShapes(Paths.get("figuren2D.csv"));
		ausgabe(formen2D);
		

//		init3D();
//		ShapeFactory.writeAll(formen3D, Paths.get("figuren3D.csv"));

	}

	private static ThreeDFig menueAuswahl3D() {
		int selector = 1;
		for (ThreeDFig tdf : ThreeDFig.values()) {
			System.out.println(selector++ + " " + tdf);
		}
		System.out.print("Welche Figur soll erstellt werden? ");
		return ThreeDFig.intToEnum(scanner.nextInt() - 1);
	}

	private static TwoDFig menueAuswahl2D() {
		int selector = 1;
		for (TwoDFig tdf : TwoDFig.values()) {
			System.out.println(selector++ + " " + tdf);
		}
		System.out.print("Welche Figur soll erstellt werden? ");
		return TwoDFig.intToEnum(scanner.nextInt() - 1);
	}

	private static List<Double> readValuesFor(TwoDFig tdf) {
		List<Double> l = new ArrayList<>();
		try {
			for (String str : tdf.dimensionNames()) {
				System.out.print(str + ":");
				l.add(scanner.nextDouble());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;
	}

	@SuppressWarnings("incomplete-switch")
	private static List<Double> readValuesFor(ThreeDFig tdf) {
		List<Double> l = new ArrayList<>();
		try {
			for (String str : tdf.dimensionNames()) {
				System.out.print(str + ":");
				l.add(scanner.nextDouble());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		TwoDFig base;
		switch (tdf) {
		case PRISM:
		case REGULARPRISM:
		case REGULARPYRAMID:
			System.out.println("Welche Figur sollte als Basis dienen?");
			base = menueAuswahl2D();
			l.addAll(readValuesFor(base));
			break;
		}
		return l;
		// iteriere über Figurenangebot und erzeuge gewünschte Figur mit Factory
		// TODO iteriere Über Stoffe
		// lese Benutzerwunsch ein
		// berechnet mit matCalc.calculate( figur, oMaterial, vMaterial )

	}

	public static void calcPreiseMitAuswahl() {
		ThreeDFig tdf = menueAuswahl3D();
		List<Double> valList = readValuesFor(tdf);
		Figur3D fig = ShapeFactory.create3DShape(tdf, valList);
		String matO = matCalc.chooseOberflaechenPreis();
		String matV = matCalc.chooseVolumenPreis();
		double preis = matCalc.calculate(fig, matO, matV);
		System.out.println(fig.name() + " mit " + matO + "und mit " + matV + " kostet " + preis);
	}

	public static void testMaterialCalculator() {
		matCalc = new MaterialCalculator(Paths.get("Materialpreise Oberflaechen.csv"),
				Paths.get("Materialpreise Fuellung.csv"));
		try {
			matCalc.calculateAll(formen3D);
		} catch (Exception e) {
			System.out.println("Da ist etwas bei der Materialenkalkulation schief gelaufen.");
			e.printStackTrace();
		}

	}
	
	public static void ausgabe(List<? extends ICSVString> figs) {
		for(ICSVString f : figs)
			System.out.println(f.toCSVString());
	}

	public static void init2D() {
		formen2D.add(ShapeFactory.create2DShape(TwoDFig.CIRCLE, makeRandomDoubleList(1)));
		formen2D.add(ShapeFactory.create2DShape(TwoDFig.RECTANGLE, makeRandomDoubleList(2)));
		formen2D.add(ShapeFactory.create2DShape(TwoDFig.POLYGON, makeRandomDoubleList(2)));
		try {
			formen2D.add(ShapeFactory.create2DShape(TwoDFig.TRIANGLE, makeRandomDoubleList(3)));
			formen2D.add(ShapeFactory.create2DShape(TwoDFig.STAR, makeRandomDoubleList(3)));
			List<Double> temp= new ArrayList<>();
			temp.add(1.0);
			temp.add(4.0);temp.add(2.0);
			temp.add(3.0);temp.add(0.3);
			temp.add(4.0);temp.add(0.6);
			formen2D.add(ShapeFactory.create2DShape(TwoDFig.STICKFIGURE, temp));
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void init3D() {
		for (Figur2D f : formen2D) {
			try {
				N_Eck n = (N_Eck) ShapeFactory.create2DShape(TwoDFig.POLYGON, makeRandomDoubleList(2));
				formen3D.add(ShapeFactory.create3DShapeWithBase(ThreeDFig.PRISM, f, makeRandomDoubleList(1)));
				formen3D.add(ShapeFactory.create3DShapeWithBase(ThreeDFig.REGULARPYRAMID, n, makeRandomDoubleList(1)));
				formen3D.add(ShapeFactory.create3DShapeWithBase(ThreeDFig.REGULARPRISM, n, makeRandomDoubleList(1)));
			} catch (IllegalArgumentException e) {
//				e.printStackTrace();
			}
		}
		formen3D.addAll(geradePrismen);
		formen3D.add(ShapeFactory.create3DShape(ThreeDFig.CONE, makeRandomDoubleList(2)));
		formen3D.add(ShapeFactory.create3DShape(ThreeDFig.CYLINDER, makeRandomDoubleList(2)));
		formen3D.add(ShapeFactory.create3DShape(ThreeDFig.REGULARPRISM, makeRandomDoubleList(3)));
		formen3D.add(ShapeFactory.create3DShape(ThreeDFig.SPHERE, makeRandomDoubleList(1)));
		formen3D.add(ShapeFactory.create3DShape(ThreeDFig.REGULARPYRAMID, makeRandomDoubleList(3)));
	}

	public static List<Double> makeRandomDoubleList(int number) {
		ArrayList<Double> values = new ArrayList<>();
		for (int i = 0; i < number; i++)
			values.add(rnd.nextInt(100) / 10.0 + 3);

		return values;
	}

	private static void testFigur2D() {
		for (Figur2D fig : formen2D) {
			System.out.println("Figur von Typ " + fig.name());
			System.out.println("    Flaeche: " + " " + fig.flaeche());
			System.out.println("    Umfang: " + " " + fig.umfang());
			if ("N_Eck".equals(fig.toString().substring(fig.toString().indexOf(".") + 1))) {
				System.out.println("    Aussenradius:" + ((N_Eck) fig).aussenKreisRadius());
				System.out.println("    Innenradius:" + ((N_Eck) fig).innenKreisRadius());
			}
		}
	}

	private static void testFigur3D() {
		for (Figur3D fig : formen3D) {
			System.out.println("Figur von Typ " + fig.name());
			System.out.println("    Oberflaeche: " + " " + Math.round(fig.oberflaeche() * 1000) / 1000.0);
			System.out.println("    Volumen: " + Math.round(fig.volumen() * 1000) / 1000.0);
		}

	}

}
