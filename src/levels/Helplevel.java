package levels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

import mainGameEngine.InputHandler;
import mainGameEngine.StateManager;

public class Helplevel extends Level
{     
	boolean isRunning = true;
	boolean keyDownWasDown = false;
	boolean keyUpWasDown = false;
	private StateManager sm;
	
	InputHandler input;
	
	final int FPS = 30;

	BufferedImage backBuffer; 
	Insets insets;


	private int currentlySelected;
	private String[] menuItems = {"Player1", "Movement: (W,A,S,D) Dash attack: (Space) Hold up to wall climb",
			"Player2", "Movement: (Arrow Keys) Double Jump: (Up Twice) Ground Pound: (Double Jump, Down) Freeze Time: ()",
			"QUIT"};

	private Font retroComputer, retroComputerBold;


	public Helplevel(StateManager sm, Graphics g){
		super(sm , g);
		}



	/** 
	 * This method starts the game and runs it in a loop 
	 */ 
	public void run() 
	{ 
		initialize(); 

		while(isRunning) 
		{ 
			long time = System.currentTimeMillis(); 

			update(); 
			draw(); 

			//  delay for each frame  -   time it took for one frame 
			time = (1000 / FPS) - (System.currentTimeMillis() - time); 
			System.out.println("test");

			if (time > 0) 
			{ 
				try 
				{ 
					Thread.sleep(time); 
				} 
				catch(Exception e){} 
			} 
		}  
	} 

	/** 
	 * This method will set up everything need for the game to run 
	 */ 
	public void initialize() 
	{

		g.clearRect(0, 0, sm.WINDOW_WIDTH, sm.WINDOW_HEIGHT);
		
		try {
		    File HelplevelMusic = new File("Blitz.wav");
		    AudioInputStream stream;
		    AudioFormat format;
		    DataLine.Info info;
		    Clip clip;

		    stream = AudioSystem.getAudioInputStream(HelplevelMusic);
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
			retroComputer = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("retroComputerFont.ttf"))).deriveFont(Font.PLAIN, 50);
			retroComputerBold = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("retroComputerFont.ttf"))).deriveFont(Font.BOLD, 50);
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

			}else if (currentlySelected == 2){
				isRunning = false;
			}
		}

	} 
	

	public void drawUniverse(Graphics universe) {
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
	}



	@Override
	public void drawScreen(Graphics screen) {
		g.drawImage(sm.backBuffer, sm.insets.left, sm.insets.top, sm);
	} 
}