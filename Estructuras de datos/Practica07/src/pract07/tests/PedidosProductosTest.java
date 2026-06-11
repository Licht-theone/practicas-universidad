package pract07.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import pract07.modelo.*;

import java.io.FileNotFoundException;

/**
 * Test de la gestion de los los pedidos y productos de una empresa.
 * 
 * @author Estructuras de Datos (UC)
 * @version Oct-2023
 */
public class PedidosProductosTest {
	private static final String NOM_FICH = "src/pract07/datos_productos.txt";
	private static final String COD1 = "A1";
	private static final int NUM_INI_UNIDADES_1 = 20;
	private static final String COD2 = "B23";
	private static final int NUM_INI_UNIDADES_2 = 30;
	private static final String COD3 = "C2";
	private static final int NUM_INI_UNIDADES_3 = 10;

	@Test
	void registraProcesaSimpleTest() throws FileNotFoundException {
		Empresa empresa = new Empresa(NOM_FICH);

		// Comprueba que no se puede procesar ningun pedido nada mas crear la empresa
		try {
			empresa.procesaSiguientePedido();
			fail("Deberia haberse lanzado la excepcion");
		} catch (Empresa.NoHayPedidosPendientes e) {
			// El comportamiento correcto es que se lance la excepción
		}

		// registra un pedido y le procesa
		empresa.registraPedido(COD1, NUM_INI_UNIDADES_1 / 2);
		Pedido pedido = empresa.procesaSiguientePedido();
		assertTrue("Datos pedido incorrectos",
				pedido.producto().codigo().equals(COD1) && 
				pedido.numUnidades() == NUM_INI_UNIDADES_1 / 2);

		// Comprueba que no hay ningun pedido que procesar
		try {
			empresa.procesaSiguientePedido();
			fail("Deberia haberse lanzado la excepcion");
		} catch (Empresa.NoHayPedidosPendientes e) {
			// El comportamiento correcto es que se lance la excepción
		}
	}

	@Test
	void erroresRegistraPedidoTest() throws FileNotFoundException {
		Empresa empresa = new Empresa(NOM_FICH);

		// comprueba que no se puede registrar un pedido con un codigo invalido
		try {
			empresa.registraPedido("Cod_no_existente", 1);
			fail("Deberia haberse lanzado la excepcion");
		} catch (Empresa.CodigoProductoIncorrecto e) {
			// El comportamiento correcto es que se lance la excepción
		}

		// comprueba que no se puede registrar un pedido cuando no hay suficientes unidades
		try {
			empresa.registraPedido(COD1, NUM_INI_UNIDADES_1 + 1);
			fail("Deberia haberse lanzado la excepcion");
		} catch (Empresa.CantidadInsuficiente e) {
			// El comportamiento correcto es que se lance la excepción
		}
		
		// registra pedidos que sobrepasan el numero de unidades
		empresa.registraPedido(COD2, NUM_INI_UNIDADES_2 - 10);
		empresa.registraPedido(COD2, 11);
		
		empresa.procesaSiguientePedido(); // NUM_UNIDADES_2 = 10
		
		// comprueba que se produce un error, puesto que quedan 10 unidades, pero
		// el pedido es de 11
		try {
			empresa.procesaSiguientePedido();
			fail("Deberia haberse lanzado la excepcion");
		} catch (Empresa.CantidadInsuficiente e) {
			// El comportamiento correcto es que se lance la excepción
		}	
	}

