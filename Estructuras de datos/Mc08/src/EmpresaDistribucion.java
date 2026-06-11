import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
@SuppressWarnings("serial")
public class EmpresaDistribucion {
	public class NumClienteIncorrecto extends RuntimeException {}
	public class MatriculaIncorrecta extends RuntimeException {}
	public class NoHayEntregasPendientes extends RuntimeException {}
	private Queue<Entrega> pendientes = new LinkedList<Entrega>();
	private Map<Integer, Cliente> clientes = new HashMap<Integer, Cliente>();
	private Map<String, Camion> camiones = new HashMap<String, Camion>();
	
	public EmpresaDistribucion() {
		clientes.put(1234, new Cliente(1234));
		clientes.put(5678, new Cliente(5678));
		camiones.put("1111-AAA", new Camion("1111-AAA"));
		camiones.put("2222-BBB", new Camion("2222-BBB"));
		camiones.put("3333-CCC", new Camion("3333-CCC"));
	}

	public void registraEntregaPendiente(int num1, String desc) throws NumClienteIncorrecto {
		if (!clientes.containsKey(num1)) {
			throw new NumClienteIncorrecto();
		}
		pendientes.add(new Entrega(clientes.get(num1), desc));
	}

	public void asignaSiguienteEntregaPendienteACamion(String mat2, int i) throws MatriculaIncorrecta, 
	Camion.PosicionRutaIncorrecta, NoHayEntregasPendientes {
		if (!camiones.containsKey(mat2)) {
			throw new MatriculaIncorrecta();
		}
		if (pendientes.size() == 0) {
			throw new NoHayEntregasPendientes();
		}
		camiones.get(mat2).asignaEntrega(pendientes.poll(), i);
	}

	public void camionRealizaEntrega(String mat) throws MatriculaIncorrecta, Camion.RutaEntregasVacia {
		if (!camiones.containsKey(mat)) {
			throw new MatriculaIncorrecta();
		}
		camiones.get(mat).realizaEntrega();
	}

	public Collection<Entrega> ultimasEntregas(int i) throws NumClienteIncorrecto {
		if (!clientes.containsKey(i)) {
			throw new NumClienteIncorrecto();
		}
		return clientes.get(i).ultimasEntregas();
	}

	public void eliminaEntregasClienteAsignadas(int num1) throws NumClienteIncorrecto {
		if (!clientes.containsKey(num1)) {
			throw new NumClienteIncorrecto();
		}
		for (Entry<String, Camion> c: camiones.entrySet()) {
			c.getValue().eliminaEntregasPdtesCliente(num1);
		}
	}

	
}
