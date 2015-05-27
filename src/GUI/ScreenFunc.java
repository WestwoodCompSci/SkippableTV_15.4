package GUI;
import java.awt.Color;   
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ScreenFunc {
	JFrame frame;
	JButton button;
	JButton button2;
	JCheckBox checkBox;
	JLabel label;
	JLabel label2;
	JLabel label3;
	JLabel label4;
	JButton button3;
	JButton button4;
	JButton button5;
	JButton button6;
	JButton button7;
	JButton button8;
	JButton button9;
	JButton button10;
	JButton button11;
	ImageIcon image;
	
	public ScreenFunc() {
    frame = new JFrame("Login");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    image = new ImageIcon(getClass().getResource("skippable.jpg"));
    Image img = image.getImage();
    BufferedImage bi = new BufferedImage(700,350, BufferedImage.TYPE_INT_ARGB);
    Graphics g = bi.createGraphics();
    g.drawImage(img,-80,0,700,350, null);
    ImageIcon newIcon = new ImageIcon(bi);
    label = new JLabel("SKIPPABLE.tv");
    label2 = new JLabel("Username: ");
    label3 = new JLabel("Password: ");
    label4 = new JLabel(newIcon);
    button = new JButton("Login |");
    button2 = new JButton(" Sign Up");
    button3 = new JButton("Forgot Password?");
    button4 = new JButton("                                                             ");
    button5 = new JButton("                                                             ");
    button6 = new JButton("                                                             ");
    button7 = new JButton("           ");
    button8 = new JButton("                                                             ");
    button9 = new JButton("                                                             ");
    button10 = new JButton("                                                             ");
    button11 = new JButton("                                                             ");
    JTextField textfield = new JTextField(15);
    JTextField textfield2 = new JTextField(15);
    JPanel panel = new JPanel();
    Font font = label.getFont();
    label.setFont(new Font("Century Gothic", Font.BOLD, 80));
    label.setFont(font.deriveFont(Font.BOLD, 80f));
    label.setForeground(Color.WHITE);
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setLayout(new GridBagLayout());
    GridBagConstraints left = new GridBagConstraints();
    left.anchor = GridBagConstraints.EAST;
    left.gridwidth = GridBagConstraints.CENTER;
    GridBagConstraints right = new GridBagConstraints();
    right.weightx = 2.0;
    right.anchor = GridBagConstraints.WEST;
    right.gridwidth = GridBagConstraints.REMAINDER;
    panel.add(button7,left);
    button7.setSize(500,500);
    button7.setBackground(new Color(80,86,90));
    button7.setBorder(null);
    panel.add(label4,right);
    panel.add(button5,left);
    button5.setBackground(new Color(80,86,90));
    button5.setBorder(null);
    panel.add(label,right);
    panel.add(button6);
    button6.setSize(500,500);
    button6.setBackground(new Color(80,86,90));
    button6.setBorder(null);
    label.setAlignmentX(Component.CENTER_ALIGNMENT);
    panel.add(button10,left);
    button10.setBackground(new Color(80,86,90));
    button10.setForeground(Color.WHITE);
    button10.setBorder(null);
    panel.add(button,left);
    button.setBackground(new Color(80,86,90));
    button.setForeground(Color.WHITE);
    button.setBorder(null);
    panel.add(button2,right);
    button2.setBackground(new Color(80,86,90));
    button2.setForeground(Color.WHITE);
    button2.setBorder(null);
    panel.add(button8,left);
    button8.setSize(5000,5000);
    button8.setBackground(new Color(80,86,90));
    button8.setBorder(null);
    panel.add(label2,left);
    label2.setForeground(Color.WHITE);
    panel.add(textfield,right);
    panel.add(button9,left);
    button9.setBackground(new Color(80,86,90));
    button9.setBorder(null);
    panel.add(label3,left);
    label3.setForeground(Color.WHITE);
    panel.add(textfield2,right);
    panel.add(button4,left);
    button4.setSize(500,500);
    button4.setBackground(new Color(80,86,90));
    button4.setBorder(null);
    panel.add(button11, left);
    button11.setBackground(new Color(80,86,90));
    button11.setBorder(null);
    panel.add(button3,right);
    button3.setBackground(new Color(80,86,90));
    button3.setForeground(Color.WHITE);
    button3.setBorder(null);
    button.setAlignmentX(Component.RIGHT_ALIGNMENT);
    panel.setBackground(new Color(80,86,90));
    button.setFont(new Font("CenturyGothic", Font.PLAIN, 20));
    button2.setFont(new Font("CenturyGothic", Font.PLAIN, 20));
    label2.setFont(new Font("CenturyGothic", Font.PLAIN, 20));
    label3.setFont(new Font("CenturyGothic", Font.PLAIN, 20));
    button3.setFont(new Font("CenturyGothic", Font.BOLD, 15));
    button2.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame anotherFrame = new JFrame();
			anotherFrame.pack();
			anotherFrame.setVisible(true);
			anotherFrame.setResizable(false);
			anotherFrame.setSize(899, 700);
			anotherFrame.setLocation(270,80);
			JPanel anotherPanel = new JPanel();
			anotherFrame.add(anotherPanel);
			anotherPanel.setBackground(new Color(80,86,90));
			ImageIcon image2 = new ImageIcon(getClass().getResource("skippable.jpg"));
		    Image img2 = image2.getImage();
		    BufferedImage bi2 = new BufferedImage(700,350, BufferedImage.TYPE_INT_ARGB);
		    Graphics g2 = bi2.createGraphics();
		    g2.drawImage(img,-80,0,700,350, null);
		    ImageIcon newIcon2 = new ImageIcon(bi2);
		    JLabel labelA = new JLabel(newIcon2);
		    anotherPanel.add(labelA);
		    JLabel labelB = new JLabel("SKIPPABLE.tv");
		    JLabel labelC = new JLabel("Username: ");
		    JLabel labelD = new JLabel("Password: ");
		    JLabel labelE = new JLabel("Email: ");
		    JLabel labelF = new JLabel("Confirm Password: ");
		    JButton buttonA = new JButton("Login |");
		    JButton buttonB = new JButton(" Sign Up");
		    JButton buttonC = new JButton("Forgot Password?");
		    JButton buttonD = new JButton("                                                             ");
		    JButton buttonE = new JButton("                                                             ");
		    JButton buttonF = new JButton("                                                             ");
		    JButton buttonG = new JButton("                               ");
		    JButton buttonH = new JButton("                                                             ");
		    JButton buttonI = new JButton("                                                             ");
		    JButton buttonJ = new JButton("                                                             ");
		    JButton buttonK = new JButton("                                                             ");
		    JButton buttonL = new JButton("                                                             ");
		    JButton buttonM = new JButton("                                                             ");
		    JTextField textfieldA = new JTextField(15);
		    JTextField textfieldB = new JTextField(15);
			JTextField textfieldC = new JTextField(15);
			JTextField textfieldD = new JTextField(15);
			Font fontA = labelB.getFont();
		    labelB.setFont(new Font("CenturyGothic", Font.BOLD, 80));
		    labelB.setFont(fontA.deriveFont(Font.BOLD, 80f));
		    labelB.setForeground(Color.WHITE);
		    anotherPanel.setLayout(new GridBagLayout());
		    GridBagConstraints leftA = new GridBagConstraints();
		    leftA.anchor = GridBagConstraints.EAST;
		    leftA.gridwidth = GridBagConstraints.CENTER;
		    GridBagConstraints rightA = new GridBagConstraints();
		    rightA.weightx = 2.0;
		    rightA.anchor = GridBagConstraints.WEST;
		    rightA.gridwidth = GridBagConstraints.REMAINDER;
		    anotherPanel.add(buttonG,left);
		    buttonG.setSize(500,500);
		    buttonG.setBackground(new Color(80,86,90));
		    buttonG.setBorder(null);
		    anotherPanel.add(labelA,right);
		    anotherPanel.add(buttonE,left);
		    buttonE.setBackground(new Color(80,86,90));
		    buttonE.setBorder(null);
		    anotherPanel.add(labelB,right);
		    anotherPanel.add(buttonF);
		    buttonF.setSize(500,500);
		    buttonF.setBackground(new Color(80,86,90));
		    buttonF.setBorder(null);	
		    anotherPanel.add(buttonL, left);
		    buttonL.setBackground(new Color(80,86,90));
		    buttonL.setBorder(null);
		    anotherPanel.add(buttonA,left);
		    buttonA.setBackground(new Color(80,86,90));
		    buttonA.setForeground(Color.WHITE);
		    buttonA.setBorder(null);
		    anotherPanel.add(buttonB,right);
		    buttonB.setBackground(new Color(80,86,90));
		    buttonB.setForeground(Color.WHITE);
		    buttonB.setBorder(null);
		    anotherPanel.add(buttonH,left);
		    buttonH.setSize(5000,5000);
		    buttonH.setBackground(new Color(80,86,90));
		    buttonH.setBorder(null);
		    anotherPanel.add(labelE,left);
		    labelE.setForeground(Color.WHITE);
		    anotherPanel.add(textfieldA,right);
		    anotherPanel.add(buttonI,left);
		    buttonI.setBackground(new Color(80,86,90));
		    buttonI.setBorder(null);
		    anotherPanel.add(labelC,left);
		    labelC.setForeground(Color.WHITE);
		    anotherPanel.add(textfieldB,right);
		    anotherPanel.add(buttonJ, left);
		    buttonJ.setBackground(new Color(80,86,90));
		    buttonJ.setBorder(null);
		    anotherPanel.add(labelD,left);
		    labelD.setForeground(Color.WHITE);
		    anotherPanel.add(textfieldC,right);
		    anotherPanel.add(buttonK, left);
		    buttonK.setBackground(new Color(80,86,90));
		    buttonK.setBorder(null);
		    anotherPanel.add(labelF, left);
		    labelF.setForeground(Color.WHITE);
		    anotherPanel.add(textfieldD, right);
		    buttonA.setFont(new Font("CenturyGothic", Font.PLAIN, 25));
		    buttonB.setFont(new Font("CenturyGothic", Font.PLAIN, 25));
		    labelC.setFont(new Font("CenturyGothic", Font.PLAIN, 20));
		    labelD.setFont(new Font("CenturyGothic", Font.PLAIN, 20));
		    buttonC.setFont(new Font("CenturyGothic", Font.PLAIN, 20));
		    labelE.setFont(new Font("CenturyGothic", Font.PLAIN, 20));
		    labelF.setFont(new Font("CenturyGothic", Font.PLAIN, 20));
		    
		    }
    });
    
    frame.getContentPane().add(panel);
    frame.pack();
    frame.setSize(899,700);
    //frame.setSize(200, 60);
    frame.setResizable(false);
    frame.setLocation(270, 80);
    frame.setVisible(true);
 }

	// method to add an action listener to the gui's
	// private button
	public void setButtonActionListener(ActionListener al) {
		button.addActionListener(al);
	}
	
	

	// gui method to check if box is checked
	public boolean isBoxChecked() {
		return checkBox.isSelected();
	}

	// method to set label
	public void setText(String text) {
		label.setText(text);
	}

}