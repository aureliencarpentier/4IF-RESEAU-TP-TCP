package stream.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Server {
	
	ServerSocket listenSocket;
	ArrayList<ClientThread> listClients = new ArrayList<ClientThread>();
	Map<ClientThread, String> mapUsernames = new HashMap<>();
	int indexUsername;
	int port;
	
	public Server(int port) {
		this.port = port;
		this.indexUsername = 0;
	}
	
	public void run() {
		try {
			listenSocket = new ServerSocket(this.port);
			System.out.println("Server ready...");
			while(true) {
				Socket clientSocket = listenSocket.accept();
				System.out.println("Connexion from:" + clientSocket.getInetAddress());
				broadcast("A new client is connected");
				
				ClientThread ct = new ClientThread(clientSocket, this);
				ct.start();
				listClients.add(ct);
				mapUsernames.put(ct, "Client " + indexUsername);
				indexUsername++;
			}
		} catch (IOException e) {
			e.printStackTrace();	
		}
	}
	
	/**
	 * Sends a message to all clients connected to the server
	 * @param msg
	 */
	public void broadcast(String msg) {
		for(ClientThread client : listClients) {
			client.sendMessage(msg);
		}
	}
	
	public void sendMessageToOtherClient(ClientThread sender, String msg) {
		for(ClientThread client : listClients) {
			if(client != sender) {
				client.sendMessage(mapUsernames.get(sender) + " : " + msg);
			}
		}
	}
}
