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
		this.direction = 1;
		this.speedX = type.getSpeed();
		this.speedY = 0;
		
		this.GRAVITY = 0.9 / FPS;
	}

	
	public void compute(){
		double xToMove = 0;;
		
		if (!hasTag(Tags.FLYING)){
			xToMove = speedX * direction;
		}
		
		this.speedY = Math.max(0.1, speedY + GRAVITY);
		this.y += speedY;
		
		//calculateCollides
		
		
	}
	
	private void calculateCollides(Terrain[] platforms,PlayerOne player1,PlayerTwo player2){
		
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
