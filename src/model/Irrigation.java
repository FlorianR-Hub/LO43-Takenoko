package model;

public class Irrigation extends GameObject{

	private int x2;
	private int y2;
	
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

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}
}
