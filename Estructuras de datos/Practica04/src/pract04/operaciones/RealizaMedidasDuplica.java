package pract04.operaciones;

import pract04.ilista.ILista;
import pract04.lista_array.ListaArray;
import pract04.lista_doble_enlace.ListaDobleEnlace;
import pract04.operaciones.medidas.Medida;
import pract04.operaciones.medidas.OperacionesConMedidas;


/**
 * Programa para medir tiempos de ejecucion del metodo
 * duplicaOcurrenciasElemento de la clase OperacionesConListas.
 * 
 * @author Estructuras de Datos (UC)
 * @version sep-21
 */
public class RealizaMedidasDuplica {

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
		final int[] tamanhoEjemplares = {3000, 6000, 9000, 12000, 15000, 18000};

		// Medidas de tiempos. Cada medida contiene el valor de n y el tiempo
		// que ha tardado en ejecutarse
		Medida[] medidas1 = new Medida[tamanhoEjemplares.length - 1];
		Medida[] medidas2 = new Medida[tamanhoEjemplares.length - 1];

		// realiza las medidas para los valores de n almacenados en el array tamanhoEjemplares
		for (int i = 0; i < tamanhoEjemplares.length; i++) {
			// crea las listas con todos los elementos iguales al que se va a duplicar
			// para crear la situacion de peor caso
			creaListaConEleRepetido(listaArray, tamanhoEjemplares[i], str);
			creaListaConEleRepetido(listaDobleEnlace, tamanhoEjemplares[i], str);

			// Mide el tiempo de ejecucion de los dos metodos

			t0 = System.currentTimeMillis(); // instante inicial

			// Mide tiempo para la listaArray
			OperacionesConListas.duplicaOcurrenciasElemento(listaArray, str);

			t1 = System.currentTimeMillis(); // instante intermedio

			// Mide tiempo para la listaDobleEnlace
			OperacionesConListas.duplicaOcurrenciasElemento(listaDobleEnlace, str);

			t2 = System.currentTimeMillis(); // instante final

			// almacena las medidas realizadas (La primera medida se descarta)
			if (i > 0) {
				medidas1[i - 1] = 
						new Medida(tamanhoEjemplares[i], (double) (t1 - t0));
				medidas2[i - 1] = 
						new Medida(tamanhoEjemplares[i], (double) (t2 - t1));
			}

			// comprueba que se ha duplicado el tamanho de las listas
			System.out.println("Tamanho de la listaArray:" + listaArray.tamanho());
			System.out.println("Tamanho de la listaDobleEnlace:" + listaDobleEnlace.tamanho());
			if (listaArray.tamanho() != tamanhoEjemplares[i] * 2) {
				System.out.println("Error: no se ha duplicado el tamanho de la listaArray");
			}
			if (listaDobleEnlace.tamanho() != tamanhoEjemplares[i] * 2) {
				System.out.println("Error: no se ha duplicado el tamanho de la listaDobleEnlace");
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
