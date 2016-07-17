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
	
	public boolean intersects(Vector2f vertices[], float distance){
		for(Vector2f v: vertices){
			for(Block b: blocks){
				if (b.isInRange(v, distance)){
					if(b.intersects(v)){
						return true;
					}
				}
			}
		}		
		return false;
	}
	
	public float intersectsUp(Vector2f vertices[], float distance){
		for(Vector2f v: vertices){
			for(Block b: blocks){
				if (b.isInRange(v, distance)){
					return b.intersectsUp(v);
				}
			}
		}		
		return 0;
	}
	
	public float intersectsUp(Vector2f position, float distance){
		for(Block b: blocks){
			if (b.isInRange(position, distance)){
				return b.intersectsUp(position);
			}
		}	
		return 0;
	}
}
