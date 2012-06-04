package jsql.server;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JLabel;

import jsql.data.Database;
import jsql.data.Row;
import jsql.data.Table;

import javax.swing.JLayeredPane;
import javax.swing.JTabbedPane;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

/**
 * @author DWater
 * 
 */
@SuppressWarnings("serial")
public class Frame_AddData extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPanel jP_Main;
	private JPanel jP_Table;
	private JPanel jP_AddData;
	private JTable jTable1;
	private JTable jTableNewData;
	private JScrollPane jSP1;
	private JScrollPane jSP2;
	private Vector<String> _ColNameTable1 = new Vector<String>();
	private Vector<Vector<Object>> _Values = new Vector<Vector<Object>>();
	private Vector<Vector<Object>> _NewValue = new Vector<Vector<Object>>();
	private JButton jBtn_Ok;
	@SuppressWarnings("rawtypes")
	private JComboBox jCbb_ListTable;
	private JLabel jLbl_ChoseTable;

	private JButton jBtn_AddTable;
	private JLabel jLbl_NameTable;
	private JTextField jTf_TableName;
	private JButton jBtn_AddField;

	public Frame_AddData() {
		this.InitFrame();
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

		jBtn_Ok = new JButton("OK");
		jBtn_Ok.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jBtn_Ok.setBounds(251, 11, 103, 30);
		jBtn_Ok.setActionCommand("ok");
		jBtn_Ok.addActionListener(this);
		jP_Main.add(jBtn_Ok);

		jCbb_ListTable = new JComboBox();
		jCbb_ListTable.setActionCommand("changetable");
		jCbb_ListTable.addActionListener(this);
		jCbb_ListTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jCbb_ListTable.setBounds(119, 11, 103, 30);
		jP_Main.add(jCbb_ListTable);

		jLbl_ChoseTable = new JLabel("Chon Bang: ");
		jLbl_ChoseTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jLbl_ChoseTable.setBounds(24, 11, 85, 30);
		jP_Main.add(jLbl_ChoseTable);

		jP_Table = new JPanel();
		jP_Table.setBounds(10, 88, 642, 214);
		jP_Table.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createEtchedBorder(), "TABLE"));
		jP_Main.add(jP_Table);

		jTable1 = new JTable();
		jTable1.setFont(new java.awt.Font("Tahoma", 0, 14));

		jSP1 = new javax.swing.JScrollPane();
		jSP1.setBounds(10, 21, 622, 182);
		jSP1.setViewportView(jTable1);

		jP_Table.setLayout(null);
		jP_Table.add(jSP1);

		jCbb_ListTable.setModel(new DefaultComboBoxModel(Helper
				.GetListTableName(Main.GetDataBase())));

		jP_AddData = new JPanel();
		jP_AddData.setBounds(10, 329, 642, 147);
		jP_AddData.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "ADD DATA"));
		jP_Main.add(jP_AddData);
		jP_AddData.setLayout(null);

		jBtn_AddField = new JButton("Add");
		jBtn_AddField.setBounds(10, 100, 94, 30);
		jBtn_AddField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jBtn_AddField.setActionCommand("ok");
		jBtn_AddField.setActionCommand("addfield");
		jBtn_AddField.addActionListener(this);
		jP_AddData.add(jBtn_AddField);

		jTableNewData = new JTable();
		jTableNewData.setFont(new java.awt.Font("Tahoma", 0, 14));

		jSP2 = new javax.swing.JScrollPane();
		jSP2.setBounds(10, 21, 622, 68);
		jSP2.setViewportView(jTableNewData);

		jP_AddData.setLayout(null);
		jP_AddData.add(jSP2);

		Table table = Main.GetDataBase().getTable(
				jCbb_ListTable.getSelectedIndex());

		_ColNameTable1 = Helper.GetListFiledAndType(table);
		_Values = Helper.GetValues(table);
		_NewValue = Helper.CreateRowEmpy(table);

		jTable1.setModel(new DefaultTableModel(_Values, _ColNameTable1));
		jTableNewData
				.setModel(new DefaultTableModel(_NewValue, _ColNameTable1));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if ("ok".equals(arg0.getActionCommand())) {

			// luu du lieu xuong database -> close form
			Main.GetDataBase().saveToFile();

			JOptionPane.showMessageDialog(this, "Saved to DataBase ^_^",
					"Warning", JOptionPane.INFORMATION_MESSAGE);

			this.dispose();
		}

		if ("changetable".equals(arg0.getActionCommand())) {

			Table table = Main.GetDataBase().getTable(
					jCbb_ListTable.getSelectedIndex());

			_ColNameTable1 = Helper.GetListFiledAndType(table);
			_Values = Helper.GetValues(table);
			_NewValue = Helper.CreateRowEmpy(table);

			jTable1.setModel(new DefaultTableModel(_Values, _ColNameTable1));

			jTableNewData.setModel(new DefaultTableModel(_NewValue,
					_ColNameTable1));

		}

		if ("addfield".equals(arg0.getActionCommand())) {

			// them 1row vao
			Table table = Main.GetDataBase().getTable(
					jCbb_ListTable.getSelectedIndex());

			Row tRow = table.getRow(0);

			for (int i = 0; i < tRow.numCol(); i++)
			{
			//	jsql.data.Type x = (jsql.data.Type) _NewValue.get(1).get(i);
				//tRow.setDataAt(i, x);
			}

			_Values = Helper.GetValues(table);
			_NewValue = Helper.CreateRowEmpy(table);

			jTable1.setModel(new DefaultTableModel(_Values, _ColNameTable1));

			jTableNewData.setModel(new DefaultTableModel(_NewValue,
					_ColNameTable1));
		}
	}

	public void SaveToDB() {

	}
}
