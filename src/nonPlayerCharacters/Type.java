package nonPlayerCharacters;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public enum Type {
	GOOMBA("res/Laughing Stock.gif", new Tags[]{Tags.UNSPECIAL}),
	FLYER("res/Tainted Ground v1.png", new Tags[]{Tags.FLYING});
	//add more as we need them, also change these as needed
	
	private Tags[] tags;
	private Image img;
	
	Type(String image, Tags[] tags){
		
		try{
			img = ImageIO.read(new File(image));
		}catch (IOException e){
			System.err.println(image + " is not a valid image location");
		}
		
		this.tags = tags;
	}
	
	public Image getImage(){
		return this.img;
	}
}
