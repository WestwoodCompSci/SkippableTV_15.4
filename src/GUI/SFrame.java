package GUI;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

public class SFrame extends JFrame{	
	private Graphics2D g;
	
	public SFrame(){
		//Initial Values
		
		this.setTitle("Skippable TV");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Adding Components
		
		this.setContentPane(new SPanel());
		
		//Finishing Up
		
		this.pack();
		
		this.setSize(1080, 720);
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - this.getWidth())/ 2);
	    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
	    
	    this.setLocation(x, y);
	    
		this.setResizable(false);
		this.setVisible(true);
	}
}
