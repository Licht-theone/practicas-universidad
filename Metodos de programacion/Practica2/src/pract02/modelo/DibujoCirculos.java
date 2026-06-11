package pract02.modelo;

import java.util.Arrays;

/**
 * Dibujo formado por varios circulos que se muestra en una ventana.
 * 
 * @author Metodos de Programacion (UC) y Aaron Alegria.
 * @version feb-2021
 */
public class DibujoCirculos {
	// ancho de la ventana en la que se mostrara el dibujo.
	private final int anchoVentanaPixels;

	// alto de la ventana en la que se mostrara el dibujo.
	private final int altoVentanaPixels;

	// maximo numero de circulos que puede contener el dibujo
	private static final int MAX_NUM_CIRCULOS = 20;

	// numero de circulos que contiene el dibujo
	private int numCirculos = 0;  // inicialmente vale 0

	// array con los circulos a dibujar. Tiene una capacidad maxima
	// de MAX_NUM_CIRCULOS, pero las posiciones ocupadas son las que
	// estan en el rango [0 .. numCirculos-1]
	private Circulo[] circulos = new Circulo[MAX_NUM_CIRCULOS];

	/**
	 * Construye un dibujo para mostrar en una ventana
	 * con las dimensiones indicadas.
	 * @param anchoVentanaPixels ancho de la ventana en pixels.
	 * @param altoVentanaPixels alto de la ventana en pixels.
	 */
	public DibujoCirculos(int anchoVentanaPixels, int altoVentanaPixels) {
		this.anchoVentanaPixels = anchoVentanaPixels;
		this.altoVentanaPixels = altoVentanaPixels;
	}

	/**
	 * Anhade un circulo al dibujo.
	 * @param circulo circulo a anhadir.
	 * 
	 * XXX por simplicidad no comprueba si se ha llenado el
	 * array (ya veremos a lo largo de la asignatura como se
	 * deben notificar los errores).
	 */
	public void anhadeCirculo(Circulo circulo) {
		circulos[numCirculos] = circulo;
		numCirculos++;
	}


	/**
	 * Retorna una copia de los circulos del dibujo.
	 * @return una copia de los circulos del dibujo.
	 */
	public Circulo[] circulos() {
		return Arrays.copyOf(circulos, numCirculos);
	}

	/**
	 * Mueve los circulos del dibujo.
	 * (Apartados 3 y 4)
	 * @param intervaloTiempo intervalo de tiempo en segundos durante el que se
	 * realiza el movimiento.
	 */
	public void mueveCirculos(double intervaloTiempo) {
		for (int i = 0; i < numCirculos; i++) {
			circulos[i].mueve(intervaloTiempo);
			if ((circulos[i].centroX() + circulos[i].radio()) >= anchoVentanaPixels || (circulos[i].centroX() - circulos[i].radio()) <= 0) {
				circulos[i].asignaVelocidad(-circulos[i].vX(), circulos[i].vY());
			}
			if ((circulos[i].centroY() + circulos[i].radio()) >= altoVentanaPixels || (circulos[i].centroY() - circulos[i].radio()) <= 0) {
				circulos[i].asignaVelocidad(circulos[i].vX(), -circulos[i].vY());
			}
		}
	}
}
