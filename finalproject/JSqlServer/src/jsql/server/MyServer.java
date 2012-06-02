package jsql.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import jsql.data.Result;

@SuppressWarnings("unused")
public class MyServer implements Runnable {
	private ServerSocket _ServerSocket;
	private Socket _Socket;
	private ObjectOutputStream _OOS;
	private ObjectInputStream _OIS;
	private Thread _Thread;
	public int _Port;
	
	Result _Result;

	public MyServer(int port) {

		_Port = port;
		_Result = new Result("");

		try {
			_ServerSocket = new ServerSocket(_Port);
			System.out.println("Khởi chạy máy chủ thành công");

			// ?? 1 client hay nhieu client
			_Socket = _ServerSocket.accept();
			_Thread = new Thread(this);
			_Thread.start();
		} catch (IOException ex) {
			Logger.getLogger(MyServer.class.getName()).log(Level.SEVERE, null,
					ex);
		}
	}

	@Override
	public void run() {
		try {
			Thread.sleep(100);

			_OOS = new ObjectOutputStream(_Socket.getOutputStream());
			_OIS = new ObjectInputStream(_Socket.getInputStream());

			while (true) {
				// doc cau truy van cua client	
				_Result = (Result)_OIS.readObject();

				// thuc hien cau truy van
				
				
				// tra ket qua ve client
				_OOS.writeObject(_Result);
				_OOS.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		try {
			_ServerSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}