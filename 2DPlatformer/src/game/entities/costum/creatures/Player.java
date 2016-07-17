package game.entities.costum.creatures;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;

import display.renderer.Renderer;
import toolbox.Vector;
import game.Game;
import game.controller.Controler;
import game.entities.costum.GameObject;
import game.entities.costum.tileset.TileSet;
import game.level.Level;

public class Player{
	private Vector2f position;
	private float speed = 7f;
	
	private Level level;
	
	private Vector2f[] hitbox = {
		new Vector2f(-0.25f, 0.4f),
		new Vector2f(-0.25f, -0.5f),
		new Vector2f(0.25f, -0.5f),
		new Vector2f(0.25f, 0.4f)			
	};
	private Controler controler = new Controler();
	private TileSet playerTileSet;
	private static final String NAME = "Player";
	private GameObject object;

	public Player(Vector2f position, Level level) {
		this.position = position;
		this.level = level;
		updateHitbox(position);
		playerTileSet = new TileSet(NAME, "Graphics/Player/player", 32, 32);
		object = playerTileSet.getTile(0, 0, position, 1, 1);
		Game.addGameObject(object);
		controler = new Controler();
		//controler.addCommand("up", Keyboard.KEY_W);
		controler.addCommand("jump", Keyboard.KEY_SPACE);
		controler.addCommand("right", Keyboard.KEY_D);
		controler.addCommand("down", Keyboard.KEY_S);
		controler.addCommand("left", Keyboard.KEY_A);
	}
	
	public void tick(long delta){
		updatePosition(delta);
		updateHitbox(position);
		
	}
	
	private void updatePosition(long delta){
		controler.tick();
		jumpMecanics(delta);
		
		if(controler.isActive("right")){
			goTo(1, delta);
		}
		
		if(controler.isActive("down")){
			goTo(2, delta);
		}
		
		if(controler.isActive("left")){
			goTo(3, delta);
		}
		object.setPosition(position);
	}
	
	private long jumpStartTime;
	private jumpState jumpstate = jumpState.falling;
	private float gravityConstant = 15f;
	private void jumpMecanics(long delta) {
		//start to jump
		if(onGround() && controler.isActive("jump")){
			jumpStartTime = System.currentTimeMillis();
			jumpstate = jumpState.jump;
		}
		float jumpTimeDelta = ((float)(System.currentTimeMillis() - jumpStartTime))/1000f;
		//handle Falling/ ySpeed
		float ySpeed = 0;
		if(jumpstate == jumpState.jump || jumpstate == jumpState.falling){
			ySpeed = -jumpTimeDelta*gravityConstant + 3f*delta/10;
			System.out.println(ySpeed);
			if(ySpeed < -3f){
				ySpeed = -3f*(delta/10);
			}
		}
		System.out.println(ySpeed);
		if(ySpeed < 0){
			jumpstate = jumpState.falling;
		}else{
			if(ySpeed > 0){
				jumpstate = jumpState.jump;
			}else{
				jumpstate = jumpState.onGround;
			}
			
		}
		System.out.println(jumpstate.toString());
		//check for Ground/ stop Falling
		if(jumpstate == jumpState.falling){
			newPosition.x = position.x;
			for(float f = 0; f <= ySpeed; f -= 0.01f){
				newPosition.y = position.y + f;
				if(onGround(newPosition)){
					System.out.print(ySpeed + "-->");
					ySpeed = f + 0.01f;
					System.out.println(ySpeed);
					jumpstate = jumpState.onGround;
					break;
				}
			}
		}
		position.y += ySpeed;
		updateHitbox(position);
	}

	private static final Vector2f direction[] = {
		new Vector2f(0,1),
		new Vector2f(1,0),
		new Vector2f(0,-1),
		new Vector2f(-1,0)
	};
	
	private Vector2f newPosition = new Vector2f();
	/**
	 * 0 = up, 1 = right, 2 = down, 3 = left
	 * @param direction
	 */
	private void goTo(int goTo, long delta){
		newPosition.x = position.x;
		newPosition.y = position.y;
		newPosition.x += (delta*speed*Renderer.getZoom()/10000)*direction[goTo].x;
		newPosition.y += (delta*speed*Renderer.getZoom()/10000)*direction[goTo].y;
		if(checkPosition(newPosition)){
			position.x = newPosition.x;
			position.y = newPosition.y;
		}
		
	}
	
	public boolean checkPosition(Vector2f position){
		updateHitbox(position);
		return !level.intersects(movedHitbox, 1);
	}
	
	public GameObject getGameObject(){
		return object;
	}
	
	public boolean onGround(){
		if(	(level.intersectsUp(movedHitbox[1], 1) <= 0.05f &&
			level.intersectsUp(movedHitbox[1], 1) > 0f	)||
			(level.intersectsUp(movedHitbox[2], 1) <= 0.05f &&
			level.intersectsUp(movedHitbox[2], 1) > 0f	)){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean onGround(Vector2f position){
		updateHitbox(position);
		if(	(level.intersectsUp(movedHitbox[1], 1) <= 0.02f &&
			level.intersectsUp(movedHitbox[1], 1) > 0f	)||
			(level.intersectsUp(movedHitbox[2], 1) <= 0.02f &&
			level.intersectsUp(movedHitbox[2], 1) > 0f	)){
			updateHitbox(this.position);
			return true;
		}else{
			updateHitbox(this.position);
			return false;
		}
	}
	
	private Vector2f movedHitbox[] = new Vector2f[4];
	/**
	 * @return float[0] = left up, ... float[3] = right up, counterclockwise
	 */
	private void updateHitbox(Vector2f position){
		for(int i = 0; i < 4; i++){
			movedHitbox[i] = Vector.add(position, hitbox[i]);
		}		
	}
	
	public Vector2f[] getHitbox(){
		return movedHitbox;
	}
	
	private enum jumpState{
		jump, onGround, falling;
	}
	
}
