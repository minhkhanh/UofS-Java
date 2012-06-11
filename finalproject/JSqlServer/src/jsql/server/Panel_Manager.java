package jsql.server;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import jsql.data.Database;

/**
 * @author DWater
 * 
 */
@SuppressWarnings("serial")
public class Panel_Manager extends JPanel implements ActionListener {

	public enum actionC {
		mn_browse, mn_addtable, mn_deletetable, mn_adddata
	};
	
	private JButton jBtn_AddTable;
	private JButton jBtn_DeleteTable;
	private JButton jBtn_AddData;
	private JButton jBtn_CreateNewDatabase;
	@SuppressWarnings("rawtypes")
	private static JComboBox jCbb_ListTable;

	private Frame_AddData _FrameAddData;
	private Frame_CreateNewDB _FrameCreateDB;

	public Panel_Manager() {

		this.setSize(784, 439);
		this.setLayout(null);
		this.setName("Manager Database");

		jBtn_AddTable = new JButton("Add Table");
		jBtn_AddTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jBtn_AddTable.setBounds(10, 75, 131, 30);
		jBtn_AddTable.setActionCommand("addtable");
		jBtn_AddTable.addActionListener(this);
		this.add(jBtn_AddTable);

		jBtn_DeleteTable = new JButton("Delete Table");
		jBtn_DeleteTable.setToolTipText("");
		jBtn_DeleteTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jBtn_DeleteTable.setActionCommand("deletetable");
		jBtn_DeleteTable.addActionListener(this);
		jBtn_DeleteTable.setBounds(10, 116, 131, 30);
		this.add(jBtn_DeleteTable);

		jBtn_AddData = new JButton("Add Data");
		jBtn_AddData.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jBtn_AddData.setActionCommand("adddata");
		jBtn_AddData.addActionListener(this);
		jBtn_AddData.setBounds(10, 157, 131, 30);
		this.add(jBtn_AddData);

		JLabel lblFileDatabase = new JLabel("File Database:");
		lblFileDatabase.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFileDatabase.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFileDatabase.setBounds(10, 11, 103, 29);
		this.add(lblFileDatabase);


		jCbb_ListTable = new JComboBox();
		jCbb_ListTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jCbb_ListTable.setBounds(151, 116, 103, 30);
		this.add(jCbb_ListTable);

		jBtn_CreateNewDatabase = new JButton("Create DataBase");
		jBtn_CreateNewDatabase.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jBtn_CreateNewDatabase.setActionCommand("createdb");
		jBtn_CreateNewDatabase.addActionListener(this);
		jBtn_CreateNewDatabase.setBounds(528, 75, 138, 30);
		this.add(jBtn_CreateNewDatabase);

		// INIT


		if (Main.GetDataBase() != null)
			Refresh();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if ("createdb".equals(arg0.getActionCommand())) {
			_FrameCreateDB = new Frame_CreateNewDB();
			_FrameCreateDB.setVisible(true);
		}


		if ("deletetable".equals(arg0.getActionCommand())) {
			if (this.CheckChooseDataBase()
					&& jCbb_ListTable.getModel().getSize() > 0) {

				int ch = JOptionPane.showConfirmDialog(this, "Xóa bảng \""
						+ jCbb_ListTable.getSelectedItem().toString() + "\"."
						+ " Bạn có chắc chắn ?", "Warning",
						JOptionPane.YES_NO_OPTION);
				if (ch == 1)
					return;

				// delete table
				Main.GetDataBase().DeleteTable(
						jCbb_ListTable.getSelectedIndex());

				JOptionPane.showMessageDialog(this,
						"Đã xóa bảng thành công ^_^", "Warning",
						JOptionPane.WARNING_MESSAGE);

				this.Refresh();
			}
		}

		if ("adddata".equals(arg0.getActionCommand())) {

			if (this.CheckChooseDataBase()) {
				_FrameAddData = new Frame_AddData();
				_FrameAddData.setVisible(true);
			}
		}
	}

	public Boolean CheckChooseDataBase() {
		if (Main.GetDataBase() == null) {
			JOptionPane.showMessageDialog(this,
					"Xin chọn file Database trước !", "Warning",
					JOptionPane.WARNING_MESSAGE);

			return false;
		}
		return true;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void Refresh() {
		jCbb_ListTable.setModel(new DefaultComboBoxModel(Helper
				.GetListTableName(Main.GetDataBase())));
	}
}
