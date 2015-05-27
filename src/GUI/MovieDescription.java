package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class MovieDescription {
	private int x;
	private int y;
	
	private int rating;
	private String description;
	
	private ArrayList<String> paragraph;
	
	private boolean scroll;
	
	private int startCount;
	
	public MovieDescription(int x, int y, int rating, String description){
		this.x = x;
		this.y = y;
		
		this.rating = rating;
		this.description = description;
		
		startCount = 0;
	}
	
	public void scrollUp(MouseEvent e){
		if(scroll && (e.getX() > x && e.getX() < x + 220 && e.getY() > y + 80 && e.getY() < y + 320)){
			if(startCount > 0){
				startCount--;
			}
			else{
				startCount = 0;
			}
		}
	}
	
	public void scrollDown(MouseEvent e){
		if(scroll && (e.getX() > x && e.getX() < x + 220 && e.getY() > y + 80 && e.getY() < y + 320)){
			if(startCount < paragraph.size() - 15){
				startCount++;
			}
			else{
				startCount = paragraph.size() - 15;
			}
		}
	}
	
	public ArrayList<String> parseDescription(String description, Graphics2D g){
		ArrayList<String> temp = new ArrayList<String>();
		
		int startWord = 0;
		
		int length = 0;
		
		String tempString = "";
		
		for(int i = 0; i < description.length(); i++){
			if(Character.isSpaceChar(description.charAt(i))){
				String tempAdd = description.substring(startWord, i);
				length += (int)g.getFontMetrics().getStringBounds(tempAdd, g).getWidth();
				if(length > 220){
					temp.add(tempString);
					tempString = tempAdd;
					length = (int)g.getFontMetrics().getStringBounds(tempString, g).getWidth();
				}				
				else{
					tempString += tempAdd;
				}
				
				startWord = i;
			}
			else if(i == description.length() - 1){
				String tempAdd = description.substring(startWord, i + 1); 
				tempString += tempAdd;
				temp.add(tempString);
			}
		}
		
		if(temp.size() > 15){
			scroll =  true;
		}
		else{
			scroll  = false;
		}
		
		return temp;
	}
	
	public void update(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics2D g){		
		int starRating;
		
		if(rating >= 0 && rating < 20){
			starRating = 1;
		}
		else if(rating >= 20 && rating < 40){
			starRating = 2;
		}
		else if(rating >= 40 && rating < 60){
			starRating = 3;
		}
		else if(rating >= 60 && rating < 80){
			starRating = 4;
		}
		else{
			starRating = 5;
		}
		
		for(int i = 0; i < 5; i++){
			if(i < starRating){
				g.drawImage(new ImageIcon("images/bStar.png").getImage(), x + (20*i), y + 14, 20, 20, null);
			}
			else{
				g.drawImage(new ImageIcon("images/gStar.png").getImage(), x + (20*i), y + 14, 20, 20, null);
			}
		}
		
		Font temp = SPanel.font.deriveFont(Font.BOLD, 17f);
		
		g.setFont(temp);
		g.setColor(new Color(240,240,240));
		g.drawString("Ratings: ", x + 110, y + 30);
		
		g.drawString("Summary: ", x, y + 60);

		Font temp1 = SPanel.font.deriveFont(15f);
		
		g.setFont(temp1);
		
		g.drawString(rating + "%", x + 180, y + 30);
		
		paragraph = parseDescription(description, g);
		
		if(scroll){
			for(int i = startCount; i < startCount + 15; i++){
				g.drawString(paragraph.get(i), x, y + 80 + 16*(i - startCount));
			}
			
			g.setColor(new Color(0,0,0,100));
			
			g.fillRoundRect(x + 220, y + 70 + startCount*(240/paragraph.size()), 5, (240/(paragraph.size()/15)), 5, 5);
			
			g.setColor(new Color(0,0,0,50));
			g.fillRoundRect(x + 220, y + 70, 5, 240, 5, 5);
		}
		else{
			for(int i = 0; i < paragraph.size(); i++){
				g.drawString(paragraph.get(i), x, y + 80 + 16*i);
			}
		}
	}
}
 