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

public class Principal {

	private JFrame frmTiendaWallrose;
	private JTable tablaClientes;
	private JTable tablaProductos;
	private JTable tablaOrdenes;
	private JLabel lblTotalPendiente;

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
		frmTiendaWallrose.setBounds(100, 100, 450, 347);
		frmTiendaWallrose.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTiendaWallrose.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmTiendaWallrose.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel Clientes = new JPanel();
		tabbedPane.addTab("Clientes", null, Clientes, null);
		Clientes.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 429, 280);
		Clientes.add(scrollPane);
		
		tablaClientes = new JTable();
		tablaClientes.setModel(new DefaultTableModel(
			    new Object[][] {},
			    new String[] {
			        "ID", "Nombre", "Email"
			    }
			));
		scrollPane.setViewportView(tablaClientes);
		
		JPanel Productos = new JPanel();
		tabbedPane.addTab("Productos", null, Productos, null);
		Productos.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 11, 429, 269);
		Productos.add(scrollPane_1);
		
		tablaProductos = new JTable();
		tablaProductos.setModel(new DefaultTableModel(
			    new Object[][] {},
			    new String[] {
			        "Código", "Nombre", "Existencias", "Unidad", "Precio"
			    }
			));
		scrollPane_1.setViewportView(tablaProductos);
		
		JPanel Órdenes = new JPanel();
		tabbedPane.addTab("Órdenes", null, Órdenes, null);
		Órdenes.setLayout(null);
		
		JLabel lblTotalPendienteFijo = new JLabel("Total pendiente:");
		lblTotalPendienteFijo.setBounds(87, 256, 91, 24);
		Órdenes.add(lblTotalPendienteFijo);
		
		lblTotalPendiente = new JLabel("0.0");
		lblTotalPendiente.setBounds(185, 256, 99, 24);
		Órdenes.add(lblTotalPendiente);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 0, 429, 280);
		Órdenes.add(scrollPane_2);
		
		tablaOrdenes = new JTable();
		tablaOrdenes.setModel(new DefaultTableModel(
			    new Object[][] {},
			    new String[] {
			        "Número", "Fecha", "ID Cliente", "Cliente", "Estado", "Total"
			    }
			));
		scrollPane_2.setViewportView(tablaOrdenes);
	}
	
	private void cargarClientes() {
	    ControladoraWallRose control = ControladoraWallRose.obtenerInstancia();

	    DefaultTableModel model = (DefaultTableModel) tablaClientes.getModel();
	    model.setRowCount(0);

	    for (Cliente cliente : control.obtenerListadoClientes()) {
	        Object[] fila = new Object[] {
	            cliente.getId(),
	            cliente.getNombre(),
	            cliente.getEmail()
	        };

	        model.addRow(fila);
	    }
	}
	
	private void cargarProductos() {
	    ControladoraWallRose control = ControladoraWallRose.obtenerInstancia();

	    DefaultTableModel model = (DefaultTableModel) tablaProductos.getModel();
	    model.setRowCount(0);

	    for (Producto producto : control.obtenerListadoProductos()) {
	        Object[] fila = new Object[] {
	            producto.getCodigo(),
	            producto.getNombre(),
	            producto.getExistencias(),
	            producto.getUnidad(),
	            producto.getPrecio()
	        };

	        model.addRow(fila);
	    }
	}
	
	private void cargarOrdenes() {
	    ControladoraWallRose control = ControladoraWallRose.obtenerInstancia();

	    DefaultTableModel model = (DefaultTableModel) tablaOrdenes.getModel();
	    model.setRowCount(0);

	    for (OrdenCompra orden : control.obtenerListadoOrdenes()) {
	        Object[] fila = new Object[] {
	            orden.getNumero(),
	            orden.getFecha(),
	            orden.getCliente().getId(),
	            orden.getCliente().getNombre(),
	            orden.getEstado().name(),
	            orden.calcularTotal()
	        };

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
	        JOptionPane.showMessageDialog(
	        	frmTiendaWallrose,
	            "Debe seleccionar un cliente.",
	            "Error",
	            JOptionPane.ERROR_MESSAGE
	        );

	        return null;
	    }

	    DefaultTableModel model = (DefaultTableModel) tablaClientes.getModel();
	    return (String) model.getValueAt(fila, 0);
	}
	
	/*
	private void verCliente() {
	    String idCliente = obtenerIdClienteSeleccionado();

	    if (idCliente != null) {
	        DetalleCliente ventana = new DetalleCliente(idCliente);
	        ventana.setVisible(true);
	    }
	}
	*/
}
