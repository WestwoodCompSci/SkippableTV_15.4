package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

public class SPanel extends JPanel implements Runnable,MouseListener,MouseInputListener,MouseWheelListener,KeyListener{
	public static int width = 1040;
	public static int height = 585;
	
	private Thread thread;
	private boolean running;
	
	private BufferedImage image;
	private Graphics2D g;
	
	private final int FPS = 60;
	private int averageFPS;

	private SideBar s;
	private MainContent m;
	
	public SPanel(){
		super();
		
		running = false;
		
		this.setPreferredSize(new Dimension(width, height));
		this.setFocusable(true);
		this.requestFocus();
	}
	
	public void addNotify(){
		super.addNotify();
		if(thread == null){
			thread = new Thread(this);
			thread.start();
		}
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addMouseWheelListener(this);
		this.addKeyListener(this);
	}
	
	public void run(){
		running = true;
		
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D)image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		
		s = new SideBar(this);
		
		m = new MainContent(260, 0, "Untitled", this);
		
		long startTime;
		long URDTimeMilli;
		long waitTime;
		long totalTime = 0;
		
		int frameCount = 0;
		int maxFrameCount = 60;
		
		long targetTime = 1000/FPS;
		
		while(running){
			startTime = System.nanoTime();
			
			update();
			renderGraphics();
			paintGraphics();
			
			URDTimeMilli = (System.nanoTime() - startTime)/1000000;
			
			waitTime = targetTime - URDTimeMilli;
			
			try{
				Thread.sleep(waitTime);
			}catch(Exception e){}
			
			totalTime += System.nanoTime() - startTime;
			frameCount++;
			
			if(frameCount == maxFrameCount){
				averageFPS = (int)(1000.0/((totalTime/frameCount)/1000000));
				frameCount = 0;
				totalTime = 0;
			}
		}
	}
	
	public void update(){
		s.update();
		if(!s.isStarting()){
			m.update();
		}
	}
	
	public void renderGraphics(){
		//Background
		
		g.setColor(new Color(80,86,90));
		g.fillRect(0, 0, width, height);
		
		//SideBar
		
		m.draw(g);
		
		s.draw(g);
	}
	
	public void paintGraphics(){
		Graphics g2 = this.getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {
		GenreList temp = s.getGenreList();
		if(temp != null){
			temp.checkHovered(e);
		}
		SearchBar temp2 = m.getSearchBar();
		if(temp2 != null){
			temp2.checkTextHovered(e);
		}
		m.checkHovered(e);
		s.checkProfileHovered(e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		SearchBar temp = m.getSearchBar();
		if(temp != null){
			temp.selectText(true);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		GenreList temp = s.getGenreList();
		if(temp != null){
			temp.checkPressed(e, true);
		}
		m.checkPressed(e, true);
		s.checkProfilePressed(e, true);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		GenreList temp = s.getGenreList();
		if(temp != null){
			temp.checkPressed(e, false);
		}
		SearchBar temp1 = m.getSearchBar();
		if(temp1 != null){
			temp1.selectText(false);
		}
		m.checkPressed(e, false);
		
		s.checkProfilePressed(e, false);
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		GenreList temp = s.getGenreList();
		if(temp != null){
			int notches = e.getWheelRotation();
			if(notches < 0){
				notches = Math.abs(notches);
				for(int i = 0; i < notches; i++){
					temp.scrollUp(e);
				}
			}
			else{
				for(int i = 0; i < notches; i++){
					temp.scrollDown(e);
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		SearchBar temp = m.getSearchBar();
		if(temp != null){
			if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
				temp.backSpace();
			}
			else if (e.getKeyCode() == KeyEvent.VK_DELETE){
				
			}
			
			else if(e.getKeyCode() == KeyEvent.VK_LEFT){
				
			}
			
			else if(KeyEvent.getKeyText(e.getKeyCode()).length() == 1){
				temp.appendText(e.getKeyChar());
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}
}