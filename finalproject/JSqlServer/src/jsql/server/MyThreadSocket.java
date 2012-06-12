package jsql.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import jsql.data.Request;
import jsql.data.Result;
import jsql.parse.Parser;
import jsql.parse.Statement;

/**
 * @author DWater
 * 
 */
public class MyThreadSocket extends Thread {
	private Socket _Socket;
	private ObjectOutputStream _OOS;
	private ObjectInputStream _OIS;
	private Statement _Statement;
	private Request _Request;
	private Result _Result;

	private String _InetAddr;

	public MyThreadSocket(Socket socket) {
		this._Socket = socket;
		_InetAddr = _Socket.getInetAddress().getHostName();

		Panel_Server.PrintLog(_InetAddr + " đã kết nối đến Server ^_^");
	}

	@Override
	public void run() {
		try {
			Thread.sleep(100);

			_OOS = new ObjectOutputStream(_Socket.getOutputStream());
			_OIS = new ObjectInputStream(_Socket.getInputStream());

			while (true) {
				// get request from client
				_Request = (Request) _OIS.readObject();

				// thuc hien cau truy van
				_Statement = Parser.parseStatement(_Request.GetQuery());
				_Result = Main.GetDataBase().executeStatement(_Statement);

				// tra ket qua ve client
				_OOS.writeObject(_Result);
				_OOS.flush();

				// Hiển thị ra log ở server
				Panel_Server.PrintLog(_InetAddr + ": " + _Result.getMessage());
				// cập nhật lại thông tin ở server
				Frame_Main.Refresh();
			}
		} catch (Exception e) {
			// e.printStackTrace();
			Panel_Server.PrintLog(_InetAddr + " đã ngắt kết nối !");
			//
			// this.destroy();
		}
	}
}
