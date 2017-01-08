package characters;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import com.sun.glass.events.KeyEvent;

import mainGameEngine.InputHandler;
import mainGameEngine.StateManager;
import physics.Physics;
import terrain.Terrain;

public class PlayerOne {
	final int FPS = 60;

	private final double BASE_X_SPEED = 60 / FPS; // pixels/frame
	private final double JUMPSPEED = 2.1; //2.65
	private final double GRAVITY = 0.9 / FPS;
	private final int JUMPSMAX = 1;
	private final int ANIMATION_SPEED = 4;

	private int keyLeft, keyRight, keyUp, keyDown, keyShift, jumps, xDirection, frame;
	private double x, y, xVelocity, yVelocity;
	private boolean inAir, keyReleasedUp, keyReleasedShift;

	private Image playerOneStationairy, playerOneSprite;
	private ArrayList<Image> playerOneRight, playerOneLeft;

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
		this.jumps = 0;
		this.frame = 0;

		this.HEIGHT = 32;	//Of sprite or Hitbox
		this.WIDTH = 32; //Update later
		this.sm = sm;
		input = sm.input;


		try {
			this.playerOneStationairy = ImageIO.read(new File("res/PlayerSprites/Player 1.png"));
			//this.playerOneRight = ImageIO.read(new File("res/PlayerSprites/Player 1 walk right.gif"));
			//this.playerOneLeft = ImageIO.read(new File("res/PlayerSprites/Player 1 walk left.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		playerOneRight = new ArrayList<Image>();
		playerOneLeft = new ArrayList<Image>();
		loadSprite(playerOneRight, "res/PlayerSprites/Player 1 walk right.gif");
		loadSprite(playerOneLeft, "res/PlayerSprites/Player 1 walk left.gif");
		

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

	public Image getSprite(){
		return playerOneSprite;
	}

	

	public void setSprite(Image sprite){
		this.playerOneSprite = sprite;
	}
	public void setSprite(ArrayList<Image> sprite){
		this.playerOneSprite = sprite.get((int)((this.frame / ANIMATION_SPEED) % sprite.size()));
		
	}
	public void setSprite(ArrayList<Image> sprite, int i){
		this.playerOneSprite = sprite.get(i);
	}
	
	private void loadSprite(ArrayList<Image> gifList, String fileName){
		try {
			ImageReader reader = ImageIO.getImageReadersByFormatName("gif").next();
			File input = new File(fileName);
			ImageInputStream stream = ImageIO.createImageInputStream(input);
			reader.setInput(stream);

			int count = reader.getNumImages(true);
			for (int index = 0; index < count; index++) {
				BufferedImage frame = reader.read(index);
				// Here you go
				gifList.add(frame);
			}
		} catch (IOException ex) {
		}
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

	//GET INPUT AND USE IT
	/**
	 * Updates Player Object while getting input and calculating new x & y
	 */
	public void updatePlayer(Terrain[] platforms){
		this.inAir(platforms);
		getInputs();
		frame++;
		
		xDirection = this.keyLeft + this.keyRight;

		xVelocity = (this.keyLeft + this.keyRight) * this.BASE_X_SPEED;
		//yVelocity = (this.keyUp + this.keyDown) * this.BASE_Y_SPEED;

		//Collision (Should we have this in a separate method?)
		if (this.x + xVelocity < 0){
			this.setCurrentX(0);
			this.xVelocity = 0;
		}
		if (this.x + xVelocity >= sm.UNIVERSE_WIDTH - WIDTH){
			this.setCurrentX(sm.UNIVERSE_WIDTH - WIDTH -1);
			this.xVelocity = 0;
		}

		//Uses collision in physics class to calculate physics for all the objects

		if (yVelocity < 10){
			yVelocity += GRAVITY;
		}
		
		this.collisionCalculate(platforms);

		if (this.keyUp == 1 && jumps > 0){
			jumps--;
			yVelocity = -JUMPSPEED;
		}

		this.moveXandY(xVelocity, yVelocity);
		
		this.updateSprites();

		xVelocity = 0;
		//yVelocity = 0;
		keyLeft = 0;
		keyRight = 0;
		keyUp = 0;


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
	
	private void getInputs(){
		if (!keyReleasedUp && input.isKeyDown(KeyEvent.VK_W)){
			this.keyUp = 1;
		}
		keyReleasedUp = input.isKeyDown(KeyEvent.VK_W);

		if (input.isKeyDown(KeyEvent.VK_S)){
			this.keyDown = 1;
		}

		//Set xSpeed
		if (input.isKeyDown(KeyEvent.VK_A)){
			this.keyLeft = -1;
		}
		if (input.isKeyDown(KeyEvent.VK_D)){
			this.keyRight = 1;
		}
		
		if (!keyReleasedShift && input.isKeyDown(KeyEvent.VK_SHIFT)){
			this.keyShift = 1;
		}
		keyReleasedShift = input.isKeyDown(KeyEvent.VK_SHIFT);
	}
	
	private void collisionCalculate(Terrain[] platforms){
		for (Terrain form: platforms){

			int aX = this.getCurrentX();
			int aY2 = this.getCurrentY();
			int aX2 = aX + this.getWidth();
			int aY = aY2 + this.getHeight();


			//X Collision
			if (physics.collides(aX + xVelocity, aY, aX2 + xVelocity, aY2, form)){
				while(!physics.collides(aX + sign(xVelocity), aY, aX2 + sign(xVelocity), aY2, form)){
					this.moveX(sign(xVelocity));

					aX = this.getCurrentX();
					aX2 = aX + this.getHeight();
				}
				xVelocity = 0;
			}

			//Y Collision
			if (physics.collides(aX, aY+1, aX2, aY2+1, form)){
				jumps = JUMPSMAX;
			}

			if (physics.collides(aX, aY + yVelocity, aX2, aY2 + yVelocity, form)){
				while(!physics.collides(aX, aY + sign(yVelocity), aX2, aY2 + sign(yVelocity), form))
				{
					this.moveY(sign(yVelocity));

					aY = this.getCurrentY();
					aY2 = aY + this.getHeight();
				}
				yVelocity = 0;
			}

			if (getCurrentY() + yVelocity < 0){
				yVelocity = 0;
			}

		}
	}

	//Changes Sprites Based on Movement/Actions
	public void updateSprites(){
		if (xDirection == 1){
			this.setSprite(playerOneRight);
		} else if (xDirection == -1){
			this.setSprite(playerOneLeft);
		} else {
			this.setSprite(playerOneStationairy);
		}
	}

}
