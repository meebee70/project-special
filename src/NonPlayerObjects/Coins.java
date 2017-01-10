package NonPlayerObjects;

	import java.awt.Image;
	import java.io.File;
	import java.io.IOException;
	import javax.imageio.ImageIO;
	import mainGameEngine.InputHandler;
	import mainGameEngine.StateManager;
	import physics.Physics;

public class Coins {

		final int FPS = 60;

		private double x, y;

		private Image sprite;

		private StateManager sm;

		private InputHandler input;

		Physics physics = new Physics();

		final private int HEIGHT;
		final private int WIDTH;

		public Coins(int x, int y, StateManager sm){
			this.x = x;
			this.y = y;

			this.HEIGHT = 32;	//Of sprite or Hitbox
			this.WIDTH = 32; //Update later
			this.sm = sm;
			input = sm.input;


			try {
				this.sprite = ImageIO.read(new File("res/PlayerSprites/Player 1.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}


		}

		//ACCESSORS
		public int getCurrentX() {
			return (int) x;
		}

		public int getCurrentY() {
			return (int) y;
		}

		public int getHeight() {
			return HEIGHT;
		}

		public int getWidth() {
			return WIDTH;
		}

		public Image getSprite(){
			return sprite;
		}

		//MUTATORS
		//@Param = new x and/or y coord
		public void setCurrentX(double newX) {
			this.x = newX;
		}
		public void setCurrentY(double newY) {
			this.y = newY;
		}
		public void setXandY(double newX, double newY){
			this.x = newX;
			this.y = newY;
		}


}

