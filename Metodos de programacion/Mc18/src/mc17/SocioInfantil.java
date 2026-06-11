package mc17;

public class SocioInfantil extends Socio{
	private final static int CUOTA_INF = 30;
	private Socio socioAsociado;
	private final static double DESCUENTO = 0.75;
	/**
	 * @param dni
	 * @param nombre
	 * @param asociado
	 * @param socioAsociado
	 */
	public SocioInfantil(String dni, String nombre, Socio s) {
		super(dni, nombre);
		socioAsociado = s;
	}
	public boolean estaAsociado() {
		if (socioAsociado instanceof SocioNormal) {
			return true;
		}
		return false;
	}
	public Socio asociado() {
		return socioAsociado;
	}
	@Override
	public double precioTotal() {
		if (socioAsociado instanceof SocioNormal) {
			return (CUOTA_INF + precioTotalCursos()) * DESCUENTO;
		}
		return CUOTA_INF + precioTotalCursos();
	}
	@Override
	public String toString() {
		return "[cursos=" + cursos.toString() + ", precioTotal()=" + precioTotal() + ", Dni=" + getDni()
				+ ",Nombre=" + getNombre() + ", numCursos()=" + numCursos() + ", precioTotal="
				+ precioTotal() + "Padre=" + socioAsociado.getNombre() + " DNI=" + socioAsociado.getDni();
	}
}
