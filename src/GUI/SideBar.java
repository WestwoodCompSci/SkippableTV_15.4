package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class SideBar extends JPanel{
	int x;
	
	public boolean starting;
	
	public SideBar(){
		this.setPreferredSize(new Dimension(SPanel.width, SPanel.height));
		x = -260;
		this.setLocation(x,0);
		starting = true;
	}
	
	public void update(){
		if(starting){
			if(x >= 0){
				starting = false;
				this.setLocation(0,0);
			}
			else{
				x += 10;
				this.setLocation(x, 0);
			}
		}
	}
	
	public void draw(Graphics2D g){
		GradientPaint horizontalFade = new GradientPaint(this.getX() + 260,0,new Color(0,0,0,100),this.getX() + 267, 0,new Color(0,0,0,0));
		g.setPaint(horizontalFade);
		g.fillRect(this.getX() + 260, 0, 7, 585);
		
		g.setColor(new Color(214,214,214));
		g.fillRect(this.getX(), 0, 260, 585);
		
		GradientPaint verticalDownFade = new GradientPaint(0,50,new Color(0,0,0,100),0, 53,new Color(0,0,0,0));
		g.setPaint(verticalDownFade);
		g.fillRect(this.getX(), 50, 260, 3);
		
		g.setColor(Color.BLUE);
		g.fillRect(this.getX(), 0, 260, 50);
		
		GradientPaint verticalUpFade = new GradientPaint(0,480,new Color(0,0,0,0),0, 485,new Color(0,0,0,100));
		g.setPaint(verticalUpFade);
		g.fillRect(this.getX(),480,260,5);
		
		g.setColor(Color.RED);
		g.fillRect(this.getX(), 485, 260, 100);
	}
}
