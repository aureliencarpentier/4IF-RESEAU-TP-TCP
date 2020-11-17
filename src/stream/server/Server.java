package stream.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server {
	
	private ServerSocket listenSocket;
	private ArrayList<ClientThread> listClients = new ArrayList<ClientThread>();
	private Map<ClientThread, String> mapUsernames = new HashMap<>();
	private List<String> listMessages = new ArrayList<>();
	private int indexUsername;
	private int port;
	
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
		LocalDateTime time = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");
		String formattedDate = time.format(format);
		
		String toSend = formattedDate + " > " + mapUsernames.get(sender) + " : " + msg;
		this.listMessages.add(toSend);
		for(ClientThread client : listClients) {
			if(client != sender) {
				client.sendMessage(toSend);
			}
		}
	}
	
	public void sendHistoryMessage(ClientThread client) {
		for(String msg : this.listMessages) {
			client.sendMessage(msg);
		}
	}
	
	public List<String> getListMessages() {
		return listMessages;
	}
}
