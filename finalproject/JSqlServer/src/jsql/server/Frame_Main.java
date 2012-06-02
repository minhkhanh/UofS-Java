package jsql.server;

import java.awt.BorderLayout;
import java.awt.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.html.ListView;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import jsql.data.Database;

@SuppressWarnings("serial")
public class Frame_Main extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPanel p_Main;
	private JPanel p_Log;
	private JTextField tf_AddrFileDB;
	private JTextField tf_Port;
	private JButton btn_Browse;
	private JButton btn_ManagerTable;
	private JButton btn_Listen;
	private JButton btn_Stop;
	private JLabel lbl_AddrFolder;
	private JLabel lbl_Port;
	private JFileChooser jFChooser;
	private JTable tableLog;
	private JScrollPane jSP_Log;

	private MyServer _MyServer;
	private int _Port;
	private Frame_ManagerTable _FrameManagerTable;
	private FileFilter _FileFilter_DB;
	private Database _DataBase;
	private String _PathDataBase;

	private Vector<String> colNameTableLog = new Vector<String>();
	private Vector<Vector<String>> _Logs = new Vector<Vector<String>>();

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

		p_Main = new JPanel();
		contentPane.add(p_Main, BorderLayout.CENTER);
		p_Main.setLayout(null);

		lbl_AddrFolder = new JLabel("File DataBase:");
		lbl_AddrFolder.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_AddrFolder.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_AddrFolder.setBounds(20, 11, 100, 30);
		p_Main.add(lbl_AddrFolder);

		lbl_Port = new JLabel("Port:");
		lbl_Port.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_Port.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_Port.setBounds(10, 54, 110, 30);
		p_Main.add(lbl_Port);

		tf_AddrFileDB = new JTextField();
		tf_AddrFileDB.setEditable(false);
		tf_AddrFileDB.setText("");
		tf_AddrFileDB.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tf_AddrFileDB.setBounds(130, 12, 239, 30);
		tf_AddrFileDB.setColumns(10);
		p_Main.add(tf_AddrFileDB);

		tf_Port = new JTextField();
		tf_Port.setText("3456");
		tf_Port.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tf_Port.setBounds(130, 55, 120, 30);
		tf_Port.setColumns(10);
		p_Main.add(tf_Port);

		btn_Browse = new JButton("Browse");
		btn_Browse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_Browse.setBounds(390, 11, 131, 30);
		btn_Browse.setActionCommand("browse");
		btn_Browse.addActionListener(this);
		p_Main.add(btn_Browse);

		btn_ManagerTable = new JButton("Manager Table");
		btn_ManagerTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_ManagerTable.setBounds(531, 11, 131, 30);
		btn_ManagerTable.setActionCommand("managertable");
		btn_ManagerTable.addActionListener(this);
		p_Main.add(btn_ManagerTable);

		btn_Listen = new JButton("Listen");
		btn_Listen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_Listen.setBounds(390, 54, 131, 30);
		btn_Listen.setActionCommand("listen");
		btn_Listen.addActionListener(this);
		p_Main.add(btn_Listen);

		btn_Stop = new JButton("Stop");
		btn_Stop.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_Stop.setBounds(531, 54, 131, 30);
		btn_Stop.setActionCommand("stop");
		btn_Stop.addActionListener(this);
		p_Main.add(btn_Stop);

		p_Log = new JPanel();
		p_Log.setBounds(20, 100, 642, 376);
		p_Log.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createEtchedBorder(), "LOG"));
		p_Main.add(p_Log);

		colNameTableLog.add("ID");
		colNameTableLog.add("Time");
		colNameTableLog.add("Action");

		tableLog = new JTable();
		tableLog.setFont(new java.awt.Font("Tahoma", 0, 14));
		tableLog.setModel(new DefaultTableModel(_Logs, colNameTableLog));

		jSP_Log = new javax.swing.JScrollPane();
		jSP_Log.setBounds(10, 21, 622, 344);
		jSP_Log.setViewportView(tableLog);

		p_Log.setLayout(null);
		p_Log.add(jSP_Log);
	}

	public void Init() {

		_FileFilter_DB = new FileFilter() {
			@Override
			public String getDescription() {
				return "DataBase";
			}

			@Override
			public boolean accept(File f) {
				String extension = getExtension(f);
				if (extension != null && extension.equals("db")) {
					return true;
				}

				if (f.isDirectory()) {
					return true;
				}

				return false;
			}
		};

		jFChooser = new JFileChooser();
		jFChooser.setFileFilter(_FileFilter_DB);

		_PathDataBase = "";
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if ("browse".equals(arg0.getActionCommand())) {

			int returnVal = jFChooser.showDialog(this, "Chọn DataBase");

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				_PathDataBase = jFChooser.getSelectedFile().getPath();
				tf_AddrFileDB.setText(_PathDataBase);
				// _DataBase = new Database(_PathDataBase);
				PrintLog("Đã chọn File DataBase");
			} else {
				if (!_PathDataBase.equals("")) {
					PrintLog("Chọn chọn File DataBase");
				} else {
					PrintLog("Đã hủy việc chọn File DataBase");
				}
			}
		}

		if ("managertable".equals(arg0.getActionCommand())) {
			_FrameManagerTable = new Frame_ManagerTable();
			_FrameManagerTable.setVisible(true);
			_FrameManagerTable.setAddrFileDB(_PathDataBase);
		}

		if ("listen".equals(arg0.getActionCommand())) {
			if (tf_Port.getText().trim() != null) {
				_Port = Integer.parseInt(tf_Port.getText().trim());
				_MyServer = new MyServer(_Port);
				PrintLog("Đã mở Server");
			}
		}

		if ("stop".equals(arg0.getActionCommand())) {
			// _MyServer.stop();

			PrintLog("Đã dừng server");
		}
	}

	public String getExtension(File f) {
		String ext = null;
		String s = f.getName();
		int i = s.lastIndexOf('.');

		if (i > 0 && i < s.length() - 1) {
			ext = s.substring(i + 1).toLowerCase();
		}
		return ext;
	}

	public void PrintLog(String strAction) {
		Vector<String> tLog = new Vector<>();
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
