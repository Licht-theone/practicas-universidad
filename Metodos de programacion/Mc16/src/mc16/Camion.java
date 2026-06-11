package mc16;

public class Camion extends VehiculoCarga{
	private static final int SUPLEM = 40;
	public Camion(String matricula, double pma) {
		super(matricula, pma);
	}
	
	public double precio(int dias) {
		return super.precio(dias) + SUPLEM;
	}
}
