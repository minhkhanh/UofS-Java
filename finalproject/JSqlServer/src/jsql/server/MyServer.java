package jsql.server;

import java.awt.Event;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import jsql.data.Database;
import jsql.data.Request;
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
	private int _Port;
	private Vector<MyThreadSocket> listClient;

	public MyServer(int port) {
		_Port = port;
	}

	public void SetPort(int port) {
		_Port = port;
	}

	@Override
	public void run() {

		try {
			listClient = new Vector<MyThreadSocket>();
			_ServerSocket = new ServerSocket(_Port);
			Panel_Server.PrintLog("Server: Start the server successfully ^_^");
			Panel_Server.PrintLog("Server: Server is running at port: "
					+ Integer.toString(_ServerSocket.getLocalPort()));

			while (!_ServerSocket.isClosed()) {
				Socket s = _ServerSocket.accept();
				MyThreadSocket t = new MyThreadSocket(s);
				listClient.add(t);
				t.start();
			}
		} catch (Exception ex) {
			//ex.printStackTrace();
		}
	}

	public void stop() {
		try {
			_ServerSocket.close();
			for (MyThreadSocket t : listClient) {
				t.stopConnect();
			}
			listClient.clear();
			Panel_Server.PrintLog("Server: Server has been stopped !!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}