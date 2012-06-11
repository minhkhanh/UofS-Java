package jsql.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;

/**
 * @author DWater
 * 
 */
@SuppressWarnings("serial")
public class MainMenuBar extends JMenuBar implements ActionListener {

	public enum actionC {
		exit, help, info
	};

	JMenu jMn_File;
	JMenu jMn_Option;
	JMenu jMn_Help;
	JMenuItem jMI_Exit;
	JMenuItem jMI_Help;
	JMenuItem jMI_Info;

	public MainMenuBar() {

		this.setBorder(new BevelBorder(BevelBorder.RAISED));

		jMn_File = new JMenu("File");
		this.add(jMn_File);

		jMn_Option = new JMenu("Option");
		this.add(jMn_Option);

		jMn_Help = new JMenu("Help");
		this.add(jMn_Help);

		jMI_Exit = new JMenuItem("Thoát");
		jMI_Exit.setActionCommand(actionC.exit.toString());
		jMI_Exit.addActionListener(this);
		jMn_File.addSeparator();
		jMn_File.add(jMI_Exit);

		jMI_Help = new JMenuItem("Hướng dẫn");
		jMI_Help.setActionCommand(actionC.help.toString());
		jMI_Help.addActionListener(this);
		jMn_Help.add(jMI_Help);

		jMI_Info = new JMenuItem("Thông tin");
		jMn_Help.add(jMI_Info);
		jMI_Info.setActionCommand(actionC.info.toString());
		jMI_Info.addActionListener(this);

	}

	public void actionPerformed(ActionEvent e) {

		actionC action = actionC.valueOf(e.getActionCommand());

		switch (action) {

		case exit:
			System.exit(0);
			break;
		case help:
			JOptionPane
					.showMessageDialog(
							this,
							"\nHướng dẫn sử dụng!!"
									+ "\n\n  1. Chọn file database."
									+ "\n  2. Nhấn listen để chạy server."
									+ "\n  3. Qua phần Manager Database để tạo database, thêm bảng, "
									+ "\nthêm  dữ liệu, ....", "Hướng dẫn",
							JOptionPane.CLOSED_OPTION);
			break;
		case info:
			JOptionPane.showMessageDialog(this, "\nĐẠI HỌC KHOA HỌC TỰ NHIÊN"
					+ "\nKHOA CÔNG NGHỆ THÔNG TIN" + "\n\nĐỒ ÁN JAVA (JSQL)"
					+ "\n\nGiảng viên: Nguyễn Văn Khiết"
					+ "\n\nNhóm sinh viên thực hiên:"
					+ "\n    - 0812223 - Trần Minh Khánh"
					+ "\n    - 0812333 - Nguyễn Hiếu Nghĩa"
					+ "\n    - 0912237 - Trần Duy Khương",
					"Thông tin chương trình", JOptionPane.INFORMATION_MESSAGE,
					Helper.createImageIcon("/img/logoUS.png"));
			break;
		}
	}
}
