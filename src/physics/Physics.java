package physics;

import characters.PlayerOne;
import characters.PlayerTwo;
import terrain.Terrain;

public class Physics {
	private final double defaultGravity = 1.1 / 60;
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


	//useful stuff anything might use

	public static boolean collides(PlayerOne player, Terrain platform){

		final double aX = player.getCurrentX();
		final double aY2 = player.getCurrentY();
		final double aX2 = aX + player.getWidth();
		final double aY = aY2 + player.getHeight();
		final double bX = platform.getX();
		final double bY2 = platform.getY();
		final double bX2 = bX + platform.getWidth();
		final double bY = bY2 + platform.getHeight();

		return collidesTest(aX, aY, aX2, aY2, bX, bY, bX2, bY2);
	}

	public static boolean collides(PlayerTwo player, Terrain platform){

		final double aX = player.getCurrentX();
		final double aY2 = player.getCurrentY();
		final double aX2 = aX + player.getWidth();
		final double aY = aY2 + player.getHeight();
		final double bX = platform.getX();
		final double bY2 = platform.getY();
		final double bX2 = bX + platform.getWidth();
		final double bY = bY2 + platform.getHeight();

		return collidesTest(aX, aY, aX2, aY2, bX, bY, bX2, bY2);
	}
	
	public static boolean collides(double aX, double aY, double aX2, double aY2, Terrain platform){
		final double bX = platform.getX();
		final double bY2 = platform.getY();
		final double bX2 = bX + platform.getWidth();
		final double bY = bY2 + platform.getHeight();
		return collidesTest(aX, aY, aX2, aY2, bX, bY, bX2, bY2);
	}

	public boolean collides(double aX, double aY, double aX2, double aY2, double bX, double bY, double bX2, double bY2){
		return collidesTest(aX, aY, aX2, aY2, bX, bY, bX2, bY2);
	}

	
	
	private static boolean collidesTest(double aX, double aY, double aX2, double aY2, double bX, double bY, double bX2, double bY2){
		if (!(aX >= bX2 || aX <= bX) || !(aX2 >= bX2 || aX2 <= bX)){
			if (!((aY <= bY2) || (aY >= bY)) || !((aY2 <= bY2) || (aY2 >= bY))){
				return true;
			}
		}
		return false;
	}

}
