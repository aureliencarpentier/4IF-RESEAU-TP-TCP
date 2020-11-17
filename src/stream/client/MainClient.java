package stream.client;

public class MainClient {
	
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