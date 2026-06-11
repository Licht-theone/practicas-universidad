package pract06.torres_hanoi.gui;

import java.awt.Color;
import java.awt.Point;

import j2d.JObjetoRectangulo;
import j2d.utils.Sonido;

import static pract06.torres_hanoi.JuegoTorresHanoi.*;

/**
 * Representacion de los discos en la parte grafica del juego de las Torres de
 * Hahoi.
 * 
 * @author Metodos de Programacion (UC)
 * @version oct-2024
 */
public class JDisco extends JObjetoRectangulo {
	private static final int DELTA_ANCHO_X = 20;
	public static final int ALTO_Y = 15;
	public static final float VEL_MX = 15;
	private static final Color[] COLORES = 
		{Color.RED, Color.ORANGE, Color.YELLOW,
			Color.GREEN, Color.CYAN, Color.BLUE};
	private final int longitud;	
	private Sonido sonidoContacto = new Sonido(PATH_SONIDOS + 
			"cse_select.wav");
	private boolean siguiendoTrayectoria = false;

	/**
	 * Construye un disco.
	 * @param longitud longitud del disco (un numero entero: 1, 2, 3 ...)
	 */
	public JDisco(int longitud) {
		super(null, DELTA_ANCHO_X * longitud, ALTO_Y,
				COLORES[(longitud - 1) % COLORES.length]);
		this.longitud = longitud;
	}
	
	/**
	 * Retorna la longitud del disco.
	 * @return la longitud del disco.
	 */
	public int longitud() {
		return longitud;
	}

	/*
	@Override
	public void colision(JObjeto otroObj, boolean causante) {
		super.colision(otroObj, causante);
		if (causante) {
			//asignaVel(0, 0);
			sonidoContacto.suena();
		}
	}
	*/
	
	/**
	 * Hace que el disco siga la trayectoria indicada.
	 * @param puntos trayectoria a seguir por el disco.
	 */
	public void sigueTrayectoria(Point[] puntos) {
		sigueTrayectoria(puntos, VEL_MX);
	}
	
	/**
	 * Hace que el disco siga la trayectoria con la velocidad maxima indicada.
	 * @param puntos trayectoria a seguir por el disco.
	 * @param vel velocidad maxima con la que recorre la trayectoria.
	 */
	public void sigueTrayectoria(Point[] puntos, float vel) {
		guia().sigueTrayectoria(puntos, vel);
		siguiendoTrayectoria = true;
	}
	
	/**
	 * Indica si el disco esta siguiendo la trayectoria en este momento.
	 * @return si el disco esta siguiendo la trayectoria en este momento.
	 */
	public boolean siguiendoTrayectoria() {
		return siguiendoTrayectoria;
	}

	@Override
	public void ciclo() {
		super.ciclo();
		
		if (siguiendoTrayectoria && guia().trayectoriaFinalizadaEnCiclo()) {
			siguiendoTrayectoria = false;
			sonidoContacto.suena();
		}
	}

	@Override
	public String toString() {
		return "{" + longitud + "}";
	}
		
}
