package modelo;

import fundamentos.*;

public class Agenda {
	//static atributo de la clase no de cada objeto
	private static final int MAX_CONTACTOS = 10;
	//declaracion del array
	private Contacto[] contactos;
	private int numContactos = 0;
	//metodos
	//constructor
	public Agenda() {
		contactos = new Contacto[MAX_CONTACTOS];
	}
	public Agenda(int numContactos) {
		contactos = new Contacto[MAX_CONTACTOS];
		for (int i = 0; i < numContactos; i++) {
			contactos[i] = new Contacto("Contacto"+i, "666777888"+i, "empleado"+i+"@macaya.es");
		}
		//num de elementos q mete en el array o la posicion donde voy a meter el prox elemento
		this.numContactos = numContactos;
	}
	public void atexto() {
		System.out.println("tienes:"+numContactos+" contactos en la agenda");
		for (int i = 0; i < numContactos; i++) {
			System.out.println(contactos[i]);
		}
	}
}
