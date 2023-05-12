import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import figuren3D.Figur3D;

public class MaterialCalculator {
	private Map<String, Double> oberflaechenPreise = new HashMap<>();
	private Map<String, Double> volumenPreise = new HashMap<>();
	private static Scanner scanner = new Scanner(System.in);

	public MaterialCalculator(Map<String, Double> oberflaechenPreise, Map<String, Double> volumenPreise) {
		super();
		this.oberflaechenPreise = oberflaechenPreise;
		this.volumenPreise = volumenPreise;
	}

	public MaterialCalculator(Path filePreiseOberflaechen, Path filePreiseFuellung) {
		List<String> strList;
		try {
			strList = Files.readAllLines(filePreiseOberflaechen);
			for (String str : strList) {
				String[] strArr = str.split(";");
				oberflaechenPreise.put(strArr[0], Double.parseDouble(strArr[1]));
			}
			strList = Files.readAllLines(filePreiseFuellung);
			for (String str : strList) {
				String[] strArr = str.split(";");
				volumenPreise.put(strArr[0], Double.parseDouble(strArr[1]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
//		//TestAusgabe Datei einlesen
//		for (String str : oberflaechenPreise.keySet())
//			System.out.print(str + " ");
//		System.out.println();
//		for (Double preis : oberflaechenPreise.values())
//			System.out.print(preis + " ");
//		System.out.println();
//		for (String str : oberflaechenPreise.keySet())
//			System.out.print(str + " ");
//		System.out.println();
//		for (Double preis : volumenPreise.values())
//			System.out.print(preis + " ");
//		System.out.println();
	}


	public String chooseOberflaechenPreis() {
		System.out.println("OBERFLAECHEN MATERIALEN");
		for (String matO : oberflaechenPreise.keySet()) {
			double oPreis = oberflaechenPreise.get(matO);
			System.out.println(matO + " kostet " + oPreis+ " pro Quadratmeter");
		}
		System.out.print("Welches Material soll es sein? ");
		return scanner.next();
	}

	public String chooseVolumenPreis() {
		System.out.println("FÜLLMATERIALEN");
		for(String matV : volumenPreise.keySet()) {
			double vPreis = volumenPreise.get(matV);
			System.out.println(matV + " kostet " + vPreis + " pro Kubikmeter");
		}
		System.out.print("Welches Material soll es sein? ");
		return scanner.next();
	}

	public double calculate(Figur3D fig, String oMaterial, String vMaterial) {
		double oPreis = oberflaechenPreise.get(oMaterial);
		double vPreis = volumenPreise.get(vMaterial);
		return oPreis * fig.oberflaeche() + vPreis * fig.volumen();
	}

	public void calculateAll(List<Figur3D> formen3D) {
		for (Figur3D fig : formen3D) {
			for ( String matO: oberflaechenPreise.keySet()) {
				double oPreis = oberflaechenPreise.get(matO);
				System.out.println(
						"Die Ummantelung von " + fig.name() + " mit " + matO + " kostet " + oPreis * fig.oberflaeche());
			}
			for (String matV: volumenPreise.keySet()) {
				double vPreis = volumenPreise.get(matV);
				System.out.println(
						"Die Fuellung von " + fig.name() + " mit " + matV + " kostet " + vPreis * fig.volumen());
			}
		}
	}

}
