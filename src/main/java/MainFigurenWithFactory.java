
/**
 * @author rollins
 */
import figuren3D.*;
import figuren2D.*;
import figuren.*;
import figuren.ShapeFactory.ThreeDFig;

import java.util.ArrayList;
import java.util.Random;

public class MainFigurenWithFactory {
	static Random rnd = new Random();
	ArrayList<Figur2D> formen2D = new ArrayList<>();
	ArrayList<Figur3D> formen3D = new ArrayList<>();
	ArrayList<GeradesPrisma<? extends Figur2D>> geradePrismen = new ArrayList<>();

	public static void main(String[] args) {
		MainFigurenWithFactory ft = new MainFigurenWithFactory();
		ft.init2D();
		ft.init3D();
		ft.testFigur2D();
		ft.testFigur3D();
	}

	private void testFigur2D() {
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

	private void testFigur3D() {
		for (Figur3D fig : formen3D) {
			System.out.println("Figur von Typ " + fig.name());
			System.out.println("    Oberflaeche: " + " " + Math.round(fig.oberflaeche() * 1000) / 1000.0);
			System.out.println("    Volumen: " + Math.round(fig.volumen() * 1000) / 1000.0);
		}

	}

	public void init2D() {
		formen2D.add(ShapeFactory.create2DShape(ShapeFactory.TwoDFig.CIRCLE, makeRandomDoubleList(1)));
		formen2D.add(ShapeFactory.create2DShape(ShapeFactory.TwoDFig.RECTANGLE, makeRandomDoubleList(2)));
		formen2D.add(ShapeFactory.create2DShape(ShapeFactory.TwoDFig.POLYGON, makeRandomDoubleList(2)));
		try {
			formen2D.add(ShapeFactory.create2DShape(ShapeFactory.TwoDFig.TRIANGLE, makeRandomDoubleList(3)));
			formen2D.add(ShapeFactory.create2DShape(ShapeFactory.TwoDFig.STAR, makeRandomDoubleList(3)));
			formen2D.add(ShapeFactory.create2DShape(ShapeFactory.TwoDFig.STICKFIGURE, makeRandomDoubleList(6)));
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void init3D() {
		formen3D.addAll(geradePrismen);
		formen3D.add(ShapeFactory.create3DShape(ThreeDFig.CONE, makeRandomDoubleList(2)));
		formen3D.add(ShapeFactory.create3DShape(ThreeDFig.CYLINDER, makeRandomDoubleList(2)));
		formen3D.add(ShapeFactory.create3DShape(ThreeDFig.REGULARPRISM, makeRandomDoubleList(3)));
		formen3D.add(ShapeFactory.create3DShape(ThreeDFig.SPHERE, makeRandomDoubleList(1)));
		formen3D.add(ShapeFactory.create3DShape(ThreeDFig.REGULARPYRAMID, makeRandomDoubleList(3)));
	}

	public static ArrayList<Double> makeRandomDoubleList(int number) {
		ArrayList<Double> values = new ArrayList<>();
		for (int i = 0; i < number; i++)
			values.add(rnd.nextInt(100) / 10.0 + 3);

		return values;
	}
}
