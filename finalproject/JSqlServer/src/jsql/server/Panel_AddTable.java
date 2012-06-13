package jsql.server;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import jsql.data.Column;
import jsql.data.Table;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class Panel_AddTable extends JPanel implements ActionListener {

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
	private JButton jBtn_Resest;

	public Panel_AddTable() {
		this.InitFrame();
		this.Init();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void InitFrame() {

		this.setSize(784, 439);
		this.setLayout(null);
		this.setName("Add Table");

		jBtn_AddTable = new JButton("OK");
		jBtn_AddTable.setIcon(new ImageIcon(Panel_AddTable.class
				.getResource("/img/accept.png")));
		jBtn_AddTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jBtn_AddTable.setBounds(308, 9, 94, 35);
		jBtn_AddTable.setActionCommand(KeyAction.addtable_addtable.toString());
		jBtn_AddTable.addActionListener(this);
		this.add(jBtn_AddTable);

		jLbl_NameTable = new JLabel("Table name:");
		jLbl_NameTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jLbl_NameTable.setBounds(20, 11, 77, 30);
		this.add(jLbl_NameTable);

		jTf_TableName = new JTextField();
		jTf_TableName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jTf_TableName.setBounds(107, 12, 173, 30);
		jTf_TableName.setColumns(10);
		this.add(jTf_TableName);

		jP_Table = new JPanel();
		jP_Table.setBounds(20, 52, 754, 218);
		jP_Table.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createEtchedBorder(), "TABLE"));
		this.add(jP_Table);

		colNameTable1.add("Primary Key");
		colNameTable1.add("Field Name");
		colNameTable1.add("Data Type");
		colNameTable1.add("Description");

		jTable1 = new JTable();
		jTable1.setFont(new java.awt.Font("Tahoma", 0, 14));
		jTable1.setModel(new DefaultTableModel(_Fields, colNameTable1));

		jSP1 = new javax.swing.JScrollPane();
		jSP1.setBounds(10, 21, 734, 186);
		jSP1.setViewportView(jTable1);

		jP_Table.setLayout(null);
		jP_Table.add(jSP1);

		jP_AddField = new JPanel();
		jP_AddField.setBounds(10, 291, 764, 137);
		jP_AddField.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createEtchedBorder(), "ADD FIELD"));
		this.add(jP_AddField);
		jP_AddField.setLayout(null);

		jLbl_DataType = new JLabel("Data Type:");
		jLbl_DataType.setBounds(536, 54, 69, 30);
		jP_AddField.add(jLbl_DataType);
		jLbl_DataType.setFont(new Font("Tahoma", Font.PLAIN, 14));

		jLbl_FieldNam = new JLabel("Field Name:");
		jLbl_FieldNam.setBounds(154, 54, 70, 30);
		jP_AddField.add(jLbl_FieldNam);
		jLbl_FieldNam.setFont(new Font("Tahoma", Font.PLAIN, 14));

		jLbl_Description = new JLabel("Description:");
		jLbl_Description.setBounds(154, 95, 73, 30);
		jP_AddField.add(jLbl_Description);
		jLbl_Description.setFont(new Font("Tahoma", Font.PLAIN, 14));

		jChb_Primary = new JCheckBox("Primary Key");
		jChb_Primary.setBounds(154, 21, 99, 25);
		jP_AddField.add(jChb_Primary);
		jChb_Primary.setFont(new Font("Tahoma", Font.PLAIN, 14));

		jTf_FieldName = new JTextField();
		jTf_FieldName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jTf_FieldName.setColumns(10);
		jTf_FieldName.setBounds(234, 54, 229, 30);
		jP_AddField.add(jTf_FieldName);

		jTf_Description = new JTextField();
		jTf_Description.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jTf_Description.setColumns(10);
		jTf_Description.setBounds(234, 96, 520, 30);
		jP_AddField.add(jTf_Description);

		jCbb_DataType = new JComboBox();
		jCbb_DataType.setModel(new DefaultComboBoxModel(new String[] { "int",
				"string", "float" }));
		jCbb_DataType.setBounds(620, 56, 134, 30);
		jP_AddField.add(jCbb_DataType);

		jBtn_AddField = new JButton("Add Field");
		jBtn_AddField.setIcon(new ImageIcon(Panel_AddTable.class
				.getResource("/img/add.png")));
		jBtn_AddField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jBtn_AddField.setActionCommand(KeyAction.addtable_addfield.toString());
		jBtn_AddField.setBounds(10, 54, 123, 35);
		jBtn_AddField.addActionListener(this);
		jP_AddField.add(jBtn_AddField);

		jBtn_Resest = new JButton("Reset");
		jBtn_Resest.setIcon(new ImageIcon(Panel_AddTable.class
				.getResource("/img/refresh.png")));
		jBtn_Resest.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jBtn_Resest.setActionCommand(KeyAction.addtable_reset.toString());
		jBtn_Resest.addActionListener(this);
		jBtn_Resest.setBounds(423, 9, 103, 35);
		add(jBtn_Resest);
	}

	public void Init() {
		jTf_TableName.setEnabled(false);
		jTf_FieldName.setEnabled(false);
		jTf_Description.setEnabled(false);
		jBtn_AddTable.setEnabled(false);
		jBtn_AddField.setEnabled(false);
		jChb_Primary.setEnabled(false);
		jCbb_DataType.setEnabled(false);
		jBtn_Resest.setEnabled(false);
	}

	public void ChoosenDatabase() {
		jTf_TableName.setEnabled(true);
		jTf_FieldName.setEnabled(true);
		jTf_Description.setEnabled(true);
		jBtn_AddTable.setEnabled(true);
		jBtn_AddField.setEnabled(true);
		jChb_Primary.setEnabled(true);
		jCbb_DataType.setEnabled(true);
		jBtn_Resest.setEnabled(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		KeyAction action = KeyAction.valueOf(arg0.getActionCommand());

		if (action == KeyAction.addtable_addtable) {

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
					if ((Boolean) _Fields.get(i).get(0))
						tcol.setPrimary(true);
					else
						tcol.setPrimary(false);

					colums.add(tcol);
				}

				table.setName(tableName);
				table.setColumns(colums);

				Main.GetDataBase().addTable(table);
				Main.GetDataBase().saveToFile();

				JOptionPane.showMessageDialog(this, "Added table \""
						+ tableName + "\"" + " successfully ^_^", "OK",
						JOptionPane.INFORMATION_MESSAGE);

				Panel_Server.PrintLog("Server: Added table \"" + tableName
						+ "\"" + " into the database.");

				this.ResetAddTable();

				Frame_Main.Refresh();
			}
		}

		if (action == KeyAction.addtable_addfield) {

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

		if (action == KeyAction.addtable_reset) {
			this.ResetAddTable();
		}
	}

	public Boolean CheckAddField(String fieldName, String dataType,
			String description) {
		// fieldName bi trung
		for (int i = 0; i < _Fields.size(); i++)
			if (fieldName.equals(_Fields.get(i).get(1))) {
				JOptionPane.showMessageDialog(this, "FieldName already exists!",
						"Warning", JOptionPane.WARNING_MESSAGE);
				return false;
			}

		// chua nhap fieldName
		if (fieldName.equals("")) {
			JOptionPane.showMessageDialog(this, "Enter FieldName!",
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
			JOptionPane.showMessageDialog(this, "Choose DataType!",
					"Warning", JOptionPane.WARNING_MESSAGE);
			return false;
		}

		// Chua nhap description
		if (description.equals("")) {
			int ch = JOptionPane.showConfirmDialog(this,
					"You did not enter the Description! Continue?", "Warning",
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
