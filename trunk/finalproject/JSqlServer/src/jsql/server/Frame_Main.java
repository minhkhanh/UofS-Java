package jsql.server;

import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileFilter;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.io.File;

import jsql.data.Database;

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
	private JFileChooser jFChooser;
	private JLabel lbl_Status;

	private MyServer _MyServer;
	private int _Port;
	private Frame_ManagerTable _FrameManagerTable;
	private FileFilter _FileFilter_DB;
	private Database _DataBase;
	private String _PathDataBase;

	public Frame_Main() {
		this.InitFrame();
		this.Init();
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
		tf_AddrFolder.setEditable(false);
		tf_AddrFolder.setText("");
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

		lbl_Status = new JLabel("Status");
		lbl_Status.setBounds(43, 100, 161, 14);
		lbl_Status.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_Status.setText("Chưa chọn file DataBase");
		panel_1.add(lbl_Status);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0,
				0)));
		panel_1_1.setBounds(10, 128, 654, 253);
		panel_1.add(panel_1_1);
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
				tf_AddrFolder.setText(_PathDataBase);
				lbl_Status.setText("Đã chọn file DataBase");

				_DataBase = new Database(_PathDataBase);
			} else {
				if (!_PathDataBase.equals(""))
					lbl_Status.setText("Đã chọn file DataBase");
				else
					lbl_Status.setText("Chưa chọn file DataBase");
			}
		}

		if ("managertable".equals(arg0.getActionCommand())) {
			_FrameManagerTable = new Frame_ManagerTable();
			_FrameManagerTable.setVisible(true);
		}

		if ("listen".equals(arg0.getActionCommand())) {
			if (tf_Port.getText().trim() != null) {
				_Port = Integer.parseInt(tf_Port.getText().trim());

				_MyServer = new MyServer(_Port);
			}
		}

		if ("stop".equals(arg0.getActionCommand())) {
			_MyServer.stop();
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
}
