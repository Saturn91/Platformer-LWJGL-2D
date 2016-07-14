package game.level;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import display.renderer.Loader;
import game.entities.costum.block.Block;
import game.entities.costum.creatures.Player;
import game.entities.costum.tileset.TileSet;
import game.entities.standart.Camera;

public class Map {
	private Player player;
	private ArrayList<Block> blocks;
	private Level level;
	public Map() {
		blocks = new ArrayList<>();
		TileSet tileset = new TileSet("standartTileset", "Graphics/TestTileSet", 32, 32);
		
		level = LevelInterpreter.generateLevel("testLevel", tileset);
		
		player = new Player(new Vector2f(1, 2));
		Camera.bindTo(player.getGameObject());
	}
	
	public void tick(long delta){
		player.tick(delta);
		if(level.intersects(player.getGameObject().getPosition(), 1)){
			System.out.println("Map: intersects");
		}
	}
	
	public void cleanUp(){
		Loader.cleanUp();
	}
}
