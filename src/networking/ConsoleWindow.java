package networking;


import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class ConsoleWindow extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea textArea = new JTextArea(40, 50);
	private TextAreaOutputStream taOutputStream = new TextAreaOutputStream(
			textArea, "Console");
	private JFrame frame;
	private boolean built = false;
	public ConsoleWindow() {
		
		add(new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));

		System.setOut(new PrintStream(taOutputStream));
		
		
	}

	public void build() {
		frame = new JFrame("Console");
		frame.setResizable(false);
		
		
		frame.add(new ConsoleWindow());
		frame.pack();
		frame.setLocationRelativeTo(this.getParent());
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	
	public void showGUI() {
		if(!built) {
			build();
			built = true;
		}
		
		frame.setVisible(true);
		
		
	}


	public class TextAreaOutputStream extends OutputStream {

		private final JTextArea textArea;
		private final StringBuilder sb = new StringBuilder();
		private String title;

		public TextAreaOutputStream(final JTextArea textArea, String title) {
			this.textArea = textArea;
			this.title = title;
			this.textArea.setBackground(Color.BLACK);
			this.textArea.setForeground(Color.GREEN);
			this.textArea.setEditable(false);
			sb.append(title + "> ");
		}

		@Override
		public void flush() {
		}

		@Override
		public void close() {
		}

		@Override
		public void write(int b) throws IOException {

			if (b == '\r')
				return;

			if (b == '\n') {
				final String text = sb.toString() + "\n";
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						textArea.append(text);
					}
				});
				sb.setLength(0);
				sb.append(title + "> ");
				return;
			}

			sb.append((char) b);
		}
	}
}

