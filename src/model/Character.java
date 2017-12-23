package model;

public abstract class Character {

	protected int posX;
	protected int posY;
	protected String name;		

	public Character(int x, int y, String name){
		this.posX = x;
		this.posY = y;
		this.name = name;
	}
	
	public abstract void move(Tile p);

	public abstract boolean isMoveAllowed(Tile p);
	
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}