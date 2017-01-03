package mainGameEngine;

public class Main {
	private static StateManager sm = new StateManager();
	private static MainMenu menu = new MainMenu(sm);
	
	/**
	 * this is the game, DO NOT touch this
	 */
	public static void main(String[] args){
		
		menu.run();
		while(true){
			if (sm.levels.isEmpty()){
				break;
			}else
				sm.levels.peek().run();
		}
		
		System.exit(0);
	}
}
