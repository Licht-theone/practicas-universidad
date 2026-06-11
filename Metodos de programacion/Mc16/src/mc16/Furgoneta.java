package mc16;

public class Furgoneta extends VehiculoCarga{

	public Furgoneta(String matricula, double pma) {
		super(matricula, pma);
	}
	
	public double precio(int dias) {
		return super.precio(dias);
	}
}
