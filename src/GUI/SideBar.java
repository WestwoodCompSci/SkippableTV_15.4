package GUI;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class SideBar extends JPanel{
	public SideBar(Dimension d){
		this.setBackground(new Color(255,255,255,100));
		
		this.setPreferredSize(d);
		
		this.setLocation(0, 0);
	}
}
