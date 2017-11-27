package projet;

public class Gardener extends Character{

	public Gardener(int x, int y) {
		super(x,y);
	}
	
	public void move(Tile p) {
		this.x = p.getX();
		this.y = p.getY();
		grow(p);
	}
	
	public void grow(Tile p) {
		Tile[][] board = GameBoard.getBoard();
		p.increase();
		
		for (int i=0;i<GameBoard.BSIZE;i++)
			for (int j=0;j<GameBoard.BSIZE;j++)
				if(board[i][j].isAdjacent(p) && board[i][j].getType() == p.getType())
					board[i][j].increase();
					
	}
}
