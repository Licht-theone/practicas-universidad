package mc17;

public class SocioNormal extends Socio{

	public SocioNormal(String dni, String nombre) {
		super(dni, nombre);
	}

	@Override
	public double precioTotal() {
		return CUOTA_NORMAL + precioTotalCursos();
	}

	@Override
	public String toString() {
		return "[cursos=" + cursos.toString() + ", precioTotal()=" + precioTotal() + ", Dni=" + getDni()
				+ ",Nombre=" + getNombre() + ", numCursos()=" + numCursos() + ", precioTotal="
				+ precioTotal();
	}
	
}
