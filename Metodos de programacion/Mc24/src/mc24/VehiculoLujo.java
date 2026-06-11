package mc24;

public class VehiculoLujo extends Vehiculo{

	public VehiculoLujo(String matricula, int plazas) {
		super(matricula, plazas);
	}

	@Override
	public double precio() {
		return precio + (precio * 0.1);
	}

}
