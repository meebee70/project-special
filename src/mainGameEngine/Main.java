package mainGameEngine;

public class Main {
	private static StateManager sm = new StateManager();
	private static MainMenu menu = new MainMenu(sm, sm.getGraphics());
	
	/**
	 * this is the game, DO NOT touch this
	 */
	public static void main(String[] args){
		
		sm.levels.push(menu);
		while(!sm.levels.isEmpty()){
				sm.levels.peek().run();
		}
		
		System.exit(0);
	}
}
