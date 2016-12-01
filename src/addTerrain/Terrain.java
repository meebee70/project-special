package addTerrain;

public class Terrain {
	
	private int x;
	private int y;
	private final int WIDTH;
	private final int HEIGHT;
	
	private final String sprite;
	
	
	
	public Terrain(int x, int y, int width, int height,String sprite){
		this.x = x;
		this.y = y;
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
	
	public void translate(int xAmount, int yAmount){
		this.x += xAmount;
		this.y += yAmount;
	}
	
	

}
