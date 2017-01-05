package characters;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.glass.events.KeyEvent;

import mainGameEngine.InputHandler;
import mainGameEngine.StateManager;
import physics.Physics;
import terrain.Terrain;

public class PlayerTwo {
	//Only changes player physics (to be changed later)
	final int FPS = 60;

	private final double BASE_X_SPEED = 60 / FPS; // pixels/frame
	private final double JUMPSPEED = 2.2;
	private final double GRAVITY = 0.9 / FPS;
	private final double GROUND_POUND_SPEED = GRAVITY * 4;
	private final double PAUSE_LENGTH_MAX = 280 / FPS;
	private final int JUMPSMAX = 2;

	private int keyLeft, keyRight, keyUp, keyDown, keyCtrl, jumps;
	private double x, y, xVelocity, yVelocity, inAirTimer;
	private boolean keyReleasedUp, keyReleasedCtrl, inAir;

	private Image sprite;

	private StateManager sm;

	private InputHandler input;

	Physics physics = new Physics();

	final private int HEIGHT;
	final private int WIDTH;

	public PlayerTwo(int x, int y, StateManager sm){
		this.x = x;
		this.y = y;
		this.xVelocity = 0;
		this.yVelocity = 0;
		this.keyLeft = 0;
		this.keyRight = 0;
		this.keyUp = 0;
		this.keyDown = 0;
		this.keyCtrl = 0;
		this.jumps = 0;
		this.inAirTimer = 0;

		this.HEIGHT = 32;	//Of sprite or Hitbox
		this.WIDTH = 26; //Update later
		this.sm = sm;
		input = sm.input;


		try {
			this.sprite = ImageIO.read(new File("res/PlayerSprites/Player 2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	//ACCESSORS
	public int getCurrentX() {
		return (int) x;
	}

	public int getCurrentY() {
		return (int) y;
	}

	public int getHeight() {
		return HEIGHT;
	}

	public int getWidth() {
		return WIDTH;
	}
	
	public boolean inAir(Terrain[] platforms){
		inAir = true;
		for (Terrain form: platforms){

			int aX = this.getCurrentX();
			int aY2 = this.getCurrentY();
			int aX2 = aX + this.getWidth();
			int aY = aY2 + this.getHeight();
			inAir = !(physics.collides(aX, aY+1, aX2, aY2+1, form) || !inAir);
		}
		return inAir;
	}

	public Image getSprite(){
		return sprite;
	}

	//MUTATORS
	//@Param = new x and/or y coord
	public void setCurrentX(double newX) {
		this.x = newX;
	}
	
	public void setCurrentY(double newY) {
		this.y = newY;
	}
	public void setXandY(double newX, double newY){
		this.x = newX;
		this.y = newY;
	}

	//Adds @Param to x and/or y coord
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

	//GET INPUT AND USE IT
	/**
	 * Updates Player Object while getting input and calculating new x & y
	 */
	public void updatePlayer(Terrain[] platforms){
		this.getInputs();

		xVelocity = (this.keyLeft + this.keyRight) * this.BASE_X_SPEED;

		if (yVelocity < 10){
			yVelocity += GRAVITY;
		}
		
		this.outerBoundCollides();

		//Tests if Player is in the Air or not
		if (this.inAir(platforms) && this.keyDown == 1){
			yVelocity += GROUND_POUND_SPEED;
			System.out.println("Ground Pound");
		}

		this.collisionCalculate(platforms);

		if (this.keyUp == 1 && jumps > 0){
			jumps--;
			yVelocity = -JUMPSPEED;
		}
		
		if (inAirTimer > 0){
			inAirTimer--;
			xVelocity = 0;
			yVelocity = 0;
		} else if (keyCtrl == 1 && inAir && inAirTimer == 0){
			inAirTimer = PAUSE_LENGTH_MAX;
			xVelocity = 0;
			yVelocity = 0;
		}
		
		this.moveXandY(xVelocity, yVelocity);

		xVelocity = 0;
		//yVelocity = 0;
		keyLeft = 0;
		keyRight = 0;
		keyUp = 0;
		keyDown = 0;
		keyCtrl = 0;




	}
	private int sign(double velocity){
		if (velocity > 0){
			return 1;
		} else if (velocity < 0){
			return -1;
		} else {
			return 0;
		}
	}
	
	private void outerBoundCollides(){
		if (this.x + xVelocity < 0){
			this.setCurrentX(0);
			this.xVelocity = 0;
		}else if (this.x + xVelocity >= sm.UNIVERSE_WIDTH - WIDTH){
			this.setCurrentX(sm.UNIVERSE_WIDTH - WIDTH -1);
			this.xVelocity = 0;
		}
		
		if (getCurrentY() + yVelocity < 0){
			yVelocity = 0;
		}else if (getCurrentY() + yVelocity > sm.UNIVERSE_HEIGHT){
			yVelocity = 0;
			y = sm.UNIVERSE_HEIGHT - 50;
		}
	}
	
	private void getInputs(){
		if (!keyReleasedUp && input.isKeyDown(KeyEvent.VK_UP)){
			this.keyUp = 1;
		}
		keyReleasedUp = input.isKeyDown(KeyEvent.VK_UP);

		if (input.isKeyDown(KeyEvent.VK_DOWN)){
			this.keyDown = 1;
		}

		//Set xSpeed
		if (input.isKeyDown(KeyEvent.VK_LEFT)){
			this.keyLeft = -1;
		}
		if (input.isKeyDown(KeyEvent.VK_RIGHT)){
			this.keyRight = 1;
		}
		
		if (!keyReleasedCtrl && input.isKeyDown(KeyEvent.VK_CONTROL)){
			this.keyCtrl = 1;
		}
		keyReleasedCtrl = input.isKeyDown(KeyEvent.VK_CONTROL);
	}
	
	private void collisionCalculate(Terrain[] platforms){
		for (Terrain form: platforms){

			int aX = this.getCurrentX();
			int aY2 = this.getCurrentY();
			int aX2 = aX + this.getWidth();
			int aY = aY2 + this.getHeight();
			//final int bX = form.getX();
			//final int bY2 = form.getY();
			//final int bX2 = bX + form.getWidth();
			//final int bY = bY2 + form.getHeight();

			//X Collision
			if (physics.collides(aX + xVelocity, aY, aX2 + xVelocity, aY2, form)){
				while(!physics.collides(aX + sign(xVelocity), aY, aX2 + sign(xVelocity), aY2, form)){
					this.moveX(sign(xVelocity));

					aX = this.getCurrentX();
					aX2 = aX + this.getHeight();
				}
				xVelocity = 0;
			}
			
			//Sets jumps
			if (physics.collides(aX, aY+1, aX2, aY2+1, form)){
				jumps = JUMPSMAX;
				System.out.println("Jumps Max");
			}
			
			//Y Collision
			if (physics.collides(aX, aY + yVelocity, aX2, aY2 + yVelocity, form)){
				while(!physics.collides(aX, aY + sign(yVelocity), aX2, aY2 + sign(yVelocity), form))
				{
					this.moveY(sign(yVelocity));
					aY2 = this.getCurrentY();
					aY = aY2 + this.getHeight();
				}
				yVelocity = 0;
			}

		}
	}


}
