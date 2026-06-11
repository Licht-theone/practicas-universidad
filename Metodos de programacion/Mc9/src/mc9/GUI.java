package mc9;

import fundamentos.*;

public class GUI {
	public static final int BUSCA_MESA = 0;
	public static final int ANHADE_CONSUMICION = 1;
	public static final int COBRA_MESA = 2;
	public static final int MESA_CON_CONS = 3;

	public static void main(String[] args) {
		String desc;
		int numMesa;
		double importe;
		Lectura lect;
		Restaurante cielo = new Restaurante();
		Menu menu = new Menu("Restaurante");
		menu.insertaOpcion("Busca mesa libre", BUSCA_MESA);
		menu.insertaOpcion("Añade consumicion", ANHADE_CONSUMICION);
		menu.insertaOpcion("Cobra mesa", COBRA_MESA);
		menu.insertaOpcion("Mesas con consumicion", MESA_CON_CONS);
		int opcion;
		while (true) {
			opcion = menu.leeOpcion();
			switch (opcion) {
			case BUSCA_MESA:
				int i = cielo.buscaMesaLibre();
				if (i == -1) {
					mensaje("Completo", "No hay ninguna mesa libre");
					break;
				}
				mensaje("Mesa libre", "La mesa " + i + " esta libre");
				break;
			case ANHADE_CONSUMICION:
				lect = new Lectura("Añadir consumicion a mesa");
				lect.creaEntrada("Numero de mesa", "0");
				lect.creaEntrada("Descripcion", "Cerveza");
				lect.creaEntrada("Importe", "5.0");
				lect.esperaYCierra();
				numMesa = lect.leeInt("Numero de mesa");
				desc = lect.leeString("Descripcion");
				importe = lect.leeDouble("Importe");
				Consumicion c = new Consumicion(desc, importe);
				if (!cielo.anotaConsumicion(c, numMesa)) {
					mensaje("Error", "No se pudo añadir");
					break;
				}
				mensaje("Añadido con exito", "Se añadio la consumicion");
				break;
			case COBRA_MESA:
				lect = new Lectura("Cobrar mesa");
				lect.creaEntrada("Numero de mesa", "0");
				lect.esperaYCierra();
				numMesa = lect.leeInt("Numero de mesa");
				importe = cielo.cobraMesa(numMesa);
				if (importe == -1) {
					mensaje("ERROR", "No se pudo cobrar");
					break;
				}
				mensaje("Mesa cobrada", "El total de la mesa " + numMesa + " fue de " + importe + "€");
				break;
			case MESA_CON_CONS:
				lect = new Lectura("Mesas con consumicion");
				lect.creaEntrada("Descripcion", "Cerveza");
				lect.esperaYCierra();
				desc = lect.leeString("Descripcion");
				int num = cielo.mesasConConsumicion(desc);
				if (num == -1) {
					mensaje("No se encontro", "No se encontro ninguna mesa con esa consumicion");
					break;
				}
				mensaje("Exito", "Hay " + num + " mesas con esa consumicion");
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
