package networking;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class ClientExample
{
	final static String serverName = "";
	final static int port = 80;
	public static void main(String [] args)
	{
		

		try
		{
			//establishing connection to server
			System.out.println("Connecting to " + serverName
					+ " on port " + port);
			Socket client = new Socket(serverName, port);

			System.out.println("Just connected to "
					+ client.getRemoteSocketAddress());
			//setting up data streams
			OutputStream outToServer = client.getOutputStream();
			DataOutputStream out = new DataOutputStream(outToServer);
			InputStream inFromServer = client.getInputStream();
			DataInputStream in = new DataInputStream(inFromServer);
			//sending data to server
			Scanner s = new Scanner(System.in);
			String str = " ";
			while(!str.equals("")) {
				str = s.nextLine();
				out.writeUTF(str);
			}
			s.close();
			//receiving data from server
			System.out.println("Server says " + in.readUTF());
			//closing connection
			client.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}