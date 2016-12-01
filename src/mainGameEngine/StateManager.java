package mainGameEngine;

import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.util.Stack;

import javax.swing.JFrame;

import levels.Level;

public class StateManager extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final int WINDOW_WIDTH = 1024;
	public final int WINDOW_HEIGHT = 512;
	public final int UNIVERSE_WIDTH = 2000;
	public final int UNIVERSE_HEIGHT = 1500;
	public Insets insets;
	public BufferedImage backBuffer;
	public InputHandler input;
	
	public Stack<Level> levels = new Stack<Level>();
	
	public StateManager(){
		setTitle("Game Tutorial"); 
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT); 
		setResizable(false); 
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		setVisible(true);
		
		
		insets = getInsets(); 
		setSize(insets.left + WINDOW_WIDTH + insets.right, 
				insets.top + WINDOW_HEIGHT + insets.bottom); 

		backBuffer = new BufferedImage(UNIVERSE_WIDTH, UNIVERSE_HEIGHT, BufferedImage.TYPE_INT_RGB); 
		input = new InputHandler(this);
	}
}
