package view;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import controller.GUI;
import controller.GameManager;
import model.Goal;

public class GoalButton extends Button{

	private static final long serialVersionUID = 1L;
	
	private Goal goal;

	public GoalButton(int type, int x, int y, int height, int width) {
		super(x,y,height,width);
		
		try {
			this.img = ImageIO.read(new File(path + "goal" + type + ".png"));
		}
		catch (IOException e) {
			 e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(this.img, 0, 0, this.getWidth(), this.getHeight(), this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		if(GameManager.getGoalsGardener().remove(goal)) {
			GUI.getPlayer().addGoal(goal);;
			goal.setOwner(GUI.getPlayer().getNumPlayer());
			GUI.getFrame().setEnabled(true);
			GUI.getDrawGoalView().setVisible(false);
		}		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
