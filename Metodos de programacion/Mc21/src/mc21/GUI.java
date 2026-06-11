package mc21;

import java.io.FileNotFoundException;
import java.io.IOException;

public class GUI {

	public static void main(String[] args) {
		Ciudad c = new Ciudad();
		try {
			c.leeFicheroTexto("santander.txt");
		} catch (FileNotFoundException e) {
			System.exit(-1);
		}
		System.out.println(c.toString());
		try {
			c.escribirFicheroTexto("salida.txt");
		} catch (IOException e) {
			System.exit(-1);
		}	
	}
}
