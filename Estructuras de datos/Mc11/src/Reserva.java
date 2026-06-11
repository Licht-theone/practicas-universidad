

/**
 * Representa una reserva que un cliente realiza sobre un 
 * determinada producto. SE pueden reservas un numero determinado
 * de unidades
 * 
 * @author Estructuras de Datos (UC)
 * @version nov-2018
 *
 */
public class Reserva {
	
	private final int unidades;
	private final Cliente cliente;

	/**
	 * Constructor de la reserva.
	 * @param unidades unidades reservadas.
	 * @param cliente cliente que realiza la reserva.
	 */
	public Reserva(int unidades, Cliente cliente) {
		this.unidades = unidades;
		this.cliente = cliente;
	}
	
	/**
	 * Retorna el numero de unidades reservadas.
	 * @return numero de unidades reservadas.
	 */
	public int getUnidades() {
		return unidades;
	}

	/**
	 * Retorna el cliente que ha realizado la reserva.
	 * @return cliente que ha realizado la reserva.
	 */
	public Cliente getCliente() {
		return cliente;
	}
}
