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

import NonPlayerObjects.Coins;
import levels.Level;
import mainGameEngine.InputHandler;
import mainGameEngine.StateManager;
import physics.Physics;
import terrain.LevelEnder;
import terrain.Terrain;

public class PlayerTwo {

	//Only changes player physics (to be changed later)
	final int FPS;

	private final double BASE_X_SPEED, JUMPSPEED,GRAVITY, GROUND_POUND_SPEED, FREEZE_LENGTH_MAX; // pixels/frame
	private final int PAUSES_LEFT_MAX,JUMPSMAX, ANIMATION_SPEED;
  
	private int keyLeft, keyRight, keyUp, keyDown, keyCtrl, jumps, pausesLeft, xDirection, frame;
	private double x, y, xVelocity, yVelocity, freezeTimer, playerTwoPoints;
	private boolean keyReleasedUp, keyReleasedCtrl, inAir;

	private Image playerTwoSprite, playerTwoStationairy;
	private ArrayList<Image> playerTwoRight, playerTwoLeft, playerTwoGroundPound, playerTwoFreeze;
	
	private StateManager sm;
	
	private Level level;

	private InputHandler input;

	Physics physics = new Physics();

	final private int HEIGHT;
	final private int WIDTH;

	public PlayerTwo(int x, int y, StateManager sm, Level levelThatWeAreIn){
    
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

		this.playerTwoPoints = 0;
		this.pausesLeft = 0;
		this.freezeTimer = 0;
		this.frame = 0;
		
		this.FPS = levelThatWeAreIn.getFPS();
		
		BASE_X_SPEED = 60 / FPS; // pixels/frame
		JUMPSPEED = 2.1;
		GRAVITY = physics.getGravity();
		GROUND_POUND_SPEED = GRAVITY * 4;
		FREEZE_LENGTH_MAX = 8000 / FPS;
		PAUSES_LEFT_MAX = 1;
		JUMPSMAX = 2;
		ANIMATION_SPEED = 4;

		this.HEIGHT = 32;	//Of sprite or Hitbox
		this.WIDTH = 22; //Update later
		this.sm = sm;
		this.level = levelThatWeAreIn;
		input = sm.input;


		try {
			this.playerTwoStationairy = ImageIO.read(new File("res/PlayerSprites/Player 2.gif"));
			//this.playerTwoFreeze = ImageIO.read(new File("res/PlayerSprites/Player 2 Freeze.gif"));
			//this.playerTwoGroundPound = ImageIO.read(new File("res/PlayerSprites/Player 2 Ground Pound.gif"));
			//this.playerTwoRight = ImageIO.read(new File("res/PlayerSprites/Player 2 walk right.gif"));
			//this.playerTwoLeft = ImageIO.read(new File("res/PlayerSprites/Player 2 walk left.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		playerTwoRight = new ArrayList<Image>();
		playerTwoLeft = new ArrayList<Image>();
		playerTwoGroundPound = new ArrayList<Image>();
		playerTwoFreeze = new ArrayList<Image>();
		loadSprite(playerTwoRight, "res/PlayerSprites/Player 2 walk right.gif");
		loadSprite(playerTwoLeft, "res/PlayerSprites/Player 2 walk left.gif");
		loadSprite(playerTwoGroundPound, "res/PlayerSprites/Player 2 Ground Pound.gif");
		loadSprite(playerTwoFreeze, "res/PlayerSprites/Player 2 Freeze.gif");
		
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
			inAir = !(Physics.collides(aX, aY+1, aX2, aY2+1, form) || !inAir);
		}
		return inAir;
	}
	

	public Image getSprite(){
		return playerTwoSprite;
	}

	

