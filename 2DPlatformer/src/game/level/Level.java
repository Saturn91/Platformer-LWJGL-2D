package game.level;

import game.entities.costum.block.Block;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

public class Level {
	private ArrayList<Block> blocks;
	
	public Level() {
		blocks = new ArrayList<>();
	}
	int i = 0;
	public void addBlock(Block block){
		blocks.add(block);
	}
	
	public boolean intersects(Vector2f position, float distance){
		for(Block b: blocks){
			if (b.isInRange(position, distance)){
				if(b.intersects(position)){
					return true;
				}
			}
		}
		return false;
	}
}
