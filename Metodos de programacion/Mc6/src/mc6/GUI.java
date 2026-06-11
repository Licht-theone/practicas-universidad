package mc6;

import fundamentos.*;

public class GUI {
	public static class EntreCero extends Exception {}
	public static class PerdidaPrecision extends Exception {}
	public static void main(String[] args) {
		Lectura lect = new Lectura("DIVISION");
		lect.creaEntrada("NUM1", 0);
		lect.creaEntrada("NUM2", 0);
		lect.esperaYCierra();
		int n1 = lect.leeInt("NUM1");
		int n2 = lect.leeInt("NUM2");
		try {
			int c = division(n1, n2);
			System.out.println("Cociente: " + c);
		}
		catch(EntreCero e) {
			System.out.println("No se puede dividir por cero");
		}
		catch(PerdidaPrecision p) {
			System.out.println("Perdida de precision int");
		}
	}
	
	public static int division (int a, int b) throws EntreCero, PerdidaPrecision {
		if (b == 0) {
			throw new EntreCero();
		}
		if (a % b != 0) {
			throw new PerdidaPrecision();
		}
		return a / b;
	}
	
}
