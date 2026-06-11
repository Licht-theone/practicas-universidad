
public class Hotel {
	private final String nombre;
	private final double precio;
	private final String fecha;
	private final String hora;
	
	/**
	 * @param nombre
	 * @param precio
	 * @param fecha
	 * @param hora
	 */
	public Hotel(String nombre, double precio, String fecha, String hora) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.fecha = fecha;
		this.hora = hora;
	}
	public String getNombre() {
		return nombre;
	}
	public double getPrecio() {
		return precio;
	}
	public String getFecha() {
		return fecha;
	}
	public String getHora() {
		return hora;
	}
}
