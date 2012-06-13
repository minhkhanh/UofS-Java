package jsql.jclient;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ClientFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public ClientFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		contentPane = new ClientPanel();
		setContentPane(contentPane);
	}

}
