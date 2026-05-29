package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import control.ControladoraWallRose;
import logica.Cliente;
import logica.Producto;
import logica.OrdenCompra;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;

public class Principal {

	private JFrame frmTiendaWallrose;
	private JTable tablaClientes;
	private JTable tablaProductos;
	private JTable tablaOrdenes;
	private JLabel lblTotalPendiente;
	// private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frmTiendaWallrose.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
		cargarTodo();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTiendaWallrose = new JFrame();
		frmTiendaWallrose.setResizable(false);
		frmTiendaWallrose.setTitle("Tienda WallRose");
		frmTiendaWallrose.setBounds(100, 100, 850, 520);
		frmTiendaWallrose.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTiendaWallrose.getContentPane().setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmTiendaWallrose.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel panelProductos = new JPanel();
		tabbedPane.addTab("Productos", null, panelProductos, null);
		panelProductos.setLayout(null);

		JScrollPane scrollProductos = new JScrollPane();
		scrollProductos.setBounds(10, 10, 650, 410);
		panelProductos.add(scrollProductos);

		tablaProductos = new JTable();
		tablaProductos.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Código", "Nombre", "Existencias", "Unidad", "Precio" }));
		tablaProductos.setDefaultEditor(Object.class, null);
		tablaProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollProductos.setViewportView(tablaProductos);

