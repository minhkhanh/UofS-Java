package jsql.server;

import java.awt.EventQueue;

public class Main {

	static Frame_Main _FrameMain;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Frame_Main().setVisible(true);
            }
        });
	}

}
