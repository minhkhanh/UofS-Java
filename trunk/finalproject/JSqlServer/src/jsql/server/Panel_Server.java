package jsql.server;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 * @author DWater
 * 
 */
@SuppressWarnings("serial")
public class Panel_Server extends JPanel implements ActionListener {

	private JPanel jP_Log;
	private JTextField jTf_Port;
	private JButton jBtn_Listen;
	private JButton jBtn_Stop;
	private JLabel jLbl_Port;
	private JLabel jLbl_Status;
	private static JTable tableLog;
	private JScrollPane jSP_Log;
	private static Vector<String> colNameTableLog = new Vector<String>();
	private static Vector<Vector<String>> _Logs = new Vector<Vector<String>>();

	private MyServer _MyServer;
	private Thread _ThreadServer;
	private int _Port;

	public Panel_Server() {
		this.InitFrame();
		this.Init();
	}

	public void InitFrame() {

		this.setSize(784, 439);
		this.setLayout(null);
		this.setName("Server");

		jLbl_Port = new JLabel("Port:");
		jLbl_Port.setHorizontalAlignment(SwingConstants.RIGHT);
		jLbl_Port.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jLbl_Port.setBounds(171, 11, 110, 30);
		this.add(jLbl_Port);

		jTf_Port = new JTextField();
		jTf_Port.setText("3456");
		jTf_Port.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jTf_Port.setBounds(322, 12, 120, 30);
		jTf_Port.setColumns(10);
		this.add(jTf_Port);

		jBtn_Listen = new JButton("Listen");
		jBtn_Listen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jBtn_Listen.setBounds(502, 11, 131, 30);
		jBtn_Listen.setActionCommand(KeyAction.server_listen.toString());
		jBtn_Listen.addActionListener(this);
		this.add(jBtn_Listen);

		jBtn_Stop = new JButton("Stop");
		jBtn_Stop.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jBtn_Stop.setBounds(643, 11, 131, 30);
		jBtn_Stop.setActionCommand(KeyAction.server_stop.toString());
		jBtn_Stop.addActionListener(this);
		this.add(jBtn_Stop);

		jP_Log = new JPanel();
		jP_Log.setBounds(10, 52, 764, 376);
		jP_Log.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createEtchedBorder(), "LOG"));
		this.add(jP_Log);

		colNameTableLog.add("ID");
		colNameTableLog.add("Time");
		colNameTableLog.add("Action");

		tableLog = new JTable();
		tableLog.setFont(new java.awt.Font("Tahoma", 0, 14));
		tableLog.setModel(new DefaultTableModel(_Logs, colNameTableLog));
		tableLog.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableLog.getColumnModel().getColumn(1).setPreferredWidth(50);
		tableLog.getColumnModel().getColumn(2).setPreferredWidth(100);

		jSP_Log = new javax.swing.JScrollPane();
		jSP_Log.setBounds(10, 21, 744, 344);
		jSP_Log.setViewportView(tableLog);

		jP_Log.setLayout(null);
		jP_Log.add(jSP_Log);
	}

	public void Init() {
		// INIT
		jBtn_Listen.setEnabled(false);
		jBtn_Stop.setEnabled(false);

		jLbl_Status = new JLabel("Port:");
		jLbl_Status.setHorizontalAlignment(SwingConstants.RIGHT);
		jLbl_Status.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jLbl_Status.setBounds(10, 11, 110, 30);
		add(jLbl_Status);

		_MyServer = new MyServer(3456);
		_ThreadServer = new Thread(_MyServer);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent arg0) {

		KeyAction action = KeyAction.valueOf(arg0.getActionCommand());

		if (action == KeyAction.server_listen) {

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

		if (action == KeyAction.server_stop) {
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

		tableLog.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableLog.getColumnModel().getColumn(1).setPreferredWidth(50);
		tableLog.getColumnModel().getColumn(2).setPreferredWidth(100);
	}

	public void ChoosenDatabase() {

		if (!_ThreadServer.isAlive()) {
			jBtn_Listen.setEnabled(true);
			jBtn_Stop.setEnabled(false);
		}
	}
}
