package nonPlayerCharacters;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public enum Type {
	GOOMBA("res/Laughing Stock.gif"),
	FLYER("res/Tainted Ground v1.png");
	//add more as we need them, also change these as needed
	
	
	private Image img;
	
	Type(String image){
		
		try{
			img = ImageIO.read(new File(image));
		}catch (IOException e){
			System.err.println(image + " is not a valid image location");
		}
		
	}
	
	public Image getImage(){
		return this.img;
	}
}
