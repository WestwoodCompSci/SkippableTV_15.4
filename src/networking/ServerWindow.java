package networking;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.AbstractAction;
import javax.swing.Action;

public class ServerWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	boolean start = true;
	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerWindow frame = new ServerWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ServerWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

		Box verticalBox = Box.createVerticalBox();
		contentPane.add(verticalBox);

		Component verticalGlue = Box.createVerticalGlue();
		verticalBox.add(verticalGlue);

		Box horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);

		Component horizontalGlue_2 = Box.createHorizontalGlue();
		horizontalBox.add(horizontalGlue_2);

		JLabel lblSkippabletvServerApplication = new JLabel("Skippable.TV Server Application");
		lblSkippabletvServerApplication.setFont(new Font("Tahoma", Font.BOLD, 18));
		horizontalBox.add(lblSkippabletvServerApplication);

		Component horizontalGlue_3 = Box.createHorizontalGlue();
		horizontalBox.add(horizontalGlue_3);

		Component verticalGlue_1 = Box.createVerticalGlue();
		verticalBox.add(verticalGlue_1);

		Box horizontalBox_1 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_1);

		Component horizontalGlue = Box.createHorizontalGlue();
		horizontalBox_1.add(horizontalGlue);

		JButton btnStart = new JButton("Start");
		btnStart.setAction(action);


		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 14));
		horizontalBox_1.add(btnStart);

		Component horizontalGlue_1 = Box.createHorizontalGlue();
		horizontalBox_1.add(horizontalGlue_1);

		Component verticalGlue_2 = Box.createVerticalGlue();
		verticalBox.add(verticalGlue_2);
	}

	private class SwingAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		static final int port = 80;
		ServerSocket serverSocket = null;
		SwingWorker<String, Void> worker;


		public SwingAction() {
			putValue(NAME, "Start");
			
			
		}
		public void actionPerformed(ActionEvent e) {
			if(start) {

				putValue(NAME, "Stop");
				start = false;
				
				try {
					serverSocket = new ServerSocket(port);
					serverSocket.setSoTimeout(0);
				} catch (IOException ex) {
					ex.printStackTrace();

				}
				System.out.println("Waiting for clients on port " + serverSocket.getLocalPort() + "...");
				worker = new SwingWorker<String, Void>() {


					public String doInBackground() {


						try {

							Socket socket = serverSocket.accept();
							EchoThread et = new EchoThread(socket);
							et.start();
						} catch (IOException e) {
							System.out.println("I/O error: " + e);
						}
						doInBackground();
						return "";


					}

				};


			}
			else {
				putValue(NAME, "Start");
				start = true;
				try {
					serverSocket.close();
					worker.cancel(true);
					System.out.println("Stopped server.");
				} catch (IOException e1) {
					System.out.println("I/O error: " + e);
				}
			}

		}
	}
}
