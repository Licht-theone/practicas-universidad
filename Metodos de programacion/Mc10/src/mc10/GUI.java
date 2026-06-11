package mc10;

import fundamentos.*;

public class GUI {
	public static final int ANHADE_VEHI = 0;
	public static final int ELIMINA_VEHI = 1;
	public static final int VEHICULO_ESTACIONA = 2;
	public static final int VEHI_A_REPARTO = 3;
	public static final int LOCALIZACION = 4;
	public static final int OFI_MENOS_VEHI = 5;
	public static final int VEHICULO_NO_EXISTE = -1;
	public static final int VEHICULO_EN_REP = -2;
	public static final int VEHICULO_EN_OF = -3;
	public static void main(String[] args) {
		Lectura lect;
		String matricula;
		int numOfi;
		Oficina o;
		Empresa seur = new Empresa();
		Menu menu = new Menu("Seur");
		menu.insertaOpcion("Añade vehiculo", ANHADE_VEHI);
		menu.insertaOpcion("Elimina vehiculo", ELIMINA_VEHI);
		menu.insertaOpcion("Estaciona vehiculo en oficina", VEHICULO_ESTACIONA);
		menu.insertaOpcion("Vehiculo comienza reparto", VEHI_A_REPARTO);
		menu.insertaOpcion("Localiza vehiculo", LOCALIZACION);
		menu.insertaOpcion("Oficina con menos vehiculos", OFI_MENOS_VEHI);
		int opcion;
		while (true) {
			opcion = menu.leeOpcion();
			switch (opcion) {
			case ANHADE_VEHI:
				lect = new Lectura("Añadir vehiculo");
				lect.creaEntrada("Matricula", "0000AAA");
				lect.creaEntrada("Oficina", "0");
				lect.esperaYCierra();
				matricula = lect.leeString("Matricula");
				numOfi = lect.leeInt("Oficina");
				if (!seur.anhadeVehiculo(matricula, numOfi)) {
					mensaje("Error", "No se pudo añadir");
					break;
				}
				mensaje("Añadido con exito", 
						"El vehiculo con matricula " + matricula + 
						" se añadio a la oficina " + numOfi);
				break;
			case ELIMINA_VEHI:
				lect = new Lectura("Eliminar vehiculo");
				lect.creaEntrada("Matricula", "0000AAA");
				lect.esperaYCierra();
				matricula = lect.leeString("Matricula");
				if (!seur.eliminaVehiculo(matricula)) {
					mensaje("Error", "El vehiculo no existe");
					break;
				}
				mensaje("Vehiculo eliminado", 
						"El vehiculo con matricula " + matricula + " fue eliminado");
				break;
			case VEHICULO_ESTACIONA:
				lect = new Lectura("Estacionar vehiculo");
				lect.creaEntrada("Matricula", "0000AAA");
				lect.creaEntrada("Oficina", "0");
				lect.esperaYCierra();
				numOfi = lect.leeInt("Oficina");
				matricula = lect.leeString("Matricula");
				if (!seur.estacionaVehiculoEnOfi(matricula, numOfi)) {
					mensaje("Error", "No se pudo estacionar");
					break;
				}
				mensaje("Vehiculo estacionado", "En la oficina " + numOfi);
				break;
			case VEHI_A_REPARTO:
				lect = new Lectura("Vehiculo empieza reparto");
				lect.creaEntrada("Matricula", "0000AAA");
				lect.esperaYCierra();
				matricula = lect.leeString("Matricula");
				if (!seur.vehiculoComienzaReparto(matricula)) {
					mensaje("Error", "El vehiculo no existe");
					break;
				}
				mensaje("Vehiculo ha comenzado el reparto", 
						"El vehiculo con matricula " + matricula + 
						" ha comenzado el reparto");
				break;
			case LOCALIZACION:
				lect = new Lectura("Localiza vehiculo");
				lect.creaEntrada("Matricula", "0000AAA");
				lect.esperaYCierra();
				matricula = lect.leeString("Matricula");
				int num = seur.consultaLocalizacion(matricula);
				if (num == VEHICULO_NO_EXISTE) {
					mensaje("Error", "El vehiculo no existe");
					break;
				}
				if (num == VEHICULO_EN_OF) {
					mensaje("En oficina", "El vehiculo esta estacionado en oficina");
					break;
				}
				if (num == VEHICULO_EN_REP) {
					mensaje("En reparto", "El vehiculo esta en reparto");
					break;
				}
				break;
			case OFI_MENOS_VEHI:
				o = seur.oficinaConMenosVehi();
				if (o == null) {
					mensaje("Error", "Error");
					break;
				}
				mensaje("Oficina " + o.numero(), "Con un total de " + o.numVehiculos());
				break;

			default:
				throw new AssertionError("Opcion no esperada");
			}
		}
	}

	private static void mensaje(String titulo, String txt) {
		Mensaje msj = new Mensaje(titulo);
		msj.escribe(txt);
	}

}

