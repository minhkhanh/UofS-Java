package jsql.server;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import jsql.data.Database;

/**
 * @author DWater
 * 
 */
@SuppressWarnings("unused")
public class Main {

	private static Database _DataBase;

	public static void main(String[] args) {

		try {
			for (UIManager.LookAndFeelInfo info : UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception ex) {
			java.util.logging.Logger.getLogger(Main.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}

		EventQueue.invokeLater(new Runnable() {

			public void run() {
				final Frame_Main frm = new Frame_Main();
				frm.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				frm.addWindowListener(new WindowAdapter() {					
					
					@Override
					public void windowClosing(WindowEvent arg0) {
						System.out.println("exiting");
						int result = JOptionPane.showConfirmDialog(frm, "Are you sure you want to exit?", "Quit", JOptionPane.YES_NO_OPTION);
						if (result == JOptionPane.YES_OPTION) {
							if (GetDataBase()!=null) GetDataBase().saveToFile();
							frm.dispose();
							System.exit(0);
						}						
					}
				});
				frm.setVisible(true);
			}
		});
	}

	public static Database GetDataBase() {
		return _DataBase;
	}

	public static void SetDataBase(Database database) {
		_DataBase = database;
	}
}
