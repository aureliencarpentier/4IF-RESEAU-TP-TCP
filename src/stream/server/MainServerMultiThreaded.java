/***
 * EchoServer
 * Example of a TCP server
 * Date: 10/01/04
 * Authors:
 */

package stream.server;

/**
 * Classe permettant de lancer le serveur
 * @author Binome 1-8
 *
 */
public class MainServerMultiThreaded  {

	/**
	 * La méthode main
	 * @param args
	 */
	public static void main(String args[]){ 
		Server server;

		if (args.length != 1) {
			System.out.println("Usage: java MainServerMultiThreaded <port>");
			System.exit(1);
		}
		server = new Server(Integer.parseInt(args[0]));
		server.run();
	}
}


