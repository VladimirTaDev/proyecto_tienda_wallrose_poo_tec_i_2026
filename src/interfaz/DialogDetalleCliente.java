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

public class DialogDetalleCliente extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblEmail;
	private JLabel lblNombre;
	private JLabel lblId;

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
		setModal(true);
		setResizable(false);
		setTitle("Detalle cliente");
		setBounds(100, 100, 380, 150);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] {54, 46, 46, 46, 46, 46, 46};
		gbl_contentPanel.rowHeights = new int[] {14, 0, 0, 1};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0};
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
			gbc_lblId.insets = new Insets(0, 0, 5, 5);
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
			gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
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
			gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
			gbc_lblEmail.anchor = GridBagConstraints.NORTHWEST;
			gbc_lblEmail.gridx = 1;
			gbc_lblEmail.gridy = 2;
			contentPanel.add(lblEmail, gbc_lblEmail);
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
	}

}
