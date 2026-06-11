

public class Participante  {

	private static int inicicalizarDorsal = 1; 
	private final int dorsal;  
	private Equipo equipo; // equipo del que forma parte 
	private double  tiempoParticipante; 
	private boolean haLlegado; 
	
	public Participante() {
		dorsal = inicicalizarDorsal; 
		inicicalizarDorsal++; 
		haLlegado = false; 
		equipo = null; 
	}

	public int dorsal() {
		return dorsal;
	}

	public Equipo equipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public double tiempoParticipante() {
		return tiempoParticipante;
	}

	public void setTiempoParticipante(double tiempoParticipante) {
		this.tiempoParticipante = tiempoParticipante;
	}

	public boolean haLlegado() {
		return haLlegado;
	}

	public void llega() {
		haLlegado = true;
	}
	
	
	
}
