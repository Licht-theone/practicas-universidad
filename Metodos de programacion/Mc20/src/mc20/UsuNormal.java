package mc20;

public class UsuNormal extends Usuario{
	private static final int MAX_PELIS = 3;
	private static final int EUROS_PELI = 1;
	private int contadorPelis;
	/**
	 * @param dni
	 * @param contadorPelis
	 */
	public UsuNormal(String dni) {
		super(dni);
		contadorPelis = 0;
	}
	@Override
	public boolean descargaPelicula(Pelicula p) {
		if (buscaPelicula(p.getCodigo()) != null) {
			return false;
		}
		if (peliculasDescargadas.size() >= MAX_PELIS) {
			return false;
		}
		peliculasDescargadas.add(p);
		contadorPelis++;
		return true;
	}
	@Override
	public int precioMes() {
		return contadorPelis * EUROS_PELI;
	}
	@Override
	public boolean finalizaDescarga(Pelicula p) {
		if (buscaPelicula(p.getCodigo()) == null) {
			return false;
		}
		peliculasDescargadas.remove(p);
		return true;
	}
	
}
