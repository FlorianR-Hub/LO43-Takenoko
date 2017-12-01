package model;

public class Irrigation extends GameObject{

	private int posX2;
	private int posY2;
	
	public Irrigation() {
		super();
		this.setX2(0);
		this.setY2(0);
	}
	
	public Irrigation(int x, int y, int x2, int y2) {
		super(x,y);
		this.setX2(x2);
		this.setY2(y2);
	}
		
	public void place() {
	}

	public int getPosX2() {
		return posX2;
	}

	public void setX2(int x2) {
		this.posX2 = x2;
	}

	public int getY2() {
		return posY2;
	}

	public void setY2(int y2) {
		this.posY2 = y2;
	}
}
