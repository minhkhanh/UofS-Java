package jsql.server;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import jsql.data.Database;

/**
 * @author DWater
 * 
 */
@SuppressWarnings("serial")
public class Frame_CreateNewDB extends JFrame implements ActionListener {

	private JPanel jP_Main;
	private JPanel jP_CreateNewDB;
	private JButton jBtn_NewButton;
	private JButton jBtn_Cancel;
	private JButton jBtn_Browse;
	private JTextField jTf_Target;
	private JTextField jTf_Name;
	private JFileChooser jFChooser;

	public Frame_CreateNewDB() {
		this.InitFrame();
		this.Init();
	}

	public void InitFrame() {


		setTitle("Create New Database");
		setResizable(false);
		setSize(426, 275);
		setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2
				- getWidth() / 2,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2
						- getHeight() / 2);

		jP_Main = new JPanel();
		jP_Main.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(jP_Main);
		jP_Main.setLayout(null);

		jP_CreateNewDB = new JPanel();
		jP_CreateNewDB.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createEtchedBorder(),
				"Create New DataBase"));
		jP_CreateNewDB.setBounds(10, 11, 395, 216);
		jP_Main.add(jP_CreateNewDB);
		jP_CreateNewDB.setLayout(null);

		jBtn_NewButton = new JButton("Create");
		jBtn_NewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jBtn_NewButton.setBounds(20, 173, 89, 30);
		jBtn_NewButton.setActionCommand(KeyAction.cnd_create.toString());
		jBtn_NewButton.addActionListener(this);
		jP_CreateNewDB.add(jBtn_NewButton);

		jBtn_Cancel = new JButton("Cancel");
		jBtn_Cancel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jBtn_Cancel.setBounds(142, 173, 89, 30);
		jBtn_Cancel.setActionCommand(KeyAction.cnd_cancel.toString());
		jBtn_Cancel.addActionListener(this);
		jP_CreateNewDB.add(jBtn_Cancel);

		jBtn_Browse = new JButton("Browse");
		jBtn_Browse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jBtn_Browse.setBounds(290, 43, 89, 30);
		jBtn_Browse.setActionCommand(KeyAction.cnd_browse.toString());
		jBtn_Browse.addActionListener(this);
		jP_CreateNewDB.add(jBtn_Browse);

		jTf_Target = new JTextField();
		jTf_Target.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jTf_Target.setBounds(20, 44, 260, 30);
		jP_CreateNewDB.add(jTf_Target);
		jTf_Target.setColumns(10);

		jTf_Name = new JTextField();
		jTf_Name.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jTf_Name.setColumns(10);
		jTf_Name.setBounds(20, 115, 260, 30);
		jP_CreateNewDB.add(jTf_Name);

		JLabel lblng = new JLabel("Target");
		lblng.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblng.setBounds(26, 16, 46, 30);
		jP_CreateNewDB.add(lblng);

		JLabel lblEnterYourName = new JLabel("New Database name:");
		lblEnterYourName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEnterYourName.setBounds(26, 85, 209, 30);
		jP_CreateNewDB.add(lblEnterYourName);
	}

	public void Init() {
		jFChooser = new JFileChooser();
		jFChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		KeyAction action = KeyAction.valueOf(arg0.getActionCommand());

		if (action == KeyAction.cnd_browse) {

			// chon thu muc de luu database
			int returnVal = jFChooser.showDialog(this, "Choose");
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				String pathFileDataBase = jFChooser.getSelectedFile().getPath();
				jTf_Target.setText(pathFileDataBase);
			}
		}

		if (action == KeyAction.cnd_create) {

			String target = jTf_Target.getText().trim();
			String name = jTf_Name.getText().trim();
			File tfile;

			// chưa chọn thư mục để lưu file jSql
			if (target.trim().equals("")) {
				JOptionPane.showMessageDialog(this,
						"Please choose directory to save file Database !", "Warning",
						JOptionPane.WARNING_MESSAGE);
				return;
			}

			// thư mục người dùng nhập không tồn tại
			tfile = new File(target);
			if (!tfile.exists()) {
				int ch = JOptionPane.showConfirmDialog(this,
						"Directory path does not exist. "
								+ "\nThe path will be created."
								+ "\nAgree or Not ?", "Warning",
						JOptionPane.YES_NO_OPTION);
				if (ch == 1)
					return;
				if (ch == 0) {
					boolean chh = new File(target).mkdirs();

					if (!chh) {
						JOptionPane
								.showMessageDialog(
										this,
										"Can not create directory. Please check the path !",
										"Warning", JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
			}

			// chua nhap ten
			if (name.equals("")) {
				JOptionPane.showMessageDialog(this,
						"Please enter Database's name !", "Warning",
						JOptionPane.WARNING_MESSAGE);
				return;
			}

			// database bi trung ten
			tfile = new File(target + "\\" + name + ".db");
			if (tfile.exists()) {
				JOptionPane.showMessageDialog(this,
						"Database already exists. Please enter a different name !", "Warning",
						JOptionPane.WARNING_MESSAGE);
				return;
			}

			// tao database
			Database.createNewDatabase(target + "\\" + name + ".db");

			JOptionPane.showMessageDialog(this,
					"Database created successfully ^_^", "Info",
					JOptionPane.INFORMATION_MESSAGE);

			Panel_Server.PrintLog("Server: Created a new Database: " + target
					+ "\\" + name + ".db");
			this.dispose();

			Main.SetDataBase(Database
					.loadFromFile(target + "\\" + name + ".db"));
			Frame_Main.Refresh();
		}

		if (action == KeyAction.cnd_cancel) {
			this.dispose();
		}
	}
}
