package pract01.modelo;

/**
 * Agenda de contactos.
 *
 * @author Metodos de Programacion (UC) y Aaron Alegria
 * @version sep-2023
 */
public class Agenda {
	// contactos de la agenda
	private static final int MX_NUM_CONTACTOS = 10;
	private Contacto[] contactos = new Contacto[MX_NUM_CONTACTOS];
	private int numContactos = 0; // numero actual de contactos en la agenda
	
	/**
	 * Anhade el contacto pasado como parametro a la agenda.
	 * @param contacto contacto a añadir
	 * @return verdadero si el contacto ha sido anhadido y falso si no a sido
	 * anahdido porque ya existe en la agenda otro contacto con el mismo nombre
	 * o porque se ha alcanzado el numero maximo de contactos permitido.
	 */
	public boolean anhadeContacto(Contacto contacto) {
		// Si numContactos es mayor o igual que MX_NUM_CONTACTOS
		//   retorna falso
		// Si ya existe un contacto con el mismo nombre (usar buscaContacto())
		//   retorna falso
		// Anhade el contacto en la posicion numContactos del array contactos
		// Incrementa numContactos
		if ((numContactos >= MX_NUM_CONTACTOS) || (buscaContacto(contacto.nombre()) != null)) {
			return false;
		}
		contactos[numContactos] = contacto;
		numContactos++;
		return true;
	}
	
	/**
	 * Cambia el telefono de un contacto.
	 * @param nombre nombre del contacto cuyo telefono se quiere cambiar
	 * @param tlf nuevo telefono a asignar al contacto
	 * @return verdadero si se ha cambiado el telefono del contacto y falso si no ha sido
	 * posible ya que no existe ningun contacto con el nombre indicado
	 */
	public boolean cambiaTlfContacto(String nombre, String tlf) {
		// Busca el contacto llamando a buscaContacto()
		// Si no existe retorna falso
		// Cambia el telefono del contacto.
		Contacto contacto = buscaContacto(nombre);
		if (contacto == null) {
			return false;
		}
		contacto.cambiaTlf(tlf);
		return true;
	}
	
	/**
	 * Cambia el e-mail de un contacto.
	 * @param nombre nombre del contacto cuyo e-mail se quiere cambiar
	 * @param email nuevo e-mail a asignar al contacto
	 * @return verdadero si se ha cambiado el e-mail del contacto y falso si no ha sido
	 * posible ya que no existe ningun contacto con el nombre indicado
	 */
	public boolean cambiaEmailContacto(String nombre, String email) {
		// Busca el contacto llamando a buscaContacto()
		// Si no existe retorna falso
		// Cambia el e-mail del contacto.
		Contacto contacto = buscaContacto(nombre);
		if (contacto == null) {
			return false;
		}
		contacto.cambiaEmail(email);
		return true;
	}
	
	/**
	 * Retorna el contacto con el nombre indicado.
	 * @param nombre nombre del contacto buscado
	 * @return el contacto con el nombre buscado o null si no existe ningun contacto con
	 * ese nombre
	 */
	public Contacto buscaContacto(String nombre) {
		// Recorre la parte utilizada en array contactos
		for (int i = 0; i < numContactos; i++) {
			// Si el nombre del contacto i-esimo es igual a nombre
			//   retorna el contacto i-esimo del array
			if (contactos[i].nombre().equals(nombre)) {
				return contactos[i];
			}
		}
		return null; // no encontrado
	}
}
