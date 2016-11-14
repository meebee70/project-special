package Game;

import java.awt.Dimension;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	private static final long serialVersionUID = 1l;
	
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 600;
	
	
	public GamePanel(){
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		
	}

}
