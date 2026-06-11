package ficheros;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;

import mc21.Persona;

public class Ficheros {
	public void escribirFichero(String nombreFichero) throws IOException {
		try (PrintWriter out = new PrintWriter(new FileWriter(nombreFichero))) {
			for (int i = 0; i < 3; i++) {
				out.printf("%-10s %6.2f %s %n", "s", 1000, "€");
			}
		}
	}

	public void leerFichero(String nomFich) throws FileNotFoundException {

		try (Scanner in = new Scanner(new FileReader(nomFich))) {
			// configura el formato de números
			in.useLocale(Locale.ENGLISH);
			// lee hasta fin de fichero o primer numero
			while (in.hasNext()) {
				String tipo=in.next();
				String mat=in.next();
			}
		}
	}

	public void grabaAFichero(String nomFich)throws IOException {
		try (ObjectOutputStream sal = new ObjectOutputStream( new FileOutputStream(nomFich))) {
			// graba el objeto actual
			sal.writeObject(this);
		}
	}

	public static Persona leeDeFichero(String nomFich) throws IOException, ClassNotFoundException {
		try (ObjectInputStream ent = new ObjectInputStream( new FileInputStream(nomFich))) {
			while(true){
				(Persona)ent.readObject();
			}
			return p;
		}
		catch(EOFException e){
		}
	}
}



