package pract11.modelo;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Oficina de una empresa de alquiler de vehiculos.
 * 
 * @author Estructuras de Datos (UC) y Aaron Alegria
 * @version nov-2019
 */
public class Oficina {
	// nombre de la oficina
	private final String nombre;
	private Queue<Vehiculo> vehiculosDisponibles = new LinkedList<Vehiculo>();
	
	/**
	 * Construye una oficina con el nombre indicado.
	 * @param nombre nombre de la oficina
	 */
	public Oficina(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Anhade un vehiculo disponible para alquiler a la oficina.
	 * @param v nuevo vehiculo disponible para alquiler
	 */
	public void anhadeVehiculoDisponible(Vehiculo v) {
		vehiculosDisponibles.offer(v);
		v.devuelveAOficina(this);
	}
	
	/**
	 * Busca un vehiculo con las caracteristicas indicadas y le elimina de la lista
	 * de vehiculos disponibles para alquiler en la oficina.
	 * @param numPlazas numero de plazas del vehiculo deseado
	 * @param tipoCombustible tipo de combustible del vehiculo deseado
	 * @return el vehiculo que lleva mas tiempo disponible con las caracteristicas indicadas
	 * o null si no existe ningun vehiculo con esas caracterisiticas
	 */
	public Vehiculo alquila(int numPlazas, Vehiculo.TipoCombustible tipoCombustible) {
		Vehiculo aux;
		for (Vehiculo v: vehiculosDisponibles) {
			if (v.numPlazas() == numPlazas && v.tipoCombustible() == tipoCombustible) {
				aux = v;
				vehiculosDisponibles.remove(v);
				aux.alquila();
				return aux;
			}
		}
		return null;
	}
	
	/**
	 * Retorna el nombre de la oficina.
	 * @return el nombre de la oficina
	 */
	public String nombre() {
		return nombre;
	}
	
	/**
	 * Retorna el numero de vehiculos disponibles para alquiler en la oficina.
	 * @return numero de vehiculos disponibles para alquiler en la oficina
	 */
	public int numVehiculosDisponibles() {
		return vehiculosDisponibles.size();
	}

	@Override
	public String toString() {
		return "(nombre=" + nombre + ")";
	}
	
}
