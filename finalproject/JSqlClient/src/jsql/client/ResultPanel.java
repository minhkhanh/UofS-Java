package jsql.client;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import jsql.data.Result;

public class ResultPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel resultPanel = null;
	
	/**
	 * Create the panel.
	 */
	public ResultPanel() {
		setBorder(new TitledBorder(null, "Result", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setPreferredSize(new Dimension(300, 350));
		setLayout(new BorderLayout(0, 0));
	}
	
	public void showResultTable(Result result) {
		if (result.getTable()==null) {
			showResultMessage(result);
			return;
		}
		if (!(resultPanel instanceof TableResultPanel)) {
			clearResult();
			resultPanel = new TableResultPanel();
			add(resultPanel, BorderLayout.CENTER);
		}
		((TableResultPanel)resultPanel).setTable(result.getTable());
		validate();
	}
	
	public void showResultMessage(Result result) {
		if (!(resultPanel instanceof TextResultPanel)) {
			clearResult();
			resultPanel = new TextResultPanel();
			add(resultPanel, BorderLayout.CENTER);
		}
		((TextResultPanel)resultPanel).addResult(result.getMessage());
		validate();
	}

	public void clearResult() {
		if (resultPanel==null) return;
		remove(resultPanel);
		resultPanel = null;
		validate();
	}
}
