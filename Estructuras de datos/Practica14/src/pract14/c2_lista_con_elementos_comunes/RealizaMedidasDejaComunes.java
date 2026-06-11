package pract14.c2_lista_con_elementos_comunes;

import java.util.ArrayList;

import fundamentos.*;

/**
 * Programa para medir tiempos de ejecucion de las dos versiones del
 * algoritmo para dejar en una lista los elementos comunes a dos listas.
 * 
 * @author Estructuras de Datos (UC)
 * @version dic-2023
 */
public class RealizaMedidasDejaComunes {

	private static final int TAMANHO_LISTA = 40000;
	private static final int UMBRAL_MUESTRA_LISTAS = 15;

	/**
	 * Programa para medir tiempos de ejecucion.
	 * @param args argumentos del main (no usados)
	 */
	public static void main(String[] args) {
		long t0; // tiempos (en ms)
		long t1; 
		long t2;

		// genera listas de productos aleatorias
		ArrayList<Producto> listEfi = new ArrayList<>();
		ArrayList<Producto> listInefi = new ArrayList<>();
		ArrayList<Producto> list2 = new ArrayList<>();
		generaListas(listEfi, listInefi, list2, TAMANHO_LISTA);

		// Muestra las listas (si no son muy grades)
		if (listEfi.size() < UMBRAL_MUESTRA_LISTAS) {
			System.out.println("Lista1: " + listEfi + "\n" +
								"Lista2: " + list2);
		}

		// Mide el tiempo de ejecucion de las dos implementaciones

		t0 = System.currentTimeMillis(); // instante inicial

		// Mide tiempo para la implementacion eficiente 
		ProductosComunesEficiente.dejaElementosComunes(listEfi, list2);

		t1 = System.currentTimeMillis(); // instante intermedio

		// Mide tiempo para la implementacion ineficiente
		ProductosComunesIneficiente.dejaElementosComunes(listInefi, list2);
		// ProductosComunes.dejaElementosComunes2(listInefi, list2);

		t2 = System.currentTimeMillis(); // instante final

		// Muestra las listas (si no son muy grades)
		if (listEfi.size() < UMBRAL_MUESTRA_LISTAS) {
			System.out.println("ListEfi: " + listEfi + "\n" +
								"ListInefi: " + listInefi);
		}
		
		// Comprueba que los resultados coinciden
		if (!listEfi.equals(listInefi)) {
			mensaje("ERROR!", "Las listas de los dos algoritmos no coinciden");
		}

		// muestra tiempos
		String strMsj = "Tiempo algoritmo eficiente:" + (t1 - t0) + "ms\n";		
		strMsj = strMsj + "Tiempo algoritmo ineficiente:  " + (t2 - t1) + "ms\n";			
		mensaje("Tiempos", strMsj);
	}

	private static void generaListas(ArrayList<Producto> listEfi,
			ArrayList<Producto> listInefi, ArrayList<Producto> list2, int tamanhoLista) {
		final int ultimo_cod_producto = tamanhoLista;

		// crea la primera lista
		for (int i = 0; i < tamanhoLista; i++) {
			int codigo = (int) (Math.random() * ultimo_cod_producto);
			listEfi.add(new Producto(codigo, "Descripcion producto " + codigo));
		}

		// hace una copia
		listInefi.addAll(listEfi);

		// crea la segunda lista
		for (int i = 0; i < tamanhoLista / 2; i++) {
			int codigo = (int) (Math.random() * ultimo_cod_producto);
			list2.add(new Producto(codigo, "Descripcion producto " + codigo));
		}		
	}

	/**
	 * Metodo auxiliar que muestra un ventana de mensaje.
	 * @param titulo titulo de la ventana
	 * @param txt texto contenido en la ventana
	 */
	private static void mensaje(String titulo, String txt) {
		Mensaje msj = new Mensaje(titulo);
		msj.escribe(txt);

	}
}
