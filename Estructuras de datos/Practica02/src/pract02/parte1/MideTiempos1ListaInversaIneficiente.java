package pract02.parte1;

import java.util.Random;

import pract02.lista_array_noiter.*;
import pract02.medidas.Medida;
import pract02.medidas.OperacionesConMedidas;

/**
 * Programa para medir los tiempos de ejecucion de listaInversaIneficiente().
 * 
 * @author Estructuras de Datos (UC)
 * @version sep-2024
 */
public class MideTiempos1ListaInversaIneficiente {

	/**
	 * Programa para medir tiempos de ejecucion.
	 * @param args argumentos del main (no usados).
	 */
	public static void main(String[] args) {
		final int NUM_REP_MEDIDA = 1; // veces que se realiza cada medida
		final int RANGO_NUMS = 10; // numeros en el rango 0..RANGO_NUMS-1
		final int TAMANHO_MAX_MOSTRAR = 10; // tamanho maximo de las listas a mostrar
		long t0; // tiempo inicial (en ms)
		long t1; // tiempo intermedio (en ms)
		
		IListaNoIter<Integer> listaOriginal;		
		IListaNoIter<Integer> lista1 = null;
		
		// Tamanho de los ejemplares(valores de n) para los que se van
		// a realizar las medidas de tiempos
		final int[] tamanhoEjemplares = {10, 5_000, 10_000, 15_000, 20_000, 25_000, 30_000};
		
		// Medidas de tiempos. Cada medida contiene el valor de n y el tiempo
		// que ha tardado en ejecutarse
		Medida[] medidas1 = new Medida[tamanhoEjemplares.length];
		
		// realiza las medidas para los valores de n almacenados en el array tamanhoEjemplares
		for (int i = 0; i < tamanhoEjemplares.length; i++) {
			// crea una lista de tamanho n con numeros aleatorios en el rango [0,RANGO_NUMS-1]
			listaOriginal = creaListaAleatoria(tamanhoEjemplares[i], RANGO_NUMS, 0);
			
			// Mide el tiempo de ejecucion del metodo
			
			t0 = System.currentTimeMillis(); // instante inicial
			
			for (int j = 0; j < NUM_REP_MEDIDA; j++) {
				lista1 = ListaInversa.listaInversaIneficiente(listaOriginal);
			}
			
			t1 = System.currentTimeMillis(); // instante final
			
			// almacena las medidas realizadas
			medidas1[i] = 
				new Medida(tamanhoEjemplares[i], (double) (t1 - t0) / NUM_REP_MEDIDA);
			
			System.out.printf("n=%7d", tamanhoEjemplares[i]);
			System.out.printf("  Algoritmo ineficiente:  %8.2fms %n", medidas1[i].ms());
			
			// muestra las listas (cuando el tamanho es pequenho)
			if (tamanhoEjemplares[i] <= TAMANHO_MAX_MOSTRAR) {
				System.out.println("Lista original:" + listaOriginal);
				System.out.println("Lista inversa: " + lista1);
			}
		}
		
		// Muestra las medidas obtenidas en una grafica
		OperacionesConMedidas.dibujaGrafica(medidas1, "listaInversaIneficiente");
	}
	
	/**
	 * Crea un lista del tamanho indicado formada por numeros enteros
	 * aleatorios de valores comprendidos entre 0..rango-1.
	 * @param tamanho tamanho de la lista a crear.
	 * @param rango rango de los numeros aleatorios.
	 * @param semilla semilla para el generador de numeros aleatorios
	 * @return lista aleatoria generada.
	 */
	private static ListaArrayNoIter<Integer> creaListaAleatoria(
			int tamanho, int rango, int semilla) {
		ListaArrayNoIter<Integer> lista = new ListaArrayNoIter<Integer>(tamanho);
		Random rand = new Random(semilla);

		for (int i = 0; i < tamanho; i++) {
			lista.anhade(lista.tamanho(), rand.nextInt(rango));
		}

		return lista;
	}
}
