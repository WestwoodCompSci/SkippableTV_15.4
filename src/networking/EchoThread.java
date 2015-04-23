package networking;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.net.Socket;

public class EchoThread extends Thread {
	protected Socket socket;

	public EchoThread(Socket clientSocket) {
		socket = clientSocket;
	}

	public void run() {
		DataInputStream in = null;

		DataOutputStream out = null;
		try {

			in = new DataInputStream(socket.getInputStream());

			out = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			return;
		}


		try
		{



			System.out.println("Just connected to "
					+ socket.getRemoteSocketAddress());
			
			
				String s = in.readUTF();
				System.out.println("Message from client: " + s);
			
			
			



			out.writeUTF("Thank you for connecting to "
					+ socket.getLocalSocketAddress() + "\nGoodbye!");
			System.out.println("Connection closed from " + socket.getRemoteSocketAddress());
			socket.close();
		}catch(Exception e)
		{
			System.out.println("Connection unexpectedly closed from " + socket.getRemoteSocketAddress());

		}


	}
}
