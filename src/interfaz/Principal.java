package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;

public class Principal {

	private JFrame frmTiendaWallrose;

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
		
		JPanel Productos = new JPanel();
		tabbedPane.addTab("Productos", null, Productos, null);
		Productos.setLayout(null);
		
		JPanel Órdenes = new JPanel();
		tabbedPane.addTab("Órdenes", null, Órdenes, null);
		Órdenes.setLayout(null);
	}

}
