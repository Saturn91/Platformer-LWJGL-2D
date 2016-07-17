package game.entities.light;

public class LightEngine {
	private Light lights[];
	private boolean lightsStorage[];
	private int lightCounter;
	
	public LightEngine(int maxLights) {
		lights = new Light[maxLights];
		lightsStorage = new boolean[maxLights];
		lightCounter = 0;
	}
	
	public int addLight(Light light){
		for(int i = 0; i < lights.length; i++){
			if(!lightsStorage[i]){
				lightCounter++;
				lightsStorage[i] = true;
				lights[i] = light;
				return i;
			}
		}
		System.out.println("maxNum of Lights is " + lights.length + "!");
		return -1;
	}
	
	public void delLight(int i){
		if(lights[i] != null){
			lightsStorage[i] = false;
			lights[i] = null;
			lightCounter --;
		}		
	}
	
	public Light[] getLights(){
		Light lights[] = new Light[lightCounter];
		int thisCounter = 0;
		for(int i = 0; i < lights.length; i++){
			if(lightsStorage[i]){
				lights[thisCounter] = lights[i];
				thisCounter ++;
			}
		}
		
		return lights;
	}
	
	
}
