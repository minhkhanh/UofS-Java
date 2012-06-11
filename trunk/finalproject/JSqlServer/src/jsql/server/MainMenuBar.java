package jsql.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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
public class MainMenuBar extends JMenuItem implements ActionListener {

	public enum actionC {
		exit, help, info
	};

	public JMenuBar myMenuBar = new JMenuBar();

	public MainMenuBar() {
		JMenu jMn_File = new JMenu("File");
		JMenu jMn_Option = new JMenu("Option");
		JMenu jMn_Help = new JMenu("Help");

		// add to item to menu help
		JMenuItem jMI_Help = new JMenuItem("Hướng dẫn");
		JMenuItem jMI_Info = new JMenuItem("Thông tin");

		// add to file menu
		JMenuItem m_newProject = new JMenuItem("New Database");
		JMenuItem i_open = new JMenuItem("Open");
		JMenuItem i_save = new JMenuItem("Save");
		JMenuItem i_save_as = new JMenuItem("Save As");
		JMenuItem i_save_all = new JMenuItem("Save All");
		JMenuItem i_exit = new JMenuItem("Exit");

		m_newProject.setActionCommand("newDat");
		m_newProject.addActionListener(this);
		i_open.setActionCommand("open");
		i_open.addActionListener(this);
		i_save.setActionCommand("Save");
		i_save.addActionListener(this);
		i_save_as.setActionCommand("SaveAs");
		i_save_as.addActionListener(this);
		i_save_all.setActionCommand("SaveAll");
		i_save_all.addActionListener(this);
		i_exit.setActionCommand("exit");
		i_exit.addActionListener(this);

		jMn_File.add(m_newProject);
		jMn_File.addSeparator();
		jMn_File.add(i_open);
		jMn_File.addSeparator();
		jMn_File.add(i_save);
		jMn_File.add(i_save_as);
		jMn_File.add(i_save_all);
		jMn_File.addSeparator();
		jMn_File.add(i_exit);

		jMn_Help.add(jMI_Help);
		jMn_Help.add(jMI_Info);

		myMenuBar.add(jMn_File);
		myMenuBar.add(jMn_Option);
		myMenuBar.add(jMn_Help);
		myMenuBar.setBorder(new BevelBorder(BevelBorder.RAISED));
	}

	public void actionPerformed(ActionEvent e) {

		actionC key = actionC.valueOf(e.getActionCommand());

		switch (key) {
		case help:
			JOptionPane.showInternalMessageDialog(
					myMenuBar, "Không thể tạo thư mục. Xin xem lại đường dẫn !",
					"Warning", JOptionPane.WARNING_MESSAGE);
			break;
		case info:
			break;
		case exit:
			System.exit(0);
			break;
		}

		/*
		 * int ch = JOptionPane.showConfirmDialog(this,
		 * "Đường dẫn thư mục không tồn tại. " + "\nĐường dẫn sẽ được tạo." +
		 * "\nĐồng ý hay Không ?", "Warning", JOptionPane.YES_NO_OPTION); if (ch
		 * == 1) return; if (ch == 0) { boolean chh = new File(target).mkdirs();
		 * 
		 * if (!chh) { JOptionPane .showMessageDialog( this,
		 * "Không thể tạo thư mục. Xin xem lại đường dẫn !", "Warning",
		 * JOptionPane.WARNING_MESSAGE); return; } }
		 */
	}
}
