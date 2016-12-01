package characters;

import java.util.Objects;

public class PlayerTwo {
	
	private final int X_SPEED = 3; // pixels/frame
	private int direction; //positive for true, negative for false
	private double ySpeed = 0;
	
	private double x;
	private double y;
	
	private String sprite;
	
	public PlayerTwo(double x,double y){
		this.x = x;
		this.y = y;
	}
	
	public void setDirectiction(int direction){
		this.direction = direction;
	}
	
	public void affectDirection(int amount){
		this.direction += amount;
	}
	
	public void move(){
		int xMove = X_SPEED + direction;
		this.x = xMove;
	}
	
	public void collide(Object[] objects){
		System.out.println(Objects.class);
	}
	

}
