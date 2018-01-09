package model;

public class Board {
	
	public final static int BSIZE = 7;
	
	private static Tile[][] board = new Tile[BSIZE][BSIZE];
	
	public static void initBoard(){

		for (int i=0;i<BSIZE;i++) {
			for (int j=0;j<BSIZE;j++) {
				board[i][j]= new Tile(i, j, 0, 0, false);
			}
		}

		board[3][3] = new Tile(3,3,4,0, true); // Pond : starting tile
		Tile[] adjTiles = board[3][3].getAdjacentTiles();
		
		for(Tile t : adjTiles) {
			board[5][5].setIrrigations(t);
			t.setIrrigated(true);
		};
	}
	
	public static Tile[][] getBoard(){
		return board;
	}
}
