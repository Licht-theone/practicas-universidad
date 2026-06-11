package pract12.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fundamentos_test.test.infraestructura.FundamentosTest;
import pract12.gui.GUIGestionGimnasio;

/**
 * Test de la clase Gimnasio.
 * (Test desde la interfaz grafica utilizando fundamentos_test)
 * 
 * @author  Metodos de Programacion (UC)
 * @version nov-23
 */
class GimnasioTest {

	private static final String[] ACTIVIDADES =
		{"Aqua fit", "Pilates", "Yoga", "Actividad3", "Actividad4",
		 "Actividad5", "Actividad6", "Actividad7", "Actividad8", "Actividad9",
		 "Actividad10", "Actividad11"};
	private static final float[] PRECIO_ACTIVIDADES =
		{10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 7};
	private static final int[] PLAZAS_ACTIVIDADES =
		{20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9};

	private static final String[] DNIS_BASICO = 
		{"10000000A", "10000001A", "10000002A", "10000003A", "10000004A",
		 "10000005A", "10000006A", "10000007A", "10000008A", "10000009A"};
	private static final String[] DNIS_PLUS =
		{"20000000B", "20000001B", "20000002B", "20000003B", "20000004B",
		 "20000005B", "20000006B", "20000007B", "20000008B", "20000009B"};

	private static final String[] NOMBRES_PLUS =
		{"Jose Antonio Perez Garcia", "Lola Lopez Gonzalez", "Nom2", "Nom3", "Nom4",
		 "Nom5", "Nom6", "Nom7", "Nom8", "Nom9"};
	private static final int LIMITE_ACTIVIDADES_BASICO = 4;
	private static final int LIMITE_ACTIVIDADES_PLUS = 10;

	private static final float PRECIO_FIJO_BASICO = 40;

	// opciones del menu
	private static final int INSCRIBE_EN_ACTIVIDAD =
			GUIGestionGimnasio.INSCRIBE_EN_ACTIVIDAD;
	private static final int MUESTRA_DATOS_SOCIO =
			GUIGestionGimnasio.MUESTRA_DATOS_SOCIO;
	private static final int INSCRIPCION_SOCIO =
			GUIGestionGimnasio.INSCRIPCION_SOCIO;
	private static final int FICH_INFORME_ACTIVIDAD =
			GUIGestionGimnasio.FICH_INFORME_ACTIVIDAD;
	private static final String[] OPTION_NAMES =
		{"INSCRIBE_EN_ACTIVIDAD", "MUESTRA_DATOS_SOCIO", "INSCRIPCION_SOCIO",
		"FICH_INFORME_ACTIVIDAD"};

	// Mensajes GUI
	private static final String[][] msjsGUI =
		{
				{"correctamente", "ningun socio", "ninguna actividad",
					"ya esta inscrito", "limite", "completado sus plazas"}, // INSCRIBE
				{"Pago:", "ningun socio"}, // DATOS SOCIO
				{"socio esta", "socio no esta",
					"ningun socio", "ninguna actividad"}, // INSCRIPCION_SOCIO
				{"Generado", "No existe", "Error escribiendo"}
		};

	@Test
	void inscribeSocioErrorDniTest() {
		System.out.println("inscribeSocioErrorDniTest");

		// comprueba que no se puede inscribir un DNI no existente
		FundamentosTest.interaccionGUI(INSCRIBE_EN_ACTIVIDAD, "ningun socio",
				"DniXXX", ACTIVIDADES[2]);

		// comprueba que se puede inscribir un DNI existente
		FundamentosTest.interaccionGuiOK(INSCRIBE_EN_ACTIVIDAD,
				DNIS_BASICO[3], ACTIVIDADES[2]);		
	}

