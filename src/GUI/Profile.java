package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;

public class Profile {
	private int x;
	private int y;
	
	private String name;
	
	private ImageIcon profPic;
	
	private boolean hovered;
	private boolean pressed;
	private boolean selected;
	
	private boolean over;
	
	private int textX;
	
	private boolean starting;
	
	private int alphaFade;
	
	public Profile(int x, int y, String name){
		this.x = x;
		this.y = y;
		
		this.name = name;
		
		profPic = new ImageIcon("images/filler.png");
		
		textX = 0;
		
		over = false;
		
		starting = true;
		alphaFade = 255;
	}
	
	public Profile(int x, int y, String name, ImageIcon img){
		this.x = x;
		this.y = y;
		
		this.name = name;
		
		profPic = img;
		
		textX = 0;
		
		over = false;
	}
	
	public void setHover(boolean b){
		hovered = b;
	}
	
	public void setPressed(boolean b){
		pressed = b;
	}
	
	public void toggleSelected(){
		if(selected){
			selected = false;
		}
		else{
			selected = true;
		}
	}
	
	public boolean isSelected(){
		return selected;
	}
	
	public void update(){
		if(starting){
			if(alphaFade <= 15){
				alphaFade = 0;
				starting = false;
			}
			else{
				alphaFade -= 20;
			}
		}
	}
	
	public void draw(Graphics2D g){		
		//g.setColor(new Color(0, 70, 81));
		//g.fillRect(x + 208, 0, 2, 50);
		
		g.drawImage(profPic.getImage(), 0, 0, 50, 50, null);
		
		if(hovered){					
			Font temp = SPanel.font.deriveFont(22f);
			
			g.setColor(new Color(240,240,240));
			g.setFont(temp);
			
			int length = (int)g.getFontMetrics().getStringBounds(name, g).getWidth();
			
			if(length > 140){	
				g.setClip(55, 0, 150, 50);
				
				over = true;
				
				int overBy = length + 50;
				
				textX++;
				
				if(textX >= overBy){
					textX = 0;
				}
				
				g.drawString(name, x - textX + 60, y + 33);
				g.drawString(name, x - textX + length + 110, y + 33);
			}
			else{
				g.drawString(name, x + 65, y + 33);
			}
			g.setClip(0, 0, SPanel.width, SPanel.height);
		}
		else{
			Font temp = SPanel.font.deriveFont(22f);
			
			g.setColor(new Color(240,240,240));
			g.setFont(temp);
			String shown = name;
			int length = (int)g.getFontMetrics().getStringBounds(shown, g).getWidth();
			int end = name.length() - 1;
			
			int periods = (int)g.getFontMetrics().getStringBounds("...", g).getWidth();
			
			while(length > 140 - periods){
				shown = name.substring(0, end);
				length = (int)g.getFontMetrics().getStringBounds(shown, g).getWidth();
				end--;
				over = true;
			}
			
			if(over){
				shown += "...";
			}
			
			length = (int)g.getFontMetrics().getStringBounds(shown, g).getWidth();
			
			g.drawString(shown, x + 60, y + 33);		
		}
		
		if(selected){
			if(hovered){
				if(pressed){
					g.setColor(new Color(0, 50, 61));
					g.fillRect(210, 0, 50, 50);
				}
				else{
					g.setColor(new Color(0, 60, 71));
					g.fillRect(210, 0, 50, 50);
				}
			}
			else{
				g.setColor(new Color(0, 70, 81));
				g.fillRect(210, 0, 50, 50);	
			}
		}
		else{
			if(hovered){
				if(pressed){
					g.setColor(new Color(0, 60, 81));
					g.fillRect(210, 0, 50, 50);
				}
				else{
					g.setColor(new Color(7, 80, 91));
					g.fillRect(210, 0, 50, 50);
				}
			}
			else{
				g.setColor(new Color(17, 90, 101));
				g.fillRect(210, 0, 50, 50);	
			}
		}
		
		g.setColor(new Color(37,110,121,alphaFade));
		g.fillRect(0,0,260,50);
	}
}
