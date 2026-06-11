package mc20;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class Plataforma {
	public class MaxPeliculasDescargadas extends RuntimeException{}
	public class PeliculaNoDescargada extends RuntimeException{}
	public class NoExisteUsuario extends RuntimeException{}
	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	
	public void descargaPeli(String dni, Pelicula p) throws NoExisteUsuario, MaxPeliculasDescargadas{
		Usuario u = buscaUsu(dni);
		if (u == null) {
			throw new NoExisteUsuario();
		}
		if (!u.descargaPelicula(p)) {
			throw new MaxPeliculasDescargadas();
		}
	}
	
	public void finalizaDescarga (String dni, Pelicula p) throws NoExisteUsuario, PeliculaNoDescargada{
		Usuario u = buscaUsu(dni);
		if (u == null) {
			throw new NoExisteUsuario();
		}
		if (!u.finalizaDescarga(p)) {
			throw new PeliculaNoDescargada();
		}
	}
	
	public int cuotaUsu(String dni) throws NoExisteUsuario{
		Usuario u = buscaUsu(dni);
		if (u == null) {
			throw new NoExisteUsuario();
		}
		return u.precioMes();
	}
	
	private Usuario buscaUsu(String dni) {
		for (Usuario u: usuarios) {
			if (u.getDni().equals(dni)) {
				return u;
			}
		}
		return null;
	}
}
