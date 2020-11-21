package domain;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Formatter;
import java.util.Scanner;

public class Server {
	private Socket socket;
	private Scanner socketInput;
	private Formatter socketOutput;
	private ServerSocket serversocket;
	
	public Server(int portNumber, String name) {
		try {
			serversocket = new ServerSocket(portNumber);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(true) {
			try {
                System.out.println("Fileserver waiting...");
                socket = serversocket.accept();
                handleClient();
            } catch (IOException ex) {
                System.out.println("Problemen : " + ex.getMessage());
            } 
		}
	}
	
	public void handleClient() {
		try {
			socketInput = new Scanner(socket.getInputStream());
			socketOutput = new Formatter(socket.getOutputStream());   

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
