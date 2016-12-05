package levels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.glass.events.KeyEvent;

import addTerrain.Terrain;
import mainGameEngine.InputHandler;
import mainGameEngine.StateManager;

public class Level1 extends Level {
	private int left = sm.insets.left;
	private int top = sm.insets.top;
	private Image background;
	private InputHandler input = sm.input;
	private static int universeWidth = 20000;
	private static int universeHeight = 8000;
	
	private int x1 = left;
	private int y1 = top;
	
	private int x2 = left + 100;
	private int y2 = top - 30;
	
	private int xUniverse = ((x1 + x2)/2);
	private int yUniverse = ((y1 +y2)/2);
	
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
		g.drawImage(background,(int)x1 , (int)y1, sm);
		
		for (int i = 0; i < platforms.length; i++){
			platforms[i] = new Terrain(i * 40, i * 200, i * 50, i * 50 - 5, null);
		}

		
		
	}

	@Override
	public void update() {
		//player 1
		if (input.isKeyDown(KeyEvent.VK_W)){
			y1--;
		}else if (input.isKeyDown(KeyEvent.VK_S)){
			y1++;
		}if (input.isKeyDown(KeyEvent.VK_A)){
			x1--;
		}else if (input.isKeyDown(KeyEvent.VK_D)){
			x1++;
		}
		
		//player 2
		if (input.isKeyDown(KeyEvent.VK_UP)){
			y2--;
		}else if (input.isKeyDown(KeyEvent.VK_DOWN)){
			y2++;
		}if (input.isKeyDown(KeyEvent.VK_LEFT)){
			x2--;
		}else if (input.isKeyDown(KeyEvent.VK_RIGHT)){
			x2++;
		}
		
		if(input.isKeyDown(KeyEvent.VK_ESCAPE)){
			sm.levels.pop();
		}
		
		xUniverse =-((x1 + x2)/2) + (sm.WINDOW_WIDTH/2);
		yUniverse =-((y1 +y2)/2) + (sm.WINDOW_HEIGHT /2);
		
		System.out.println(x1 + " " + y1 + "  " + x2 + " " + y2 + "  " + xUniverse + " " + yUniverse);
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
		universe.drawImage(background,x1,y1,sm);
		universe.drawImage(background, x2,y2, sm);
		
		
		
	}
	
	public void drawScreen(Graphics screen){
		screen.setColor(Color.BLACK);
		screen.fillRect(left, top, sm.WINDOW_WIDTH, sm.WINDOW_HEIGHT);
		screen.drawImage(sm.backBuffer, xUniverse, yUniverse, sm);
	}

}
