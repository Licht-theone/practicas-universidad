package pract05.tests;

import static org.junit.Assert.assertTrue;


import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pract05.modelo.CadenaTiendas;
import pract05.modelo.Compra;
import pract05.modelo.Tarjeta;

/**
 * Test de la clase CadenaTiendas.
 * 
 * @author  Metodos de Programacion (UC)
 * @version oct-22
 */
class CadenaTiendasTest {

	@Test
	public void testAnhadeTarjeta() {
		System.out.println("testAnhadeTarjeta");

		CadenaTiendas cadenaTiendas = new CadenaTiendas();

		// comprueba que anhade correctamente tarjetas
		cadenaTiendas.anhadeTarjeta(new Tarjeta("12345678A"));
		cadenaTiendas.anhadeTarjeta(new Tarjeta("22334455A"));

		// comprueba que anhade tarjeta con DNI repetido
		cadenaTiendas.anhadeTarjeta(new Tarjeta("12345678A"));
	}

	@Test
	public void testRegistraCompraSimple() {
		boolean ret;
		System.out.println("testRegistraCompraSimple");

		CadenaTiendas cadenaTiendas = new CadenaTiendas();

		// ahnade varias tarjetas

		cadenaTiendas.anhadeTarjeta(new Tarjeta("DNI1"));
		cadenaTiendas.anhadeTarjeta(new Tarjeta("DNI2"));
		cadenaTiendas.anhadeTarjeta(new Tarjeta("DNI3"));
		cadenaTiendas.anhadeTarjeta(new Tarjeta("DNI4"));
		cadenaTiendas.anhadeTarjeta(new Tarjeta("DNI5"));

		// registra compra para tarjetas existentes

		ret = cadenaTiendas.registraCompra("TAR1", new Compra("A", 1.0));
		assertTrue("No permite registrar una compra", ret);	
		ret = cadenaTiendas.registraCompra("TAR5", new Compra("A", 1.0));
		assertTrue("No permite registrar una compra", ret);
		ret = cadenaTiendas.registraCompra("TAR3", new Compra("A", 1.0));
		assertTrue("No permite registrar una compra", ret);

		// trata de registrar compra para tarjeta no existente

		ret = cadenaTiendas.registraCompra("TAR7", new Compra("A", 1.0));
		assertTrue("Permite registrar compra para tarjeta no existente", !ret);
	}

	@Test
	public void testBuscaTarjeta() {
		Tarjeta tarjeta;
		System.out.println("testBuscaTarjeta");

		CadenaTiendas cadenaTiendas = new CadenaTiendas();

		// ahnade varias tarjetas
		Tarjeta tarjeta1 = new Tarjeta("DNI1");
		Tarjeta tarjeta2 = new Tarjeta("DNI2");
		Tarjeta tarjeta3 = new Tarjeta("DNI3");
		Tarjeta tarjeta4 = new Tarjeta("DNI4");
		Tarjeta tarjeta5 = new Tarjeta("DNI5");

		cadenaTiendas.anhadeTarjeta(tarjeta1);
		cadenaTiendas.anhadeTarjeta(tarjeta2);
		cadenaTiendas.anhadeTarjeta(tarjeta3);
		cadenaTiendas.anhadeTarjeta(tarjeta4);
		cadenaTiendas.anhadeTarjeta(tarjeta5);

		// busca tarjetas existentes

		tarjeta = cadenaTiendas.buscaTarjeta("TAR5");
		assertTrue("No encuentra tarjeta", tarjeta == tarjeta5);		
		tarjeta = cadenaTiendas.buscaTarjeta("TAR1");
		assertTrue("No encuentra tarjeta", tarjeta == tarjeta1);		
		tarjeta = cadenaTiendas.buscaTarjeta("TAR4");
		assertTrue("No encuentra tarjeta", tarjeta == tarjeta4);

		// busca tarjeta no existente

		tarjeta = cadenaTiendas.buscaTarjeta("TAR0");
		assertTrue("Encuentra tarjeta no existente", tarjeta == null);

		tarjeta = cadenaTiendas.buscaTarjeta("TAR6");
		assertTrue("Encuentra tarjeta no existente", tarjeta == null);
	}

	@Test
	public void testBuscaCompraErrorCodigo() {
		boolean ret;
		Compra compra;
		System.out.println("testBuscaCompraErrorCodigo");

		CadenaTiendas cadenaTiendas = new CadenaTiendas();

		// ahnade una tarjeta
		cadenaTiendas.anhadeTarjeta(new Tarjeta("DNI1"));

		// registra una compra
		ret = cadenaTiendas.registraCompra("TAR1", new Compra("BB", 1.0));
		assertTrue(ret);

		// comprueba que retorna la compra de la tarjeta
		compra = cadenaTiendas.buscaCompraDeTarjeta("TAR1", 0);
		assertTrue("No ha encontrado la compra", compra != null);

		// comprueba que no retorna compra de una tarjeta inexistente
		compra = cadenaTiendas.buscaCompraDeTarjeta("TAR2", 0);
		assertTrue("Ha encontrado una compra para una tarjeta inexistente",
				compra == null);		
	}

