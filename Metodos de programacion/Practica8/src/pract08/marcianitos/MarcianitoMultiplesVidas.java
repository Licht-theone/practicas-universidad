package pract08.marcianitos;

import java.awt.Image;


import javax.swing.ImageIcon;

import j2d.Juego;
import pract08.Pantalla;

/**
 * Marcianito con multiples vidas.
 *    
 * @author Metodos de Programacion (UC) y Aaron Alegria
 * @version abr-2024
 */
@SuppressWarnings("unused")
public abstract class MarcianitoMultiplesVidas extends Marcianito{
	private int vidasRestantes;

	// constructor
	// Recibe como parametros las imagenes y el numero de vidas
	public MarcianitoMultiplesVidas(Image im1, Image im2, int numVidas) {
		super(im1, im2);
		vidasRestantes = numVidas;
	}

	protected void alcanzadoPorRayoLaser() {
		vidasRestantes--;
		if (vidasRestantes <= 0) {
			super.alcanzadoPorRayoLaser();
		}
	}
}
