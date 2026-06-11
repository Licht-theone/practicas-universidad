package pract01.modelo;

/**
 * Contacto de la agenda.
 *
 * @author Metodos de Programacion (UC) y Aaron Alegria
 * @version sep-2023
 */
public class Contacto {
	// atributos (informacion almacenada en un contacto)
	private final String nombre;
	private String tlf;
	private String email;
	/**
	 * Construye un contacto con los datos indicados.
	 * @param nombre nombre del contacto
	 * @param tlf telefono del contacto
	 * @param email direccion de correo electronico del contacto
	 */
	public Contacto(String nombre, String tlf, String email) {
		// da valor a los atributos
		this.nombre = nombre;
		this.tlf = tlf;
		this.email = email;
	}

	// metodos observadores (nombre(), tlf() y email())
	public String nombre() {
		return nombre;
	}
	
	public String tlf() {
		return tlf;
	}
	
	public String email() {
		return email;
	}
	
	// metodos cambiadores del telefono y el e-mail
	public void cambiaTlf(String nuevoTlf) {
		tlf = nuevoTlf;
	}
	
	public void cambiaEmail(String nuevoEmail) {
		email = nuevoEmail;
	}
}
