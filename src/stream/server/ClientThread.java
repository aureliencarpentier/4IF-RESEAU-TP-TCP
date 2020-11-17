/***
 * ClientThread
 * Example of a TCP server
 * Date: 14/12/08
 * Authors:
 */

package stream.server;

import java.io.*;
import java.net.*;

public class ClientThread
extends Thread {
	
	private Server server;
	private Socket clientSocket;
	private BufferedReader socIn;
	private PrintStream socOut;

	ClientThread(Socket s, Server server) {
		this.clientSocket = s;
		this.server = server;
	}

	/**
	 * receives a request from client then sends an echo to the client
	 * @param clientSocket the client socket
	 **/
	public void run() {
		try {
			socIn = null;
			socIn = new BufferedReader(
					new InputStreamReader(clientSocket.getInputStream()));    
			socOut = new PrintStream(clientSocket.getOutputStream());
			while (true) {
				String line = socIn.readLine();
				server.sendMessageToOtherClient(this, line);
			}
		} catch (Exception e) {
			System.err.println("Error in Server:" + e); 
		}
	}

	public void sendMessage(String msg) {
		socOut.println(msg);
	}
}


