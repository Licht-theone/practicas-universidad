package pract08.marcianitos;

import static pract08.JuegoMarcianitos.PATH_ICONOS;

import java.awt.Image;
import javax.swing.ImageIcon;

import pract08.proyectiles.ProyectilGuiado;

/**
 * Marcianito master. Tipo mas poderoso de marcianito, dispara bombas
 * explosivas.
 *    
 * @author Metodos de Programacion (UC) y Aaron Alegria
 * @version abr-2024
 */
public class MarcianitoMaster extends MarcianitoMultiplesVidas{
	// imagenes
	private static final Image IMG_MARCIANO1 =
			new ImageIcon(PATH_ICONOS + "invasor3_1.png").getImage();	
	private static final Image IMG_MARCIANO2 =
			new ImageIcon(PATH_ICONOS + "invasor3_2.png").getImage();

	// constructor
	public MarcianitoMaster(int numVidas) {
		super(IMG_MARCIANO1, IMG_MARCIANO2, numVidas);
	}
	// metodo lanzaProyectiles()
	// Crea un proyectil guiado (clase ProyectilGuiado).

	@Override
	protected void lanzaProyectiles() {
		ProyectilGuiado p = new ProyectilGuiado(0, 5);
		escena().incluyeObj(p, centro().x, centro().y);
	}

}
