package stream.server;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Classe serveur
 * @author binome 1-8
 *
 */
public class Server {
	
	private ServerSocket listenSocket;
	private ArrayList<ClientThread> listClients = new ArrayList<ClientThread>();
	private Map<ClientThread, String> mapUsernames = new HashMap<>();
	private List<String> listMessages = new ArrayList<>();
	private int port;
	private File history;
	
	/**
	 * Constructeur
	 * @param port Port sur lequel le serveur écoute
	 */
	public Server(int port) {
		this.port = port;
	}
	
	/**
	 * Permet de mettre en route le serveur
	 */
	public void run() {
		try {
			listenSocket = new ServerSocket(this.port);
			System.out.println("Server ready...");
			
			setUpHistory();
			
			while(true) {
				Socket clientSocket = listenSocket.accept();
				System.out.println("Connexion from:" + clientSocket.getInetAddress());
				broadcast("A new client is connected");
				
				ClientThread ct = new ClientThread(clientSocket, this);
				ct.start();
				listClients.add(ct);
			}
		} catch (IOException e) {
			e.printStackTrace();	
		}
	}
	
	/**
	 * Envoi un message à tous les utilisateurs
	 * @param msg
	 */
	public void broadcast(String msg) {
		for(ClientThread client : listClients) {
			client.sendMessage(msg);
		}
	}
	
	/**
	 * Renvoi aux utilisateurs le message qu'un utilisateur a envoyé
	 * @param sender L'emetteur du message
	 * @param msg Le message
	 */
	public void sendMessageToOtherClient(ClientThread sender, String msg) {
		LocalDateTime time = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");
		String formattedDate = time.format(format);
		
		String toSend = formattedDate + " > " + mapUsernames.get(sender) + " : " + msg;
		this.listMessages.add(toSend);
		writeToHistory(toSend);
		for(ClientThread client : listClients) {
			
				client.sendMessage(toSend);
			
		}
	}
	
	/**
	 * Envoi l'historique des messages à un client
	 * @param client Client qui va recevoir l'historique
	 */
	public void sendHistoryMessage(ClientThread client) {
		for(String msg : this.listMessages) {
			client.sendMessage(msg);
		}
	}
	
	/**
	 * Met en place l'historique
	 */
	public void setUpHistory() {
		history = new File("history.txt");
		try {
			if(history.createNewFile()) {
				System.out.println("history file created");
			} else {
				System.out.println("File already exists");
				Scanner scanner = new Scanner(history);
				while(scanner.hasNextLine()) {
					String line = scanner.nextLine();
					listMessages.add(line);
				}
				scanner.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			
		}
	}
	
	/**
	 * Ecrit un message dans l'historique
	 * @param str Message à écrire
	 */
	public void writeToHistory(String str) {
		FileWriter writer;
		try {
			writer = new FileWriter("history.txt", true);
			writer.write(str + "\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
			
		}
	}
	
	public List<String> getListMessages() {
		return listMessages;
	}
	
	public void setUsername(ClientThread client, String username) {
		mapUsernames.put(client, username);
	}
	
	
}
