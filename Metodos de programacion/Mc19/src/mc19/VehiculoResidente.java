package mc19;

import java.util.ArrayList;

public class VehiculoResidente extends Vehiculo{
	private static final double PRECIO_MIN = 0.013;
	private ArrayList<Estacionamiento> registro = new ArrayList<Estacionamiento>();
	public VehiculoResidente(String matricula) {
		super(matricula);
	}
	@Override
	public void estacionaVehiculo(Estacionamiento e) throws YaEstacionado {
		if (estacionado != null) {
			throw new YaEstacionado();
		}
		registro.add(e);
		estacionado = e;
		
	}
	@Override
	public void finalizaYCobra() throws NoEstacionado {
		if (estacionado == null) {
			throw new NoEstacionado();
		}
		estacionado = null;
	}
	@Override
	public double cobra() {
		int suma = 0;
		for (Estacionamiento e: registro) {
			suma += e.minutos();
		}
		return suma * PRECIO_MIN;
	}
}
