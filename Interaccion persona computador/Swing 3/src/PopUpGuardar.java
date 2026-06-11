import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PopUpGuardar {

	private JFrame frmGuardado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PopUpGuardar window = new PopUpGuardar();
					window.frmGuardado.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PopUpGuardar() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGuardado = new JFrame();
		frmGuardado.setTitle("Guardado");
		frmGuardado.setBounds(100, 100, 450, 300);
		frmGuardado.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JButton btnNewButton = new JButton("Cerrar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmGuardado.dispose();
			}
		});
		frmGuardado.getContentPane().add(btnNewButton, BorderLayout.SOUTH);
		
		JLabel lblNewLabel = new JLabel("Datos guardados");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frmGuardado.getContentPane().add(lblNewLabel, BorderLayout.CENTER);
	}

}
