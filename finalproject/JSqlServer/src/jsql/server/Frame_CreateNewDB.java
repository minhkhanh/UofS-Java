package jsql.server;

import java.awt.Font;
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

@SuppressWarnings("serial")
public class Frame_CreateNewDB extends JFrame implements ActionListener {

	private JPanel jP_Main;

	JButton btnNewButton;
	JButton btnCancel;
	JButton btnBrowse;
	private JTextField jTF_Target;
	private JTextField jTf_Name;

	private JFileChooser jFChooser;

	/**
	 * Create the frame.
	 */
	public Frame_CreateNewDB() {
		setResizable(false);
		setBounds(100, 100, 426, 275);
		jP_Main = new JPanel();
		jP_Main.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(jP_Main);
		jP_Main.setLayout(null);

		JPanel jP_CreateNewDB = new JPanel();
		jP_CreateNewDB.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createEtchedBorder(),
				"Create New DataBase"));
		jP_CreateNewDB.setBounds(10, 11, 395, 216);
		jP_Main.add(jP_CreateNewDB);
		jP_CreateNewDB.setLayout(null);

		btnNewButton = new JButton("Create");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(20, 173, 89, 30);
		btnNewButton.setActionCommand("CNDB_Create");
		btnNewButton.addActionListener(this);
		jP_CreateNewDB.add(btnNewButton);

		btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancel.setBounds(142, 173, 89, 30);
		btnCancel.setActionCommand("CNDB_Cancel");
		btnCancel.addActionListener(this);
		jP_CreateNewDB.add(btnCancel);

		btnBrowse = new JButton("Browse");
		btnBrowse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBrowse.setBounds(290, 43, 89, 30);
		btnBrowse.setActionCommand("CNDB_Browse");
		btnBrowse.addActionListener(this);
		jP_CreateNewDB.add(btnBrowse);

		jTF_Target = new JTextField();
		jTF_Target.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jTF_Target.setBounds(20, 44, 260, 30);
		jP_CreateNewDB.add(jTF_Target);
		jTF_Target.setColumns(10);

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

		jFChooser = new JFileChooser();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if ("CNDB_Browse".equals(arg0.getActionCommand())) {

			// chon thu muc de luu database
			// int returnVal = jFChooser.showDialog(this, "Choose");
			// if (returnVal == JFileChooser.APPROVE_OPTION) {
			// _PathFileDataBase = jFChooser.getSelectedFile().getPath();
			// tf_AddrFileDB.setText(_PathFileDataBase);
			// Main.SetDataBase(Database.loadFromFile(_PathFileDataBase));
			// }
		}

		if ("CNDB_Create".equals(arg0.getActionCommand())) {

			String target = jTF_Target.getName().trim();
			String name = jTf_Name.getText().trim();

			// database bi trung ten
			File a = new File(target + "\\" + name + ".db");
			if (!a.exists()) {
				JOptionPane.showMessageDialog(this, "Database is exists!",
						"Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}

			// chua nhap ten
			if (name.equals("")) {
				JOptionPane.showMessageDialog(this,
						"Please Enter Your DataBase Name!", "Warning",
						JOptionPane.WARNING_MESSAGE);
				return;
			}

			// tao database
			Database.createNewDatabase(target + "\\" + name + ".db");

			JOptionPane.showMessageDialog(this,
					"Created New DataBase Sucessful!", "Info",
					JOptionPane.INFORMATION_MESSAGE);

			Frame_Main.PrintLog("Created New DataBase: "
					+ jTf_Name.getText().trim());
		}

		if ("CNDB_Cancel".equals(arg0.getActionCommand())) {
			this.dispose();
		}
	}
}
