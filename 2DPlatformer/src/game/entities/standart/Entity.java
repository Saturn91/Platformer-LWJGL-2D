package game.entities.standart;


public class Entity {
	private TexturedModel model;
	
	public Entity(TexturedModel model){
		this.model = model;
	}

	public TexturedModel getModel() {
		return model;
	}	
}
