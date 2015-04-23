package networking;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.event.ActionListener;

public class ServerWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	boolean start = true;
	private final Action action = new SwingAction();
	private static ServerWindow frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ServerWindow();
					frame.setLocationRelativeTo(null);
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

		JLabel lblSkippabletvServerApplication = new JLabel("SkippableTV_15.4 Server Application");
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

		Component verticalStrut = Box.createVerticalStrut(10);
		verticalBox.add(verticalStrut);

		Box horizontalBox_2 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_2);

		Component horizontalGlue_4 = Box.createHorizontalGlue();
		horizontalBox_2.add(horizontalGlue_4);

		JButton btnShowConsole = new JButton("Show Console");
		btnShowConsole.addActionListener(new ActionListener() {
			ConsoleWindow w = new ConsoleWindow();
			public void actionPerformed(ActionEvent arg0) {

				w.showGUI();
				frame.toFront();


			}
		});
		btnShowConsole.setFont(new Font("Tahoma", Font.PLAIN, 9));
		horizontalBox_2.add(btnShowConsole);

		Component horizontalGlue_5 = Box.createHorizontalGlue();
		horizontalBox_2.add(horizontalGlue_5);

		Component verticalGlue_2 = Box.createVerticalGlue();
		verticalBox.add(verticalGlue_2);
	}

	private class SwingAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		ThreadedEchoServer s;
		public SwingAction() {
			putValue(NAME, "Start");


		}
		public void actionPerformed(ActionEvent e) {
			if(start) {

				s = new ThreadedEchoServer();
				s.start();

				putValue(NAME, "Stop");
				start = false;





			}
			else {

				s.kill();
				putValue(NAME, "Start");
				start = true;

			}

		}
	}
}
