package networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadedEchoServer {

    static final int PORT = 80;

    public static void main(String args[]) {
        ServerSocket serverSocket = null;
        Socket socket = null;

        try {
            serverSocket = new ServerSocket(PORT);
            serverSocket.setSoTimeout(0);
        } catch (IOException e) {
            e.printStackTrace();

        }
        
        System.out.println("Waiting for clients on port " + serverSocket.getLocalPort() + "...");
        while (true) {
            try {
            	  
            	  socket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
            // new thread for a client
            
            new EchoThread(socket).start();
        }
    }
}