package jsql.server;

import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

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
public class Frame_Main extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPanel jP_Main;
	private JPanel jP_Log;
	private JTextField tf_AddrFileDB;
	private JTextField tf_Port;
	private JButton jBtn_Browse;
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

	private MyServer _MyServer;
	private Thread _ThreadServer;
	private int _Port;
	private Frame_ManagerTable _FrameManagerTable;
	private FileFilterDb _FileFilterDb;
	private String _PathFileDataBase;

	public Frame_Main() {
		this.InitFrame();
		this.Init();
	}

	public void InitFrame() {
		setResizable(false);
		setTitle("jSQLServer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 700, 525);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		jP_Main = new JPanel();
		contentPane.add(jP_Main, BorderLayout.CENTER);
		jP_Main.setLayout(null);

		jLbl_AddrFolder = new JLabel("File DataBase:");
		jLbl_AddrFolder.setHorizontalAlignment(SwingConstants.RIGHT);
		jLbl_AddrFolder.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jLbl_AddrFolder.setBounds(20, 11, 100, 30);
		jP_Main.add(jLbl_AddrFolder);

		jLbl_Port = new JLabel("Port:");
		jLbl_Port.setHorizontalAlignment(SwingConstants.RIGHT);
		jLbl_Port.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jLbl_Port.setBounds(10, 54, 110, 30);
		jP_Main.add(jLbl_Port);

		tf_AddrFileDB = new JTextField();
		tf_AddrFileDB.setEditable(false);
		tf_AddrFileDB.setText("");
		tf_AddrFileDB.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tf_AddrFileDB.setBounds(130, 12, 239, 30);
		tf_AddrFileDB.setColumns(10);
		jP_Main.add(tf_AddrFileDB);

		tf_Port = new JTextField();
		tf_Port.setText("3456");
		tf_Port.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tf_Port.setBounds(130, 55, 120, 30);
		tf_Port.setColumns(10);
		jP_Main.add(tf_Port);

		jBtn_Browse = new JButton("Browse");
		jBtn_Browse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jBtn_Browse.setBounds(390, 11, 131, 30);
		jBtn_Browse.setActionCommand("browse");
		jBtn_Browse.addActionListener(this);
		jP_Main.add(jBtn_Browse);

		jBtn_ManagerTable = new JButton("Manager DB");
		jBtn_ManagerTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jBtn_ManagerTable.setBounds(531, 11, 131, 30);
		jBtn_ManagerTable.setActionCommand("managertable");
		jBtn_ManagerTable.addActionListener(this);
		jP_Main.add(jBtn_ManagerTable);

		jBtn_Listen = new JButton("Listen");
		jBtn_Listen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jBtn_Listen.setBounds(390, 54, 131, 30);
		jBtn_Listen.setActionCommand("listen");
		jBtn_Listen.addActionListener(this);
		jP_Main.add(jBtn_Listen);

		jBtn_Stop = new JButton("Stop");
		jBtn_Stop.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jBtn_Stop.setBounds(531, 54, 131, 30);
		jBtn_Stop.setActionCommand("stop");
		jBtn_Stop.addActionListener(this);
		jP_Main.add(jBtn_Stop);

		jP_Log = new JPanel();
		jP_Log.setBounds(20, 100, 642, 376);
		jP_Log.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createEtchedBorder(), "LOG"));
		jP_Main.add(jP_Log);

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
		jSP_Log.setBounds(10, 21, 622, 344);
		jSP_Log.setViewportView(tableLog);

		jP_Log.setLayout(null);
		jP_Log.add(jSP_Log);

		_FileFilterDb = new FileFilterDb() {
		};

		jFChooser = new JFileChooser();
		jFChooser.setFileFilter(_FileFilterDb);
		_PathFileDataBase = "";
		_MyServer = new MyServer(3456);
		_ThreadServer = new Thread(_MyServer);
	}
	
	public void Init(){
		jBtn_Listen.setEnabled(false);
		jBtn_Stop.setEnabled(false);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent arg0) {

		if ("browse".equals(arg0.getActionCommand())) {

			int returnVal = jFChooser.showDialog(this, "Choose DataBase");

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				_PathFileDataBase = jFChooser.getSelectedFile().getPath();
				tf_AddrFileDB.setText(_PathFileDataBase);
				Main.SetDataBase(Database.loadFromFile(_PathFileDataBase));
				PrintLog("Đã chọn File DataBase");
				

				jBtn_Listen.setEnabled(true);
				jBtn_Stop.setEnabled(false);
				
			} else {
				if (!_PathFileDataBase.equals("")) {
					PrintLog("Chọn chọn File DataBase");
				} else {
					PrintLog("Đã hủy việc chọn File DataBase");
				}
			}
		}

		if ("managertable".equals(arg0.getActionCommand())) {
			_FrameManagerTable = new Frame_ManagerTable();
			_FrameManagerTable.setVisible(true);
		}

		if ("listen".equals(arg0.getActionCommand())) {
			if (tf_Port.getText().trim() != null) {

				_Port = Integer.parseInt(tf_Port.getText().trim());

				if (_ThreadServer.isAlive()) {
					_MyServer.stop();
					_ThreadServer.stop();
				}
				_MyServer.SetPort(_Port);
				_ThreadServer = new Thread(_MyServer);
				_ThreadServer.start();
				

				jBtn_Listen.setEnabled(false);
				jBtn_Stop.setEnabled(true);
			}
		}

		if ("stop".equals(arg0.getActionCommand())) {
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

		// add time
		time = c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.MONTH) + "/"
				+ c.get(Calendar.YEAR) + "   " + c.get(Calendar.HOUR_OF_DAY)
				+ ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);

		tLog.add(time);

		// add action
		tLog.add(strAction);

		//
		_Logs.add(tLog);

		tableLog.setModel(new DefaultTableModel(_Logs, colNameTableLog));
	}
}
