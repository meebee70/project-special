package nonPlayerCharacters;

import java.awt.Image;

import mainGameEngine.StateManager;

public class Enemy {
	
	private StateManager sm;
	
	private final Type type;
	private Image sprite;
	
	public double x;
	public double y;
	
	public double width;
	public double height;
	
	
	public Enemy(StateManager sm, Type type, double startX, double startY){
		this.sm = sm;
		this.type = type;
		this.sprite = type.getImage();
		
		this.x = startX;
		this.y = startY;
	}

	
}
