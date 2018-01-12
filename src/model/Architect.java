package model;

public class Architect extends Character{

	public Architect(int x, int y, String name) {
		super(x,y, name);
	}
	
	public void move(Tile t) {
		this.posX = t.getX();
		this.posY = t.getY();
		upgrade(t);
	}
	
	public void upgrade(Tile t) {
		if(t.isRoaded())
			t.increase();
		
		// Pour faire grandir les maisons adjacentes à la tuile t
		for(Tile tile : t.getAdjacentTiles()) 
			if(tile.getType() == t.getType() && tile.isRoaded())
				tile.increase();					
	}
}
