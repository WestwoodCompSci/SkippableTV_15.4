package GUI;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class SFrame extends JFrame{	
	private Graphics2D g;
	private SPanel panel;
	
	public SFrame(){
		//Initial Values
		
		this.setTitle("SKIPPABLE.tv");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ImageIcon icon = new ImageIcon("images/skip.png");
		this.setIconImage(icon.getImage());
		
		//Adding Components
		
		panel = new SPanel();
		
		//this.setResizable(false);
		
		this.setContentPane(panel);
		
		this.setResizable(false);
		
		//Finishing Up
		
		this.pack();
		
		//this.setResizable(false);
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - this.getWidth())/ 2);
	    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
	    
	    this.setLocation(x, y);
	    
		this.setVisible(true);
	}
}
