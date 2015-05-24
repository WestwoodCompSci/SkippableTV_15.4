package GUI;

import java.awt.Graphics2D;

public class Hours {
	private int x;
	private int y;
	
	private int amount;
	private int max;
	
	public Hours(int x, int y, int max){
		this.x = x;
		this.y = y;
		
		this.max = max;
	}
	
	public void update(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics2D g){
		
	}
}
