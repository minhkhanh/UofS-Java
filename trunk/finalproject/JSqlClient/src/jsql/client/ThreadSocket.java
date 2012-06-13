package jsql.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import jsql.data.Request;

public class ThreadSocket extends Thread{
	private Socket socket;
	private Request request;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	public ThreadSocket(Socket socket){
		this.socket = socket;
		start();
	}
	
	public void run(){
		try {
			Thread.sleep(100);
			
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			
			while(true){
				
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
