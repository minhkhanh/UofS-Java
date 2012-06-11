package jsql.server;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import jsql.data.Table;
import jsql.parse.Parser;
import jsql.parse.Statement;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

/**
 * @author DWater
 * 
 */
@SuppressWarnings("serial")
public class Panel_AddData extends JPanel implements ActionListener {

	private JPanel jP_Table;
	private JPanel jP_AddData;
	private JTable jTable1;
	private JTable jTableNewData;
	private JScrollPane jSP1;
	private JScrollPane jSP2;
	private Vector<String> _ColNameTable1 = new Vector<String>();
	private Vector<Vector<Object>> _Values = new Vector<Vector<Object>>();
	private Vector<Vector<Object>> _NewValue = new Vector<Vector<Object>>();
	private JButton jBtn_SaveToDB;
	@SuppressWarnings("rawtypes")
	private JComboBox jCbb_ListTable;
	private JLabel jLbl_ChoseTable;
	private JButton jBtn_AddField;

	public Panel_AddData() {
		this.InitFrame();
		this.Init();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void InitFrame() {

		this.setSize(784, 439);
		this.setLayout(null);
		this.setName("Add Data");

		jBtn_SaveToDB = new JButton("Save to Database");
		jBtn_SaveToDB.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jBtn_SaveToDB.setBounds(301, 11, 149, 30);
		jBtn_SaveToDB.setActionCommand("ok");
		jBtn_SaveToDB.addActionListener(this);
		this.add(jBtn_SaveToDB);

		jCbb_ListTable = new JComboBox();
		jCbb_ListTable.setActionCommand("changetable");
		jCbb_ListTable.addActionListener(this);
		jCbb_ListTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jCbb_ListTable.setBounds(119, 11, 156, 30);
		this.add(jCbb_ListTable);

		jLbl_ChoseTable = new JLabel("Chon Bang: ");
		jLbl_ChoseTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jLbl_ChoseTable.setBounds(24, 11, 85, 30);
		this.add(jLbl_ChoseTable);

		jP_Table = new JPanel();
		jP_Table.setBounds(10, 63, 764, 239);
		jP_Table.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createEtchedBorder(), "DATA"));
		this.add(jP_Table);

		jTable1 = new JTable();
		jTable1.setFont(new java.awt.Font("Tahoma", 0, 14));

		jSP1 = new javax.swing.JScrollPane();
		jSP1.setBounds(10, 21, 744, 207);
		jSP1.setViewportView(jTable1);

		jP_Table.setLayout(null);
		jP_Table.add(jSP1);

		jP_AddData = new JPanel();
		jP_AddData.setBounds(10, 348, 764, 80);
		jP_AddData.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "ADD DATA"));
		this.add(jP_AddData);
		jP_AddData.setLayout(null);

		jBtn_AddField = new JButton("Add");
		jBtn_AddField.setBounds(10, 21, 77, 30);
		jBtn_AddField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jBtn_AddField.setActionCommand("ok");
		jBtn_AddField.setActionCommand("addfield");
		jBtn_AddField.addActionListener(this);
		jP_AddData.add(jBtn_AddField);

		jTableNewData = new JTable();
		jTableNewData.setFont(new java.awt.Font("Tahoma", 0, 14));

		jSP2 = new javax.swing.JScrollPane();
		jSP2.setBounds(112, 21, 642, 48);
		jSP2.setViewportView(jTableNewData);

		jP_AddData.setLayout(null);
		jP_AddData.add(jSP2);
	}

	public void Init() {
		jCbb_ListTable.setEnabled(false);
		jBtn_SaveToDB.setEnabled(false);
		jBtn_AddField.setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if ("ok".equals(arg0.getActionCommand())) {

			// luu du lieu xuong database -> close form
			Main.GetDataBase().saveToFile();

			JOptionPane.showMessageDialog(this, "Đã lưu database ^_^",
					"Warning", JOptionPane.INFORMATION_MESSAGE);

			// this.dispose();
		}

		if ("changetable".equals(arg0.getActionCommand())) {

			this.Refresh();
		}

		if ("addfield".equals(arg0.getActionCommand())) {

			TableModel tm = jTableNewData.getModel();
			Table table = Main.GetDataBase().getTable(
					jCbb_ListTable.getSelectedIndex());
			int nc = table.getColumns().size();
			String typeCol, value;

			// kiem tra xem nguoi dung nhap
			for (int i = 0; i < nc; i++) {

				// nguoi dung nhap thong tin chua
				if (tm.getValueAt(0, i).equals("")) {
					JOptionPane.showMessageDialog(this,
							"Chưa nhập cột \"" + tm.getColumnName(i) + "\"!",
							"Warning", JOptionPane.WARNING_MESSAGE);
					return;
				}

				// thong tin hop le khong
				typeCol = table.getColumnType(i).toUpperCase();
				value = tm.getValueAt(0, i).toString().trim();

				if (typeCol.equals("INT")) {
					for (int ch = 0; ch < value.length(); ch++) {
						if (!(value.charAt(ch) >= '0' && value.charAt(ch) <= '9')) {
							JOptionPane.showMessageDialog(this,
									"Dữ liệu cột \"" + tm.getColumnName(i)
											+ "\" không hợp lệ!", "Warning",
									JOptionPane.WARNING_MESSAGE);
							return;
						}
					}
				}

				if (typeCol.equals("FLOAT")) {
					for (int ch = 0; ch < value.length(); ch++) {
						if (!(value.charAt(ch) >= '0' && value.charAt(ch) <= '9')) {
							JOptionPane
									.showMessageDialog(
											this,
											"Dữ liệu cột \""
													+ tm.getColumnName(i)
													+ "\" không hợp lệ! "
													+ "\n Dùng dấu \".\" để ngăn cách phần thập phân",
											"Warning",
											JOptionPane.WARNING_MESSAGE);
							return;
						}
					}
				}

			}

			// them thong tin vao bang
			Statement statement;
			String sql;

			sql = "INSERT INTO " + table.getName() + " VALUES (";

			for (int i = 0; i < nc; i++) {

				value = tm.getValueAt(0, i).toString().trim();
				typeCol = table.getColumnType(i).toUpperCase();
				if (typeCol.equals("INT")) {
					sql += value;
				} else {
					sql += "'" + value + "'";
				}

				if (i < nc - 1)
					sql += ", ";
			}

			sql += ")";

			statement = Parser.parseStatement(sql);
			Main.GetDataBase().executeStatement(statement);

			this.Refresh();
		}
	}

	public void Refresh() {

		Table table = Main.GetDataBase().getTable(
				jCbb_ListTable.getSelectedIndex());

		_ColNameTable1 = Helper.GetListFiledAndType(table);
		_Values = Helper.GetValues(table);
		_NewValue = Helper.CreateRowEmpy(table);

		jTable1.setModel(new DefaultTableModel(_Values, _ColNameTable1));

		jTableNewData
				.setModel(new DefaultTableModel(_NewValue, _ColNameTable1));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void ChoosenDatabase() {
		jCbb_ListTable.setModel(new DefaultComboBoxModel(Helper
				.GetListTableName(Main.GetDataBase())));
		
		jCbb_ListTable.setEnabled(true);
		jBtn_SaveToDB.setEnabled(true);
		jBtn_AddField.setEnabled(true);

		this.Refresh();
	}
}
