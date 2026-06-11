package mc13;

import java.util.ArrayList;

public class Encargado {
	private final String nombre;
	private final String dni;
	private final double sueldo;
	private static final double BONO = 2.5;
	private static final double BONO_POR_EMPLEADO = 0.5;
	private ArrayList<Instalacion> registro;
	private int numEmpleados;
	/**
	 * @param nombre
	 * @param dni
	 * @param sueldo
	 */
	public Encargado(String nombre, String dni, double sueldo) {
		this.nombre = nombre;
		this.dni = dni;
		this.sueldo = sueldo;
		registro = new ArrayList<Instalacion>();
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
	
	public double sueldoTotal() {
		return sueldo + (registro.size() * BONO) + (numEmpleados * BONO_POR_EMPLEADO);
	}
	
	public void anhadeEmpleado() {
		numEmpleados++;
	}
	
	public void eliminaEmpleado() {
		numEmpleados--;
	}
	public void anhadeInstalacion(Instalacion i) {
		registro.add(i);
	}
}
