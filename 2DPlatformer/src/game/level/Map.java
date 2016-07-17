package game.level;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import shaders.StaticShader;
import display.renderer.Loader;
import display.renderer.Renderer;
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
	}
	
	public void render(Renderer renderer, StaticShader shader){
		//render Player
		renderer.render(player.getGameObject(), shader);
	}
	
	public void cleanUp(){
		Loader.cleanUp();
	}
}
