package examen.modelo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
/**
 * Clase que crea el juego.
 * 
 * @author  Aaron Alegria
 * @version may-24
 */

@SuppressWarnings("serial")
public class Juego {
	/**
	 * excepcion para cuando el personaje no existe.
	 */
	public class PersonajeNoExiste extends RuntimeException {

	}

	/**
	 * excepcion si la clase del personaje no es valida para realizar una instruccion.
	 */
	public class TipoNoValido extends RuntimeException {

	}

	/**
	 * excepcion para cuando un personaje ya esta eliminado.
	 */
	public class YaMuerto extends RuntimeException {

	}

	private ArrayList<Personaje> personajes;
	private static final int VIDA_GUERRERO = 55;
	private static final int VIDA_MAGO = 45;

	/**
	 * constructor del juego.
	 * @param nomFich nombre del fichero con los personajes
	 * @throws FileNotFoundException si el fichero no se encuentra
	 */
	public Juego(String nomFich) throws FileNotFoundException {
		personajes = new ArrayList<Personaje>();
		try (Scanner in = new Scanner(new FileReader(nomFich))) {
			in.useLocale(Locale.ENGLISH);
			String nombre;
			String tipo;
			int fuerza;
			while (in.hasNext()) {
				nombre = in.next();
				tipo = in.next();
				if (tipo.equals("GUERRERO")) {
					in.next();
					fuerza = in.nextInt();
					PersonajeGuerrero p = new PersonajeGuerrero(nombre, VIDA_GUERRERO, fuerza);
					personajes.add(p);
					while (in.hasNextDouble()) {
						in.nextDouble();
					}
				} else {
					PersonajeMago p = new PersonajeMago(nombre, VIDA_MAGO);
					personajes.add(p);
				}	
			}
		}
	}

	/**
	 * metodo que realiza un ataque de un personaje a otro.
	 * @param atacante nombre del atacante
	 * @param atacado nombre del atacado
	 * @throws PersonajeNoExiste si no existe alguno de ellos
	 * @throws YaMuerto si alguno ya esta eliminado
	 */
	public void realizaAtaque(String atacante, String atacado) throws PersonajeNoExiste, YaMuerto {
		Personaje pAtacante = buscaPersonaje(atacante);
		Personaje pAtacado = buscaPersonaje(atacado);
		if (pAtacado == null || pAtacante == null) {
			throw new PersonajeNoExiste();
		}
		if (pAtacado.eliminador() != null || pAtacante.eliminador() != null) {
			throw new YaMuerto();
		}
		pAtacado.recibeAtaque(pAtacante.ataca(), pAtacante);
	}

	/**
	 * metodo que recarga la energia de los magos.
	 * @param nombre nombre del personaje
	 * @param energia puntos a recargar
	 * @throws PersonajeNoExiste si no existe el personaje
	 * @throws YaMuerto si esta eliminado
	 * @throws TipoNoValido si no es mago
	 */
	public void recargaEnergiaMago(String nombre, int energia) 
			throws PersonajeNoExiste, YaMuerto, TipoNoValido {
		Personaje p = buscaPersonaje(nombre);
		if (p == null) {
			throw new PersonajeNoExiste();
		}
		if (p.eliminador() != null) {
			throw new YaMuerto();
		}
		if (!(p instanceof PersonajeMago)) {
			throw new TipoNoValido();
		}
		((PersonajeMago) p).recargaEnergia(energia);
	}

	/**
	 * metodo que busca un personaje dentro del arraylist por nombre.
	 * @param nombre nombre del personaje a buscar
	 * @return el personaje o null si no existe
	 */
	public Personaje buscaPersonaje(String nombre) {
		for (Personaje p: personajes) {
			if (p.nombre().equals(nombre)) {
				return p;
			}
		}
		return null;
	}
}
