//Some of this is from http://compsci.ca/v3/viewtopic.php?t=25991

package mainGameEngine;
import java.awt.*; 
import java.awt.event.KeyEvent; 
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

import levels.Helplevel;

import levels.Level;
import levels.Level1;
import mainGameEngine.InputHandler;
/**
 * Builds Game window
 */
public class MainMenu extends Level 
{        
	boolean keyDownWasDown = false;
	boolean keyUpWasDown = false;
	
	InputHandler input;
	
	final int FPS = 30;

	BufferedImage backBuffer; 
	Insets insets;


	private int currentlySelected;
	private String[] menuItems = {"Start","Help","QUIT"};

	private Font retroComputer, retroComputerBold;


	public MainMenu(StateManager sm, Graphics g){
		super(sm,g);
		
		initialize();
	}


	/** 
	 * This method will set up everything need for the game to run 
	 */ 
	public void initialize() 
	{

		
		try {
		    File mainMenuMusic = new File("res/music/Quiet Meadow.wav");
		    AudioInputStream stream;
		    AudioFormat format;
		    DataLine.Info info;
		    Clip clip;

		    stream = AudioSystem.getAudioInputStream(mainMenuMusic);
		    format = stream.getFormat();
		    info = new DataLine.Info(Clip.class, format);
		    clip = (Clip) AudioSystem.getLine(info);
		    clip.open(stream);
		    clip.loop(128);
		}
		catch (Exception e) {
			System.out.println("Exception e");
		}
		
		
		try {
			retroComputer = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("res/retroComputerFont.ttf"))).deriveFont(Font.PLAIN, 50);
			retroComputerBold = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("res/retroComputerFont.ttf"))).deriveFont(Font.BOLD, 50);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		input = sm.input;

	} 

	/** 
	 * This method will check for input, move things 
	 * around and check for win conditions, etc 
	 */ 
	public void update() 
	{
		
		//moves the selector up and down
		if (input.isKeyDown(KeyEvent.VK_UP) && !keyUpWasDown){
			keyUpWasDown = true;
			currentlySelected--;
		}else if (input.isKeyDown(KeyEvent.VK_DOWN) && !keyDownWasDown){
			keyDownWasDown = true;
			currentlySelected++;
		} else {
			keyUpWasDown = input.isKeyDown(KeyEvent.VK_UP);
			keyDownWasDown = input.isKeyDown(KeyEvent.VK_DOWN);
		}

		//loops the selector around the list
		if (currentlySelected < 0){
			currentlySelected = menuItems.length -1;
		}else if (currentlySelected > menuItems.length -1){
			currentlySelected = 0;
		}


		if (input.isKeyDown(KeyEvent.VK_ENTER)){
			if (currentlySelected == 0){
				isRunning = false;
				Level1 firstLevel = new Level1(sm,sm.getGraphics());
				sm.levels.push(firstLevel);
				
			}else if (currentlySelected == 1){
				isRunning = false;
				Helplevel helpLevel = new Helplevel(sm,sm.getGraphics());
				sm.levels.push(helpLevel);
						

			}else if (currentlySelected == 2){
				sm.levels.pop();
				isRunning = false;
			}
		}

	} 

	/** 
	 * This method will draw everything 
	 */ 
	public void draw() 
	{               
		Graphics g = sm.getGraphics(); 

		Graphics bbg = sm.backBuffer.getGraphics(); 

		bbg.setColor(Color.BLACK); 
		bbg.fillRect(0, 0, sm.WINDOW_WIDTH, sm.WINDOW_HEIGHT); 


		for (int i = 0;i < menuItems.length;i++){
			if (currentlySelected == i){
				bbg.setFont(retroComputer);
				bbg.setColor(Color.GREEN);

			}else{
				bbg.setFont(retroComputerBold);
				bbg.setColor(Color.BLUE);
			}
			bbg.drawString(menuItems[i], (sm.WINDOW_WIDTH / 2) - 50, 50 + (i * 90 + sm.WINDOW_HEIGHT/5));

		}


		g.drawImage(sm.backBuffer, sm.insets.left, sm.insets.top, sm); 
	}



	@Override
	public void drawUniverse(Graphics universe) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void drawScreen(Graphics screen) {
		// TODO Auto-generated method stub
		
	} 
} 