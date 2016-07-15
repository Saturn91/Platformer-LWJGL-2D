package game.entities.costum.creatures;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;

import toolbox.Vector;
import game.Game;
import game.controller.Controler;
import game.entities.costum.GameObject;
import game.entities.costum.tileset.TileSet;

public class Player{
	private Vector2f[] hitbox = {
		new Vector2f(-0.25f, 0.25f),
		new Vector2f(-0.25f, -0.25f),
		new Vector2f(0.25f, -0.25f),
		new Vector2f(0.25f, 0.25f)			
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
		updateHitbox();
	}
	
	public GameObject getGameObject(){
		return object;
	}
	
	private Vector2f movedHitbox[] = new Vector2f[4];
	/**
	 * @return float[0] = left up, ... float[3] = right up, counterclockwise
	 */
	private void updateHitbox(){
		for(int i = 0; i < 4; i++){
			movedHitbox[i] = Vector.add(object.getPosition(), hitbox[i]);
		}		
	}
	
	public Vector2f[] getHitbox(){
		return movedHitbox;
	}
	
}
