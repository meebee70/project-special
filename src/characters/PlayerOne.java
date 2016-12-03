package characters;

import java.util.Objects;

import com.sun.glass.events.KeyEvent;

import addTerrain.Terrain;
import mainGameEngine.InputHandler;
import mainGameEngine.StateManager;

public class PlayerOne {
	
	private final double BASE_X_SPEED = 1; // pixels/frame
	private final double BASE_Y_SPEED = 1.5;
	
	private int keyLeft, keyRight, keyUp, keyDown;
	private double x, y, xSpeed, ySpeed, xVelocity, yVelocity;
	
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
		return (int) x;
	}

	public int getCurrentY() {
		return (int) y;
	}
	
	public long getHeight() {
		return HEIGHT;
	}

	public long getWidth() {
		return WIDTH;
	}
	
	//Mutators
	public void setCurrentX(double xVelocity) {
		this.x = xVelocity;
	}

	public void setCurrentY(double yVelocity) {
		this.y = yVelocity;
	}
	
	public void setXandY(double xVelocity, double yVelocity){
		this.x = xVelocity;
		this.y = yVelocity;
	}
	
	public void moveX(double xVelocity) {
		this.x += xVelocity;
	}

	public void moveY(double yVelocity) {
		this.y += yVelocity;
	}
	
	public void moveXandY(double xVelocity, double yVelocity){
		this.x += xVelocity;
		this.y += yVelocity;
	}

	public void setVelocity(double xSpeed, double ySpeed){
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}
	public void setVelocityX(double xSpeed) {
		this.xSpeed = xSpeed;
	}

	public void setVelocityY(double ySpeed) {
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
		
		//Collision
		if (this.x + xVelocity < 0){
			this.setCurrentX(0);
			this.xVelocity = 0;
		}
		if (this.getCurrentY() < 0){
			this.setCurrentY(0);
			this.yVelocity = 0;
		}
		if (this.x + xVelocity >= sm.UNIVERSE_WIDTH - WIDTH){
			this.setCurrentX(sm.UNIVERSE_WIDTH - WIDTH -1);
			this.xVelocity = 0;
			System.out.println("End of line");
		}
		if (this.getCurrentY() >= sm.UNIVERSE_HEIGHT - HEIGHT){
			this.setCurrentY(sm.UNIVERSE_HEIGHT - HEIGHT -1);
			this.yVelocity = 0;
			System.out.println("End of line");
		}
		
		this.moveXandY(xVelocity, yVelocity);
		
		xVelocity = 0;
		yVelocity = 0;
		keyLeft = 0;
		keyRight = 0;
		keyUp = 0;
		keyDown = 0;
	}

}
