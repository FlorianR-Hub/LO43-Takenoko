package model;

import java.util.ArrayList;
import java.util.List;

public class GoalsArchitect extends Goal{
	
	/**
	 * number:  number of bamboos plants needed
	 * color : 	1=green / 2=yellow / 3=pink
	 * bonus : 	1=enclosure / 2=fertilizer / 3=pool / 0=void
	 * size  : 	size of the bamboo
	 */
	private int number;
	private int color;
	private int size;
	private int bonus;
	
	public GoalsArchitect(int n, int s, int c, int b, int p) {
		super(3);
		this.number = n;
		this.size = s;
		this.color = c;
		this.bonus = b;
		this.points = p;
	}
	
	public boolean isValid(Player p) {
		
		int nbValid = 0;
		
		for (int i=0;i<Board.BSIZE;i++)
			for (int j=0;j<Board.BSIZE;j++)
				if(Board.getBoard()[i][j].getOwner() == p.getNumPlayer() 
				&& Board.getBoard()[i][j].getType() == color
				&& Board.getBoard()[i][j].getSize() == size
				&& Board.getBoard()[i][j].getBonus() == bonus) {
					nbValid++;
				}
		
		if(nbValid == number)
			this.isValid = true;

		return isValid;
	}
	
	public static List<Goal> initGoals(){
		List<Goal> goals = new ArrayList<Goal>();
		goals.add(new GoalsArchitect(1,4,1,0,5));
		goals.add(new GoalsArchitect(1,4,1,1,4));
		goals.add(new GoalsArchitect(1,4,1,2,3));
		goals.add(new GoalsArchitect(1,4,1,3,4));
		goals.add(new GoalsArchitect(1,4,2,0,6));
		goals.add(new GoalsArchitect(1,4,2,1,5));
		goals.add(new GoalsArchitect(1,4,2,2,4));
		goals.add(new GoalsArchitect(1,4,2,3,5));
		goals.add(new GoalsArchitect(1,4,3,0,7));
		goals.add(new GoalsArchitect(1,4,3,1,6));
		goals.add(new GoalsArchitect(1,4,3,2,5));
		goals.add(new GoalsArchitect(1,4,3,3,6));
		goals.add(new GoalsArchitect(2,3,1,0,6));
		goals.add(new GoalsArchitect(3,3,2,0,7));
		goals.add(new GoalsArchitect(4,3,3,0,8));
		return goals;
	}
}
