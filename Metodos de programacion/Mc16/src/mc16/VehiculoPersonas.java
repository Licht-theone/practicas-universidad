package mc16;

public abstract class VehiculoPersonas extends Vehiculo{
	protected final int plazas;
	protected static final double SUPLEMENTO_COCHE = 1.5;
	protected static final int SUPLEMENTO_BUS = 2;
	/**
	 * @param matricula
	 * @param plazas
	 */
	public VehiculoPersonas(String matricula, int plazas) {
		super(matricula);
		this.plazas = plazas;
	}
	public int getPlazas() {
		return plazas;
	}
	
	public double precio(int dias) {
		return super.precio(dias) + ((plazas + dias) * SUPLEMENTO_COCHE);
	}
}
