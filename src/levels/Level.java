package levels;

public abstract class Level {
	
	private String name;
	
	public Level(String name){
		this.name = name;
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
