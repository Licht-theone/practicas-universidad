package pract04.operaciones.medidas;

import java.io.*;

import fundamentos.*;

/**
 * Clase que agrupa operaciones de visualizacion grafica y almacenamiento
 * en ficheros a realizar sobre arrays de medidas.
 * 
 * @author Estructuras de Datos (UC)
 * @version sep-2021
 */
public class OperacionesConMedidas {

	/**
	 * Dibuja la grafica correspondiente a las medidas en una
	 * ventana Grafica del paquete fundamentos.
	 * @param medidas medidas a representar graficamente
	 */
	public static void dibujaGrafica(Medida[] medidas) {
		Grafica g = new Grafica("Tiempos de ejecución", "n", "t (ms)");

		g.ponSimbolo(true);
		g.ponColor(Grafica.azul);
		for (int i = 0; i < medidas.length; i++) {
			g.inserta(medidas[i].tamanho(), medidas[i].ms());
		}

		g.pinta();
	}

	/**
	 * Dibuja las graficas correspondiente a dos conjuntos de medidas en una
	 * ventana Grafica del paquete fundamentos.
	 * @param medidas1 primer conjunto de medidas
	 * @param nombre1 nombre del primer conjunto de medidas
	 * @param medidas2 segundo conjunto de medidas
	 * @param nombre2 nombre del segundo conjunto de medidas
	 */
	public static void dibujaGraficas(Medida[] medidas1, String nombre1,
			Medida[] medidas2, String nombre2) {
		Grafica g = new Grafica("Tiempos de ejecución", "n", "t (ms)");

		g.ponSimbolo(true);
		g.ponColor(Grafica.azul);
		g.ponTitulo(nombre1);
		for (int i = 0; i < medidas1.length; i++) {
			g.inserta(medidas1[i].tamanho(), medidas1[i].ms());
		}
		
		g.otraGrafica();
		g.ponSimbolo(true);
		g.ponColor(Grafica.rojo);
		g.ponTitulo(nombre2);
		for (int i = 0; i < medidas2.length; i++) {
			g.inserta(medidas2[i].tamanho(), medidas2[i].ms());
		}

		g.pinta();
	}

	/**
	 * Dibuja las graficas correspondiente a tres conjuntos de medidas en una
	 * ventana Grafica del paquete fundamentos.
	 * @param medidas1 primer conjunto de medidas
	 * @param nombre1 nombre del primer conjunto de medidas
	 * @param medidas2 segundo conjunto de medidas
	 * @param nombre2 nombre del segundo conjunto de medidas
	 * @param medidas3 tercer conjunto de medidas
	 * @param nombre3 nombre del tercer conjunto de medidas
	 */
	public static void dibuja3Graficas(Medida[] medidas1, String nombre1,
			Medida[] medidas2, String nombre2,
			Medida[] medidas3, String nombre3) {
		Grafica g = new Grafica("Tiempos de ejecución", "n", "t (ms)");

		g.ponSimbolo(true);
		g.ponColor(Grafica.azul);
		g.ponTitulo(nombre1);
		for (int i = 0; i < medidas1.length; i++) {
			g.inserta(medidas1[i].tamanho(), medidas1[i].ms());
		}
		
		g.otraGrafica();
		g.ponSimbolo(true);
		g.ponColor(Grafica.rojo);
		g.ponTitulo(nombre2);
		for (int i = 0; i < medidas2.length; i++) {
			g.inserta(medidas2[i].tamanho(), medidas2[i].ms());
		}
		
		g.otraGrafica();
		g.ponSimbolo(true);
		g.ponColor(Grafica.rosa);
		g.ponTitulo(nombre3);
		for (int i = 0; i < medidas3.length; i++) {
			g.inserta(medidas3[i].tamanho(), medidas3[i].ms());
		}

		g.pinta();
	}	

	/**
	 * Graba las medidas en el fichero de texto con el nombre indicado.
	 * Cada medida ocupa una linea del fichero con sus componentes (n y ms)
	 * separados por uno o mas espacios.
	 * @param nomFich nombre del fichero en el que grabar las medidas
	 * @param medidas array de medidas a grabar
	 * @throws IOException si se produce algun error accediendo al fichero
	 */
	public static void grabaFichero(String nomFich, Medida[] medidas) throws IOException  {
		try (PrintWriter out = new PrintWriter(new FileWriter(nomFich))) {

			// escribe las medidas
			for (Medida m: medidas) {
				out.printf("%12d %5d%n", m.tamanho(), m.ms());
			}
		}
	}
}
