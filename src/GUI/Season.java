package GUI;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Season {
	private int x;
	private int y;
	
	private boolean starting;
	
	private ArrayList<Episode> episodes;
	
	private Progressbar p;
	
	private Hours h;
	
	private int startCount;
	
	public Season(int x, int y){
		this.x = x;
		this.y = y;
		
		starting = false;
		
		episodes = new ArrayList<Episode>();
		
		startCount = 0;
		
		for(int i = 0; i < 20; i++){
			if(i < 10){
				episodes.add(new Episode(x + 30, y + 170 + (int)(50*i), false, 5, true, i + 1));
			}
			else if(i < 15){
				episodes.add(new Episode(x + 30, y + 170 + (int)(50*i), false, (int)(Math.random()*4 + 1), false, i + 1));
			}
			else{
				episodes.add(new Episode(x + 30, y + 170 + (int)(50*i), true, (int)(Math.random()*4 + 1), false, i + 1));
			}
		}
		
		p = new Progressbar(x +30, y + 90, 80, 70);
		
		h = new Hours(x + 30, y + 30, 1, 30);
	}
	
	public ArrayList<Episode> getEpisodes(){
		return episodes;
	}
	
	public Hours getHours(){
		return h;
	}
	
	public void scrollUp(MouseEvent e){
		if(!starting && (e.getX() > x + 30 && e.getX() < x + 510 && e.getY() > y + 170 && e.getY() < y + 420)){
			startCount--;
			if(startCount >= 0){
				for(int i = 0; i < episodes.size(); i++){
					episodes.get(i).setY(episodes.get(i).getY() + 50);
				}
			}
			else{
				startCount = 0;
			}
		}
	}
	
	public void scrollDown(MouseEvent e){
		if(!starting && (e.getX() > x + 30 && e.getX() < x + 510 && e.getY() > y + 170 && e.getY() < y + 420)){
			startCount++;
			if(startCount <= episodes.size() - 5){
				for(int i = 0; i < episodes.size(); i++){
					episodes.get(i).setY(episodes.get(i).getY() - 50);
				}
			}
			else{
				startCount = episodes.size() - 5;
			}
		}
	}
	
	public void update(int x, int y){
		this.x = x;
		this.y = y;
		
		p.update(this.x + 30, this.y + 90);
		h.update(this.x + 30, this.y + 30);
		
		for(int i = 0; i < episodes.size(); i++){
			episodes.get(i).update(x + 30, y + 170 + (int)(50*(i - startCount)));
		}
	};
	
	public void draw(Graphics2D g){
		p.draw(g);
		h.draw(g);
		
		g.setColor(new Color(240,240,240));
		
		g.fillRoundRect(x + 30, y + 140, 480, 315, 30, 70);
		
		g.setColor(new Color(101,120,134));
		
		g.fillRect(x + 30, y + 168, 480, 2);
		
		g.fillRect(x + 30, y + 420, 480, 2);
		
		for(int i = startCount; i < startCount + 5; i++){
			episodes.get(i).draw(g);
		}
		
		g.setColor(new Color(0,0,0,100));
		g.fillRoundRect(x + 500, y + 175 + startCount*(315/episodes.size()), 5, (315/episodes.size()), 5, 5);
		
		g.setColor(new Color(0,0,0,50));
		g.fillRoundRect(x + 500, y + 175, 5, 240, 5, 5);
	};
}
