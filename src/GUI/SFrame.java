package GUI;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.JFrame;

public class SFrame extends JFrame{
	public static int width = 1080;
	public static int height = 720;
	
	public SFrame(){
		//Initial Values
		
		this.setTitle("Skippable TV");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Adding Components
		
		Box mainBox = Box.createHorizontalBox();
		
		SideBar s = new SideBar();
		MainContent m = new MainContent();
		
		mainBox.add(s);
		mainBox.add(m);
		
		this.add(mainBox);
		
		//Finishing Up
		
		this.pack();
		
		this.setSize(width, height);
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - width)/ 2);
	    int y = (int) ((dimension.getHeight() - height) / 2);
	    
	    this.setLocation(x, y);
	    
		this.setResizable(false);
		this.setVisible(true);
	}
}
