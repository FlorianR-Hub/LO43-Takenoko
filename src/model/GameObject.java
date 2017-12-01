package model;

public class GameObject {

	protected int posX;
	protected int posY;
	protected boolean isPlaced;
	
	// CONSTRUCTORS -------------------------
	
	public GameObject() {
		this.posX = 0;
		this.posY = 0;
	}
	
	public GameObject(int x, int y) {
		this.posX = x;
		this.posY = y;
	}
	
	// GETTERS ------------------------------
	
	public int getX() {
		return posX;
	}

	public int getY() {
		return posY;
	}
	
	public boolean isPlaced(){
		return isPlaced;
	}
	
	// SETTERS ------------------------------
	
	public void setX(int x) {
		this.posX = x;
	}
	
	public void setY(int y) {
		this.posY = y;
	}

	public void setPlaced(boolean bool) {
		this.isPlaced = bool;
	}
}
