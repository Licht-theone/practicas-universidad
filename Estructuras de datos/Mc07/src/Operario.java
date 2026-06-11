import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Operario {
	private final String nombre;
	private Queue<Reparacion> cola = new ArrayDeque<Reparacion>();
	private ArrayList<Reparacion> historico = new ArrayList<Reparacion>();
	public Operario(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void asignaReparacion(Reparacion r) {
		cola.add(r);
	}
	
	public Reparacion realizaReparacion() {
		Reparacion r = cola.poll();
		historico.addLast(r);
		return r;
	}
	
	public Reparacion consultaReparacion(int pos) {
		if (pos < 0 || pos >= historico.size()) {
			return null;
		}
		return historico.get(pos);
	}
	
	public int eliminaSinRep() {
		return cola.size();
	}
	
}
