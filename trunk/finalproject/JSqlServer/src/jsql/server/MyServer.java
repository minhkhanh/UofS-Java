package jsql.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyServer implements Runnable {
	private ServerSocket _ServerSocket;
	private Socket _Socket;
	private ObjectOutputStream _OOS;
	private ObjectInputStream _OIS;
	private Thread _Thread;
	public int _Port = 3456;

	public MyServer() {
		_Thread = new Thread(this);

		try {
			_ServerSocket = new ServerSocket(_Port);
			_Socket = _ServerSocket.accept();

			//_OOS = new ObjectOutputStream(_Socket.getOutputStream());
			//_OIS = new ObjectInputStream(_Socket.getInputStream());
			
			RunThread();
		} catch (IOException ex) {
			Logger.getLogger(MyServer.class.getName()).log(Level.SEVERE, null,
					ex);
		}
	}

	public void RunThread() {
		_Thread.start();
	}

	@Override
	public void run() {
		while (true) {
			/*try {
				Thread.sleep(100);
				// tra ket qua cho client
				//_OOS.writeObject("sdfdsf");
				//_OOS.flush();
				
				///_OIS.readObject(); doc thong tin tu client
			} catch (InterruptedException ex) {
				Logger.getLogger(MyServer.class.getName()).log(Level.SEVERE,
						null, ex);
			} catch (IOException ex) {
				Logger.getLogger(MyServer.class.getName()).log(Level.SEVERE,
						null, ex);
			}*/
		}
	}
	
	@SuppressWarnings("deprecation")
	public void stop(){
		_Thread.stop();
	}
}