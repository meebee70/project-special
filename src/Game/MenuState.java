package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class MenuState extends GameState {
	
	//all items that will appear on the menu, left at the bottom - right at the top
	private String[] options = {"Begin","Help","QUIT"};
	
	//the item the mouse is currently over
	private int currentSelection = 0;
	
	
	public MenuState(GameStateManager gsm){
		super(gsm);
	}

	
	public void init() {}

	public void tick() {
		
	}

	public void draw(Graphics g) {
		
		g.setColor(new Color(60,150,255));
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		for (int i = 0;i < options.length;i++){
			
			if (i == currentSelection){
				g.setColor(Color.GREEN);
			}else {
				g.setColor(Color.BLACK);
			}
			
			g.setFont(new Font("Ariel",Font.PLAIN, 60));
			
			g.drawString(options[i], (GamePanel.WIDTH / 2) - 65, 100 + ( i * 150));
		}
		
	}

	public void keyPressed(int k) {
		
	}

	public void keyReleased(int k) {
		
	}
	
	
	

}
