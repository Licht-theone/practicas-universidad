package examen.c2_comerciales;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Empresa con sus comerciales.
 * 
 * @author Estructuras de Datos (UC) y 
 * @version dic-2020
 */
public class Empresa {
    private SortedMap<String, Comercial> comerciales = new TreeMap<String, Comercial>();
	
	/**
	 * Lanzada cuando se trata de contratar un comercial con el mismo nombre que
	 * otro ya contratado.
	 */
	@SuppressWarnings("serial")
	public static class ComercialYaContratado extends RuntimeException {
	}
	
	/**
	 * Lanzada cuando se proporciona un nombre que no corresponde a ninguno de
	 * los comerciales contratados.
	 */
	@SuppressWarnings("serial")
	public static class ComercialInexistente extends RuntimeException {
	}
	
	/**
	 * Contrata un comercial.
	 * @param comercial comercial a contratar
	 * @throws ComercialYaContratado si ya existe algun comerciales contratado
	 * con el mismo nombre que el que se pretende contratar.
	 */
	public void contrataComercial(Comercial comercial) throws ComercialYaContratado {
		if (comerciales.containsKey(comercial.nombre())) {
			throw new ComercialYaContratado();
		}
		comerciales.put(comercial.nombre(), comercial);
	}
	
	/**
	 * Registra la visita de un comercial a una ciudad.
	 * @param nombreComercial nombre del comercial que realiza la visita.
	 * @param ciudad nombre de la ciudad visitada
	 * @param direccion direccion del cliente visitado
	 * @throws ComercialInexistente si el nombre no corresponde al de ninguno
	 * los comerciales contratados.
	 */
	public void registraVisita(String nombreComercial, String ciudad,
			String direccion) throws ComercialInexistente {
		if (!comerciales.containsKey(nombreComercial)) {
			throw new ComercialInexistente();
		}
		VisitaCiudad v = new VisitaCiudad(ciudad, direccion);
		comerciales.get(nombreComercial).registraVisita(v);
	}
	
	/**
	 * Retorna una lista con los comerciales que han visitado la ciudad
	 * indicada.
	 * @param ciudad nombre de la ciudad.
	 * @return una lista con los comerciales que han visitado la ciudad
	 * indicada.
	 */
	public List<Comercial> comercialesVistantesCiudad(String ciudad) {
		List<Comercial> hanVisitado = new LinkedList<Comercial>();
		for (Entry<String, Comercial> c: comerciales.entrySet()) {
			if (c.getValue().haVisitadoCiudad(ciudad)) {
				hanVisitado.add(c.getValue());
			}
		}
		return hanVisitado;
	}
	
	/**
	 * Retorna las visitas realizadas por un comercial a ciudades cuyo nombre
	 * sea menor (alfabeticamente) que el nombre de la ciudad de corte indicado.
	 * @param nombreComercial nombre del comercial.
	 * @param nombreCorteCiudad nombre de corte de ciudad.
	 * @return las visitas realizadas por un comercial a ciudades cuyo nombre
	 * sea menor (alfabeticamente) que el nombre de la ciudad de corte indicado.
	 * @throws ComercialInexistente si el nombre no corresponde al de ninguno
	 * los comerciales contratados.
	 */
	public Collection<VisitaCiudad> visitasDeComercial(String nombreComercial,
			String nombreCorteCiudad)  throws ComercialInexistente {
		if (!comerciales.containsKey(nombreComercial)) {
			throw new ComercialInexistente();
		}
		return comerciales.get(nombreComercial).visitasDeComercial(nombreCorteCiudad);
	}

}
