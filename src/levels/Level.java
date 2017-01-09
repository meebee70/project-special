package levels;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import mainGameEngine.InputHandler;
import mainGameEngine.StateManager;

public abstract class Level {
	
	public Graphics g;
	public boolean isRunning = true;
	private final int FPS = 60;
	public StateManager sm;
	public InputHandler input;
	
	public Level( StateManager sm, Graphics g){
		this.sm = sm;
		this.g = sm.getGraphics();
		this.input = sm.getInputHandler();
		
	}
	
	public void run(){
		
		//initialize();
		
		long time = System.currentTimeMillis();
		
		while (isRunning){
			
			if (exitIsPressed()){
				exitLevel(250);
			}
			
			update();
			draw();
			
			
		//  delay for each frame  -   time it took for one frame 
					time = (1000 / FPS) - (System.currentTimeMillis() - time); 
					
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
	
	//used for the initial setup of the level
	public abstract void initialize();
	
	//constantly updates logic for the level
	public abstract void update();
	
	//draws the level
	public void draw(){
		Graphics universe = sm.backBuffer.getGraphics();
		drawUniverse(universe);
		
		drawScreen(g);
	}
	
	/*draws the level as is
	 * 
	 */
	public abstract void drawUniverse(Graphics universe);
	
	/*draws the level on the screen
	 * 
	 */
	public abstract void drawScreen(Graphics screen);
	
	
	/**leaves the level to return to the last level opened
	 * 
	 * @param waitTime how long the program waits to give control back to user !!setting this too low will cause issues with selecting unwanted items
	 */
	public void exitLevel(int waitTime){
		sm.popLevel();
		isRunning = false;
		try{
			Thread.sleep(waitTime);
		}catch (Exception e){}
		
	}
	
	public void exitToMenu(int waitTime){
		isRunning = false;
		
		while (sm.levels.size() > 1){
			sm.levels.pop();
		}
		
		try{
			Thread.sleep(waitTime);
		}catch (Exception e){}
		
	}
	
	public boolean exitIsPressed(){
		return (input.isKeyDown(KeyEvent.VK_ESCAPE));
				
	}
	
	public int getFPS(){
		return FPS;
	}
	
	
}
