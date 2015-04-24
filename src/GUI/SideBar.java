package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class SideBar extends JPanel{
	
	
	
	public SideBar(){
		this.setPreferredSize(new Dimension(SPanel.width, SPanel.height));
		
	}
	
	public void update(){
		
	}
	
	public void draw(Graphics2D g){
		GradientPaint blacktoblack = new GradientPaint(260,0,new Color(0,0,0,100),267, 0,new Color(0,0,0,0));
		g.setPaint(blacktoblack);
		g.fillRect(260, 0, 7, 585);
		
		g.setColor(new Color(214,214,214));
		g.fillRect(0, 0, 260, 585);
		
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, 260, 50);
		
		g.setColor(Color.RED);
		g.fillRect(0, 485, 260, 100);
	}
}
