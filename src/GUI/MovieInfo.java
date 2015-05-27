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
	private boolean starting3;
	
	private int whiteAlpha;
	private int alpha;
	
	private int r;
	
	private boolean close;
	
	private SeasonList s;
	
	private MovieDescription d;
	
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

		alpha = 255;
		
		close = false;
	}
	
	public SeasonList getSeasonList(){
		return s;
	}
	
	public MovieDescription getDescription(){
		return d;
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
				starting3 = true;
				s = new SeasonList(x + 240, y);
				d = new MovieDescription(x + 25, y + 180, 76, "This Emmy-winning original thriller series stars Golden Globe winner Kevin Spacey as ruthless, cunning Congressman Francis Underwood, who will stop at nothing to conquer the halls of power in Washington D.C. His secret weapon: his gorgeous, ambitious, and equally conniving wife Claire (Golden Globe winner Robin Wright).This Emmy-winning original thriller series stars Golden Globe winner Kevin Spacey as ruthless, cunning Congressman Francis Underwood, who will stop at nothing to conquer the halls of power in Washington D.C. His secret weapon: his gorgeous, ambitious, and equally conniving wife Claire (Golden Globe winner Robin Wright).This Emmy-winning original thriller series stars Golden Globe winner Kevin Spacey as ruthless, cunning Congressman Francis Underwood, who will stop at nothing to conquer the halls of power in Washington D.C. His secret weapon: his gorgeous, ambitious, and equally conniving wife Claire (Golden Globe winner Robin Wright).This Emmy-winning original thriller series stars Golden Globe winner Kevin Spacey as ruthless, cunning Congressman Francis Underwood, who will stop at nothing to conquer the halls of power in Washington D.C. His secret weapon: his gorgeous, ambitious, and equally conniving wife Claire (Golden Globe winner Robin Wright).This Emmy-winning original thriller series stars Golden Globe winner Kevin Spacey as ruthless, cunning Congressman Francis Underwood, who will stop at nothing to conquer the halls of power in Washington D.C. His secret weapon: his gorgeous, ambitious, and equally conniving wife Claire (Golden Globe winner Robin Wright).");
			}
			else{
				r += 80;
				startingX -= 40;
				startingY -= 40;
			}
		}
		else if(starting3){
			if(alpha <= 5){
				alpha = 0;
			}
			else{
				alpha -= 25; 
			}
		}
		
		if(close){
			if(y < -460){
				y = -485;
				close = false;
			}
			else{
				y -= 25;
			}
		}
		
		if(s != null){
			s.update(x + 240, y);
		}
		
		if(d != null){
			d.update(x + 25, y + 180);
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
			
			g.setColor(new Color(85,96,105));
			g.fillOval(x + 50, y + 20, 160,160);
			
			g.setColor(new Color(101,120,134, alpha));
			g.fillOval(x + 50, y + 20, 160,160);
			
			s.draw(g);
			d.draw(g);
		}
	}
	
	public void close(){
		close = true;
	}
}
