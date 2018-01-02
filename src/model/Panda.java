package model;

public class Panda extends Character {

	public Panda(int x, int y, String name) {
		super(x,y, name);
	}
	
	public void move(Tile t) {
		this.posX = t.getX();
		this.posY = t.getY();
		t.decrease(); //eat
	}
	
	public boolean isMoveAllowed(Tile t) {
		if(t.getType() == 0)
			return false;
		
		return true;
	}
}
