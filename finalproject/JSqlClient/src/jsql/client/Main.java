package jsql.client;

import javax.swing.*;
import java.awt.*;


public class Main{
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException: " + ex.getMessage());
        } catch (InstantiationException ex) {
        	 System.out.println("InstantiationException: " + ex.getMessage());
        } catch (IllegalAccessException ex) {
        	 System.out.println("IllegalAccessException: " + ex.getMessage());
        } catch (UnsupportedLookAndFeelException ex) {
        	 System.out.println("UnsupportedLookAndFeelException: " + ex.getMessage());
        }
		
		EventQueue.invokeLater(new Runnable() {

            public void run() {
                new jsql.client.ClientFrame().setVisible(true);
            }
        });
	}



}
