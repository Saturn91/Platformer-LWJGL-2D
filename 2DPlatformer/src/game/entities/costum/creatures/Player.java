package game.entities.costum.creatures;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;

import game.Game;
import game.controller.Controler;
import game.entities.costum.GameObject;
import game.entities.costum.tileset.TileSet;

public class Player{
	private float[] hitbox = {
		-0.5f, 0.5f,
		-0.5f, -0.5f,
		0.5f, -0.5f,
		0,5f, 0.5f			
	};
	private Controler controler = new Controler();
	private TileSet playerTileSet;
	private static final String NAME = "Player";
	private GameObject object;

	public Player(Vector2f position) {
		playerTileSet = new TileSet(NAME, "Graphics/Player/player", 32, 32);
		object = playerTileSet.getTile(0, 0, position, 1, 1);
		Game.addGameObject(object);
		controler.reSetPosition(position);
	}
	
	public void tick(long delta){
		controler.tick(delta);
		object.setPosition(controler.getPosition());
	}
	
	public GameObject getGameObject(){
		return object;
	}
	
	
	
}
