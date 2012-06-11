package jsql.server;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

import jsql.data.Database;

/**
 * @author DWater
 * 
 */
@SuppressWarnings("serial")
public class Frame_ManagerDB extends JFrame implements ActionListener {

	private JPanel jP_Main;
	private JButton jBtn_AddTable;
	private JButton jBtn_DeleteTable;
	private JButton jBtn_AddData;
	private JButton jBtn_Browse;
	private JButton jBtn_CreateNewDatabase;
	private JFileChooser jFChooser;
	private static JTextField jTf_AddrFileDB;
	@SuppressWarnings("rawtypes")
	private static JComboBox jCbb_ListTable;

	private Frame_AddTable _FrameAddTable;
	private Frame_AddData _FrameAddData;
	private Frame_CreateNewDB _FrameCreateDB;

	private FileFilterDb _FileFilterDb;

	public Frame_ManagerDB() {
		this.InitFrame();
		this.Init();
	}

	@SuppressWarnings("rawtypes")
	public void InitFrame() {

		setResizable(false);
		setBounds(300, 100, 700, 525);
		jP_Main = new JPanel();
		jP_Main.setBorder(new EmptyBorder(5, 5, 5, 5));
		jP_Main.setLayout(null);
		setContentPane(jP_Main);

		jBtn_AddTable = new JButton("Add Table");
		jBtn_AddTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jBtn_AddTable.setBounds(10, 75, 131, 30);
		jBtn_AddTable.setActionCommand("addtable");
		jBtn_AddTable.addActionListener(this);
		jP_Main.add(jBtn_AddTable);

		jBtn_DeleteTable = new JButton("Delete Table");
		jBtn_DeleteTable.setToolTipText("");
		jBtn_DeleteTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jBtn_DeleteTable.setActionCommand("deletetable");
		jBtn_DeleteTable.addActionListener(this);
		jBtn_DeleteTable.setBounds(10, 116, 131, 30);
		jP_Main.add(jBtn_DeleteTable);

		jBtn_AddData = new JButton("Add Data");
		jBtn_AddData.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jBtn_AddData.setActionCommand("adddata");
		jBtn_AddData.addActionListener(this);
		jBtn_AddData.setBounds(10, 157, 131, 30);
		jP_Main.add(jBtn_AddData);

		JLabel lblFileDatabase = new JLabel("File Database:");
		lblFileDatabase.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFileDatabase.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFileDatabase.setBounds(10, 11, 103, 29);
		jP_Main.add(lblFileDatabase);

		jTf_AddrFileDB = new JTextField();
		jTf_AddrFileDB.setEditable(false);
		jTf_AddrFileDB.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jTf_AddrFileDB.setBounds(123, 11, 387, 30);
		jP_Main.add(jTf_AddrFileDB);
		jTf_AddrFileDB.setColumns(10);

		jCbb_ListTable = new JComboBox();
		jCbb_ListTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jCbb_ListTable.setBounds(151, 116, 103, 30);
		jP_Main.add(jCbb_ListTable);

		jBtn_Browse = new JButton("Browse");
		jBtn_Browse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jBtn_Browse.setActionCommand("browse");
		jBtn_Browse.setBounds(528, 10, 138, 30);
		jBtn_Browse.setActionCommand("browse");
		jBtn_Browse.addActionListener(this);
		jP_Main.add(jBtn_Browse);

		jBtn_CreateNewDatabase = new JButton("Create DataBase");
		jBtn_CreateNewDatabase.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jBtn_CreateNewDatabase.setActionCommand("createdb");
		jBtn_CreateNewDatabase.addActionListener(this);
		jBtn_CreateNewDatabase.setBounds(528, 75, 138, 30);
		jP_Main.add(jBtn_CreateNewDatabase);
	}

	public void Init() {
		_FileFilterDb = new FileFilterDb() {
		};
		jFChooser = new JFileChooser();
		jFChooser.setFileFilter(_FileFilterDb);
		if (Main.GetDataBase() != null)
			jTf_AddrFileDB.setText(Main.GetDataBase().GetFilePath());
	}

	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent arg0) {

		if ("createdb".equals(arg0.getActionCommand())) {
			_FrameCreateDB = new Frame_CreateNewDB();
			_FrameCreateDB.setVisible(true);
		}

		if ("browse".equals(arg0.getActionCommand())) {

			int returnVal = jFChooser.showDialog(this, "Choose DataBase");

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				String pathFileDataBase = jFChooser.getSelectedFile().getPath();
				Main.SetDataBase(Database.loadFromFile(pathFileDataBase));
				this.Refresh();
			}
		}

		if ("addtable".equals(arg0.getActionCommand())) {
			if (this.CheckChooseDataBase()) {
				_FrameAddTable = new Frame_AddTable();
				_FrameAddTable.setVisible(true);
			}
		}

		if ("deletetable".equals(arg0.getActionCommand())) {
			if (this.CheckChooseDataBase()
					&& jCbb_ListTable.getModel().getSize() > 0) {

				int ch = JOptionPane.showConfirmDialog(this, "Xóa bảng \""
						+ jCbb_ListTable.getSelectedItem().toString() + "\""
						+ " Bạn có chắc chắn??", "Warning",
						JOptionPane.YES_NO_OPTION);
				if (ch == 1)
					return;

				// delete table
				Main.GetDataBase().DeleteTable(
						jCbb_ListTable.getSelectedIndex());

				JOptionPane.showMessageDialog(this,
						"Đã xóa bảng thành công ^_^", "Warning",
						JOptionPane.WARNING_MESSAGE);

				this.Refresh();
			}
		}

		if ("adddata".equals(arg0.getActionCommand())) {

			if (this.CheckChooseDataBase()) {
				_FrameAddData = new Frame_AddData();
				_FrameAddData.setVisible(true);
			}
		}
	}

	public Boolean CheckChooseDataBase() {
		if (Main.GetDataBase() == null) {
			JOptionPane.showMessageDialog(this,
					"Xin chọn file Database trước !!!", "Warning",
					JOptionPane.WARNING_MESSAGE);

			return false;
		}
		return true;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void Refresh() {
		jTf_AddrFileDB.setText(Main.GetDataBase().GetFilePath());
		jCbb_ListTable.setModel(new DefaultComboBoxModel(Helper
				.GetListTableName(Main.GetDataBase())));
	}
}
