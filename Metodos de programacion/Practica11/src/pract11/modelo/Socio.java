package pract11.modelo;

import java.util.ArrayList;

import pract11.modelo.Actividad.ActividadMaxSocios;

/**
 * Superclase de socios de un gimnasio.
 * @author Aaron Alegria
 * @version may-2023
 */
@SuppressWarnings("serial")
public abstract class Socio {
	
	/**
	 * excepcio para si un socio esta inscrito.
	 */
	public class SocioYaInscrito extends RuntimeException {
	}
	
	/**
	 * excepcion para si el socio tiene el maximo de actividades.
	 */
	public class SocioMaxActividades extends RuntimeException {
	}
	
	private ArrayList<Actividad> actividades;
	private final String dni;
	
	/**
	 * constructor.
	 * @param dni dni del socio
	 */
	public Socio(String dni) {
		this.dni = dni;
		actividades = new ArrayList<Actividad>();
	}
	
	/**
	 * observador del dni.
	 * @return el dni
	 */
	public String dni() {
		return dni;
	}
	
	/**
	 * metodo que apunta a un socio a una actividad.
	 * @param a actividad a la que apuntar
	 * @throws SocioYaInscrito si ya esta inscrito
	 * @throws SocioMaxActividades si esta apuntado al maximo de actividades
	 * @throws ActividadMaxSocios si la actividad esta llena
	 */
	public abstract void apuntaActividad(Actividad a) 
			throws SocioYaInscrito, SocioMaxActividades, ActividadMaxSocios;
	
	/**
	 * metodo que busca una actividad.
	 * @param nombre nombre de la actividad
	 * @return la actividad o null
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
	 * metodo auxiliar para que el arraylist sea private y el checkstyle no salte.
	 * @param a actividad
	 */
	protected void anhadeActividad(Actividad a) {
		actividades.add(a);
	}
	
	/**
	 * metodo auxiliar para el sumatorio de precios para los socios plus.
	 * @return sumatorio de precios
	 */
	protected double precioTotalSociosPlus() {
		int sumatorio = 0;
		for (Actividad a: actividades) {
			sumatorio += a.precio();
		}
		return sumatorio;
	}
	
	/**
	 * metodo que calcula la cuota.
	 * @return la cuota
	 */
	public abstract double cuotaMensual();
	
	/**
	 * metodo que retorna el numero de actividades en las que esta apuntado un socio.
	 * @return numactividades
	 */
	public int numActividades() {
		return actividades.size();
	}
	
}
