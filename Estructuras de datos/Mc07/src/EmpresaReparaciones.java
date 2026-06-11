import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

@SuppressWarnings("serial")
public class EmpresaReparaciones {
	private HashMap<String, Operario> operarios = new HashMap<String, Operario>();
	public class OperarioNoExistente extends RuntimeException{}
	public class NoHayReparacionesPendientes extends RuntimeException{}
	public class PosReparacionIncorrecta extends RuntimeException{}
	
	public EmpresaReparaciones(String nomFich) throws FileNotFoundException {
		try (Scanner in = new Scanner(new FileReader(nomFich))){
			while(in.hasNext()) {
				String nombre = in.next();
				Operario nuevo = new Operario(nombre);
				operarios.put(nombre, nuevo);
			}
		}
	}
	
	public void asignaReparacionAOperario(String nombre, Reparacion r) throws OperarioNoExistente {
		Operario o = operarios.get(nombre);
		if (o == null) {
			throw new OperarioNoExistente();
		}
		o.asignaReparacion(r);
	}
	
	public Reparacion operarioFinalizaReparacion(String nombre) throws OperarioNoExistente, NoHayReparacionesPendientes {
		Operario o = operarios.get(nombre);
		if (o == null) {
			throw new OperarioNoExistente();
		}
		Reparacion r = o.realizaReparacion();
		if (r == null) {
			throw new NoHayReparacionesPendientes();
		}
		return r;
	}
	
	public Reparacion reparacionEnHistorico(String nombre, int pos) throws OperarioNoExistente, PosReparacionIncorrecta {
		Operario o = operarios.get(nombre);
		if (o == null) {
			throw new OperarioNoExistente();
		}
		Reparacion r = o.consultaReparacion(pos);
		if (r == null) {
			throw new PosReparacionIncorrecta();
		}
		return r;
	}
	
	public void eliminaOperariosDesocupados() {
		
	}
}
