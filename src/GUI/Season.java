package GUI;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class Season {
	private int x;
	private int y;
	
	private boolean starting;
	
	private ArrayList<Episode> episodes;
	
	public Season(int x, int y){
		this.x = x;
		this.y = y;
		
		starting = true;
		
		episodes = new ArrayList<Episode>();
	}
	
	public void update(){};
	
	public void draw(Graphics2D g){};
}
