package jsql.server;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class Frame_ManagerTable extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPanel panel_1;

	private JButton btn_AddTable;
	private JButton btn_DeleteTable;
	private JButton btn_AddData;


	private JTextField tf_AddrFileDB;
	@SuppressWarnings("rawtypes")
	private JComboBox cbb_ListTable;
	
	private Frame_AddTable _FrameAddTable;
	private Frame_EditTable _FrameEditTable;

	public Frame_ManagerTable() {
		this.InitFrame();
	}

	public void InitFrame() {

		setBounds(300, 100, 700, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);

		btn_AddTable = new JButton("Add Table");
		btn_AddTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_AddTable.setBounds(25, 221, 131, 30);
		btn_AddTable.setActionCommand("addtable");
		btn_AddTable.addActionListener(this);
		panel_1.add(btn_AddTable);

		btn_DeleteTable = new JButton("Delete Table");
		btn_DeleteTable.setToolTipText("");
		btn_DeleteTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_DeleteTable.setActionCommand("deletetable");
		btn_DeleteTable.addActionListener(this);
		btn_DeleteTable.setBounds(25, 271, 131, 30);
		panel_1.add(btn_DeleteTable);

		btn_AddData = new JButton("Them du lieu");
		btn_AddData.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_AddData.setActionCommand("adddata");
		btn_AddData.addActionListener(this);
		btn_AddData.setBounds(25, 329, 131, 30);
		panel_1.add(btn_AddData);
		
		JLabel lblFileDatabase = new JLabel("File Database:");
		lblFileDatabase.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFileDatabase.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFileDatabase.setBounds(25, 11, 131, 29);
		panel_1.add(lblFileDatabase);
		
		tf_AddrFileDB = new JTextField();
		tf_AddrFileDB.setText("Duong dan file DB");
		tf_AddrFileDB.setEditable(false);
		tf_AddrFileDB.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tf_AddrFileDB.setBounds(170, 11, 494, 30);
		panel_1.add(tf_AddrFileDB);
		tf_AddrFileDB.setColumns(10);
		
		cbb_ListTable = new JComboBox();
		cbb_ListTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbb_ListTable.setBounds(174, 271, 103, 30);
		panel_1.add(cbb_ListTable);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if ("addtable".equals(arg0.getActionCommand())) {
			_FrameAddTable = new Frame_AddTable();
			_FrameAddTable.setVisible(true);
		}

		if ("deletetable".equals(arg0.getActionCommand())) {
		}

		if ("adddata".equals(arg0.getActionCommand())) {
			_FrameEditTable = new Frame_EditTable();
			_FrameEditTable.setVisible(true);
		}
	}
	
	public void setAddrFileDB(String addr){
		tf_AddrFileDB.setText(addr);
		
		cbb_ListTable.setModel(new DefaultComboBoxModel(new String[] {
				"HocSinh", "Lop", }));
	}
}
