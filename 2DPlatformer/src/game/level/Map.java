package game.level;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;

import toolbox.Constants;
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
		
		TileSet tileset = new TileSet("Standart", "TestTileSet", 32, 32);
		Loader loader = new Loader();
		
		RawModel model= loader.loadToVAO(Constants.QuadVerticies(1, 1), Constants.TextureCords(), Constants.QuadIndices());
		ModelTexture texture = new ModelTexture(loader.loadTexture("transparence"));
		TexturedModel staticModel = new TexturedModel(model, texture);
		Entity entity = new Entity(staticModel);
		GameObject.addEntity("Test", entity);
		
		for(int x = 0; x < 25; x++){
			for(int y = 0; y < 25; y++){
				Game.addEntity(tileset.getTile(0, 0, new Vector2f(x, y), 1, 0));
			}
		}
		
		Game.addEntity(new GameObject("Test", new Vector2f(0,0), 1.5f, 1));
	}
	
	public void cleanUp(){
		Loader.cleanUp();
	}
}
