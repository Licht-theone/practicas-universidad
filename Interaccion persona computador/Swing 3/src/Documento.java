import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.undo.UndoManager;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;

public class Documento {

	private JFrame frmword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Documento window = new Documento();
					window.frmword.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Documento() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		frmword = new JFrame();
		frmword.setTitle("\"Word\"");
		frmword.setBounds(100, 100, 800, 520);
		frmword.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmword.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar = new JToolBar();
		frmword.getContentPane().add(toolBar, BorderLayout.NORTH);
		JTextPane textPane = new JTextPane();
		frmword.getContentPane().add(textPane, BorderLayout.CENTER);
		textPane.setText("");
		UndoManager deshacedorRehacedor = new UndoManager();
		deshacedorRehacedor.setLimit(100);
		textPane.getDocument().addUndoableEditListener(new UndoableEditListener() {
			@Override
			public void undoableEditHappened(UndoableEditEvent e) {
				deshacedorRehacedor.addEdit(e.getEdit());
			}
		});
		
		JButton btnGuardar = new JButton("");
		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PopUpGuardar.main(null);
			}
		});
		btnGuardar.setIcon(new ImageIcon(Documento.class.getResource("/imagenes/save.gif")));
		toolBar.add(btnGuardar);

		JButton btnDeshacer = new JButton("");
		btnDeshacer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				deshacedorRehacedor.undo();
			}
		});
		btnDeshacer.setIcon(new ImageIcon(Documento.class.getResource("/imagenes/icons8-deshacer-30.png")));
		toolBar.add(btnDeshacer);

		JButton btnRehacer = new JButton("");
		btnRehacer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				deshacedorRehacedor.redo();
			}
		});
		btnRehacer.setIcon(new ImageIcon(Documento.class.getResource("/imagenes/icons8-rehacer-30.png")));
		toolBar.add(btnRehacer);

		JButton btnCopiar = new JButton("");
		btnCopiar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textPane.copy();
			}
		});
		btnCopiar.setIcon(new ImageIcon(Documento.class.getResource("/imagenes/icons8-copiar-30.png")));
		toolBar.add(btnCopiar);

		JButton btnCortar = new JButton("");
		btnCortar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textPane.cut();
			}
		});
		btnCortar.setIcon(new ImageIcon(Documento.class.getResource("/imagenes/icons8-cortar-30.png")));
		toolBar.add(btnCortar);

		JButton btnPegar = new JButton("");
		btnPegar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textPane.paste();
			}
		});
		btnPegar.setIcon(new ImageIcon(Documento.class.getResource("/imagenes/icons8-pegar-30.png")));
		toolBar.add(btnPegar);

		JButton btnNegrita = new JButton("");
		btnNegrita.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AttributeSet atributosAct = textPane.getCharacterAttributes();
				SimpleAttributeSet atributos = new SimpleAttributeSet();
				int inicioSeleccion = textPane.getSelectionStart();
				int finSeleccion = textPane.getSelectionEnd();
				StyledDocument doc = textPane.getStyledDocument();
				if (StyleConstants.isBold(atributosAct)) {
					StyleConstants.setBold(atributos, false);
				} else {
					StyleConstants.setBold(atributos, true);
				}
				doc.setCharacterAttributes(inicioSeleccion, finSeleccion, atributos, false);
			}
		});
		btnNegrita.setToolTipText("Negrita");
		btnNegrita.setIcon(new ImageIcon(Documento.class.getResource("/imagenes/icons8-negrita-30.png")));
		toolBar.add(btnNegrita);

		JButton btnCursiva = new JButton("");
		btnCursiva.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AttributeSet atributosAct = textPane.getCharacterAttributes();
				SimpleAttributeSet atributos = new SimpleAttributeSet();
				int inicioSeleccion = textPane.getSelectionStart();
				int finSeleccion = textPane.getSelectionEnd();
				StyledDocument doc = textPane.getStyledDocument();
				if (StyleConstants.isItalic(atributosAct)) {
					StyleConstants.setItalic(atributos, false);
				} else {
					StyleConstants.setItalic(atributos, true);
				}
				doc.setCharacterAttributes(inicioSeleccion, finSeleccion, atributos, false);
			}
		});
		btnCursiva.setToolTipText("Cursiva");
		btnCursiva.setIcon(new ImageIcon(Documento.class.getResource("/imagenes/icons8-cursiva-26.png")));
		toolBar.add(btnCursiva);

		JButton btnSubrayado = new JButton("");
		btnSubrayado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AttributeSet atributosAct = textPane.getCharacterAttributes();
				SimpleAttributeSet atributos = new SimpleAttributeSet();
				int inicioSeleccion = textPane.getSelectionStart();
				int finSeleccion = textPane.getSelectionEnd();
				StyledDocument doc = textPane.getStyledDocument();
				if (StyleConstants.isUnderline(atributosAct)) {
					StyleConstants.setUnderline(atributos, false);
				} else {
					StyleConstants.setUnderline(atributos, true);
				}
				doc.setCharacterAttributes(inicioSeleccion, finSeleccion, atributos, false);
			}
		});
		btnSubrayado.setToolTipText("Subrayado");
		btnSubrayado.setIcon(new ImageIcon(Documento.class.getResource("/imagenes/icons8-subrayar-30.png")));
		btnSubrayado.setVerticalAlignment(SwingConstants.BOTTOM);
		toolBar.add(btnSubrayado);

		JButton btnAlinIzq = new JButton("");
		btnAlinIzq.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SimpleAttributeSet atributos = new SimpleAttributeSet();
				int inicioSeleccion = textPane.getSelectionStart();
				int finSeleccion = textPane.getSelectionEnd();
				StyledDocument doc = textPane.getStyledDocument();
				StyleConstants.setAlignment(atributos, StyleConstants.ALIGN_LEFT);
				doc.setParagraphAttributes(inicioSeleccion, finSeleccion, atributos, false);
			}
		});
		btnAlinIzq.setToolTipText("Alinear Izquierda");
		btnAlinIzq.setIcon(new ImageIcon(Documento.class.getResource("/imagenes/icons8-alinear-a-la-izquierda-30.png")));
		toolBar.add(btnAlinIzq);

		JButton btnCentrado = new JButton("");
		btnCentrado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SimpleAttributeSet atributos = new SimpleAttributeSet();
				int inicioSeleccion = textPane.getSelectionStart();
				int finSeleccion = textPane.getSelectionEnd();
				StyledDocument doc = textPane.getStyledDocument();
				StyleConstants.setAlignment(atributos, StyleConstants.ALIGN_CENTER);
				doc.setParagraphAttributes(inicioSeleccion, finSeleccion, atributos, false);
			}
		});
		btnCentrado.setToolTipText("Centrar");
		btnCentrado.setIcon(new ImageIcon(Documento.class.getResource("/imagenes/icons8-centrado-30.png")));
		toolBar.add(btnCentrado);

		JButton btnAlinDer = new JButton("");
		btnAlinDer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SimpleAttributeSet atributos = new SimpleAttributeSet();
				int inicioSeleccion = textPane.getSelectionStart();
				int finSeleccion = textPane.getSelectionEnd();
				StyledDocument doc = textPane.getStyledDocument();
				StyleConstants.setAlignment(atributos, StyleConstants.ALIGN_RIGHT);
				doc.setParagraphAttributes(inicioSeleccion, finSeleccion, atributos, false);
			}
		});
		btnAlinDer.setToolTipText("Alinear Derecha");
		btnAlinDer.setIcon(new ImageIcon(Documento.class.getResource("/imagenes/icons8-alinear-a-la-derecha-30.png")));
		toolBar.add(btnAlinDer);

		JComboBox cbTipoLetra = new JComboBox();
		cbTipoLetra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleAttributeSet atributos = new SimpleAttributeSet();
				int inicioSeleccion = textPane.getSelectionStart();
				int finSeleccion = textPane.getSelectionEnd();
				StyledDocument doc = textPane.getStyledDocument();
				StyleConstants.setFontFamily(atributos, cbTipoLetra.getSelectedItem().toString());
				doc.setCharacterAttributes(inicioSeleccion, finSeleccion, atributos, false);
			}
		});
		
		JComboBox cbTamanhoLetra = new JComboBox();
		cbTamanhoLetra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int tamanho = Integer.parseInt(cbTamanhoLetra.getSelectedItem().toString());
				SimpleAttributeSet atributos = new SimpleAttributeSet();
				int inicioSeleccion = textPane.getSelectionStart();
				int finSeleccion = textPane.getSelectionEnd();
				StyledDocument doc = textPane.getStyledDocument();
				StyleConstants.setFontSize(atributos, tamanho);
				doc.setCharacterAttributes(inicioSeleccion, finSeleccion, atributos, false);
			}
		});
		cbTamanhoLetra.setModel(new DefaultComboBoxModel(new String[] {"4", "6", "8", "10", "12", "14", "16"}));
		toolBar.add(cbTamanhoLetra);
		cbTipoLetra.setModel(new DefaultComboBoxModel(new String[] {"Arial", "Times New Roman", "Calibri"}));
		toolBar.add(cbTipoLetra);

		

		JMenuBar menuBar = new JMenuBar();
		frmword.setJMenuBar(menuBar);

		JMenu mnArchivo = new JMenu("Archivo");
		mnArchivo.setMnemonic('A');
		menuBar.add(mnArchivo);

		JMenuItem mntmNuevo = new JMenuItem("Nuevo");
		mntmNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mntmNuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		mnArchivo.add(mntmNuevo);

		JMenuItem mntmAbrir = new JMenuItem("Abrir");
		mntmAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mntmAbrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
		mnArchivo.add(mntmAbrir);

		JMenuItem mntmGuardar = new JMenuItem("Guardar");
		mntmGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PopUpGuardar.main(null);
			}
		});
		mntmGuardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK));
		mnArchivo.add(mntmGuardar);

		JMenuItem mntmCerrar = new JMenuItem("Cerrar");
		mntmCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmword.dispose();
				System.exit(0);
			}
		});
		mntmCerrar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
		mnArchivo.add(mntmCerrar);

		JMenu mnEdicion = new JMenu("Edición");
		mnEdicion.setMnemonic('E');
		menuBar.add(mnEdicion);

		JMenuItem mntmCortar = new JMenuItem("Cortar");
		mntmCortar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textPane.cut();
			}
		});
		mntmCortar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
		mnEdicion.add(mntmCortar);

		JMenuItem mntmCopiar = new JMenuItem("Copiar");
		mntmCopiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textPane.copy();
			}
		});
		mntmCopiar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
		mnEdicion.add(mntmCopiar);

		JMenuItem mntmPegar = new JMenuItem("Pegar");
		mntmPegar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textPane.paste();
			}
		});
		mntmPegar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
		mnEdicion.add(mntmPegar);

		JMenuItem mntmDeshacer = new JMenuItem("Deshacer");
		mntmDeshacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deshacedorRehacedor.undo();
			}
		});
		mntmDeshacer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));
		mnEdicion.add(mntmDeshacer);

		JMenuItem mntmRehacer = new JMenuItem("Rehacer");
		mntmRehacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deshacedorRehacedor.redo();
			}
		});
		mnEdicion.add(mntmRehacer);

		JMenuItem mntmBuscar = new JMenuItem("Buscar");
		mntmBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String texto = JOptionPane.showInputDialog(frmword, "Introduce el texto a buscar:");
				if (texto != null && !texto.isEmpty()) {
					String contenido = textPane.getText();
					int indice = contenido.indexOf(texto);
					if (indice >= 0) {
						textPane.setCaretPosition(indice);
						textPane.moveCaretPosition(indice + texto.length());
						textPane.grabFocus();
					} else {
						JOptionPane.showMessageDialog(mntmBuscar, "No encontrado");
					}
				}
			}
		});
		mnEdicion.add(mntmBuscar);
		
		JMenuItem mntmReemplazar = new JMenuItem("Reemplazar");
		mntmReemplazar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField txtBuscar = new JTextField();
				JTextField txtReemplazar = new JTextField();

				JPanel panelInputs = new JPanel(new java.awt.GridLayout(0, 1));
				panelInputs.add(new JLabel("Texto a buscar:"));
				panelInputs.add(txtBuscar);
				panelInputs.add(new JLabel("Reemplazar con:"));
				panelInputs.add(txtReemplazar);
				int resultado = JOptionPane.showConfirmDialog(
				        frmword,       // Ventana padre (o contentPane)
				        panelInputs,          // El panel con los inputs
				        "Reemplazar Todo",    // Título
				        JOptionPane.OK_CANCEL_OPTION, 
				        JOptionPane.PLAIN_MESSAGE
				);
				if (resultado == JOptionPane.OK_OPTION) {
					String buscar = txtBuscar.getText();
					String reemplazar = txtReemplazar.getText();
					if (!buscar.isEmpty()) {
						String textoAct = textPane.getText();
						String textoNue = textoAct.replaceAll(buscar, reemplazar);
						textPane.setText(textoNue);
					}
				}
			}
		});
		mnEdicion.add(mntmReemplazar);

		JMenu mnFormato = new JMenu("Formato");
		mnFormato.setMnemonic('F');
		menuBar.add(mnFormato);

		JMenu mnAlineados = new JMenu("Alineados");
		mnFormato.add(mnAlineados);

		JMenuItem mntmCentrado = new JMenuItem("Centrado");
		mntmCentrado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleAttributeSet atributos = new SimpleAttributeSet();
				int inicioSeleccion = textPane.getSelectionStart();
				int finSeleccion = textPane.getSelectionEnd();
				StyledDocument doc = textPane.getStyledDocument();
				StyleConstants.setAlignment(atributos, StyleConstants.ALIGN_CENTER);
				doc.setParagraphAttributes(inicioSeleccion, finSeleccion, atributos, false);
			}
		});
		mnAlineados.add(mntmCentrado);

		JMenuItem mntmAlinIzq = new JMenuItem("Alinear Izquierda");
		mntmAlinIzq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleAttributeSet atributos = new SimpleAttributeSet();
				int inicioSeleccion = textPane.getSelectionStart();
				int finSeleccion = textPane.getSelectionEnd();
				StyledDocument doc = textPane.getStyledDocument();
				StyleConstants.setAlignment(atributos, StyleConstants.ALIGN_LEFT);
				doc.setParagraphAttributes(inicioSeleccion, finSeleccion, atributos, false);
			}
		});
		mnAlineados.add(mntmAlinIzq);

		JMenuItem mntmAlinDer = new JMenuItem("Alinear Derecha");
		mntmAlinDer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleAttributeSet atributos = new SimpleAttributeSet();
				int inicioSeleccion = textPane.getSelectionStart();
				int finSeleccion = textPane.getSelectionEnd();
				StyledDocument doc = textPane.getStyledDocument();
				StyleConstants.setAlignment(atributos, StyleConstants.ALIGN_RIGHT);
				doc.setParagraphAttributes(inicioSeleccion, finSeleccion, atributos, false);
			}
		});
		mnAlineados.add(mntmAlinDer);

		JMenu mnFormatoLetra = new JMenu("Formato de letra");
		mnFormato.add(mnFormatoLetra);

		JMenuItem mntmNegrita = new JMenuItem("Negrita");
		mntmNegrita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AttributeSet atributosAct = textPane.getCharacterAttributes();
				SimpleAttributeSet atributos = new SimpleAttributeSet();
				int inicioSeleccion = textPane.getSelectionStart();
				int finSeleccion = textPane.getSelectionEnd();
				StyledDocument doc = textPane.getStyledDocument();
				if (StyleConstants.isBold(atributosAct)) {
					StyleConstants.setBold(atributos, false);
				} else {
					StyleConstants.setBold(atributos, true);
				}
				doc.setCharacterAttributes(inicioSeleccion, finSeleccion, atributos, false);
			}
		});
		mnFormatoLetra.add(mntmNegrita);
		mntmNegrita.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_DOWN_MASK));

		JMenuItem mntmCursiva = new JMenuItem("Cursiva");
		mntmCursiva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AttributeSet atributosAct = textPane.getCharacterAttributes();
				SimpleAttributeSet atributos = new SimpleAttributeSet();
				int inicioSeleccion = textPane.getSelectionStart();
				int finSeleccion = textPane.getSelectionEnd();
				StyledDocument doc = textPane.getStyledDocument();
				if (StyleConstants.isItalic(atributosAct)) {
					StyleConstants.setItalic(atributos, false);
				} else {
					StyleConstants.setItalic(atributos, true);
				}
				doc.setCharacterAttributes(inicioSeleccion, finSeleccion, atributos, false);
			}
		});
		mnFormatoLetra.add(mntmCursiva);
		mntmCursiva.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.CTRL_DOWN_MASK));

		JMenuItem mntmSubrayado = new JMenuItem("Subrayado");
		mntmSubrayado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AttributeSet atributosAct = textPane.getCharacterAttributes();
				SimpleAttributeSet atributos = new SimpleAttributeSet();
				int inicioSeleccion = textPane.getSelectionStart();
				int finSeleccion = textPane.getSelectionEnd();
				StyledDocument doc = textPane.getStyledDocument();
				if (StyleConstants.isUnderline(atributosAct)) {
					StyleConstants.setUnderline(atributos, false);
				} else {
					StyleConstants.setUnderline(atributos, true);
				}
				doc.setCharacterAttributes(inicioSeleccion, finSeleccion, atributos, false);
			}
		});
		mnFormatoLetra.add(mntmSubrayado);
		mntmSubrayado.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_DOWN_MASK));

		JMenu mnColor = new JMenu("Color");
		mnFormato.add(mnColor);

		JMenuItem mntmNegro = new JMenuItem("Negro");
		mntmNegro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleAttributeSet atributos = new SimpleAttributeSet();
				int inicioSeleccion = textPane.getSelectionStart();
				int finSeleccion = textPane.getSelectionEnd();
				StyledDocument doc = textPane.getStyledDocument();
				StyleConstants.setForeground(atributos, Color.black);
				doc.setCharacterAttributes(inicioSeleccion, finSeleccion, atributos, false);
			}
		});
		mnColor.add(mntmNegro);

		JMenuItem mntmRojo = new JMenuItem("Rojo");
		mntmRojo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleAttributeSet atributos = new SimpleAttributeSet();
				int inicioSeleccion = textPane.getSelectionStart();
				int finSeleccion = textPane.getSelectionEnd();
				StyledDocument doc = textPane.getStyledDocument();
				StyleConstants.setForeground(atributos, Color.red);
				doc.setCharacterAttributes(inicioSeleccion, finSeleccion, atributos, false);
			}
		});
		mnColor.add(mntmRojo);

		JMenuItem mntmAzul = new JMenuItem("Azul");
		mntmAzul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleAttributeSet atributos = new SimpleAttributeSet();
				int inicioSeleccion = textPane.getSelectionStart();
				int finSeleccion = textPane.getSelectionEnd();
				StyledDocument doc = textPane.getStyledDocument();
				StyleConstants.setForeground(atributos, Color.blue);
				doc.setCharacterAttributes(inicioSeleccion, finSeleccion, atributos, false);
			}
		});
		mnColor.add(mntmAzul);
	}
	

}
