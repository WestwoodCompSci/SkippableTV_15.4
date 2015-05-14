package GUI;

import java.awt.Color;
import java.awt.Graphics2D;

public class MovieInfo {
	private int x;
	private int y;
	private int startingX;
	private int startingY;
	
	private int startingAngle;
	
	public MovieInfo(int x, int y, int sX, int sY, int sA){
		this.x = x;
		this.y = y;
		
		this.startingX = sX;
		this.startingY = sY;
		
		this.startingAngle = sA;
	}
	
	public void update(){
		
	}
	
	public void draw(Graphics2D g){
		g.setColor(new Color(85,96,105));
		
	}
}
