package mc17;

import fundamentos.*;

/**
 * Gestion de los socios y cursos de un gimnasio
 * @author MP
 * @version feb-13
 */
public class GestionGimnasio {

	/**
	 * Programa principal basado en menu
	 */
	public static void main(String[] args) {
		// opciones del menu
		final int ALTA_SOCIO = 0, ALTA_CURSO = 1, ANADE_SOCIO_CURSO = 2,
		INFORME_SOCIOS = 3;

		// variables auxiliares
		String dni;
		String nombre;
		Lectura lect;
		
		// crea el gimnasio
		Gimnasio g=new Gimnasio();
		
		// crea la ventana de menu
		Menu menu = new Menu("Gestion Gimnasio");
		menu.insertaOpcion("Nuevo Socio", ALTA_SOCIO);
		menu.insertaOpcion("Nuevo Curso", ALTA_CURSO);
		menu.insertaOpcion("Anyade socio a curso", ANADE_SOCIO_CURSO);
		menu.insertaOpcion("Informe socios", INFORME_SOCIOS);
		int opcion;

		// lazo de espera de comandos del usuario
		while(true) {
			opcion = menu.leeOpcion();

			// realiza las acciones dependiendo de la opcion elegida
			switch (opcion) {
			case  ALTA_SOCIO:
				lect = new Lectura("Datos Socio");
				lect.creaEntrada("Tipo Socio" , "N , J , I");
				lect.creaEntrada("Nombre", "");
				lect.creaEntrada("DNI", "");
				lect.creaEntrada("DNI Adulto asociado (Solo infantil)", "");
				lect.esperaYCierra();
				String tipo = lect.leeString("Tipo Socio");
				nombre = lect.leeString("Nombre");
				dni = lect.leeString("DNI");
				String dniPadre = lect.leeString("DNI Adulto asociado (Solo infantil)");
				Socio s=null;
				if(tipo.equals("I")){
					Socio p=g.buscaPorDni(dniPadre);
					s=new SocioInfantil(nombre,dni,p);
				}
				else{
					if(tipo.equals("J")){
						s=new SocioJubilado(nombre,dni);
					}
					else{
						s=new SocioNormal(nombre,dni);
						
					}
				}
				if(g.anhadeSocio(s)==false){
					mensaje("ERROR","Socio no a�adido");
				}
				
				
				break;

			case ALTA_CURSO:
				lect = new Lectura("Datos curso");
				lect.creaEntrada("Nombre", "");
				lect.creaEntrada("Descripcion", "");
				lect.creaEntrada("Max Alumnos", 0);
				lect.creaEntrada("Precio", 0.0);
				lect.esperaYCierra();
				nombre = lect.leeString("Nombre");
				int maxAlumnos = lect.leeInt("Max Alumnos");
				double precio = lect.leeDouble("Precio");
				
				// Anyade el nuevo curso
				Curso c=new Curso(precio,nombre,maxAlumnos);
				if(g.anhadeCurso(c)==false){
					mensaje("ERROR","Curso no anhadido correctamente");
				}
				break;

			case ANADE_SOCIO_CURSO:
				lect = new Lectura("Datos socio curso");
				lect.creaEntrada("DNI Socio", "");
				lect.creaEntrada("Nombre curso", "");
				lect.esperaYCierra();
				dni = lect.leeString("DNI Socio");
				nombre = lect.leeString("Nombre curso");
				
				//Anyade el socio al curso
				if(g.anhadeCursoASocio(dni,nombre)==false){
					mensaje("Error","Socio no anhadido a curso");
					
				}
				break;

			case INFORME_SOCIOS:
				// Genera el informe de socios
				String s1=g.informeSocios();
				mensaje("LISTADO",s1);
				
				break;
			}
				
		}
	}

	/**
	 * Metodo auxiliar que muestra un ventana de mensaje
	 * @param titulo titulo de la ventana
	 * @param txt texto contenido en la ventana
	 */
	private static void mensaje(String titulo, String txt) {
		Mensaje msj = new Mensaje(titulo);
		msj.escribe(txt);

	}

}