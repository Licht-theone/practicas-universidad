package c3_entrega_regalos;

import java.util.Collection;

/**
 * Prueba basica de la clase RepartoRegalos.
 * Este programa se proporciona como ayuda para el desarrollo del examen.
 * El alumno puede modificar esta clase en la forma que desee.
 * 
 * @author  Metodos de Programacion (UC) y <TODO: nombre alumno>
 * @version ene-23
 */
public class PruebaBasicaRegalos {

	/**
	 * Programa sencillo de prueba de la clase RepartoRegalos.
	 * @param args argumentos de la aplicacion (no utilizados).
	 */
	public static void main(String[] args) {
		RepartoRegalos reparto = new RepartoRegalos();
		Socio socio;
		
		// inscribe 3 socios
		reparto.inscribeSocio(new Socio(3));
		reparto.inscribeSocio(new Socio(2));
		reparto.inscribeSocio(new Socio(6));
		
		// Registra 2 regalos
		reparto.registraRegalo(new Regalo("Barato_10", 10));
		reparto.registraRegalo(new Regalo("Caro_100", 100));
		
		// asigna el regalo mas caro al primer socio que se inscribio
		socio = reparto.asignaRegalo();
		System.out.println("Socio num: " + socio.numSocio() +
				" regalo:" + reparto.regaloDeSocio(socio.numSocio()).descripcion());
		
		// inscribe dos nuevos socios y registra un nuevo regalo
		reparto.inscribeSocio(new Socio(1));
		reparto.inscribeSocio(new Socio(4));
		reparto.registraRegalo(new Regalo("Caro_90", 90));
		
		// asigna el regalo mas caro de los que quedan al segundo socio que se inscribio
		socio = reparto.asignaRegalo();
		System.out.println("Socio num: " + socio.numSocio() +
				" regalo:" + reparto.regaloDeSocio(socio.numSocio()).descripcion());
		
		// muestra los socios sin regalo (ordenados de menor a mayor numero de socio)
		Collection<Socio> sociosSinRegalo = reparto.sociosSinRegalo();
		System.out.println("Socios sin regalo:");
		for (Socio s: sociosSinRegalo) {
			System.out.println("  " + s.numSocio()); 
		}
				
		// TODO (opcional, se valora como un extra): ampliar el programa para probar otros casos

	}

}
