package mainGameEngine;

import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.util.Stack;

import javax.swing.JFrame;

import levels.Level;

public class StateManager extends JFrame {
	
	public final int WINDOW_WIDTH = 1024;
	public final int WINDOW_HEIGHT = 512;
	public Insets insets;
	public BufferedImage backBuffer;
	public InputHandler input;
	
	public Stack<Level> levels;
	
	public StateManager(){
		setTitle("Game Tutorial"); 
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT); 
		setResizable(false); 
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		setVisible(true);
		
		
		insets = getInsets(); 
		setSize(insets.left + WINDOW_WIDTH + insets.right, 
				insets.top + WINDOW_HEIGHT + insets.bottom); 

		backBuffer = new BufferedImage(WINDOW_WIDTH, WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB); 
		input = new InputHandler(this);
	}
}
