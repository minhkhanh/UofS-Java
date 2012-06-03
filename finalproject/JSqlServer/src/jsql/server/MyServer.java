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

	public MyServer(int port) {
		_Port = port;
	}

	public void SetPort(int port) {
		_Port = port;
	}

	@Override
	public void run() {

		try {
			_ServerSocket = new ServerSocket(_Port);
			Frame_Main.PrintLog("Khởi chạy máy chủ thành công");
			Frame_Main.PrintLog("Server is running in port: "
					+ Integer.toString(_ServerSocket.getLocalPort()));
			
			while (true) {
				new ThreadSocket(_ServerSocket.accept()).start();
			}
		} catch (IOException ex) {
			Logger.getLogger(MyServer.class.getName()).log(Level.SEVERE, null,
					ex);
		}
	}

	public void stop() {
		try {
			_ServerSocket.close();
			Frame_Main.PrintLog("Server is stopped");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}