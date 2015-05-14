package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class MovieButton {
	private int x;
	private int y;
	
	public static int r = 130;
	
	private boolean starting;
	
	private int alpha;
	
	private String title;
	
	private boolean over;
	
	private boolean hovered;
	
	private boolean pressed;
	
	private boolean clicked;
	
	private int percentDone;
	
	private int angle;
	
	private int textX;
	
	public MovieButton(int x, int y, String title, int percentDone){
		this.x = x;
		this.y = y - 5;
		
		starting = true;
		alpha = 0;
		
		this.title = title;
		
		over = false;
		
		this.percentDone = percentDone;
		
		if(percentDone > 100){
			percentDone = 100;
		}
		
		angle = (int)((percentDone/100.0) * 360);
		
		textX = 0;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getAngle(){
		return angle;
	}
	
	public boolean isClicked(){
		return clicked;
	}
	
	public void setHovered(boolean b){
		hovered = b;
	}
	
	public void setPressed(boolean b){
		pressed = b;
	}
	public void setClicked(boolean b){
		clicked = b;
	}
	
	public boolean isHovered(MouseEvent e){
		if(e.getX() > x && e.getX() < x + r && e.getY() > y && e.getY() < y + r){
			return true;
		}
		return false;
	}
	
	public void update(){
		if(starting){
			if(alpha > 204){
				alpha = 255;
				starting = false;
			}
			else{
				alpha += 51;
				y++;
			}
		}
		
	}
	
	public void draw(Graphics2D g){		
		if(hovered){
			if(pressed){
				g.setColor(new Color(0,0,0,(int)(alpha/5)));
				g.fillOval(x, y, r, r);
				
				g.setColor(new Color(101,120,134, alpha));
				g.fillArc(x + 3, y + 3, r - 6, r - 6, 90, -angle);
				
				g.setColor(new Color(85,96,105, alpha));
				g.fillArc(x + 18, y+ 18, 94, 94, -angle + 90, -(360 - angle));
				
				Font tempFont = SPanel.font.deriveFont(18f);
				
				g.setColor(new Color(240,240,240, alpha));
				g.setFont(tempFont);
				
				g.setClip(x - 5, y + r, 150, 100);
			}
			else{
				g.setColor(new Color(0,0,0,(int)(alpha/5)));
				g.fillOval(x - 6, y - 6, 142, 142);
				
				g.setColor(new Color(101,120,134, alpha));
				g.fillArc(x - 3, y - 3, r + 6, r + 6, 90, -angle);
				
				g.setColor(new Color(85,96,105, alpha));
				g.fillArc(x + 12, y+12, 106, 106, -angle + 90, -(360 - angle));
				
				Font tempFont = SPanel.font.deriveFont(18f);
				
				g.setColor(new Color(240,240,240, alpha));
				g.setFont(tempFont);
				
				g.setClip(x - 5, y + r, 150, 100);
			}
			
			Font tempFont = SPanel.font.deriveFont(18f);
			
			g.setFont(tempFont);
			
			int length = (int)g.getFontMetrics().getStringBounds(title, g).getWidth();
			
			if(length > r){
				over = true;
				
				int overBy = length + length - (r*3)/4;
				
				textX++;
				
				if(textX >= overBy){
					textX = 0;
				}
				
				g.drawString(title, x - textX, y + r + 25);
				g.drawString(title, x - textX + length + length - (r*3)/4, y + r + 25);
			}
			else{
				g.drawString(title, x + r/2 - length/2, y + r + 25);
			}
			g.setClip(0, 0, SPanel.width, SPanel.height);
		}
		else{
			g.setColor(new Color(0,0,0,(int)(alpha/5)));
			g.fillOval(x - 3, y - 3, 136, 136);
			
			g.setColor(new Color(85,96,105, alpha));
			g.fillOval(x, y, r, r);
			
			g.setColor(new Color(121,140,154, alpha));
			g.fillOval(x+15, y+15, 100, 100);
			
			Font tempFont = SPanel.font.deriveFont(18f);
			
			g.setColor(new Color(240,240,240, alpha));
			g.setFont(tempFont);
			
			String shown = title;
			int length = (int)g.getFontMetrics().getStringBounds(shown, g).getWidth();
			int end = title.length() - 1;
			
			int periods = (int)g.getFontMetrics().getStringBounds("...", g).getWidth();
			
			while(length > r - periods){
				shown = title.substring(0, end);
				length = (int)g.getFontMetrics().getStringBounds(shown, g).getWidth();
				end--;
				over = true;
			}
			
			if(over){
				shown += "...";
			}
			
			length = (int)g.getFontMetrics().getStringBounds(shown, g).getWidth();
			
			g.drawString(shown, x + r/2 - length/2, y + r + 25);
		}
	}
}
