import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class GestionVideojuegos {

	// Se declaran como campos de clase para poder acceder a ellos desde diferentes métodos
	private JFrame frmInterfazVideojuegos;
	private JTextField txtIdJuego;
	private JTextField txtTitulo;
	private JTextField txtPrecio;
	private JTextField txtStock;
	private JComboBox cbPlataforma;
	private JComboBox cbGenero;
	private ResourceBundle textos = ResourceBundle.getBundle("datos", Main.localizacion);
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionVideojuegos window = new GestionVideojuegos();
					window.frmInterfazVideojuegos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GestionVideojuegos() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmInterfazVideojuegos = new JFrame();
		frmInterfazVideojuegos.setResizable(false);
		frmInterfazVideojuegos.setTitle("Gestión de Videojuegos");
		frmInterfazVideojuegos.setBounds(100, 100, 600, 600);
		frmInterfazVideojuegos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmInterfazVideojuegos.setVisible(true);
		String[] columnas = {textos.getString("idJuego"), textos.getString("titulo"), textos.getString("genero"),
				textos.getString("plataforma"), textos.getString("precio"), textos.getString("stock")};
		DefaultTableModel model = new DefaultTableModel(columnas, 0);
		JPanel pnlPanelFicha = new JPanel();
		frmInterfazVideojuegos.getContentPane().add(pnlPanelFicha, BorderLayout.CENTER);
		pnlPanelFicha.setLayout(null);
		
		JLabel lblIdJuego = new JLabel(textos.getString("idJuego"));
		lblIdJuego.setBounds(20, 31, 140, 15);
		pnlPanelFicha.add(lblIdJuego);
		
		JLabel lblTitulo = new JLabel(textos.getString("titulo"));
		lblTitulo.setBounds(20, 61, 140, 15);
		pnlPanelFicha.add(lblTitulo);
		
		JLabel lblGenero = new JLabel(textos.getString("genero"));
		lblGenero.setBounds(20, 93, 140, 15);
		pnlPanelFicha.add(lblGenero);
		
		JLabel lblPlataforma = new JLabel(textos.getString("plataforma"));
		lblPlataforma.setBounds(20, 123, 140, 15);
		pnlPanelFicha.add(lblPlataforma);
		
		JLabel lblPrecio = new JLabel(textos.getString("precio"));
		lblPrecio.setBounds(20, 151, 140, 15);
		pnlPanelFicha.add(lblPrecio);
		
		JLabel lblStock = new JLabel(textos.getString("stock"));
		lblStock.setBounds(20, 177, 140, 15);
		pnlPanelFicha.add(lblStock);
		

		txtIdJuego = new JTextField();
		txtIdJuego.setBounds(130, 30, 140, 15);
		txtIdJuego.setColumns(10);
		pnlPanelFicha.add(txtIdJuego);
		
		txtTitulo = new JTextField();
		txtTitulo.setBounds(130, 60, 140, 15);
		pnlPanelFicha.add(txtTitulo);
		txtTitulo.setColumns(10);
		
		cbPlataforma = new JComboBox();
		cbPlataforma.setBounds(130, 119, 140, 20);
		cbPlataforma.setModel(new DefaultComboBoxModel(new String[] {"PC", "PlayStation", "XBox", "Nintendo Switch", "Nintendo DS", "Wii"}));
		cbPlataforma.setBackground(new Color(255, 255, 255));
		pnlPanelFicha.add(cbPlataforma);
		
		cbGenero = new JComboBox();
		cbGenero.setBounds(130, 90, 140, 20);
		cbGenero.setModel(new DefaultComboBoxModel(new String[] {"Acción", "Aventuras", "Deportes", "RPG", "Shooter", "Plataformas", "Estrategia"}));
		cbGenero.setBackground(Color.WHITE);
		pnlPanelFicha.add(cbGenero);
		
		txtPrecio = new JTextField();
		txtPrecio.setBounds(130, 150, 140, 15);
		pnlPanelFicha.add(txtPrecio);
		txtPrecio.setColumns(10);
		
		txtStock = new JTextField();
		txtStock.setBounds(130, 176, 140, 15);
		pnlPanelFicha.add(txtStock);
		txtStock.setColumns(10);
		
		JButton btnActualizar = new JButton(textos.getString("actualizar"));
		btnActualizar.setBounds(130, 236, 100, 25);
		pnlPanelFicha.add(btnActualizar);
		
		JButton btnGuardar = new JButton(textos.getString("guardar"));
		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (Integer.parseInt(txtPrecio.getText()) > 0 && Integer.parseInt(txtStock.getText()) > 0) {
					Object[] newrow = {txtIdJuego.getText(), txtTitulo.getText(), cbGenero.getSelectedItem().toString(),
							cbPlataforma.getSelectedItem().toString(), txtPrecio.getText(), txtStock.getText()};
					model.addRow(newrow);
				} else {
					return;
				}
			}
		});
		btnGuardar.setBounds(20, 236, 100, 25);
		pnlPanelFicha.add(btnGuardar);
		
		JButton btnEliminar = new JButton(textos.getString("eliminar"));
		btnEliminar.setBounds(240, 236, 100, 25);
		pnlPanelFicha.add(btnEliminar);
		
		JButton btnBuscar = new JButton(textos.getString("buscar"));
		btnBuscar.setBounds(350, 236, 100, 25);
		pnlPanelFicha.add(btnBuscar);
		
		JButton btnLimpiar = new JButton(textos.getString("limpiar"));
		btnLimpiar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtIdJuego.setText("");
				txtPrecio.setText("");
				txtStock.setText("");
				txtTitulo.setText("");
			}
		});
		btnLimpiar.setBounds(460, 236, 100, 25);
		pnlPanelFicha.add(btnLimpiar);
		
		table = new JTable(model);
		table.setBounds(0, 282, 586, 281);
		pnlPanelFicha.add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 304, 586, 259);
		pnlPanelFicha.add(scrollPane);
		
	}
}


