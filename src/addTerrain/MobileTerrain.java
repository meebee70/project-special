package addTerrain;

public class MobileTerrain extends Terrain{
	private int leftSpeed = 0;
	private int rightSpeed = 0;
	private int upSpeed = 0;
	private int downSpeed = 0;
	
	private int upLimit;
	private int downLimit;
	private int leftLimit;
	private int rightLimit;
	
	private int xDirection = 1;
	private int yDirection = 1;
	
	public MobileTerrain(int x, int y, int width, int height, String sprite){
		super(x,y,width,height,sprite);
	}
	
	
	//mutators
	public void setXSpeeds(int leftSpeed, int rightSpeed){
		this.leftSpeed = leftSpeed;
		this.rightSpeed = rightSpeed;
	}
	
	public void setYSpeeds(int upSpeed,int downSpeed){
		this.upSpeed = upSpeed;
		this.downSpeed = downSpeed;
	}
	
	public void setLimits(int up, int down, int left, int right){
		this.upLimit = up;
		this.downLimit = down;
		this.rightLimit = right;
		this.leftLimit = left;
	}
	
	//Accessors
	public int getLeftSpeed(){
		return this.leftSpeed;
	}
	
	public int getrightSpeed(){
		return this.rightSpeed;
	}
	
	public int getupSpeed(){
		return this.upSpeed;
	}
	
	public int getdownSpeed(){
		return this.downSpeed;
	}
	
	
	public int getUpLimit(){
		return upLimit;
	}
	
	public int getDownLimit(){
		return downLimit;
	}
	
	public int getLeftLimit(){
		return leftLimit;
	}
	
	public int getRightLimit(){
		return rightLimit;
	}
	
	
	
	
	public void move(){
		
	}
	
	

}
