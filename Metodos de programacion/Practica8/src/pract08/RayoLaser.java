package pract08;

import static pract08.JuegoMarcianitos.PATH_ICONOS;

import j2d.JObjeto;
import j2d.JObjetoIcono;

/**
 * Rayo laser disparado por el canhon laser.
 *    
 * @author Metodos de Programacion (UC)
 * @version oct-2022
 */
public class RayoLaser extends JObjetoIcono {
	private static final String FICH_RAYO = PATH_ICONOS + "rayo_laser.png";
	private static final int VEL_Y = 10;
	
	/**
	 * Construye un rayo laser.
	 */
	public RayoLaser() {
		super(null, FICH_RAYO);
		asignaVel(0, -VEL_Y);
	}
	
	@Override
	public void colision(JObjeto otroObj, boolean causante) {
		if (causante) {
			colisionador().desactiva();
			escena().eliminaObj(this);
		}	
	}

}
