package characters;

import com.sun.glass.events.KeyEvent;

import mainGameEngine.InputHandler;
import mainGameEngine.StateManager;
import physics.Physics;
import terrain.Terrain;

public class PlayerOne {
	final int FPS = 60;

	private final double BASE_X_SPEED = 60 / FPS; // pixels/frame
	private final double BASE_Y_SPEED = 60 / FPS;
	private final double JUMPSPEED = 4;
	private final double GRAVITY = 0.2 / FPS;
	private final int JUMPSMAX = 2;

	private int keyLeft, keyRight, keyUp, keyDown, jumps;
	private double x, y, xVelocity, yVelocity;

	private String sprite;

	private StateManager sm;

	private InputHandler input;

	Physics physics = new Physics();

	final private int HEIGHT;
	final private int WIDTH;

	public PlayerOne(int x, int y, StateManager sm){
		this.x = x;
		this.y = y;
		this.xVelocity = 0;
		this.yVelocity = 0;
		this.keyLeft = 0;
		this.keyRight = 0;
		this.keyUp = 0;
		this.keyDown = 0;
		this.jumps = 0;

		this.HEIGHT = 32;	//Of sprite or Hitbox
		this.WIDTH = 32; //Update later
		this.sm = sm;
		input = sm.input;


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

		//Set ySpeed
		if (input.isKeyDown(KeyEvent.VK_W)){
			this.keyUp = 1;
			//System.out.println("W");
		}
		if (input.isKeyDown(KeyEvent.VK_S)){
			this.keyDown = -1;
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
		//yVelocity = (this.keyUp + this.keyDown) * this.BASE_Y_SPEED;

		//Collision (Should we have this in a separate method?)
		/*if (this.x + xVelocity < 0){
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
		}
		if (this.getCurrentY() >= sm.UNIVERSE_HEIGHT - HEIGHT){
			this.setCurrentY(sm.UNIVERSE_HEIGHT - HEIGHT -1);
			this.yVelocity = 0;
		}*/

		//Uses collision in physics class to calculate physics for all the objects
		for (Terrain form: platforms){
			
			int aX = this.getCurrentX();
			int aY2 = this.getCurrentY();
			int aX2 = aX + this.getWidth();
			int aY = aY2 + this.getHeight();
			final int bX = form.getX();
			final int bY2 = form.getY();
			final int bX2 = bX + form.getWidth();
			final int bY = bY2 + form.getHeight();
			
			//X Collision
			if (physics.collides((int)(aX + xVelocity), aY, (int)(aX2 + xVelocity), aY2, bX, bY, bX2, bY2)){
				while(!physics.collides(aX + sign(xVelocity), aY, aX2 + sign(xVelocity), aY2, bX, bY, bX2, bY2)){
					this.moveX(sign(xVelocity));
					
					aX = this.getCurrentX();
					aX2 = aX + this.getHeight();
				}
				xVelocity = 0;
			}
			
			//Y Collision
			if (yVelocity < 10){
				yVelocity += GRAVITY;
			}
			
			if (physics.collides(aX, aY+1, aX2, aY2+1, bX, bY, bX2, bY2)){
				jumps = JUMPSMAX;
			}
			
			if (this.keyUp == 1 && jumps > 0){
				jumps -= 1;
				yVelocity = -JUMPSPEED;
			}
			
			if (physics.collides(aX, (int)(aY + yVelocity), aX2, (int)(aY2 + yVelocity), bX, bY, bX2, bY2)){
			    while(!physics.collides(aX, (int)(aY + sign(yVelocity)), aX2, (int)(aY2 + sign(yVelocity)), bX, bY, bX2, bY2))
			    {
			        this.moveY(sign(yVelocity));
			        
			        aY = this.getCurrentX();
					aY2 = aY + this.getHeight();
			    }
			    yVelocity = 0;
			}
			
		}
		
		this.moveXandY(xVelocity, yVelocity);

		xVelocity = 0;
		//yVelocity = 0;
		keyLeft = 0;
		keyRight = 0;
		keyUp = 0;
		keyDown = 0;


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

}
