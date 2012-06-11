package jsql.server;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author DWater
 * 
 */
@SuppressWarnings("serial")
public class Panel_Info extends JPanel {

	private JLabel lblNewLabel;

	public Panel_Info() {
		this.InitFrame();
		this.Init();
	}

	public void InitFrame() {

		this.setSize(784, 439);
		this.setLayout(null);
		this.setName("Information");

		lblNewLabel = new JLabel("New label");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(141, 104, 409, 137);
		lblNewLabel.setText("ĐẠI HỌC QUỐC GIA TP. HCM"
				+ "\n ĐẠI HỌC KHOA HỌC TỰ NHIÊN"
				+ "\n KHOA CÔNG NGHỆ THÔNG TIN" + "\n LẬP TRÌNH JAVA"
				+ "\n ĐỒ ÁN" + "\n Giảng viên: Nguyễn Văn Khiết");
		add(lblNewLabel);
	}

	public void Init() {
	}
}
