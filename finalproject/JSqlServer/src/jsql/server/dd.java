package jsql.server;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Vector;

import jsql.data.Database;

/**
 * @author DWater
 * 
 */
@SuppressWarnings("serial")
public class dd extends JFrame implements ActionListener {

	public enum actionC {
		browse, managertable, listen, stop
	};

	private JPanel jP_Main;
	private JPanel jP_Server;
	private JPanel jP_Manager;
	private JPanel jP_Log;
	private JTextField jTf_AddrFileDB1;
	private JTextField jTf_Port;
	private JButton jBtn_Browse1;
	private JButton jBtn_ManagerTable;
	private JButton jBtn_Listen;
	private JButton jBtn_Stop;
	private JLabel jLbl_AddrFolder;
	private JLabel jLbl_Port;
	private JFileChooser jFChooser;
	private static JTable tableLog;
	private JScrollPane jSP_Log;
	private static Vector<String> colNameTableLog = new Vector<String>();
	private static Vector<Vector<String>> _Logs = new Vector<Vector<String>>();
	
	private JButton jBtn_AddTable;
	private JButton jBtn_DeleteTable;
	private JButton jBtn_AddData;
	private JButton jBtn_Browse;
	private JButton jBtn_CreateNewDatabase;
	private static JTextField jTf_AddrFileDB;
	@SuppressWarnings("rawtypes")
	private static JComboBox jCbb_ListTable;

	private Frame_AddTable _FrameAddTable;
	private Frame_AddData _FrameAddData;
	private Frame_CreateNewDB _FrameCreateDB;


	private MyServer _MyServer;
	private Thread _ThreadServer;
	private int _Port;
	private Frame_ManagerDB _FrameManagerTable;
	private FileFilterDb _FileFilterDb;
	private String _PathFileDataBase;

	public dd() {
		this.InitFrame();
		this.Init();
	}

	public void InitFrame() {
		setResizable(false);
		setTitle("jSQLServer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 800, 570);
		jP_Main = new JPanel();
		jP_Main.setBorder(new EmptyBorder(5, 5, 5, 5));
		jP_Main.setLayout(new BorderLayout(0, 0));
		setContentPane(jP_Main);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		jP_Main.add(tabbedPane, BorderLayout.CENTER);

		jP_Server = new JPanel();
		jP_Server.setBorder(new EmptyBorder(5, 5, 5, 5));
		jP_Server.setLayout(null);
		jP_Server.setName("Server");
		tabbedPane.add(jP_Server);

		jP_Manager = new JPanel();
		jP_Manager.setBorder(new EmptyBorder(5, 5, 5, 5));
		jP_Manager.setLayout(null);
		jP_Manager.setName("Manager Database");
		tabbedPane.add(jP_Manager);

		this.setJMenuBar(new MainMenuBar());

		jLbl_AddrFolder = new JLabel("File DataBase:");
		jLbl_AddrFolder.setHorizontalAlignment(SwingConstants.RIGHT);
		jLbl_AddrFolder.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jLbl_AddrFolder.setBounds(20, 11, 100, 30);
		jP_Server.add(jLbl_AddrFolder);

		jLbl_Port = new JLabel("Port:");
		jLbl_Port.setHorizontalAlignment(SwingConstants.RIGHT);
		jLbl_Port.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jLbl_Port.setBounds(10, 54, 110, 30);
		jP_Server.add(jLbl_Port);

		jTf_AddrFileDB1 = new JTextField();
		jTf_AddrFileDB1.setEditable(false);
		jTf_AddrFileDB1.setText("");
		jTf_AddrFileDB1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jTf_AddrFileDB1.setBounds(130, 12, 239, 30);
		jTf_AddrFileDB1.setColumns(10);
		jP_Server.add(jTf_AddrFileDB1);

		jTf_Port = new JTextField();
		jTf_Port.setText("3456");
		jTf_Port.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jTf_Port.setBounds(130, 55, 120, 30);
		jTf_Port.setColumns(10);
		jP_Server.add(jTf_Port);

		jBtn_Browse1 = new JButton("Browse");
		jBtn_Browse1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jBtn_Browse1.setBounds(390, 11, 131, 30);
		jBtn_Browse1.setActionCommand(actionC.browse.toString());
		jBtn_Browse1.addActionListener(this);
		jP_Server.add(jBtn_Browse1);

		jBtn_ManagerTable = new JButton("Manager DB");
		jBtn_ManagerTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jBtn_ManagerTable.setBounds(531, 11, 131, 30);
		jBtn_ManagerTable.setActionCommand(actionC.managertable.toString());
		jBtn_ManagerTable.addActionListener(this);
		jP_Server.add(jBtn_ManagerTable);

		jBtn_Listen = new JButton("Listen");
		jBtn_Listen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jBtn_Listen.setBounds(390, 54, 131, 30);
		jBtn_Listen.setActionCommand(actionC.listen.toString());
		jBtn_Listen.addActionListener(this);
		jP_Server.add(jBtn_Listen);

		jBtn_Stop = new JButton("Stop");
		jBtn_Stop.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jBtn_Stop.setBounds(531, 54, 131, 30);
		jBtn_Stop.setActionCommand(actionC.stop.toString());
		jBtn_Stop.addActionListener(this);
		jP_Server.add(jBtn_Stop);

		jP_Log = new JPanel();
		jP_Log.setBounds(20, 100, 642, 363);
		jP_Log.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createEtchedBorder(), "LOG"));
		jP_Server.add(jP_Log);

