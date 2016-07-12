package game.controller;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;

import display.renderer.Renderer;

public class Controler {
	private Vector2f position;
	private int up = Keyboard.KEY_W;
	private int down = Keyboard.KEY_S;
	private int right = Keyboard.KEY_D;
	private int left = Keyboard.KEY_A;
	private float speed = 0.0005f;
	
	public Controler() {
		position = new Vector2f(0,0);
	}

	public Controler(int up, int down, int right, int left, Vector2f position) {
		this.up = up;
		this.down = down;
		this.right = right;
		this.left = left;
		this.position = position;
	}
	
	public void setKey(int direction, int key){
		switch (direction){
		case 0: 
			up = key;
			break;
		case 1: 
			down = key;
			break;
		case 2: 
			right = key;
			break;
		case 3: 
			left =key;
			break;
		}		
	}

	public Vector2f getPosition() {
		return position;
	}

	public void reSetPosition(Vector2f position) {
		this.position = position;
	}
	
	public void tick(long delta){
		if(Keyboard.isKeyDown(up)){
			position.y +=speed*Renderer.getZoom()*delta;
		}
		
		if(Keyboard.isKeyDown(down)){
			position.y -=speed*Renderer.getZoom()*delta;
		}
		
		if(Keyboard.isKeyDown(right)){
			position.x +=speed*Renderer.getZoom()*delta;
		}
		
		if(Keyboard.isKeyDown(left)){
			position.x -=speed*Renderer.getZoom()*delta;
		}
	}
	
	
	
	
	
	
	
}
