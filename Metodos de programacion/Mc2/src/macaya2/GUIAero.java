package macaya2;

import fundamentos.*;

public class GUIAero {

	public static void main(String[] args) {
		//Auto-generated method stub
		Aeropuerto sdr = new Aeropuerto(3);
		Avion a1 = new Avion("Airbus", 250);
		Avion a2 = new Avion("Boegin", 130);
		Avion a3 = new Avion("Lockheed Martin", 30);

		if (sdr.aterrizaAvion(a1) == false) {
			System.out.println(a1.getId()+" No pudo aterrizar\n");
		}

		else {
			System.out.println(a1.getId()+" Se pudo aterrizar\n");
		}

		if (sdr.aterrizaAvion(a2) == false) {
			System.out.println(a2.getId()+" No pudo aterrizar\n");
		}

		else {
			System.out.println(a2.getId()+" Se pudo aterrizar\n");
		}

		if (sdr.aterrizaAvion(a3) == false) {
			System.out.println(a3.getId()+" No pudo aterrizar\n");
		}

		else {
			System.out.println(a3.getId()+" Se pudo aterrizar\n");
		}

		System.out.println(sdr.toString());
		Avion Avbus = sdr.buscaAvion("Avion2");
		if (Avbus == null) {
			System.out.println("no encontrado\n");
		}
		else {
			System.out.println(Avbus);
		}
	}
}

