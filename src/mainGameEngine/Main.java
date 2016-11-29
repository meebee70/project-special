package mainGameEngine;

public class Main {
	private static StateManager sm = new StateManager();
	private static MainMenu menu = new MainMenu(sm);
	/**
	 * Exits Game and closes window
	 */
	
	public static void main(String[] args){
		
		menu.run();
		try{
		sm.levels.peek().run();
		}catch (NullPointerException e){
		}
		System.exit(0);
	}
}
