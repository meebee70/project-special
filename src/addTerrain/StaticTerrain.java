package addTerrain;

public class StaticTerrain extends Terrain {
	private final int X;
	private final int Y;
	
	/*creates the terrain at the specified locations
	 * 
	 */
	public StaticTerrain(int x,int y, int width, int height, String sprite){
		super(width,height,sprite);
		this.X = x;
		this.Y = y;
	}
	
	/*
	 * @see addTerrain.Terrain#getX()
	 */
	public int getX(){
		return X;
	}
	
	/*
	 * @see addTerrain.Terrain#getY()
	 */
	public int getY(){
		return Y;
	}

}
