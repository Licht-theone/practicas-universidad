package mc16;

public class Coche extends VehiculoPersonas{

	public Coche(String matricula, int plazas) {
		super(matricula, plazas);
	}
	
	public double precio(int dias) {
		return super.precio(dias);
	}
}
