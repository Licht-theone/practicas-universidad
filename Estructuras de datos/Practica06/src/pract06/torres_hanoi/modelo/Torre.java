package pract06.torres_hanoi.modelo;

import pract06.pila_enlaces.PilaSimpleEnlace;

/**
 * Torre que permite apilar discos.
 * 
 * @author Metodos de Programacion (UC) y Aaron Alegria
 * @version oct-2023
 */
public class Torre {
	// Posicion de la torre (La posicion 0 corresponde a la torre mas a la izquierda).
	private final int posicion;
	
	// Pila con los discos en la torre
	private PilaSimpleEnlace<Disco> discos;

	/**
	 * Construye la torre.
	 * @param posicion posicion que ocupa en el juego. La posicion 0 corresponde
	 * a la torre mas a la izquierda.
	 */
	public Torre(int posicion) {
		this.posicion = posicion;
	}

	/**
	 * Retorna la posicion de la torre.
	 * @return la posicion de la torre.
	 */
	public int posicion() {
		return posicion;
	}

	/**
	 * Apila un disco en la torre.
	 * @param disco disco a apilar.
	 */
	public void apilaDisco(Disco disco) {
		discos.apila(disco);
	}

	/**
	 * Desapila el disco que se encuentra en la cima de la torre.
	 * @return el disco desapilado.
	 */
	public Disco desapilaDisco() {
		
		return discos.desapila();
	}

	/**
	 * Retorna el numero de discos que hay apilados en la torre.
	 * @return el numero de discos que hay apilados en la torre.
	 */
	public int numDiscos() {
		return discos.tamanho();
	}

	/**
	 * Retorna, sin desapilar, el disco que se encuentra en la cima de la torre.
	 * @return el disco que se encuentra en la cima de la torre.
	 */
	public Disco discoEnCima() {
		return discos.cima();
	}

	@Override
	public String toString() {
		return discos.toString();
	}
	
	
}
