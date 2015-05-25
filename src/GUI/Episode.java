package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

public class Episode {
	private int x;
	private int y;
	
	private boolean starting;
	
	private boolean skip;
	private boolean watched;
	
	private int rating;
	
	private int number;
	
	private boolean increaseActive;
	private boolean increaseHover;
	private boolean increasePressed;
	
	private boolean decreaseActive;
	private boolean decreaseHover;
	private boolean decreasePressed;
	
	public Episode(int x, int y, boolean skip, int rating, boolean watched, int number){
		this.x = x;
		this.y = y;
		
		starting = false;
		
		this.skip = skip;
		
		this.watched = watched;
		
		this.rating = rating;
		
		this.number = number;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public void checkHovered(MouseEvent e){
		if(increaseActive){
			if(e.getX() > x + 272 && e.getX() < x + 292 && e.getY() > y + 15 && e.getY() < y + 35){
				increaseHover = true;
			}
			else{
				increaseHover = false;
			}
		}
		else{
			increaseHover = false;
		}
		
		if(decreaseActive){
			if(e.getX() > x + 197 && e.getX() < x + 217 && e.getY() > y + 15 && e.getY() < y + 35){
				decreaseHover = true;
			}
			else{
				decreaseHover = false;
			}
		}
		else{
			decreaseHover = false;
		}
	}
	
	public void checkPressed(boolean b){
		if(increaseHover){
			increasePressed = b;
			if(!b){
				if(rating == 5){
					rating = 5;
				}
				else{
					rating++;
				}
			}
		}
		if(decreaseHover){
			decreasePressed = b;
			if(!b){
				if(rating == 1){
					rating = 1;
				}
				else{
					rating--;
				}
			}
		}
	}
	
	public void update(int x, int y){
		this.x = x;
		this.y = y;
		
		if(!watched){
			increaseActive = false;
			decreaseActive = false;
		}
		else{
			if(rating == 1){
				decreaseActive = false;
			}
			else{
				decreaseActive = true;
			}
			if(rating == 5){
				increaseActive = false;
			}
			else{
				increaseActive = true;
			}
		}
	};
	
	public void draw(Graphics2D g){
		/*if(watched){
			g.setColor(new Color(200,200,200));
			g.fillRect(x, y, 480, 50);
		}
		else{
			if(skip){
				g.setColor(new Color(255,65,36));
				g.fillRect(x, y, 480, 50);
			}
			else{
				g.setColor(new Color(240,240,240));
				g.fillRect(x, y, 480, 50);
			}
		}*/
		
		g.setColor(new Color(240,240,240));
		g.fillRect(x, y, 480, 50);
		
		Font temp = SPanel.font.deriveFont(16f);
		
		g.setFont(temp);
		
		g.setColor(new Color(30,30,30));
		
		g.drawString("Episode " + number, x + 30, y + 30);
		
		g.drawString("Rating: ", x + 140, y + 30);
		
		g.drawString("Status: ", x + 300, y + 30);
		
		if(watched){
			g.setColor(new Color(0,235,111));
			g.drawString("Watched",  x + 355, y + 30);
		}
		else{
			if(skip){
				g.setColor(new Color(255,65,36));
				g.drawString("Skip", x + 355, y + 30);
			}
			else{
				g.setColor(new Color(150,150,150));
				g.drawString("Should Watch", x + 355, y + 30);
			}
		}
		
		g.setColor(Color.BLACK);
		//g.fillRect(x + 190, y + 15, 100, 20);
		
		switch(rating){
			case 1:
				g.setColor(new Color(255, 65, 36));
				break;
			case 2:
				g.setColor(new Color(232,143,62));
				break;
			case 3:
				g.setColor(new Color(255,219,79));
				break;
			case 4:
				g.setColor(new Color(212,232,89));
				break;
			default:
				g.setColor(new Color(0, 235, 111));
				break;
		}
		
		for(int i = 0; i < rating; i++){
			g.fillRect(x + 215 + (12*i), y + 15, 6, 20);
		}
		
		
		if(increaseActive){
			if(increaseHover){
				if(increasePressed){
					g.setColor(new Color(45,56,65));
					g.fillRect(x + 272, y + 15, 15, 20);
					g.fillRoundRect(x + 272, y + 15, 20, 20, 5, 5);
					g.drawImage(new ImageIcon("images/add.png").getImage(), x + 277, y + 20, 10, 10, null);
				}
				else{
					g.setColor(new Color(55,66,75));
					g.fillRect(x + 272, y + 15, 15, 20);
					g.fillRoundRect(x + 272, y + 15, 20, 20, 5, 5);
					g.drawImage(new ImageIcon("images/add.png").getImage(), x + 277, y + 20, 10, 10, null);
				}
			}
			else{
				g.setColor(new Color(65,76,85));
				g.fillRect(x + 272, y + 15, 15, 20);
				g.fillRoundRect(x + 272, y + 15, 20, 20, 5, 5);
				g.drawImage(new ImageIcon("images/add.png").getImage(), x + 277, y + 20, 10, 10, null);
			}
		}
		else{
			g.setColor(new Color(65,76,85));
			g.fillRect(x + 272, y + 15, 15, 20);
			g.fillRoundRect(x + 272, y + 15, 20, 20, 5, 5);
			g.drawImage(new ImageIcon("images/add.png").getImage(), x + 277, y + 20, 10, 10, null);
			
			g.setColor(new Color(255,255,255, 50));
			g.fillRoundRect(x + 272, y + 15, 20, 20, 5, 5);
		}
		
		if(decreaseActive){
			if(decreaseHover){
				if(decreasePressed){
					g.setColor(new Color(45,56,65));
					g.fillRect(x + 197, y + 15, 15, 20);
					g.fillRoundRect(x + 192, y + 15, 20, 20, 5, 5);
					g.drawImage(new ImageIcon("images/sub.png").getImage(), x + 197, y + 20, 10, 10, null);
				}
				else{
					g.setColor(new Color(55,66,75));
					g.fillRect(x + 197, y + 15, 15, 20);
					g.fillRoundRect(x + 192, y + 15, 20, 20, 5, 5);
					g.drawImage(new ImageIcon("images/sub.png").getImage(), x + 197, y + 20, 10, 10, null);
				}
			}
			else{
				g.setColor(new Color(65,76,85));
				g.fillRect(x + 197, y + 15, 15, 20);
				g.fillRoundRect(x + 192, y + 15, 20, 20, 5, 5);
				g.drawImage(new ImageIcon("images/sub.png").getImage(), x + 197, y + 20, 10, 10, null);
			}
		}
		else{
			g.setColor(new Color(65,76,85));
			g.fillRect(x + 197, y + 15, 15, 20);
			g.fillRoundRect(x + 192, y + 15, 20, 20, 5, 5);
			g.drawImage(new ImageIcon("images/sub.png").getImage(), x + 197, y + 20, 10, 10, null);
			
			g.setColor(new Color(255,255,255, 50));
			g.fillRoundRect(x + 192, y + 15, 20, 20, 5, 5);
		}
		
		g.setColor(new Color(101,120,134));
		g.fillRect(x, y, 480, 2);
		g.fillRect(x, y + 48, 480, 2);
	};
}
