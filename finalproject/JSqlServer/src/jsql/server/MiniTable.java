package jsql.server;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;

/**
 * @author DWater
 * 
 */
@SuppressWarnings("serial")
public class MiniTable extends JPanel implements ActionListener {

	private JLabel jLbl_TableName;
	private JTable table;
	private JScrollPane jSP;
	private static Vector<String> colName;
	private static Vector<Vector<String>> values;
	private JButton btnDelete;

	public MiniTable(jsql.data.Table data, Point pos) {

		this.setSize(175, 205);
		this.setLayout(null);
		this.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createEtchedBorder(), ""));
		this.setLocation(pos);

		colName = new Vector<String>();
		values = new Vector<Vector<String>>();

		jLbl_TableName = new JLabel(data.getName());
		jLbl_TableName.setHorizontalAlignment(SwingConstants.CENTER);
		jLbl_TableName.setBounds(5, 5, 111, 14);
		add(jLbl_TableName);

		colName.add("Field");
		colName.add("Data Type");

		int nField = data.getColumns().size();
		Vector<String> tField;

		for (int i = 0; i < nField; i++) {
			tField = new Vector<String>();
			tField.add(data.getColumName(i));
			tField.add(data.getColumnType(i));

			values.add(tField);
		}

		table = new JTable();
		table.setModel(new DefaultTableModel(values, colName));

		jSP = new javax.swing.JScrollPane();
		jSP.setBounds(5, 24, 160, 175);
		jSP.setViewportView(table);
		this.add(jSP);

		btnDelete = new JButton("");
		btnDelete.setIcon(new ImageIcon(MiniTable.class
				.getResource("/img/delete_16x16.png")));
		btnDelete.setBounds(131, 5, 39, 23);
		btnDelete.setActionCommand(KeyAction.mn_deletetable.toString());
		btnDelete.addActionListener(this);
		add(btnDelete);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		KeyAction action = KeyAction.valueOf(arg0.getActionCommand());

		if (action == KeyAction.mn_deletetable) {

			int ch = JOptionPane.showConfirmDialog(this, "Xóa bảng \""
					+ jLbl_TableName.getText() + "\"." + " Bạn có chắc chắn ?",
					"Warning", JOptionPane.YES_NO_OPTION);
			if (ch == 1)
				return;

			Main.GetDataBase().DeleteTable(jLbl_TableName.getText());
			Main.GetDataBase().saveToFile();

			JOptionPane.showMessageDialog(this, "Đã xóa bảng thành công ^_^",
					"Warning", JOptionPane.WARNING_MESSAGE);
			// Panel_Manager.Refresh();
			Panel_Manager.Remove(this);
		}
	}
}
