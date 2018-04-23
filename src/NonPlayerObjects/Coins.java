package NonPlayerObjects;

	import java.awt.Image;
	import java.io.File;
	import java.io.IOException;
	import javax.imageio.ImageIO;

	import levels.Level;
	import physics.Physics;

public class Coins {

		private double x, y;

		public Image sprite;
		
		private boolean hasGivenPoints;

		Physics physics = new Physics();

		final private int HEIGHT;
		final private int WIDTH;

		public Coins(int x, int y, Level level){
			this.x = x;
			this.y = y;

			this.HEIGHT = 32;	//Of sprite or Hitbox
			this.WIDTH = 32; //Update later
			this.sprite = null;
			this.hasGivenPoints = true;
			
			level.getCoinsList().add(this);


			try {
				this.sprite = ImageIO.read(new File("res/bitcoin_coin_32.png"));
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
			return this.sprite;
		}
		
		public void setSprite(Image sprite){
			this.sprite = sprite;
		}
		
		public boolean getPoints(){
			return hasGivenPoints;
		}
		
		public void givePoints(){
			this.hasGivenPoints = false;
		}
	
}

