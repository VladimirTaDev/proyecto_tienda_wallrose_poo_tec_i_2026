package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogProducto extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	
	private Integer codigoProducto;
	private boolean editando;
	private boolean soloLectura;
	
	private JLabel lblCodigoValor;
	private JTextField txtNombre;
	private JTextField txtExistencias;
	private JComboBox<String> comboUnidad;
	private JTextField txtPrecio;
	private JButton btnGuardar;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogProducto dialog = new DialogProducto();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Create the dialog.
	 */
	public DialogProducto() {
		initUI();
	}
	
	private void initUI() {
		setModal(true);
		setResizable(false);
		setTitle("Producto");
		setBounds(100, 100, 380, 300);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblCodigo = new JLabel("Código:");
		lblCodigo.setBounds(20, 20, 100, 25);
		getContentPane().add(lblCodigo);
		lblCodigoValor = new JLabel("(automático)");
		lblCodigoValor.setBounds(130, 20, 200, 25);
		getContentPane().add(lblCodigoValor);
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(20, 60, 100, 25);
		getContentPane().add(lblNombre);
		txtNombre = new JTextField();
		txtNombre.setBounds(130, 60, 200, 25);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		JLabel lblExistencias = new JLabel("Existencias:");
		lblExistencias.setBounds(20, 100, 100, 25);
		getContentPane().add(lblExistencias);
		txtExistencias = new JTextField();
		txtExistencias.setBounds(130, 100, 200, 25);
		getContentPane().add(txtExistencias);
		txtExistencias.setColumns(10);
		JLabel lblUnidad = new JLabel("Unidad:");
		lblUnidad.setBounds(20, 140, 100, 25);
		getContentPane().add(lblUnidad);
		comboUnidad = new JComboBox<String>();
		comboUnidad.setEditable(true);
		comboUnidad.setBounds(130, 140, 200, 25);
		comboUnidad.addItem("unidad");
		comboUnidad.addItem("kg");
		comboUnidad.addItem("g");
		comboUnidad.addItem("l");
		comboUnidad.addItem("ml");
		comboUnidad.addItem("m");
		comboUnidad.addItem("cm");
		getContentPane().add(comboUnidad);
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(20, 180, 100, 25);
		getContentPane().add(lblPrecio);
		txtPrecio = new JTextField();
		txtPrecio.setBounds(130, 180, 200, 25);
		getContentPane().add(txtPrecio);
		txtPrecio.setColumns(10);
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(120, 225, 100, 25);
		getContentPane().add(btnGuardar);
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(230, 225, 100, 25);
		getContentPane().add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
	
	public DialogProducto(Integer codigoProducto, boolean soloLectura) {
		this.codigoProducto = codigoProducto;
		this.editando = codigoProducto != null;
		this.soloLectura = soloLectura;
		initUI();
		
		// Cambia el título y el estado de los campos según el modo (agregar, editar, solo lectura)
		if (!editando) {
			setTitle("Agregar producto");
			lblCodigoValor.setText("(automático)");
		} else if (soloLectura) {
			setTitle("Ver producto");
		} else {
			setTitle("Editar producto");
		}
	}
}