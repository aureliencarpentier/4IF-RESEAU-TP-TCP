package stream.client;

/***
 * EchoServer
 * Example of a TCP server
 * Date: 10/01/04
 * Authors:
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