package model;

public class Gardener extends Character{

	public Gardener(int x, int y, String name) {
		super(x,y, name);
	}
	
	public void move(Tile p) {
		this.posX = p.getX();
		this.posY = p.getY();
		grow(p);
	}
	
	public void grow(Tile p) {
		p.increase();
		
		for(Tile tile : p.getAdjacentTiles())
			if(tile.getType() == p.getType())
				tile.increase();					
	}

	public boolean isMoveAllowed(Tile p) {		
		return true;
	}
}
