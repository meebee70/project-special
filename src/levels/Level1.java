package levels;

import java.awt.Color;
import java.awt.Graphics;

import java.util.ArrayList;

import java.awt.Image;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;

import NonPlayerObjects.Coins;
import camera.Camera;
import characters.PlayerOne;
import characters.PlayerTwo;
import mainGameEngine.StateManager;
import nonPlayerCharacters.LaughingStock;
import terrain.LevelEnder;
import terrain.Terrain;

public class Level1 extends Level {
	private int left = sm.insets.left;
	private int top = sm.insets.top;
	
	final private int INITIAL_X_P1 = 0;
	final private int INITIAL_Y_P1 = 0;
	final private int INITIAL_X_P2 = 64;
	final private int INITIAL_Y_P2 = 0;
	final private int INITIAL_X_Coin = 128;
	final private int INITIAL_Y_Coin = 128;
	
	private Coins coin;
	private PlayerOne playerOne;
	private PlayerTwo playerTwo;
	
	private LevelEnder portal;
	
	private Image taintedGround;
	
	private Terrain[] platforms = new Terrain[10];
	
	private Camera cam = new Camera(sm.getGraphics(),sm , 0 , 0 , 1000 , 500);
	
	private ArrayList<Coins> listOfCoins = new ArrayList<Coins>();
	
	private LaughingStock enemy1 = new LaughingStock(sm, this, 400, 10);

	public Level1(StateManager sm, Graphics g) {
		super(sm,g);
		
		initialize();
	}

	@Override
	public void initialize() {
		g.clearRect(left, top, sm.WINDOW_WIDTH,sm.WINDOW_HEIGHT);
		this.listOfCoins = listOfCoins;
		
		try {
			taintedGround = ImageIO.read(new File("res/Tainted Ground v1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		for (int i = 2; i < platforms.length; i++){
			platforms[i] = new Terrain(i * 400, i * 80, 300, 300, taintedGround);
		}
		platforms[0] = new Terrain(0, 460, sm.UNIVERSE_WIDTH/4,sm.UNIVERSE_HEIGHT/4, taintedGround);
		platforms[1] = new Terrain(500, 600, 300,300, taintedGround);
		platforms[2] = new Terrain(800,460,sm.UNIVERSE_WIDTH/4,sm.UNIVERSE_HEIGHT/4, taintedGround);
		
		platforms[8] = new Terrain(-8, -8, 4, 4, null);
		platforms[9] = new Terrain(-8, -8, 4, 4, null);

		playerOne = new PlayerOne(INITIAL_X_P1,INITIAL_Y_P1,sm, this);
		playerTwo = new PlayerTwo(INITIAL_X_P2,INITIAL_Y_P2,sm, this);
		coin = new Coins(INITIAL_X_Coin,INITIAL_Y_Coin,sm, this);
		
		try {
			portal = new LevelEnder(1800,200,14,80,
				ImageIO.read(new File("res/Dimensional Tear.gif")),
				new Helplevel(sm, g),
				sm
				);
		} catch (IOException e) {e.printStackTrace();}
		platforms [7] = portal;
	}

	@Override
	public void update() {
		playerOne.updatePlayer(platforms,playerTwo);
		playerTwo.updatePlayer(platforms,playerOne);
		
		enemy1.compute(platforms);
		
		//System.out.println(x1 + " " + y1 + "  " + x2 + " " + y2 + "  " + xUniverse + " " + yUniverse);
		
		cam.move(playerOne.getCurrentX(),playerTwo.getCurrentX(),playerOne.getCurrentY(),playerTwo.getCurrentY());
		platforms[platforms.length - 2] = new Terrain(cam.getX() - 20, cam.getY(), 4, 4000, taintedGround);
		platforms[platforms.length - 1] = new Terrain(cam.getX() + sm.WINDOW_WIDTH - 20, cam.getY(), 4, 4000, taintedGround);
	}
	
	public void drawUniverse(Graphics universe){
		universe.setColor(Color.PINK);
		universe.fillRect(0,0,sm.UNIVERSE_WIDTH,sm.UNIVERSE_HEIGHT);
		universe.setColor(Color.GREEN);
		for (Terrain thing: platforms){
			universe.drawImage(thing.getSprite(), thing.getX(), thing.getY(), thing.WIDTH, thing.HEIGHT, sm);
		}
		universe.drawImage(playerOne.getSprite(),playerOne.getCurrentX(),playerOne.getCurrentY(),sm);
		universe.drawImage(playerTwo.getSprite(),playerTwo.getCurrentX(),playerTwo.getCurrentY(),sm);

		universe.drawImage(coin.getSprite(),coin.getCurrentX(),coin.getCurrentY(),sm);
    
		universe.drawImage(enemy1.getSprite(),(enemy1.getX()), enemy1.getY(), sm);
	}
	
	public void drawScreen(Graphics screen){
		screen.setColor(Color.BLACK);
		screen.fillRect(left, top, sm.WINDOW_WIDTH, sm.WINDOW_HEIGHT);
		cam.drawImage(sm.backBuffer);
	}

}
