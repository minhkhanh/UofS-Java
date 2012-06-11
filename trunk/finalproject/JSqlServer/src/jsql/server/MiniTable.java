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
import java.awt.Font;

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

	private Panel_Manager manager;

	public MiniTable(Panel_Manager mn, jsql.data.Table data, Point pos) {

		manager = mn;

		this.setSize(232, 205);
		this.setLayout(null);
		this.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createEtchedBorder(), ""));
		this.setLocation(pos);

		colName = new Vector<String>();
		values = new Vector<Vector<String>>();

		jLbl_TableName = new JLabel(data.getName());
		jLbl_TableName.setFont(new Font("Tahoma", Font.BOLD, 13));
		jLbl_TableName.setHorizontalAlignment(SwingConstants.CENTER);
		jLbl_TableName.setBounds(5, 5, 176, 23);
		add(jLbl_TableName);

		colName.add("PrimaryKey");
		colName.add("Field");
		colName.add("Data Type");

		int nField = data.getColumns().size();
		Vector<String> tField;

		for (int i = 0; i < nField; i++) {
			tField = new Vector<String>();
			tField.add(data.isPrimaryKey(i).toString());
			tField.add(data.getColumName(i));
			tField.add(data.getColumnType(i));

			values.add(tField);
		}

		table = new JTable();
		table.setModel(new DefaultTableModel(values, colName));

		jSP = new javax.swing.JScrollPane();
		jSP.setBounds(5, 30, 222, 169);
		jSP.setViewportView(table);
		this.add(jSP);

		btnDelete = new JButton("");
		btnDelete.setIcon(new ImageIcon(MiniTable.class
				.getResource("/img/delete_16x16.png")));
		btnDelete.setBounds(183, 5, 39, 23);
		btnDelete.setActionCommand(KeyAction.mn_deletetable.toString());
		btnDelete.addActionListener(this);
		this.add(btnDelete);
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

			manager.Refresh();
			Frame_Main.Refresh();
		}
	}
}
