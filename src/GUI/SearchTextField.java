package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import javax.swing.JTextField;

public class SearchTextField extends JTextField{
	public SearchTextField(int x, int y){
		super();
		
		this.setLocation(x, y);
		
		this.setPreferredSize(new Dimension(200, 30));
	}
	
	public void paintComponents(Graphics2D g){
		super.paintComponents(g.create());
		
		g.setColor(Color.BLACK);
		g.fillRect(this.getX(), this.getY(), 100, 100);
	}
}
