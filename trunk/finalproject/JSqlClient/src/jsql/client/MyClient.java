package jsql.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

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
			client = new Socket(this.IP, this.port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

}
