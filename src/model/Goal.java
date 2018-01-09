package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Goal {

	/**
	 * isValid: goal valid or not
	 * type   : 	1=tile / 2=panda / 3=gardener
	 * points : points given if goal validated  
	 * owner  : 	player who takes goal
	 */
	protected boolean isValid;
	protected int type;
	protected int points;
	protected int owner;
	
	public Goal() {
		this.isValid = false;
		this.type = 0;
		this.points = 0;
		this.owner = 0;
	}
	
	public Goal(int type) {
		this.isValid = false;
		this.type = type;
	}

	public abstract boolean isValid(Player p);

	public static List<Goal> initGoalsPanda(){
		List<Goal> goalsPanda = new ArrayList<Goal>();
		for(int i=0; i<5; i++)
			goalsPanda.add(new GoalsPanda(1, 3));
		for(int i=0; i<4; i++)
			goalsPanda.add(new GoalsPanda(2,4));
		for(int i=0; i<3; i++)
			goalsPanda.add(new GoalsPanda(3,5));
		for(int i=0; i<3; i++)
			goalsPanda.add(new GoalsPanda(4,6));
		return goalsPanda;
	}
	
	public static List<Goal> initGoalsGardener(){
		List<Goal> goalsGardener = new ArrayList<Goal>();
		goalsGardener.add(new GoalsGardener(1,4,1,0,5));
		goalsGardener.add(new GoalsGardener(1,4,1,1,4));
		goalsGardener.add(new GoalsGardener(1,4,1,2,3));
		goalsGardener.add(new GoalsGardener(1,4,1,3,4));
		goalsGardener.add(new GoalsGardener(1,4,2,0,6));
		goalsGardener.add(new GoalsGardener(1,4,2,1,5));
		goalsGardener.add(new GoalsGardener(1,4,2,2,4));
		goalsGardener.add(new GoalsGardener(1,4,2,3,5));
		goalsGardener.add(new GoalsGardener(1,4,3,0,7));
		goalsGardener.add(new GoalsGardener(1,4,3,1,6));
		goalsGardener.add(new GoalsGardener(1,4,3,2,5));
		goalsGardener.add(new GoalsGardener(1,4,3,3,6));
		goalsGardener.add(new GoalsGardener(2,3,1,0,6));
		goalsGardener.add(new GoalsGardener(3,3,2,0,7));
		goalsGardener.add(new GoalsGardener(4,3,3,0,8));
		return goalsGardener;
	}
	
	public static List<Goal> initGoalsTile(){
		List<Goal> goalsTile = new ArrayList<Goal>();
		
		return goalsTile;
	}
	
	public int getPoints() {
		return points;
	}

	public int getType() {
		return type;
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}
}
