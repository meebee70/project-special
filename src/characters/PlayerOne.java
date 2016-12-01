package characters;

import java.util.Objects;

import com.sun.glass.events.KeyEvent;

import addTerrain.Terrain;
import mainGameEngine.InputHandler;
import mainGameEngine.StateManager;

public class PlayerOne {
	
	private final int BASE_X_SPEED = 1; // pixels/frame
	private final int BASE_Y_SPEED = 1;
	
	private int x, y, xSpeed, ySpeed, xVelocity, yVelocity;
	
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
		return x;
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
	public void setCurrentX(int x) {
		this.x += x;
	}

	public void setCurrentY(int y) {
		this.y += y;
	}
	
	public void setXandY(int x, int y){
		this.x = x;
		this.y = y;
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
			this.y -= BASE_Y_SPEED;
			System.out.println("W");
		}else if (input.isKeyDown(KeyEvent.VK_S)){
			yVelocity += BASE_Y_SPEED;
			System.out.println("S");
		} else {
			yVelocity = 0;
		}
		
		//Set xSpeed
		if (input.isKeyDown(KeyEvent.VK_A)){
			this.xVelocity -= BASE_X_SPEED;
			System.out.println("A");
		}else if (input.isKeyDown(KeyEvent.VK_D)){
			this.xVelocity += BASE_X_SPEED;
			System.out.println("D");
		} else {
			xVelocity = 0;
		}
		
		this.setCurrentX(xVelocity);
		this.setCurrentY(yVelocity);
		
		xVelocity = 0;
		yVelocity = 0;
	}

}