	@Test
	void inscribeSocioErrorActividadTest() {
		System.out.println("inscribeSocioErrorActividadTest");

		// comprueba que no se puede inscribir en una actividad no existente
		FundamentosTest.interaccionGUI(INSCRIBE_EN_ACTIVIDAD, "ninguna actividad",
				DNIS_PLUS[7], "ActividadXXXX");

		// comprueba que se puede inscribir en una actividad existente
		FundamentosTest.interaccionGuiOK(INSCRIBE_EN_ACTIVIDAD,
				DNIS_PLUS[7], ACTIVIDADES[1]);		
	}

	@Test
	void inscribeSocioErrorYaInscritoTest() {
		System.out.println("inscribeSocioErrorYaInscritoTest");

		// inscribe en una actividad
		FundamentosTest.interaccionGuiOK(INSCRIBE_EN_ACTIVIDAD,
				DNIS_BASICO[0], ACTIVIDADES[2]);		

		// comprueba que no se puede volver a inscribir
		FundamentosTest.interaccionGUI(INSCRIBE_EN_ACTIVIDAD, "ya esta inscrito",
				DNIS_BASICO[0], ACTIVIDADES[2]);

		// comprueba que otro socio si se puede inscribir
		FundamentosTest.interaccionGuiOK(INSCRIBE_EN_ACTIVIDAD,
				DNIS_PLUS[9], ACTIVIDADES[2]);
	}

	@Test
	void inscribeSocioErrorLimiteActividadesBasicoTest() {
		System.out.println("inscribeSocioErrorLimiteActividadesBasicoTest");

		// Inscribe socio basico en su limite de actividades
		for (int i = 0; i < LIMITE_ACTIVIDADES_BASICO; i++) {
			FundamentosTest.interaccionGuiOK(INSCRIBE_EN_ACTIVIDAD,
					DNIS_BASICO[2], ACTIVIDADES[i]);
		}

		// comprueba que falla al tratar de inscribirle en una mas
		FundamentosTest.interaccionGUI(INSCRIBE_EN_ACTIVIDAD, "limite",
				DNIS_BASICO[2], ACTIVIDADES[LIMITE_ACTIVIDADES_BASICO]);		
	}

	@Test
	void inscribeSocioErrorLimiteActividadesPlusTest() {
		System.out.println("inscribeSocioErrorLimiteActividadesPlusTest");

		// Inscribe socio plus en su limite de actividades
		for (int i = 0; i < LIMITE_ACTIVIDADES_PLUS; i++) {
			FundamentosTest.interaccionGuiOK(INSCRIBE_EN_ACTIVIDAD,
					DNIS_PLUS[4], ACTIVIDADES[i]);
		}

		// comprueba que falla al tratar de inscribirle en una mas
		FundamentosTest.interaccionGUI(INSCRIBE_EN_ACTIVIDAD, "limite",
				DNIS_PLUS[4], ACTIVIDADES[LIMITE_ACTIVIDADES_PLUS]);		
	}

	@Test
	void inscribeSocioErrorLimiteActividadTest() {
		System.out.println("inscribeSocioErrorLimiteActividadTest");

		// Inscribe socios en actividad hasta completar su capacidad
		for (String dni: DNIS_BASICO) {
			FundamentosTest.interaccionGuiOK(INSCRIBE_EN_ACTIVIDAD,
					dni, ACTIVIDADES[4]);
		}
		for (int i = 0; i < PLAZAS_ACTIVIDADES[4] - DNIS_BASICO.length; i++) {
			FundamentosTest.interaccionGuiOK(INSCRIBE_EN_ACTIVIDAD,
					DNIS_PLUS[i], ACTIVIDADES[4]);
		}

		// comprueba que falla al tratar de inscribir un socio mas
		FundamentosTest.interaccionGUI(INSCRIBE_EN_ACTIVIDAD,
				"completado sus plazas",
				DNIS_PLUS[9], ACTIVIDADES[4]);		
	}

