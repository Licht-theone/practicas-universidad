package mc3;

public class Piso {
	private Agente agente;
	private final String ref;
	private int precio;
	private final int superficie;
	private final int num_hab;
	private final int num_banos;
	private static int aut = 1;

	/**
	 * @param ref
	 * @param precio
	 * @param superficie
	 * @param num_hab
	 * @param num_banos
	 */
	public Piso(int precio, int superficie, int num_hab, int num_banos) {
		ref = "REF" + aut; 
		this.precio = precio;
		this.superficie = superficie;
		this.num_hab = num_hab;
		this.num_banos = num_banos;
		agente = null;
		aut++;
	}

	public String getIdentificador() {
		return ref;
	}

	public int getPrecio() {
		return precio;
	}

	public int getSuperficie() {
		return superficie;
	}

	public int getNum_hab() {
		return num_hab;
	}

	public int getNum_banos() {
		return num_banos;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public Agente getAgente() {
		return agente;
	}

	public void setAgente(Agente agente) {
		this.agente = agente;
	}

	@Override
	public String toString() {
		return "Piso [ref=" + ref + ", precio=" + precio + ", superficie=" + superficie + ", num_hab=" + num_hab
				+ ", num_banos=" + num_banos + "]";
	}

}
