package examen.modelo;

/**
 * Superclase de los personajes del juego.
 * 
 * @author  Aaron Alegria
 * @version may-24
 */
public abstract class Personaje {
	
	private final String nombre;
	private double puntosVida;
	private Personaje eliminador = null;
	
	/**
	 * constructor de la superclase.
	 * @param nombre nombre del personaje
	 * @param puntosVida puntos iniciales de vida
	 */
	public Personaje(String nombre, double puntosVida) {
		this.nombre = nombre;
		this.puntosVida = puntosVida;
	}
	
	/**
	 * observador de los puntos de vida.
	 * @return los puntos de vida del personaje
	 */
	public double puntosVida() {
		return puntosVida;
	}
	
	/**
	 * modificador para los puntos de vida.
	 * @param puntos los puntos a restar
	 */
	public void restaPuntos(double puntos) {
		puntosVida -= puntos;
	}
	
	/**
	 * metodo para recibir el ataque de otro personaje.
	 * @param danho el daño recibido
	 * @param p el personaje que realiza el ataque
	 */
	public abstract void recibeAtaque(double danho, Personaje p);
	
	/**
	 * observador del nombre.
	 * @return el nombre
	 */
	public String nombre() {
		return nombre;
	}

	/**
	 * observador del eliminador (si lo tiene).
	 * @return el eliminador
	 */
	public Personaje eliminador() {
		return eliminador;
	}
	
	/**
	 * modificador del eliminador.
	 * @param p el personaje que elimina
	 */
	public void elimina(Personaje p) {
		eliminador = p;
	}
	
	/**
	 * metodo para realizar el ataque.
	 * @return los puntos de daño que hace el personaje
	 */
	public abstract double ataca();
}
