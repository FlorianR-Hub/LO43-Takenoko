package model;

public class Bamboo extends GameObject{
	
	private int type;
	
	public Bamboo() {
		super();
		this.setType(0);
	}
	
	public Bamboo(int x, int y, int type) {
		super(x, y);
		this.setType(type);
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
