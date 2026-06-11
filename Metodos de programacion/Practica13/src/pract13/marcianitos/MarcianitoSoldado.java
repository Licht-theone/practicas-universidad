package pract13.marcianitos;

import static pract13.JuegoMarcianitos.PATH_ICONOS;

import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

import pract13.proyectiles.Proyectil;

/**
 * Marcianito soldado. Tipo menos poderoso de marcianitos. Dispara un proyectil
 * basico que cae en vertical.
 *    
 * @author Metodos de Programacion (UC)
 * @version abr-2024
 */
public class MarcianitoSoldado extends Marcianito {
	// imagenes
	private static final Image IMG_MARCIANO1 =
			new ImageIcon(PATH_ICONOS + "invasor1_1.png").getImage();	
	private static final Image IMG_MARCIANO2 =
			new ImageIcon(PATH_ICONOS + "invasor1_2.png").getImage();

	private static final float VEL_Y_BOMBA = 4;	
	private static final float Y_FACTOR_POS_PROYECTIL = 1.2F;

	/**
	 * Construye el marcianito.
	 */
	public MarcianitoSoldado() {
		super(IMG_MARCIANO1, IMG_MARCIANO2);
	}

	@Override
	protected void lanzaProyectiles() {
		// obtiene el centro del marcianito
		Point centro = centro();

		// crea un proyectil por debajo del borde inferior del marcianito
		escena().incluyeObj(new Proyectil(0, VEL_Y_BOMBA),
				centro.x, (int) (centro.y + (altoY() / 2) * Y_FACTOR_POS_PROYECTIL));
	}

}
