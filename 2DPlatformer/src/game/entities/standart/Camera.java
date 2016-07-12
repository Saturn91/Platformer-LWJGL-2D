package game.entities.standart;

import game.controller.Controler;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

import display.renderer.Renderer;

public class Camera {
	
	private Vector3f position = new Vector3f(0,0,0);
	private float pitch;	//High of Camera
	private float yaw;		//aiming left an d right
	private float speed = 0.0005f;
	private Controler controler;
	
	public Camera() {
		controler = new Controler();
	}
	
	public void tick(long delta){
		controler.tick(delta);
		position.x = controler.getPosition().x;
		position.y = controler.getPosition().y;
	}

	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public void setSpeed(float speed){
		this.speed = speed;
	}
	
	public void setZoom(float zoom){
		position.z = zoom;
	}
}
