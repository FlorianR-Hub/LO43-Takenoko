package model;

public class GoalsPanda extends Goal{

	/**
	 * color : 	1=green / 2=yellow / 3=pink / 4=multicolor
	 * nbBamboos[0] = green bamboos;
	 * nbBamboos[1] = yellow bamboos;
	 * nbBamboos[2] = pink bamboos;
	 */
	private int color;
	
	public GoalsPanda(int c, int p) {
		super(2);
		this.color = c;
		this.points = p;
	}

	public boolean isValid(Player p) {
		
		if(this.color < 4)
			if(p.getNbBamboos(color) == 2)
				isValid = true;
		else
			if(p.getNbBamboos(0) == 1 && p.getNbBamboos(1) == 1 && p.getNbBamboos(2) == 1)
				isValid = true;
		
		return isValid;
	}
}
