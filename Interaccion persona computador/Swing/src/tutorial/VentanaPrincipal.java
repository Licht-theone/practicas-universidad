package tutorial;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.TitledBorder;

import VentanaGuardar;
import dominio.Cliente;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPrincipal {

	private JFrame frmFichaDeAsegurados;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JLabel lblBarraEstado;
	private JComboBox cbProvincias;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
					window.frmFichaDeAsegurados.setVisible(true);
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
		frmFichaDeAsegurados = new JFrame();
		frmFichaDeAsegurados.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/imagenes/iconoTitulo.gif")));
		frmFichaDeAsegurados.setTitle("Ficha de asegurados");
		frmFichaDeAsegurados.setResizable(false);
		frmFichaDeAsegurados.setBounds(100, 100, 450, 300);
		frmFichaDeAsegurados.setSize(420, 300);
		frmFichaDeAsegurados.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JToolBar tbBarraHerramientas = new JToolBar();
		frmFichaDeAsegurados.getContentPane().add(tbBarraHerramientas, BorderLayout.NORTH);

		JButton btnGuardartb = new JButton("");
		btnGuardartb.setToolTipText("Guardar");
		btnGuardartb.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/save.gif")));
		tbBarraHerramientas.add(btnGuardartb);

		lblBarraEstado = new JLabel("Bienvenido a mi primera aplicacion...");
		frmFichaDeAsegurados.getContentPane().add(lblBarraEstado, BorderLayout.SOUTH);

		JPanel pnlPanelFicha = new JPanel();
		frmFichaDeAsegurados.getContentPane().add(pnlPanelFicha, BorderLayout.CENTER);
		pnlPanelFicha.setLayout(null);

		JPanel pnlFoto = new JPanel();
		pnlFoto.setBorder(new TitledBorder(null, "Foto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlFoto.setBounds(37, 46, 113, 105);
		pnlPanelFicha.add(pnlFoto);
		
		JLabel lblFoto = new JLabel("");
		pnlFoto.add(lblFoto);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(185, 46, 65, 12);
		pnlPanelFicha.add(lblNombre);

		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setBounds(185, 68, 65, 12);
		pnlPanelFicha.add(lblApellidos);

		JLabel lblProvincia = new JLabel("Provincia:");
		lblProvincia.setBounds(185, 90, 65, 12);
		pnlPanelFicha.add(lblProvincia);

		txtNombre = new JTextField();
		txtNombre.setBounds(254, 43, 96, 18);
		pnlPanelFicha.add(txtNombre);
		txtNombre.setColumns(10);

		txtApellidos = new JTextField();
		txtApellidos.setBounds(254, 65, 96, 18);
		pnlPanelFicha.add(txtApellidos);
		txtApellidos.setColumns(10);

		cbProvincias = new JComboBox();
		cbProvincias.setModel(new DefaultComboBoxModel(new String[] {"Cantabria", "Vizcaya", "Burgos", "Asturias", "Palencia"}));
		cbProvincias.setBounds(254, 86, 96, 20);
		pnlPanelFicha.add(cbProvincias);

		JButton btnCargarFoto = new JButton("Cargar Foto...");
		btnCargarFoto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser fcAbrir = new JFileChooser();
				int valorDevuelto = fcAbrir.showOpenDialog(null);
				//Recoger el nombre del fichero seleccionado por el usuario
				if (valorDevuelto == JFileChooser.APPROVE_OPTION) {
				File file = fcAbrir.getSelectedFile();
				//En este punto la aplicación se debería encargar de
				//realizar la operación sobre el fichero
				System.out.println("Fichero seleccionado: " +
				file.getName());
				lblFoto.setIcon(new ImageIcon(file.getAbsolutePath()));
				}
			}
		});
		btnCargarFoto.setBounds(37, 161, 113, 20);
		pnlPanelFicha.add(btnCargarFoto);

		JButton btnGuardarBot = new JButton("Guardar");
		btnGuardarBot.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/save.gif")));
		btnGuardarBot.setBounds(160, 161, 105, 20);
		pnlPanelFicha.add(btnGuardarBot);

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		btnGuardartb.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				btnGuardartbMouseClicked(evt);
			}
		});
		btnGuardarBot.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				btnGuardartbMouseClicked(evt);
			}
		});
		btnSalir.setBounds(281, 161, 84, 20);
		pnlPanelFicha.add(btnSalir);
		
		JButton btnAnimado = new JButton("");
		btnAnimado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAnimado.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/imagen2.gif")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnAnimado.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/imagen1.gif")));
			}
		});
		btnAnimado.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/imagen1.gif")));
		btnAnimado.setBounds(0, 0, 84, 44);
		pnlPanelFicha.add(btnAnimado);
	}
	private void btnGuardartbMouseClicked(MouseEvent evt) {
		lblBarraEstado.setText("Ficha guardada.");
		VentanaGuardar vent = new VentanaGuardar();
		vent.setCliente(new Cliente (txtNombre.getText(),
		txtApellidos.getText(), (String)
		cbProvincias.getSelectedItem()));
		vent.setVisible(true);
	}
}
