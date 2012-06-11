package jsql.server;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import jsql.data.Database;

/**
 * @author DWater
 * 
 */
@SuppressWarnings("serial")
public class Panel_Server extends JPanel implements ActionListener {

	public enum actionC {
		sv_browse, sv_listen, sv_stop
	};

	private JPanel jP_Log;
	private JTextField jTf_AddrFileDB1;
	private JTextField jTf_Port;
	private JButton jBtn_Browse1;
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
	private String _PathFileDataBase;

	public Panel_Server() {

		this.setSize(794, 519);
		this.setLayout(null);
		this.setName("Server");

		jLbl_AddrFolder = new JLabel("File DataBase:");
		jLbl_AddrFolder.setHorizontalAlignment(SwingConstants.RIGHT);
		jLbl_AddrFolder.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jLbl_AddrFolder.setBounds(20, 11, 100, 30);
		this.add(jLbl_AddrFolder);

		jLbl_Port = new JLabel("Port:");
		jLbl_Port.setHorizontalAlignment(SwingConstants.RIGHT);
		jLbl_Port.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jLbl_Port.setBounds(10, 54, 110, 30);
		this.add(jLbl_Port);

		jTf_AddrFileDB1 = new JTextField();
		jTf_AddrFileDB1.setEditable(false);
		jTf_AddrFileDB1.setText("");
		jTf_AddrFileDB1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jTf_AddrFileDB1.setBounds(130, 12, 486, 30);
		jTf_AddrFileDB1.setColumns(10);
		this.add(jTf_AddrFileDB1);

		jTf_Port = new JTextField();
		jTf_Port.setText("3456");
		jTf_Port.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jTf_Port.setBounds(130, 55, 120, 30);
		jTf_Port.setColumns(10);
		this.add(jTf_Port);

		jBtn_Browse1 = new JButton("Browse");
		jBtn_Browse1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jBtn_Browse1.setBounds(638, 11, 131, 30);
		jBtn_Browse1.setActionCommand(actionC.sv_browse.toString());
		jBtn_Browse1.addActionListener(this);
		this.add(jBtn_Browse1);

		jBtn_Listen = new JButton("Listen");
		jBtn_Listen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jBtn_Listen.setBounds(485, 54, 131, 30);
		jBtn_Listen.setActionCommand(actionC.sv_listen.toString());
		jBtn_Listen.addActionListener(this);
		this.add(jBtn_Listen);

		jBtn_Stop = new JButton("Stop");
		jBtn_Stop.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jBtn_Stop.setBounds(638, 54, 131, 30);
		jBtn_Stop.setActionCommand(actionC.sv_stop.toString());
		jBtn_Stop.addActionListener(this);
		this.add(jBtn_Stop);

		jP_Log = new JPanel();
		jP_Log.setBounds(10, 100, 759, 363);
		jP_Log.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createEtchedBorder(), "LOG"));
		this.add(jP_Log);

		colNameTableLog.add("ID");
		colNameTableLog.add("Time");
		colNameTableLog.add("Action");

		tableLog = new JTable();
		tableLog.setFont(new java.awt.Font("Tahoma", 0, 14));
		tableLog.setModel(new DefaultTableModel(_Logs, colNameTableLog));
		tableLog.getColumnModel().getColumn(0).setPreferredWidth(1);
		tableLog.getColumnModel().getColumn(1).setPreferredWidth(1);
		tableLog.getColumnModel().getColumn(2).setPreferredWidth(100);

		jSP_Log = new javax.swing.JScrollPane();
		jSP_Log.setBounds(10, 21, 739, 331);
		jSP_Log.setViewportView(tableLog);

		jP_Log.setLayout(null);
		jP_Log.add(jSP_Log);

		// INIT
		jBtn_Listen.setEnabled(false);
		jBtn_Stop.setEnabled(false);

		jFChooser = new JFileChooser();
		jFChooser.setFileFilter(new FileFilterDb() {
		});
		_PathFileDataBase = "";
		_MyServer = new MyServer(3456);
		_ThreadServer = new Thread(_MyServer);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		actionC action = actionC.valueOf(arg0.getActionCommand());

		if (action == actionC.sv_browse) {
			int returnVal = jFChooser.showDialog(this, "Choose DataBase");

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				_PathFileDataBase = jFChooser.getSelectedFile().getPath();
				jTf_AddrFileDB1.setText(_PathFileDataBase);
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

		if (action == actionC.sv_listen) {
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

		if (action == actionC.sv_stop) {
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
