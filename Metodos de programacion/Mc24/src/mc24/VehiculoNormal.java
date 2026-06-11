package mc24;

public class VehiculoNormal extends Vehiculo{

	public VehiculoNormal(String matricula, int plazas) {
		super(matricula, plazas);
	}

	@Override
	public double precio() {
		return precio;
	}

}