	@Test
	void datosSocioErrorDniTest() {
		System.out.println("datosSocioErrorDniTest");

		// comprueba que no se pueden mostrar los datos de un DNI no existente
		FundamentosTest.interaccionGUI(MUESTRA_DATOS_SOCIO, "ningun socio",
				"DniXXX");

		// comprueba que se pueden mostrar los datos de un DNI existente
		FundamentosTest.interaccionGuiOK(MUESTRA_DATOS_SOCIO, DNIS_BASICO[3]);		
	}

	@Test
	void datosInicialesSociosBasicosTest() {
		System.out.println("datosInicialesSociosBasicosTest");

		// comprueba que los socios basicos comienzan con pago 40 y 0 actividades
		for (String dni: DNIS_BASICO) {
			String respuesta =
					FundamentosTest.interaccionGuiOK(MUESTRA_DATOS_SOCIO, dni);
			compruebaDatosSocio(respuesta, PRECIO_FIJO_BASICO, 0, null);
		}
	}

	@Test
	void datosInicialesSociosPlusTest() {
		System.out.println("datosInicialesSociosPlusTest");

		// comprueba que los socios plus comienzan con pago 0 y 0 actividades
		int i = 0;
		for (String dni: DNIS_PLUS) {
			String respuesta =
					FundamentosTest.interaccionGuiOK(MUESTRA_DATOS_SOCIO, dni);
			compruebaDatosSocio(respuesta, 0, 0, NOMBRES_PLUS[i]);
			i++;
		}
	}

	@Test
	void pagoSocioBasicoTest() {
		System.out.println("pagoSocioBasicoTest");

		// comprueba que los socios basicos comienzan con pago 40 y 0 actividades
		for (String dni: DNIS_BASICO) {
			String respuesta =
					FundamentosTest.interaccionGuiOK(MUESTRA_DATOS_SOCIO, dni);
			compruebaDatosSocio(respuesta, PRECIO_FIJO_BASICO, 0, null);
		}

		// inscribe los socios basicos en actividades
		for (int i = 0; i < DNIS_BASICO.length; i++) {
			FundamentosTest.interaccionGuiOK(INSCRIBE_EN_ACTIVIDAD,
					DNIS_BASICO[i], ACTIVIDADES[i]);
		}
		for (int i = 0; i < DNIS_BASICO.length; i++) {
			FundamentosTest.interaccionGuiOK(INSCRIBE_EN_ACTIVIDAD,
					DNIS_BASICO[i], ACTIVIDADES[i + 1]);
		}

		// comprueba que los socios basicos siguen pagando lo mismo
		for (String dni: DNIS_BASICO) {
			String respuesta =
					FundamentosTest.interaccionGuiOK(MUESTRA_DATOS_SOCIO, dni);
			compruebaDatosSocio(respuesta, PRECIO_FIJO_BASICO, 2, null);
		}
	}

	@Test
	void pagoSocioPlusTest() {
		System.out.println("pagoSocioPlusTest");

		// inscribe los socios plus en actividades
		for (int i = 0; i < DNIS_PLUS.length; i++) {
			FundamentosTest.interaccionGuiOK(INSCRIBE_EN_ACTIVIDAD,
					DNIS_PLUS[i], ACTIVIDADES[i]);
		}
		for (int i = 0; i < DNIS_PLUS.length; i++) {
			FundamentosTest.interaccionGuiOK(INSCRIBE_EN_ACTIVIDAD,
					DNIS_PLUS[i], ACTIVIDADES[i + 1]);
		}

		// comprueba que los socios pagan lo correcto
		for (int i = 0; i < DNIS_PLUS.length; i++) {
			String respuesta =
					FundamentosTest.interaccionGuiOK(MUESTRA_DATOS_SOCIO,
							DNIS_PLUS[i]);
			float pago = PRECIO_ACTIVIDADES[i] + PRECIO_ACTIVIDADES[i + 1];
			compruebaDatosSocio(respuesta, pago, 2, NOMBRES_PLUS[i]);
		}
	}

