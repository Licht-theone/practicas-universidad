package mc17;

import java.util.ArrayList;
@SuppressWarnings("serial")
public class Biblio {
	public class LibroYaPrestado extends RuntimeException{}
	public class LibroNoPrestado extends RuntimeException{}
	public class UsuarioYaExistente extends RuntimeException{}
	public class LibroNoExiste extends RuntimeException{}
	public class UsuarioNoExiste extends RuntimeException{}
	private ArrayList<Usuario> registro = new ArrayList<Usuario>();
	private ArrayList<Libro> libros = new ArrayList<Libro>();

	public Libro buscaLibro(String titulo) {
		for (Libro l: libros) {
			if (l.getTitulo().equals(titulo)) {
				return l;
			}
		}
		return null;
	}

	public Usuario buscaUsuario(String dni) {
		for(Usuario u: registro) {
			if (u.getDni().equals(dni)) {
				return u;
			}
		}
		return null;
	}

	public void anhadeUsuario(Usuario u) throws UsuarioYaExistente{
		if (buscaUsuario(u.getDni()) != null) {
			throw new UsuarioYaExistente();
		}
		registro.add(u);
	}

	public void prestaLibro(String titulo, String dni) throws LibroYaPrestado, UsuarioNoExiste, LibroNoExiste{
		Libro l = buscaLibro(titulo);
		if (l == null) {
			throw new LibroNoExiste();
		}
		Usuario u = buscaUsuario(dni);
		if (u == null) {
			throw new UsuarioNoExiste();
		}
		if (u instanceof Socio) {
			if (!((Socio)u).prestaLibro(l)) {
				throw new LibroYaPrestado();
			}
		}
		else {
			if (((NoSocio)u).prestaLibro(l)) {
				throw new LibroYaPrestado();
			}
		}
	}

	public void devuelveLibro(String titulo, String dni) throws LibroNoPrestado, UsuarioNoExiste, LibroNoExiste{
		Libro l = buscaLibro(titulo);
		if (l == null) {
			throw new LibroNoExiste();
		}
		Usuario u = buscaUsuario(dni);
		if (u == null) {
			throw new UsuarioNoExiste();
		}
		if (u instanceof Socio) {
			if(!((Socio)u).prestaLibro(l)) {
				throw new LibroNoPrestado();
			}
		}
		else {
			if (((NoSocio)u).devuelveLibro(l)) {
				throw new LibroNoPrestado();
			}
		}
	}

	public double consultaPago(String dni) throws UsuarioNoExiste{
		Usuario u = buscaUsuario(dni);
		if (u == null) {
			throw new UsuarioNoExiste();
		}
		if (u instanceof Socio) {
			return ((Socio)u).precioMes();
		}
		else {
			return ((NoSocio)u).precioMes();
		}
	}
}
