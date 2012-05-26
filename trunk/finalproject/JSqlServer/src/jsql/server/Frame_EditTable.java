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

@SuppressWarnings("serial")
public class Frame_EditTable extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPanel panel_1;
	
	private JButton btn_Ok;
	private JComboBox cbb_ListTable;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Frame_EditTable() {
		setBounds(300, 100, 700, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		btn_Ok = new JButton("OK");
		btn_Ok.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_Ok.setBounds(205, 171, 131, 30);
		btn_Ok.setActionCommand("ok");
		panel_1.add(btn_Ok);
		
		cbb_ListTable = new JComboBox();
		cbb_ListTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		// se load tu du lieu len
		cbb_ListTable.setModel(new DefaultComboBoxModel(new String[] {
				"HocSinh", "Lop", }));
		cbb_ListTable.setBounds(258, 11, 243, 30);
		panel_1.add(cbb_ListTable);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		switch (arg0.getActionCommand()) {
		case "ok":
			// tien hanh them bang dua tren thong tin da nhap
			break;
		}
	}
	
}
