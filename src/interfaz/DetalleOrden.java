package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import control.ControladoraWallRose;
import logica.OrdenCompra;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import logica.LineaOrden;
import logica.Producto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.ListSelectionModel;

public class DetalleOrden extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private Integer numeroOrden;
	
	private JLabel lblNumero;
	private JLabel lblCliente;
	private JLabel lblFecha;
	private JLabel lblEstado;
	private JLabel lblMonto;
	private JLabel lblImpuesto;
	private JLabel lblTotal;
	private JTable tablaLineas;
	
	private JComboBox<Producto> comboProductos;
	private JTextField txtCantidad;
	private JButton btnAgregarLinea;
	private JButton btnActualizarLinea;
	private JButton btnBorrarLinea;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DetalleOrden dialog = new DetalleOrden();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DetalleOrden() {
		setResizable(false);
		initUI();
	}
	
	public DetalleOrden(Integer numeroOrden) {
        this.numeroOrden = numeroOrden;
        initUI();
        
        cargarProductosCombo();
        if (numeroOrden != null) {
            cargarDatosOrden();
        }
    }

    private void initUI() {
        setModal(true);
        setTitle("Detalle orden");
        setBounds(100, 100, 800, 560);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);
        
        JLabel lblNumeroFijo = new JLabel("Número:");
        lblNumeroFijo.setBounds(20, 20, 100, 25);
        getContentPane().add(lblNumeroFijo);
        lblNumero = new JLabel("");
        lblNumero.setBounds(130, 20, 450, 25);
        getContentPane().add(lblNumero);

        JLabel lblClienteFijo = new JLabel("Cliente:");
        lblClienteFijo.setBounds(20, 50, 100, 25);
        getContentPane().add(lblClienteFijo);
        lblCliente = new JLabel("");
        lblCliente.setBounds(130, 50, 450, 25);
        getContentPane().add(lblCliente);

        JLabel lblFechaFijo = new JLabel("Fecha:");
        lblFechaFijo.setBounds(20, 80, 100, 25);
        getContentPane().add(lblFechaFijo);
        lblFecha = new JLabel("");
        lblFecha.setBounds(130, 80, 450, 25);
        getContentPane().add(lblFecha);

        JLabel lblEstadoFijo = new JLabel("Estado:");
        lblEstadoFijo.setBounds(20, 110, 100, 25);
        getContentPane().add(lblEstadoFijo);
        lblEstado = new JLabel("");
        lblEstado.setBounds(130, 110, 450, 25);
        getContentPane().add(lblEstado);

        JLabel lblMontoFijo = new JLabel("Monto:");
        lblMontoFijo.setBounds(20, 390, 100, 25);
        getContentPane().add(lblMontoFijo);
        lblMonto = new JLabel("0.0");
        lblMonto.setBounds(130, 390, 150, 25);
        getContentPane().add(lblMonto);

        JLabel lblImpuestoFijo = new JLabel("Impuesto:");
        lblImpuestoFijo.setBounds(20, 420, 100, 25);
        getContentPane().add(lblImpuestoFijo);
        lblImpuesto = new JLabel("0.0");
        lblImpuesto.setBounds(130, 420, 150, 25);
        getContentPane().add(lblImpuesto);

        JLabel lblTotalFijo = new JLabel("Total:");
        lblTotalFijo.setBounds(20, 450, 100, 25);
        getContentPane().add(lblTotalFijo);
        lblTotal = new JLabel("0.0");
        lblTotal.setBounds(130, 450, 150, 25);
        getContentPane().add(lblTotal);
        
        JLabel lblLineas = new JLabel("Líneas de la orden:");
        lblLineas.setBounds(20, 150, 150, 25);
        getContentPane().add(lblLineas);

        JScrollPane scrollLineas = new JScrollPane();
        scrollLineas.setBounds(20, 180, 500, 190);
        getContentPane().add(scrollLineas);

        tablaLineas = new JTable();
        tablaLineas.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "Línea", "Código", "Producto", "Cantidad", "Unidad", "Costo"
            }
        ) {
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        scrollLineas.setViewportView(tablaLineas);
        
        // Selección de una sola fila en la tabla
        tablaLineas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        
        // Agregar listener para selección de filas en la tabla
        tablaLineas.getSelectionModel().addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    cargarLineaSeleccionadaEnFormulario();
                }
            }
        });
        
        JLabel lblProducto = new JLabel("Producto:");
        lblProducto.setBounds(540, 180, 120, 25);
        getContentPane().add(lblProducto);
        comboProductos = new JComboBox<Producto>();
        comboProductos.setBounds(540, 205, 220, 25);
        getContentPane().add(comboProductos);
        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setBounds(540, 245, 120, 25);
        getContentPane().add(lblCantidad);
        txtCantidad = new JTextField();
        txtCantidad.setBounds(540, 270, 220, 25);
        getContentPane().add(txtCantidad);
        txtCantidad.setColumns(10);
        btnAgregarLinea = new JButton("Agregar línea");
        btnAgregarLinea.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		agregarLinea();
        	}
        });
        btnAgregarLinea.setBounds(540, 310, 220, 25);
        getContentPane().add(btnAgregarLinea);
        btnActualizarLinea = new JButton("Actualizar línea");
        btnActualizarLinea.setBounds(540, 345, 220, 25);
        getContentPane().add(btnActualizarLinea);
        btnBorrarLinea = new JButton("Borrar línea");
        btnBorrarLinea.setBounds(540, 380, 220, 25);
        getContentPane().add(btnBorrarLinea);
                
        getContentPane().add(contentPanel, BorderLayout.CENTER);
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
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        dispose();
		    }
		});
		btnCerrar.setBounds(640, 439, 120, 25);
		getContentPane().add(btnCerrar);
    }
    
	private void cargarDatosOrden() {
		try {
			ControladoraWallRose control = ControladoraWallRose.obtenerInstancia();
			OrdenCompra orden = control.obtenerOrdenCompra(numeroOrden);
			if (orden == null) {
				throw new IllegalArgumentException("No existe la orden seleccionada.");
			}
			lblNumero.setText(String.valueOf(orden.getNumero()));
			lblCliente.setText(orden.getCliente().getId() + " - " + orden.getCliente().getNombre());
			lblFecha.setText(String.valueOf(orden.getFecha()));
			lblEstado.setText(orden.getEstado().name());
			lblMonto.setText(String.valueOf(orden.calcularMonto()));
			lblImpuesto.setText(String.valueOf(orden.calcularMontoImpuesto()));
			lblTotal.setText(String.valueOf(orden.calcularTotal()));
			cargarLineas();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error al cargar orden: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
			dispose();
		}
	}

	private void cargarLineas() {
		ControladoraWallRose control = ControladoraWallRose.obtenerInstancia();
		DefaultTableModel model = (DefaultTableModel) tablaLineas.getModel();
		model.setRowCount(0);
		List<LineaOrden> lineas = control.obtenerLineasOrden(numeroOrden);
		for (int i = 0; i < lineas.size(); i++) {
			LineaOrden linea = lineas.get(i);
			Producto producto = linea.getProducto();
			Object[] fila = new Object[] { i + 1, producto.getCodigo(), producto.getNombre(), linea.getCantidad(),
					producto.getUnidad(), linea.calcularCosto() };
			model.addRow(fila);
		}
	}
	
	private void cargarProductosCombo() {
		ControladoraWallRose control = ControladoraWallRose.obtenerInstancia();
		comboProductos.removeAllItems();
		for (Producto producto : control.obtenerListadoProductos()) {
			comboProductos.addItem(producto);
		}
	}
	
	private void cargarLineaSeleccionadaEnFormulario() {
	    int fila = tablaLineas.getSelectedRow();
	    if (fila == -1) {
	        return;
	    }
	    int indiceLinea = tablaLineas.convertRowIndexToModel(fila);
	    ControladoraWallRose control = ControladoraWallRose.obtenerInstancia();
	    List<LineaOrden> lineas = control.obtenerLineasOrden(numeroOrden);
	    if (indiceLinea < 0 || indiceLinea >= lineas.size()) {
	        return;
	    }
	    LineaOrden linea = lineas.get(indiceLinea);
	    comboProductos.setSelectedItem(linea.getProducto());
	    txtCantidad.setText(String.valueOf(linea.getCantidad()));
	}
	
	private double leerCantidad() {
	    try {
	        String texto = txtCantidad.getText().trim().replace(",", ".");
	        if (texto.equals("")) {
	            throw new IllegalArgumentException("Debe escribir la cantidad.");
	        }
	        return Double.parseDouble(texto);
	    } catch (NumberFormatException e) {
	        throw new IllegalArgumentException("La cantidad debe ser un número.");
	    }
	}
	
	private Producto obtenerProductoSeleccionado() {
	    Producto producto = (Producto) comboProductos.getSelectedItem();
	    if (producto == null) {
	        throw new IllegalArgumentException("Debe seleccionar un producto.");
	    }
	    return producto;
	}
	
	private void agregarLinea() {
	    try {
	        Producto producto = obtenerProductoSeleccionado();
	        double cantidad = leerCantidad();
	        ControladoraWallRose control = ControladoraWallRose.obtenerInstancia();
	        control.agregarLineaOrden(numeroOrden, producto.getCodigo(), cantidad);
	        limpiarFormularioLinea();
	        cargarDatosOrden();
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(
	            this,
	            "Error al agregar línea: " + e.getMessage(),
	            "Error",
	            JOptionPane.ERROR_MESSAGE
	        );
	    }
	}
	
	private void limpiarFormularioLinea() {
	    txtCantidad.setText("");
	    if (comboProductos.getItemCount() > 0) {
	        comboProductos.setSelectedIndex(0);
	    }
	    tablaLineas.clearSelection();
	}
	

}