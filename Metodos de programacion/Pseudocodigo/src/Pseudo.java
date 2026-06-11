

public class Pseudo {

	public static void main(String[] args) {
		boolean tirar = true;
		int tirada;
		int puntos1 = 0;
		int puntos2 = 0;
		ledapaga();
		int objetivo = random(9);
		objetivo += 6;
		System.out.println("La puntuacion objetivo es:" + objetivo);
		System.out.println("\nTira el jugador1\n");
		do {
			tiradados();
			tirada = random(5);
			tirada += 1;
			puntos1 += tirada;
			pintacaradado(tirada);
			System.out.println("Quiere volver a tirar? 0=no 1=si");
			if (1) {
				tirar = false;
			}
		} while(tirar);
		System.out.println("\nTira el jugador 2\n");
		System.out.println("El anterior jugador tuvo una puntuacion de:" + puntos1);
		tirar = true;
		do {
			tiradados();
			tirada = random(5);
			tirada += 1;
			puntos2 += tirada;
			pintacaradado(tirada);
			System.out.println("Quiere volver a tirar? 0=no 1=si");
			if (1) {
				tirar = false;
			}
		} while(tirar);
		
		if (puntos1 > objetivo) {
			if (puntos2 > objetivo) {
				ledempate();
				empate(puntos1, puntos2);
			} else {
				ledgana2();
				System.out.println("Gana el jugador 2");
			}
		} else if (puntos2 > objetivo){
			ledgana();
			System.out.println("Gana el jugador 1");
		} else if (puntos1 == puntos2) {
			ledempate();
			empate(puntos1, puntos2);
		} else if (puntos1 > puntos2) {
			ledgana();
			System.out.println("Gana el jugador 1");
		} else {
			ledgana2();
			System.out.println("Gana el jugador 2");
		}
		System.exit(1);
	}
	
	public static void empate(int puntos1, int puntos2) {
		System.out.println("No hay ganador");
		System.out.println("Puntos jugador1:" + puntos1);
		System.out.println("Puntos jugador2:" + puntos2);
	}
	
	
	
	
	
	
	
	
	
	
	public static void ledapaga() {
		
	}
	
	public static int random(int num) {
		return 0;
	}
	
	public static void tiradados() {
		
	}
	
	public static void pintacaradado(int num) {
		
	}
	
	public static void ledgana() {
		
	}
	public static void ledgana2() {
		
	}
	public static void ledempate() {
		
	}
}
