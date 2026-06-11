package mc17;


public class Libro {
	private final String titulo;
	private boolean prestado;
	/**
	 * @param titulo
	 * @param prestado
	 */
	public Libro(String titulo) {
		this.titulo = titulo;
		prestado = false;
	}
	public boolean prestaLibro() {
		if (prestado) {
			return false;
		}
		prestado = true;
		return true;
	}
	public boolean devuelveLibro() {
		if (!prestado) {
			return false;
		}
		prestado = false;
		return true;
	}
	public String getTitulo() {
		return titulo;
	}
}
