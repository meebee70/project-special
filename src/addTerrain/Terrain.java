package addTerrain;

public abstract class Terrain {
	
	public int x;
	public int y;
	public final int WIDTH;
	public final int HEIGHT;
	
	private final String sprite;
	
	
	/*generic constructor
	 * 
	 */
	public Terrain(int x, int y, int width, int height,String sprite){
		this.x = x;
		this.y = y;
		this.WIDTH = width;
		this.HEIGHT = height;
		this.sprite = sprite;
	}
	
	/*Constructor used for non-moving (static) terrain
	 * 
	 */
	public Terrain(int width, int height, String sprite){
		this.WIDTH = width;
		this.HEIGHT = height;
		this.sprite = sprite;
	}
	
	/*gets the left most x position of this terrain
	 * 
	 */
	public int getX(){
		return x;
	}
	
	/*gets the top most y position of this terrain
	 * 
	 */
	public int getY(){
		return y;
	}
	
	/*gets the width of this terrain (from the left)
	 * 
	 */
	public int getWidth(){
		return WIDTH;
	}
	
	/*gets the height of this terrain (from the top)
	 * 
	 */
	public int getHeight(){
		return HEIGHT;
	}
	
	/*gets the address (as a string) of the sprite
	 * 
	 */
	public String getSprite(){
		return this.sprite;
	}
}
