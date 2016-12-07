package physics;

import characters.PlayerOne;
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
		
		double platX = platform.getX();
		double platY = platform.getY();
		
		if (isInside(platX,platX + platform.WIDTH, player.getCurrentX()) && isInside(platY,platY + platform.HEIGHT,player.getCurrentY())){
			return true;
		}
		return false;
	}
	
	public boolean collides(int x, int y, Terrain platform){
		
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
	}
	
}
