package addTerrain;

public abstract class Terrain {
	
	public int x;
	public int y;
	public final int WIDTH;
	public final int HEIGHT;
	
	private final String sprite;
	
	
	
	public Terrain(int x, int y, int width, int height,String sprite){
		this.x = x;
		this.y = y;
		this.WIDTH = width;
		this.HEIGHT = height;
		this.sprite = sprite;
	}
	
	public Terrain(int width, int height, String sprite){
		this.WIDTH = width;
		this.HEIGHT = height;
		this.sprite = sprite;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getWidth(){
		return WIDTH;
	}
	
	public int getHeight(){
		return HEIGHT;
	}
	
	public String getSprite(){
		return this.sprite;
	}
}
