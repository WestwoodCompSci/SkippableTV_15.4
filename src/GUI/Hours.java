package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class Hours {
	private int x;
	private int y;
	
	private int amount;
	private int max;
	
	private AddButton aB;
	private SubButton sB;
	
	public Hours(int x, int y, int amount, int max){
		this.x = x;
		this.y = y;
		
		this.max = max;
		
		this.amount = amount;
		
		aB = new AddButton(x + 210, y, this);
		sB = new SubButton(x + 135, y, this);
	}
	
	public void add(){
		if(amount >= max){
			amount = max;
		}
		else{
			amount++;
		}
	}
	
	public void subtract(){
		if(amount <= 1){
			amount = 1;
		}
		else{
			amount--;
		}
	}
	
	public void checkHovered(MouseEvent e){
		aB.checkHovered(e);
		sB.checkHovered(e);
	}
	
	public void checkPressed(boolean b){
		aB.checkPressed(b);
		sB.checkPressed(b);
	}
	
	public void update(int x, int y){
		this.x = x;
		this.y = y;
		
		if(amount == max){
			aB.setActive(false);
		}
		else{
			aB.setActive(true);
		}
		
		if(amount == 1){
			sB.setActive(false);
		}
		else{
			sB.setActive(true);
		}
		
		sB.update(this.x + 135, this.y);
		aB.update(this.x + 210, this.y);
	}
	
	public void draw(Graphics2D g){
		g.setColor(new Color(240,240,240));
		
		Font temp = SPanel.font.deriveFont(19f);
		
		g.setFont(temp);
		
		g.drawString("Time Available:", x, y + 20);
		
		g.setColor(new Color(230,230,230));
		
		g.fillRect(x + 160, y, 50, 25);
		
		g.setColor(new Color(120,126,130));
		int length = (int)g.getFontMetrics().getStringBounds("" + amount, g).getWidth();
		
		g.drawString("" + amount, x + 200 - length, y + 20);
		
		aB.draw(g);
		sB.draw(g);
		
		g.setColor(new Color(240,240,240));
		g.drawString("hrs", x + 240, y + 20);
	}
}
