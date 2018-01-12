package view;

import controller.GUI;

public class DrawGoalView extends DrawView {

	private static final long serialVersionUID = 1L;
	
	private GoalButton goalTile;
	private GoalButton goalMonster;
	private GoalButton goalArchitect;
	
	public DrawGoalView(String name, int width, int height) {
		super(name, width, height);
	}

	public void display() {
		GUI.getFrame().setEnabled(false);
		
		content.removeAll();
		
		goalTile = new GoalButton(1, 20, 14, 300, 200);
		goalMonster = new GoalButton(2, 240, 14, 300, 200);
		goalArchitect = new GoalButton(3, 460, 14, 300, 200);
		
		
		content.add(goalTile);
		content.add(goalMonster);
		content.add(goalArchitect);
		
		this.setVisible(true);
	}
}
