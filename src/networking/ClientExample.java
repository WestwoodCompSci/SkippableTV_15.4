package networking;

import java.net.*;
import java.io.*;

public class ClientExample
{
	final static String serverName = "192.168.1.10";
	final static int port = 80;
	public static void main(String [] args)
	{
		

		try
		{
			//establishing connection to server
			System.out.println("Connecting to " + serverName
					+ " on port " + port);
			
			Socket client = new Socket();
			client.connect(new InetSocketAddress(serverName,port), 5000);
			
			System.out.println("Just connected to "
					+ client.getRemoteSocketAddress());
			//setting up data streams
			OutputStream outToServer = client.getOutputStream();
			DataOutputStream out = new DataOutputStream(outToServer);
			InputStream inFromServer = client.getInputStream();
			DataInputStream in = new DataInputStream(inFromServer);
			//sending data to server
			
				out.writeUTF("ayylmao");
			
			//receiving data from server
			System.out.println("Message from server: " + in.readUTF());
			//closing connection
			client.close();
		}catch(IOException e)
		{
			//e.printStackTrace();
			System.out.println("Error connecting to host server.");
		}
	}
}