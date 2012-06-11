package jsql.server;

import java.awt.Point;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;

/**
 * @author DWater
 * 
 */
@SuppressWarnings("serial")
public class MiniTable extends JPanel {

	private JLabel jLbl_TableName;
	private JTable table;
	private JScrollPane jSP;
	private static Vector<String> colName;
	private static Vector<Vector<String>> values;
	private JButton btnDelete;

	public MiniTable(jsql.data.Table data, Point pos) {

		this.setSize(183, 253);
		this.setLayout(null);
		this.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createEtchedBorder(), ""));
		this.setLocation(pos);

		colName = new Vector<>();
		values = new Vector<>();

		jLbl_TableName = new JLabel(data.getName());
		jLbl_TableName.setHorizontalAlignment(SwingConstants.CENTER);
		jLbl_TableName.setBounds(5, 5, 114, 14);
		add(jLbl_TableName);

		colName.add("Field");
		colName.add("Data Type");

		int nField = data.getColumns().size();
		Vector<String> tField;

		for (int i = 0; i < nField; i++) {
			tField = new Vector<>();
			tField.add(data.getColumName(i));
			tField.add(data.getColumnType(i));

			values.add(tField);
		}

		table = new JTable();
		table.setModel(new DefaultTableModel(values, colName));

		jSP = new javax.swing.JScrollPane();
		jSP.setBounds(5, 24, 173, 224);
		jSP.setViewportView(table);
		this.add(jSP);

		btnDelete = new JButton("");
		btnDelete.setIcon(new ImageIcon(MiniTable.class
				.getResource("/img/delete_16x16.png")));
		btnDelete.setBounds(139, 5, 39, 23);
		add(btnDelete);
	}

}
