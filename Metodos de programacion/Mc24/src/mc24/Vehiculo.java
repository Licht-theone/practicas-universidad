package mc24;

public abstract class Vehiculo {
	protected final int precio = 30;
	private final String matricula;
	private final int plazas;
	private boolean alquilado;
	/**
	 * @param matricula
	 * @param plazas
	 */
	public Vehiculo(String matricula, int plazas) {
		super();
		this.matricula = matricula;
		this.plazas = plazas;
		alquilado = false;
	}
	public abstract double precio();

	public String getMatricula() {
		return matricula;
	}
	public int getPlazas() {
		return plazas;
	}
	public boolean isAlquilado() {
		return alquilado;
	}
	public void alquila() {
		alquilado = true;
	}
	public void finaliza() {
		alquilado = false;
	}
}
