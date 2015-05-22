package GUI;

import java.awt.Graphics2D;

public class Episode {
	private int x;
	private int y;
	
	private boolean starting;
	
	private boolean skip;
	
	private int rating;
	
	public Episode(int x, int y, boolean skip, int rating){
		this.x = x;
		this.y = y;
		
		starting = false;
		
		this.skip = skip;
		
		this.rating = rating;
	}
	
	public void update(){};
	
	public void draw(Graphics2D g){};
}