	@Test
	void ordenPedidosTest() throws FileNotFoundException {
		Empresa empresa = new Empresa(NOM_FICH);

		// registra tres pedidos
		empresa.registraPedido(COD1, 1);
		empresa.registraPedido(COD2, 5);
		empresa.registraPedido(COD2, 2);

		// comprueba que procesa el primer pedido realizado
		Pedido pedido = empresa.procesaSiguientePedido();
		assertTrue("Datos pedido incorrectos",
				pedido.producto().codigo().equals(COD1) && 
				pedido.numUnidades() == 1);

		// comprueba que procesa el segundo pedido realizado
		pedido = empresa.procesaSiguientePedido();
		assertTrue("Datos pedido incorrectos",
				pedido.producto().codigo().equals(COD2) && 
				pedido.numUnidades() == 5);

		// registra otro pedido
		empresa.registraPedido(COD1, 3);

		// comprueba que los pedidos se procesan en orden
		pedido = empresa.procesaSiguientePedido();
		assertTrue("Datos pedido incorrectos",
				pedido.producto().codigo().equals(COD2) && 
				pedido.numUnidades() == 2);
		pedido = empresa.procesaSiguientePedido();
		assertTrue("Datos pedido incorrectos",
				pedido.producto().codigo().equals(COD1) && 
				pedido.numUnidades() == 3);

		// Comprueba que no queda ningun pedido que procesar
		try {
			empresa.procesaSiguientePedido();
			fail("Deberia haberse lanzado la excepcion");
		} catch (Empresa.NoHayPedidosPendientes e) {
			// El comportamiento correcto es que se lance la excepción
		}
	}

	@Test
	void procesaNoSuficientesUnidadesTest() throws FileNotFoundException {
		Empresa empresa = new Empresa(NOM_FICH);

		// registra pedidos
		empresa.registraPedido(COD1, NUM_INI_UNIDADES_1 - 10);
		empresa.registraPedido(COD2, NUM_INI_UNIDADES_2 - 13);
		empresa.registraPedido(COD1, 11);
		empresa.registraPedido(COD2, 13);
		empresa.registraPedido(COD2, 1);
		
		// procesa los pedidos
		empresa.procesaSiguientePedido(); // Unidades1 = NUM_INI_UNIDADES_1 - 10
		empresa.procesaSiguientePedido(); // Unidades2 = NUM_INI_UNIDADES_2 - 13

		// comprueba que no se puede procesar un pedido cuando no hay suficientes unidades
		try {
			empresa.procesaSiguientePedido();
			fail("Deberia haberse lanzado la excepcion");
		} catch (Empresa.CantidadInsuficiente e) {
			// El comportamiento correcto es que se lance la excepción
		}

		// agota las unidades del producto COD2
		empresa.procesaSiguientePedido(); // Unidades2 = 0

		// comprueba que no se puede procesar un pedido cuando no hay suficientes unidades
		try {
			empresa.procesaSiguientePedido();
			fail("Deberia haberse lanzado la excepcion");
		} catch (Empresa.CantidadInsuficiente e) {
			// El comportamiento correcto es que se lance la excepción
		}
	}

	@Test
	void descatalogaTest() throws FileNotFoundException {
		Empresa empresa = new Empresa(NOM_FICH);
		
		// el registro de descatalogados comienza vacio
		assertEquals(0, empresa.numProductosDescatalogados());
		try {
			empresa.productoDescatalogado(0);
			fail("Deberia haberse lanzado la excepcion");
		} catch (Empresa.PosicionIncorrecta e) {
			// El comportamiento correcto es que se lance la excepción
		}
		
		// descataloga un producto
		empresa.descatalogaProductos(NUM_INI_UNIDADES_3 + 1);
		assertEquals(1, empresa.numProductosDescatalogados());
		
		// comprueba que el producto descatalogado es el correcto
		Producto producto = empresa.productoDescatalogado(0);
		assertTrue("Datos producto incorrectos",
				producto.codigo().equals(COD3) && 
				producto.numUnidadesDisponibles() == NUM_INI_UNIDADES_3);
		
		// descataloga otro producto
		empresa.descatalogaProductos(NUM_INI_UNIDADES_1 + 1);
		assertEquals(2, empresa.numProductosDescatalogados());
		
		// comprueba que el producto descatalogado es el correcto
		producto = empresa.productoDescatalogado(1);
		assertTrue("Datos producto incorrectos",
				producto.codigo().equals(COD1) && 
				producto.numUnidadesDisponibles() == NUM_INI_UNIDADES_1);
		
		// descataloga otro producto
		empresa.descatalogaProductos(NUM_INI_UNIDADES_2 + 1);
		assertEquals(3, empresa.numProductosDescatalogados());
		
		// comprueba que el producto descatalogado es el correcto
		producto = empresa.productoDescatalogado(2);
		assertTrue("Datos producto incorrectos",
				producto.codigo().equals(COD2) && 
				producto.numUnidadesDisponibles() == NUM_INI_UNIDADES_2);
		
		// comprueba las otras dos posiciones en el registro de descatalogados
		producto = empresa.productoDescatalogado(0);
		assertTrue("Datos producto incorrectos",
				producto.codigo().equals(COD3) && 
				producto.numUnidadesDisponibles() == NUM_INI_UNIDADES_3);
		producto = empresa.productoDescatalogado(1);
		assertTrue("Datos producto incorrectos",
				producto.codigo().equals(COD1) && 
				producto.numUnidadesDisponibles() == NUM_INI_UNIDADES_1);
	}

