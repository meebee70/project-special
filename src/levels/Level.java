package levels;

public abstract class Level {
	
	
	private boolean isRunning = true;
	private final int fps = 60;
	private String name;
	
	public Level(String name){
		this.name = name;
	}
	
	public void run(){
		initialize();
		
		long time = System.currentTimeMillis();
		
		while (isRunning){
			update();
			draw();
			
			
		//  delay for each frame  -   time it took for one frame 
					time = (1000 / fps) - (System.currentTimeMillis() - time); 
					
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
	
	
	public String getName(){
		return this.name;
	}
	
	//used for the initial setup of the level
	public abstract void initialize();
	
	//constantly updates logic for the level
	public abstract void update();
	
	//draws the level
	public abstract void draw();
	
	
	
	
	

}
