package mc22;

import java.util.ArrayList;

import mc22.Concierto.ConciertoLleno;

public abstract class Abono {
	@SuppressWarnings("serial")
	public class MaxEntradas extends RuntimeException{}
	private final String dni;
	protected ArrayList<Concierto> entradas;
	
	/**
	 * @param dni
	 */
	public Abono(String dni) {
		this.dni = dni;
		entradas = new ArrayList<Concierto>();
	}
	
	public String getDni() {
		return dni;
	}

	public Concierto buscaConcierto(String nombre) {
		for (Concierto c: entradas) {
			if (c.getNombre().equals(nombre)) {
				return c;
			}
		}
		return null;
	}
	
	public abstract double precioTot();
	
	public abstract void compraEntrada(Concierto c, Abono ab) throws MaxEntradas, ConciertoLleno;
}
