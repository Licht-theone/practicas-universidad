package mc5;
import fundamentos.*;
public class GUIPaqueton {

	public static void main(String[] args) {
		// opciones del menu
		final int CARGA=0, ENTREGA=1, BUSCA = 2, LISTA_VAC= 3;

		// variables auxiliares
		String desc;
		String id;
		int idCam;
		Lectura lect;
		Empresa correos = new Empresa();
		// Crea el banco.


		// crea la ventana de menu
		Menu menu = new Menu("CORREOS");
		menu.insertaOpcion("CARGA PAQUETE", CARGA);
		menu.insertaOpcion("ENTREGA PAQUETE", ENTREGA);
		menu.insertaOpcion("BUSCA CAMION CON PAQUETE", BUSCA);
		menu.insertaOpcion("LISTA DE VACIOS", LISTA_VAC);
		int opcion;

		// lazo de espera de comandos del usuario
		while(true) {
			opcion = menu.leeOpcion();

			// realiza las acciones dependiendo de la opciÃ³n elegida
			switch (opcion) {

			case CARGA:
				// lee los datos de la nueva cuenta
				lect = new Lectura("Descripcion del paquete");
				lect.creaEntrada("Descripcion", "info");
				lect.creaEntrada("Numero de camion", 0);
				lect.esperaYCierra();
				desc = lect.leeString("Descripcion");
				idCam = lect.leeInt("Numero de camion");
				Paquete paq = new Paquete(desc);
				if (idCam >= 10) {
					mensaje("ERROR", "Ese camion no existe");
					break;
				}
				if (!correos.cargaPaqueteCamion(paq, idCam)) {
					mensaje("ERROR", "no se pudo añadir");
					break;
				}
				mensaje("PAQUETE " + paq.getId(), "Añadido al camion " + idCam);
				break;

			case ENTREGA:
				// lee los datos del movimiento
				lect = new Lectura("ENTREGA PAQUETE");
				lect.creaEntrada("ID PAQUETE", "PAQ");               
				lect.esperaYCierra();
				id = lect.leeString("ID PAQUETE");
				if (!correos.entregaPaquete(id)) {
					mensaje("ERROR", "paquete no encontrado");
					break;
				}
				mensaje("ENTREGADO", "El paquete " + id + " fue entregado correctamente");
				break;
			case BUSCA:
				lect = new Lectura("BUSCA CAMION CON PAQUETE");
				lect.creaEntrada("Id paquete", "PAQ");
				lect.esperaYCierra();
				id = lect.leeString("Id paquete");
				idCam = correos.buscaCamionConPaquete(id);
				if (idCam == 11) {
					mensaje("ERROR", "Ningun camion contenia el paquete");
					break;
				}
				mensaje("ENCONTRADO", "El paquete esta en el camion:" + idCam);
				break;
			case LISTA_VAC:
				lect = new Lectura("CAMIONES VACIOS");
				mensaje("Lista de vacios", correos.listaCamionesVacios());
				break;
			}
		} // while
	}

	/**
	 * Metodo auxiliar que muestra un ventana de mensaje
	 * @param titulo titulo de la ventana
	 * @param txt texto contenido en la ventana
	 */
	private static void mensaje(String titulo, String txt) {
		Mensaje msj = new Mensaje(titulo);
		msj.escribe(txt);
	}


}


