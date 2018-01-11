package model;

import java.util.ArrayList;
import java.util.List;

public class GoalsTile extends Goal{

	public GoalsTile() {
		super(1);
		
	}
	
	public boolean isValid(Player p) {

		return isValid;
	}

	public static List<Goal> initGoals(){
		List<Goal> goalsTile = new ArrayList<Goal>();
		
		return goalsTile;
	}
	
}
