package terrain;

import java.awt.Image;

public class Terrain {
	
	public int x;
	public int y;
	public final int WIDTH;
	public final int HEIGHT;
	
	private Image sprite;
	
	
	public Terrain(int x, int y, int width, int height, Image sprite){
		this.x = x;
		this.y = y;
		this.WIDTH = width;
		this.HEIGHT = height;
		this.sprite = sprite;
		
		setSprite(sprite);
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
	public Image getSprite(){
		return this.sprite;
	}
	
	public void setSprite(Image sprite){
		this.sprite = sprite;
	}
}
