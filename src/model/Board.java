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
		board[5][4].setIrrigated(true);
		board[4][5].setIrrigated(true);
		board[4][6].setIrrigated(true);
		board[5][6].setIrrigated(true);
		board[6][6].setIrrigated(true);
		board[6][5].setIrrigated(true);
	}
	
	public static Tile[][] getBoard(){
		return board;
	}
}
