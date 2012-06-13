package jsql.client;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;

public class StatementPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final JScrollPane scrollPane = new JScrollPane();
	JTextArea textArea;

	/**
	 * Create the panel.
	 */
	public StatementPanel() {
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Statement", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setMinimumSize(new Dimension(600, 390));
		setLayout(new BorderLayout(0, 0));
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		add(scrollPane);
	}
}
