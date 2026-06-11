package mc22;

import mc22.Concierto.ConciertoLleno;

public class AbonoBas extends Abono{
	private static final int MAX_ENTRADAS = 2;
	public AbonoBas(String dni) {
		super(dni);
	}

	@Override
	public double precioTot() {
		double sumatorio = 0;
		for (Concierto c: entradas) {
			sumatorio += c.getPrecio();
		}
		return sumatorio;
	}

	@Override
	public void compraEntrada(Concierto c, Abono ab) throws MaxEntradas, ConciertoLleno {
		if (entradas.size() >= MAX_ENTRADAS) {
			throw new MaxEntradas();
		}
		c.anhadeAsistente(ab);
		entradas.add(c);
	}
	
}
