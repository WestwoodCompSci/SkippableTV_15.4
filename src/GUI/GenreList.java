package GUI;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GenreList {
	private ArrayList<GenreButton> gButtons;
	
	private int x;
	private int y;
	
	private int startIndex;
	
	private boolean starting;
	private int startAdd;
	
	public GenreList(int x, int y){
		startIndex = 0;
		
		this.x = x;
		this.y = y;
		
		gButtons = new ArrayList<GenreButton>();
		
		starting = true;
		startAdd = 0;
	}
	
	public void update(){
		if(starting){
			if(startAdd > 40){
				starting = false;
				for(int i = 0; i < 5; i++){
					gButtons.add(new GenreButton(startAdd + "", x, y + GenreButton.height*(startAdd/10) + (i+1)*GenreButton.height));
				}
			}
			else{
				if(startAdd%10 == 0){
					gButtons.add(new GenreButton(startAdd + "", x, y + GenreButton.height*(startAdd/10)));
					if(startAdd == 0){
						gButtons.get(0).setSelected(true);
					}
				}
				startAdd++;
			}
		}
		
		for(int i = 0; i < gButtons.size(); i++){
			gButtons.get(i).update();
		}
	}
	
	public void draw(Graphics2D g){
		for(int i = 0; i < gButtons.size(); i++){
			gButtons.get(i).draw(g);
		}
		
	}
	
	public void checkHovered(MouseEvent e){
		for(int i = 0; i < gButtons.size(); i++){
			boolean hover = gButtons.get(i).isHovered(e);
			if(hover){
				gButtons.get(i).setHovered(true);
			}
			else{
				gButtons.get(i).setHovered(false);
			}
		}
	}
	
	public void checkPressed(MouseEvent e, boolean b){
		for(int i = 0; i < gButtons.size(); i++){
			boolean hover = gButtons.get(i).isHovered(e);
			if(hover){
				gButtons.get(i).setPressed(b);
				if(!b){
					for(int j = 0; j < gButtons.size(); j++){
						if(j != i){
							gButtons.get(j).setSelected(false);
						}
						else{
							gButtons.get(j).setSelected(true);
						}
					}
				}
				break;
			}
		}
	}
	
	public void scrollUp(){
		startIndex--;
		if(startIndex >= 0){
			for(int i = 0; i < gButtons.size(); i++){
				gButtons.get(i).setY(gButtons.get(i).getY() + GenreButton.height);
			}
		}
		else{
			startIndex = 0;
		}
	}
	public void scrollDown(){
		startIndex++;
		if(startIndex < gButtons.size()-5){
			for(int i = 0; i < gButtons.size(); i++){
				gButtons.get(i).setY(gButtons.get(i).getY() - GenreButton.height);
			}
		}
		else{
			startIndex = gButtons.size()-6;
		}
	}
}