	@Test
	public void testBuscaCompraErrorPosicion() {
		boolean ret;
		Compra compra;
		System.out.println("testBuscaCompraErrorCodigo");

		CadenaTiendas cadenaTiendas = new CadenaTiendas();

		// ahnade una tarjeta
		cadenaTiendas.anhadeTarjeta(new Tarjeta("DNI1"));

		// registra dos compras
		ret = cadenaTiendas.registraCompra("TAR1", new Compra("BB", 1.0));
		assertTrue(ret);
		ret = cadenaTiendas.registraCompra("TAR1", new Compra("BB", 1.0));
		assertTrue(ret);

		// comprueba que retorna la compras registrdas
		compra = cadenaTiendas.buscaCompraDeTarjeta("TAR1", 0);
		assertTrue("No ha encontrado la compra 0", compra != null);
		compra = cadenaTiendas.buscaCompraDeTarjeta("TAR1", 1);
		assertTrue("No ha encontrado la compra 1", compra != null);

		// comprueba que no retorna una compra en posicion no valida
		compra = cadenaTiendas.buscaCompraDeTarjeta("TAR1", -1);
		assertTrue("Ha encontrado una compra para la posicion -1",
				compra == null);	
		compra = cadenaTiendas.buscaCompraDeTarjeta("TAR1", 2);
		assertTrue("Ha encontrado una compra para una posicion no valida",
				compra == null);	
	}

	@Test
	public void testBuscaCompra() {
		boolean ret;
		Compra compra;
		System.out.println("testBuscaCompra");

		CadenaTiendas cadenaTiendas = new CadenaTiendas();

		// ahnade tarjetas

		cadenaTiendas.anhadeTarjeta(new Tarjeta("DNI1"));
		cadenaTiendas.anhadeTarjeta(new Tarjeta("DNI2"));
		cadenaTiendas.anhadeTarjeta(new Tarjeta("DNI3"));

		// ahnade compras

		Compra compra10 = new Compra("10", 110);
		Compra compra11 = new Compra("11", 120);
		Compra compra12 = new Compra("12", 130);
		Compra compra20 = new Compra("20", 210);

		ret = cadenaTiendas.registraCompra("TAR1", compra10);
		assertTrue(ret);		
		ret = cadenaTiendas.registraCompra("TAR1", compra11);
		assertTrue(ret);		
		ret = cadenaTiendas.registraCompra("TAR1", compra12);
		assertTrue(ret);		
		ret = cadenaTiendas.registraCompra("TAR2", compra20);
		assertTrue(ret);

		//	busca compras existentes

		compra = cadenaTiendas.buscaCompraDeTarjeta("TAR1", 0);
		assertTrue("No encontrada la compra esperada", compra == compra10);		
		compra = cadenaTiendas.buscaCompraDeTarjeta("TAR1", 1);
		assertTrue("No encontrada la compra esperada", compra == compra11);		
		compra = cadenaTiendas.buscaCompraDeTarjeta("TAR1", 2);
		assertTrue("No encontrada la compra esperada", compra == compra12);		
		compra = cadenaTiendas.buscaCompraDeTarjeta("TAR2", 0);
		assertTrue("No encontrada la compra esperada", compra == compra20);

		// busca compras no existentes

		compra = cadenaTiendas.buscaCompraDeTarjeta("TAR1", -1);
		assertTrue("Encontrada compra no esperada", compra == null);		
		compra = cadenaTiendas.buscaCompraDeTarjeta("TAR1", 3);
		assertTrue("Encontrada compra no esperada", compra == null);		
		compra = cadenaTiendas.buscaCompraDeTarjeta("TAR2", 1);
		assertTrue("Encontrada compra no esperada", compra == null);		
		compra = cadenaTiendas.buscaCompraDeTarjeta("TAR3", 0);
		assertTrue("Encontrada compra no esperada", compra == null);
	}

	@Test
	public void testPuntosTarjetas() {
		Tarjeta tarjeta;
		boolean ret;
		System.out.println("testPuntosTarjetas");

		CadenaTiendas cadenaTiendas = new CadenaTiendas();

		cadenaTiendas.anhadeTarjeta(new Tarjeta("DNI1"));

		// comprueba que los datos de la tarjeta son correctos

		tarjeta = cadenaTiendas.buscaTarjeta("TAR1");
		assertTrue("Los puntos inicialies no son 0",
				tarjeta.puntosAcumulados() == 0.0);
		assertTrue("El DNI no es el esperado", tarjeta.dni().equals("DNI1"));

		// anhade compras y comprueba que los puntos se calculan correctamente

		ret = cadenaTiendas.registraCompra("TAR1", new Compra("A", 9.9));
		assertTrue(ret);
		tarjeta = cadenaTiendas.buscaTarjeta("TAR1");
		assertTrue("Los puntos no son 0, son " + tarjeta.puntosAcumulados(),
				tarjeta.puntosAcumulados() == 0);

		ret = cadenaTiendas.registraCompra("TAR1", new Compra("A", 9.9));
		assertTrue(ret);
		tarjeta = cadenaTiendas.buscaTarjeta("TAR1");
		assertTrue("Los puntos no son 0, son " + tarjeta.puntosAcumulados(),
				tarjeta.puntosAcumulados() == 0);

		ret = cadenaTiendas.registraCompra("TAR1", new Compra("A", 17.3));
		assertTrue(ret);
		tarjeta = cadenaTiendas.buscaTarjeta("TAR1");
		assertTrue("Los puntos no son 1, son " + tarjeta.puntosAcumulados(),
				tarjeta.puntosAcumulados() == 1);

		ret = cadenaTiendas.registraCompra("TAR1", new Compra("A", 30.0));
		assertTrue(ret);
		tarjeta = cadenaTiendas.buscaTarjeta("TAR1");
		assertTrue("Los puntos no son 4, son " + tarjeta.puntosAcumulados(),
				tarjeta.puntosAcumulados() == 4);
	}

	/**
	 * Pone el atributo estatico ultimoCodigo a 0 para que en todos los metodos
	 * de test las tarjetas empiecen por el codigo "TAR1".
	 * @throws Exception si se produce un error tratando de modificar
	 * el atributo estatico
	 */
	@BeforeEach
	void setUp() throws Exception {
		Field field = Tarjeta.class.getDeclaredField("ultimoCodigo");
		field.setAccessible(true);
		field.set(null, 0);
	}

}
