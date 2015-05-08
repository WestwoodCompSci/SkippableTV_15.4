package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class GenreButton{
	public static int width = 260;
	public static int height = 87;
	
	private int x;
	private int y;
	
	private String title;
	
	private boolean starting;
	private int alpha;
	
	private boolean selected;
	private boolean hovered;
	private boolean pressed;
	
	private Color color;
	
	private Color original;
	private Color hover;
	private Color press;
	private Color select;
	
	public GenreButton(String n, int x, int y){
		title = n;
		
		this.x = x;
		this.y = y + 10;
		
		alpha = 0;
		starting = true;
		
		selected = false;
		
		color = new Color(150,150,150, alpha);
		original = new Color(150,150,150);
		hover = new Color(125,125,125);
		press = new Color(100,100,100);
		select = new Color(75,75,75);
	}
	
	public int getX(){return x;}
	public int getY(){return y;}
	
	public void setSelected(boolean b){
		selected = b;
	}
	
	public void setHovered(boolean b){
		hovered = b;
	}
	
	public void setPressed(boolean b){
		pressed = b;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;	
	}
	
	public void update(){
		if(starting){
			if(alpha >= 229){
				starting = false;
				alpha = 255;
				color = original;
			}
			else{
				y--;
				alpha += (int)(255/10);
				color = new Color(150,50,150, alpha);
			}
		}
		if(!selected){
			if(hovered){
				color = hover;
				if(pressed){
					color = press;
				}
			}
			else{
				color = original;
			}
		}
	}
	
	public void draw(Graphics2D g){
		if(selected){
			g.setColor(select);
		}
		else{
			g.setColor(color);
		}
		g.fillRect(x, y, width, height);
		
		//GradientPaint verticalFade1 = new GradientPaint(x,y,new Color(0,0,0,50),x,y + 3,new Color(0,0,0,0));
		//g.setPaint(verticalFade1);
		g.setColor(new Color(0,0,0,50));
		g.fillRect(x, y, width, 1);
		
		//GradientPaint verticalFade2 = new GradientPaint(x,y + height - 3,new Color(0,0,0,0),x,y + height,new Color(0,0,0,50));
		//g.setPaint(verticalFade2);
		g.fillRect(x, y + height - 1, width, 1);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Century Gothic", Font.BOLD, 24));
		int length = (int)g.getFontMetrics().getStringBounds(title, g).getWidth();
		g.drawString(title, x + width/2 - length/2, y + height/2 + 5);
	}

	public boolean isHovered(MouseEvent e){
		if(e.getX() > x && e.getX() < x + width){
			if(e.getY() > y && e.getY() < y + height){
				return true;
			}
		}
		return false;
	}
}
