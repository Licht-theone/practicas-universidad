package mc16;

public abstract class VehiculoCarga extends Vehiculo{
	protected static final int SUPLEMENTO_PMA = 20;
	protected final double pma;
	/**
	 * @param matricula
	 * @param pma
	 */
	public VehiculoCarga(String matricula, double pma) {
		super(matricula);
		this.pma = pma;
	}
	public double getPma() {
		return pma;
	}
	
	public double precio(int dias) {
		return super.precio(dias) + (pma * SUPLEMENTO_PMA);
	}
}
