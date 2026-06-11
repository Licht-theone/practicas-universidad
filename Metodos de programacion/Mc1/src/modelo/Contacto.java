package modelo;

public class Contacto {
	//atributos
	private final String nombre;
	private String tlf;
	private String email;
	
	//metodos constructores
	/**
	 * @param nombre
	 * @param tlf
	 * @param email
	 */
	public Contacto(String nombre, String tlf, String email) {
		this.nombre = nombre;
		this.tlf = tlf;
		this.email = email;
	}
	
	//observadores y modificadores
	public String getTlf() {
		return tlf;
	}

	public void setTlf(String tlf) {
		this.tlf = tlf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}
	
	//to string
	@Override
	public String toString() {
		return "Contacto [nombre=" + nombre + ", tlf=" + tlf + ", email=" + email + "]";
	}
	
}
