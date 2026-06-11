

/**
 * Pedido para entrega a domicilio de un supermercado.
 * @author 
 * @version 2-dic-2021
 */
public class Pedido {
	private final String nomCli;
	private final String direccion;
	private final float peso;
	public Pedido(String nomCli, String direccion, float peso) {
		this.nomCli = nomCli;
		this.direccion = direccion;
		this.peso = peso;
	}
	public String nomCli() {
		return nomCli;
	}
	public String direccion() {
		return direccion;
	}
	public float peso() {
		return peso;
	}
	

}
