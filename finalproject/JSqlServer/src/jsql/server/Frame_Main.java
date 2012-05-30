package jsql.server;

import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

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
	JFileChooser jFChooser;

	private MyServer _MyServer;
	private int _Port;
	private Frame_ManagerTable _FrameManagerTable;
	JLabel lblNewLabel;

	public Frame_Main() {
		this.InitFrame();
	}

	public void InitFrame() {
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

		lbl_AddrFolder = new JLabel("Thư mục dữ liệu:");
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
		btn_Browse.addActionListener(this);
		panel_1.add(btn_Browse);

		btn_ManagerTable = new JButton("Manager Table");
		btn_ManagerTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_ManagerTable.setBounds(531, 11, 131, 30);
		btn_ManagerTable.setActionCommand("managertable");
		btn_ManagerTable.addActionListener(this);
		panel_1.add(btn_ManagerTable);

		btn_Listen = new JButton("Listen");
		btn_Listen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_Listen.setBounds(390, 54, 131, 30);
		btn_Listen.setActionCommand("listen");
		btn_Listen.addActionListener(this);
		panel_1.add(btn_Listen);

		btn_Stop = new JButton("Stop");
		btn_Stop.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_Stop.setBounds(531, 54, 131, 30);
		btn_Stop.setActionCommand("stop");
		btn_Stop.addActionListener(this);
		panel_1.add(btn_Stop);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0,
				0)));
		panel_1_1.setBounds(10, 128, 654, 253);
		panel_1.add(panel_1_1);

		lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(43, 100, 161, 14);
		panel_1.add(lblNewLabel);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if ("browse".equals(arg0.getActionCommand())) {
			jFChooser = new JFileChooser();
			;
			int op = jFChooser.showOpenDialog(this);

			if (op == JFileChooser.APPROVE_OPTION) {
				// lấy đường file ra xử lý
				// jFChooser.getSelectedFile().getPath();
			} else {
				// cancel
			}
		}

		if ("managertable".equals(arg0.getActionCommand())) {
			_FrameManagerTable = new Frame_ManagerTable();
			_FrameManagerTable.setVisible(true);
		}

		if ("listen".equals(arg0.getActionCommand())) {
			if (tf_Port.getText().trim() != null) {
				_Port = Integer.parseInt(tf_Port.getText().trim());

				//_MyServer._Port = _Port;
				_MyServer = new MyServer();
			}
		}

		if ("stop".equals(arg0.getActionCommand())) {
			_MyServer.stop();
		}
	}
}
