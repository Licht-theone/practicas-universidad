package pract14.c1_cuenta_repeticiones;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Contabiliza las veces que se repite cada una de las palabras existentes
 * en un fichero de texto.
 * Utiliza una implementacion ineficiente basada en una lista.
 * 
 * @author Estructuras de Datos (UC)
 * @version dic-2016
 */
public class CuentaRepeticionesIneficiente {

	private ArrayList<RepeticionesPalabra> cuentaRepeticiones = new ArrayList<>();

	/**
	 * Contabiliza las veces que se repite cada una de las palabras existentes
	 * en un fichero de texto y lo almacena en una estructura de datos interna para
	 * su posterior consulta con el metodo repeticiones.
	 * @param nomFich nombre del fichero en el que contabilizar las palabras.
	 * @throws FileNotFoundException si no existe el fichero.
	 */
	//O(n)
	public CuentaRepeticionesIneficiente(String nomFich) throws FileNotFoundException {
		String str = "";
		try (Scanner in = new Scanner(new FileReader(nomFich))) {

			while (in.hasNext()) {
				str = in.next();

				// convierte a minusculas
				str = str.toLowerCase();

				// elimina los caracteres que no sean letras
				str = str.replaceAll("[^a-zñáéíóúü]", "");

				// acumula ocurrencia
				if (!str.equals("")) {
					int pos = cuentaRepeticiones.indexOf(new RepeticionesPalabra(str));
					if (pos == -1) {
						cuentaRepeticiones.add(new RepeticionesPalabra(str));
					} else {
						cuentaRepeticiones.get(pos).sumaRepeticion();
					}
				}
			}
		}
	}

	/**
	 * Retorna las veces que se repite la palabra en el fichero que fue
	 * pasada como parametro al constructor.
	 * @param palabra palabra de la que se quiere saber cuantas veces se
	 * encuentra repetida en el fichero.
	 * @return numero de repeticiones de la palabra en el fichero.
	 */
	//O(n)
	public int repeticionesPalabra(String palabra) {
		int pos = cuentaRepeticiones.indexOf(new RepeticionesPalabra(palabra));
		if (pos == -1) {
			return 0;
		} else {
			return cuentaRepeticiones.get(pos).repeticiones();
		}
	}
}
