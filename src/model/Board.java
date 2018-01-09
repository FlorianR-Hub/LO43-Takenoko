package model;

public class Board {
	
	public final static int BSIZE = 11;
	
	private static Tile[][] board = new Tile[BSIZE][BSIZE];
	
	public static void initBoard(){

		for (int i=0;i<BSIZE;i++) {
			for (int j=0;j<BSIZE;j++) {
				board[i][j]= new Tile(i, j, 0, 0, false);
			}
		}

		board[5][5] = new Tile(5,5,4,0, true); // Pond : starting tile
		Tile[] adjTiles = new Tile[6];
		adjTiles = board[5][5].getAdjacentTiles();
		
		for(Tile t : adjTiles) {
			board[5][5].setIrrigations(t);
		}
		
		Tile[] adjCases = board[5][5].getAdjacentTiles();
			
		for(Tile t : adjCases)
			if(t != null)
				t.setIrrigated(true);
	}
	
	public static Tile[][] getBoard(){
		return board;
	}
}
