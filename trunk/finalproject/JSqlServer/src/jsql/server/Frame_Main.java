package jsql.server;

import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Component;

@SuppressWarnings("serial")
public class Frame_Main extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPanel panel_1;
	private JTextField tf_AddrFolder;
	private JTextField tf_Port;
	private JButton btn_Browse;
	private JButton btn_ManagerTable;
	private JButton btn_Listen;
	private JButton btn_Stop;
	private JLabel lbl_AddrFolder;
	private JLabel lbl_Port;

	private MyServer _MyServer;
	private Frame_Browse _FrameBrowse;
	private Frame_ManagerTable _FrameManagerTable;
	

	public Frame_Main() {
		setResizable(false);
		setTitle("jSQLServer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 700, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);

		lbl_AddrFolder = new JLabel("Thu muc du lieu:");
		lbl_AddrFolder.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_AddrFolder.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_AddrFolder.setBounds(10, 11, 110, 30);
		panel_1.add(lbl_AddrFolder);

		lbl_Port = new JLabel("Port:");
		lbl_Port.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_Port.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_Port.setBounds(10, 54, 110, 30);
		panel_1.add(lbl_Port);

		tf_AddrFolder = new JTextField();
		tf_AddrFolder.setText("D:\\DB\\");
		tf_AddrFolder.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tf_AddrFolder.setBounds(130, 12, 239, 30);
		tf_AddrFolder.setColumns(10);
		panel_1.add(tf_AddrFolder);

		tf_Port = new JTextField();
		tf_Port.setText("3456");
		tf_Port.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tf_Port.setBounds(130, 55, 120, 30);
		tf_Port.setColumns(10);
		panel_1.add(tf_Port);

		btn_Browse = new JButton("Browse");
		btn_Browse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_Browse.setBounds(390, 11, 131, 30);
		btn_Browse.setActionCommand("browse");
		panel_1.add(btn_Browse);

		btn_ManagerTable = new JButton("Manager Table");
		btn_ManagerTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_ManagerTable.setBounds(531, 11, 131, 30);
		btn_ManagerTable.setActionCommand("managertable");
		panel_1.add(btn_ManagerTable);

		btn_Listen = new JButton("Listen");
		btn_Listen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_Listen.setBounds(390, 54, 131, 30);
		btn_Listen.setActionCommand("listen");
		panel_1.add(btn_Listen);

		btn_Stop = new JButton("Stop");
		btn_Stop.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_Stop.setBounds(531, 54, 131, 30);
		btn_Stop.setActionCommand("stop");
		panel_1.add(btn_Stop);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0,
				0)));
		panel_1_1.setBounds(10, 128, 654, 253);
		panel_1.add(panel_1_1);
		
		
		btn_Browse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				_FrameBrowse = new Frame_Browse();
				_FrameBrowse.setVisible(true);
			}
		});
		
		btn_ManagerTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				_FrameManagerTable = new Frame_ManagerTable();
				_FrameManagerTable.setVisible(true);
			}
		});
		
		btn_Listen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		btn_Stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
		case "browse":
			break;
		case "managertable":
			break;
		case "listen":
			int port = 3456; // lay port tu tf_port
			_MyServer = new MyServer(port);
			break;
		case "stop":
			break;
		}
	}
}
