package jsql.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyServer implements Runnable {
	private ServerSocket _ServerSocket;
	private Thread _Thread;
	private int _Port; //default 3456

	public MyServer(int port) {
		
		_Port = port;

		try {
			_ServerSocket = new ServerSocket(_Port);
			_ServerSocket.accept();

			_Thread = new Thread(this);
			_Thread.start();
		} catch (IOException ex) {
			Logger.getLogger(MyServer.class.getName()).log(Level.SEVERE, null,
					ex);
		}
	}

	/*public void RunThread() {
		_Thread.start();
	}*/

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(100);
				
				///////////
			} catch (InterruptedException ex) {
				Logger.getLogger(MyServer.class.getName()).log(Level.SEVERE,
						null, ex);
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public void Stop(){
		_Thread.stop();		
	}
}
