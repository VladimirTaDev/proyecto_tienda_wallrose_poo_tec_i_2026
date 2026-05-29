package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import control.ControladoraWallRose;
import logica.Cliente;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogCliente extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtEmail;
	private String idCliente;
	private boolean editando;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogCliente dialog = new DialogCliente(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogCliente(String idCliente) {
		this.idCliente = idCliente;
		this.editando = idCliente != null;

		setTitle(editando ? "Editar cliente" : "Agregar cliente");
		setModal(true);
		setResizable(false);
		setBounds(new Rectangle(100, 100, 360, 320));
		setTitle(editando ? "Editar cliente" : "Agregar cliente");
		setBounds(100, 100, 425, 180);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 400, 0 };
		gridBagLayout.rowHeights = new int[] { 100, 33, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagConstraints gbc_contentPanel = new GridBagConstraints();
		gbc_contentPanel.anchor = GridBagConstraints.NORTH;
		gbc_contentPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_contentPanel.insets = new Insets(0, 0, 5, 0);
		gbc_contentPanel.gridx = 0;
		gbc_contentPanel.gridy = 0;
		getContentPane().add(contentPanel, gbc_contentPanel);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 5, 5, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 25, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel = new JLabel("ID:");
			lblNewLabel.setPreferredSize(new Dimension(30, 30));
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.NORTHWEST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 0;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			txtId = new JTextField();
			txtId.setPreferredSize(new Dimension(20, 20));
			GridBagConstraints gbc_txtId = new GridBagConstraints();
			gbc_txtId.gridwidth = 3;
			gbc_txtId.insets = new Insets(0, 0, 5, 5);
			gbc_txtId.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtId.gridx = 1;
			gbc_txtId.gridy = 0;
			contentPanel.add(txtId, gbc_txtId);
			txtId.setColumns(10);
		}
		{
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setAlignmentX(5.0f);
			lblNombre.setAlignmentY(5.0f);
			GridBagConstraints gbc_lblNombre = new GridBagConstraints();
			gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
			gbc_lblNombre.anchor = GridBagConstraints.NORTHWEST;
			gbc_lblNombre.gridx = 0;
			gbc_lblNombre.gridy = 1;
			contentPanel.add(lblNombre, gbc_lblNombre);
		}
		{
			txtNombre = new JTextField();
			GridBagConstraints gbc_txtNombre = new GridBagConstraints();
			gbc_txtNombre.gridwidth = 3;
			gbc_txtNombre.insets = new Insets(0, 0, 5, 5);
			gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtNombre.gridx = 1;
			gbc_txtNombre.gridy = 1;
			contentPanel.add(txtNombre, gbc_txtNombre);
			txtNombre.setColumns(10);
		}
		{
			JLabel lblEmail = new JLabel("Email:");
			GridBagConstraints gbc_lblEmail = new GridBagConstraints();
			gbc_lblEmail.anchor = GridBagConstraints.WEST;
			gbc_lblEmail.insets = new Insets(0, 0, 0, 5);
			gbc_lblEmail.gridx = 0;
			gbc_lblEmail.gridy = 2;
			contentPanel.add(lblEmail, gbc_lblEmail);
		}
		{
			txtEmail = new JTextField();
			GridBagConstraints gbc_txtEmail = new GridBagConstraints();
			gbc_txtEmail.gridwidth = 3;
			gbc_txtEmail.insets = new Insets(0, 0, 0, 5);
			gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtEmail.gridx = 1;
			gbc_txtEmail.gridy = 2;
			contentPanel.add(txtEmail, gbc_txtEmail);
			txtEmail.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			GridBagConstraints gbc_buttonPane = new GridBagConstraints();
			gbc_buttonPane.anchor = GridBagConstraints.NORTH;
			gbc_buttonPane.fill = GridBagConstraints.HORIZONTAL;
			gbc_buttonPane.gridx = 0;
			gbc_buttonPane.gridy = 1;
			getContentPane().add(buttonPane, gbc_buttonPane);
			{
				JButton btnGuardar = new JButton("Guardar");
				btnGuardar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						guardarCliente();
					}
				});
				btnGuardar.setActionCommand("OK");
				buttonPane.add(btnGuardar);
				getRootPane().setDefaultButton(btnGuardar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}

		// Si estamos editando, cargamos los datos del cliente
		if (editando) {
			cargarCliente();
		}
	}

	private void cargarCliente() {
		ControladoraWallRose control = ControladoraWallRose.obtenerInstancia();
		Cliente cliente = control.obtenerCliente(idCliente);
		if (cliente == null) {
			JOptionPane.showMessageDialog(this, "No existe el cliente seleccionado.", "Error",
					JOptionPane.ERROR_MESSAGE);
			dispose();
			return;
		}
		txtId.setText(cliente.getId());
		txtNombre.setText(cliente.getNombre());
		txtEmail.setText(cliente.getEmail());
		txtId.setEditable(false);
	}

	private void guardarCliente() {
		try {
			String id = txtId.getText().trim();
			String nombre = txtNombre.getText().trim();
			String email = txtEmail.getText().trim();
			validarCampos(id, nombre, email);
			ControladoraWallRose control = ControladoraWallRose.obtenerInstancia();
			if (editando) {
				control.actualizarCliente(idCliente, nombre, email);
			} else {
				control.crearCliente(id, nombre, email);
			}
			dispose();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error al guardar cliente: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	// Valida que los campos no estén vacíos
	private void validarCampos(String id, String nombre, String email) {
		if (id.equals("") || nombre.equals("") || email.equals("")) {
			throw new IllegalArgumentException("Debe completar todos los campos.");
		}
	}

}
