package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class SeasonList {
	private int x;
	private int y;
	
	private boolean starting;
	
	private int goalY;
	
	private ArrayList<Season> seasons;
	
	private int selectedIndex;
	
	private RightButton rB;
	
	private LeftButton lB;
	
	private int alpha;
	
	public SeasonList(int x, int y){
		this.x = x;
		this.y = y + 10;
		
		goalY = y;
		
		starting = true;
		
		selectedIndex = 1;
		
		seasons = new ArrayList<Season>();
		
		alpha = 255;
		
		rB = new RightButton(this.x + 470, this.y + 15, this);
		lB = new LeftButton(this.x + 40, this.y + 15, this);
	}
	
	public Season getSeason(){
		return seasons.get(selectedIndex - 1); 
	}
	
	public void checkHovered(MouseEvent e){
		if(rB != null){
			rB.checkHovered(e);
		}
		
		if(lB != null){
			lB.checkHovered(e);
		}
		
		if(seasons.size() > 0){
			Season temp = seasons.get(selectedIndex - 1);
			temp.getHours().checkHovered(e);
			for(int i = 0; i < temp.getEpisodes().size(); i++){
				temp.getEpisodes().get(i).checkHovered(e);
			}
		}
	}
	
	public void checkPressed(boolean b){
		if(rB != null){
			rB.checkPressed(b);
		}
		
		if(lB != null){
			lB.checkPressed(b);
		}
		
		if(seasons.size() > 0){
			Season temp = seasons.get(selectedIndex - 1);
			temp.getHours().checkPressed(b);
			for(int i = 0; i < temp.getEpisodes().size(); i++){
				temp.getEpisodes().get(i).checkPressed(b);
			}
		}
	}
	
	public void moveRight(){
		if(selectedIndex != seasons.size() - 1){
			selectedIndex++;
		}
	}
	
	public void moveLeft(){
		if(selectedIndex != 1){
			selectedIndex--;
		}
	}
	
	public void update(int x, int y){
		if(starting){
			if(this.y <= goalY){
				this.y = goalY;
			}
			if(alpha <= 5){
				alpha = 0;
				starting = false;
			}
			else{
				this.y--;
				seasons.add(new Season(x,goalY + 60));
				alpha -= 25;
			}
		}
		else{
			this.x = x;
			this.y = y;
		}
		
		if(rB != null){
			if(selectedIndex == seasons.size() - 1 || seasons.size() == 1){
				rB.setActive(false);
			}
			else{
				rB.setActive(true);
			}
			
			rB.update(this.x + 470, this.y + 15);
		}
		
		if(lB != null){
			if(selectedIndex == 1 || seasons.size() == 1){
				lB.setActive(false);
			}
			else{
				lB.setActive(true);
			}
			
			lB.update(this.x + 40, this.y + 15);
		}
		
		if(seasons.size() > 0){
			seasons.get(selectedIndex - 1).update(x, y + 60); 
		}
	};
	
	public void draw(Graphics2D g){
		g.setClip(x + 70, y, 400, 535);
		
		g.setColor(new Color(25,36,45));
		
		g.fillRect(x + 70, y + 15, 400, 60);
		
		Font temp = SPanel.font.deriveFont(24f);
		
		g.setFont(temp);
		
		g.setColor(new Color(85,96,105));
		g.fillRoundRect(x + 194, y + 19, 152, 52, 10, 10);
		
		int length = (int)g.getFontMetrics().getStringBounds("Season " + selectedIndex, g).getWidth(); 
		
		g.setColor(new Color(240,240,240));

		if(selectedIndex == 1){
			g.drawString("Season " + selectedIndex, x + length/2 + 195 - 7, y + 53);
		}
		else{
			g.drawString("Season " + selectedIndex, x + length/2 + 195 - 13, y + 53);
		}
		
		g.setFont(temp);
		
		if(seasons.size() == 1){
			
		}
		else if(selectedIndex == 1){
			g.setColor(new Color(85,96,105));
			g.fillRoundRect(x + 365, y + 20, 150, 50 ,10 ,10);
			
			int tempIndex = selectedIndex + 1;
			
			length = (int)g.getFontMetrics().getStringBounds("Season " + tempIndex, g).getWidth();
			
			g.setColor(new Color(240,240,240));
			
			if(tempIndex == 1){
				g.drawString("Season " + tempIndex, x + length/2 + 365 - 7, y + 53);
			}
			else{
				g.drawString("Season " + tempIndex, x + length/2 + 365 - 13, y + 53);
			}
		}
		else if (selectedIndex == seasons.size() - 1){
			g.setColor(new Color(85,96,105));
			g.fillRoundRect(x + 25, y + 20, 150, 50 ,10 ,10);
			
			int tempIndex = selectedIndex - 1;
			
			length = (int)g.getFontMetrics().getStringBounds("Season " + tempIndex, g).getWidth();	
			
			g.setColor(new Color(240,240,240));
			
			if(tempIndex == 1){
				g.drawString("Season " + tempIndex, x + length/2 + 25 - 7, y + 53);
			}
			else{
				g.drawString("Season " + tempIndex, x + length/2 + 25 - 13, y + 53);
			}
		}
		else{
			g.setColor(new Color(85,96,105));
			g.fillRoundRect(x + 25, y + 20, 150, 50 ,10 ,10);
			
			int tempIndex = selectedIndex - 1;
			
			length = (int)g.getFontMetrics().getStringBounds("Season " + tempIndex, g).getWidth();
			
			g.setColor(new Color(240,240,240));
			
			if(tempIndex == 1){
				g.drawString("Season " + tempIndex, x + length/2 + 25 - 7, y + 53);
			}
			else{
				g.drawString("Season " + tempIndex, x + length/2 + 25 - 13, y + 53);
			}
			
			g.setColor(new Color(85,96,105));
			g.fillRoundRect(x + 365, y + 20, 150, 50 ,10 ,10);
			
			tempIndex = selectedIndex + 1;
			
			length = (int)g.getFontMetrics().getStringBounds("Season " + tempIndex, g).getWidth();
			
			g.setColor(new Color(240,240,240));
			
			if(tempIndex == 1){
				g.drawString("Season " + tempIndex, x + length/2 + 365 - 7, y + 53);
			}
			else{
				g.drawString("Season " + tempIndex, x + length/2 + 365 - 13, y + 53);
			}
		}
		
		g.setClip(x + 40, y + 15, 500, 6000);
		
		if(rB != null){
			rB.draw(g);
		}
		
		if(lB != null){
			lB.draw(g);
		}
		
		g.setColor(new Color(101,120,134, alpha));
		g.fillRect(x, y, 1000, 1000);
		
		g.setClip(0, 0, SPanel.width, SPanel.height);
		
		if(seasons.size() > 0){
			seasons.get(selectedIndex - 1).draw(g);
		}
	};
}
