package pract02.modelo;

import java.awt.Color;

/**
 * Circulo con las coordenadas de su centro y su radio.
 * 
 * @author Metodos de Programacion (UC) y Aaron Alegria
 * @version sep-2023
 */
public class Circulo {
	// radio del circulo.
	private final int radio;

	// coordenada x del centro del circulo.
	private double centroX;

	// coordenada y del centro del circulo.
	private double centroY;

	//color del circulo
	private final Color color;

	//velocidades
	private double velocidadX;
	private double velocidadY;
	private static final double refresco = 0.05;

	/**
	 * Construye un circulo.
	 * @param radio radio del circulo.
	 * @param centroX coordenada x del centro del circulo.
	 * @param centroY coordenada y del centro del circulo.
	 */
	public Circulo(int radio, double centroX, double centroY, Color color) {
		this.radio = radio;
		this.centroX = centroX;
		this.centroY = centroY;
		this.color = color;
		velocidadX = 0;
		velocidadY = 0;
	}

	//asigna la velocidad al circulo
	/*
	 * @param vX velocidad en eje x
	 * @param vY velociadd eje y
	 */
	public void asignaVelocidad(double vX, double vY) {
		velocidadX = vX;
		velocidadY = vY;

	}

	/**
	 * Retorna el radio del circulo.
	 * @return el radio del circulo.
	 */
	public int radio() {
		return radio;
	}

	/**
	 * Retorna la coordenada x del centro del circulo.
	 * @return la coordenada x del centro del circulo.
	 */
	public double centroX() {
		return centroX;
	}

	/**
	 * Retorna la coordenada y del centro del circulo.
	 * @return la coordenada y del centro del circulo.
	 */
	public double centroY() {
		return centroY;
	}

	//retorna el color
	public Color color() {
		return color;
	}

	//retorna las velocidades
	public double vX() {
		return velocidadX;
	}

	public double vY() {
		return velocidadY;
	}

	public void mueve(double tiempo) {
		centroX += velocidadX * refresco;
		centroY += velocidadY * refresco;

	}

}
