package view;

import javax.swing.JFrame;
import controller.GUI;

public class DrawGoalView extends DrawView {

	private static final long serialVersionUID = 1L;
	
	private GoalButton goalTile;
	private GoalButton goalPanda;
	private GoalButton goalGardener;
	
	public DrawGoalView(String name, int width, int height) {
		super(name, width, height);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}

	public void display() {
		GUI.getFrame().setEnabled(false);
		
		goalTile = new GoalButton(1, 20, 14, 300, 200);
		goalPanda = new GoalButton(2, 240, 14, 300, 200);
		goalGardener = new GoalButton(3, 460, 14, 300, 200);
		
		content.removeAll();
		content.add(goalTile);
		content.add(goalPanda);
		content.add(goalGardener);
		
		this.setVisible(true);
	}
}
