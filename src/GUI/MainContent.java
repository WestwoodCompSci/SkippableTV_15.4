package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MainContent{
	
	private int x;
	private int y;
	
	private ArrayList<MovieButton> movies;
	
	private boolean starting;
	
	private int buttonX;
	private int buttonY;
	
	private int startingY;
	
	private int timer;
	
	private String title;
	
	private SearchBar search;
	
	private SPanel parent;
	
	private MovieInfo info;
	
	private boolean showningInfo;
	
	public MainContent(int x, int y, String title, SPanel s){
		this.x = x;
		this.y = y;
		
		movies = new ArrayList<MovieButton>();
		
		buttonX = x + 50;
		buttonY = y + 60;
		
		startingY = y - 50;
		
		starting = true;
		
		timer = 0;
		
		this.title = title;
		
		parent = s;
		
		info = null;
		
		showningInfo = false;
	}
	
	public SearchBar getSearchBar(){
		return search;
	}
	
	public boolean isShowingInfo(){
		return showningInfo;
	}
	
	public void checkHovered(MouseEvent e){
		for(int i = 0; i < movies.size(); i++){
			boolean hover = movies.get(i).isHovered(e);
			if(hover){
				movies.get(i).setHovered(true);
			}
			else{
				movies.get(i).setHovered(false);
			}
		}
	}
	
	public void checkPressed(MouseEvent e, boolean b){
		for(int i = 0; i < movies.size(); i++){
			boolean hover = movies.get(i).isHovered(e);
			if(hover){
				movies.get(i).setPressed(b);
				if(!b){
					for(int j = 0; j < movies.size(); j++){
						if(j != i){
							movies.get(j).setClicked(false);
						}
						else{
							movies.get(j).setClicked(true);
							info = new MovieInfo(260, 50, movies.get(j).getX(),movies.get(j).getY(),movies.get(j).getAngle());
							showningInfo = true;;
						}
					}
				}
				break;
			}
		}
	}
	
	public void update(){
		if(starting){
			if(timer > 59){
				starting = false;
				search = new SearchBar(x + 500, y + 10, this);
			}
			else{
				if(timer%5 == 0){
					if(timer%20 == 0 && timer != 0){
						buttonX = x + 50;
						buttonY += 175;
					}
					
					if(timer == 0){
						movies.add(new MovieButton(buttonX, buttonY, "Some Filler Text", 40));
					}
					else{
						movies.add(new MovieButton(buttonX, buttonY, "" + timer, timer * 2));
					}
					
					buttonX += 180;
				}
				timer++;
			}
			if(startingY >= 0){
				startingY = 0;
			}
			else{
				startingY += 10;
			}
		}
		else{
			search.update();
		}
		
		for(int i = 0; i < movies.size(); i++){
			movies.get(i).update();
		}
		
		if(info != null){
			info.update();
		}
	}
	
	public void draw(Graphics2D g){
		for(int i = 0; i < movies.size(); i++){
			movies.get(i).draw(g);
		}
		
		if(info != null){
			info.draw(g);
		}
		
		if(starting){
			GradientPaint verticalDownFade = new GradientPaint(x,startingY + 50,new Color(0,0,0,100),x, startingY + 53,new Color(0,0,0,0));
			g.setPaint(verticalDownFade);
			g.fillRect(x, startingY + 50, 780, 3);
			
			g.setColor(new Color(60,66,70));
			g.fillRect(x, startingY, 780, 50);
			
			Font tempFont = SPanel.font.deriveFont(24f);
			
			g.setColor(new Color(220,220,220));
			g.setFont(tempFont);
			g.drawString(title, x + 30, startingY + 35);
		}
		else{
			GradientPaint verticalDownFade = new GradientPaint(x,y + 50,new Color(0,0,0,100),x, y + 53,new Color(0,0,0,0));
			g.setPaint(verticalDownFade);
			g.fillRect(x, y + 50, 780, 3);
			
			g.setColor(new Color(60,66,70));
			g.fillRect(x, y, 780, 50);
			
			Font tempFont = SPanel.font.deriveFont(24f);
			
			g.setColor(new Color(220,220,220));
			g.setFont(tempFont);
			g.drawString(title, x + 30, y + 35);
			
			search.draw(g);
		}
	}
	
	public void setCursor(Cursor c){
		parent.setCursor(c);
	}
}
