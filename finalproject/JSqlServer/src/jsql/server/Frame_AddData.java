package jsql.server;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JLabel;

import jsql.data.Database;
import javax.swing.JLayeredPane;
import javax.swing.JTabbedPane;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

/**
 * @author DWater
 * 
 */
@SuppressWarnings("serial")
public class Frame_AddData extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPanel jP_Main;
	private JPanel jP_Table;
	private JPanel jP_AddField;
	private JTable jTable1;
	private JScrollPane jSP1;
	private Vector<String> colNameTable1 = new Vector<String>();
	private Vector<Vector<?>> _Fields = new Vector<Vector<?>>();
	private JButton jBtn_Ok;
	@SuppressWarnings("rawtypes")
	private JComboBox jCbb_ListTable;
	private JLabel jLbl_ChoseTable;

	private Database _DataBase;

	public Frame_AddData(String pathdb) {
		_DataBase = Database.loadFromFile(pathdb);
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
		jP_Table.setBounds(10, 88, 642, 254);
		jP_Table.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createEtchedBorder(), "TABLE"));
		jP_Main.add(jP_Table);

		jTable1 = new JTable();
		jTable1.setFont(new java.awt.Font("Tahoma", 0, 14));

		jSP1 = new javax.swing.JScrollPane();
		jSP1.setBounds(10, 21, 622, 222);
		jSP1.setViewportView(jTable1);

		jP_Table.setLayout(null);
		jP_Table.add(jSP1);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(64, 400, 238, 61);
		jP_Main.add(tabbedPane);

		jCbb_ListTable.setModel(new DefaultComboBoxModel(Helper
				.GetListTableName(_DataBase)));
		colNameTable1 = Helper.GetListFiledName(_DataBase
				.getTable(jCbb_ListTable.getSelectedIndex()));
		jTable1.setModel(new DefaultTableModel(Helper.GetValues(_DataBase
				.getTable(jCbb_ListTable.getSelectedIndex())), colNameTable1));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if ("changetable".equals(arg0.getActionCommand())) {
			colNameTable1 = Helper.GetListFiledName(_DataBase
					.getTable(jCbb_ListTable.getSelectedIndex()));
			jTable1.setModel(new DefaultTableModel(Helper.GetValues(_DataBase
					.getTable(jCbb_ListTable.getSelectedIndex())), colNameTable1));
		}
	}
}
