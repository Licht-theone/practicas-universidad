package gui;

import java.util.ArrayList;

import mc16.Vehiculo;
import mc16.VehiculoCarga;
import mc16.VehiculoPersonas;
@SuppressWarnings("serial")
public class Empresa {
	public class VehiculoExistente extends RuntimeException{}
	public class VehiculoNoEncontrado extends RuntimeException{}
	public class TipoErroneo extends RuntimeException{}
	private ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
	
	public void ahnadeVehiculo(Vehiculo v) throws VehiculoExistente, TipoErroneo{
		if (buscaVehiculo(v.getMatricula()) != null) {
			throw new VehiculoExistente();
		}
		vehiculos.add(v);
	}
	/*
	 * public void anhadeVehiculo
	 * (String mat, int plazas, double pma, String tipo) 
	 * throws VehiculoExistente, TipoErroneo{
	 * 		if (buscaVehiculo(mat) != null){
	 * 			throw new VehiculoExistente();
	 * 		}
	 * 		switch(tipo){
	 * 		Vehiculo v;
	 * 		Case "Coche":
	 * 		v = new Coche (mat, plazas);
	 * 		break;
	 * 		Case "Microbus"
	 *		v = new Microbus (mat, plazas);
	 * 		break;
	 * 		Case "Furgoneta"
	 * 		v = new Furgoneta (mat, pma);
	 *  	break;
	 * 		Case "Camion"
	 * 		v = new Camion (mat, pma);
	 *  	break;
	 *  	default:
	 *  	throw new TipoErroneo();
	 * 		}
	 * 		vehiculos.add(v)		
	 * }
	 */
	
	public double precioAlquiler(String matricula, int dias) throws VehiculoNoEncontrado{
		Vehiculo v = buscaVehiculo(matricula);
		if (v == null) {
			throw new VehiculoExistente();
		}
		return v.precio(dias);
	}
	
	public Vehiculo buscaVehiculo(String matricula) {
		for (Vehiculo v: vehiculos) {
			if (v.getMatricula().equals(matricula)) {
				return v;
			}
		}
		return null;
	}
	
	public Vehiculo vehiculoMayorPMA() {
		double max = Integer.MIN_VALUE;
		double nuevoMax = 0;
		Vehiculo a = null;
		for(Vehiculo v: vehiculos) {
			if (v instanceof VehiculoCarga) {
				nuevoMax = ((VehiculoCarga) v).getPma();
				if (nuevoMax > max) {
					max = nuevoMax;
					a = v;
				}
			}
		}
		return a;
	}
	
	public Vehiculo vehiculoPasMasBarato(int dias) {
		double min = Integer.MAX_VALUE;
		double nuevoMin = 0;
		Vehiculo a = null;
		for(Vehiculo v: vehiculos) {
			if (v instanceof VehiculoPersonas) {
				nuevoMin = ((VehiculoPersonas) v).precio(dias);
				if (nuevoMin < min) {
					min = nuevoMin;
					a = v;
				}
			}
		}
		return a;
	}
}
