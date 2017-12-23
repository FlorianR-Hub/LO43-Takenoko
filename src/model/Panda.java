package model;

public class Panda extends Character {

	public Panda(int x, int y, String name) {
		super(x,y, name);
	}
	
	public void move(Tile p) {
		this.posX = p.getX();
		this.posY = p.getY();
		p.decrease(); //eat
	}
	
	public boolean isMoveAllowed(Tile p) {
		if(p.getType() == 0)
			return false;
		
		return true;
	}
}
