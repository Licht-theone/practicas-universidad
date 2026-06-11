package mc6;

import fundamentos.*;

public class GUIarray {
	public static class PosicionNoVal extends Exception {}
	public static void main(String[] args) {
		int[] v = {5,7,2,8,3};
		Lectura lect = new Lectura("ARRAYS");
		lect.creaEntrada("POS", 0);
		lect.esperaYCierra();
		int pos = lect.leeInt("POS");
		try {
			int num = posEnArray(v, pos);
			System.out.println("NUMERO: " + num);	
		}
		catch(PosicionNoVal p) {
			System.out.println("Out of bounds");
		}
		
	}
	public static int posEnArray(int v[], int pos) throws PosicionNoVal {
		if (pos > v.length) {
			throw new PosicionNoVal();
		}
		return v[pos];
	}
}
