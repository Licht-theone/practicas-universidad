package mc16;
//protected prohibido!!!
public abstract class Vehiculo {
	protected static final int PRECIO_BASE = 50;
	protected final String matricula;
	/**
	 * @param matricula
	 */
	public Vehiculo(String matricula) {
		this.matricula = matricula;
	}
	public String getMatricula() {
		return matricula;
	}
	
	public double precio(int dias) {
		return dias *PRECIO_BASE;
	}
}
