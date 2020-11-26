/***
 * EchoClient
 * Example of a TCP client 
 * Date: 10/01/04
 * Authors:
 */
package stream.client;

import java.io.*;
import java.net.*;
/**
 * Classe représentant un client
 * @author Binome 1-8
 *	
 */
public class Client {

	private String host;
	private int port;
	private BufferedReader stdIn = null;
	private BufferedReader socIn = null;
	private PrintStream socOut = null;
	private Socket echoSocket = null;
	private String username;
	private boolean isConnected = false;
	
	private MessageHandler handler;
	
	public Client(String host, int port) {
		this.port = port;
		this.host = host;
	}

	/**
	 * Méthode appelée dans le MainClient, qui permet de gérer les thread d'envoi et de réception de messages du client
	 **/
	public void run() throws Exception {
		//InterfaceController interfaceController = new InterfaceController();

		
			// creation socket ==> connexion
			this.echoSocket = new Socket(this.host, this.port);
			this.socIn = new BufferedReader(
					new InputStreamReader(echoSocket.getInputStream()));    
			this.socOut= new PrintStream(echoSocket.getOutputStream());
			this.stdIn = new BufferedReader(new InputStreamReader(System.in));
			isConnected = true;
			choisirUsername();

			Thread envoyer = new Thread (new Runnable(){
				String line;
				@Override
				public void run() {
					while (isConnected) {
						try {
							line=stdIn.readLine();
						} catch (IOException e) {
							e.printStackTrace();
						}
						if (line.equals(".")) { 
							isConnected = false; 
							break;
						}
						socOut.println(line);
					}
					try {
						echoSocket.close();
					} catch (IOException e) {
						e.printStackTrace();

					}
				}
			});

			Thread recevoir = new Thread (new Runnable(){
				@Override
				public void run() {
					while (isConnected) {
						try {
							String msg = socIn.readLine();
							handler.handleMessage(msg);
							System.out.println("echo: " + msg);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			});
			recevoir.start();
			envoyer.start();
	}
	
	/**
	 * Met la variable isConnected à faux pour arreter de recevoir et d'envoyer
	 */
	public void stop() {
		isConnected = false;
	}
	
	/**
	 * Permet d'envoyer un message
	 * @param msg Le message à envoyer
	 */
	public void sendMessage(String msg) {
		socOut.println(msg);
	}
	
	public void setHandler(MessageHandler handler) {
		this.handler = handler;
	}

	/**
	 * Méthode appelée dans la méthode run de la classe Client, qui permet de choisir un nom d'utilisateur
	 **/
	public void choisirUsername() {
		this.socOut.println(this.username);
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
}