	@Test
	void estaInscritoSocioErrorDniTest() {
		System.out.println("estaInscritoSocioErrorDniTest");

		// comprueba que falla con un DNI no valido
		FundamentosTest.interaccionGUI(INSCRIPCION_SOCIO, "ningun socio",
				"DniXXX", ACTIVIDADES[2]);

		// comprueba que no falla con DNIs validos
		FundamentosTest.interaccionGUI(INSCRIPCION_SOCIO, "socio no esta",
				DNIS_BASICO[1], ACTIVIDADES[2]);
		FundamentosTest.interaccionGUI(INSCRIPCION_SOCIO, "socio no esta",
				DNIS_BASICO[2], ACTIVIDADES[2]);
		FundamentosTest.interaccionGUI(INSCRIPCION_SOCIO, "socio no esta",
				DNIS_PLUS[3], ACTIVIDADES[3]);
	}

	@Test
	void estaInscritoSocioErrorActividadTest() {
		System.out.println("estaInscritoSocioErrorActividadTest");

		// comprueba que falla con una actividad no existente
		FundamentosTest.interaccionGUI(INSCRIPCION_SOCIO, "ninguna actividad",
				DNIS_PLUS[7], "ActividadXXXX");

		// comprueba que no falla con actividades existentes
		FundamentosTest.interaccionGUI(INSCRIPCION_SOCIO, "socio no esta",
				DNIS_PLUS[7], ACTIVIDADES[1]);		
		FundamentosTest.interaccionGUI(INSCRIPCION_SOCIO, "socio no esta",
				DNIS_BASICO[1], ACTIVIDADES[3]);		
	}

	@Test
	void estaInscritoSimpleTest() {
		System.out.println("estaInscritoSimpleTest");

		// comprueba que en pricipio no esta inscrito
		FundamentosTest.interaccionGUI(INSCRIPCION_SOCIO, "socio no esta",
				DNIS_BASICO[3], ACTIVIDADES[2]);

		// inscribe el socio
		FundamentosTest.interaccionGuiOK(INSCRIBE_EN_ACTIVIDAD,
				DNIS_BASICO[3], ACTIVIDADES[2]);	

		// comprueba que esta inscrito en esa actividad
		FundamentosTest.interaccionGuiOK(INSCRIPCION_SOCIO,
				DNIS_BASICO[3], ACTIVIDADES[2]);

		// comprueba que no esta inscrito en otra actividad
		FundamentosTest.interaccionGUI(INSCRIPCION_SOCIO, "socio no esta",
				DNIS_BASICO[3], ACTIVIDADES[1]);
	}

	@Test
	void estaInscritoVariosEnUnaTest() {
		System.out.println("estaInscritoVariosEnUnaTest");

		// comprueba que en pricipio no esta inscrito
		FundamentosTest.interaccionGUI(INSCRIPCION_SOCIO, "socio no esta",
				DNIS_BASICO[3], ACTIVIDADES[2]);

		// inscribe tres socios en una actividad
		FundamentosTest.interaccionGuiOK(INSCRIBE_EN_ACTIVIDAD,
				DNIS_BASICO[3], ACTIVIDADES[2]);	
		FundamentosTest.interaccionGuiOK(INSCRIBE_EN_ACTIVIDAD,
				DNIS_PLUS[0], ACTIVIDADES[2]);		
		FundamentosTest.interaccionGuiOK(INSCRIBE_EN_ACTIVIDAD,
				DNIS_PLUS[1], ACTIVIDADES[2]);	

		// comprueba que estan inscritos en esa actividad
		FundamentosTest.interaccionGuiOK(INSCRIPCION_SOCIO,
				DNIS_BASICO[3], ACTIVIDADES[2]);
		FundamentosTest.interaccionGuiOK(INSCRIPCION_SOCIO,
				DNIS_PLUS[0], ACTIVIDADES[2]);
		FundamentosTest.interaccionGuiOK(INSCRIPCION_SOCIO,
				DNIS_PLUS[1], ACTIVIDADES[2]);

		// comprueba que no estan inscritos en otra actividad
		FundamentosTest.interaccionGUI(INSCRIPCION_SOCIO, "socio no esta",
				DNIS_BASICO[3], ACTIVIDADES[5]);
		FundamentosTest.interaccionGUI(INSCRIPCION_SOCIO, "socio no esta",
				DNIS_PLUS[0], ACTIVIDADES[3]);
		FundamentosTest.interaccionGUI(INSCRIPCION_SOCIO, "socio no esta",
				DNIS_PLUS[1], ACTIVIDADES[0]);
	}

