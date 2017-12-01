package model;

public class Panda extends Character {

	public Panda(int x, int y) {
		super(x,y);
	}
	
	public void move(Tile p) {
		this.posX = p.getX();
		this.posY = p.getY();
		eat(p);
	}
	
	public void eat(Tile p) // useless ?
	{
		p.decrease();
	}
}
