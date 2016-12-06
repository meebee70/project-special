package physics;

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
	
	
}
