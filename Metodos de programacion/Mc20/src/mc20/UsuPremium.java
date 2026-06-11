package mc20;

public class UsuPremium extends Usuario{
	private static final int MAX_PELIS = 5;
	private static final int CUOTA = 10;
	
	public UsuPremium(String dni) {
		super(dni);
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
		return true;
	}

	@Override
	public int precioMes() {
		return CUOTA;
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
