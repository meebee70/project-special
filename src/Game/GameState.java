package Game;

public abstract class GameState {

	protected GameStateManager gsm;
	
	protected GameState(GameStateManager gsm){
		this.gsm = gsm;
		init();
	}
	
	public abstract void init();
		
	
}
