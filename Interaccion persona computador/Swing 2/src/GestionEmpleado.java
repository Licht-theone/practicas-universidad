

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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollBar;


public class GestionEmpleado {
	private JFrame frmGestionEmpleado;
	private ResourceBundle textos = ResourceBundle.getBundle("datos", Main.localizacion);
	private JTextField txtID;
	private JTextField txtNombre;
	private JTextField txtCargo;
	private JTextField txtDept;
	private JTextField txtEmail;
	private JTextField txtTlf;
	private JTable table;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionEmpleado window = new GestionEmpleado();
					window.frmGestionEmpleado.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GestionEmpleado() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frmGestionEmpleado = new JFrame();
		frmGestionEmpleado.setResizable(false);
		frmGestionEmpleado.setTitle("Gestion Empleado");
		frmGestionEmpleado.setBounds(100, 100, 450, 300);
		frmGestionEmpleado.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmGestionEmpleado.setSize(600, 600);
		frmGestionEmpleado.setVisible(true);
		String[] columnas = {textos.getString("idEmpleado"), textos.getString("nombre"), 
				textos.getString("puesto"), textos.getString("dept"), "Email", textos.getString("tlf")};
		DefaultTableModel model = new DefaultTableModel(columnas, 0);
		JPanel pnlPanelFicha = new JPanel();
		frmGestionEmpleado.getContentPane().add(pnlPanelFicha, BorderLayout.CENTER);
		pnlPanelFicha.setLayout(null);


		JLabel lblID = new JLabel(textos.getString("idEmpleado"));
		lblID.setBounds(26, 57, 100, 20);
		pnlPanelFicha.add(lblID);

		JLabel lblNombre = new JLabel(textos.getString("nombre"));
		lblNombre.setBounds(26, 88, 100, 18);
		pnlPanelFicha.add(lblNombre);

		JLabel lbCargo = new JLabel(textos.getString("puesto"));
		lbCargo.setBounds(26, 117, 81, 20);
		pnlPanelFicha.add(lbCargo);

		JLabel lbDepartamento = new JLabel(textos.getString("dept"));
		lbDepartamento.setBounds(26, 148, 86, 20);
		pnlPanelFicha.add(lbDepartamento);

		JLabel lbCorreo = new JLabel("Email");
		lbCorreo.setBounds(31, 182, 94, 20);
		pnlPanelFicha.add(lbCorreo);

		JLabel lblTelefono = new JLabel(textos.getString("tlf"));
		lblTelefono.setBounds(26, 213, 81, 19);
		pnlPanelFicha.add(lblTelefono);

		JButton btnNuevo = new JButton(textos.getString("limpiar"));
		btnNuevo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtCargo.setText("");
				txtDept.setText("");
				txtEmail.setText("");
				txtID.setText("");
				txtNombre.setText("");
				txtTlf.setText("");
			}
		});

		btnNuevo.setBounds(10, 293, 86, 29);
		pnlPanelFicha.add(btnNuevo);

		JButton btnGuardar = new JButton(textos.getString("guardar"));
		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object[] newrow = {txtID.getText(), txtNombre.getText(),
						txtCargo.getText(), txtDept.getText(), txtEmail.getText(), txtTlf.getText()};
				model.addRow(newrow);
			}
		});

		btnGuardar.setBounds(227, 293, 86, 29);
		pnlPanelFicha.add(btnGuardar);

		JButton btnActualizar = new JButton(textos.getString("actualizar"));
		btnActualizar.setBounds(116, 293, 86, 29);
		pnlPanelFicha.add(btnActualizar);

		JButton btnEliminar = new JButton(textos.getString("eliminar"));
		btnEliminar.setBounds(339, 293, 86, 29);
		pnlPanelFicha.add(btnEliminar);

		JButton btnBuscar = new JButton(textos.getString("buscar"));

		btnBuscar.setBounds(448, 292, 81, 31);
		pnlPanelFicha.add(btnBuscar);
		
		txtID = new JTextField();

		txtID.setBounds(116, 57, 197, 20);
		pnlPanelFicha.add(txtID);
		txtID.setColumns(10);
		
		txtNombre = new JTextField();
		
		txtNombre.setBounds(116, 88, 197, 20);
		pnlPanelFicha.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtCargo = new JTextField();

		txtCargo.setBounds(116, 119, 197, 20);
		pnlPanelFicha.add(txtCargo);
		txtCargo.setColumns(10);
		
		txtDept = new JTextField();

		txtDept.setBounds(116, 148, 197, 20);
		pnlPanelFicha.add(txtDept);
		txtDept.setColumns(10);
		
		txtEmail = new JTextField();

		txtEmail.setBounds(116, 182, 197, 20);
		pnlPanelFicha.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtTlf = new JTextField();

		txtTlf.setBounds(117, 212, 197, 20);
		pnlPanelFicha.add(txtTlf);
		txtTlf.setColumns(10);
		
		table = new JTable(model);
		table.setBounds(0, 346, 586, 217);
		pnlPanelFicha.add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 333, 586, 230);
		pnlPanelFicha.add(scrollPane);

	}
}
