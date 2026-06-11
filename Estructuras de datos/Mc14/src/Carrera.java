import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

@SuppressWarnings("serial")
public class Carrera {
	public class ParticipateNoExiste extends RuntimeException{}
	public class ParticipanteYaEnMeta extends RuntimeException{}
	private Set<Equipo> clasifEq = new TreeSet<Equipo>();
	private Map<Integer, Participante> participantes = new HashMap<Integer, Participante>();
	private SortedMap<String, Equipo> eqAlf = new TreeMap<String, Equipo>();

	
	public void anhadeParticipante(Participante p) {
		participantes.put(p.dorsal(), p);
	}
	
	public void anhadeEquipo(Equipo e) {
		eqAlf.put(e.nombre(), e);
	}
	
	public void asignaParticipanteAEquipo(int dorsal, String nombre) {
		Participante p = participantes.get(dorsal);
		Equipo e = eqAlf.get(nombre);
		p.setEquipo(e);
		e.anhadeParticipante(p);
	}
	
	public void participanteLlegaAMeta(int dorsal, double tiempo) throws ParticipanteYaEnMeta, ParticipateNoExiste{
		Participante p = participantes.get(dorsal);
		if (p == null) {
			throw new ParticipateNoExiste();
		}
		if (p.haLlegado()) {
			throw new ParticipanteYaEnMeta();
		}
		p.setTiempoParticipante(tiempo);
		p.llega();
		Equipo e = p.equipo();
		if (e != null) {
			e.acumulaTiempo(tiempo);
		}
		if (e.todosEnMeta()) {
			clasifEq.add(e);
		}
	}

	public String muestraClasificacionEquipos() {
		String s = "";
		for (Equipo e: clasifEq) {
			s += e.toString() + "\n";
		}
		return s;
	}
}
