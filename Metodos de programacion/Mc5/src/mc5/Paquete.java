package mc5;

public class Paquete {
	private final String id;
	private final String descripcion;
	private static int aut = 1;

	/**
	 * @param id
	 * @param descripcion
	 */
	public Paquete(String descripcion) {
		id = "PAQ" + aut;
		this.descripcion = descripcion;
		aut++;
	}

	public String getId() {
		return id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	@Override
	public String toString() {
		return "Paquete [id=" + id + ", descripcion=" + descripcion + "]";
	}

}
