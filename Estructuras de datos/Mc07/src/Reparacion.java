
public class Reparacion {
	
	private final String descripcion;
	private final String direccion;
	
	
	public Reparacion(String descripcion, String direccion) {
		super();
		this.descripcion = descripcion;
		this.direccion = direccion;
	}
	
	public String descripcion() {
		return descripcion;
	}
	public String direccion() {
		return direccion;
	}

	@Override
	public String toString() {
		return "[descripcion=" + descripcion + ", direccion=" + direccion + "]";
	}
	
	

}
