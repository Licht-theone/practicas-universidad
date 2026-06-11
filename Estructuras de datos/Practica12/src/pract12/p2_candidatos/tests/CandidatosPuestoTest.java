package pract12.p2_candidatos.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pract12.p2_candidatos.modelo.*;

/**
 * Test de la clase CandidatosPuesto.
 * 
 * @author Estructuras de Datos (UC)
 * @version nov-2024
 */
class CandidatosPuestoTest {

	@Test
	void constructorTest() {
		System.out.println("constructorTest");
		CandidatosPuesto candidatos = new CandidatosPuesto(4);

		Candidato candidato = candidatos.mejorValorado();
		System.out.println("  Mejor valorado (cola vacia): " + candidato);
		assertNull(candidatos.mejorValorado());
	}

	@Test
	void anhadeRepetidoTest() {
		System.out.println("anhadeRepetidoTest");
		CandidatosPuesto candidatos = new CandidatosPuesto(4);

		candidatos.anhadeCandidato(new Candidato("Pepe", 100));		
		candidatos.anhadeCandidato(new Candidato("Lola", 10));

		try {
			candidatos.anhadeCandidato(new Candidato("Pepe", 5));
			fail("Deberia haberse lanzado la excepcion");
		} catch (CandidatosPuesto.NombreCandidatoYaExistente e) {
			// Lo correcto es que se lance la excepcion
		}

		Candidato candidato = candidatos.mejorValorado();
		System.out.println("  Mejor valorado: " + candidato);

		// se puede volver a anhadir
		candidatos.anhadeCandidato(new Candidato("Pepe", 10));	
	}

	@Test
	void anhadeAlcanzaMaximoTest() {
		System.out.println("anhadeAlcanzaMaximoTest");
		CandidatosPuesto candidatos = new CandidatosPuesto(4);

		candidatos.anhadeCandidato(new Candidato("Pepe", 100));		
		candidatos.anhadeCandidato(new Candidato("Lola", 10));		
		candidatos.anhadeCandidato(new Candidato("Pepa", 200));		
		candidatos.anhadeCandidato(new Candidato("Lolo", 2));

		try {
			candidatos.anhadeCandidato(new Candidato("Otro", 5));
			fail("Deberia haberse lanzado la excepcion");
		} catch (CandidatosPuesto.AlcanzadoMaximoNumCandidatos e) {
			// Lo correcto es que se lance la excepcion
		}

		Candidato candidato = candidatos.mejorValorado();
		System.out.println("  Mejor valorado: " + candidato);		
		candidatos.anhadeCandidato(new Candidato("Lolo2", 2));

		try {
			candidatos.anhadeCandidato(new Candidato("Otro2", 5));
			fail("Deberia haberse lanzado la excepcion");
		} catch (CandidatosPuesto.AlcanzadoMaximoNumCandidatos e) {
			// Lo correcto es que se lance la excepcion
		}
	}

	@Test
	void anhadeDesencolaSimpleTest() {
		System.out.println("anhadeDesencolaSimpleTest");
		CandidatosPuesto candidatos = new CandidatosPuesto(6);

		Candidato[] lista = {new Candidato("Pepa", 200),
			new Candidato("Pepe", 100), 
			new Candidato("Lola", 10),
			new Candidato("Lolo", 2) };

		// anhade desordenados
		candidatos.anhadeCandidato(lista[2]);		
		candidatos.anhadeCandidato(lista[0]);		
		candidatos.anhadeCandidato(lista[3]);		
		candidatos.anhadeCandidato(lista[1]);
		
		// comprueba que los desencola en orden
		for (Candidato candidato: lista) {
			Candidato mejor = candidatos.mejorValorado();
			System.out.println("  Mejor valorado: " + mejor);
			assertEquals(candidato, mejor);
		}
	}
	
	@Test
	void anhadeDesencolaTest() {
		System.out.println("anhadeDesencolaSimpleTest");
		Candidato mejor;
		CandidatosPuesto candidatos = new CandidatosPuesto(8);

		Candidato[] lista = {new Candidato("Pepa", 200),
			new Candidato("Pepe", 100), 
			new Candidato("Lola", 10),
			new Candidato("Lolo", 2) };

		// anhade desordenados
		candidatos.anhadeCandidato(lista[2]);		
		candidatos.anhadeCandidato(lista[0]);		
		candidatos.anhadeCandidato(lista[3]);		
		candidatos.anhadeCandidato(lista[1]);

		mejor = candidatos.mejorValorado();
		System.out.println("  Mejor valorado: " + mejor);
		assertEquals(lista[0], mejor);
		mejor = candidatos.mejorValorado();
		System.out.println("  Mejor valorado: " + mejor);
		assertEquals(lista[1], mejor);
		
		// anhade otros 2
		Candidato candidato1 = new Candidato("Juana", 300);
		Candidato candidato2 = new Candidato("Jonh", 1);
		candidatos.anhadeCandidato(candidato2);
		candidatos.anhadeCandidato(candidato1);
		
		// desencola
		mejor = candidatos.mejorValorado();
		System.out.println("  Mejor valorado: " + mejor);
		assertEquals(candidato1, mejor);
		mejor = candidatos.mejorValorado();
		System.out.println("  Mejor valorado: " + mejor);		
		assertEquals(lista[2], mejor);	
		mejor = candidatos.mejorValorado();
		System.out.println("  Mejor valorado: " + mejor);	
		assertEquals(lista[3], mejor);
		mejor = candidatos.mejorValorado();
		System.out.println("  Mejor valorado: " + mejor);		
		assertEquals(candidato2, mejor);
		
		// comprueba que esta vacia
		mejor = candidatos.mejorValorado();
		System.out.println("  Mejor valorado (cola vacia): " + mejor);
		assertNull(mejor);
	}
		
}