		JButton btnVerProducto = new JButton("Ver");
		btnVerProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verProducto();
			}
		});
		btnVerProducto.setBounds(680, 30, 120, 25);
		panelProductos.add(btnVerProducto);

		JButton btnAgregarProducto = new JButton("Agregar");
		btnAgregarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarProducto();
			}
		});
		btnAgregarProducto.setBounds(680, 70, 120, 25);
		panelProductos.add(btnAgregarProducto);

		JButton btnEditarProducto = new JButton("Editar");
		btnEditarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarProducto();
			}
		});
		btnEditarProducto.setBounds(680, 110, 120, 25);
		panelProductos.add(btnEditarProducto);

		JButton btnBorrarProducto = new JButton("Borrar");
		btnBorrarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarProducto();
			}
		});
		btnBorrarProducto.setBounds(680, 150, 120, 25);
		panelProductos.add(btnBorrarProducto);

		// Órdenes, panel
		JPanel panelOrdenes = new JPanel();
		tabbedPane.addTab("Órdenes", null, panelOrdenes, null);
		panelOrdenes.setLayout(null);

		JScrollPane scrollOrdenes = new JScrollPane();
		scrollOrdenes.setBounds(10, 10, 650, 370);
		panelOrdenes.add(scrollOrdenes);

		tablaOrdenes = new JTable();
		tablaOrdenes.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Número", "Fecha", "ID Cliente", "Cliente", "Estado", "Total" }) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		tablaOrdenes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollOrdenes.setViewportView(tablaOrdenes);

		JLabel lblTotalPendienteFijo = new JLabel("Total pendiente:");
		lblTotalPendienteFijo.setBounds(10, 400, 120, 25);
		panelOrdenes.add(lblTotalPendienteFijo);

		lblTotalPendiente = new JLabel("0.0");
		lblTotalPendiente.setBounds(130, 400, 150, 25);
		panelOrdenes.add(lblTotalPendiente);

		// Botones de órdenes
		JButton btnCrearOrden = new JButton("Crear");
		btnCrearOrden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frmTiendaWallrose, "Crear orden todavía no implementado.");
			}
		});
		btnCrearOrden.setBounds(680, 30, 120, 25);
		panelOrdenes.add(btnCrearOrden);

		JButton btnVerOrden = new JButton("Ver");
		btnVerOrden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verOrden();
			}
		});
		btnVerOrden.setBounds(680, 70, 120, 25);
		panelOrdenes.add(btnVerOrden);

		JButton btnBorrarOrden = new JButton("Borrar");
		btnBorrarOrden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frmTiendaWallrose, "Borrar orden todavía no implementado.");
			}
		});
		btnBorrarOrden.setBounds(680, 110, 120, 25);
		panelOrdenes.add(btnBorrarOrden);
		// Fin botones órdenes

		// Clientes, panel
		JPanel panelClientes = new JPanel();
		tabbedPane.addTab("Clientes", null, panelClientes, null);
		panelClientes.setLayout(null);

		JButton btnVerCliente = new JButton("Ver");
		btnVerCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verCliente();
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 650, 410);
		panelClientes.add(scrollPane);

		tablaClientes = new JTable();
		tablaClientes.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Nombre", "Email" }) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		scrollPane.setViewportView(tablaClientes);
		btnVerCliente.setBounds(680, 30, 120, 25);
		panelClientes.add(btnVerCliente);

		JButton btnAgregarCliente = new JButton("Agregar");
		btnAgregarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarCliente();
			}
		});
		btnAgregarCliente.setBounds(680, 70, 120, 25);
		panelClientes.add(btnAgregarCliente);

		JButton btnEditarCliente = new JButton("Editar");
		btnEditarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarCliente();
			}
		});
		btnEditarCliente.setBounds(680, 110, 120, 25);
		panelClientes.add(btnEditarCliente);

		JButton btnBorrarCliente = new JButton("Borrar");
		btnBorrarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarCliente();
			}
		});
		btnBorrarCliente.setBounds(680, 150, 120, 25);
		panelClientes.add(btnBorrarCliente);
	}

	private void cargarClientes() {
		ControladoraWallRose control = ControladoraWallRose.obtenerInstancia();

		DefaultTableModel model = (DefaultTableModel) tablaClientes.getModel();
		model.setRowCount(0);

		for (Cliente cliente : control.obtenerListadoClientes()) {
			Object[] fila = new Object[] { cliente.getId(), cliente.getNombre(), cliente.getEmail() };

			model.addRow(fila);
		}
	}

	private void cargarProductos() {
		ControladoraWallRose control = ControladoraWallRose.obtenerInstancia();

		DefaultTableModel model = (DefaultTableModel) tablaProductos.getModel();
		model.setRowCount(0);

		for (Producto producto : control.obtenerListadoProductos()) {
			Object[] fila = new Object[] { producto.getCodigo(), producto.getNombre(), producto.getExistencias(),
					producto.getUnidad(), producto.getPrecio() };

			model.addRow(fila);
		}
	}

	private void cargarOrdenes() {
		ControladoraWallRose control = ControladoraWallRose.obtenerInstancia();

		DefaultTableModel model = (DefaultTableModel) tablaOrdenes.getModel();
		model.setRowCount(0);

		for (OrdenCompra orden : control.obtenerListadoOrdenes()) {
			Object[] fila = new Object[] { orden.getNumero(), orden.getFecha(), orden.getCliente().getId(),
					orden.getCliente().getNombre(), orden.getEstado().name(), orden.calcularTotal() };

			model.addRow(fila);
		}

		lblTotalPendiente.setText(String.valueOf(control.obtenerMontoTotalPendiente()));
	}

	private void cargarTodo() {
		cargarClientes();
		cargarProductos();
		cargarOrdenes();
	}

	private String obtenerIdClienteSeleccionado() {
		int fila = tablaClientes.getSelectedRow();
		if (fila == -1) {
			JOptionPane.showMessageDialog(frmTiendaWallrose, "Debe seleccionar un cliente.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return null;
		}
		DefaultTableModel model = (DefaultTableModel) tablaClientes.getModel();
		return (String) model.getValueAt(fila, 0);
	}

	// Abre dialog detalle cliente para mostrar la información del cliente
	// seleccionado.
	private void verCliente() {
		String idCliente = obtenerIdClienteSeleccionado();
		if (idCliente == null) {
			return;
		}
		DialogDetalleCliente ventana = new DialogDetalleCliente(idCliente);
		ventana.setLocationRelativeTo(frmTiendaWallrose);
		ventana.setVisible(true);
	}

	// Abre dialog cliente para agregar uno nuevo.
	private void agregarCliente() {
		DialogCliente ventana = new DialogCliente(null);
		ventana.setVisible(true);
		cargarTodo();
	}

	// Abre dialog cliente para editar el cliente seleccionado.
	private void editarCliente() {
		String idCliente = obtenerIdClienteSeleccionado();
		if (idCliente == null) {
			return;
		}
		DialogCliente ventana = new DialogCliente(idCliente);
		ventana.setVisible(true);
		cargarTodo();
	}

	// Borra el cliente seleccionado, mostrando un mensaje de confirmación.
	private void borrarCliente() {
		String idCliente = obtenerIdClienteSeleccionado();
		if (idCliente == null) {
			return;
		}
		ControladoraWallRose control = ControladoraWallRose.obtenerInstancia();
		Cliente cliente = control.obtenerCliente(idCliente);
		String nombreCliente = idCliente;
		if (cliente != null) {
			nombreCliente = cliente.getNombre();
		}
		int respuesta = JOptionPane.showConfirmDialog(frmTiendaWallrose,
				"Se eliminará el cliente " + nombreCliente + " y todas sus órdenes asociadas. ¿Desea continuar?",
				"Confirmar", JOptionPane.YES_NO_OPTION);
		if (respuesta == JOptionPane.YES_OPTION) {
			try {
				control.borrarCliente(idCliente);
				cargarTodo();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(frmTiendaWallrose, "Error al borrar cliente: " + e.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	// Obtiene código del producto seleccionado.
	private Integer obtenerCodigoProductoSeleccionado() {
		int fila = tablaProductos.getSelectedRow();
		if (fila == -1) {
			JOptionPane.showMessageDialog(frmTiendaWallrose, "Debe seleccionar un producto.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return null;
		}
		DefaultTableModel model = (DefaultTableModel) tablaProductos.getModel();
		return (Integer) model.getValueAt(fila, 0);
	}

	private void verProducto() {
		Integer codigoProducto = obtenerCodigoProductoSeleccionado();
		if (codigoProducto == null) {
			return;
		}
		DialogProducto ventana = new DialogProducto(codigoProducto, true);
		ventana.setLocationRelativeTo(frmTiendaWallrose);
		ventana.setVisible(true);
	}
	
	private void agregarProducto() {
		DialogProducto ventana = new DialogProducto(null, false);
		ventana.setLocationRelativeTo(frmTiendaWallrose);
		ventana.setVisible(true);
		cargarTodo();
	}
	
	private void editarProducto() {
		Integer codigoProducto = obtenerCodigoProductoSeleccionado();
		if (codigoProducto == null) {
			return;
		}
		DialogProducto ventana = new DialogProducto(codigoProducto, false);
		ventana.setLocationRelativeTo(frmTiendaWallrose);
		ventana.setVisible(true);
		cargarTodo();
	}
	
	private void borrarProducto() {
		Integer codigoProducto = obtenerCodigoProductoSeleccionado();
		if (codigoProducto == null) {
			return;
		}
		ControladoraWallRose control = ControladoraWallRose.obtenerInstancia();
		Producto producto = control.obtenerProducto(codigoProducto);
		String nombreProducto = String.valueOf(codigoProducto);
		if (producto != null) {
			nombreProducto = producto.getNombre();
		}
		int respuesta = JOptionPane.showConfirmDialog(frmTiendaWallrose,
				"Se eliminará el producto " + nombreProducto + ". ¿Desea continuar?", "Confirmar",
				JOptionPane.YES_NO_OPTION);
		if (respuesta == JOptionPane.YES_OPTION) {
			try {
				control.borrarProducto(codigoProducto);
				cargarTodo();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(frmTiendaWallrose, "Error al borrar producto: " + e.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private Integer obtenerNumeroOrdenSeleccionada() {
	    int fila = tablaOrdenes.getSelectedRow();
	    if (fila == -1) {
	        JOptionPane.showMessageDialog(
	            frmTiendaWallrose,
	            "Debe seleccionar una orden.",
	            "Error",
	            JOptionPane.ERROR_MESSAGE
	        );
	        return null;
	    }
	    int filaModelo = tablaOrdenes.convertRowIndexToModel(fila);
	    DefaultTableModel model = (DefaultTableModel) tablaOrdenes.getModel();
	    Object valor = model.getValueAt(filaModelo, 0);
	    return Integer.parseInt(valor.toString());
	}
	
	private void verOrden() {
		Integer numeroOrden = obtenerNumeroOrdenSeleccionada();
		if (numeroOrden == null) {
			return;
		}
		DetalleOrden ventana = new DetalleOrden(numeroOrden);
		ventana.setLocationRelativeTo(frmTiendaWallrose);
		ventana.setVisible(true);
		cargarTodo();
	}

}
