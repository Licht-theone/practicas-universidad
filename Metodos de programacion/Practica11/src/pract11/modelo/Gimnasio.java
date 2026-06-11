package pract11.modelo;

import java.util.ArrayList;

import pract11.modelo.Actividad.ActividadMaxSocios;
import pract11.modelo.Socio.SocioMaxActividades;
import pract11.modelo.Socio.SocioYaInscrito;

/**
 * Gimnasio que ofrece actividades a sus socios.
 * @author Metodos de Programacion (UC) y Aaron Alegria
 * @version may-2023
 */
@SuppressWarnings("serial")
public class Gimnasio {
	//atributos
	private ArrayList<Actividad> actividades;
	private ArrayList<Socio> socios;

	//excepciones
	/**
	 * excepcion para cuando no existe el socio.
	 */
	public class NoExisteSocio extends RuntimeException {
	}
	
	/**
	 * excepcion para cuando no existe la actividad.
	 */
	public class NoExisteActividad extends RuntimeException {
	}
	
	/**
	 * Construye un gimnasio a partir de los datos de los socios y actividades
	 * escritos en un fichero.
	 * Simula la lectura del fichero creando algunas actividades y socios.
	 */
	public Gimnasio() {
		// Crea 12 actividades y las anhade la gimnasio
		// Nombres de las actividades: Actividad0 .. Actividad11
		// Precios de las actividades:       10.0 .. 21.0
		// Plazas de las actividades:          20 .. 9
		actividades = new ArrayList<Actividad>();
		socios = new ArrayList<Socio>();
		final int numActividades = 12;
		for (int i = 0; i < numActividades; i++) {
			final String nombre = "Actividad" + i;
			final int precio = 10 + i;
			final int plazas = 20 - i;
			// crea y registra una actividad con el nombre, precio y plazas
			// calculadas.
			Actividad a = new Actividad(nombre, precio, plazas);
			actividades.add(a);
		}

		// Crea 10 socios de tipo BASICO y los registra en el gimnasio.
		// DNIs de los socios: 10000000A .. 10000009A
		final int numSociosBasicos = 10;
		for (int i = 0; i < numSociosBasicos; i++) {
			final String dni = (10000000 + i) + "A";
			// crea y registra un socio basico con el DNI calculado.
			SocioBasico s = new SocioBasico(dni);
			socios.add(s);
		}

		// Crea 10 socios de tipo PLUS y los registra en el gimnasio.
		// DNIs de los socios:  20000000B .. 20000009B
		// Nombres de los socios:    Nom0 .. Nom9
		final int numSociosPlus = 10;
		for (int i = 0; i < numSociosPlus; i++) {
			final String dni = (20000000 + i) + "B";
			final String nombre = "Nom" + i;
			// crea y registra un socio plus con el DNI y nombre calculados.
			SocioPlus s = new SocioPlus(dni, nombre);
			socios.add(s);
		}
	}

	/**
	 * metodo que busca un socio.
	 * @param dni dni del socio a buscar
	 * @return el socio si le encuentra, null en caso contrario
	 */
	public Socio buscaSocio(String dni) {
		for (Socio s: socios) {
			if (s.dni().equals(dni)) {
				return s;
			}
		}
		return null;
	}
	
	/**
	 * metodo que busca una actividad.
	 * @param nombre nombre de la actividad
	 * @return la actividad o null en caso de no encontrarla
	 */
	public Actividad buscaActividad(String nombre) {
		for (Actividad a: actividades) {
			if (a.nombre().equals(nombre)) {
				return a;
			}
		}
		return null;
	}
	
	/**
	 * metodo que inscribe a un socio a una actividad.
	 * @param dni dni del socio
	 * @param nombreAct nombre de la actividad
	 * @throws SocioYaInscrito si ya esta inscrito
	 * @throws SocioMaxActividades si ya tiene el maximo de actividades
	 * @throws NoExisteSocio si no existe el socio
	 * @throws NoExisteActividad si no existe la actividad
	 * @throws ActividadMaxSocios si la actividad esta llena
	 */
	public void inscribeSocio(String dni, String nombreAct) 
			throws SocioYaInscrito, SocioMaxActividades, NoExisteSocio, 
			NoExisteActividad, ActividadMaxSocios {
		Socio s = buscaSocio(dni);
		if (s == null) {
			throw new NoExisteSocio();
		}
		Actividad a = buscaActividad(nombreAct);
		if (a == null) {
			throw new NoExisteActividad();
		}
		s.apuntaActividad(a);
	}
	
	/**
	 * consulta si un socio esta inscrito a una actividad.
	 * @param dni dni del socio
	 * @param nombreAct nombre de la actividad
	 * @return true si esta inscrito, false en caso contrario
	 * @throws NoExisteActividad si no existe la actividad
	 * @throws NoExisteSocio si no existe el socio
	 */
	public boolean consultaInscripcion(String dni, String nombreAct) 
			throws NoExisteActividad, NoExisteSocio {
		Socio s = buscaSocio(dni);
		if (s == null) {
			throw new NoExisteSocio();
		}
		Actividad a = buscaActividad(nombreAct);
		if (a == null) {
			throw new NoExisteActividad();
		}
		if (s.buscaActividad(nombreAct) == null) {
			return false;
		}
		return true;
	}
}
