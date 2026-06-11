import java.util.LinkedList;
import java.util.ListIterator;

@SuppressWarnings("serial")
public class Camion {
	public class PosicionRutaIncorrecta extends RuntimeException{}
	public class RutaEntregasVacia extends RuntimeException{}
	private LinkedList<Entrega> ruta = new LinkedList<Entrega>();
	private final String matricula;
	public Camion(String matricula) {
		this.matricula = matricula;
	}
	public String matricula() {
		return matricula;
	}
	
	public void asignaEntrega(Entrega e, int pos) throws PosicionRutaIncorrecta {
		if (pos < 0 || pos > ruta.size()) {
			throw new PosicionRutaIncorrecta();
		}
		ruta.add(pos, e);
	}
	
	public void realizaEntrega() throws RutaEntregasVacia{
		if (ruta.isEmpty()) {
			throw new RutaEntregasVacia();
		}
		Entrega e = ruta.get(0);
		ruta.remove(0);
		e.cliente().asignaUltEntrega(e, matricula);
	}
	
	public void eliminaEntregasPdtesCliente(int numCli) {
		ListIterator<Entrega> iter = ruta.listIterator();
		while (iter.hasNext()) {
			Entrega e = iter.next();
			if (e.cliente().numCliente() == numCli) {
				iter.remove();
			}
		}
	}
}
