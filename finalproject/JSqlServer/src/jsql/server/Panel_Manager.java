package jsql.server;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author DWater
 * 
 */
@SuppressWarnings("serial")
public class Panel_Manager extends JPanel implements ActionListener {

	public enum actionC {
		mn_browse, mn_addtable, mn_deletetable, mn_adddata
	}

	private JButton jBtn_DeleteTable;
	@SuppressWarnings("rawtypes")
	private static JComboBox jCbb_ListTable;

	public Panel_Manager() {
		this.InitFrame();
		this.Init();
	}

	@SuppressWarnings("rawtypes")
	public void InitFrame() {
		this.setSize(784, 439);
		this.setLayout(null);
		this.setName("Manager Table");

		jBtn_DeleteTable = new JButton("Delete Table");
		jBtn_DeleteTable.setToolTipText("");
		jBtn_DeleteTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jBtn_DeleteTable.setActionCommand("deletetable");
		jBtn_DeleteTable.addActionListener(this);
		jBtn_DeleteTable.setBounds(10, 11, 131, 30);
		this.add(jBtn_DeleteTable);

		jCbb_ListTable = new JComboBox();
		jCbb_ListTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jCbb_ListTable.setBounds(151, 11, 103, 30);
		this.add(jCbb_ListTable);
	}

	public void Init() {
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

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

	Vector<MiniTable> _ListMiniTable;

	public void ChoosenDatabase() {

		_ListMiniTable = new Vector<>();
		MiniTable tMiniTable;
		Point pos;
		int nTable = Main.GetDataBase().getTables().size();

		for (int i = 0; i < nTable; i++) {
			pos = new Point(i * 255, 100);

			tMiniTable = new MiniTable(Main.GetDataBase().getTable(i), pos);
			this.add(tMiniTable);
		}
	}
}
