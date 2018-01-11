package model;

import java.util.ArrayList;
import java.util.List;

public class GoalsTile extends Goal{

	private String form;
	private int color;
	
	public GoalsTile(String f, int c, int p) {
		super(1);
		this.form = f;
		this.color = c;
		this.points = p;
	}
	
	public boolean isValid(Player p) {

		switch(this.form) {
		case "line":
			for (int i=0;i<Board.BSIZE;i++)
				for (int j=0;j<Board.BSIZE;j++)
					if(Board.getBoard()[i][j].getType() == color)
						if(Board.getBoard()[i][j].getAdjacentTiles()[0].getType() == color && Board.getBoard()[i][j].getAdjacentTiles()[3].getType() == color
						|| Board.getBoard()[i][j].getAdjacentTiles()[1].getType() == color && Board.getBoard()[i][j].getAdjacentTiles()[4].getType() == color
						|| Board.getBoard()[i][j].getAdjacentTiles()[2].getType() == color && Board.getBoard()[i][j].getAdjacentTiles()[5].getType() == color)
							this.isValid = true;
			break;
		case "diamond":
			int color1, color2;
			switch(color) {
			case 23:
				color1 = 2;
				color2 = 3;
				break;
			case 31:
				color1 = 3;
				color2 = 1;
				break;
			case 21:
				color1 = 2;
				color2 = 1;
				break;
			default:
				color1 = color2 = color;
			}
			for (int i=0;i<Board.BSIZE;i++)
				for (int j=0;j<Board.BSIZE;j++)
					if(Board.getBoard()[i][j].getType() == color1)
						if(Board.getBoard()[i][j].getAdjacentTiles()[0].getType() == color1 && Board.getBoard()[i][j].getAdjacentTiles()[1].getType() == color2 && Board.getBoard()[i][j].getAdjacentTiles()[0].getAdjacentTiles()[1].getType() == color2                    
						|| Board.getBoard()[i][j].getAdjacentTiles()[1].getType() == color1 && Board.getBoard()[i][j].getAdjacentTiles()[2].getType() == color2 && Board.getBoard()[i][j].getAdjacentTiles()[1].getAdjacentTiles()[2].getType() == color2
						|| Board.getBoard()[i][j].getAdjacentTiles()[2].getType() == color1 && Board.getBoard()[i][j].getAdjacentTiles()[3].getType() == color2 && Board.getBoard()[i][j].getAdjacentTiles()[2].getAdjacentTiles()[3].getType() == color2
						|| Board.getBoard()[i][j].getAdjacentTiles()[3].getType() == color1 && Board.getBoard()[i][j].getAdjacentTiles()[4].getType() == color2 && Board.getBoard()[i][j].getAdjacentTiles()[3].getAdjacentTiles()[4].getType() == color2
						|| Board.getBoard()[i][j].getAdjacentTiles()[4].getType() == color1 && Board.getBoard()[i][j].getAdjacentTiles()[5].getType() == color2 && Board.getBoard()[i][j].getAdjacentTiles()[4].getAdjacentTiles()[5].getType() == color2
						|| Board.getBoard()[i][j].getAdjacentTiles()[5].getType() == color1 && Board.getBoard()[i][j].getAdjacentTiles()[0].getType() == color2 && Board.getBoard()[i][j].getAdjacentTiles()[5].getAdjacentTiles()[0].getType() == color2)
							this.isValid = true;
			break;
		case "triangle":
			for (int i=0;i<Board.BSIZE;i++)
				for (int j=0;j<Board.BSIZE;j++)
					if(Board.getBoard()[i][j].getType() == color)
						if(Board.getBoard()[i][j].getAdjacentTiles()[0].getType() == color && Board.getBoard()[i][j].getAdjacentTiles()[1].getType() == color
						|| Board.getBoard()[i][j].getAdjacentTiles()[1].getType() == color && Board.getBoard()[i][j].getAdjacentTiles()[2].getType() == color
						|| Board.getBoard()[i][j].getAdjacentTiles()[2].getType() == color && Board.getBoard()[i][j].getAdjacentTiles()[3].getType() == color
						|| Board.getBoard()[i][j].getAdjacentTiles()[3].getType() == color && Board.getBoard()[i][j].getAdjacentTiles()[4].getType() == color
						|| Board.getBoard()[i][j].getAdjacentTiles()[4].getType() == color && Board.getBoard()[i][j].getAdjacentTiles()[5].getType() == color
						|| Board.getBoard()[i][j].getAdjacentTiles()[5].getType() == color && Board.getBoard()[i][j].getAdjacentTiles()[0].getType() == color)
							this.isValid = true;	
			break;
		case "moon":
			for (int i=0;i<Board.BSIZE;i++)
				for (int j=0;j<Board.BSIZE;j++)
					if(Board.getBoard()[i][j].getType() == color)
						if(Board.getBoard()[i][j].getAdjacentTiles()[0].getType() == color && Board.getBoard()[i][j].getAdjacentTiles()[2].getType() == color
						|| Board.getBoard()[i][j].getAdjacentTiles()[2].getType() == color && Board.getBoard()[i][j].getAdjacentTiles()[4].getType() == color
						|| Board.getBoard()[i][j].getAdjacentTiles()[4].getType() == color && Board.getBoard()[i][j].getAdjacentTiles()[0].getType() == color
						|| Board.getBoard()[i][j].getAdjacentTiles()[1].getType() == color && Board.getBoard()[i][j].getAdjacentTiles()[3].getType() == color
						|| Board.getBoard()[i][j].getAdjacentTiles()[3].getType() == color && Board.getBoard()[i][j].getAdjacentTiles()[5].getType() == color
						|| Board.getBoard()[i][j].getAdjacentTiles()[5].getType() == color && Board.getBoard()[i][j].getAdjacentTiles()[1].getType() == color)
							this.isValid = true;		
			break;
		}
		return isValid;
	}

	public static List<Goal> initGoals(){
		List<Goal> goals = new ArrayList<Goal>();
		goals.add(new GoalsTile("line",1,2));
		goals.add(new GoalsTile("line",2,3));
		goals.add(new GoalsTile("line",3,4));
		goals.add(new GoalsTile("moon",1,2));
		goals.add(new GoalsTile("moon",2,3));
		goals.add(new GoalsTile("moon",3,4));
		goals.add(new GoalsTile("triangle",1,2));
		goals.add(new GoalsTile("triangle",2,3));
		goals.add(new GoalsTile("triangle",3,4));
		goals.add(new GoalsTile("diamond",1,3));
		goals.add(new GoalsTile("diamond",2,4));
		goals.add(new GoalsTile("diamond",3,5));
		goals.add(new GoalsTile("diamond",23,5));
		goals.add(new GoalsTile("diamond",31,4));
		goals.add(new GoalsTile("diamond",21,3));
		return goals;
	}
}
