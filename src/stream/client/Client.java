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
 * Classe repr�sentant un client
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
	 * M�thode appel�e dans le MainClient, qui permet de g�rer les thread d'envoi et de r�ception de messages du client
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
				// TODO Auto-generated method stub
				while (true) {
				try {
					line=stdIn.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
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
				// TODO Auto-generated method stub
				while (true) {
	        	try {
					System.out.println("echo: " + socIn.readLine());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			}
        });
        
        recevoir.start();
        envoyer.start();
        
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host:" + this.host);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                               + "the connection to:"+ this.host);
            System.exit(1);
        }
                             

      //echoSocket.close();
    }
	
	/**
	 * M�thode appel�e dans la m�thode run de la classe Client, qui permet de choisir un nom d'utilisateur
	 **/
	public void choisirUsername() {
		System.out.println("choisissez un nom d'utilisateur: ");
		try {
			this.stdIn.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
        
}


