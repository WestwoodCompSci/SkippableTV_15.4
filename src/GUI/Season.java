package GUI;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class Season {
	private int x;
	private int y;
	
	private boolean starting;
	
	private ArrayList<Episode> episodes;
	
	private Progressbar p;
	
	public Season(int x, int y){
		this.x = x;
		this.y = y;
		
		starting = true;
		
		episodes = new ArrayList<Episode>();
		
		p = new Progressbar(x +30, y + 90, 80, 70);
	}
	
	public void update(int x, int y){
		this.x = x;
		this.y = y;
		
		p.update(this.x + 30, this.y + 90);
	};
	
	public void draw(Graphics2D g){
		p.draw(g);
	};
}
