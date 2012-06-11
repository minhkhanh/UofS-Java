package jsql.server;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import jsql.data.Database;

/**
 * @author DWater
 * 
 */
@SuppressWarnings("serial")
public class Frame_Main extends JFrame implements ActionListener {

	private JPanel jP_Main;
	private JPanel jP_Database;
	private JTabbedPane tabbedPane;
	private JTextField jTf_AddrDB;
	private JButton jBtn_Browse;
	private JFileChooser jFChooser;
	private Panel_Info p_Info;
	private Panel_Server p_Server;
	private Panel_ManagerTable p_Manager;
	private Panel_AddTable p_AddTable;
	private Panel_AddData p_AddData;
	private JButton jBtn_CreateNewDb;

	public Frame_Main() {

		setResizable(false);
		setTitle("jSQLServer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2
				- getWidth() / 2,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2
						- getHeight() / 2);

		jP_Main = new JPanel();
		jP_Main.setBorder(new EmptyBorder(5, 5, 5, 5));
		jP_Main.setLayout(null);
		setContentPane(jP_Main);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(5, 75, 784, 469);
		jP_Main.add(tabbedPane);

		p_Info = new Panel_Info();
		p_Server = new Panel_Server();
		p_Manager = new Panel_ManagerTable();
		p_AddTable = new Panel_AddTable();
		p_AddData = new Panel_AddData();
		tabbedPane.add(p_Server);
		tabbedPane.add(p_Manager);
		tabbedPane.add(p_AddTable);
		tabbedPane.add(p_AddData);

		tabbedPane.add(p_Info);

		jP_Database = new JPanel();
		jP_Database.setBounds(5, 5, 784, 49);
		jP_Main.add(jP_Database);
		jP_Database.setLayout(null);
		jP_Database.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createEtchedBorder(), ""));

		jTf_AddrDB = new JTextField();
		jTf_AddrDB.setText("");
		jTf_AddrDB.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jTf_AddrDB.setEditable(false);
		jTf_AddrDB.setColumns(10);
		jTf_AddrDB.setBounds(130, 12, 362, 30);
		jP_Database.add(jTf_AddrDB);

		JLabel lblFileDatabase = new JLabel("File Database:");
		lblFileDatabase.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFileDatabase.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFileDatabase.setBounds(20, 11, 100, 30);
		jP_Database.add(lblFileDatabase);

		jBtn_Browse = new JButton("Browse");
		jBtn_Browse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jBtn_Browse.setActionCommand(KeyAction.main_browse.toString());
		jBtn_Browse.addActionListener(this);
		jBtn_Browse.setBounds(502, 11, 131, 30);
		jP_Database.add(jBtn_Browse);

		jBtn_CreateNewDb = new JButton("Create New DB");
		jBtn_CreateNewDb.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jBtn_CreateNewDb.setActionCommand(KeyAction.main_createdb.toString());
		jBtn_CreateNewDb.setBounds(643, 11, 131, 30);
		jP_Database.add(jBtn_CreateNewDb);

		this.setJMenuBar(new MainMenuBar());

		jFChooser = new JFileChooser();
		jFChooser.setFileFilter(new FileFilter_SqlFile() {
		});
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		KeyAction action = KeyAction.valueOf(arg0.getActionCommand());

		if (action == KeyAction.main_browse) {
			new Frame_CreateNewDB().setVisible(true);
		}

		if (action == KeyAction.main_browse) {

			int returnVal = jFChooser.showDialog(this, "Choose DataBase");

			String _PathFileDataBase = "";
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				_PathFileDataBase = jFChooser.getSelectedFile().getPath();
				jTf_AddrDB.setText(_PathFileDataBase);
				Main.SetDataBase(Database.loadFromFile(_PathFileDataBase));

				Panel_Server.PrintLog("Server: Đã chọn File DataBase");

				p_Server.ChoosenDatabase();
				p_AddTable.ChoosenDatabase();
				p_AddData.ChoosenDatabase();
			} else {
				if (_PathFileDataBase.equals("")) {
					Panel_Server.PrintLog("Đã hủy việc chọn File DataBase");
				}
			}
		}
	}
}
