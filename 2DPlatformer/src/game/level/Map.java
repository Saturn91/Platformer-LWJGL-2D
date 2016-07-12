package game.level;

import org.lwjgl.util.vector.Vector2f;

import display.renderer.Loader;
import game.entities.costum.creatures.Player;
import game.entities.costum.tileset.TileSet;

public class Map {
	private Player player;
	public Map() {
		
		TileSet tileset = new TileSet("standartTileset", "Graphics/TestTileSet", 32, 32);
		
		
		int mapID[][];
		
		mapID = LevelFileReader.read("testLevel");
		
		LevelInterpreter.generateLevel(mapID, 100, 20, tileset);
		
		player = new Player(new Vector2f(1, 2));
	}
	
	public void tick(long delta){
		player.tick(delta);
	}
	
	public void cleanUp(){
		Loader.cleanUp();
	}
}
