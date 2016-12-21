package mainGameEngine;

import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import levels.Level;
import physics.Physics;

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
	public Physics physics;
	
	private Image img;
	
	public Stack<Level> levels = new Stack<Level>();
	
	public StateManager(){
		setTitle("Project Helios"); 
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT); 
		setResizable(false); 
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		
		try{
			img = ImageIO.read(new File("res/Laughing Stock.gif"));
		}catch (Exception e){}
		setIconImage(img);
		
		this.setVisible(true);
		
		
		insets = getInsets(); 
		setSize(insets.left + WINDOW_WIDTH + insets.right, 
				insets.top + WINDOW_HEIGHT + insets.bottom); 

		backBuffer = new BufferedImage(UNIVERSE_WIDTH, UNIVERSE_HEIGHT, BufferedImage.TYPE_INT_RGB); 
		input = new InputHandler(this);
		physics = new Physics();
	}

	
	public int getWindowHeight(){
		return this.WINDOW_HEIGHT;
	}
	
	public int getWindowWidth(){
		return this.WINDOW_WIDTH;
	}
	
	public int getUniverseHeight(){
		return this.UNIVERSE_HEIGHT;
	}
	
	public int getUniverseWidth(){
		return this.UNIVERSE_WIDTH;
	}
	
	public Insets getOurInsets(){
		return this.insets;
	}
	
	public BufferedImage getUniverse(){
		return this.backBuffer;
	}
	
	public InputHandler getInputHandler(){
		return this.input;
	}
	
	public Physics getPhysics(){
		return this.physics;
	}
	
	public Stack<Level> getLevels(){
		return this.levels;
	}
}
