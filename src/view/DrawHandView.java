package view;

import controller.GUI;
import model.Goal;

public class DrawHandView extends DrawView {

	private static final long serialVersionUID = 1L;
		
	public DrawHandView(String name, int width, int height) {
		super(name, width, height);
	}

	public void display() {
		GUI.getFrame().setEnabled(false);
						
		content.removeAll();
		
		int nbGoals = GUI.getPlayer().getGoals().size();
		
		this.setSize(1000, 360+(nbGoals/5)*340);
		
		for(int i=0; i< nbGoals; i++)
		{
			Goal goal = GUI.getPlayer().getGoals().get(i);
			content.add(new HandButton(goal, (i%4)*230, 25 + (i/4)*320, 300, 300));
		}
		
		this.setVisible(true);
	}
}
