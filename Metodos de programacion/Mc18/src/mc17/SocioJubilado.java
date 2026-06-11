package mc17;

public class SocioJubilado extends Socio{
	private final static double DESCUENTO = 0.9;
	
	public SocioJubilado(String dni, String nombre) {
		super(dni, nombre);
	}

	@Override
	public double precioTotal() {
		return (CUOTA_NORMAL + precioTotalCursos()) * DESCUENTO;
	}
	@Override
	public String toString() {
		return "[cursos=" + cursos.toString() + ", precioTotal()=" + precioTotal() + ", Dni=" + getDni()
				+ ",Nombre=" + getNombre() + ", numCursos()=" + numCursos() + ", precioTotal="
				+ precioTotal();
	}
}
