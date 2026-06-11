package mc20;

public class Pelicula {
	private final String codigo;
	private final String titulo;
	/**
	 * @param codigo
	 * @param titulo
	 */
	public Pelicula(String codigo, String titulo) {
		super();
		this.codigo = codigo;
		this.titulo = titulo;
	}
	public String getCodigo() {
		return codigo;
	}
	public String getTitulo() {
		return titulo;
	}
}
