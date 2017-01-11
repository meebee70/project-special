package nonPlayerCharacters;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public enum Type {
	GOOMBA("res/Laughing Stock.gif", new Tags[]{Tags.UNSPECIAL}, 1),
	FLYER("res/Tainted Ground v1.png", new Tags[]{Tags.FLYING},3);
	//add more as we need them, also change these as needed
	
	private Tags[] tags;
	private Image img;
	private double speed;
	
	Type(String image, Tags[] tags, double speed){
		
		try{
			img = ImageIO.read(new File(image));
		}catch (IOException e){
			System.err.println(image + " is not a valid image location");
		}
		
		this.tags = tags;
		this.speed = speed;
	}
	
	public Image getImage(){
		return this.img;
	}
	
	public Tags[] getTags(){
		return this.tags;
	}
	
	public double getSpeed(){
		return speed;
	}
}
