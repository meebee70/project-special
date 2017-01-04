package levels;

import java.awt.Graphics;

import mainGameEngine.StateManager;

public abstract class Level {
	
	public Graphics g;
	public boolean isRunning = true;
	private final int FPS = 60;
	public StateManager sm;
	
	public Level( StateManager sm, Graphics g){
		this.sm = sm;
		this.g = sm.getGraphics();
	}
	
	public void run(){
		initialize();
		
		long time = System.currentTimeMillis();
		
		while (isRunning){
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
	
}
