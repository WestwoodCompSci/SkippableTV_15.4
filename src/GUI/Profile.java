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
	
	public Profile(int x, int y, String name){
		this.x = x;
		this.y = y;
		
		this.name = name;
		
		profPic = new ImageIcon("images/filler.png");
	}
	
	public Profile(int x, int y, String name, ImageIcon img){
		this.x = x;
		this.y = y;
		
		this.name = name;
		
		profPic = img;
	}
	
	public void update(){
		
	}
	
	public void draw(Graphics2D g){
		g.setColor(new Color(240,240,240));
		g.setFont(new Font("Century Gothic", Font.BOLD, 24));
		g.drawString(name, x + 65, y + 33);
		
		g.setColor(new Color(7, 80, 91));
		g.fillRect(x + 50, 0, 2, 50);
		g.fillRect(x + 208, 0, 2, 50);
		
		g.drawImage(profPic.getImage(), 0, 0, 50, 50, null);
		
		g.setColor(new Color(27, 100, 111));
		g.fillRect(210, 0, 50, 50);
	}
}
