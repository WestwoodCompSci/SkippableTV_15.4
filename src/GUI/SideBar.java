package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class SideBar extends JPanel{
	int x;
	
	private boolean starting;
	private boolean scrolling;
	
	private GenreList genres;
	
	private Profile profile;
	
	private OptionPane options;
	
	//private int alphaScroll;
	
	private SPanel parent;
	
	public SideBar(SPanel s){
		this.setPreferredSize(new Dimension(SPanel.width, SPanel.height));
		x = -260;
		this.setLocation(x,0);
		starting = true;
		scrolling = false;
		
		//alphaScroll = 100;
		
		options = new OptionPane(0, -365);
		
		parent = s;
	}
	
	public boolean isStarting(){
		if(!starting){
			return genres.isStarting();
		}
		
		return true;
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
	
	public void checkProfileHovered(MouseEvent e){
		if(profile != null){
			if(e.getX() > 210 && e.getX() < 260 && e.getY() > 0 && e.getY() < 50){
				profile.setHover(true);
			}
			else{
				profile.setHover(false);
			}
		}
	}
	
	public void checkProfilePressed(MouseEvent e, boolean b){
		if(profile != null){
			if(e.getX() > 210 && e.getX() < 260 && e.getY() > 0 && e.getY() < 50){
				profile.setPressed(b);
				if(!b){
					profile.toggleSelected();
				}
			}
			else{
				profile.setPressed(false);
			}
		}
	}
	
	public void update(){
		if(starting){
			if(x >= 0){
				starting = false;
				this.setLocation(0,0);
				genres = new GenreList(0,50, this);
				profile = new Profile(0,0, "Testing testing testing");
			}
			else{
				x += 10;
				this.setLocation(x, 0);
			}
		}
		else{
			genres.update();
			profile.update();
			
			options.setShown(profile.isSelected());
			
			options.update();
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
			
			if(!genres.isStarting()){				
				g.setColor(new Color(50,50,50, 100));
				g.fillRoundRect(x + 253, 50, 5, 435, 5, 5);
				
				g.setColor(new Color(0,0,0, 100));
				g.fillRoundRect(x + 253, genres.getStartIndex()*(435/genres.getSize())*2 + 52, 5, 435/genres.getSize()*2, 5, 5);
			}
			
			options.draw(g);
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
		
		if(!starting){
			profile.draw(g);
		}
		
		g.drawImage(new ImageIcon("images/skip.png").getImage(), x + 10, 500, 70, 70,null);
		
		Font temp = SPanel.font.deriveFont(28f);
		
		g.setColor(new Color(0, 0, 0, 50));
		g.setFont(temp);
		g.drawString("SKIPPABLE.tv", x + 92, 548);
		g.setColor(new Color(220,220,220));
		g.drawString("SKIPPABLE.tv", x + 90, 545);
		
	}
	
	public void setCursor(Cursor c){
		parent.setCursor(c);
	}
}
