package pract11.modelo;

/**
 * Vehiculo en alquiler.
 * 
 * @author Estructuras de Datos (UC)
 * @version nov-2021
 */
public class Vehiculo {
	/**
	 * Tipo de combustible de los vehiculos.
	 */
	public static enum TipoCombustible { GASOLINA, DIESEL, ELECTRICO, HIBRIDO }
	
	// datos del vehiculo
	private final String matricula;
	private final TipoCombustible tipoCombustible;
	private final int numPlazas;
	
	// oficina en la que se encuentra estacionado, o null si el vehiculo
	// se encuentra alquilado
	private Oficina oficina = null;
	

	/**
	 * Construye un vehiculo con los datos indicados.
	 * @param matricula matricula del vehiculo
	 * @param tipoCombustible tipo de combustible del vehiculo
	 * @param numPlazas numero de plazas del vehiculo
	 */
	public Vehiculo(String matricula, TipoCombustible tipoCombustible, int numPlazas) {
		this.matricula = matricula;
		this.tipoCombustible = tipoCombustible;
		this.numPlazas = numPlazas;
	}
	
	/**
	 * El vehiculo pasa a estar alquilado.
	 */
	public void alquila() {
		oficina = null;
	}
	
	/**
	 * Retorna si esta alquilado o no.
	 * @return verdadero si esta alquilado
	 */
	public boolean estaAlquilado() {
		return oficina == null;
	}
	
	/**
	 * Oficina en la que se encuentra estacionado el vehiculo, o null si el vehiculo
	 * se encuentra alquilado.
	 * @return oficina en la que se encuentra estacionado el vehiculo
	 */
	public Oficina oficina() {
		return oficina;
	}
	
	/**
	 * Devuelve el vehiculo a la oficina.
	 * @param oficina oficina en la que se devuelve el vehiculo
	 */
	public void devuelveAOficina(Oficina oficina) {
		this.oficina = oficina;
	}

	/**
	 * Retorna la matricula del vehiculo.
	 * @return matricula del vehiculo
	 */
	public String matricula() {
		return matricula;
	}

	/**
	 * Retorna el tipo de combustible del vehiculo.
	 * @return tipo de combustible del vehiculo
	 */
	public TipoCombustible tipoCombustible() {
		return tipoCombustible;
	}
	
	/**
	 * Retorna el numero de plazas del vehiculo.
	 * @return numero de plazas del vehiculo
	 */
	public int numPlazas() {
		return numPlazas;
	}

	@Override
	public String toString() {
		return "(matricula=" + matricula + ", tipoCombustible=" + tipoCombustible +
				", numPlazas=" + numPlazas + ", oficina=" + oficina + ")";
	}
	
	
	
}
