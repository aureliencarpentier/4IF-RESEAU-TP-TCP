package stream.server;

import java.io.*;
import java.net.*;

/**
 * Classe qui étend Thread et qui permet associe un utilisateur
 * @author binome 1-8
 *
 */
public class ClientThread extends Thread {
	
	private Server server;
	private Socket clientSocket;
	private BufferedReader socIn;
	private PrintStream socOut;

	ClientThread(Socket s, Server server) {
		this.clientSocket = s;
		this.server = server;
	}

	/**
	 * Permet de mettre en route le thread
	 */
	public void run() {
		try {
			socIn = new BufferedReader(
					new InputStreamReader(clientSocket.getInputStream()));    
			socOut = new PrintStream(clientSocket.getOutputStream());
			
			String username = socIn.readLine();
			System.out.println(username);
			server.setUsername(this, username);
			sendMessage("Vous etes bien connecté");
			for(String msg : server.getListMessages()) {
				System.out.println(msg);
				sendMessage(msg);
			}
			String line;
			while ((line = socIn.readLine()) != null) {
				server.sendMessageToOtherClient(this, line);
			}
		} catch (Exception e) {
			System.err.println("Error in Server:" + e); 
		}
	}

	/***
	 * Permet d'envoyer un message à l'utilisateur
	 * @param msg Le message a envoyé
	 */
	public void sendMessage(String msg) {
		this.socOut.println(msg);
	}
}


