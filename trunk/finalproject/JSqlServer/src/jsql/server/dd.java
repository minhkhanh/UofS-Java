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
		sv_browse, sv_managertable, sv_listen, sv_stop, mn_browse, mn_addtable, mn_deletetable, mn_adddata
	};

	private JPanel jP_Main;

	private MyServer _MyServer;
	private Thread _ThreadServer;
	private int _Port;
	private Frame_ManagerDB _FrameManagerTable;
	private String _PathFileDataBase;

	JTabbedPane tabbedPane;

	public dd() {
		this.InitFrame();
		this.Init();
	}

	@SuppressWarnings("rawtypes")
	public void InitFrame() {
		setResizable(false);
		setTitle("jSQLServer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 800, 570);
		jP_Main = new JPanel();
		jP_Main.setBorder(new EmptyBorder(5, 5, 5, 5));
		jP_Main.setLayout(new BorderLayout(0, 0));
		setContentPane(jP_Main);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		jP_Main.add(tabbedPane, BorderLayout.CENTER);

		this.setJMenuBar(new MainMenuBar());

		tabbedPane.add(new Panel_Server());
		tabbedPane.add(new Panel_Manager());
	}

	public void Init() {
		/*
		 * jBtn_Listen.setEnabled(false); jBtn_Stop.setEnabled(false);
		 * 
		 * _FileFilterDb = new FileFilterDb() { };
		 * 
		 * jFChooser = new JFileChooser();
		 * jFChooser.setFileFilter(_FileFilterDb); _PathFileDataBase = ""; //
		 * khoi tao port ban dau la 3456 khi listen se lay thong tin port nguoi
		 * // dung nhap vao _MyServer = new MyServer(3456); _ThreadServer = new
		 * Thread(_MyServer);
		 */
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent arg0) {

		

	}

	
}
