/***
 * EchoServer
 * Example of a TCP server
 * Date: 10/01/04
 * Authors:
 */

package stream.server;

public class MainServerMultiThreaded  {

	/**
	 * main method
	 * @param String port
	 * 
	 **/
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


