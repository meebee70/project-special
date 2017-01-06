package camera;

import java.awt.Graphics;
import java.awt.Image;

import mainGameEngine.StateManager;

public class Camera {
	private Graphics frame;
	private StateManager sm;
	private int x;
	private int y;
	private final int xZoom;
	private final int yZoom;
	private final int speed;
	
	
	public Camera(Graphics window,int xZoom,int yZoom){
		this.frame = window;
		this.xZoom = xZoom;
		this.yZoom = yZoom;
		this.speed = 1;
	}

	public Camera(Graphics window , StateManager sm ,int x,int y, int xZoom, int yZoom){
		this.frame = window;
		this.sm = sm;
		this.x = x;
		this.y = y;
		this.xZoom = xZoom;
		this.yZoom = yZoom;
		this.speed = 3;
	}
	
	public void drawImage(Image image){
		frame.drawImage(image,
				0,
				0,
				sm.getWidth(),
				sm.getHeight(),
				this.x, this.y, this.x + xZoom, this.y + yZoom, sm);
	}
	
	
	public void move(int x1,int x2, int y1, int y2){
		double leftBound = (xZoom * 0.1) + (this.x);
		double rightBound = (xZoom - (xZoom * 0.1)) + (this.x);
		
		if (doesMove(x1, x2, leftBound,rightBound)){
			if (getXDirection(x1,x2,y1,y2,leftBound,rightBound) == 1){
				this.x = Math.min(this.x + speed, sm.UNIVERSE_WIDTH - this.xZoom);
			}else{
				this.x = Math.max(this.x - speed, 0);
			}
		}
		
		this.y = (int) (Math.max((Math.max(y1, y2)) - (this.yZoom / 1.6),-sm.insets.top));
		}
	
	private int getXDirection(int x1, int x2, int y1, int y2, double leftBound, double rightBound){
		int toReturn;
		if ((x1 > rightBound) || ((x2 > rightBound))){
			toReturn = 1;
		}else
			toReturn = -1;
		
		System.out.println(toReturn);
		return toReturn;
	}
	
	private boolean doesMove(int player1X,int player2X, double leftBound, double rightBound){
		boolean leftMove = false;
		boolean rightMove = false;
		
		if (player1X < leftBound || player2X < leftBound){
			leftMove = true;
		}
		if (player1X > rightBound || player2X > rightBound){
			rightMove = true;
		}
		
		if (leftMove ^ rightMove){
			return true;
		}else{
			return false;
		}
	}
}