	@Test
	void estaInscritoUnoEnVariasTest() {
		System.out.println("estaInscritoVariosEnUnaTest");

		// inscribe dos socios en varias actividades
		FundamentosTest.interaccionGuiOK(INSCRIBE_EN_ACTIVIDAD,
				DNIS_BASICO[3], ACTIVIDADES[2]);	
		FundamentosTest.interaccionGuiOK(INSCRIBE_EN_ACTIVIDAD,
				DNIS_BASICO[3], ACTIVIDADES[1]);	
		FundamentosTest.interaccionGuiOK(INSCRIBE_EN_ACTIVIDAD,
				DNIS_BASICO[3], ACTIVIDADES[0]);	
		FundamentosTest.interaccionGuiOK(INSCRIBE_EN_ACTIVIDAD,
				DNIS_PLUS[4], ACTIVIDADES[2]);		
		FundamentosTest.interaccionGuiOK(INSCRIBE_EN_ACTIVIDAD,
				DNIS_PLUS[4], ACTIVIDADES[3]);	

		// comprueba que estan inscritos en sus actividades
		FundamentosTest.interaccionGuiOK(INSCRIPCION_SOCIO,
				DNIS_BASICO[3], ACTIVIDADES[2]);
		FundamentosTest.interaccionGuiOK(INSCRIPCION_SOCIO,
				DNIS_BASICO[3], ACTIVIDADES[1]);
		FundamentosTest.interaccionGuiOK(INSCRIPCION_SOCIO,
				DNIS_BASICO[3], ACTIVIDADES[0]);
		FundamentosTest.interaccionGuiOK(INSCRIPCION_SOCIO,
				DNIS_PLUS[4], ACTIVIDADES[2]);
		FundamentosTest.interaccionGuiOK(INSCRIPCION_SOCIO,
				DNIS_PLUS[4], ACTIVIDADES[3]);

		// comprueba que no estan inscritos en otras actividades
		FundamentosTest.interaccionGUI(INSCRIPCION_SOCIO, "socio no esta",
				DNIS_BASICO[3], ACTIVIDADES[3]);
		FundamentosTest.interaccionGUI(INSCRIPCION_SOCIO, "socio no esta",
				DNIS_PLUS[4], ACTIVIDADES[1]);
		FundamentosTest.interaccionGUI(INSCRIPCION_SOCIO, "socio no esta",
				DNIS_PLUS[4], ACTIVIDADES[0]);
	}

