package model;

import controller.GUI;

public class Monster extends Character {

	public Monster(int x, int y, String name) {
		super(x,y, name);
	}
	
	public void move(Tile t) {
		this.posX = t.getX();
		this.posY = t.getY();
		this.eat(t);
	}
	
	public void eat(Tile t) {
		int type = t.getType() - 1;
		
		if(t.getSize() > 0 && t.getBonus() != 2)
			GUI.getPlayer().setNbStones(type, GUI.getPlayer().getNbStones(type) + 1);
		
		t.decrease();			
	}
	
	public boolean isMoveAllowed(Tile t) {
		if(t.getType() == 0)
			return false;
		
		return true;
	}
}
