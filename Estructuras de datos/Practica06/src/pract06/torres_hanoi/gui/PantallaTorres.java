package pract06.torres_hanoi.gui;

import j2d.JEscena;
import j2d.JObjetoRectangulo;
import j2d.JObjetoTexto;
import j2d.mods.Temporizador;
import j2d.mods.ITemporizado;
import j2d.utils.Sonido;
import pract06.torres_hanoi.modelo.ControlMovimientos;
import pract06.torres_hanoi.modelo.Disco;
import pract06.torres_hanoi.modelo.Torre;

import static pract06.torres_hanoi.JuegoTorresHanoi.*;

import java.awt.Color;
import java.awt.Point;
import java.util.LinkedList;

/**
 * Escena del juego de las "Torres de Hanoi" que muestra las torres e invoca
 * al algoritmo diseñado en la clase ControlMovimientos.
 * 
 * @author Metodos de Programacion (UC)
 * @version oct-2024
 */
public class PantallaTorres extends JEscena implements ITemporizado {
	public static final int SUELO_Y = ALTO_VENTANA - 100;
	private final JTorre[] torres;
	private final ControlMovimientos control;
	private final Temporizador controlMovimientos =
			new Temporizador(MS_ENTRE_MOVIMIENTOS, this);
	private static boolean errorDetectado = false;
	private static boolean finalizado = false;
	private JDisco ultimoDiscoMovido;

	private static Sonido sonidoError = new Sonido(PATH_SONIDOS + 
			"bse_attack_deny.wav");
	private static Sonido sonidoConseguido = new Sonido(PATH_SONIDOS + 
			"cse_yellow.wav");

	private static final int MSJ_CONSEGUIDO_X = ANCHO_VENTANA * 2 / 3;
	private static final int MSJ_CONSEGUIDO_Y = SUELO_Y + 40;
	private static final Point PTO_TECHO = new Point(ANCHO_VENTANA / 2, 40);

	/**
	 * Construye la escena.
	 * @param controlJuego clase que implementa el algoritmo de movimiento
	 * de discos.
	 */
	public PantallaTorres(ControlMovimientos controlJuego) {
		super("Pantalla torres");
		this.control = controlJuego;

		// torres
		final int delta_x = ANCHO_VENTANA / (controlJuego.numTorres() + 1);
		torres = new JTorre[controlJuego.numTorres()];
		for (int i = 0; i < torres.length; i++) {
			torres[i] = new JTorre(controlJuego.torreEnPos(i));
			incluyeObj(torres[i], delta_x * (i + 1), SUELO_Y - JTorre.ALTO_Y);
		}

		// Suelo
		JObjetoRectangulo suelo = new JObjetoRectangulo(null, ANCHO_VENTANA,
				ALTO_VENTANA - SUELO_Y, Color.BLACK);
		suelo.asignaFactorGravedad(0);
		suelo.colisionador().haceDominante(true);
		incluyeObj(suelo, 0, SUELO_Y);

		// Refleja el estado inicial de las torres del modelo
		JDisco jDisco = null;
		for (int t = 0; t < controlJuego.numTorres(); t++) {
			// Saca los elementos de la torre (para saber que discos hay)
			Torre torre = controlJuego.torreEnPos(t);
			LinkedList<Disco> discos = new LinkedList<>();
			while (torre.discoEnCima() != null) {
				discos.add(0, torre.desapilaDisco());
			}

			// Vuelve a poner los discos en la torre
			for (Disco d: discos) {
				torre.apilaDisco(d);
			}

			// Representa los discos en la GUI
			int d = 0;
			for (Disco disco: discos) {
				jDisco = new JDisco(disco.longitud());
				incluyeObjCentrado(jDisco, torres[t].centro().x,
						SUELO_Y - (d + 2) * (jDisco.altoY() + JDisco.ALTO_Y));
				jDisco.sigueTrayectoria(new Point[] {torres[t].puntoCima()},
						JDisco.VEL_MX);
				torres[t].poneDisco(jDisco);
				d++;
			}
		}
		ultimoDiscoMovido = jDisco;
	}

