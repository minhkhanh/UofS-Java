package jsql.server;

import java.awt.Event;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import jsql.data.Database;
import jsql.data.Result;
import jsql.parse.Parser;
import jsql.parse.Statement;

/**
 * @author DWater
 * 
 */
@SuppressWarnings("unused")
public class MyServer implements Runnable {
	private ServerSocket _ServerSocket;
	private Socket _Socket;
	private ObjectOutputStream _OOS;
	private ObjectInputStream _OIS;
	private int _Port;
	private Result _Result;
	private Boolean _IsConnect;
	private Database _DataBase;
	private String _QueryOfClient;
	private Statement _Statement;

	public MyServer(int port) {
		_Port = port;
		_Result = new Result("");
		_IsConnect = false;
	}

	public void SetPort(int port) {
		_Port = port;
	}

	@Override
	public void run() {

		if (_IsConnect) {
			try {
				Thread.sleep(100);

				_OOS = new ObjectOutputStream(_Socket.getOutputStream());
				_OIS = new ObjectInputStream(_Socket.getInputStream());

				while (true) {
					// doc cau truy van cua client
					_QueryOfClient = (String) _OIS.readObject();

					// thuc hien cau truy van
					_Statement = Parser.parseStatement(_QueryOfClient);
					_Result = _DataBase.executeStatement(_Statement);

					// tra ket qua ve client
					_OOS.writeObject(_Result);
					_OOS.flush();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				_ServerSocket = new ServerSocket(_Port);
				System.out.println("Khởi chạy máy chủ thành công");

				// ?? 1 client hay nhieu client
				_Socket = _ServerSocket.accept();
				_IsConnect = true;
				Frame_Main.PrintLog(_Socket.getInetAddress()
						+ " connected to Server ^_^");
			} catch (IOException ex) {
				Logger.getLogger(MyServer.class.getName()).log(Level.SEVERE,
						null, ex);
			}
		}

	}

	public void stop() {
		try {
			_IsConnect = false;
			_ServerSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void SetDataBase(Database loadFromFile) {
		// TODO Auto-generated method stub
		_DataBase = loadFromFile;
	}
}