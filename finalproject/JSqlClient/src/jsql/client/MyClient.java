package jsql.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class MyClient implements Runnable{
	
	private String IP;
	private int port;
	private Socket client;
	
	public MyClient(String ip, int port){
		this.IP = ip;
		this.port = port;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			client = new Socket(IP, port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Can not connect to Server!", "Error", 1);
		}
	}
	
	public void stop(){
		try {
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isConnected(){
		return client.isConnected();
	}

}
