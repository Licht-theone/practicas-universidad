package c2_es_ascendiente_en_heap;

/**
 * Prueba basica del metodo esAscendientePropio().
 * Este programa se proporciona como ayuda para el desarrollo del examen.
 * El alumno puede modificar esta clase en la forma que desee.
 * 
 * @author  Metodos de Programacion (UC) y <TODO: nombre alumno>
 * @version ene-23
 */
public class PruebaBasica {

	/**
	 * Programa sencillo de prueba del metodo esAscendientePropio().
	 * @param args argumentos de la aplicacion (no utilizados).
	 */
	public static void main(String[] args) {
		ColaPrioMonticulo<Integer> cola = new ColaPrioMonticulo<>(20);
		
		cola.encolaConPrioridad(7);		
		cola.encolaConPrioridad(3);		
		cola.encolaConPrioridad(2);		
		cola.encolaConPrioridad(5);		
		cola.encolaConPrioridad(9);		
		cola.encolaConPrioridad(8);		
		cola.encolaConPrioridad(1);		
		cola.encolaConPrioridad(6);
		//          1
		//     5         2
		//   6   9     8   3 
		// 7
		System.out.println("Cola:" + cola);

		// prueba varias parejas de elementos para ver si uno es ascendiente propio del otro
		final int[][] parejas =
			{{1, 1}, {7, 6}, {7, 1}, {7, 2}, {7, 9}, {9, 5}, {3, 1}, {1, 2}};
		for (int i = 0; i < parejas.length; i++) {
			System.out.println(parejas[i][1] + " es ascendiente propio de " +
					parejas[i][0] + " " +
					cola.esAscendientePropio(parejas[i][0], parejas[i][1]));
		}
		
		// TODO (opcional, se valora como un extra): ampliar el programa para probar otros casos

	}

}
