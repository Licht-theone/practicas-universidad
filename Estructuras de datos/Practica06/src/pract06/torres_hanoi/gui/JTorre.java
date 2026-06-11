package pract06.torres_hanoi.gui;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayDeque;
import java.util.Deque;

import j2d.JObjetoRectangulo;
import pract06.torres_hanoi.modelo.Torre;

/**
 * Representacion de las torres en la parte grafica del juego de las Torres de
 * Hahoi.
 * 
 * @author Metodos de Programacion (UC)
 * @version oct-2023
 */
public class JTorre extends JObjetoRectangulo {
	private static final int ANCHO_X = 10;
	public static final int ALTO_Y = 250;
	private static final Color color = Color.DARK_GRAY;
	private Deque<JDisco> discos = new ArrayDeque<>();
	private final Torre torre;

	/**
	 * Construye la torre de la GUI.
	 * @param torre objeto torre del modelo que esta asociado con la torre de
	 * la GUI.
	 */
	public JTorre(Torre torre) {
		super(null, ANCHO_X, ALTO_Y, color);
		this.torre = torre;
		this.colisionador().desactiva();
		this.asignaFactorGravedad(0);
	}
	
	/**
	 * Posicion ocupada por la torre (0 es la posicion de la torre mas a la
	 * izquierda).
	 * @return posicion ocupada por la torre.
	 */
	public int posicionTorre() {
		return torre.posicion();
	}

	/**
	 * Pone un disco en la cima de la torre.
	 * @param disco disco a apilar.
	 */
	public void poneDisco(JDisco disco) {
		discos.push(disco);
	}

	/**
	 * Quita el disco en la cima de la torre.
	 * @return el disco en la cima de la torre o null si no hay ningun disco
	 * en la torre.
	 */
	public JDisco quitaDisco() {
		return discos.pollFirst();
	}
	
	/**
	 * Retorna el disco que se encuentra en la cima de la torre (sin desapilarlo).
	 * @return el disco que se encuentra en la cima de la torre.
	 */
	public JDisco discoEnCima() {
		return discos.peekFirst();
	}

	/**
	 * Indica si se ha desapilado un disco de la torre en el ultimo movimiento.
	 * @return si se ha desapilado un disco de la torre en el ultimo movimiento.
	 */
	public boolean discoDesapilado() {
		return torre.numDiscos() < discos.size();
	}

	/**
	 * Indica si se ha apilado un disco de la torre en el ultimo movimiento.
	 * @return si se ha apilado un disco de la torre en el ultimo movimiento.
	 */
	public boolean discoApilado() {
		return torre.numDiscos() > discos.size();
	}

	/**
	 * Retorna si la torre esta vacia.
	 * @return si la torre esta vacia.
	 */
	public boolean estaVacia() {
		return discos.isEmpty();
	}
	
	/**
	 * Comprueba si el estado de la torre coincide con el de la torre del
	 * modelo con la que se encuentra asociada.
	 */
	public void comprueba() {
		if (torre.numDiscos() != discos.size()) {
			PantallaTorres.errorFatal("Error en numero de discos en torre " +
					torre.posicion() + "\n" + 
					"Discos en GUI:" + discos.size() +
					" Discos en modelo:" + torre.numDiscos(), "");
			return;
		}
		
		if (torre.discoEnCima() == null && discos.peekFirst() == null) {
			return; // no error
		}
		
		if ((torre.discoEnCima() != null && discos.peekFirst() == null) ||
				(torre.discoEnCima() == null && discos.peekFirst() != null)	||
				(torre.discoEnCima().longitud() != discos.peekFirst().longitud())) {
			PantallaTorres.errorFatal("Error en en la cima de la torre " +
					torre.posicion() + "\n" + 
					"Disco en la cima en GUI:" + discos.peekFirst().longitud() +
					" Disco en la cima en modelo:" + torre.discoEnCima().longitud(), "");
			return;
		}

		// comprueba que los discos de las dos torres son los mismos
		/*
		for (int i = 0; i < discos.size(); i++) {
			if (discos.get(i).tamanho() != torre.discoEnPos(i).longitud()) {
				PantallaTorres.errorFatal("Error en disco tamanho " +
						torre.discoEnPos(i).longitud() +  " en torre " +
						torre.posicion());
				return;
			}
		}
		*/
	}

	/**
	 * Retorna el punto justo por encima de la barra que representa la torre.
	 * @return el punto justo por encima de la barra que representa la torre.
	 */
	public Point puntoSuperior() {
		return new Point(centro().x,
				PantallaTorres.SUELO_Y - altoY() - JDisco.ALTO_Y * 2);
	}

	/**
	 * Retorna el punto en el que se deberia situar el disco que pasara a ocupar
	 * la cima de la torre. 
	 * @return el punto en el que se deberia situar el disco que pasara a ocupar
	 * la cima de la torre.
	 */
	public Point puntoCima() {
		return new Point(centro().x,
				PantallaTorres.SUELO_Y - discos.size() * JDisco.ALTO_Y -
				JDisco.ALTO_Y / 2);
	}

	@Override
	public String toString() {
		return "(" + torre.posicion() + ", " + discos + ")";
	}
	
	
}
