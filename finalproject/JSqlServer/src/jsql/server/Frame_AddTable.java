package jsql.server;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import jsql.data.Column;
import jsql.data.Table;

/**
 * @author DWater
 * 
 */
@SuppressWarnings("serial")
public class Frame_AddTable extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPanel jP_Main;
	private JPanel jP_Table;
	private JPanel jP_AddField;
	private JTable jTable1;
	private JScrollPane jSP1;
	private Vector<Object> colNameTable1 = new Vector<Object>();
	private Vector<Vector<Object>> _Fields = new Vector<Vector<Object>>();
	private JButton jBtn_AddTable;
	private JLabel jLbl_NameTable;
	private JTextField jTf_TableName;
	private JLabel jLbl_FieldNam;
	private JLabel jLbl_DataType;
	private JLabel jLbl_Description;
	private JCheckBox jChb_Primary;
	private JTextField jTf_FieldName;
	private JTextField jTf_Description;
	private JComboBox<?> jCbb_DataType;
	private JButton jBtn_AddField;

	public Frame_AddTable() {
		this.InitFrame();
		this.Init();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void InitFrame() {

		setResizable(false);
		setBounds(300, 100, 700, 525);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		jP_Main = new JPanel();
		contentPane.add(jP_Main, BorderLayout.CENTER);
		jP_Main.setLayout(null);

		jBtn_AddTable = new JButton("OK");
		jBtn_AddTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jBtn_AddTable.setBounds(308, 11, 94, 30);
		jBtn_AddTable.setActionCommand("addtable");
		jBtn_AddTable.addActionListener(this);
		jP_Main.add(jBtn_AddTable);

		jLbl_NameTable = new JLabel("Table name:");
		jLbl_NameTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jLbl_NameTable.setBounds(20, 11, 77, 30);
		jP_Main.add(jLbl_NameTable);

		jTf_TableName = new JTextField();
		jTf_TableName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jTf_TableName.setBounds(107, 12, 173, 30);
		jTf_TableName.setColumns(10);
		jP_Main.add(jTf_TableName);

		jP_Table = new JPanel();
		jP_Table.setBounds(20, 52, 642, 254);
		jP_Table.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createEtchedBorder(), "TABLE"));
		jP_Main.add(jP_Table);

		colNameTable1.add("Primary Key");
		colNameTable1.add("Field Name");
		colNameTable1.add("Data Type");
		colNameTable1.add("Description");

		jTable1 = new JTable();
		jTable1.setFont(new java.awt.Font("Tahoma", 0, 14));
		jTable1.setModel(new DefaultTableModel(_Fields, colNameTable1));

		jSP1 = new javax.swing.JScrollPane();
		jSP1.setBounds(10, 21, 622, 222);
		jSP1.setViewportView(jTable1);

		jP_Table.setLayout(null);
		jP_Table.add(jSP1);

		jP_AddField = new JPanel();
		jP_AddField.setBounds(20, 329, 642, 137);
		jP_AddField.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createEtchedBorder(), "ADD FIELD"));
		jP_Main.add(jP_AddField);
		jP_AddField.setLayout(null);

		jLbl_DataType = new JLabel("Data Type:");
		jLbl_DataType.setBounds(419, 55, 69, 30);
		jP_AddField.add(jLbl_DataType);
		jLbl_DataType.setFont(new Font("Tahoma", Font.PLAIN, 14));

		jLbl_FieldNam = new JLabel("Field Name:");
		jLbl_FieldNam.setBounds(132, 55, 70, 30);
		jP_AddField.add(jLbl_FieldNam);
		jLbl_FieldNam.setFont(new Font("Tahoma", Font.PLAIN, 14));

		jLbl_Description = new JLabel("Description:");
		jLbl_Description.setBounds(132, 96, 73, 30);
		jP_AddField.add(jLbl_Description);
		jLbl_Description.setFont(new Font("Tahoma", Font.PLAIN, 14));

		jChb_Primary = new JCheckBox("Primary Key");
		jChb_Primary.setBounds(132, 22, 99, 25);
		jP_AddField.add(jChb_Primary);
		jChb_Primary.setFont(new Font("Tahoma", Font.PLAIN, 14));

		jTf_FieldName = new JTextField();
		jTf_FieldName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jTf_FieldName.setColumns(10);
		jTf_FieldName.setBounds(212, 55, 173, 30);
		jP_AddField.add(jTf_FieldName);

		jTf_Description = new JTextField();
		jTf_Description.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jTf_Description.setColumns(10);
		jTf_Description.setBounds(212, 97, 420, 30);
		jP_AddField.add(jTf_Description);

		jCbb_DataType = new JComboBox();
		jCbb_DataType.setModel(new DefaultComboBoxModel(new String[] { "int",
				"string","float" }));
		jCbb_DataType.setBounds(498, 57, 134, 30);
		jP_AddField.add(jCbb_DataType);

		jBtn_AddField = new JButton("Add Field");
		jBtn_AddField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jBtn_AddField.setActionCommand("ok");
		jBtn_AddField.setBounds(10, 33, 94, 30);
		jBtn_AddField.setActionCommand("addfield");
		jBtn_AddField.addActionListener(this);
		jP_AddField.add(jBtn_AddField);
	}

	public void Init() {

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if ("addtable".equals(arg0.getActionCommand())) {

			String tableName = jTf_TableName.getText().trim();

			if (CheckAddTable(tableName)) {

				// them table moi vao database
				Table table = new Table();
				Vector<Column> colums = new Vector<Column>();
				Column tcol;

				for (int i = 0; i < _Fields.size(); i++) {
					tcol = new Column();
					tcol.setName(_Fields.get(i).get(1).toString());
					tcol.setType(_Fields.get(i).get(2).toString().toUpperCase());
					tcol.setDescription(_Fields.get(i).get(3).toString());
					if (_Fields.get(i).get(0).equals("false"))
						tcol.setPrimary(false);
					else
						tcol.setPrimary(true);

					colums.add(tcol);
				}

				table.setName(tableName);
				table.setColumns(colums);

				Main.GetDataBase().addTable(table);
				Main.GetDataBase().saveToFile();

				JOptionPane.showMessageDialog(this, "Đã thêm table: \""
						+ tableName + "\"" + " thành công ^_^", "OK",
						JOptionPane.INFORMATION_MESSAGE);

				this.ResetAddTable();

				Frame_ManagerDB.Refresh();
			}
		}

		if ("addfield".equals(arg0.getActionCommand())) {

			Boolean primary = jChb_Primary.isSelected();
			String fieldName = jTf_FieldName.getText().trim();
			String dataType = jCbb_DataType.getSelectedItem().toString();
			String description = jTf_Description.getText().trim();

			if (this.CheckAddField(fieldName, dataType, description)) {

				Vector<Object> tField = new Vector<Object>();

				tField.add(primary);
				tField.add(fieldName);
				tField.add(dataType);
				tField.add(description);

				_Fields.add(tField);
				jTable1.setModel(new DefaultTableModel(_Fields, colNameTable1));

				this.ResetAddField();
			}
		}
	}

	public Boolean CheckAddField(String fieldName, String dataType,
			String description) {
		// fieldName bi trung
		for (int i = 0; i < _Fields.size(); i++)
			if (fieldName.equals(_Fields.get(i).get(1))) {
				JOptionPane.showMessageDialog(this, "FieldName đã tồn tại!",
						"Warning", JOptionPane.WARNING_MESSAGE);
				return false;
			}

		// chua nhap fieldName
		if (fieldName.equals("")) {
			JOptionPane.showMessageDialog(this, "Xin nhập FieldName!",
					"Warning", JOptionPane.WARNING_MESSAGE);
			return false;
		}

		// fieldName khong hop le
		if (!Helper.CheckFieldAndTableName(fieldName)) {
			JOptionPane
					.showMessageDialog(
							this,
							"FieldName không đúng quy định!"
									+ "\n Tên chỉ gồm các ký tự A-Z, a-z, 0-9, ký tự đầu không được là số!",
							"Warning", JOptionPane.WARNING_MESSAGE);
			return false;
		}

		// Chua chon DataType
		if (dataType.equals("")) {
			JOptionPane.showMessageDialog(this, "Xin chọn DataType!",
					"Warning", JOptionPane.WARNING_MESSAGE);
			return false;
		}

		// Chua nhap description
		if (description.equals("")) {
			int ch = JOptionPane.showConfirmDialog(this,
					"Bạn chưa nhập phần Description! Tiếp tục?", "Warning",
					JOptionPane.YES_NO_OPTION);
			if (ch == 1)
				return false;
		}

		return true;
	}

	public void ResetAddField() {
		jChb_Primary.setSelected(false);
		jTf_FieldName.setText("");
		jTf_Description.setText("");
		jCbb_DataType.setSelectedIndex(0);
	}

	public Boolean CheckAddTable(String tableName) {
		// table da co
		List<Table> tables = Main.GetDataBase().getTables();
		for (int i = 0; i < tables.size(); i++) {
			if (tableName.equals(tables.get(i).getName())) {
				JOptionPane.showMessageDialog(this,
						"TableName bị trùng. Xin nhập tên khác cho Table!",
						"Warning", JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}

		// chua nhap tablename
		if (tableName.equals("")) {
			JOptionPane.showMessageDialog(this, "Xin nhập tên cho Table!",
					"Warning", JOptionPane.WARNING_MESSAGE);
			return false;
		}

		// table khong hop le
		if (!Helper.CheckFieldAndTableName(tableName)) {
			JOptionPane
					.showMessageDialog(
							this,
							"Tên Table không đúng quy định!"
									+ "\n Tên chỉ gồm các ký tự A-Z, a-z, 0-9, ký tự đầu không được là số!",
							"Warning", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		// chua them field nao
		if (_Fields.size() < 1) {
			JOptionPane.showMessageDialog(this,
					"Xin thêm ít nhất một Field vào Table!", "Warning",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}

		return true;
	}

	public void ResetAddTable() {
		this.ResetAddField();
		jTf_TableName.setText("");
		_Fields.removeAllElements();
		jTable1.setModel(new DefaultTableModel(_Fields, colNameTable1));
	}
}
