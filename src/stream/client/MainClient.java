package stream.client;

/***
 * EchoServer
 * Example of a TCP server
 * Date: 10/01/04
 * Authors:
 */

/**
 * Classe repr�sentant le Main du c�t� du client
 * @author Binome 1-8
 *	
 */
public class MainClient  {
	
	/**
	 * main method
	 * @param String port
	 * 
	 **/
	public static void main(String args[]){ 
		Client client;

		if (args.length != 2) {
			System.out.println("Usage: java MainClient <host> <port>");
			System.exit(1);
		}
		client = new Client(args[0],Integer.parseInt(args[1]));
		client.run();
	}
}