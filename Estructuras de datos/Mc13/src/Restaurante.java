import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Restaurante implements Comparable<Restaurante> {
	private final String nombre;
	private double notaMedia;
	private ArrayList<Valoracion> valoraciones = new ArrayList<Valoracion>();
	private Set<Critico> criticos = new HashSet<Critico>();
	public Restaurante(String nombre) {
		this.nombre = nombre;
	}
	public String nombre() {
		return nombre;
	}
	
	public void anhadeValoracion(Valoracion v) {
		valoraciones.add(v);
	}
	
	public Set<Critico> criticos() {
		return null;
	}
	private void media() {
		int tot = valoraciones.size();
		float media = 0;
		for (Valoracion v: valoraciones) {
			media += v.nota();
		}
		notaMedia = (media / tot);
	}
	@Override
	public int compareTo(Restaurante o) {
		media();
		return (int) (o.notaMedia - this.notaMedia);
	}
}
