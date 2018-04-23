package nonPlayerCharacters;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import levels.Level;
import mainGameEngine.StateManager;
import physics.Physics;
import terrain.Terrain;

public class LaughingStock {
	
	
	private Image sprite;
		
	
	
	
	private double width,height,x,y,direction,speedX,speedY;
	private final double GRAVITY;
	private final int FPS;
	private boolean isInAir = false;
	
	private Tags[] tags;
	
	@SuppressWarnings("unused")
	private Level level;

	
	public LaughingStock(StateManager sm, Level level, double startX, double startY){
		
		this.tags = new Tags[]{Tags.UNSPECIAL};
		
		this.level = level;
		
		this.FPS = level.getFPS();
		try {
			this.sprite = ImageIO.read(new File("res/Laughing Stock.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.x = startX;
		this.y = startY;
		this.width = 26;
		this.height = 26;
		
		this.direction = 1;
		this.speedX = 1;
		this.speedY = 0;
		
		this.GRAVITY = 0.9 / FPS;
	}

	
	public void compute(Terrain[] platforms){
		
		calculateCollides(platforms);
		
		double xToMove = 0;;
		
		if (!hasTag(Tags.FLYING)){
			xToMove = speedX * direction;
		}else if(hasTag(Tags.FLYING)){
			
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
