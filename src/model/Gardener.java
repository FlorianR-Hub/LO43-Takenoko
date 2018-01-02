package model;

public class Gardener extends Character{

	public Gardener(int x, int y, String name) {
		super(x,y, name);
	}
	
	public void move(Tile t) {
		this.posX = t.getX();
		this.posY = t.getY();
		grow(t);
	}
	
	public void grow(Tile t) {
		t.increase();
		
		for(Tile tile : t.getAdjacentTiles())
			if(tile.getType() == t.getType())
				tile.increase();					
	}

	public boolean isMoveAllowed(Tile t) {		
		return true;
	}
}
