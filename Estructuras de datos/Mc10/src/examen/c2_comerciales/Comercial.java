package examen.c2_comerciales;

import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Comercial de una empresa.
 * 
 * @author Estructuras de Datos (UC) y 
 * @version dic-2020
 */
public class Comercial {
	private final String nombre;
    private SortedMap<String, VisitaCiudad> ciudadesVisitadas = new TreeMap<String, VisitaCiudad>();

	/**
	 * Construye un comercial con el nombre indicado.
	 * @param nombre nombre del comercial.
	 */
	public Comercial(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Retorna el nombre del comercial.
	 * @return el nombre del comercial
	 */
	public String nombre() {
		return nombre;
	}
	
	public void registraVisita(VisitaCiudad v) {
		if (ciudadesVisitadas.containsKey(v.nombreCiudad())) {
			ciudadesVisitadas.get(v.nombreCiudad()).actualizaDireccionUltimoCliente(v.direccionUltimoCliente());
		}
		ciudadesVisitadas.put(v.nombreCiudad(), v);
	}
	
	public boolean haVisitadoCiudad(String nombre) {
		if (ciudadesVisitadas.containsKey(nombre)) {
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<VisitaCiudad> visitasDeComercial (String nombreCorte) {
		return (Collection<VisitaCiudad>) ciudadesVisitadas.headMap(nombreCorte);
	}

}
