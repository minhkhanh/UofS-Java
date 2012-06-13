package jsql.jclient;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;

public class HeaderPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField textField;
	JButton btnConnect;
	JButton btnDisconect;
	JButton btnExecute;

	/**
	 * Create the panel.
	 */
	public HeaderPanel() {
		setMinimumSize(new Dimension(600, 60));
		setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblServerAddress = new JLabel("Server address:");
		add(lblServerAddress);
		
		textField = new JTextField();
		textField.setText("127.0.0.1:3456");
		add(textField);
		textField.setColumns(25);
		
		btnConnect = new JButton("Connect");
		add(btnConnect);
		
		btnDisconect = new JButton("Disconnect");
		btnDisconect.setEnabled(false);
		add(btnDisconect);
		
		btnExecute = new JButton("Execute");
		btnExecute.setEnabled(false);
		add(btnExecute);

	}

}
