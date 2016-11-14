package Game;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable, KeyListener {
	private static final long serialVersionUID = 1l;
	
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 600;
	
	
	private Thread thread;
	private boolean isRunning = false;
	
	public GamePanel(){
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		
		start();
	}
	
	private void start(){
		isRunning = true;
		thread = new thread(this);
		thread.start();
	}
	
	public void run(){
		
	}
	
	
	public void keyPressed(KeyEvent e) {

		
	}
	public void keyReleased(KeyEvent e) {

		
	}
	public void keyTyped(KeyEvent e) {

		
	}

}
