package jsql.server;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Frame_EditTable extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPanel panel_1;

	private JButton btn_Ok;
	@SuppressWarnings("rawtypes")
	private JComboBox cbb_ListTable;
	private JLabel lbl_ChoseTable;

	public Frame_EditTable() {
		this.InitFrame();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void InitFrame() {

		setResizable(false);
		setBounds(300, 100, 700, 525);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);

		btn_Ok = new JButton("OK");
		btn_Ok.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_Ok.setBounds(251, 11, 103, 30);
		btn_Ok.setActionCommand("ok");
		panel_1.add(btn_Ok);

		cbb_ListTable = new JComboBox();
		cbb_ListTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		// se load tu du lieu len
		cbb_ListTable.setModel(new DefaultComboBoxModel(new String[] {
				"HocSinh", "Lop", }));
		cbb_ListTable.setBounds(119, 11, 103, 30);
		panel_1.add(cbb_ListTable);

		lbl_ChoseTable = new JLabel("Chon Bang: ");
		lbl_ChoseTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_ChoseTable.setBounds(24, 11, 85, 30);
		panel_1.add(lbl_ChoseTable);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

//		switch (arg0.getActionCommand()) {
//		case "ok":
//			// tien hanh them bang dua tren thong tin da nhap
//			break;
//		}
	}

}
