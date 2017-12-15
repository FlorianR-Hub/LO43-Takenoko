package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
		
		List<Tile> adjCases = new ArrayList<Tile>();
		adjCases = board[5][5].getAdjacentTiles();
		
		Iterator<Tile> itr = adjCases.iterator();
        while(itr.hasNext()){
			itr.next().setIrrigated(true);
        }
	}
	
	public static Tile[][] getBoard(){
		return board;
	}
}
