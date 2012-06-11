package jsql.server;

import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

/**
 * @author DWater
 * 
 */
@SuppressWarnings("serial")
public class MiniTable extends JPanel {

	private JLabel jLbl_TableName;
	private JTable table;
	private JScrollPane jSP_Log;
	private static Vector<String> colNameTableLog = new Vector<String>();
	private static Vector<Vector<String>> _Logs = new Vector<Vector<String>>();

	public MiniTable(jsql.data.Table data) {

		this.setSize(200, 300);
		this.setLayout(null);
		this.setName("Server");

		table = new JTable();

		colNameTableLog.add("Field");
		colNameTableLog.add("Data Type");

		table.setModel(new DefaultTableModel(_Logs, colNameTableLog));
		jSP_Log = new javax.swing.JScrollPane();
		jSP_Log.setBounds(10, 24, 180, 344);
		this.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createEtchedBorder(), ""));
		jSP_Log.setViewportView(table);
		this.add(jSP_Log);

		jLbl_TableName = new JLabel("Name table");
		jLbl_TableName.setBounds(20, 11, 46, 14);
		add(jLbl_TableName);
	}

}
