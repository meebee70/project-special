package levels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.glass.events.KeyEvent;

import characters.PlayerOne;
import characters.PlayerTwo;
import mainGameEngine.InputHandler;
import mainGameEngine.StateManager;
import terrain.Terrain;

public class Level1 extends Level {
	private int left = sm.insets.left;
	private int top = sm.insets.top;
	private Image background;
	private InputHandler input = sm.input;
	private static int universeWidth = 20000;
	private static int universeHeight = 8000;
	
	final private int INITIAL_X_P1 = 0;
	final private int INITIAL_Y_P1 = 0;
	final private int INITIAL_X_P2 = 100;
	final private int INITIAL_Y_P2 = 30;
	
	private int x2 = INITIAL_X_P2;
	private int y2 = INITIAL_Y_P2;
	
	private int xUniverse = ((INITIAL_X_P1 + x2)/2);
	private int yUniverse = ((INITIAL_Y_P1 +y2)/2);
	
	private PlayerOne playerOne;
	private PlayerTwo playerTwo;
	
	private Terrain[] platforms = new Terrain[10];
	

	public Level1(StateManager sm, Graphics g) {
		super(sm,g);
	}

	@Override
	public void initialize() {
		g.clearRect(left, top, sm.WINDOW_WIDTH,sm.WINDOW_HEIGHT);
		
		try {
			background = ImageIO.read(new File("Laughing Stock.gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(background,(int)INITIAL_X_P1 , (int)INITIAL_Y_P1, sm);
		
		for (int i = 0; i < platforms.length; i++){
			platforms[i] = new Terrain(i * 40, i * 200, i * 50, i * 50 - 5, null);
		}

		playerOne = new PlayerOne(64,64,sm);
		playerTwo = new PlayerTwo(128,64,sm);
		
	}

	@Override
	public void update() {
		playerOne.updatePlayer();
		playerTwo.updatePlayer();
		
		if(input.isKeyDown(KeyEvent.VK_ESCAPE)){
			sm.levels.pop();
		}
		
		xUniverse =-((playerOne.getCurrentX() + playerTwo.getCurrentX())/2) + (sm.WINDOW_WIDTH/2);
		yUniverse =-((playerOne.getCurrentY() +playerTwo.getCurrentY())/2) + (sm.WINDOW_HEIGHT /2);
		
		//System.out.println(x1 + " " + y1 + "  " + x2 + " " + y2 + "  " + xUniverse + " " + yUniverse);
	}

	@Override/**
	public void draw() {
		Graphics universe = sm.backBuffer.getGraphics();
		drawUniverse(universe);
		
		drawScreen(g);
	}
	*/
	
	public void drawUniverse(Graphics universe){
		universe.setColor(Color.PINK);
		universe.fillRect(0,0,universeWidth,universeHeight);
		universe.setColor(Color.GREEN);
		for (Terrain thing: platforms){
			universe.fillRect(thing.getX(), thing.getY(), thing.WIDTH, thing.HEIGHT);
		}
		universe.drawImage(background,playerOne.getCurrentX(),playerOne.getCurrentY(),sm);
		universe.drawImage(background,playerTwo.getCurrentX(),playerTwo.getCurrentY(),sm);
		
		
		
	}
	
	public void drawScreen(Graphics screen){
		screen.setColor(Color.BLACK);
		screen.fillRect(left, top, sm.WINDOW_WIDTH, sm.WINDOW_HEIGHT);
		screen.drawImage(sm.backBuffer, xUniverse, yUniverse, sm);
	}

}