	@Test
	void descatalogaVariosTest() throws FileNotFoundException {
		Empresa empresa = new Empresa(NOM_FICH);
		
		// el registro de descatalogados comienza vacio
		assertEquals(0, empresa.numProductosDescatalogados());
		
		// descataloga dos producto
		empresa.descatalogaProductos(NUM_INI_UNIDADES_1 + 1);
		assertEquals(2, empresa.numProductosDescatalogados());
		
		// comprueba que los productos descatalogados son los correctos
		Producto producto = empresa.productoDescatalogado(0);
		assertTrue("Datos producto incorrectos",
				producto.codigo().equals(COD1) && 
				producto.numUnidadesDisponibles() == NUM_INI_UNIDADES_1);
		producto = empresa.productoDescatalogado(1);
		assertTrue("Datos producto incorrectos",
				producto.codigo().equals(COD3) && 
				producto.numUnidadesDisponibles() == NUM_INI_UNIDADES_3);
	}
	
	@Test
	void descatalogaUmbralIgualTest() throws FileNotFoundException {
		Empresa empresa = new Empresa(NOM_FICH);
		
		// llama a descataloga con un umbral igual al minimo
		empresa.descatalogaProductos(NUM_INI_UNIDADES_3);
		
		// comprueba que no ha descatalogado ninguno
		try {
			empresa.productoDescatalogado(0);
			fail("Deberia haberse lanzado la excepcion");
		} catch (Empresa.PosicionIncorrecta e) {
			// El comportamiento correcto es que se lance la excepción
		}
		
		// llama a descataloga con un umbral igual al segundo menor
		empresa.descatalogaProductos(NUM_INI_UNIDADES_1);
		
		// comprueba que solo ha descatalogado uno
		empresa.productoDescatalogado(0);
		try {
			empresa.productoDescatalogado(1);
			fail("Deberia haberse lanzado la excepcion");
		} catch (Empresa.PosicionIncorrecta e) {
			// El comportamiento correcto es que se lance la excepción
		}
	}
	
	@Test
	void descatalogaDespuesPedidoTest() throws FileNotFoundException {
		Empresa empresa = new Empresa(NOM_FICH);
		
		// registra un pedido y le procesa
		empresa.registraPedido(COD1, NUM_INI_UNIDADES_1 - 2);
		empresa.procesaSiguientePedido();
		
		// en este punto el producto COD1 tiene 2 unidades
		
		// descataloga solo el producto COD1
		empresa.descatalogaProductos(3);
		
		// comprueba que ha descatalogado el esperado
		Producto producto = empresa.productoDescatalogado(0);
		assertTrue("Datos producto incorrectos",
				producto.codigo().equals(COD1) && 
				producto.numUnidadesDisponibles() == 2);
		
		// comprueba que solo ha descatalogado uno
		try {
			empresa.productoDescatalogado(1);
			fail("Deberia haberse lanzado la excepcion");
		} catch (Empresa.PosicionIncorrecta e) {
			// El comportamiento correcto es que se lance la excepción
		}
	}

}
