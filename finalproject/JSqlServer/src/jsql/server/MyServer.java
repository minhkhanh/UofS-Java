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
	private Thread _Thread;
	private int _Port;
	private ObjectOutputStream _OOS;
	private ObjectInputStream _OIS;

	public MyServer(int port) {

		_Port = port;

		try {
			_ServerSocket = new ServerSocket(_Port);
			_Socket = _ServerSocket.accept();
			_OOS = new ObjectOutputStream(_Socket.getOutputStream());
			_Thread = new Thread(this);
			_Thread.start();
		} catch (IOException ex) {
			Logger.getLogger(MyServer.class.getName()).log(Level.SEVERE, null,
					ex);
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(100);

				_OOS.writeObject("III");
				_OOS.flush();
			} catch (InterruptedException | IOException ex) {
				Logger.getLogger(MyServer.class.getName()).log(Level.SEVERE,
						null, ex);
			}
		}
	}

	@SuppressWarnings("deprecation")
	public void Stop() {
		_Thread.stop();
	}
}
