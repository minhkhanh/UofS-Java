package jsql.server;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Frame_AddTable extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPanel panel_1;
	
	private JButton btn_Ok;
	private JLabel lbl_NameTable;
	private JTextField tf_NameTable;
	
	public Frame_AddTable() {
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
		btn_Ok.setBounds(253, 11, 131, 30);
		btn_Ok.setActionCommand("ok");
		panel_1.add(btn_Ok);
		
		lbl_NameTable = new JLabel("Ten Bang:");
		lbl_NameTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_NameTable.setBounds(10, 73, 77, 30);
		panel_1.add(lbl_NameTable);
		
		tf_NameTable = new JTextField();
		tf_NameTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tf_NameTable.setBounds(97, 74, 173, 30);
		tf_NameTable.setColumns(10);
		panel_1.add(tf_NameTable);		
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
