package GUI;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class SideBar extends JPanel{
	public SideBar(){
		this.setPreferredSize(new Dimension(SFrame.width/4, SFrame.height));
		this.setMaximumSize(new Dimension(SFrame.width/4, SFrame.height));
		this.setMinimumSize(new Dimension(SFrame.width/4, SFrame.height));
		
		this.setBackground(new Color(237,237,237));
	}
}
