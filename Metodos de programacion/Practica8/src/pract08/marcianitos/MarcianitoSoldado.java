package pract08.marcianitos;

import static pract08.JuegoMarcianitos.PATH_ICONOS;

import java.awt.Image;
import javax.swing.ImageIcon;

import pract08.proyectiles.Proyectil;

/**
 * Marcianito soldado. Tipo menos poderoso de marcianitos. Dispara un proyectil
 * basico que cae en vertical.
 *    
 * @author Metodos de Programacion (UC) y Aaron Alegria
 * @version abr-2024
 */
public class MarcianitoSoldado extends MarcianitoMultiplesVidas{ 
	// imagenes
	private static final Image IMG_MARCIANO1 =
			new ImageIcon(PATH_ICONOS + "invasor1_1.png").getImage();	
	private static final Image IMG_MARCIANO2 =
			new ImageIcon(PATH_ICONOS + "invasor1_2.png").getImage();

	// constructor
	// Invoca al constructor de la superclase (Marcianito)
	public MarcianitoSoldado(int numVidas) {
		super(IMG_MARCIANO1, IMG_MARCIANO2, numVidas);
	}

	// Crea un proyectil (objeto de la clase Proyectil) que cae verticalmente.
	@Override
	protected void lanzaProyectiles() {
		Proyectil p = new Proyectil(0, 5);
		escena().incluyeObj(p, centro().x, centro().y);
	}
}