		colNameTableLog.add("ID");
		colNameTableLog.add("Time");
		colNameTableLog.add("Action");

		tableLog = new JTable();
		tableLog.setFont(new java.awt.Font("Tahoma", 0, 14));
		tableLog.setModel(new DefaultTableModel(_Logs, colNameTableLog));
		tableLog.getColumnModel().getColumn(0).setPreferredWidth(1);
		tableLog.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableLog.getColumnModel().getColumn(2).setPreferredWidth(100);

		jSP_Log = new javax.swing.JScrollPane();
		jSP_Log.setBounds(10, 21, 622, 331);
		jSP_Log.setViewportView(tableLog);

		jP_Log.setLayout(null);
		jP_Log.add(jSP_Log);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(298, 71, 46, 14);
		jP_Server.add(lblNewLabel);
		
		
		
		// manager
		jBtn_AddTable = new JButton("Add Table");
		jBtn_AddTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jBtn_AddTable.setBounds(10, 75, 131, 30);
		jBtn_AddTable.setActionCommand("addtable");
		jBtn_AddTable.addActionListener(this);
		jP_Manager.add(jBtn_AddTable);

		jBtn_DeleteTable = new JButton("Delete Table");
		jBtn_DeleteTable.setToolTipText("");
		jBtn_DeleteTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jBtn_DeleteTable.setActionCommand("deletetable");
		jBtn_DeleteTable.addActionListener(this);
		jBtn_DeleteTable.setBounds(10, 116, 131, 30);
		jP_Manager.add(jBtn_DeleteTable);

		jBtn_AddData = new JButton("Add Data");
		jBtn_AddData.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jBtn_AddData.setActionCommand("adddata");
		jBtn_AddData.addActionListener(this);
		jBtn_AddData.setBounds(10, 157, 131, 30);
		jP_Manager.add(jBtn_AddData);

		JLabel lblFileDatabase = new JLabel("File Database:");
		lblFileDatabase.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFileDatabase.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFileDatabase.setBounds(10, 11, 103, 29);
		jP_Manager.add(lblFileDatabase);

		jTf_AddrFileDB = new JTextField();
		jTf_AddrFileDB.setEditable(false);
		jTf_AddrFileDB.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jTf_AddrFileDB.setBounds(123, 11, 387, 30);
		jP_Manager.add(jTf_AddrFileDB);
		jTf_AddrFileDB.setColumns(10);

