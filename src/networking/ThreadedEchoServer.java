package networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadedEchoServer {

	static final int PORT = 80;
	static boolean stop = false;
	

	public static void start() {
		ServerSocket serverSocket = null;
		Socket socket = null;

		try {
			serverSocket = new ServerSocket(PORT);
			serverSocket.setSoTimeout(0);
		} catch (IOException e) {
			e.printStackTrace();

		}

		System.out.println("Waiting for clients on port " + serverSocket.getLocalPort() + "...");
		while (!stop) {
			try {

				socket = serverSocket.accept();
				EchoThread et = new EchoThread(socket);
				et.start();
			} catch (IOException e) {
				System.out.println("I/O error: " + e);
			}
			// new thread for a client

			
		}
		try {
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}