package game.level;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;

import toolbox.Constants;
import toolbox.SaveSystem;
import Textures.ModelTexture;
import display.renderer.Loader;
import game.Game;
import game.entities.costum.GameObject;
import game.entities.costum.tileset.TileSet;
import game.entities.standart.Entity;
import game.entities.standart.RawModel;
import game.entities.standart.TexturedModel;

public class Map {
	
	public Map() {
		
		TileSet tileset = new TileSet("standartTileset", "Graphics/TestTileSet", 32, 32);
		
		int mapID[][];
		
		
		/*SaveSystem saveSystem = new SaveSystem("res/levels/", "lvl");
		saveSystem.addBufferLine("width: 100");
		saveSystem.addBufferLine("height: 20");
		for(int y = 0; y < 20; y++){
			for(int x = 0; x < 100; x++){
				saveSystem.addToBuffer("-0001");
			}
			saveSystem.addBufferLine("");
		}
		saveSystem.save("testLevel");*/
		
		mapID = LevelFileReader.read("testLevel");
		
		LevelInterpreter.generateLevel(mapID, 100, 20, tileset);
	}
	
	public void cleanUp(){
		Loader.cleanUp();
	}
}
