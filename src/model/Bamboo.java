package model;

public class Bamboo extends GameObject{
	
	private int type;
	
	// CONSTRUCTORS -------------------------
	
	// Default
	public Bamboo() {
		super();
		this.setType(0);
	}
	
	// With position & type
	public Bamboo(int x, int y, int type){
		super(x, y);
		this.setType(type);
	}
	
	// GETTERS ------------------------------
	
	public int getType() {
		return type;
	}

	// SETTERS ------------------------------
	
	public void setType(int type) {
		this.type = type;
	}
}
