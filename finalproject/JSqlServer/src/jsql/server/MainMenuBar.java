package jsql.server;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.border.BevelBorder;

/**
 * 
 * @author Ka
 */
public class MainMenuBar implements ActionListener {

	public enum n_key {
		newDat, open, Save, SaveAs, SaveAll, exit
	};

	public JMenuBar myMenuBar = new JMenuBar();

	public MainMenuBar() {
		JMenu jMn_File = new JMenu("File");
		JMenu jMn_Option = new JMenu("Option");
		JMenu m_View = new JMenu("View");
		JMenu m_Run = new JMenu("Run");
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
		myMenuBar.add(m_View);
		myMenuBar.add(m_Run);
		myMenuBar.add(jMn_Help);
		myMenuBar.setBorder(new BevelBorder(BevelBorder.RAISED));
	}

	public void actionPerformed(ActionEvent e) {
		String temp = e.getActionCommand();

		n_key key = n_key.valueOf(temp);

		switch (key) {
		case newDat:
			// CreateDatabaseForm df = CreateDatabaseForm.getInstant();
			// df.setVisible(true);
			break;
		case open:
			break;
		case Save:
			break;
		case SaveAs:
			break;
		case SaveAll:
			break;
		case exit:
			System.exit(0);
			break;
		}
	}

}