	public void setSprite(Image sprite){
		this.playerTwoSprite = sprite;
	}
	public void setSprite(ArrayList<Image> sprite){
		if (freezeTimer > 0){
			this.playerTwoSprite = sprite.get((int)((this.frame / (ANIMATION_SPEED*4)) % sprite.size()));
		} else {
			this.playerTwoSprite = sprite.get((int)((this.frame / ANIMATION_SPEED) % sprite.size()));
		}
		
	}
	public void setSprite(ArrayList<Image> sprite, int i){
		this.playerTwoSprite = sprite.get(i);
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

	/**
	 * Updates Player Object while getting input and calculating new x & y
	 */
	public void updatePlayer(Terrain[] platforms, PlayerOne player1){
		this.getInputs();
		this.inAir(platforms);
		
		frame++;

		//Sets jumps
		if (!this.inAir){
			jumps = JUMPSMAX;
			pausesLeft = PAUSES_LEFT_MAX;
		}
		xDirection = this.keyLeft + this.keyRight;
		xVelocity = xDirection * this.BASE_X_SPEED;

		if (yVelocity < 10){
			yVelocity += GRAVITY;
		}

		this.outerBoundCollides();

		//Does a Ground Pound
		if (this.inAir && this.keyDown == 1 && !(freezeTimer > 0) ){
			yVelocity += GROUND_POUND_SPEED;
		}

		this.collisionCalculate(platforms,player1);

		if (this.keyUp == 1 && jumps > 0 && freezeTimer == 0){
			jumps--;
			yVelocity = -JUMPSPEED;
		}


		coinCollision();
    
		if (freezeTimer > 0){
			freezeTimer--;
			xVelocity = 0;
			yVelocity = 0;
		}
		if (keyCtrl == 1 && inAir && freezeTimer == 0 && pausesLeft > 0){
			freezeTimer = FREEZE_LENGTH_MAX;
			pausesLeft--;
			this.xVelocity = 0;
			this.yVelocity = 0;
			this.jumps = 0;

		}

		this.moveXandY(xVelocity, yVelocity);

		updateSprites();

		xVelocity = 0;

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

	private void collisionCalculate(Terrain[] platforms,PlayerOne player1){
		for (Terrain form: platforms){

			//player 1 coords
			int aX = this.getCurrentX();
			int aY2 = this.getCurrentY();
			int aX2 = aX + this.getWidth();
			int aY = aY2 + this.getHeight();

			//player 2 coords
			int bX = player1.getCurrentX() - 1;
			int bY2 = player1.getCurrentY() - 1;
			int bX2 = bX + player1.getWidth();
			int bY =  bY2 + player1.getHeight();

			//X Collision
			if (Physics.collides(aX + xVelocity, aY, aX2 + xVelocity, aY2, form)){
				while(!Physics.collides(aX + sign(xVelocity), aY, aX2 + sign(xVelocity), aY2, form)){
					this.moveX(sign(xVelocity));

					aX = this.getCurrentX();
					aX2 = aX + this.getHeight();
				}
				if (form.getClass() == LevelEnder.class){
					((LevelEnder) form).goToNextLevel();
				}
				xVelocity = 0;
			}

			if (Physics.collides(aX + xVelocity,aY,aX2 + xVelocity,aY2,bX,bY,bX2,bY2)){
				xVelocity = 0;
			}

			//Y Collision
			if (Physics.collides(aX, aY + yVelocity, aX2, aY2 + yVelocity, form)){
				while(!Physics.collides(aX, aY + sign(yVelocity), aX2, aY2 + sign(yVelocity), form))
				{
					this.moveY(sign(yVelocity));
					aY2 = this.getCurrentY();
					aY = aY2 + this.getHeight();
				}
				if (form.getClass() == LevelEnder.class){
					((LevelEnder) form).goToNextLevel();
				}
				yVelocity = 0;
			}
			if (Physics.collides(aX,aY + yVelocity,aX2,aY2 + yVelocity,bX,bY,bX2,bY2)){
				moveY(-1);
				yVelocity = 0;
			}
			
		}
	}
	//Changes Sprites Based on Movement/Actions
	public void updateSprites(){
		if (this.freezeTimer > 0){
			this.setSprite(playerTwoFreeze);
		} else if (this.inAir && this.keyDown == 1){
			this.setSprite(playerTwoGroundPound);
		} else if (this.xDirection == 1){
			this.setSprite(playerTwoRight);
		} else if (this.xDirection == -1){
			this.setSprite(playerTwoLeft);
		} else {
			this.setSprite(playerTwoStationairy);
		}
	}
	
	private void coinCollision(){
		ArrayList<Coins> listOfCoins = this.level.getCoinsList();
		
		for (Coins coin : listOfCoins){
			final double aX = this.getCurrentX();
			final double aY2 = this.getCurrentY();
			final double aX2 = aX + this.getWidth();
			final double aY = aY2 + this.getHeight();
			final double bX = coin.getCurrentX();
			final double bY2 = coin.getCurrentY();
			final double bX2 = bX + coin.getWidth();
			final double bY = bY2 + coin.getHeight();
			//Coin Collision
			if (Physics.collides(aX, aY, aX2, aY2, bX, bY, bX2, bY2) && coin.getPoints()){
				coin.setSprite(null);
				playerTwoPoints++;
				coin.givePoints();
				System.out.println(playerTwoPoints);
				}
			}
		}
}

