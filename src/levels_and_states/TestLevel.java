package levels_and_states;

import java.awt.Color;
import java.awt.Graphics;

import com.sun.glass.events.KeyEvent;

public class TestLevel extends GameState{
	
	
	private int x = 400;
	private int y = 600;
	
	private int x2 = 100;
	private int y2 = 200;
	
	public TestLevel(GameStateManager gsm){
		super(gsm);
	}


	public void init() {
	}


	public void tick() {
	}


	public void draw(Graphics g) {

		
		
		
		g.setColor(Color.RED);
		g.fillRect(this.x2, this.y2, 20, 4);
		g.setColor(Color.BLUE);
		g.fillRect(this.x, this.y, 80, 30);
		
		
	}


	public void keyPressed(int k) {

		if (k == KeyEvent.VK_UP){
			this.y--;
		}else if (k == KeyEvent.VK_RIGHT){
			this.x++;
		}else if (k == KeyEvent.VK_DOWN){
			this.y++;
		}else if (k == KeyEvent.VK_LEFT){
			this.x--;
		}
		
		if (k == KeyEvent.VK_W){
			this.y2 -= 2;
		}else if (k == KeyEvent.VK_D){
			this.x2 += 2;
		}else if (k == KeyEvent.VK_S){
			this.y2 += 2;
		}else if (k == KeyEvent.VK_A){
			this.x2 -= 2;
		}
		
		
	}


	public void keyReleased(int k) {

		
	}

}
