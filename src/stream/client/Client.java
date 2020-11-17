/***
 * EchoClient
 * Example of a TCP client 
 * Date: 10/01/04
 * Authors:
 */
package stream.client;

import java.io.*;
import java.net.*;


public class Client {



  /**
  *  main method
  *  accepts a connection, receives a message from client then sends an echo to the client
  **/
    public static void main(String[] args) throws IOException {
        
    	final BufferedReader stdIn ;
        final BufferedReader socIn ;
    	
        //InterfaceController interfaceController = new InterfaceController();
        Socket echoSocket = null;
        final PrintStream socOut ;

        if (args.length != 2) {
          System.out.println("Usage: java EchoClient <EchoServer host> <EchoServer port>");
          System.exit(1);
        }

        try {
      	    // creation socket ==> connexion
      	    echoSocket = new Socket(args[0],new Integer(args[1]).intValue());
	    socIn = new BufferedReader(
	    		          new InputStreamReader(echoSocket.getInputStream()));    
	    socOut= new PrintStream(echoSocket.getOutputStream());
	    stdIn = new BufferedReader(new InputStreamReader(System.in));
	    
	    
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
            System.err.println("Don't know about host:" + args[0]);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                               + "the connection to:"+ args[0]);
            System.exit(1);
        }
                             

      //echoSocket.close();
    }
}


