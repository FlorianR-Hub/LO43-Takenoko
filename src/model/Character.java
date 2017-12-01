package model;

public abstract class Character {

	protected int posX;
	protected int posY;
		
	public Character(int x, int y){
		this.posX = x;
		this.posY = y;
	}
	
	public abstract void move(Tile p);
}