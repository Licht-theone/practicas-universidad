package mc19;

public class VehiculoOficial extends Vehiculo{
	private static final int PRECIO = 200;
	public VehiculoOficial(String matricula) {
		super(matricula);
	}
	@Override
	public void estacionaVehiculo(Estacionamiento e) throws YaEstacionado {
		if (estacionado != null) {
			throw new YaEstacionado();
		}
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
		return PRECIO;
	}
}
