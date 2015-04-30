package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

public class SPanel extends JPanel implements Runnable,MouseListener,MouseInputListener{
	public static int width = 1040;
	public static int height = 585;
	
	private Thread thread;
	private boolean running;
	
	private BufferedImage image;
	private Graphics2D g;
	
	private final int FPS = 60;
	private int averageFPS;

	private SideBar s;
	
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
	}
	
	public void run(){
		running = true;
		
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D)image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		s = new SideBar();
		
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
	}
	
	public void renderGraphics(){
		//Background
		
		g.setColor(new Color(200,130,130));
		g.fillRect(0, 0, width, height);
		
		//SideBar
		
		s.draw(g);
		
		//Test
		
		g.setColor(Color.WHITE);
		g.drawString("FPS: " + averageFPS, 20, 20);
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
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		GenreList temp = s.getGenreList(;
		if(temp != null){
			temp.checkPressed(e, true);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		GenreList temp = s.getGenreList();
		if(temp != null){
			temp.checkPressed(e, false);
		}
	}
}
