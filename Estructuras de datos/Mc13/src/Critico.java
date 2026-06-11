import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Critico {
	@SuppressWarnings("serial")
	public class NoEsLaMasUrgente extends RuntimeException{}
	private final String codigo;
	private Map<String, Valoracion> valorados = new HashMap<String, Valoracion>();
	private PriorityQueue<Valoracion> pendientes = new PriorityQueue<Valoracion>();
	
	public Critico(String codigo) {
		this.codigo = codigo;
	}

	public String codigo() {
		return codigo;
	}
	public void programaValoracion(Valoracion v) {
		pendientes.offer(v);
	}
	public void valora(int nota, Restaurante r) throws NoEsLaMasUrgente {
		if (!pendientes.peek().restaurante().nombre().equals(r.nombre())) {
			throw new NoEsLaMasUrgente();
		}
		Valoracion v = pendientes.poll();
		v.valora(nota);
		if (valorados.containsKey(r.nombre())) {
			valorados.replace(r.nombre(), v);
		} else {
			valorados.put(r.nombre(), v);
		}
	}
	public Valoracion ultValoracion(Restaurante r) {
		return valorados.get(r.nombre());
	}
	
	@Override
	public int hashCode() {
		return codigo.hashCode();
	}
}
