package model;

public class Gardener extends Character{

	public Gardener(int x, int y) {
		super(x,y);
	}
	
	public void move(Tile p) {
		this.posX = p.getX();
		this.posY = p.getY();
		grow(p);
	}
	
	public void grow(Tile p) {
		Tile[][] board = Board.getBoard();
		p.increase();
		
		for (int i=0;i<Board.BSIZE;i++)
			for (int j=0;j<Board.BSIZE;j++)
				if(board[i][j].isAdjacent(p) && board[i][j].getType() == p.getType())
					board[i][j].increase();
					
	}
}
