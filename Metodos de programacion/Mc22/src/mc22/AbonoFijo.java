package mc22;

import mc22.Concierto.ConciertoLleno;

public class AbonoFijo extends Abono{

	public AbonoFijo(String dni) {
		super(dni);
	}

	@Override
	public double precioTot() {
		return 100;
	}

	@Override
	public void compraEntrada(Concierto c, Abono ab) throws MaxEntradas, ConciertoLleno {
		c.anhadeAsistente(ab);
		entradas.add(c);
	}
}
