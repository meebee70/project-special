package nonPlayerCharacters;

import java.awt.Image;

import characters.PlayerOne;
import characters.PlayerTwo;
import levels.Level;
import mainGameEngine.StateManager;
import physics.Physics;
import terrain.Terrain;

public class Enemy {
	
	private StateManager sm;
	
	private final Type type;
	private Image sprite;
	
	private double width,height,x,y,direction,speedX,speedY;
	private final double GRAVITY;
	private final int FPS;
	private boolean isInAir = false;
	
	private Physics phys;
	
	private Tags[] tags;
	
	private Level level;
	
	
	public Enemy(StateManager sm, Type type,Level level, double startX, double startY){
		this.sm = sm;
		
		this.level = level;
		
		phys = sm.getPhysics();
		this.type = type;
		this.FPS = level.getFPS();
		
		this.sprite = type.getImage();
		this.tags = type.getTags();
		
		this.x = startX;
		this.y = startY;
		this.width = 26;
		this.height = 26;
		
		this.direction = 1;
		this.speedX = type.getSpeed();
		this.speedY = 0;
		
		this.GRAVITY = 0.9 / FPS;
	}

	
	public void compute(Terrain[] platforms){
		
		calculateCollides(platforms);
		
		double xToMove = 0;;
		
		if (!hasTag(Tags.FLYING)){
			xToMove = speedX * direction;
		}
		
		if (isInAir){
			this.speedY = Math.max(0.1, speedY + GRAVITY);
		}else {
			this.speedY = 0;
		}
		
		this.x += xToMove;
		this.y += speedY;
		
		//calculateCollides
		
		
	}
	
	private void calculateCollides(Terrain[] platforms){
		double x = this.x;
		double y = this.y;
		double x2 = x + this.width;
		double y2 = y + this.height;
		isInAir = true;
		
		for (Terrain form: platforms){
			if (Physics.collides(x + speedX, y, x2 + speedX, y2, form)){
				direction *= -1;
			}
			
			if (Physics.collides(x, y + speedY + 5, x2, y2 + speedY + 5, form)){
				//this.y -= speedY * 5;
				isInAir = false;
				
			}
		}
		System.out.println(isInAir);
	}
	
	
	
	
	
	
	
	
	
	public Image getSprite(){
		return this.sprite;
	}
	
	public int getX(){
		return (int)this.x;
	}
	
	public int getY(){
		return (int)this.y;
	}
	
	public double getWidth(){
		return this.width;
	}
	
	public double getHeight(){
		return this.height;
	}
	
	
	
	private boolean hasTag(Tags search){
		for (Tags tag:tags){
			if (tag == search){
				return true;
			}
		}
		
		return false;
	}
}
