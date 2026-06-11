package mc19;

public class Estacionamiento {
	private final String fecha;
	private final String hora;
	private final int min;
	private final Vehiculo estacionado;
	/**
	 * @param fecha
	 * @param hora
	 * @param estacionado
	 */
	public Estacionamiento(String fecha, String hora, Vehiculo estacionado, int min) {
		this.fecha = fecha;
		this.hora = hora;
		this.estacionado = estacionado;
		this.min = min;
	}
	public String getFecha() {
		return fecha;
	}
	public String getHora() {
		return hora;
	}
	public Vehiculo getEstacionado() {
		return estacionado;
	}
	public int minutos() {
		return min;
	}
}
