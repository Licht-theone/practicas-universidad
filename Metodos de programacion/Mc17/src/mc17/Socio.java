package mc17;

public class Socio extends Usuario{
	private final String nombre;
	/**
	 * @param dni
	 * @param nombre
	 */
	public Socio(String dni, String nombre) {
		super(dni);
		this.nombre = nombre;
	}
	public String getNombre() {
		return nombre;
	}
	
	public int precioMes() {
		return PRECIO_SOCIO;
	}
	
	public boolean prestaLibro(Libro l) {
		if (!l.prestaLibro()) {
			return false;
		}
		return true;
	}
	
	public boolean devuelveLibro(Libro l) {
		if (!l.devuelveLibro()) {
			return false;
		}
		return true;
	}
}
