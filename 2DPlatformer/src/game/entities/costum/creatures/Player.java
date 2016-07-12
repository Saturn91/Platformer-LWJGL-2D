package game.entities.costum.creatures;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;

import game.Game;
import game.controller.Controler;
import game.entities.costum.GameObject;
import game.entities.costum.tileset.TileSet;

public class Player extends GameObject{
	private Controler controler = new Controler();
	private TileSet playerTileSet;

	public Player(Vector2f position) {
		super("Player", position, 1, 1);
		playerTileSet = new TileSet("player", "Graphics/Player/player", 32, 32);
		Game.addGameObject(playerTileSet.getTile(0, 0, position, 1, 1));
		controler.reSetPosition(position);
		controler.setKey(0, Keyboard.KEY_UP);
		controler.setKey(1, Keyboard.KEY_DOWN);
		controler.setKey(2, Keyboard.KEY_RIGHT);
		controler.setKey(3, Keyboard.KEY_LEFT);
	}
	
	public void tick(long delta){
		controler.tick(delta);
		position = controler.getPosition();
	}
	
}
