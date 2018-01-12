package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class GoalsMonster extends Goal{

	/**
	 * color : 	1=green / 2=blue / 3=orange / 4=multicolor
	 * nbStones[0] = green stones;
	 * nbStones[1] = blue stones;
	 * nbStones[2] = orange stones;
	 */
	private int color;
	
	public GoalsMonster(int c, int p) {
		super(2);
		this.color = c;
		this.points = p;
		
		try {
			this.img = ImageIO.read(new File(path + "goal_T2_C" + this.color + ".png"));
		}
		catch (IOException e) {
			 e.printStackTrace();
		}
	}

	public boolean isValid(Player p) {
		if(this.color < 4)
		{
			if(p.getNbStones(color - 1) >= 2) {
				p.setNbStones(color - 1, p.getNbStones(color)-2);
				isValid = true;
			}
		}
		else
		{
			if(p.getNbStones(0) >= 1 && p.getNbStones(1) >= 1 && p.getNbStones(2) >= 1) {
				p.setNbStones(0, p.getNbStones(0)-1);
				p.setNbStones(1, p.getNbStones(1)-1);
				p.setNbStones(2, p.getNbStones(2)-1);
				isValid = true;
			}
		}
			

		return isValid;
	}
	
	public static List<Goal> initGoals(){
		List<Goal> goals = new ArrayList<Goal>();
		for(int i=0; i<5; i++)
			goals.add(new GoalsMonster(1,3));
		for(int i=0; i<4; i++)
			goals.add(new GoalsMonster(2,4));
		for(int i=0; i<3; i++)
			goals.add(new GoalsMonster(3,5));
		for(int i=0; i<3; i++)
			goals.add(new GoalsMonster(4,6));
		
		return goals;
	}
}
