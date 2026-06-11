package main;

public class Persona implements Comparable <Object>{
	private final String nombre;
	private final int edad;

	public Persona(String nombre, int edad) {
		this.nombre = nombre;
		this.edad = edad;
	}

	public String getNombre() {
		return nombre;
	}

	public int getEdad() {
		return edad;
	}

	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", edad=" + edad + "]";
	}

	@Override
	public int compareTo(Object o) {
		return this.edad - ((Persona)o).edad;
	}

	public boolean equals(Persona p) {
		if (p.edad == this.edad && p.nombre.equals(this.nombre)) {
			return true;
		} else {
			return false;
		}
	}
}
