package levels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.glass.events.KeyEvent;

import camera.Camera;
import characters.PlayerOne;
import characters.PlayerTwo;
import mainGameEngine.InputHandler;
import mainGameEngine.StateManager;
import terrain.Terrain;

public class Level1 extends Level {
	private int left = sm.insets.left;
	private int top = sm.insets.top;
	private InputHandler input = sm.input;
	
	final private int INITIAL_X_P1 = 300;
	final private int INITIAL_Y_P1 = 0;
	final private int INITIAL_X_P2 = 100;
	final private int INITIAL_Y_P2 = 30;
	
	private PlayerOne playerOne;
	private PlayerTwo playerTwo;
	
	private Terrain[] platforms = new Terrain[10];
	
	private Camera cam = new Camera(sm.getGraphics(),sm , -50 , -50 , 1000 , 500);
	

	public Level1(StateManager sm, Graphics g) {
		super(sm,g);
	}

	@Override
	public void initialize() {
		g.clearRect(left, top, sm.WINDOW_WIDTH,sm.WINDOW_HEIGHT);
		//g.drawImage(sprPlayerOne,(int)INITIAL_X_P1 , (int)INITIAL_Y_P1, sm);
		
		for (int i = 0; i < platforms.length; i++){
			platforms[i] = new Terrain(i * 400, i * 80, 300, 300, null);
		}
		platforms[0] = new Terrain(0, 460, sm.UNIVERSE_WIDTH,sm.UNIVERSE_HEIGHT, null);

		playerOne = new PlayerOne(INITIAL_X_P1,INITIAL_Y_P1,sm);
		playerTwo = new PlayerTwo(INITIAL_X_P2,INITIAL_Y_P2,sm);
		
	}

	@Override
	public void update() {
		playerOne.updatePlayer(platforms);
		playerTwo.updatePlayer(platforms);
		
		if(input.isKeyDown(KeyEvent.VK_ESCAPE)){
			sm.levels.pop();
		}
		//System.out.println(x1 + " " + y1 + "  " + x2 + " " + y2 + "  " + xUniverse + " " + yUniverse);
		
		cam.move(playerOne.getCurrentX(),playerTwo.getCurrentX(),playerOne.getCurrentY(),playerTwo.getCurrentY());
	}
	
	public void drawUniverse(Graphics universe){
		universe.setColor(Color.PINK);
		universe.fillRect(0,0,sm.UNIVERSE_WIDTH,sm.UNIVERSE_HEIGHT);
		universe.setColor(Color.GREEN);
		for (Terrain thing: platforms){
			universe.fillRect(thing.getX(), thing.getY(), thing.WIDTH, thing.HEIGHT);
		}
		universe.drawImage(playerOne.getSprite(),playerOne.getCurrentX(),playerOne.getCurrentY(),sm);
		universe.drawImage(playerTwo.getSprite(),playerTwo.getCurrentX(),playerTwo.getCurrentY(),sm);
	}
	
	public void drawScreen(Graphics screen){
		screen.setColor(Color.BLACK);
		screen.fillRect(left, top, sm.WINDOW_WIDTH, sm.WINDOW_HEIGHT);
		cam.drawImage(sm.backBuffer);
	}

}
