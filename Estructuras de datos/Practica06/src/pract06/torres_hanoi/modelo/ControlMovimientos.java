package pract06.torres_hanoi.modelo;

/**
 * Clase que realiza el control de los movimientos de los discos entre las
 * torres. Implementa el algoritmo que permite mover los discos de la torre
 * origen a la torre destino.
 * 
 * @author Metodos de Programacion (UC) y Aaron Alegria
 * @version oct-2023
 */
public class ControlMovimientos {
	private static final int NUM_TORRES = 3;
	private static final int NUM_DISCOS = 4;

	private Torre[] torres = new Torre[NUM_TORRES];

	private int movimiento = 1;
	private int posTorreDiscoMenor = 0;

	/**
	 * Crea el controlador de movimientos con los discos en su posicion
	 * inicial.
	 */
	public ControlMovimientos() {
		// crea las torres
		for (int i = 0; i < NUM_TORRES; i++) {
			torres[i] = new Torre(i);
		}

		// pone los discos en la primera torre
		for (int i = NUM_DISCOS - 1; i >= 0; i--) {
			torres[0].apilaDisco(new Disco(i + 1));
		}
	}

	/**
	 * Realiza el movimiento de un disco entre dos torres. Este movimiento
	 * corresponde a un paso del algoritmo que permite mover los discos de la
	 * torre origen a la torre destino.
	 */
	public void realizaMovimiento() {
		System.out.println(this); // para depuracion
		int posPrevia = posTorreDiscoMenor;
		if (movimiento % 2 == 1) {
			// movimiento impar
			// mueve el disco mas pequenho y actualiza posTorreDiscoMenor
			// El disco pequenho se mueve a la torre de su derecha (si el numero de discos
			// es par) o a la de su izquierda (si el numero de discos es impar)
			if (torres[posTorreDiscoMenor].numDiscos() % 2 == 0) {
				posTorreDiscoMenor = (posTorreDiscoMenor + 1) % NUM_TORRES;
				torres[posTorreDiscoMenor].apilaDisco(torres[posPrevia].desapilaDisco());
			} else {
				posTorreDiscoMenor = (posTorreDiscoMenor - 1) % NUM_TORRES;
				torres[posTorreDiscoMenor].apilaDisco(torres[posPrevia].desapilaDisco());
			}

		} else {
			// movimiento par
			// realiza el unico movimiento de disco posible entre torres que no
			// involucra al disco mas pequenho
			int torreA = (posTorreDiscoMenor + 1) % NUM_TORRES;
			int torreB = (posTorreDiscoMenor + 1) % NUM_TORRES;
			if (torres[torreA].numDiscos() > 0 && 
					(torres[torreB].numDiscos() == 0 || 
					torres[torreA].discoEnCima().longitud() < 
					torres[torreB].discoEnCima().longitud())) {
				torres[torreB].apilaDisco(torres[torreA].desapilaDisco());
			} else {
				torres[torreA].apilaDisco(torres[torreB].desapilaDisco());
			}

		}

		movimiento++;
	}

	/**
	 * Retorna el numero de torres.
	 * @return el numero de torres.
	 */
	public int numTorres() {
		return torres.length;
	}

	/**
	 * Retorna la torre en la posicion indicada.
	 * @param pos posicion de la torre.
	 * @return la torre en la posicion indicada.
	 */
	public Torre torreEnPos(int pos) {
		return torres[pos];
	}

	@Override
	public String toString() {
		String str = "Movimiento:" + movimiento + "\n";
		for (int i = 0; i < torres.length; i++) {
			str += "Torre " + i + ":" + torres[i] + "\n";
		}
		return str;
	}

}
