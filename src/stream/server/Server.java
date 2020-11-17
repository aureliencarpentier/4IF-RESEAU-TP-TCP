package stream.server;

import java.net.ServerSocket;
import java.util.ArrayList;

public class Server {
	ServerSocket listenSocket;
	ArrayList<ClientThread> listClients = new ArrayList<ClientThread>();
	int port;
	
	public Server(int port) {
		this.port = port;
	}
	
	public void run() {
		System.out.println("Server ready..."); 
	}

}
