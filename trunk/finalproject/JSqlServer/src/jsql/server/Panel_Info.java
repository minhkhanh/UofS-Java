package jsql.server;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import java.awt.SystemColor;

/**
 * @author DWater
 * 
 */
@SuppressWarnings("serial")
public class Panel_Info extends JPanel {

	JPanel jP_Help;
	JPanel jP_Info;
	private JScrollPane jSP_Help;

	public Panel_Info() {
		this.InitFrame();
		this.Init();
	}

	public void InitFrame() {

		this.setSize(784, 439);
		this.setLayout(null);
		this.setName("Information");

		jP_Info = new JPanel();
		jP_Info.setBounds(10, 11, 376, 417);
		jP_Info.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createEtchedBorder(), "Thông tin"));
		add(jP_Info);
		jP_Info.setLayout(null);

		JLabel lblTrn_1 = new JLabel("     - 0912237: Trần Duy Khương");
		lblTrn_1.setBounds(27, 295, 339, 25);
		jP_Info.add(lblTrn_1);
		lblTrn_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblTrn_1.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblNguyn = new JLabel("     - 0812333: Nguyễn Hiếu Nghĩa");
		lblNguyn.setBounds(27, 272, 339, 25);
		jP_Info.add(lblNguyn);
		lblNguyn.setHorizontalAlignment(SwingConstants.LEFT);
		lblNguyn.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblTrn = new JLabel("     - 0812223: Trần Minh Khánh");
		lblTrn.setBounds(27, 247, 339, 25);
		jP_Info.add(lblTrn);
		lblTrn.setHorizontalAlignment(SwingConstants.LEFT);
		lblTrn.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblNhmSinhVin = new JLabel("Nhóm sinh viên:");
		lblNhmSinhVin.setBounds(27, 224, 339, 25);
		jP_Info.add(lblNhmSinhVin);
		lblNhmSinhVin.setHorizontalAlignment(SwingConstants.LEFT);
		lblNhmSinhVin.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblGingVin = new JLabel("Giảng viên: Nguyễn Văn Khiết");
		lblGingVin.setBounds(27, 188, 339, 25);
		jP_Info.add(lblGingVin);
		lblGingVin.setHorizontalAlignment(SwingConstants.LEFT);
		lblGingVin.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblnCui = new JLabel("ĐỒ ÁN CUỐI KỲ JSQL");
		lblnCui.setBounds(71, 149, 295, 25);
		jP_Info.add(lblnCui);
		lblnCui.setHorizontalAlignment(SwingConstants.CENTER);
		lblnCui.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblLpTrnhng = new JLabel("LẬP TRÌNH ỨNG DỤNG JAVA (TH2009/3)");
		lblLpTrnhng.setBounds(71, 119, 295, 25);
		jP_Info.add(lblLpTrnhng);
		lblLpTrnhng.setHorizontalAlignment(SwingConstants.CENTER);
		lblLpTrnhng.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lbliHcKhoa = new JLabel("ĐẠI HỌC KHOA HỌC TỰ NHIÊN");
		lbliHcKhoa.setBounds(71, 58, 295, 25);
		jP_Info.add(lbliHcKhoa);
		lbliHcKhoa.setHorizontalAlignment(SwingConstants.CENTER);
		lbliHcKhoa.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(71, 34, 295, 25);
		jP_Info.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setText("ĐẠI HỌC QUỐC GIA TP. HCM\r\n");

		JLabel lblKhoaCngNgh = new JLabel("KHOA CÔNG NGHỆ THÔNG TIN");
		lblKhoaCngNgh.setBounds(71, 83, 295, 25);
		jP_Info.add(lblKhoaCngNgh);
		lblKhoaCngNgh.setHorizontalAlignment(SwingConstants.CENTER);
		lblKhoaCngNgh.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel label = new JLabel("");
		label.setBounds(10, 38, 70, 70);
		jP_Info.add(label);
		label.setIcon(new ImageIcon(Panel_Info.class
				.getResource("/img/logoUS.png")));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));

		jP_Help = new JPanel();
		jP_Help.setLayout(null);
		jP_Help.setBounds(398, 11, 376, 417);
		jP_Help.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createEtchedBorder(), "Hướng dẫn"));
		add(jP_Help);

		JTextPane txtpnHngDnS = new JTextPane();
		txtpnHngDnS.setBackground(SystemColor.controlShadow);
		txtpnHngDnS.setBackground(new Color(240, 240, 240));
		txtpnHngDnS
				.setText("\t\tHƯỚNG DẪN SỬ DỤNG\r\n\r\n1. Chạy module Server\r\n     1.1. Nhấn Browse để tạo chọn file jSql (database)\r\n     1.2. Nhấn Listen để mở server. Có thể tùy chỉnh thông số Port. \r\n     1.3. Chờ client kết nối đến.\r\n2. Chạy module Client.\r\n     2.1. Chỉnh Port trùng với server.\r\n     2.2. Nhấn Conect để kết nối đến Server.\r\n     2.3. Nhập các câu truy vấn rồi nhấn Execute để yêu cầu Server thực hiện.\r\n     2.4  Chờ Server xử lý và hiển thị kết quả.\r\n     2.5 Lập lại bươc 2.3\r\n3. Quản lý Database & Table\r\n     - Nhấn Create New DB: Hiển thị form tạo database mới.\r\n     - Chọn tab Manager: Quản lý database và table.\r\n     - Chọn tab Add Table: Thêm bảng mới cho database\r\n     - Chọn tab Add Data: Thêm giá trị vào bảng.\r\n     - Chọn tab Information: Thông tin & hướng dẫn sử dụng.");
		txtpnHngDnS.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnHngDnS.setEditable(false);
		txtpnHngDnS.setBounds(10, 35, 356, 371);

		jSP_Help = new javax.swing.JScrollPane();
		jSP_Help.setBounds(10, 35, 356, 371);
		jSP_Help.setViewportView(txtpnHngDnS);
		jP_Help.add(jSP_Help);
	}

	public void Init() {
	}
}
