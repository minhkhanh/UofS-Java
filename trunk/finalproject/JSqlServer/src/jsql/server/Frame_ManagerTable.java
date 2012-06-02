package jsql.server;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
import jsql.data.Table;

/**
 * @author DWater
 * 
 */
@SuppressWarnings("serial")
public class Frame_ManagerTable extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPanel jP_Main;
	private JButton jBtn_AddTable;
	private JButton jBtn_DeleteTable;
	private JButton jBtn_AddData;
	private JButton jBtn_Browse;
	private JButton jBtn_CreateNewDatabase;
	private JFileChooser jFChooser;
	private JTextField jTf_AddrFileDB;
	@SuppressWarnings("rawtypes")
	private JComboBox jCbb_ListTable;

	private Frame_AddTable _FrameAddTable;
	private Frame_AddData _FrameAddData;

	private Database _DataBase;
	private String _PathFileDataBase;
	private FileFilterDb _FileFilterDb;

	public Frame_ManagerTable() {
		this.InitFrame();
	}

	@SuppressWarnings("rawtypes")
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

		jBtn_AddData = new JButton("Them du lieu");
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

		_FileFilterDb = new FileFilterDb() {
		};
		jFChooser = new JFileChooser();
		jFChooser.setFileFilter(_FileFilterDb);
		_PathFileDataBase = "";
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if ("createdb".equals(arg0.getActionCommand())) {
		}

		if ("browse".equals(arg0.getActionCommand())) {

			int returnVal = jFChooser.showDialog(this, "Choose DataBase");

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				_PathFileDataBase = jFChooser.getSelectedFile().getPath();
				jTf_AddrFileDB.setText(_PathFileDataBase);
				this.LoadDataBase();
			}
		}

		if ("addtable".equals(arg0.getActionCommand())) {
			if (this.CheckChooseDataBase()) {
				_FrameAddTable = new Frame_AddTable(_PathFileDataBase);
				_FrameAddTable.setVisible(true);
			}
		}

		if ("deletetable".equals(arg0.getActionCommand())) {
			if (this.CheckChooseDataBase()
					&& jCbb_ListTable.getModel().getSize() > 0) {

				int ch = JOptionPane.showConfirmDialog(this, "Delete table \""
						+ jCbb_ListTable.getSelectedItem().toString() + "\""
						+ " Are you sure??", "Warning",
						JOptionPane.YES_NO_OPTION);
				if (ch == 1)
					return;
				// delete table
				_DataBase.DeleteTable(jCbb_ListTable.getSelectedIndex());
				JOptionPane.showMessageDialog(this, "Deleted successful",
						"Warning", JOptionPane.WARNING_MESSAGE);

				this.LoadDataBase();
			}
		}

		if ("adddata".equals(arg0.getActionCommand())) {

			if (this.CheckChooseDataBase()) {
				_FrameAddData = new Frame_AddData(_PathFileDataBase);
				_FrameAddData.setVisible(true);
			}
		}
	}

	public Boolean CheckChooseDataBase() {
		if (_PathFileDataBase.equals("")) {
			JOptionPane.showMessageDialog(this, "Please choose File DataBase!",
					"Warning", JOptionPane.WARNING_MESSAGE);

			return false;
		}
		return true;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void LoadDataBase() {
		_DataBase = Database.loadFromFile(_PathFileDataBase);

		jCbb_ListTable.setModel(new DefaultComboBoxModel(Helper
				.GetListTableName(_DataBase)));

	}
}
