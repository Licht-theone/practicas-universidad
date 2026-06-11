package mc21;

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
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Ciudad {
	private ArrayList<Persona> lista = new ArrayList<Persona>();

	public void anhadePersona(Persona p) {
		lista.add(p);
	}

	@Override
	public String toString() {
		return lista.toString();
	}

	public void leeFicheroTexto(String nomFich) throws FileNotFoundException {
		try (Scanner in = new Scanner(new FileReader(nomFich))) {
			// configura el formato de números
			in.useLocale(Locale.ENGLISH);
			// lee hasta fin de fichero o primer numero
			while (in.hasNext()) {
				String nombre = in.next();
				while (!in.hasNextInt()) {
					nombre += " " + in.next();
				}
				int edad = in.nextInt();
				Persona p = new Persona(nombre, edad);
				anhadePersona(p);
			}
		}
	}
	public void escribirFicheroTexto(String nombreFichero) throws IOException {
		try (PrintWriter out = new PrintWriter(new FileWriter(nombreFichero))) {
			out.printf("%s", toString());
		}
	}

	public void grabaAFichero(String nomFich)throws IOException, ClassNotFoundException {
		try (ObjectOutputStream sal = new ObjectOutputStream( new FileOutputStream(nomFich))) {
			// un for y cada variable de persona es un writeobject
			sal.writeObject(this);
		}
	}
	
	public void leeDeFichero(String nomFich) throws IOException, ClassNotFoundException {
		try (ObjectInputStream ent = new ObjectInputStream( new FileInputStream(nomFich))) {
			while(true){
				int edad = ent.readInt();
				String nombre = (String) ent.readObject(); //cast al tipo de objeto
				Persona p = new Persona(nombre, edad);
				lista.add(p);
			}
		}
		catch(EOFException e){
		}
	}
}
