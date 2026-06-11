package pract13.marcianitos;

import static pract13.JuegoMarcianitos.PATH_ICONOS;

import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

import pract13.proyectiles.Proyectil;

/**
 * Marcianito jefe. Dispara dos proyectiles con trayectoria oblicua.
 *    
 * @author Metodos de Programacion (UC)
 * @version abr-2024
 */
public class MarcianitoJefe extends MarcianitoMultiplesVidas {
	// imagenes
	private static final Image IMG_MARCIANO1 =
			new ImageIcon(PATH_ICONOS + "invasor2_1.png").getImage();	
	private static final Image IMG_MARCIANO2 =
			new ImageIcon(PATH_ICONOS + "invasor2_2.png").getImage();

	private static final float VEL_X_BOMBA = 1;
	private static final float VEL_Y_BOMBA = 4;
	private static final int NUM_VIDAS = 2;
	
	private static final float Y_FACTOR_POS_PROYECTIL = 1.2F;

	/**
	 * Construye un marcianito jefe.
	 */
	public MarcianitoJefe() {
		super(IMG_MARCIANO1, IMG_MARCIANO2, NUM_VIDAS);
	}

	@Override
	protected void lanzaProyectiles() {
		// obtiene el centro del marcianito
		Point centro = centro();

		// crea dos proyectiles con trayectoria oblicua
		escena().incluyeObj(new Proyectil(-VEL_X_BOMBA, VEL_Y_BOMBA), 
				centro.x, (int) (centro.y + (altoY() / 2) * Y_FACTOR_POS_PROYECTIL));
		escena().incluyeObj(new Proyectil(VEL_X_BOMBA, VEL_Y_BOMBA), 
				centro.x, (int) (centro.y + (altoY() / 2) * Y_FACTOR_POS_PROYECTIL));
	}

}
