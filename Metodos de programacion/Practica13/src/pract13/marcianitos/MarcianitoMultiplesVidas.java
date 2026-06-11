package pract13.marcianitos;

import java.awt.Image;

/**
 * Marcianito con multiples vidas.
 *    
 * @author Metodos de Programacion (UC)
 * @version oct-2022
 */
public abstract class MarcianitoMultiplesVidas extends Marcianito {
	private int vidasRestantes;

	/**
	 * Construye un marcianito.
	 * El marcianito va cambiando su imagen entre las dos imagenes indicadas. 
	 * @param imagen1 primera imagen de la animacion del marcianito.
	 * @param imagen2 segunda imagen de la animacion del marcianito.
	 * @param vidas vidas del marcianito.
	 */
	public MarcianitoMultiplesVidas(Image imagen1, Image imagen2, int vidas) {
		super(imagen1, imagen2);
		this.vidasRestantes = vidas;
	}

	@Override
	protected void alcanzadoPorRayoLaser() {
		vidasRestantes--;
		if (vidasRestantes == 0) {
			super.alcanzadoPorRayoLaser();
		}
	}
}
