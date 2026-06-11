package pract11.modelo;

import java.util.*;

/**
 * Gestion de una empresa de alquiler de vehiculos.
 * 
 * @author Estructuras de Datos (UC) y Aaron Alegria
 * @version nov-2021
 */
public class EmpresaAlquiler {
	private Map<String, Vehiculo> vehiculos = new HashMap<String, Vehiculo>();
	private SortedMap<String, Oficina> oficinas = new TreeMap<String, Oficina>();
	
	/**
	 * Lanzada si se trata de anhadir una oficina ya existente.
	 */
	@SuppressWarnings("serial")
	public static class OficinaYaExistente extends RuntimeException {
	}
	
	/**
	 * Lanzada si se trata de anhadir un vehiculo ya existente.
	 */
	@SuppressWarnings("serial")
	public static class VehiculoYaExistente extends RuntimeException {
	}
	
	/**
	 * Lanzada si no existe ninguna oficina con el nombre indicado.
	 */
	@SuppressWarnings("serial")
	public static class NombreOficinaIncorrecto extends RuntimeException {
	}
	
	/**
	 * Lanzada si no existe ningun vehiculo con la matricula indicada.
	 */
	@SuppressWarnings("serial")
	public static class MatriculaVehiculoIncorrecta extends RuntimeException {
	}
	
	/**
	 * Lanzada si se trata de devolver un vehiculo que no se encuentra alquilado.
	 */
	@SuppressWarnings("serial")
	public static class VehiculoNoAlquilado extends RuntimeException {
	}
	
	/**
	 * Anhade la oficina a la empresa.
	 * @param oficina oficina a anhadir.
	 * @throws OficinaYaExistente si ya existe una oficina con 
	 * el mismo nombre.
	 */
	//log(n)
	public void anhadeOficina(Oficina oficina) throws OficinaYaExistente {
		if (oficinas.containsKey(oficina.nombre())) {
			throw new OficinaYaExistente();
		}
		oficinas.put(oficina.nombre(), oficina);
	}

	/**
	 * Anhade el vehiculo a la empresa y le pone como disponible en la
	 * oficina indicada.
	 * @param vehiculo vehiculo a anhadir.
	 * @param nombreOficina nombre de la oficina en la que el vehiculo esta disponible.
	 * @throws VehiculoYaExistente si ya existe otro vehiculo con la misma
	 * matricula.
	 * @throws NombreOficinaIncorrecto si no existe ninguna oficina con el
	 * nombre indicado.
	 */
	//log(n)
	public void anhadeVehiculo(Vehiculo vehiculo,
			String nombreOficina) throws VehiculoYaExistente, NombreOficinaIncorrecto {
		if (vehiculos.containsKey(vehiculo.matricula())) {
			throw new VehiculoYaExistente();
		}
		if (!oficinas.containsKey(nombreOficina)) {
			throw new NombreOficinaIncorrecto();
		}
		vehiculos.put(vehiculo.matricula(), vehiculo);
		oficinas.get(nombreOficina).anhadeVehiculoDisponible(vehiculo);
	}
	
	/**
	 * Alquila en la oficina indicada un vehiculo con las caracteristicas que se
	 * pasan como parametros.
	 * @param nombreOficina nombre de la oficina en la que se desea alquilar el vehiculo
	 * @param numPlazas numero de plazas del vehiculo deseado
	 * @param tipoCombustible tipo de combustible del vehiculo deseado
	 * @return el vehiculo que lleva mas tiempo disponible en la oficina con las
	 * caracteristicas indicadas o null si no existe ningun vehiculo con esas
	 * caracterisiticas
	 * @throws NombreOficinaIncorrecto si el nombre de la oficina no corresponde
	 * con ninguna de las oficinas existentes en la empresa.
	 */
	//log(n)
	public Vehiculo alquilaVehiculo(String nombreOficina,
			int numPlazas,
			Vehiculo.TipoCombustible tipoCombustible) throws NombreOficinaIncorrecto {
		if (!oficinas.containsKey(nombreOficina)) {
			throw new NombreOficinaIncorrecto();
		}
		return oficinas.get(nombreOficina).alquila(numPlazas, tipoCombustible);
	}
	
	/**
	 * Devuelve el vehiculo a la oficina indicada.
	 * @param nombreOficina nombre de la oficina a la que se devuelve el vehiculo
	 * @param matricula matricula del vehiculo que se desea devolver
	 * @throws NombreOficinaIncorrecto si el nombre de la oficina no corresponde
	 * con ninguna de las oficinas existentes en la empresa.
	 * @throws MatriculaVehiculoIncorrecta si la matricula no corresponde con
	 * ninguna de las matriculas de los vehiculos de la empresa.
	 * @throws VehiculoNoAlquilado si el vehiculo no se encuentra alquilado en
	 * este momento.
	 */
	//log(n)
	public void devuelveVehiculo(String nombreOficina,
			String matricula) throws NombreOficinaIncorrecto, MatriculaVehiculoIncorrecta,
			VehiculoNoAlquilado {
		if (!oficinas.containsKey(nombreOficina)) {
			throw new NombreOficinaIncorrecto();
		}
		if (!vehiculos.containsKey(matricula)) {
			throw new MatriculaVehiculoIncorrecta();
		}
		if (!vehiculos.get(matricula).estaAlquilado()) {
			throw new VehiculoNoAlquilado();
		}
		oficinas.get(nombreOficina).anhadeVehiculoDisponible(vehiculos.get(matricula));
	}
	
	/**
	 * Busca el vehiculo con la matricula indicada entre todos los vehiculos
	 * que tiene la empresa (alquilados o no).
	 * @param matricula matricula del vehiculo buscado
	 * @return el vehiculo buscado o null si no hay ningun vehiculo con esa
	 * matricula
	 */
	//O(1)
	public Vehiculo buscaVehiculo(String matricula) {
		return vehiculos.get(matricula);
	}
	
	/**
	 * Retorna todas las oficinas de la empresa ordenadas alfabeticamente en base a su
	 * nombre.
	 * @return todas las oficinas de la empresa ordenadas alfabeticamente en base a su
	 * nombre.
	 */
	//log(n)
	public Collection<Oficina> oficinas() {
		return oficinas.values();
	}
}
