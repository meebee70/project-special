package terrain;

import java.awt.Image;

import levels.Level;
import mainGameEngine.StateManager;

public class LevelEnder extends Terrain {
	
	private StateManager sm;
	private Level nextLevel;

	public LevelEnder(int x, int y, int width, int height, Image sprite,Level nextLevel,StateManager sm) {
		super(x, y, width, height, sprite);
		this.sm = sm;
		this.nextLevel = nextLevel;
	}
	
	public void goToNextLevel(){
		nextLevel.isRunning = true;
		sm.levels.peek().isRunning = false;
		sm.levels.push(nextLevel);
	}

}
