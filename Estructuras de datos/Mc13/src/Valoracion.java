
public class Valoracion implements Comparable<Valoracion>{
	private Restaurante restaurante;
	private int nota;
	private final int urgencia;
	private final Critico c;
	public Valoracion(Restaurante restaurante, int urgencia, Critico c) {
		this.restaurante = restaurante;
		nota = 0;
		this.urgencia = urgencia;
		this.c = c;
	}
	public Restaurante restaurante() {
		return restaurante;
	}
	
	public int nota() {
		return nota;
	}
	
	public void valora(int nota) {
		this.nota = nota;
	}
	
	@Override
	public int compareTo(Valoracion o) {
		return o.urgencia - this.urgencia;
	}
	public Critico critico() {
		return c;
	}
	
}
