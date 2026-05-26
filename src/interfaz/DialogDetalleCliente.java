package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JOptionPane;
import control.ControladoraWallRose;
import logica.Cliente;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import logica.OrdenCompra;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogDetalleCliente extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblEmail;
	private JLabel lblNombre;
	private JLabel lblId;
	private String idCliente;
	private JTable table;
	private JTable tablaOrdenesCliente;
	private JLabel lblTotalPendiente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogDetalleCliente dialog = new DialogDetalleCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogDetalleCliente() {
        this(null);
    }
	
	public DialogDetalleCliente(String idCliente) {
		this.idCliente = idCliente;
		
		setModal(true);
		setResizable(false);
		setTitle("Detalle cliente");
		setBounds(100, 100, 630, 630);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] {54, 46, 46, 46, 46, 46, 46};
		gbl_contentPanel.rowHeights = new int[] {14, 0, 0, 1, 0, 30};
		gbl_contentPanel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblId_text = new JLabel("ID:");
			GridBagConstraints gbc_lblId_text = new GridBagConstraints();
			gbc_lblId_text.anchor = GridBagConstraints.NORTHWEST;
			gbc_lblId_text.insets = new Insets(0, 0, 5, 5);
			gbc_lblId_text.gridx = 0;
			gbc_lblId_text.gridy = 0;
			contentPanel.add(lblId_text, gbc_lblId_text);
		}
		{
			lblId = new JLabel("");
			GridBagConstraints gbc_lblId = new GridBagConstraints();
			gbc_lblId.gridwidth = 6;
			gbc_lblId.anchor = GridBagConstraints.NORTHWEST;
			gbc_lblId.insets = new Insets(0, 0, 5, 0);
			gbc_lblId.gridx = 1;
			gbc_lblId.gridy = 0;
			contentPanel.add(lblId, gbc_lblId);
		}
		{
			JLabel lblNombre_text = new JLabel("Nombre:");
			GridBagConstraints gbc_lblNombre_text = new GridBagConstraints();
			gbc_lblNombre_text.anchor = GridBagConstraints.NORTHWEST;
			gbc_lblNombre_text.insets = new Insets(0, 0, 5, 5);
			gbc_lblNombre_text.gridx = 0;
			gbc_lblNombre_text.gridy = 1;
			contentPanel.add(lblNombre_text, gbc_lblNombre_text);
		}
		{
			lblNombre = new JLabel("");
			GridBagConstraints gbc_lblNombre = new GridBagConstraints();
			gbc_lblNombre.gridwidth = 6;
			gbc_lblNombre.anchor = GridBagConstraints.NORTHWEST;
			gbc_lblNombre.insets = new Insets(0, 0, 5, 0);
			gbc_lblNombre.gridx = 1;
			gbc_lblNombre.gridy = 1;
			contentPanel.add(lblNombre, gbc_lblNombre);
		}
		{
			JLabel lblEmail_text = new JLabel("Email:");
			GridBagConstraints gbc_lblEmail_text = new GridBagConstraints();
			gbc_lblEmail_text.anchor = GridBagConstraints.NORTHWEST;
			gbc_lblEmail_text.insets = new Insets(0, 0, 5, 5);
			gbc_lblEmail_text.gridx = 0;
			gbc_lblEmail_text.gridy = 2;
			contentPanel.add(lblEmail_text, gbc_lblEmail_text);
		}
		{
			lblEmail = new JLabel("");
			GridBagConstraints gbc_lblEmail = new GridBagConstraints();
			gbc_lblEmail.gridwidth = 6;
			gbc_lblEmail.insets = new Insets(0, 0, 5, 0);
			gbc_lblEmail.anchor = GridBagConstraints.NORTHWEST;
			gbc_lblEmail.gridx = 1;
			gbc_lblEmail.gridy = 2;
			contentPanel.add(lblEmail, gbc_lblEmail);
		}
		{
			JLabel lblOrdenes = new JLabel("Lista de órdenes:");
			GridBagConstraints gbc_lblOrdenes = new GridBagConstraints();
			gbc_lblOrdenes.anchor = GridBagConstraints.WEST;
			gbc_lblOrdenes.gridwidth = 7;
			gbc_lblOrdenes.insets = new Insets(0, 0, 5, 0);
			gbc_lblOrdenes.gridx = 0;
			gbc_lblOrdenes.gridy = 3;
			contentPanel.add(lblOrdenes, gbc_lblOrdenes);
		}
		{
			JScrollPane scrollOrdenes = new JScrollPane();
			GridBagConstraints gbc_scrollOrdenes = new GridBagConstraints();
			gbc_scrollOrdenes.insets = new Insets(0, 0, 5, 5);
			gbc_scrollOrdenes.gridwidth = 6;
			gbc_scrollOrdenes.fill = GridBagConstraints.BOTH;
			gbc_scrollOrdenes.gridx = 0;
			gbc_scrollOrdenes.gridy = 4;
			contentPanel.add(scrollOrdenes, gbc_scrollOrdenes);
			{
				tablaOrdenesCliente = new JTable();
				tablaOrdenesCliente.setModel(new DefaultTableModel(
					    new Object[][] {},
					    new String[] {
					        "Número", "Fecha", "Estado"
					    }
					) {
					    private static final long serialVersionUID = 1L;
					    @Override
					    public boolean isCellEditable(int row, int column) {
					        return false;
					    }
					});
				scrollOrdenes.setViewportView(tablaOrdenesCliente);
			}
		}
		{
			JPanel filterPanel = new JPanel();
			GridBagConstraints gbc_filterPanel = new GridBagConstraints();
			gbc_filterPanel.insets = new Insets(0, 0, 5, 0);
			gbc_filterPanel.fill = GridBagConstraints.BOTH;
			gbc_filterPanel.gridx = 6;
			gbc_filterPanel.gridy = 4;
			contentPanel.add(filterPanel, gbc_filterPanel);
			GridBagLayout gbl_filterPanel = new GridBagLayout();
			gbl_filterPanel.columnWidths = new int[] {30};
			gbl_filterPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
			gbl_filterPanel.columnWeights = new double[]{0.0};
			gbl_filterPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			filterPanel.setLayout(gbl_filterPanel);
			{
				JButton btnTodas = new JButton("Todas");
				btnTodas.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cargarOrdenesCliente();
					}
				});
				GridBagConstraints gbc_btnTodas = new GridBagConstraints();
				gbc_btnTodas.insets = new Insets(0, 0, 5, 0);
				gbc_btnTodas.gridx = 0;
				gbc_btnTodas.gridy = 1;
				filterPanel.add(btnTodas, gbc_btnTodas);
			}
			{
				JButton btnIniciadas = new JButton("Iniciadas");
				btnIniciadas.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cargarOrdenesIniciadasCliente();
					}
				});
				GridBagConstraints gbc_btnIniciadas = new GridBagConstraints();
				gbc_btnIniciadas.insets = new Insets(0, 0, 5, 0);
				gbc_btnIniciadas.gridx = 0;
				gbc_btnIniciadas.gridy = 3;
				filterPanel.add(btnIniciadas, gbc_btnIniciadas);
			}
			{
				JButton btnPendientes = new JButton("Pendientes");
				btnPendientes.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cargarOrdenesPendientesCliente();
					}
				});
				GridBagConstraints gbc_btnPendientes = new GridBagConstraints();
				gbc_btnPendientes.insets = new Insets(0, 0, 5, 0);
				gbc_btnPendientes.gridx = 0;
				gbc_btnPendientes.gridy = 5;
				filterPanel.add(btnPendientes, gbc_btnPendientes);
			}
			{
				JButton btnTerminadas = new JButton("Terminadas");
				btnTerminadas.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cargarOrdenesTerminadasCliente();
					}
				});
				GridBagConstraints gbc_btnTerminadas = new GridBagConstraints();
				gbc_btnTerminadas.gridx = 0;
				gbc_btnTerminadas.gridy = 7;
				filterPanel.add(btnTerminadas, gbc_btnTerminadas);
			}
		}
		{
			JLabel lblTotalPendienteFijo = new JLabel("Total pendiente:");
			GridBagConstraints gbc_lblTotalPendienteFijo = new GridBagConstraints();
			gbc_lblTotalPendienteFijo.insets = new Insets(0, 0, 0, 5);
			gbc_lblTotalPendienteFijo.gridx = 0;
			gbc_lblTotalPendienteFijo.gridy = 5;
			contentPanel.add(lblTotalPendienteFijo, gbc_lblTotalPendienteFijo);
		}
		{
			lblTotalPendiente = new JLabel("");
			GridBagConstraints gbc_lblTotalPendiente = new GridBagConstraints();
			gbc_lblTotalPendiente.anchor = GridBagConstraints.WEST;
			gbc_lblTotalPendiente.gridwidth = 6;
			gbc_lblTotalPendiente.gridx = 1;
			gbc_lblTotalPendiente.gridy = 5;
			contentPanel.add(lblTotalPendiente, gbc_lblTotalPendiente);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
			JPanel infoPanel = new JPanel();
			getContentPane().add(infoPanel, BorderLayout.NORTH);
			{
				table = new JTable();
				infoPanel.add(table);
			}
		}
		
		if (idCliente != null) {
		    cargarDatosCliente();
		}
	}
	
	// Carga los datos del cliente en los campos de texto 
		private void cargarDatosCliente() {
		    try {
		        ControladoraWallRose control = ControladoraWallRose.obtenerInstancia();
		        Cliente cliente = control.obtenerCliente(idCliente);
		        if (cliente == null) {
		            throw new IllegalArgumentException("No existe el cliente seleccionado.");
		        }
		        lblId.setText(cliente.getId());
		        lblNombre.setText(cliente.getNombre());
		        lblEmail.setText(cliente.getEmail());
		        lblTotalPendiente.setText(String.valueOf(cliente.calcularMontoPendiente()));
		        cargarOrdenesCliente();
		    } catch (Exception e) {
		        JOptionPane.showMessageDialog(
		            this,
		            "Error al cargar cliente: " + e.getMessage(),
		            "Error",
		            JOptionPane.ERROR_MESSAGE
		        );
		        dispose();
		    }
		}
		
		// Carga las órdenes de compra del cliente en la tabla
		private void cargarOrdenesEnTabla(List<OrdenCompra> ordenes) {
		    DefaultTableModel model = (DefaultTableModel) tablaOrdenesCliente.getModel();
		    model.setRowCount(0);
		    for (OrdenCompra orden : ordenes) {
		        Object[] fila = new Object[] {
		            orden.getNumero(),
		            orden.getFecha(),
		            orden.getEstado().name()
		        };
		        model.addRow(fila);
		    }
		}
		
		// Obtiene las órdenes de compra del cliente y las muestra en la tabla
		private void cargarOrdenesCliente() {
		    try {
		        ControladoraWallRose control = ControladoraWallRose.obtenerInstancia();
		        List<OrdenCompra> ordenes = control.obtenerListadoOrdenesCliente(idCliente);
		        cargarOrdenesEnTabla(ordenes);
		    } catch (Exception e) {
		        JOptionPane.showMessageDialog(
		            this,
		            "Error al cargar órdenes del cliente: " + e.getMessage(),
		            "Error",
		            JOptionPane.ERROR_MESSAGE
		        );
		    }
		}
		
		// Carga las órdenes de compra iniciadas del cliente en la tabla
		private void cargarOrdenesIniciadasCliente() {
		    try {
		        ControladoraWallRose control = ControladoraWallRose.obtenerInstancia();
		        List<OrdenCompra> ordenes = control.obtenerListadoOrdenesIniciadasCliente(idCliente);
		        cargarOrdenesEnTabla(ordenes);
		    } catch (Exception e) {
		        JOptionPane.showMessageDialog(
		            this,
		            "Error al cargar órdenes iniciadas: " + e.getMessage(),
		            "Error",
		            JOptionPane.ERROR_MESSAGE
		        );
		    }
		}

		// Carga las órdenes de compra pendientes del cliente en la tabla
		private void cargarOrdenesPendientesCliente() {
		    try {
		        ControladoraWallRose control = ControladoraWallRose.obtenerInstancia();
		        List<OrdenCompra> ordenes = control.obtenerListadoOrdenesPendientesCliente(idCliente);
		        cargarOrdenesEnTabla(ordenes);
		    } catch (Exception e) {
		        JOptionPane.showMessageDialog(
		            this,
		            "Error al cargar órdenes pendientes: " + e.getMessage(),
		            "Error",
		            JOptionPane.ERROR_MESSAGE
		        );
		    }
		}

		// Carga las órdenes de compra pendientes del cliente en la tabla
		private void cargarOrdenesTerminadasCliente() {
		    try {
		        ControladoraWallRose control = ControladoraWallRose.obtenerInstancia();
		        List<OrdenCompra> ordenes = control.obtenerListadoOrdenesTerminadasCliente(idCliente);
		        cargarOrdenesEnTabla(ordenes);
		    } catch (Exception e) {
		        JOptionPane.showMessageDialog(
		            this,
		            "Error al cargar órdenes terminadas: " + e.getMessage(),
		            "Error",
		            JOptionPane.ERROR_MESSAGE
		        );
		    }
		}

}
