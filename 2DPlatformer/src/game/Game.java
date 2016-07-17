package game;

import game.entities.costum.GameObject;
import game.entities.costum.creatures.Animation;
import game.entities.light.Light;
import game.entities.light.LightEngine;
import game.entities.standart.Camera;
import game.level.Map;

import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import shaders.StaticShader;
import display.renderer.Renderer;


public class Game {
	
	private static ArrayList<GameObject> gameObjects;
	private static ArrayList<Animation> animations;
	private Renderer renderer;
	private StaticShader shader;
	private Map map;
	private Camera camera;
	
	public Game() {
		init();
	}
	
	/**
	 * get trough all entities and render them
	 */
	public void render(){
		shader.setPointLights(LightEngine.getLights());
		//Prepare Renderer
		renderer.prepare();
		
		//Start Shaderprogramm
		shader.start();
		
		shader.update();
		
		shader.loadViewMatrix(camera);
		
		for(Animation a: animations){
			a.tick();
			renderer.render(a.getActualFrame(), shader);
		}
		
		map.render(renderer, shader);
		
		shader.stop();
	}
	
	/**
	 * Change all states of Entities. 
	 */
	public void tick(long delta){
		//Camera
		map.tick(delta);
		camera.tick(delta);
		
	}
	
	public static void addGameObject(GameObject gameObject){
		gameObjects.add(gameObject);
	}
	
	public static void addAnimation(Animation animation){
		animations.add(animation);
	}
	
	/**
	 * build Game
	 */
	public void init(){
		gameObjects = new ArrayList<>();
		animations = new ArrayList<>();
		shader = new StaticShader();
		shader.setEnviromentLight(new Vector3f(0.05f,0.02f,0.05f));
		Light light = new Light(new Vector2f(0,0), new Vector3f(0f, 0.0f, 0.0f));
		shader.configureCameraLight(light);		
		camera = new Camera();
		renderer = new Renderer(shader);
		renderer.setZoom(10);
		map = new Map();
		
	}
	
	public Vector3f generateColor(){
		Random random = new Random();
		float x = random.nextFloat();
		float y = random.nextFloat();
		float z = random.nextFloat();
		return new Vector3f(x,y,z);
	}
	
	public Vector2f generatePosition(){
		Random random = new Random();
		float x = random.nextFloat()*24f + 1f;
		float y = random.nextFloat()*24f + 1f;
		return new Vector2f(x,y);
	}
	
	public Light[] generateLights(){
		Light light[] = new Light[3];
		for(int i = 0; i<3; i++){
			light[i] = new Light(generatePosition(), generateColor());
		}		
		return light;
	}
	
	public static void deleteGameObjects(){
		gameObjects = new ArrayList<>();
	}
}
