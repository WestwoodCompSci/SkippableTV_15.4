package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class SeasonList {
	private int x;
	private int y;
	
	private boolean starting;
	
	private int goalY;
	
	private ArrayList<Season> seasons;
	
	private int selectedIndex;
	
	public SeasonList(int x, int y){
		this.x = x;
		this.y = y - 30;
		
		goalY = y;
		
		starting = true;
		
		selectedIndex = 1;
		
		seasons = new ArrayList<Season>();
	}
	
	public void update(int x, int y){
		if(starting){
			if(y > goalY){
				y = goalY;
				starting = false;
			}
			else{
				y += 5;
				seasons.add(new Season(x,y));
			}
		}
		
		this.x = x;
		this.y = y;
	};
	
	public void draw(Graphics2D g){
		g.setClip(x + 70, y, 400, 535);
		
		Font temp = SPanel.font.deriveFont(24f);
		
		g.setFont(temp);
		
		g.setColor(new Color(85,96,105));
		g.fillRoundRect(x + 195, y + 20, 150, 50, 10, 10);
		
		int length = (int)g.getFontMetrics().getStringBounds("Season " + selectedIndex, g).getWidth(); 
		
		g.setColor(new Color(240,240,240));
		g.drawString("Season " + selectedIndex, x + length/2 + 195 - 7, y + 53);
		
		g.setFont(temp);
		
		if(selectedIndex == 1){
			g.setColor(new Color(85,96,105));
			g.fillRoundRect(x + 365, y + 20, 150, 50 ,10 ,10);
			
			int tempIndex = selectedIndex + 1;
			
			length = (int)g.getFontMetrics().getStringBounds("Season " + tempIndex, g).getWidth();
			
			g.setColor(new Color(240,240,240));
			
			g.drawString("Season " + tempIndex, x + length/2 + 365 - 7, y + 53);
		}
		else if (selectedIndex == seasons.size() - 1){
			g.setColor(new Color(85,96,105));
			g.fillRoundRect(x + 25, y + 20, 200, 50 ,10 ,10);
			
			int tempIndex = selectedIndex - 1;
			
			length = (int)g.getFontMetrics().getStringBounds("Season " + tempIndex, g).getWidth();
			
			g.setColor(new Color(240,240,240));
			
			g.drawString("Season " + tempIndex, x + length/2 - 25 - 7, y + 53);
		}
		else{
			g.setColor(new Color(85,96,105));
			g.fillRoundRect(x + 25, y + 20, 150, 50 ,10 ,10);
			
			int tempIndex = selectedIndex - 1;
			
			length = (int)g.getFontMetrics().getStringBounds("Season " + tempIndex, g).getWidth();
			
			g.setColor(new Color(240,240,240));
			
			g.drawString("Season " + tempIndex, x + length/2 - 25 - 7, y + 53);
			
			g.fillRoundRect(x + 365, y + 20, 150, 50 ,10 ,10);
			
			tempIndex = selectedIndex + 1;
			
			length = (int)g.getFontMetrics().getStringBounds("Season " + tempIndex, g).getWidth();
			
			g.setColor(new Color(240,240,240));
			
			g.drawString("Season " + tempIndex, x + length/2 + 365 - 7, y + 53);
		}
		
		g.setClip(0, 0, SPanel.width, SPanel.height);
	};
}