	@Test
	void ficheroInformeActividadTest() throws FileNotFoundException {
		System.out.println("ficheroInformeActividadTest");
		final int numActividad = 11;

		// inscribe socios en la actividade
		FundamentosTest.interaccionGuiOK(INSCRIBE_EN_ACTIVIDAD,
				DNIS_PLUS[0], ACTIVIDADES[numActividad]);	
		FundamentosTest.interaccionGuiOK(INSCRIBE_EN_ACTIVIDAD,
				DNIS_BASICO[0], ACTIVIDADES[numActividad]);	
		FundamentosTest.interaccionGuiOK(INSCRIBE_EN_ACTIVIDAD,
				DNIS_PLUS[1], ACTIVIDADES[numActividad]);	
		FundamentosTest.interaccionGuiOK(INSCRIBE_EN_ACTIVIDAD,
				DNIS_BASICO[4], ACTIVIDADES[numActividad]);

		// Escribe el fichero
		FundamentosTest.interaccionGuiOK(FICH_INFORME_ACTIVIDAD,
				ACTIVIDADES[numActividad]);

		String[] lineasEsperadas =
			{"20000000B   Jose Antonio Perez Garcia        7.00",
			 "10000000A                                    40.00",
			 "20000001B   Lola Lopez Gonzalez              7.00",
			 "10000004A                                    40.00"};

		// Lee el fichero linea a linea
		String[] lineas = new String[4];
		try (Scanner in =
				new Scanner(new FileReader(ACTIVIDADES[numActividad] + ".txt"))) {
			in.nextLine(); // salta la cabecera
			for (int i = 0; i < lineas.length; i++) {
				lineas[i] = in.nextLine();
			}
		}

		// los contenidos de la lineas deben ser los esperados
		System.out.println("  Comprueba contenidos (sin espacios repetidos)...");
		for (int i = 0; i < lineas.length; i++) {
			final String esperado = lineasEsperadas[i].replaceAll("\\s+", " ");
			final String leido = lineas[i].replaceAll("\\s+", " ");
			System.out.println("   Esperado:" + esperado);
			System.out.println("   Leido:   " + leido);
			assertEquals(esperado, leido);
		}

		// todas las lineas deben ser igual de largas
		final int largoLinea = lineas[0].length();
		for (int i = 1; i < lineas.length; i++) {
			assertEquals(largoLinea, lineas[i].length(),
					"Las lineas del fichero deberian tener igual longitud");
		}

		// los nombres deben empezar en la misma columna
		assertEquals(lineas[0].indexOf('J'), lineas[2].indexOf('L'),
				"Los nombres deben empezar en la misma columna");

		// los pagos deben estar alineados por el punto decimal
		final int posPunto = lineas[0].indexOf('.');
		for (int i = 1; i < lineas.length; i++) {
			assertEquals(posPunto, lineas[i].indexOf('.'),
					"Los pagos deben estar alineados por el punto decimal");
		}

	}

	/**
	 * Comprueba que la respuesta dada por la GUI coincide con los datos
	 * esperados para un socio.
	 * La respuesta tiene el formato "Pago: %f Num actividades: %i"
	 * @param respuesta string con la respuesta de la GUI.
	 * @param precioEsperado precio esperado
	 * @param numActividadesEsperado numero de actividades esperado
	 * @param nombreEsperado nombre del socio (null si no es un socio plus)
	 */
	private void compruebaDatosSocio(String respuesta, float precioEsperado,
			int numActividadesEsperado, String nombreEsperado) {
		try (Scanner in = new Scanner(respuesta)) {
			in.useLocale(Locale.ENGLISH);

			in.next(); // "Pago:"
			float precio = in.nextFloat();
			assertEquals(precioEsperado, precio, 0.001, "Error en precio");

			in.next(); // "Num"
			in.next(); // "actividades:"
			int numActividades = in.nextInt();
			assertEquals(numActividadesEsperado, numActividades,
					"Error en numero de actividades");

			if (nombreEsperado != null) {
				in.next(); // "Nombre:"
				final String nombre = in.nextLine().strip();
				assertEquals(nombreEsperado, nombre, "Error en nombre");
			}
		}
	}

	// metodos para la infraestructura de test

	@BeforeAll
	public static void preparaModoTest() {
		FundamentosTest.lanzaModoTest(OPTION_NAMES, msjsGUI, false);
	}

	@AfterAll
	public static void finalizaModoTest() {
		FundamentosTest.finalizaModoTest();
	}

	/**
	 * Se ejecuta antes de cada test.
	 */
	@BeforeEach
	public void lanzaGUI() {
		FundamentosTest.lanzaGUI(GUIGestionGimnasio.class);
	}

	/**
	 * Se ejecuta despues de cada test.
	 * @throws InterruptedException error en thread main.
	 */
	@AfterEach
	public void finalizaGUI() throws InterruptedException {
		FundamentosTest.finalizaGUI(GUIGestionGimnasio.FIN_APLICACION);
	}
}
