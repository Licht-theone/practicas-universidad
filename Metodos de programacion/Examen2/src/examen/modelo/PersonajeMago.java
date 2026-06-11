package examen.modelo;

/**
 * Subclase de los personajes del juego para los magos.
 * 
 * @author  Aaron Alegria
 * @version may-24
 */
public class PersonajeMago extends Personaje {
	private int energia;
	private static final int DANHO_MAX = 14;
	private static final int DANHO_MIN = 4;
	private static final int ENERGIA = 10;
	
	/**
	 * constructor.
	 * @param nombre nombre del personaje
	 * @param puntosVida vida inicial
	 */
	public PersonajeMago(String nombre, double puntosVida) {
		super(nombre, puntosVida);
		energia = ENERGIA;
	}

	@Override
	public void recibeAtaque(double danho, Personaje p) {
		restaPuntos(danho);
		if (puntosVida() <= 0) {
			elimina(p);
		}

	}
	
	/**
	 * observador de la energia.
	 * @return la energia
	 */
	public int energia() {
		return energia;
	}

	/**
	 * recarga la energia del personaje.
	 * @param puntos los puntos a recargar
	 */
	public void recargaEnergia(int puntos) {
		energia += puntos;
	}
	
	@Override
	public double ataca() {
		if (energia > 0) {
			energia--;
			return DANHO_MAX;
		} else {
			return DANHO_MIN;
		}
	}

}
