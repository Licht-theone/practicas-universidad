package pract02.gui;

import pract02.modelo.Circulo;
import java.awt.Color;



/**
 * Programa principal que permite dibujar y mover circulos
 * en una ventana.
 * 
 * @author Metodos de Programacion (UC) y Aaron Alegria
 * @version feb-2021
 */
public class GUIMueveCirculos {
	// dimensiones de la ventana
	private static final int ANCHO_VENTANA_PIXELS = 400;
	private static final int ALTO_VENTANA_PIXELS = 500;

	// intervalo de refresco (en segundos)
	private static final double INTERVALO_REFRESCO = 0.05;

	/**
	 * Programa principal.
	 * 
	 * @param args argumentos del main (no usados).
	 */
	public static void main(String[] args) {
		// crea la ventana de dibujo.
		VentanaCirculos ventanaCirculos =
				new VentanaCirculos(ANCHO_VENTANA_PIXELS, ALTO_VENTANA_PIXELS);

		// crea un circulo y le anhade a la ventana.
		Circulo circulo1 = new Circulo(20, 50, 50, Color.RED);
		Circulo circulo2 = new Circulo(20, 100, 50, Color.BLUE);
		Circulo circulo3 = new Circulo(20, 50, 100, Color.GREEN);
		Circulo circulo4 = new Circulo(20, 100, 100, Color.CYAN);
		ventanaCirculos.anhadeCirculo(circulo1);
		ventanaCirculos.anhadeCirculo(circulo2);
		ventanaCirculos.anhadeCirculo(circulo3);
		ventanaCirculos.anhadeCirculo(circulo4);
		circulo1.asignaVelocidad(100, 100);
		circulo2.asignaVelocidad(50, 75);
		circulo3.asignaVelocidad(100, 75);
		circulo4.asignaVelocidad(75, 100);
		// dibuja el contenido de la ventana

		while (true) {
			ventanaCirculos.redibuja(INTERVALO_REFRESCO);
		}
	}
}
