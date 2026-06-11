package c1_elimina_valores_repetidos_mapa;

/**
 * Prueba basica del metodo eliminaEntradasConValorRepetido().
 * Este programa se proporciona como ayuda para el desarrollo del examen.
 * El alumno puede modificar esta clase en la forma que desee.
 * 
 * @author  Metodos de Programacion (UC) y <TODO: nombre alumno>
 * @version ene-23
 */
public class PruebaBasica {

	/**
	 * Programa sencillo de prueba del metodo eliminaEntradasConValorRepetido().
	 * @param args argumentos de la aplicacion (no utilizados).
	 */
	public static void main(String[] args) {
		MapaDispersionAbierta<Integer, String> mapa = new MapaDispersionAbierta<>(8);
		
		for (int i = 0; i < 5; i++) {
			// anhade dos entradas con el mismo valor y con llaves i e i+7
			mapa.anhade(i, "V" + i);
			mapa.anhade(i + 7, "V" + i);
		}
		System.out.println("Mapa inicial:" + mapa);
		
		mapa.eliminaEntradasConValorRepetido();
		System.out.println("Mapa final:  " + mapa);
		
		// TODO (opcional, se valora como un extra): ampliar el programa para probar otros casos
	}

}
