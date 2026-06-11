import java.util.HashMap;
import java.util.Map;

/**
 * Camion de reparto de un supermercado.
 * @author 
 * @version 2-dic-2021
 */
public class Camion {
	private final String matricula;
	private Map<String, Pedido> pedidos = new HashMap<String, Pedido>();
	private final float cargaMax;
	private float cargaAct;
	public Camion(String matricula, float cargaMax) {
		this.matricula = matricula;
		this.cargaMax = cargaMax;
		cargaAct = 0;
	}
	public String matricula() {
		return matricula;
	}
	public float cargaMax() {
		return cargaMax;
	}
	public void vacia() {
		pedidos.clear();
		cargaAct = 0;
	}
	public boolean cargaCamion(Pedido p) {
		if (cargaAct + p.peso() > cargaMax) {
			return false;
		}
		cargaAct += p.peso();
		pedidos.put(p.direccion(), p);
		return true;
	}
	public Pedido buscaPedido(String direccion) {
		return pedidos.get(direccion);
	}
}
