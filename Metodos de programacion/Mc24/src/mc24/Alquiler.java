package mc24;

public class Alquiler {
	
	private final Vehiculo vehiculo;
	private final String dni;
	private final int id;
	private boolean finalizado;
	
	/**
	 * @param vehiculo
	 * @param dni
	 * @param id
	 */
	public Alquiler(Vehiculo vehiculo, String dni) {
		this.vehiculo = vehiculo;
		this.dni = dni;
		id = cont;
		cont++;
		vehiculo.alquila();
		finalizado = false;
	}
	public boolean isFinalizado() {
		return finalizado;
	}
	public void finaliza() {
		finalizado = true;
		vehiculo.finaliza();
	}
	private static int cont = 0;
	
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	public String getDni() {
		return dni;
	}
	public int getId() {
		return id;
	}
	
}
