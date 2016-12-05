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
	
	
	public Camera(Graphics window,int xZoom,int yZoom){
		this.frame = window;
		this.xZoom = xZoom;
		this.yZoom = yZoom;
	}

	public Camera(Graphics window , StateManager sm ,int x,int y, int xZoom, int yZoom){
		this.frame = window;
		this.sm = sm;
		this.x = x;
		this.y = y;
		this.xZoom = xZoom;
		this.yZoom = yZoom;
	}
	
	public void drawImage(Image image){
		frame.drawImage(image,sm.insets.left,sm.insets.top,sm.WINDOW_WIDTH,sm.WINDOW_HEIGHT, this.x, this.y, this.x + xZoom, this.y + yZoom, sm);
	}
	
	public boolean doesMove(int player1X,int player2X, int player1Y, int player2Y){
		double leftBound = (sm.WINDOW_WIDTH * 0.3) - this.x;
		double rightBound = (sm.WINDOW_WIDTH - (sm.WINDOW_WIDTH * 0.3)) - this.x;
		boolean player1 = false;
		boolean player2 = false;
		System.out.print(leftBound + " " + player1X + "   " + rightBound + " " + player2X + "     ");
		
		if (leftBound < player1X || player1X < rightBound){
			player1 = true;
		}
		if (leftBound < player2X || player2X < rightBound){
			player2 = true;
		}
		
		System.out.print(player1 + "   " + player2 + "    ");
		
		if (player1 ^ player2){
			System.out.println("true");
			return true;
		}else{
			System.out.println("false");
			return false;
		}
	}
}