	@Override
	public void ciclo() {
		if (errorDetectado || finalizado) {
			return;
		}

		if (ultimoDiscoMovido.guia().trayectoriaFinalizadaEnCiclo()) {
			// el disco acaba de finalizar su movimiento

			// comprueba si ha acabado
			if (torres[0].estaVacia() && torres[1].estaVacia()) {
				// ha acabado
				finalizado = true;
				incluyeObj(new JObjetoTexto("¡Lo has logrado!", Color.MAGENTA), 
						MSJ_CONSEGUIDO_X, MSJ_CONSEGUIDO_Y);
				sonidoConseguido.suena();

			} else {
				// no ha acabado: espera un poco hasta realizar otro movimiento
				controlMovimientos.iniciaCuenta();
			}
		}
	}

	/**
	 * Realiza los movimientos de los discos entre las torres. Se invoca
	 * despues de cada movimiento realizado por el control de movimientos. Para
	 * saber el disco que ha sido movido compara el estado de las jTorres con
	 * el de las torres del modelo.
	 */
	private void actualizaTorres() {
		// busca las torres que han cambiado
		JTorre torreDestino = null;
		JTorre torreOrigen = null;
		for (JTorre torre: torres) {
			if (torre.discoDesapilado()) {
				torreOrigen = torre;
			}
			if (torre.discoApilado()) {
				torreDestino = torre;
			}
		}

		// deteccion de errores
		if (torreOrigen == null && torreDestino == null) {
			errorFatal("No ha cambiado ninguna torre",
					control.toString());
			return;
		}
		if (torreOrigen == null && torreDestino != null) {
			errorFatal("Movimiento incompleto: disco NO quitado de su " +
					"torre original y movido a torre " +
					torreDestino.posicionTorre(),
					control.toString());
			return;
		}
		if (torreOrigen != null && torreDestino == null) {
			if (torreOrigen.discoEnCima() != null) {
				torreOrigen.discoEnCima().sigueTrayectoria(
						new Point[] {torreOrigen.puntoSuperior(), PTO_TECHO});
			}
			errorFatal("Movimiento incompleto: disco quitado de torre " +
					torreOrigen.posicionTorre() +
					" pero no movido a ninguna torre",
					control.toString());
			return;
		}

		// mueve disco de torre a torre
		ultimoDiscoMovido = torreOrigen.quitaDisco();
		if (ultimoDiscoMovido == null) {
			errorFatal("No se puede desapilar un disco de la torre " +
					torreOrigen.posicion() + " puesto que esta vacia",
					control.toString());
			return;
		}
		ultimoDiscoMovido.sigueTrayectoria(new Point[] {torreOrigen.puntoSuperior(),
				torreDestino.puntoSuperior(), torreDestino.puntoCima()});

		if (torreDestino.discoEnCima() != null &&
				ultimoDiscoMovido.longitud() >= 
				torreDestino.discoEnCima().longitud()) {	
			PantallaTorres.errorFatal("Error apilado en torre " + 
					torreDestino.posicionTorre() + "\n" +
					"Disco en cima:" + torreDestino.discoEnCima().longitud() +
					" Disco a apilar:" + ultimoDiscoMovido.longitud(),
					control.toString());
			return;
		}
		torreDestino.poneDisco(ultimoDiscoMovido);

		// comprueba que las torres estan bien
		for (JTorre torre: torres) {
			torre.comprueba();
		}
	}

	/**
	 * Notifica un error fatal detectado en el desarrollo del juego. Finaliza
	 * la realizacion de movimientos.
	 * @param msj mensaje correspondiente al error detectado.
	 * @param estadoControl estado del controlador del modelo.
	 */
	public static void errorFatal(String msj, String estadoControl) {
		errorDetectado  = true;
		sonidoError.suena();
		System.out.println("Error:" + msj + "\n" + estadoControl);
	}

	@Override
	public void finTiempo(Temporizador controlMovimientos) {	
		if (errorDetectado || finalizado) {
			return;
		}

		// llama al controlador para que realice un nuevo movimiento
		control.realizaMovimiento();

		// actualiza los discos y las torres
		actualizaTorres();
	}

}
