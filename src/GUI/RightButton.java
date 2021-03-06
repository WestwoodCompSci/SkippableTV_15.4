package GUI;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

public class RightButton {
	private int x;
	private int y;
	
	private boolean active;
	private boolean hovered;
	private boolean pressed;
	
	private SeasonList parent;
	
	public RightButton(int x, int y, SeasonList p){
		this.x = x;
		this.y = y;
		
		parent = p;
	}
	
	public void setActive(boolean b){
		active = b;
	}
	
	public void checkHovered(MouseEvent e){
		if(active){
			if(e.getX() > x && e.getX() < x + 30 && e.getY() > y && e.getY() < y + 60){
				hovered = true;
			}
			else{
				hovered = false;
			}
		}
		else{
			hovered = false;
		}
	}
	
	public void checkPressed(boolean b){
		if(hovered){
			pressed = b;
			if(!b){
				parent.moveRight();
			}
		}
	}
	
	public void update(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics2D g){
		if(active){
			GradientPaint horizontalFade = new GradientPaint(x, 0,new Color(0,0,0,100),x - 10, 0,new Color(0,0,0,0));
			g.setPaint(horizontalFade);
			g.fillRect(x - 10, y, 10, 60);
			
			if(hovered){
				if(pressed){
					g.setColor(new Color(45,56,65));
					g.fillRect(x, y, 25, 60);
					g.fillRoundRect(x, y, 30, 60, 5, 5);
					g.drawImage(new ImageIcon("images/right.png").getImage(), x, y, 30, 60, null);
				}
				else{
					g.setColor(new Color(55,66,75));
					g.fillRect(x, y, 25, 60);
					g.fillRoundRect(x, y, 30, 60, 5, 5);
					g.drawImage(new ImageIcon("images/right.png").getImage(), x, y, 30, 60, null);
				}
			}
			else{
				g.setColor(new Color(65,76,85));
				g.fillRect(x, y, 25, 60);
				g.fillRoundRect(x, y, 30, 60, 5, 5);
				g.drawImage(new ImageIcon("images/right.png").getImage(), x, y, 30, 60, null);
			}
		}
		else{
			GradientPaint horizontalFade = new GradientPaint(x, 0,new Color(0,0,0,100),x - 10, 0,new Color(0,0,0,0));
			g.setPaint(horizontalFade);
			g.fillRect(x - 10, y, 10, 60);
			
			g.setColor(new Color(65,76,85));
			g.fillRect(x, y, 25, 60);
			g.fillRoundRect(x, y, 30, 60, 5, 5);
			
			g.drawImage(new ImageIcon("images/right.png").getImage(), x, y, 30, 60, null);
			
			g.setColor(new Color(255,255,255,50));
			g.fillRoundRect(x, y, 30, 60, 5, 5);
		}
	}
}