import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
@SuppressWarnings("serial")
public abstract class Cliente {
	public class NoDeViaje extends RuntimeException{}
	private final String dni;
	private boolean viajando;
	protected ArrayList<Hotel> registro;
	private int numViajes = 0;
	
	/**
	 * @param dni
	 */
	public Cliente(String dni) {
		super();
		this.dni = dni;
		viajando = false;
		registro = new ArrayList<Hotel>();
	}

	public boolean isViajando() {
		return viajando;
	}

	public void comienzaViaje() {
		viajando = true;
		numViajes++;
	}
	
	public void finalizaViaje() throws IOException, NoDeViaje{
		if (!viajando) {
			throw new NoDeViaje();
		}
		try (PrintWriter out = new PrintWriter(new FileWriter(dni + "_" + numViajes + ".txt"))){
			for (Hotel h: registro) {
				out.printf("%10s %9s %7s %3.2f", h.getNombre(), h.getFecha(), h.getHora(), h.getPrecio());
			}
		}
		viajando = false;
	}
	
	public void anhadeViaje(Hotel h) throws NoDeViaje{
		if (!viajando) {
			throw new NoDeViaje();
		}
		registro.add(h);
	}
	
	public String getDni() {
		return dni;
	}
	
}
