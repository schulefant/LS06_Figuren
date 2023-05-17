
/**
 * @author rollins
 */
import figuren3D.*;
import figuren2D.*;
import figuren.*;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

public class MainFiguren {
	static Random rnd = new Random();
	ArrayList<Figur2D> formen2D = new ArrayList<>();
	ArrayList<Figur3D> formen3D = new ArrayList<>();
	ArrayList<GeradesPrisma<? extends Figur2D>> geradePrismen = new ArrayList<>();

	RegelmaessigeGeradePyramide drei = new RegelmaessigeGeradePyramide(6, new N_Eck(5, 3));
	RegelmaessigeGeradePyramide vier = new RegelmaessigeGeradePyramide(6, new N_Eck(5, 4));
	RegelmaessigeGeradePyramide sieben = new RegelmaessigeGeradePyramide(6, new N_Eck(5, 7));
	KreisKegel kk = new KreisKegel(6, 2.5);
	Tetraeder tetra = new Tetraeder(5);
	Kugel k = new Kugel(2.5);

	public static void main(String[] args) {
		MainFiguren ft = new MainFiguren();
		ft.init();
//		ft.testWerte3D();
//
//		ft.testFigur2D();
//		ft.testFigur3D();

	}

	private void testWerte3D() {

		System.out.println("Kugel mit Radius 2,5:");
		System.out.println("    Volumen: " + k.volumen());
		System.out.println("    Oberflaeche: " + " " + k.oberflaeche());

		System.out.println("Kreiskegel mit Hoehe 6 und Radius 2,5:");
		System.out.println("    Volumen: " + kk.volumen());
		System.out.println("    Oberflaeche: " + " " + kk.oberflaeche());

		System.out.println("Dreieckige regelmaessige Pyramide mit Hoehe 6 und Kante 5:");
		System.out.println("    Volumen: " + drei.volumen());
		System.out.println("    Oberflaeche: " + " " + drei.oberflaeche());

		System.out.println("Viereckige regelmaessige Pyramide mit Hoehe 6 und Kante 5:");
		System.out.println("    Volumen: " + vier.volumen());
		System.out.println("    Oberflaeche: " + " " + vier.oberflaeche());

		System.out.println("Siebeneckige regelmaessige Pyramide mit Hoehe 6 und Kante 5:");
		System.out.println("    Volumen: " + sieben.volumen());
		System.out.println("    Oberflaeche: " + " " + sieben.oberflaeche());
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

	public void init() {
		formen2D.add(new Maennchen(new Kreis(1.5), new Dreieck(4, 6, 6), new Rechteck(1, 5)));
		formen2D.add(new Stern(3, 6, 5));
		formen2D.add(new N_Eck(5, 3));
		formen2D.add(new N_Eck(3.5, 5));
		formen2D.add(new Kreis(3));
		formen2D.add(new Dreieck(6, 7, 9));
		formen2D.add(new Rechteck(4, 7.7));
		formen2D.add(new N_Eck(3, 9)); // Nonagon

//Figur3D		
		for (Figur2D fig : formen2D) {
			geradePrismen.add(new GeradesPrisma<Figur2D>(rnd.nextInt(10), fig));
		}
		formen3D.addAll(geradePrismen);

		formen3D.add(kk);
		formen3D.add(drei);
		formen3D.add(vier);
		formen3D.add(sieben);
		formen3D.add(k);
	}
}
