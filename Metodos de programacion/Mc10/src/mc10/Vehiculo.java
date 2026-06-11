package mc10;

public class Vehiculo {
	private final String matricula;
	private Oficina oficina;

	/**
	 * @param matricula
	 */
	public Vehiculo(String matricula, Oficina oficina) {
		super();
		this.matricula = matricula;
		this.oficina = oficina;
	}

	public String matricula() {
		return matricula;
	}

	public Oficina getOficina() {
		return oficina;
	}

	public void setOficina(Oficina oficina) {
		this.oficina = oficina;
	}

}
