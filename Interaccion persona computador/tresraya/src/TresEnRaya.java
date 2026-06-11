import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TresEnRaya {


	private JFrame frmTresEnRaya;
	private char turno = 'X';
	private int marcadorX = 0;
	private int marcadorO = 0;
	private JLabel lblTurno, lblMarcador;
	private JButton btnReiniciar;
	private JButton[][] botones = new JButton[3][3];
	private String nombre1 = "Jugador X";
	private String nombre2 = "Jugador O";
	private JButton btnCerrar;
	private JButton btnLimpiar;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TresEnRaya window = new TresEnRaya();
					window.frmTresEnRaya.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public TresEnRaya() {
		String input1 = JOptionPane.showInputDialog("Nombre del Jugador X:");
		if (input1 != null && !input1.trim().isEmpty()) nombre1 = input1;
		

		String input2 = JOptionPane.showInputDialog("Nombre del Jugador O:");
		if (input2 != null && !input2.trim().isEmpty()) nombre2 = input2;
		initialize();
	}


	private void initialize() {

		frmTresEnRaya = new JFrame();
		frmTresEnRaya.setTitle("Tres en Raya");
		frmTresEnRaya.setSize(400, 500);
		frmTresEnRaya.setLocationRelativeTo(null);//Centra la ventana en la pantalla
		frmTresEnRaya.getContentPane().setLayout(new BorderLayout(10, 10)); //10 píxeles de espacio entre componentes
		frmTresEnRaya.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		

		frmTresEnRaya.setIconImage(Toolkit.getDefaultToolkit().getImage(TresEnRaya.class.getResource("/imagenes/112.png")));




		JPanel panelSuperior = new JPanel();
		panelSuperior.setLayout(new GridLayout(2, 1));
		panelSuperior.setBorder(new EmptyBorder(13, 13, 0, 13)); 
		panelSuperior.setBackground(Color.WHITE);

		lblTurno = new JLabel("Turno: " + nombre1);
		lblTurno.setForeground(Color.BLUE);
		lblTurno.setFont(new Font("Arial", Font.BOLD, 16));
		lblTurno.setHorizontalAlignment(SwingConstants.CENTER);      
		lblMarcador = new JLabel(nombre1 + ": " + marcadorX + " | " + nombre2 + ": " + marcadorO);
		lblMarcador.setFont(new Font("Arial", Font.BOLD, 16));
		lblMarcador.setHorizontalAlignment(SwingConstants.CENTER);
		panelSuperior.add(lblTurno);
		panelSuperior.add(lblMarcador);       
		frmTresEnRaya.getContentPane().add(panelSuperior, BorderLayout.NORTH);

		JPanel panelTablero = new JPanel();
		panelTablero.setLayout(new GridLayout(3, 3, 5, 5)); 
		panelTablero.setBorder(new EmptyBorder(10, 10, 10, 10));


		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				botones[i][j] = new JButton("");
				botones[i][j].setFont(new Font("Arial", Font.BOLD, 40));
				botones[i][j].setFocusable(false);


				final JButton botonActual = botones[i][j];
				botonActual.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						jugada(botonActual);
					}
				});

				panelTablero.add(botones[i][j]);
			}
		}
		frmTresEnRaya.getContentPane().add(panelTablero, BorderLayout.CENTER);


		btnReiniciar = new JButton("Reiniciar partida");
		btnReiniciar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnReiniciar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reiniciarTablero(); 
			}
		});


		JPanel panelInferior = new JPanel(); 
		panelInferior.setBorder(new EmptyBorder(0, 10, 10, 10));
		panelInferior.setLayout(new BorderLayout());
		panelInferior.add(btnReiniciar, BorderLayout.CENTER);

		frmTresEnRaya.getContentPane().add(panelInferior, BorderLayout.SOUTH);
		
		btnCerrar = new JButton("Cerrar");
		btnCerrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmTresEnRaya.dispose();
				System.exit(0);
			}
		});
		panelInferior.add(btnCerrar, BorderLayout.WEST);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				reiniciarTablero();	
			}
		});
		panelInferior.add(btnLimpiar, BorderLayout.EAST);
	}


	private void jugada(JButton b) {

		if (!b.getText().equals("")) {
			return;
		}

		Toolkit.getDefaultToolkit().beep();

		b.setText(String.valueOf(turno));

		if(turno == 'X') {
			b.setForeground(Color.BLUE);
			//colores
			b.setBackground(Color.cyan);
		}
		else {
			b.setForeground(Color.RED);
			b.setBackground(Color.orange);
		}

		if (comprobarVictoria(turno)) {
			finPartida(turno);
		} 
		else if (esEmpate()) {
			finPartida('E'); 
		} 
		else {
			cambiarTurno();
		}
	}


	private void cambiarTurno() {
		if (turno == 'X') {
			turno = 'O';
			lblTurno.setText("Turno: " + nombre2);
			lblTurno.setForeground(Color.RED);
		} else {
			turno = 'X';
			lblTurno.setText("Turno: " + nombre1);
			lblTurno.setForeground(Color.BLUE); 
		}

	}

	private void finPartida(char ganador) {

		String nombreGanador;


		Toolkit.getDefaultToolkit().beep();

		if (ganador == 'E') {
		
			JOptionPane.showMessageDialog(this.frmTresEnRaya, 
					"¡Ha sido un empate!", 
					"Empate", 
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			
			if (ganador == 'X') {
				marcadorX++;
				nombreGanador = nombre1;
			} else {
				marcadorO++;
				nombreGanador = nombre2;
			}

			lblMarcador.setText(nombre1 + ": " + marcadorX + " | " + nombre2 + ": " + marcadorO);

			JOptionPane.showMessageDialog(this.frmTresEnRaya,"¡Ha ganado " +
					nombreGanador + "!","Victoria", JOptionPane.INFORMATION_MESSAGE);


			
		}
		reiniciarTablero();
	}

	private boolean esEmpate() {
		boolean resultadoEmpate=true;
		for (int i=0;i<3;i++) {
			for (int j=0;j<3;j++) {
				if (botones[i][j].getText().equals("")) resultadoEmpate= false;
			}
		}
		return resultadoEmpate;
	}
	private void reiniciarTablero() {

		Toolkit.getDefaultToolkit().beep();

		for (int i=0;i<3;i++) {
			for (int j=0;j<3;j++) {
				botones[i][j].setText("");
				botones[i][j].setBackground(null);
			}
		}
		turno = 'X';
		lblTurno.setText("Turno: " + nombre1);
		
		lblTurno.setForeground(Color.blue);


	}

	private boolean comprobarVictoria(char jugador) {
		String s = String.valueOf(jugador);
		boolean victoria = false;
		// Filas y columnas
		for (int i = 0; i < 3 && !victoria; i++) {
			if (botones[i][0].getText().equals(s) && botones[i][1].getText().equals(s) &&
					botones[i][2].getText().equals(s)) {
				victoria = true;
			}
			if (botones[0][i].getText().equals(s) && botones[1][i].getText().equals(s) && botones[2][i].getText().equals(s)){
				victoria = true;
			}
		}
		// Diagonales
		if (!victoria && botones[0][0].getText().equals(s) && botones[1][1].getText().equals(s) &&
				botones[2][2].getText().equals(s)) {
			victoria = true;
		}
		if (!victoria && botones[0][2].getText().equals(s) && botones[1][1].getText().equals(s) &&
				botones[2][0].getText().equals(s)) {
			victoria = true;
		}
		return victoria;
	}
}