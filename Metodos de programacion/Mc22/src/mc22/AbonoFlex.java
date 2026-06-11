package mc22;

import mc22.Concierto.ConciertoLleno;

public class AbonoFlex extends Abono{

	public AbonoFlex(String dni) {
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
		c.anhadeAsistente(ab);
		entradas.add(c);
	}
	
}
