package levels;
import java.awt.*; 
import java.awt.event.KeyEvent; 
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

import mainGameEngine.StateManager;
/**
 * Builds Game window
 */
public class Helplevel extends Level
{        
	boolean keyDownWasDown = false;
	boolean keyUpWasDown = false;
	
	final int FPS = 30;

	BufferedImage backBuffer; 
	Insets insets;


	private int currentlySelected;
	private String[] menuItems = {
			"Player One",
			"Movement WASD",
			"Dash attack Space",
			"Hold up to wall climb",
			"Player Two",
			"Movement Arrow Keys",
			"Double Jump Up Twice",
			"Ground Pound Jump+Down",
			"Freeze Time CTRL",
			"QUIT"
			};

	private Font retroComputerHelp, retroComputerBold;


	public Helplevel(StateManager sm,Graphics g){
		super(sm,g);
		
		initialize();
	}



	
	/** 
	 * This method will set up everything need for the game to run 
	 */ 
	public void initialize() 
	{

		try {
			retroComputerHelp = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("res/retroComputerFont.ttf"))).deriveFont(Font.PLAIN, 15);
			retroComputerBold = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("res/retroComputerFont.ttf"))).deriveFont(Font.BOLD, 15);
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
			if (currentlySelected == menuItems.length - 1){
				exitLevel(100);
			}
		}

	} 



	@Override
	public void drawUniverse(Graphics universe) {
		universe.setColor(Color.BLACK); 
		universe.fillRect(0, 0, sm.WINDOW_WIDTH, sm.WINDOW_HEIGHT); 

		//Forces currentlySelected to only be 0, 3 or 6
		while(!(currentlySelected == 0 || currentlySelected == 4 || currentlySelected == menuItems.length - 1)){
			if (keyUpWasDown){
				currentlySelected--;
			} else {
				currentlySelected++;
			}
			if (currentlySelected >= menuItems.length){
				currentlySelected = 0;
			}
		}
		
		//Draws Text
		for (int i = 0;i < menuItems.length;i++){
			if (currentlySelected == i){
				universe.setFont(retroComputerBold);
				universe.setColor(Color.GREEN);
			}else{
				universe.setFont(retroComputerHelp);
				universe.setColor(Color.BLUE);
			}
			universe.drawString(menuItems[i], 80, 50 + (i * 40));

		}
		
	}



	@Override
	public void drawScreen(Graphics screen) {
		screen.drawImage(sm.backBuffer, sm.insets.left, sm.insets.top, sm); 
	} 
} 