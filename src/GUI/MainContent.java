package GUI;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class MainContent extends JPanel{
	public MainContent(){
		this.setPreferredSize(new Dimension(3*SFrame.width/4, SFrame.height));
		this.setMaximumSize(new Dimension(3*SFrame.width/4, SFrame.height));
		this.setMinimumSize(new Dimension(3*SFrame.width/4, SFrame.height));
		
		this.setBackground(new Color(219,114,114));
	}
}
