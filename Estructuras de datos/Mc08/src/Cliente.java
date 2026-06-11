import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

public class Cliente {
	private final int numCliente;
	private SortedMap<String, Entrega> ultimasEntregas = new TreeMap<String, Entrega>();
	public Cliente(int numCliente) {
		this.numCliente = numCliente;
	}

	public int numCliente() {
		return numCliente;
	}

	public Collection<Entrega> ultimasEntregas() {
		return ultimasEntregas.values();
	}

	public void asignaUltEntrega(Entrega ultEntrega, String matricula) {
		ultimasEntregas.put(matricula, ultEntrega);
	}
	
}
