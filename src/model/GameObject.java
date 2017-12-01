package model;

public class GameObject {

	protected int x;
	protected int y;
	protected boolean isPlaced;
	
	public GameObject() {
		this.setX(0);
		this.setY(0);
	}
	
	public GameObject(int x, int y) {
		this.setX(x);
		this.setY(y);
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isPlaced() {
		return isPlaced;
	}

	public void setPlaced(boolean isPlaced) {
		this.isPlaced = isPlaced;
	}
}
