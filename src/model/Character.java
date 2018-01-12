package model;

public abstract class Character {

	protected int posX;
	protected int posY;
	protected String name;		

	public Character(int x, int y, String name){
		this.posX = x;
		this.posY = y;
		this.name = name;
	}
	
	public abstract void move(Tile t);

	// permet de savoir si le d�placement A -> B est autoris�
	public boolean isMoveAllowed(Tile t) {
		
		if(this.isStraightLine(t)) { // permet de savoir si le deplacement est en ligne droite
			int direction = 0;
			int distance = 0;
			Tile currentTile = Board.getBoard()[posX][posY];
			
			if(posX%2 == 0) { // x pair
				if(posX > t.getX() && posY > t.getY()) {//b
					direction = 0;
					distance = posX - t.getX(); 
				}
				else if(posX == t.getX() && posY > t.getY()) {
					direction = 1;
					distance = posY - t.getY();
				}
				else if(posX < t.getX() && posY > t.getY()) { //a
					direction = 2;
					distance = t.getX() - posX;
				}
				else if(posX < t.getX() && posY <= t.getY()) { //a
					direction = 3;
					distance = t.getX() - posX;
				}
				else if(posX == t.getX() && posY < t.getY()) {
					direction = 4;
					distance = t.getY() - posY;
				}
				else if(posX > t.getX() && posY <= t.getY()) {//b
					direction = 5;
					distance = posX - t.getX(); 
				}
			}
			else { // x impair
				if(posX > t.getX() && posY >= t.getY()) {//b
					direction = 0;
					distance = posX - t.getX(); 
				}
				else if(posX == t.getX() && posY > t.getY()) {
					direction = 1;
					distance = posY - t.getY();
				}
				else if(posX < t.getX() && posY >= t.getY()) { //a
					direction = 2;
					distance = t.getX() - posX;
				}
				else if(posX < t.getX() && posY < t.getY()) { //a
					direction = 3;
					distance = t.getX() - posX;
				}
				else if(posX == t.getX() && posY < t.getY()) {
					direction = 4;
					distance = t.getY() - posY;
				}
				else if(posX > t.getX() && posY < t.getY()) {//b
					direction = 5;
					distance = posX - t.getX(); 
				}
			}
			
			for(int i=0; i<distance; i++) {
				if(currentTile.getAdjacentTiles()[direction].getType() != 0)
					currentTile = currentTile.getAdjacentTiles()[direction];
				else
					return false;
			}
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isStraightLine(Tile t) {
		
		if(posX == t.getX()) {
			return true;
		}
		else if(posX%2 == 0 && t.getX()%2 == 0 || posX%2 == 1 && t.getX()%2 == 1) {
			if( Math.abs(posY - t.getY()) == Math.abs(posX - t.getX())/2 )
				return true;	
		}
		else if(t.getY() - posY >= 0 && posX%2 == 0 && t.getX()%2 == 1) {
			//System.out.println("y+ pair > impair");
			if( Math.abs(posY - t.getY()) == (Math.abs(posX - t.getX())-1)/2 )
				return true;	
		}
		else if(t.getY() - posY > 0 && posX%2 == 1 && t.getX()%2 == 0) {
			//System.out.println("y+ impair > pair");
			if( Math.abs(posY - t.getY()) == (Math.abs(posX - t.getX())+1)/2 )
				return true;		
		}
		else if(t.getY() - posY < 0 && posX%2 == 0 && t.getX()%2 == 1) {
			//System.out.println("y- pair > impair");
			if( Math.abs(posY - t.getY()) == (Math.abs(posX - t.getX())+1)/2 )
				return true;	
		}
		else if(t.getY() - posY <= 0 && posX%2 == 1 && t.getX()%2 == 0) {
			//System.out.println("y- impair > pair");
			if( Math.abs(posY - t.getY()) == (Math.abs(posX - t.getX())-1)/2 )
				return true;		
		}
		return false;
	}
	
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}