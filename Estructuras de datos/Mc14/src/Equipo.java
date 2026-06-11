
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Equipo implements Comparable<Equipo> {

	private final String nombre; 
	private static final int MAX_PARTICIPANTES = 10; 
	private double tiempo;
	private Map<Integer, Participante> integrantes = new HashMap<Integer, Participante>();
	public Equipo(String nombre) {
		super();
		this.nombre = nombre;
	}
	public String nombre() {
		return nombre;
	}
	
	public double tiempo() {
		return tiempo;
	}
	public void acumulaTiempo(double tiempo) {
		this.tiempo += tiempo;
	}
	public void anhadeParticipante(Participante p) {
		if (integrantes.size() >= MAX_PARTICIPANTES) {
			
		}
		integrantes.put(p.dorsal(), p);
	}
	public boolean todosEnMeta() {
		int cont = 0;
		for(Entry<Integer, Participante> p: integrantes.entrySet()) {
			if (p.getValue().haLlegado()) {
				cont++;
			}
		}
		if (cont == integrantes.size()) {
			return true;
		}
		return false;
	}
	
	@Override
	public int compareTo(Equipo o) {
		return (int) (this.tiempo - o.tiempo());
	}
	
	@Override
	public String toString() {
		String s = nombre + " Tiempo:" + tiempo + "\n";
		for(Entry<Integer, Participante> p: integrantes.entrySet()) {
			s += p.getValue().dorsal() + " Meta: " + p.getValue().haLlegado() + "\n";
		}
		return s;
	}
}
