package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class SearchBar {
	private int x;
	private int y;
	
	private boolean starting;
	
	private int alpha;
	
	private String typed;	
	
	private boolean textHovered;
	private boolean textSelected;
	
	private MainContent parent;
	
	private int blink;
	
	private long startSelect;
	
	private int blinkIndex;
	
	public SearchBar(int x, int y, MainContent p){
		this.x = x;
		this.y = y;
		
		alpha = 0;
		
		starting = true;
		
		typed = "";
		
		textHovered = false;
		textSelected = false;
		
		parent = p;
		
		blink = 255;
		
		blinkIndex = 0;
	}
	
	
	
	public void checkTextHovered(MouseEvent e){
		if(e.getX() > x && e.getX() < x + 200 && e.getY() > y && e.getY() < y + 30){
			parent.setCursor(new Cursor(Cursor.TEXT_CURSOR));
			textHovered = true;
		}
		else{
			parent.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			textHovered = false;
		}
	}
	
	public void selectText(boolean b){
		if(textHovered && b == true){
			textSelected = true;
			startSelect = System.currentTimeMillis();
		}
		else{
			textSelected = false;
		}
	}
	
	public void appendText(char a){
		if(textSelected){
			if(blinkIndex < typed.length()){
				String temp = typed.substring(blinkIndex++);
				typed = typed.substring(0, blinkIndex) + a + temp;
			}
			else{
				typed += a;
			}
			blink = 255;
			blinkIndex++;
			startSelect = System.currentTimeMillis();
		}
	}
	
	public void backSpace(){
		if(textSelected && !typed.isEmpty()){
			typed = typed.substring(0, typed.length()-1);
			blink = 255;
			blinkIndex--;
			startSelect = System.currentTimeMillis();
		}
	}
	
	public void update(){
		if(starting){
			if(alpha > 204){
				alpha = 255;
				starting = false;
			}
			else{
				alpha += 51;
			}
		}
		if(textSelected){
			long elapsed = System.currentTimeMillis() - startSelect;
			if(elapsed > 500){
				if(blink == 255){
					blink = 0;
				}
				else{
					blink = 255;
				}
				startSelect = System.currentTimeMillis();
			}
		}
	}
	
	public void draw(Graphics2D g){
		g.setColor(new Color(240,240,240, alpha));
		g.fillRoundRect(x, y, 200, 30, 7, 7);
		g.fillRect(x + 7, y, 193, 30);
		
		g.setColor(new Color(120,126,130, alpha));
		g.fillRoundRect(x + 200, y, 50, 30, 7, 7);
		g.fillRect(x + 200, y, 43, 30);
		
		/*g.setColor(new Color(30,30,30));
		g.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		int length = (int)g.getFontMetrics().getStringBounds(typed, g).getWidth();
		g.setClip(x + 5, y, 187, 30);
		
		if(length > 187){
			int over = length - 187;
			g.drawString(typed, x + 5 - over, y + 23);
		}
		else{
			g.drawString(typed, x + 5, y + 23);
		}
		
		g.setClip(0, 0, SPanel.width, SPanel.height);
		if(textSelected){			
			if(length > 187){
				g.setColor(new Color(10,10,10, blink));
				g.fillRect(x + 193, y + 5, 2, 20);
			}
			else{
				g.setColor(new Color(10,10,10, blink));
				g.fillRect(x + length + 6, y + 5, 2, 20);
			}
		}*/
		
		int length = 0;
		
		Font tempFont = SPanel.font.deriveFont(18f);
		
		g.setFont(tempFont);
		
		if((int)g.getFontMetrics().getStringBounds(typed, g).getWidth() > 187){
			int over = (int)g.getFontMetrics().getStringBounds(typed, g).getWidth() - 187;
			for(int i = 0; i <= typed.length(); i++){
				if(typed.length() != 0 && i != typed.length()){
					g.setClip(x + 5, y, 187, 30);
					char temp = typed.charAt(i);
					g.drawString("" + temp, x + 5 + length - over, y + 23);
					length += (int)g.getFontMetrics().getStringBounds("" + temp, g).getWidth();		
					g.setClip(0, 0, SPanel.width, SPanel.height); 
				}
				if(i == blinkIndex && textSelected){
					g.setColor(new Color(10,10,10,blink));
					g.fillRect(x + length + 6 - over, y + 5, 2, 20);
				}
			}
		}
		else{
			for(int i = 0; i <= typed.length(); i++){				
				if(typed.length() != 0 && i < typed.length()){
					char temp = typed.charAt(i);
					g.drawString("" + temp, x + 5 + length, y + 23);
					length += (int)g.getFontMetrics().getStringBounds("" + temp, g).getWidth();				
				}
				if(i == blinkIndex && textSelected){
					g.setColor(new Color(10,10,10,blink));
					g.fillRect(x + length + 6, y + 5, 2, 20);
				}
			}
		}
	}
}
