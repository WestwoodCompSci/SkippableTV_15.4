package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Progressbar {
	private int x;
	private int y;
	
	private int percentSeason;
	private int percentComplete;
	
	private int targetPercentS;
	private int targetPercentC;
	
	private boolean starting1;
	private boolean starting2;
	private boolean starting3;
	
	private int alpha;
	
	public Progressbar(int x, int y, int s, int c){
		this.x = x;
		this.y = y;
		
		targetPercentS = s;
		targetPercentC = c;
		
		percentSeason = 0;
		percentComplete = 0;
		
		alpha = 0;
		
		starting1 = true;
		starting2 = false;
		starting3 = false;
	}
	
	public void update(int x, int y){
		if(starting1){
			if(percentSeason >= targetPercentS/4){
				starting2 = true;
			}
			if(percentSeason >= targetPercentS - (int)(targetPercentS/20.0)){
				percentSeason = targetPercentS;
				starting1 = false;
			}
			else{
				percentSeason += (int)(targetPercentS/20.0);
			}
		}
		if(starting2){
			if(percentComplete >= targetPercentC - (int)(targetPercentC/20.0)){
				percentComplete = targetPercentC;
				starting2 = false;
				starting3 = true;
			}
			else{
				percentComplete += (int)(targetPercentC/20.0);
			}
		}
		else if(starting3){
			if(alpha >= 250){
				alpha = 255;
			}
			else{
				alpha += 25;
			}
		}
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics2D g){
		g.setColor(new Color(240,240,240));
		g.fillRoundRect(x, y, 480, 16, 5, 5);
		
		g.setColor(new Color(80,80,80));
		g.fillRect(x + 5, y + 4, 470, 8);
		
		g.setColor(new Color(150,150,150));
		g.fillRect(x + 5, y + 4, (int)(470*(percentSeason/100.0)), 8);
		
		g.setColor(new Color(13,255,131));
		g.fillRect(x + 5, y + 4, (int)(470*(percentComplete/100.0)), 8);
		
		g.setColor(new Color(240,240,240, alpha));
		g.fillRect(x + 5 + (int)(470*(percentSeason/100.0)) - 2, y - 10, 4, 10);
		g.fillRect(x + 5 + (int)(470*(percentComplete/100.0)) - 2, y + 16, 4, 10);
		
		Font temp = SPanel.font.deriveFont(15f);
		
		g.setFont(temp);
		
		if(percentSeason < 50){
			g.drawString(percentSeason + "% of Season", x + 5 + (int)(470*(percentSeason/100.0)) - 4, y - 13);
		}
		else{
			int length = (int)g.getFontMetrics().getStringBounds(percentSeason + "% of Season", g).getWidth();
			g.drawString(percentSeason + "% of Season", x + 5 + (int)(470*(percentSeason/100.0)) + 2 - length, y - 13);
		}
		
		
		if(percentComplete < 50){
			g.drawString(percentComplete + "% of Season", x + 5 + (int)(470*(percentComplete/100.0)) - 4, y + 36);
		}
		else{
			int length = (int)g.getFontMetrics().getStringBounds(percentComplete + "% of Season", g).getWidth();
			g.drawString(percentComplete + "% of Season", x + 5 + (int)(470*(percentComplete/100.0)) + 2 - length, y + 36);
		}
	}
}
