package GUI;

import java.awt.Color;
import java.awt.Graphics2D;

public class MovieButton {
	private int x;
	private int y;
	
	private static int r = 150;
	
	public MovieButton(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void update(){
		
	}
	
	public void draw(Graphics2D g){
		g.setColor(new Color(0,0,0,50));
		g.fillOval(x - 3, y - 3, 156, 156);
		
		g.setColor(new Color(85,96,105));
		g.fillOval(x, y, r, r);
		
		g.setColor(new Color(121,140,154));
		g.fillOval(x+15, y+15, 120, 120);
	}
}
