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

	String host;
	int port;
	private BufferedReader stdIn = null;
	private BufferedReader socIn = null;
	private PrintStream socOut = null;
	Socket echoSocket = null;

	public Client(String host, int port) {
		this.port = port;
		this.host = host;
	}

	/**
	 * Méthode appelée dans le MainClient, qui permet de gérer les thread d'envoi et de réception de messages du client
	 **/
	public void run() {
		//InterfaceController interfaceController = new InterfaceController();

		try {
			// creation socket ==> connexion
			this.echoSocket = new Socket(this.host, this.port);
			this.socIn = new BufferedReader(
					new InputStreamReader(echoSocket.getInputStream()));    
			this.socOut= new PrintStream(echoSocket.getOutputStream());
			this.stdIn = new BufferedReader(new InputStreamReader(System.in));

			choisirUsername();

			Thread envoyer = new Thread (new Runnable(){
				String line;
				@Override
				public void run() {
					while (true) {
						try {
							line=stdIn.readLine();
						} catch (IOException e) {
							e.printStackTrace();
						}
						if (line.equals(".")) break;
						socOut.println(line);
					}
				}
			});

			Thread recevoir = new Thread (new Runnable(){
				@Override
				public void run() {
					while (true) {
						try {
							System.out.println("echo: " + socIn.readLine());
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			});
			recevoir.start();
			envoyer.start();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Méthode appelée dans la méthode run de la classe Client, qui permet de choisir un nom d'utilisateur
	 **/
	public void choisirUsername() {
		System.out.println("choisissez un nom d'utilisateur: ");
		try {
			String username = this.stdIn.readLine();
			this.socOut.println(username);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


