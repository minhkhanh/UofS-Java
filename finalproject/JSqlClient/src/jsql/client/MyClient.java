package jsql.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

import jsql.data.Request;
import jsql.data.Result;
import jsql.jclient.ISocketConnection;

public class MyClient extends Thread {
	
	private String IP;
	private int port;
	private Socket client;
	private ISocketConnection handle;
	private int iCount = 0;
	
	public MyClient(String ip, int port, ISocketConnection handle){
		this.IP = ip;
		this.port = port;
		this.handle = handle;
	}

	@Override
	public void run() {
		try {
			client = new Socket(IP, port);
			handle.hasSocketConnected();
            while (client.isConnected()) {                                
                ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
                try {
					Result result = (Result) ois.readObject();
					iCount++;
					handle.hasResponse(result, iCount);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
            }
		} catch (IOException e) {
			e.printStackTrace();
			//JOptionPane.showMessageDialog(null, "Không thể kết nối với server!", "Lỗi", 1);
		}
		handle.hasSocketDisconnect();
	}
	
	public void stopClient(){
		try {
			client.close();			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isConnected(){
		return client!=null && client.isConnected();
	}
	
	public int getRequestCount() {
		return iCount;
	}
	
	public boolean canSendRequest() {
		return getRequestCount()==0;
	}

	public void sendRequest(Vector<Request> requests) throws Exception {
		if (!isConnected()) throw new Exception("Hiện không kết nối với server!");
		if (!canSendRequest()) throw new Exception("Hiện gởi request!");
		iCount = 0;
		ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
		for (Request request : requests) {
			//iCount++;
			oos.writeObject(request);
		}
		oos.flush();
	}

}
