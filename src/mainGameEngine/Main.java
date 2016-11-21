package mainGameEngine;

public class Main {
	
	static MainMenu menu = new MainMenu();
	/**
	 * Exits Game
	 */
	
	public static void main(String[] args){
		
		menu.run();
		
		System.exit(0);
	}
}
