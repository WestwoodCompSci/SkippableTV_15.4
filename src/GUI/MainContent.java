package GUI;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class MainContent extends JPanel{
	
	public MainContent(Dimension d){
		this.setBackground(Color.BLUE);
		
		this.setPreferredSize(d);
		
		this.setLocation(0, 0);
	}
}
