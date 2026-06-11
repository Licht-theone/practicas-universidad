package pract12.modelo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import pract12.modelo.Actividad.ActividadMaxSocios;
import pract12.modelo.Socio.SocioMaxActividades;
import pract12.modelo.Socio.SocioYaInscrito;

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
	 * @throws FileNotFoundException si el archivo no existe
	 * 
	 */
	public Gimnasio() throws FileNotFoundException {
		// Crea 12 actividades y las anhade la gimnasio
		// Nombres de las actividades: Actividad0 .. Actividad11
		// Precios de las actividades:       10.0 .. 21.0
		// Plazas de las actividades:          20 .. 9
		// Crea 10 socios de tipo BASICO y los registra en el gimnasio.
		// DNIs de los socios: 10000000A .. 10000009A
		// Crea 10 socios de tipo PLUS y los registra en el gimnasio.
		// DNIs de los socios:  20000000B .. 20000009B
		// Nombres de los socios:    Nom0 .. Nom9
		actividades = new ArrayList<Actividad>();
		socios = new ArrayList<Socio>();
		try (Scanner in = new Scanner(new FileReader("datos_gimnasio.txt"))) {
			in.useLocale(Locale.ENGLISH);
			in.next();
			String nombre = in.next();
			double precio;
			int plazas;
			String dni;
			while (!nombre.equals("Socios")) {
				while (!in.hasNextDouble()) {
					nombre += " " + in.next();
				}
				precio = in.nextDouble();
				plazas = in.nextInt();
				Actividad a = new Actividad(nombre, precio, plazas);
				actividades.add(a);
				nombre = in.next();
			}
			while (in.hasNext()) {
				String tipo = in.next();
				dni = in.next();
				if (tipo.equals("BASICO")) {
					SocioBasico s = new SocioBasico(dni);
					socios.add(s);
				} else if (tipo.equals("PLUS")) {
					nombre = in.nextLine();
					SocioPlus s = new SocioPlus(dni, nombre);
					socios.add(s);
				} else {
					SocioInfantil s = new SocioInfantil(dni);
					socios.add(s);
				}
			}
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
		s.apuntaActividad(a, s);
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

	/**
	 * metodo que genera un fichero con todos los socios apuntados a una actividad y sus cuotas.
	 * @param nombre nombre de la actividad
	 * @throws NoExisteActividad si la actividad no existe
	 * @throws IOException en caso de no poder generar el fichero
	 */
	public void informeActividad(String nombre) throws NoExisteActividad, IOException {
		Actividad a = buscaActividad(nombre);
		if (a == null) {
			throw new NoExisteActividad();
		}
		String nombreFichero = nombre + ".txt";
		Socio[] soc = a.socios();
		try (PrintWriter out = new PrintWriter(new FileWriter(nombreFichero))) {
			out.printf("DNI         NOMBRE (solo socios plus)     Pago mensual\n");
			for (Socio s: soc) {
				String nomSoc = " ";
				if (s instanceof SocioPlus) {
					nomSoc = ((SocioPlus) s).nombre();
				}
				out.printf(Locale.ENGLISH, "%-10s %-26s %11.2f%n", 
						s.dni(), nomSoc, s.cuotaMensual());
			}
		}
	}
}
