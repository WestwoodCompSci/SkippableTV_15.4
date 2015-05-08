package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class SideBar extends JPanel{
	int x;
	
	private boolean starting;
	private boolean scrolling;
	
	private GenreList genres;
	
	private int alphaScroll;
	
	public SideBar(){
		this.setPreferredSize(new Dimension(SPanel.width, SPanel.height));
		x = -260;
		this.setLocation(x,0);
		starting = true;
		scrolling = false;
		
		alphaScroll = 100;
	}
	
	public void setScrolling(boolean b){
		scrolling = b;
	}
	
	public GenreList getGenreList(){
		return genres;
	}
	
	public boolean isScrolling(){
		return scrolling;
	}
	
	public void setAlphaScroll(int x){
		alphaScroll = 100;
	}
	
	public void update(){
		if(starting){
			if(x >= 0){
				starting = false;
				this.setLocation(0,0);
				genres = new GenreList(0,50, this);
			}
			else{
				x += 10;
				this.setLocation(x, 0);
			}
		}
		else{
			genres.update();
		}
	}
	
	public void draw(Graphics2D g){
		GradientPaint horizontalFade = new GradientPaint(this.getX() + 260,0,new Color(0,0,0,100),this.getX() + 267, 0,new Color(0,0,0,0));
		g.setPaint(horizontalFade);
		g.fillRect(this.getX() + 260, 0, 7, 585);
		
		g.setColor(new Color(214,214,214));
		g.fillRect(this.getX(), 0, 260, 585);
		
		if(!starting){
			genres.draw(g);
			
			if(!genres.isStarting() && scrolling){				
				g.setColor(new Color(50,50,50,alphaScroll));
				g.fillRoundRect(x + 253, 50, 5, 435, 5, 5);
				
				g.setColor(new Color(0,0,0,alphaScroll));
				g.fillRoundRect(x + 253, genres.getStartIndex()*(435/genres.getSize())*2 + 52, 5, 435/genres.getSize()*2, 5, 5);
			}
		}
		
		GradientPaint verticalDownFade = new GradientPaint(0,50,new Color(0,0,0,100),0, 53,new Color(0,0,0,0));
		g.setPaint(verticalDownFade);
		g.fillRect(this.getX(), 50, 260, 3);
		
		g.setColor(new Color(37, 110, 121));
		g.fillRect(this.getX(), 0, 260, 50);
		
		GradientPaint verticalUpFade = new GradientPaint(0,480,new Color(0,0,0,0),0, 485,new Color(0,0,0,100));
		g.setPaint(verticalUpFade);
		g.fillRect(this.getX(),480,260,5);
		
		g.setColor(new Color(37, 110, 121));
		g.fillRect(this.getX(), 485, 260, 100);
		
		g.drawImage(new ImageIcon("images/skip.png").getImage(), x + 10, 500, 70, 70,null);
	
		g.setColor(new Color(0, 0, 0, 50));
		g.setFont(new Font("Century Gothic", Font.BOLD, 27));
		g.drawString("SKIPPABLE.tv", x + 92, 545);
		g.setColor(new Color(220,220,220));
		g.drawString("SKIPPABLE.tv", x + 90, 543);
		
	}
}
