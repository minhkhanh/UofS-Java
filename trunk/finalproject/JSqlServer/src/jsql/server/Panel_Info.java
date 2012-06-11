package jsql.server;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.Label;
import javax.swing.ImageIcon;
import javax.swing.JSplitPane;

/**
 * @author DWater
 * 
 */
@SuppressWarnings("serial")
public class Panel_Info extends JPanel {

	private JLabel lblNewLabel;

	public Panel_Info() {
		setBackground(new Color(102, 255, 153));
		this.InitFrame();
		this.Init();
	}

	public void InitFrame() {

		this.setSize(784, 439);
		this.setLayout(null);
		this.setName("Information");
		

		lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(66, 11, 313, 25);
		lblNewLabel
				.setText("ĐẠI HỌC QUỐC GIA TP. HCM\r\n");
		add(lblNewLabel);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Panel_Info.class.getResource("/img/logoUS.png")));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(10, 11, 100, 100);
		add(label);
		
		JLabel lbliHcKhoa = new JLabel("ĐẠI HỌC KHOA HỌC TỰ NHIÊN");
		lbliHcKhoa.setHorizontalAlignment(SwingConstants.CENTER);
		lbliHcKhoa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbliHcKhoa.setBounds(66, 35, 313, 25);
		add(lbliHcKhoa);
		
		JLabel lblKhoaCngNgh = new JLabel("KHOA CÔNG NGHỆ THÔNG TIN");
		lblKhoaCngNgh.setHorizontalAlignment(SwingConstants.CENTER);
		lblKhoaCngNgh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblKhoaCngNgh.setBounds(66, 60, 313, 25);
		add(lblKhoaCngNgh);
		
		JLabel lblLpTrnhng = new JLabel("LẬP TRÌNH ỨNG DỤNG JAVA (TH2009/3)");
		lblLpTrnhng.setHorizontalAlignment(SwingConstants.CENTER);
		lblLpTrnhng.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLpTrnhng.setBounds(66, 96, 313, 25);
		add(lblLpTrnhng);
		
		JLabel lblnCui = new JLabel("ĐỒ ÁN CUỐI KỲ JSQL");
		lblnCui.setHorizontalAlignment(SwingConstants.CENTER);
		lblnCui.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblnCui.setBounds(66, 126, 313, 25);
		add(lblnCui);
		
		JLabel lblGingVin = new JLabel("Giảng viên: Nguyễn Văn Khiết\r\n");
		lblGingVin.setHorizontalAlignment(SwingConstants.LEFT);
		lblGingVin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGingVin.setBounds(40, 165, 339, 25);
		add(lblGingVin);
		
		JLabel lblNhmSinhVin = new JLabel("Nhóm sinh viên:");
		lblNhmSinhVin.setHorizontalAlignment(SwingConstants.LEFT);
		lblNhmSinhVin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNhmSinhVin.setBounds(40, 201, 339, 25);
		add(lblNhmSinhVin);
		
		JLabel lblTrn = new JLabel("     - 0812223: Trần Minh Khánh");
		lblTrn.setHorizontalAlignment(SwingConstants.LEFT);
		lblTrn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTrn.setBounds(40, 224, 339, 25);
		add(lblTrn);
		
		JLabel lblNguyn = new JLabel("     - 0812333: Nguyễn Hiếu Nghĩa");
		lblNguyn.setHorizontalAlignment(SwingConstants.LEFT);
		lblNguyn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNguyn.setBounds(40, 249, 339, 25);
		add(lblNguyn);
		
		JLabel lblTrn_1 = new JLabel("     - 0912237: Trần Duy Khương");
		lblTrn_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblTrn_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTrn_1.setBounds(40, 272, 339, 25);
		add(lblTrn_1);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(50, -70, 369, 400);
		add(textPane);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 374, 385, 54);
		add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(389, 374, 385, 54);
		add(panel_1);
	}

	public void Init() {
	}
}
