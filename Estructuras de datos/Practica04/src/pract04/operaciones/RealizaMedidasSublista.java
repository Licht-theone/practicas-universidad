package pract04.operaciones;

import pract04.ilista.ILista;
import pract04.lista_array.ListaArray;
import pract04.lista_doble_enlace.ListaDobleEnlace;
import pract04.operaciones.medidas.Medida;
import pract04.operaciones.medidas.OperacionesConMedidas;


/**
 * Programa para medir tiempos de ejecucion del metodo
 * sublistaPosiciones  de la clase OperacionesConListas.
 * 
 * @author Estructuras de Datos (UC)
 * @version sep-23
 */
public class RealizaMedidasSublista {

	/**
	 * Programa para medir tiempos de ejecucion.
	 * @param args argumentos del main (no usados)
	 */
	public static void main(String[] args) {
		long t0; // tiempo inicial (en ms)
		long t1; // tiempo intermedio (en ms)
		long t2; // tiempo final (en ms)
		
		// listas sobre las que realizar las medidas
		ListaArray<String> listaArray = new ListaArray<String>();
		ListaDobleEnlace<String> listaDobleEnlace = new ListaDobleEnlace<String>();
		
		// elemento a meter en las listas
		final String str = "Hola";

		// Tamanho de los ejemplares (valores de n) para los que se van
		// a realizar las medidas de tiempos
		final int[] tamanhoEjemplares = {10000, 10000, 15000, 20000, 25000, 30000, 35000};

		// Medidas de tiempos. Cada medida contiene el valor de n y el tiempo
		// que ha tardado en ejecutarse
		Medida[] medidas1 = new Medida[tamanhoEjemplares.length - 1];
		Medida[] medidas2 = new Medida[tamanhoEjemplares.length - 1];

		// realiza las medidas para los valores de n almacenados en el array tamanhoEjemplares
		for (int i = 0; i < tamanhoEjemplares.length; i++) {
			// crea las listas
			creaListaConEleRepetido(listaArray, tamanhoEjemplares[i], str);
			creaListaConEleRepetido(listaDobleEnlace, tamanhoEjemplares[i], str);
			// crea el array con tantas posiciones como elementos en la
			// lista para crear la situacion de peor caso
			int[] posiciones = new int[tamanhoEjemplares[i]];
			for (int j = 0; j < tamanhoEjemplares[i]; j++) {
				posiciones[j] = tamanhoEjemplares[i] - j - 1;
			}

			// Mide el tiempo de ejecucion de los dos metodos

			t0 = System.currentTimeMillis(); // instante inicial

			// Mide tiempo para la listaArray
			ListaArray<String> subLista1 =
					OperacionesConListas.sublistaPosiciones(listaArray, posiciones);

			t1 = System.currentTimeMillis(); // instante intermedio

			// Mide tiempo para la listaDobleEnlace
			ListaArray<String> subLista2 =
					OperacionesConListas.sublistaPosiciones(listaDobleEnlace, posiciones);

			t2 = System.currentTimeMillis(); // instante final

			// almacena las medidas realizadas (La primera medida se descarta)
			if (i > 0) {
				medidas1[i - 1] = 
						new Medida(tamanhoEjemplares[i], (double) (t1 - t0));
				medidas2[i - 1] = 
						new Medida(tamanhoEjemplares[i], (double) (t2 - t1));
			}

			// comprueba que el tamanho de las sublistas es correcto
			System.out.println("Tamanho de la subLista1:" + subLista1.tamanho());
			System.out.println("Tamanho de la subLista2:" + subLista2.tamanho());
			if (subLista2.tamanho() != tamanhoEjemplares[i]) {
				System.out.println("Error: tamanho de la subLista1 incorrecto");
			}
			if (subLista2.tamanho() != tamanhoEjemplares[i]) {
				System.out.println("Error: tamanho de la subLista2 incorrecto");
			}
		}

		// Muestra las medidas obtenidas en una grafica
		OperacionesConMedidas.dibujaGraficas(medidas1, "listaArray",
				medidas2, "listaDobleEnlace");
	}

	/**
	 * Vacia la lista y la anhade el elemento indicado el numero de veces
	 * pasado como parametro.
	 * @param <E> tipo de los elementos almacenados en la lista
	 * @param lista lista a modificar
	 * @param tamanho tamanho de la lista modificada
	 * @param elemento elemento a anhadir a la lista las veces indicadas por 'tamanho'
	 */
	public static <E> void creaListaConEleRepetido(ILista<E> lista, int tamanho, E elemento) {
		lista.haceVacia();
		for (int i = 0; i < tamanho; i++) {
			lista.anhade(lista.tamanho(), elemento);
		}
	}
}
