import java.awt.EventQueue;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPrincipal {

	private JFrame frame;
	private ResourceBundle textos = ResourceBundle.getBundle("datos", Main.localizacion);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Inicio");
		frame.getContentPane().setLayout(null);
		
		JButton btnEmpleado = new JButton(textos.getString("empleados"));
		btnEmpleado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GestionEmpleado ge = new GestionEmpleado();
			}
		});
		btnEmpleado.setBounds(157, 61, 116, 22);
		frame.getContentPane().add(btnEmpleado);
		
		JButton btnVenta = new JButton(textos.getString("venta"));
		btnVenta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GestionVentas gv = new GestionVentas();
			}
		});
		btnVenta.setBounds(157, 105, 116, 22);
		frame.getContentPane().add(btnVenta);
		
		JButton btnTransaccion = new JButton(textos.getString("transaccion"));
		btnTransaccion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GestionVideojuegos gvv = new GestionVideojuegos();
			}
		});
		
		btnTransaccion.setBounds(157, 151, 116, 22);
		frame.getContentPane().add(btnTransaccion);
		frame.setVisible(true);
	}
}


