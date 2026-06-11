package mc13;

import java.util.ArrayList;

public class Empleado {
	private final String nombre;
	private final String dni;
	private final double sueldo;
	private Encargado encargado;
	private static final double BONO = 2.5;
	private ArrayList<Instalacion> registro;
	/**
	 * @param nombre
	 * @param dni
	 * @param sueldo
	 * @param encargado
	 */
	public Empleado(String nombre, String dni, double sueldo, Encargado encargado) {
		this.nombre = nombre;
		this.dni = dni;
		this.sueldo = sueldo;
		this.encargado = encargado;
		registro = new ArrayList<Instalacion>();
	}
	public Encargado getEncargado() {
		return encargado;
	}
	public void setEncargado(Encargado encargado) {
		this.encargado = encargado;
	}
	public String getNombre() {
		return nombre;
	}
	public String getDni() {
		return dni;
	}
	public double getSueldo() {
		return sueldo;
	}
	public int numCalderas() {
		return registro.size();
	}
	public double sueldoTotal() {
		return sueldo + (registro.size() * BONO);
	}
	
	public void anhadeInstalacion(Instalacion i) {
		registro.add(i);
	}
}
