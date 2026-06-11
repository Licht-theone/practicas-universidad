import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;

import javax.swing.JPanel;


import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class GestionVentas {

	private JFrame frmGestionVentas;
	private ResourceBundle textos = ResourceBundle.getBundle("datos", Main.localizacion);
	private JTextField txtCantidad;
	private JTextField txtFecha;
	private JTextField txtID;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionVentas window = new GestionVentas();
					window.frmGestionVentas.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GestionVentas() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmGestionVentas = new JFrame();
		frmGestionVentas.setResizable(false);
		frmGestionVentas.setTitle("Gestion Ventas/Inventario");
		frmGestionVentas.setBounds(100, 100, 450, 300);
		frmGestionVentas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmGestionVentas.setSize(600, 600);
		frmGestionVentas.setVisible(true);
		String[] columnas = {textos.getString("idTrans"), textos.getString("fecha"), 
				textos.getString("movimiento"), textos.getString("cantidad"), textos.getString("operacion"), textos.getString("empleado")};
		DefaultTableModel model = new DefaultTableModel(columnas, 0);
		JPanel pnlPanelFicha = new JPanel();
		frmGestionVentas.getContentPane().add(pnlPanelFicha, BorderLayout.CENTER);
		pnlPanelFicha.setLayout(null);


		JLabel lblID = new JLabel(textos.getString("idTrans"));
		lblID.setBounds(31, 73, 100, 16);
		pnlPanelFicha.add(lblID);

		JLabel lbFecha = new JLabel(textos.getString("fecha"));
		lbFecha.setBounds(31, 100, 100, 12);
		pnlPanelFicha.add(lbFecha);
		
		JLabel lbEmpleado = new JLabel(textos.getString("empleado"));
		lbEmpleado.setBounds(31, 123, 81, 20);
		pnlPanelFicha.add(lbEmpleado);
		
		JLabel lbVendido = new JLabel(textos.getString("movimiento"));
		lbVendido.setBounds(31, 154, 94, 20);
		pnlPanelFicha.add(lbVendido);
		
		JLabel lbTipoOperacion = new JLabel(textos.getString("operacion"));
		lbTipoOperacion.setBounds(31, 185, 94, 11);
		pnlPanelFicha.add(lbTipoOperacion);
		
		JButton btnRegistrarOP = new JButton(textos.getString("guardar"));
		
		
		btnRegistrarOP.setBounds(19, 291, 100, 24);
		pnlPanelFicha.add(btnRegistrarOP);
		
		JButton btnLimpiar = new JButton(textos.getString("limpiar"));
		btnLimpiar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtCantidad.setText("");
				txtID.setText("");
				txtFecha.setText("");
				
			}
		});
		
		btnLimpiar.setBounds(129, 291, 99, 24);
		pnlPanelFicha.add(btnLimpiar);
		
		JButton btnEliminar = new JButton(textos.getString("eliminar"));
		btnEliminar.setBounds(451, 291, 89, 24);
		pnlPanelFicha.add(btnEliminar);
		
		JButton btnBuscar = new JButton(textos.getString("buscar"));
		
		btnBuscar.setBounds(238, 291, 95, 24);
		pnlPanelFicha.add(btnBuscar);
		
		JComboBox comboBoxResponsable = new JComboBox();
		comboBoxResponsable.setModel(new DefaultComboBoxModel(new String [] {"Torino", "Mato", "Tino"}));
		comboBoxResponsable.setBounds(129, 123, 204, 20);
		pnlPanelFicha.add(comboBoxResponsable);
		
		JComboBox comboBoxVideojuego = new JComboBox();
		comboBoxVideojuego.setModel(new DefaultComboBoxModel(new String [] {"Mario Odyssey", "Uncharted", "Donkey Kong"}));
		comboBoxVideojuego.setBounds(129, 154, 204, 20);
		pnlPanelFicha.add(comboBoxVideojuego);
		
		JComboBox comboBoxOperacion = new JComboBox();
		comboBoxOperacion.setModel(new DefaultComboBoxModel(new String[] {"Venta", "Entrada", "Salida"}));
		comboBoxOperacion.setBounds(129, 180, 204, 20);
		pnlPanelFicha.add(comboBoxOperacion);
		
		JButton btnActualizar = new JButton(textos.getString("actualizar"));

			
		btnActualizar.setBounds(343, 291, 98, 24);
		pnlPanelFicha.add(btnActualizar);
		
		JLabel lblCantidad = new JLabel(textos.getString("cantidad")); 
		lblCantidad.setBounds(31, 207, 60, 14);
		pnlPanelFicha.add(lblCantidad);
		
		txtCantidad = new JTextField();
		txtCantidad.setBounds(129, 204, 204, 20);
		pnlPanelFicha.add(txtCantidad);
		txtCantidad.setColumns(10);
		
		txtFecha = new JTextField();

		txtFecha.setBounds(129, 96, 204, 20);
		pnlPanelFicha.add(txtFecha);
		txtFecha.setColumns(10);
		
		txtID = new JTextField();

		txtID.setBounds(129, 71, 204, 20);
		pnlPanelFicha.add(txtID);
		txtID.setColumns(10);
		
		table = new JTable(model);
		table.setBounds(0, 329, 586, 234);
		pnlPanelFicha.add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 351, 586, 212);
		pnlPanelFicha.add(scrollPane);
		
		btnRegistrarOP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (Integer.parseInt(txtCantidad.getText()) > 0) {
					Object[] newrow = {txtID.getText(), txtFecha.getText(), comboBoxVideojuego.getSelectedItem().toString(),
							txtCantidad.getText(), comboBoxOperacion.getSelectedItem().toString(), comboBoxResponsable.getSelectedItem().toString()};
					model.addRow(newrow);
				} else {
					return;
				}
		
			}
		});
		


	}
}
