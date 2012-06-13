package jsql.client;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import jsql.data.Table;

public class TableResultPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private TableModel tableModel;
	/**
	 * @wbp.nonvisual location=-3,369
	 */
	private final JScrollPane scrollPane = new JScrollPane();

	/**
	 * Create the panel.
	 */
	public TableResultPanel() {
		setLayout(new BorderLayout(0, 0));
		
		table = new JTable();
		//table.setShowHorizontalLines(false);
		//table.setShowVerticalLines(false);
		//table.setColumnSelectionAllowed(false);
		//table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setRowSelectionAllowed(false);
		scrollPane.setViewportView(table);
		tableModel = new TableModel();
		table.setModel(tableModel);
		add(scrollPane, BorderLayout.CENTER);
	}
	public TableResultPanel(Table table) {
		this();
		tableModel.setTable(table);
		this.table.setModel(tableModel);
	}
	public void setTable(Table table) {
		tableModel = new TableModel(table);
		this.table.setModel(tableModel);
	}

}
