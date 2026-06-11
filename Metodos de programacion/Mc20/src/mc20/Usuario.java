package mc20;

import java.util.ArrayList;

public abstract class Usuario {
	protected final String dni;
	protected ArrayList<Pelicula> peliculasDescargadas;
	/**
	 * @param dni
	 */
	public Usuario(String dni) {
		this.dni = dni;
		peliculasDescargadas = new ArrayList<Pelicula>();
	}
	public String getDni() {
		return dni;
	}
	public abstract boolean descargaPelicula(Pelicula p);
	protected Pelicula buscaPelicula(String cod) {
		for (Pelicula p: peliculasDescargadas) {
			if (p.getCodigo().equals(cod)) {
				return p;
			}
		}
		return null;
	}
	public abstract int precioMes();
	public abstract boolean finalizaDescarga(Pelicula p);
}
