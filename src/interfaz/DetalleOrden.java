package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

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
    }

    private void initUI() {
        setModal(true);
        setTitle("Detalle orden");
        setBounds(100, 100, 650, 480);
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
        lblMontoFijo.setBounds(20, 350, 100, 25);
        getContentPane().add(lblMontoFijo);
        lblMonto = new JLabel("0.0");
        lblMonto.setBounds(130, 350, 150, 25);
        getContentPane().add(lblMonto);

        JLabel lblImpuestoFijo = new JLabel("Impuesto:");
        lblImpuestoFijo.setBounds(20, 380, 100, 25);
        getContentPane().add(lblImpuestoFijo);
        lblImpuesto = new JLabel("0.0");
        lblImpuesto.setBounds(130, 380, 150, 25);
        getContentPane().add(lblImpuesto);

        JLabel lblTotalFijo = new JLabel("Total:");
        lblTotalFijo.setBounds(20, 410, 100, 25);
        getContentPane().add(lblTotalFijo);
        lblTotal = new JLabel("0.0");
        lblTotal.setBounds(130, 410, 150, 25);
        getContentPane().add(lblTotal);
        
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
    }

}