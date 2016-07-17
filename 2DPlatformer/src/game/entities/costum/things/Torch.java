package game.entities.costum.things;

import org.lwjgl.util.vector.Vector2f;

import game.Game;
import game.entities.costum.creatures.Animation;
import game.entities.costum.tileset.TileSet;

public class Torch {
	private Animation torchAnimation;
	private Vector2f position;
	
	public Torch(Vector2f position){
		this.position = position;
		torchAnimation = new Animation(new TileSet("Torch", "Graphics/Things/torchTileSet", 32, 32));
		torchAnimation.addFrame(0, 0, position, 150);
		torchAnimation.addFrame(1, 0, position, 150);
		torchAnimation.addFrame(0, 1, position, 150);
		torchAnimation.addFrame(1, 1, position, 150);
		Game.addAnimation(torchAnimation);
	}
}