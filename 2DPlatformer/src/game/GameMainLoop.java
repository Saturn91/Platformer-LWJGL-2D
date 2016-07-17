package game;

import org.lwjgl.opengl.Display;

import display.DisplayManager;
import display.renderer.Loader;

public class GameMainLoop {
	public static final String GAME_TITLE = "GameEngine";
	public static final int WINDOW_WIDTH = 1024;
	public static final int WINDOW_HEIGHT = 720;
	public static final float fps = 120;
	
	private DisplayManager display;
	
	private Game game;
	
	public void start(){
		display = new DisplayManager(GAME_TITLE, WINDOW_WIDTH, WINDOW_HEIGHT);
		display.setDisplaySync((int)fps);
		game = new Game();
		GameLoop();
		display.closeDisplay();		
	}
	
	public void GameLoop(){
		while(!Display.isCloseRequested()){
			tick(getTick());
			game.render();
			display.updateDisplay();
		}
	}
	
	private int tickCounter = 0;
	private long lastTick = 0;
	private long nowTime;
	private long delta;
	private long longestDelta;
	private long lastTimeTickLine;
	private long getTick(){
		nowTime = System.currentTimeMillis()/10;
		tickCounter ++;
		if(lastTick == 0){
			lastTick = nowTime;
		}
		delta = nowTime - lastTick;
		
		if(delta > longestDelta){
			longestDelta = delta;
		}
		if(nowTime - lastTimeTickLine >= 1000){
			System.out.println("ticks: " + tickCounter + " longest delta: " + longestDelta + "ms");
			tickCounter = 0;
			longestDelta = 0;
			lastTimeTickLine = nowTime;
		}
		lastTick = System.currentTimeMillis();
		return delta;
	}

	private void tick(long delta) {
		game.tick(delta);
	}

	public void close(){
		
	}
}
