package pract02.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.*;

import pract02.modelo.Circulo;
import pract02.modelo.DibujoCirculos;

/**
 * Ventana en la que se dibuja un conjunto de circulos.
 * La ventana se ha creado utilizando la libreria grafica Swing.
 * 
 * @author Metodos de Programacion (UC)
 * @version feb-2021
 */
@SuppressWarnings("serial")
public class VentanaCirculos extends JFrame { 
	// Gestion de los circulos dibujados en la ventana
	private DibujoCirculos dibujo;

	/**
	 * Crea una ventana de la dimension indicada.
	 * @param ancho ancho en pixels de la ventana.
	 * @param alto alto en pixels de la ventana.
	 */
	public VentanaCirculos(int ancho, int alto) {
		super("Dibuja circulos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// crea el panel y le asocia con la ventanta
		JPanel panelCirculos = new PanelCirculos();
		panelCirculos.setPreferredSize(new Dimension(ancho, alto));
		setContentPane(panelCirculos); // asocia la ventana con el panel

		// finaliza la creacion de la ventana
		pack();
		setResizable(false);
		setVisible(true);

		// Crea el elemento del modelo que gestiona el dibujo (y el movimiento)
		// de los circulos
		dibujo = new DibujoCirculos(ancho, alto);
	}

	/**
	 * Anhade un circulo a la ventana.
	 * @param circulo circulo a anhadir.
	 */
	public void anhadeCirculo(Circulo circulo) {
		dibujo.anhadeCirculo(circulo);		
	}

	/**
	 * Redibuja los circulos en la ventana borrando los contenidos existentes.
	 * @param tiempoEspera tiempo de espera despues de repintar (en segundos)
	 */
	public void redibuja(double tiempoEspera) {
		dibujo.mueveCirculos(tiempoEspera);
		repaint(); // llama a PanelCirculos.paintComponent()

		// espera un poco (para permitir mostrar animaciones).
		try {
			Thread.sleep((long) (tiempoEspera * 1000.0));
		} catch (InterruptedException e) {
			System.exit(-1); // fatal error
		}
	}

	/**
	 * Panel sobre el que se dibujan los circulos.
	 * 
	 * @author Metodos de Programacion (UC)
	 * @version feb-2019
	 *
	 */
	private class PanelCirculos extends JPanel {

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			// "Truco" para ver si el alumno ya ha definido el metodo color()
			// en la clase Circulo
			Method metodoColor = null;
			try {
				metodoColor = Circulo.class.getMethod("color", (Class<?>[]) null);
			} catch (NoSuchMethodException e) {
				// el metodo no existe: metodoColor mantiene el valor null.
			}

			// Dibuja los circulos del dibujo
			for (Circulo c: dibujo.circulos()) {
				// selecciona el color del circulo
				if (metodoColor == null) {
					// La clase Circulo no tiene metodo color()
					g.setColor(Color.BLACK);

				} else {
					// La clase Circulo tiene metodo color()
					try {
						g.setColor((Color) metodoColor.invoke(c, (Object[]) null));
					} catch (IllegalAccessException e) {
						e.printStackTrace();
						System.exit(-1); // fatal error
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
						System.exit(-1); // fatal error
					} catch (InvocationTargetException e) {
						e.printStackTrace();
						System.exit(-1); // fatal error
					}
				}

				// dibuja el circulo con el color anteriormente elegido
				g.fillOval((int) c.centroX() - c.radio(),
						(int) c.centroY() - c.radio(),
						c.radio() * 2, c.radio() * 2);
			}

			// para evitar los saltos en los movimientos de los circulos
			Toolkit.getDefaultToolkit().sync();
		}	
	}

}
