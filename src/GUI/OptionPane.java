package GUI;

import java.awt.Color;
import java.awt.Graphics2D;

public class OptionPane {
	private int x;
	private int y;
	
	private boolean shown;
	
	public OptionPane(int x, int y){
		this.x = x;
		this.y = y;
		
		shown = false;
	}
	
	public void setShown(boolean b){
		shown = b;
	}
	
	public void update(){
		if(shown){
			if(y <= 10){
				y += 40;
			}
			else{
				y = 50;
			}
		}
		else{
			if(y >= -345){
				y -= 40;
			}
			else{
				y = -385;
			}
		}
	}
	
	public void draw(Graphics2D g){
		g.setColor(new Color(220,220,220));
		g.fillRect(x,y, 260, 435);
	}
}
