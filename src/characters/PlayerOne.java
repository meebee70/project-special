package characters;

import java.util.Objects;

import com.sun.glass.events.KeyEvent;

import addTerrain.Terrain;
import mainGameEngine.InputHandler;
import mainGameEngine.StateManager;

public class PlayerOne {
	
	private final int BASE_X_SPEED = 1; // pixels/frame
	private final int BASE_Y_SPEED = 1;
	
	private int x, y, xSpeed, ySpeed, xVelocity, yVelocity, keyLeft, keyRight, keyUp, keyDown;
	
	private String sprite;
	
	private StateManager sm;
	
	private InputHandler input;
	
	final private int HEIGHT;
	final private int WIDTH;
	
	public PlayerOne(int x, int y, StateManager sm){
		this.x = x;
		this.y = y;
		this.xSpeed = BASE_X_SPEED;
		this.ySpeed = BASE_Y_SPEED;
		this.xVelocity = 0;
		this.yVelocity = 0;
		this.keyLeft = 0;
		this.keyRight = 0;
		this.keyUp = 0;
		this.keyDown = 0;
		
		this.HEIGHT = 32;	//Of sprite or Hitbox
		this.WIDTH = 32;	//Update later
		this.sm = sm;
		input = sm.input;
		
	}

	public void collide(Terrain[] objects){
		System.out.println(Objects.class);
	}
	
	//Accessors
	public int getCurrentX() {
		return x;
	}

	public int getCurrentY() {
		return y;
	}
	
	public int getVelocityX() {
		return xSpeed;
	}
	
	public int getVelocityY() {
		return ySpeed;
	}
	
	public long getHeight() {
		return HEIGHT;
	}

	public long getWidth() {
		return WIDTH;
	}
	
	//Mutators
	public void setCurrentX(int xVelocity) {
		this.x += xVelocity;
	}

	public void setCurrentY(int yVelocity) {
		this.y += yVelocity;
	}
	
	public void setXandY(int xVelocity, int yVelocity){
		this.x += xVelocity;
		this.y += yVelocity;
	}

	public void setVelocity(int xSpeed, int ySpeed){
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}
	public void setVelocityX(int xSpeed) {
		this.xSpeed = xSpeed;
	}

	public void setVelocityY(int ySpeed) {
		this.ySpeed = ySpeed;
	}
	
	//GET INPUT AND USE IT
	
	public void updatePlayer(){
		
		//Set ySpeed
		if (input.isKeyDown(KeyEvent.VK_W)){
			this.keyUp = -1;
			//System.out.println("W");
		}
		if (input.isKeyDown(KeyEvent.VK_S)){
			this.keyDown = 1;
			//System.out.println("S");
		}
		
		//Set xSpeed
		if (input.isKeyDown(KeyEvent.VK_A)){
			this.keyLeft = -1;
			//System.out.println("A");
		}
		if (input.isKeyDown(KeyEvent.VK_D)){
			this.keyRight = 1;
			//System.out.println("D");
		}
		
		xVelocity = (this.keyLeft + this.keyRight) * this.BASE_X_SPEED;
		yVelocity = (this.keyUp + this.keyDown) * this.BASE_Y_SPEED;
		
		this.setXandY(xVelocity, yVelocity);
		
		xVelocity = 0;
		yVelocity = 0;
		keyLeft = 0;
		keyRight = 0;
		keyUp = 0;
		keyDown = 0;
	}

}
