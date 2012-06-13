package jsql.client;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;

public class ResultPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public ResultPanel() {
		setBorder(new TitledBorder(null, "Result", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setMinimumSize(new Dimension(600, 350));
		setLayout(new BorderLayout(0, 0));
	}

}
