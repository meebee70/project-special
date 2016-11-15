package Game;

import java.awt.Color;
import java.awt.Graphics;

public class MenuState extends GameState {
	
	//all items that will appear on the menu
	private String[] options = {"Begin","help","quit"};
	
	//the item the mouse is currently over
	private int currentSelection = 0;
	
	
	public MenuState(GameStateManager gsm){
		super(gsm);
	}

	
	public void init() {}

	public void tick() {
		
	}

	public void draw(Graphics g) {
		for (int i = 0;i < options.length;i++){
		
			if (i == currentSelection){
				g.setColor(Color.BLACK);
			}else {
				g.setColor(Color.GREEN);
			}
			
			g.drawString(options[i], GamePanel.WIDTH / 2 + 65, 65 + i * 30);
		}
		
	}

	public void keyPressed(int k) {
		
	}

	public void keyReleased(int k) {
		
	}
	
	
	

}
