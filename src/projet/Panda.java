package projet;

public class Panda extends Character {

	public Panda(int x, int y) {
		super(x,y);
	}
	
	public void move(Tile p) {
		this.x = p.getX();
		this.y = p.getY();
		eat(p);
	}
	
	public void eat(Tile p) // useless ?
	{
		p.decrease();
	}
}
