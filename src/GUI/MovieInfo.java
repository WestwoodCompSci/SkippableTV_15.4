package GUI;

import java.awt.Color;
import java.awt.Graphics2D;

public class MovieInfo {
	private int x;
	private int y;
	private int startingX;
	private int startingY;
	
	private int startingAngle;
	private int endingAngle;
	
	private boolean starting1;
	private boolean starting2;
	
	private int whiteAlpha;
	
	private int r;
	
	public MovieInfo(int x, int y, int sX, int sY, int sA){
		this.x = x;
		this.y = y;
		
		this.startingX = sX;
		this.startingY = sY;
		
		this.startingAngle = sA;
		endingAngle = 0;
		
		r = MovieButton.r;
		
		starting1 = true;
		starting2 = false;
		
		whiteAlpha = 255;
	}
	
	public void update(){
		if(starting1){
			int angleLeft = 360 - startingAngle  - endingAngle;
			if(angleLeft <= 0){
				starting1 = false;
				starting2 = true;
			}
			else{
				endingAngle += 20;
			}
		}
		else if(starting2){
			if(whiteAlpha <= 5){
				whiteAlpha = 0;
			}
			else{
				whiteAlpha -= 25;
			}
			if(r > 1600){
				starting2 = false;
			}
			else{
				r += 80;
				startingX -= 40;
				startingY -= 40;
			}
		}
	}
	
	public void draw(Graphics2D g){		
		if(starting1){
			g.setColor(new Color(101,120,134));
			g.fillArc(startingX - 3, startingY - 3, r + 6, r + 6, 90, -startingAngle);
			g.fillArc(startingX - 3, startingY - 3, r + 6, r + 6, 90 - startingAngle + 5, -endingAngle);
		}
		else if(starting2){
			g.setColor(new Color(101,120,134));
			g.fillOval(startingX - 3, startingY - 3, r + 6, r + 6);
			
			g.setColor(new Color(240,240,240,whiteAlpha));
			g.fillOval(startingX - 3, startingY - 3, r + 6, r + 6);
			
		}
		else{
			g.setColor(new Color(101,120,134));
			g.fillRect(x, y, 780, 535);
		}
	}
}
