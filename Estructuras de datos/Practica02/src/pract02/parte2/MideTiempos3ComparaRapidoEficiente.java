package pract02.parte2;

import java.util.Random;

import pract02.lista_array_noiter.*;
import pract02.medidas.Medida;
import pract02.medidas.OperacionesConMedidas;

/**
 * Programa para medir los tiempos de ejecucion de las distintas versiones
 * del metodo eliminaOcurrencias().
 * Compara eliminaOcurrenciasRapido() con eliminaOcurrenciasEficiente().
 * 
 * @author Estructuras de Datos
 * @version sep-2023
 */
public class MideTiempos3ComparaRapidoEficiente {

	/**
	 * Programa para medir tiempos de ejecucion.
	 * @param args argumentos del main (no usados).
	 */
	public static void main(String[] args) {
		final int NUM_REP_MEDIDA = 4; // veces que se realiza cada medida
		final int RANGO_NUMS = 10; // numeros en el rango 0..RANGO_NUMS-1
		final int TAMANHO_MAX_MOSTRAR = 10; // tamanho maximo de las listas a mostrar
		final int ELEM_ELIMINAR = 1; // elemento a eliminar en las listas
		long t0; // tiempo inicial (en ms)
		long t1; // tiempo intermedio 1 (en ms)
		long t2; // tiempo final (en ms)

		IListaNoIter<Integer> lista1 = null;		
		IListaNoIter<Integer> lista2 = null;

		// Tamanho de los ejemplares(valores de n) para los que se van
		// a realizar las medidas de tiempos
		final int[] tamanhoEjemplares =
			{10, 10_000, 25_000, 35_000, 45_000, 55_000, 65_000, 75_000};

		// Medidas de tiempos. Cada medida contiene el valor de n y el tiempo
		// que ha tardado en ejecutarse
		Medida[] medidas1 = new Medida[tamanhoEjemplares.length];
		Medida[] medidas2 = new Medida[tamanhoEjemplares.length];

		// realiza las medidas para los valores de n almacenados en el array tamanhoEjemplares
		for (int i = 0; i < tamanhoEjemplares.length; i++) {
			// crea tres listas iguales de numeros aleatorios en el rango [0,RANGO_NUMS-1]
			lista1 = creaListaAleatoria(tamanhoEjemplares[i], RANGO_NUMS, 0);
			lista2 = creaListaAleatoria(tamanhoEjemplares[i], RANGO_NUMS, 0);

			// Mide el tiempo de ejecucion de los dos metodos

			t0 = System.currentTimeMillis(); // instante inicial

			for (int j = 0; j < NUM_REP_MEDIDA; j++) {
				// algoritmo rapido
				EliminaOcurrencias.eliminaOcurrenciasRapido(lista1, ELEM_ELIMINAR);
			}

			t1 = System.currentTimeMillis(); // instante intermedio 1

			for (int j = 0; j < NUM_REP_MEDIDA; j++) {
				// algoritmo lento
				EliminaOcurrencias.eliminaOcurrenciasEficiente(lista2, ELEM_ELIMINAR);
			}

			t2 = System.currentTimeMillis(); // instante final

			// almacena las medidas realizadas
			medidas1[i] = 
					new Medida(tamanhoEjemplares[i], (double) (t1 - t0) / NUM_REP_MEDIDA);
			medidas2[i] = 
					new Medida(tamanhoEjemplares[i], (double) (t2 - t1) / NUM_REP_MEDIDA);

			System.out.printf("n=%7d", tamanhoEjemplares[i]);
			System.out.printf("  Algoritmo rapido:   %8.2fms %n", medidas1[i].ms());
			System.out.printf("           Algoritmo eficiente:%8.2fms %n", medidas2[i].ms());


			// muestra las listas (cuando el tamanho es pequenho)
			if (tamanhoEjemplares[i] <= TAMANHO_MAX_MOSTRAR) {
				System.out.println("Lista1:        " + lista1);
				System.out.println("Lista2:        " + lista2);
			}
			
			// comprueba que los metodos han obtenido el mismo valor
			if (!lista1.equals(lista2)) {
				System.out.println("Error: el algoritmo eficiente no funciona correctamente");
				System.exit(-1);
			}
		}

		// Muestra las medidas obtenidas en una grafica
		OperacionesConMedidas.dibuja2Graficas(medidas1, "metodo rapido",
				medidas2, "metodo eficiente");
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
