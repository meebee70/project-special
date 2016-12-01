package addTerrain;

public class StaticTerrain extends Terrain {
	private final int X;
	private final int Y;
	
	
	public StaticTerrain(int x,int y, int width, int height, String sprite){
		super(width,height,sprite);
		this.X = x;
		this.Y = y;
	}

}
