package Game;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Game {
	//creates the frame
	public static void main(String[] args){
		boolean Badass = true; //Adds badassery
		JFrame frame = new JFrame("Helios");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		frame.add(new GamePanel(), BorderLayout.CENTER);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		//armen was here
		//woooOOooo!
	}

}
