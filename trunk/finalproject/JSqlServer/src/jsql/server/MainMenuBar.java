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
		jMI_Exit.setActionCommand("exit");
		jMI_Exit.addActionListener(this);
		jMn_File.addSeparator();
		jMn_File.add(jMI_Exit);

		jMI_Help = new JMenuItem("Hướng dẫn");
		jMI_Help.setActionCommand("help");
		jMI_Help.addActionListener(this);
		jMn_Help.add(jMI_Help);

		jMI_Info = new JMenuItem("Thông tin");
		jMn_Help.add(jMI_Info);
		jMI_Info.setActionCommand("info");
		jMI_Info.addActionListener(this);

	}

	public void actionPerformed(ActionEvent e) {

		actionC key = actionC.valueOf(e.getActionCommand());

		switch (key) {

		case exit:
			System.exit(0);
			break;
		case help:

			JOptionPane.showMessageDialog(this, "SDFDSF", "Hướng dẫn",
					JOptionPane.INFORMATION_MESSAGE, null);
			break;
		case info:
			break;
		}
	}
}
