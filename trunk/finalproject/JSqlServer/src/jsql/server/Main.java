package jsql.server;

import java.awt.EventQueue;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import jsql.data.Database;

/**
 * @author DWater
 * 
 */
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
				new Frame_Main().setVisible(true);
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
