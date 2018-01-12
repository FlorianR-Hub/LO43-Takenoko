package view;

import java.awt.event.MouseEvent;

import controller.GUI;
import model.Goal;
import model.Player;

public class HandButton extends Button {

	private static final long serialVersionUID = 1L;
	
	private Goal goal;

	public HandButton(Goal goal, int x, int y, int height, int width) {
		super(x,y,height,width);
		
		this.setGoal(goal);
		
		this.img = goal.getImg();
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		Player player = GUI.getPlayer();
		
		if(goal.isValid(player)) {
			player.setScore(player.getScore() + goal.getPoints());
			player.getGoals().remove(goal);
			player.setNbGoalsCompleted(player.getNbGoalsCompleted() + 1);
		}
		
		GUI.getFrame().setEnabled(true);
		GUI.getDrawHandView().setVisible(false);
		TrayView.deselectAction("hand");
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}

	public Goal getGoal() {
		return goal;
	}

	public void setGoal(Goal goal) {
		this.goal = goal;
	}

}
