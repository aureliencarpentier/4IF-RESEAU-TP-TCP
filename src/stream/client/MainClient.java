package stream.client;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import stream.server.ClientThread;

/***
 * EchoServer
 * Example of a TCP server
 * Date: 10/01/04
 * Authors:
 */


public class MainClient  {
	
	/**
	 * main method
	 * @param EchoServer port
	 * 
	 **/
	public static void main(String args[]){ 
		//ServerSocket listenSocket;
		if (args.length != 1) {
			System.out.println("Usage: java EchoServer <EchoServer port>");
			System.exit(1);
		}
		try {
			/*listenSocket = new ServerSocket(Integer.parseInt(args[0])); //port
			System.out.println("Server ready..."); 
			while (true) {
				Socket clientSocket = listenSocket.accept();
				System.out.println("Connexion from:" + clientSocket.getInetAddress());
				ClientThread ct = new ClientThread(clientSocket);
				ct.start();
				listClients.add(ct);
			}*/
		} catch (Exception e) {
			System.err.println("Error in EchoClient:" + e);
		}
	}
}