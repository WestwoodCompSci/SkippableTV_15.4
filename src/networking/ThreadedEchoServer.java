package networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadedEchoServer extends Thread {


	static final int PORT = 80;
	static boolean running = true;


	public void run() {
		ServerSocket serverSocket = null;
		Socket socket = null;

		try {
			serverSocket = new ServerSocket(PORT);
			serverSocket.setSoTimeout(100);
		} catch (IOException e) {
			e.printStackTrace();

		}

		System.out.println("Waiting for clients on port " + serverSocket.getLocalPort() + "...");
		while (running) {
			try {

				socket = serverSocket.accept();
				
				EchoThread et = new EchoThread(socket);
				et.start();
				Thread.sleep(100);
				System.out.println("Waiting for clients on port " + serverSocket.getLocalPort() + "...");
			} catch (Exception e) {

			}



		}
		try {
			serverSocket.close();
			if(socket != null) {
				socket.close();
			}
			System.out.println("Server closed.");
			running = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public void kill() {
		running = false;
	}
}