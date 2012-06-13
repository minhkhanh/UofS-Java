package jsql.jclient;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;

public class TextResultPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @wbp.nonvisual location=-3,339
	 */
	private final JScrollPane scrollPane = new JScrollPane();
	private JTextArea textArea;

	/**
	 * Create the panel.
	 */
	public TextResultPanel() {
		setLayout(new BorderLayout(0, 0));
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		add(scrollPane);

	}

}
