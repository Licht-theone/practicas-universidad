package c3_entrega_regalos;

/**
 * Socio de una asociacion de vecinos inscrito en el reparto de regalos.
 * 
 * @author  Metodos de Programacion (UC)
 * @version ene-23
 */
public class Socio {
	private final int numSocio;
	private Regalo regalo = null;
	
	/**
	 * Construye el socio.
	 * @param numSocio numero del socio.
	 */
	public Socio(int numSocio) {
		this.numSocio = numSocio;
	}

	/**
	 * Retorna el numero del socio.
	 * @return el numero del socio.
	 */
	public int numSocio() {
		return numSocio;
	}

	public boolean tieneRegalo() {
		if (regalo != null) {
			return true;
		}
		return false;
	}
	
	public Regalo regalo() {
		return regalo;
	}
	
	public void registraRegalo(Regalo r) {
		regalo = r;
	}

}
