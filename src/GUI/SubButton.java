package GUI;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

public class SubButton {
	private int x;
	private int y;
	
	private boolean active;
	private boolean hovered;
	private boolean pressed;
	
	private Hours parent;
	
	public SubButton(int x, int y, Hours parent){
		this.x = x;
		this.y = y;
		
		this.parent = parent;
	}
	
	public void setActive(boolean b){
		active = b;
	}
	
	public void checkHovered(MouseEvent e){
		if(active){
			if(e.getX() > x && e.getX() < x + 30 && e.getY() > y && e.getY() < y + 30){
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
				parent.subtract();
			}
		}
	}
	
	public void update(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics2D g){
		if(active){
			if(hovered){
				if(pressed){
					g.setColor(new Color(45,56,65));
					g.fillRect(x + 5, y, 20, 25);
					g.fillRoundRect(x, y, 25, 25, 5, 5);
					g.drawImage(new ImageIcon("images/sub.png").getImage(), x + 5, y + 5, 15, 15, null);
				}
				else{
					g.setColor(new Color(55,66,75));
					g.fillRect(x + 5, y, 20, 25);
					g.fillRoundRect(x, y, 25, 25, 5, 5);
					g.drawImage(new ImageIcon("images/sub.png").getImage(), x + 5, y + 5, 15, 15, null);
				}
			}
			else{
				g.setColor(new Color(65,76,85));
				g.fillRect(x + 5, y, 20, 25);
				g.fillRoundRect(x, y, 25, 25, 5, 5);
				g.drawImage(new ImageIcon("images/sub.png").getImage(), x + 5, y + 5, 15, 15, null);
			}
		}
		else{
			g.setColor(new Color(65,76,85));
			g.fillRect(x + 5, y, 20, 25);
			g.fillRoundRect(x, y, 25, 25, 5, 5);
			
			g.drawImage(new ImageIcon("images/sub.png").getImage(), x + 5, y + 5, 15, 15, null);
			
			g.setColor(new Color(255,255,255,50));
			g.fillRoundRect(x, y, 25, 25, 5, 5);
		}
	}
}
