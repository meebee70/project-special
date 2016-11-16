package levels;

public abstract class Level {
	
	private String name;
	
	public Level(String name){
		this.name = name;
	}
	
	
	public String getName(){
		return this.name;
	}
	
	public abstract void initialize();
	public abstract void update();
	public abstract void draw();
	

}
