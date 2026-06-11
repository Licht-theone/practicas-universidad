package mc22;

import java.util.ArrayList;

public class Concierto {
	@SuppressWarnings("serial")
	public class ConciertoLleno extends RuntimeException {}
	private final String nombre;
	private final String localidad;
	private final double precio;
	private final int maxEntradas;
	private ArrayList<Abono> abonos = new ArrayList<Abono>();
	/**
	 * @param nombre
	 * @param localidad
	 * @param precio
	 * @param maxEntradas
	 */
	public Concierto(String nombre, String localidad, double precio, int maxEntradas) {
		super();
		this.nombre = nombre;
		this.localidad = localidad;
		this.precio = precio;
		this.maxEntradas = maxEntradas;
	}
	public String getNombre() {
		return nombre;
	}
	public String getLocalidad() {
		return localidad;
	}
	public double getPrecio() {
		return precio;
	}
	public int getMaxEntradas() {
		return maxEntradas;
	}
	public void anhadeAsistente(Abono ab) throws ConciertoLleno{
		if (abonos.size() >= maxEntradas) {
			throw new ConciertoLleno();
		}
		abonos.add(ab);
	}
	
	public int cuentaFijos() {
		int num = 0;
		for (Abono ab: abonos) {
			if (ab instanceof AbonoFijo) {
				num++;
			}
		}
		return num;
	}
	
	public int cuentaFlex() {
		int num = 0;
		for (Abono ab: abonos) {
			if (ab instanceof AbonoFlex) {
				num++;
			}
		}
		return num;
	}
	
	public int cuentaBas() {
		int num = 0;
		for (Abono ab: abonos) {
			if (ab instanceof AbonoBas) {
				num++;
			}
		}
		return num;
	}
}
