package physics;

import characters.PlayerOne;
import characters.PlayerTwo;
import terrain.Terrain;

public class Physics {
	private final double defaultGravity = 0.1;
	private final double defaultFriction = 0.08;

	private double gravity;
	private double friction;


	public Physics(){
		this.gravity = defaultGravity;
		this.friction = defaultFriction;
	}

	public Physics(double gravity){
		this.gravity = gravity;
		this.friction = defaultFriction;
	}

	public Physics(double gravity, double friction){
		this.gravity = gravity;
		this.friction = friction;
	}

	//Accessors
	public double getGravity(){
		return this.gravity;
	}

	public double getFriction(){
		return this.friction;
	}

	//Mutators
	public void setGravity(double amount){
		this.gravity = amount;
	}

	public void setFriction(double amount){
		this.friction = amount;
	}


	//usefull stuff anything might use

	public boolean collides(PlayerOne player, Terrain platform){

		final double aX = player.getCurrentX();
		final double aY = player.getCurrentY();
		final double aX2 = player.getCurrentX() + player.getWidth();
		final double aY2 = player.getCurrentY() + player.getHeight();
		final double bX = platform.getX();
		final double bY = platform.getY();
		final double bX2 = bX + platform.getWidth();
		final double bY2 = bY + platform.getHeight();

		int overlap = 0;
		if (!((aX >= bX2) || (aX <= bX)) || !((aX2 >= bX2) || (aX2 <= bX))){
			overlap++;
		}
		
		if (!((aY <= bY2) || (aY >= bY)) || !((aY2 <= bY2) || (aY2 >= bY))){
			overlap++;
		}
		
		if ( (int)overlap / 2 == 1 ){
			return true;
		} else {
			return false;
		}
	}

	public boolean collides(PlayerTwo player, Terrain platform){

		final double aX = player.getCurrentX();
		final double aY = player.getCurrentY();
		final double aX2 = player.getCurrentX() + player.getWidth();
		final double aY2 = player.getCurrentY() + player.getHeight();
		final double bX = platform.getX();
		final double bY = platform.getY();
		final double bX2 = bX + platform.getWidth();
		final double bY2 = bY + platform.getHeight();
		
		int overlap = 0;
		
		if (!((aX >= bX2) || (aX <= bX)) || !((aX2 >= bX2) || (aX2 <= bX))){
			overlap++;
		}
		if (!((aY <= bY2) || (aY >= bY)) || !((aY2 <= bY2) || (aY2 >= bY))){
			overlap++;
		}
		if ( (int)overlap / 2 == 1 ){
			return true;
		} else {
			return false;
		}
	}
	
	public boolean collides(int aX, int aY, int aX2, int aY2, int bX, int bY, int bX2, int bY2){
		
		int overlap = 0;
		
		if (!((aX >= bX2) || (aX <= bX)) || !((aX2 >= bX2) || (aX2 <= bX))){
			overlap++;
		}
		if (!((aY <= bY2) || (aY >= bY)) || !((aY2 <= bY2) || (aY2 >= bY))){
			overlap++;
		}
		if ( (int)overlap / 2 == 1 ){
			return true;
		} else {
			return false;
		}
	}

	
	
	//Consider deleting once Chris is cool with changes
	
	/*public boolean collides(int x, int y, Terrain platform){

		double platX = platform.getX();
		double platY = platform.getY();

		if (isInside(platX,platX + platform.WIDTH, x) && isInside(platY,platY + platform.HEIGHT,y)){
			return true;
		}
		return false;
	}

	private boolean isInside(double leftBound, double rightBound, double value){
		if (leftBound < value && value < rightBound){
			return true;
		}else
			return false;
	}*/

}
