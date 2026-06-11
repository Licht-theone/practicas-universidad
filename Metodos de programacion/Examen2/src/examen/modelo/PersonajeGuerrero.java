package examen.modelo;

/**
 * Subclase de los personajes del juego para los guerreros.
 * 
 * @author  Aaron Alegria
 * @version may-24
 */
public class PersonajeGuerrero extends Personaje {
	private final int fuerza;
	private static final int DANHO_POR_PUNTO = 2;
	private static final double DANHO_RECIBIDO = 0.5;
	
	/**
	 * constructor.
	 * @param nombre nombre del personaje
	 * @param puntosVida puntos de vida iniciales
	 * @param fuerza fuerza del guerrero
	 */ 
	public PersonajeGuerrero(String nombre, double puntosVida, int fuerza) {
		super(nombre, puntosVida);
		this.fuerza = fuerza;
	}
	
	/**
	 * observador de la fuerza.
	 * @return la fuerza
	 */
	public int fuerza() {
		return fuerza;
	}

	@Override
	public double ataca() {
		return DANHO_POR_PUNTO * fuerza;
	}

	@Override
	public void recibeAtaque(double danho, Personaje p) {
		double puntos = DANHO_RECIBIDO * danho;
		restaPuntos(puntos);
		if (puntosVida() <= 0) {
			elimina(p);
		}
	}
	
}