		jCbb_ListTable = new JComboBox();
		jCbb_ListTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jCbb_ListTable.setBounds(151, 116, 103, 30);
		jP_Manager.add(jCbb_ListTable);

		jBtn_Browse = new JButton("Browse");
		jBtn_Browse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jBtn_Browse.setActionCommand("browse");
		jBtn_Browse.setBounds(528, 10, 138, 30);
		jBtn_Browse.setActionCommand("browse");
		jBtn_Browse.addActionListener(this);
		jP_Manager.add(jBtn_Browse);

		jBtn_CreateNewDatabase = new JButton("Create DataBase");
		jBtn_CreateNewDatabase.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jBtn_CreateNewDatabase.setActionCommand("createdb");
		jBtn_CreateNewDatabase.addActionListener(this);
		jBtn_CreateNewDatabase.setBounds(528, 75, 138, 30);
		jP_Manager.add(jBtn_CreateNewDatabase);
		
		
	}

	public void Init() {
		jBtn_Listen.setEnabled(false);
		jBtn_Stop.setEnabled(false);

		_FileFilterDb = new FileFilterDb() {
		};

		jFChooser = new JFileChooser();
		jFChooser.setFileFilter(_FileFilterDb);
		_PathFileDataBase = "";
		// khoi tao port ban dau la 3456 khi listen se lay thong tin port nguoi
		// dung nhap vao
		_MyServer = new MyServer(3456);
		_ThreadServer = new Thread(_MyServer);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent arg0) {

		actionC action = actionC.valueOf(arg0.getActionCommand());

		if (action == actionC.browse) {

			int returnVal = jFChooser.showDialog(this, "Choose DataBase");

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				_PathFileDataBase = jFChooser.getSelectedFile().getPath();
				jTf_AddrFileDB.setText(_PathFileDataBase);
				Main.SetDataBase(Database.loadFromFile(_PathFileDataBase));
				PrintLog("Đã chọn File DataBase");

				jBtn_Listen.setEnabled(true);
				jBtn_Stop.setEnabled(false);
			} else {
				if (_PathFileDataBase.equals("")) {
					PrintLog("Đã hủy việc chọn File DataBase");
				}
			}
		}

		if (action == actionC.managertable) {
			_FrameManagerTable = new Frame_ManagerDB();
			_FrameManagerTable.setVisible(true);
		}

		if (action == actionC.listen) {
			if (!jTf_Port.getText().trim().equals("")) {

				_Port = Integer.parseInt(jTf_Port.getText().trim());

				if (_ThreadServer.isAlive()) {
					_MyServer.stop();
					_ThreadServer.stop();
				}
				_MyServer.SetPort(_Port);
				_ThreadServer = new Thread(_MyServer);
				_ThreadServer.start();

				jBtn_Listen.setEnabled(false);
				jBtn_Stop.setEnabled(true);
			} else {
				JOptionPane.showMessageDialog(this,
						"Xin nhập thông tin port !!!", "Warning",
						JOptionPane.WARNING_MESSAGE);
			}
		}

		if (action == actionC.stop) {
			_MyServer.stop();
			_ThreadServer.stop();

			jBtn_Listen.setEnabled(true);
			jBtn_Stop.setEnabled(false);
		}
		
		
		
		
	}

	public static void PrintLog(String strAction) {
		Vector<String> tLog = new Vector<String>();
		Calendar c = Calendar.getInstance();
		String time;

		// add id
		tLog.add(Integer.toString(_Logs.size() + 1));

		int month = c.get(Calendar.MONTH) + 1;

		// add time
		time = c.get(Calendar.DAY_OF_MONTH) + "/" + month + "/"
				+ c.get(Calendar.YEAR) + "   " + c.get(Calendar.HOUR_OF_DAY)
				+ ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);

		tLog.add(time);

		// add action
		tLog.add(strAction);

		_Logs.add(tLog);

		tableLog.setModel(new DefaultTableModel(_Logs, colNameTableLog));
	}
}
