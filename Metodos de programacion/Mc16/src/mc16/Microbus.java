package mc16;

public class Microbus extends VehiculoPersonas{

	public Microbus(String matricula, int plazas) {
		super(matricula, plazas);
	}
	
	public double precio(int dias) {
		return super.precio(dias) + (plazas * SUPLEMENTO_BUS);
	}
}
