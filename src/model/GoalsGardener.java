package model;

public class GoalsGardener extends Goal{
	
	/**
	 * number:  number of bamboos plants needed
	 * color : 	1=green / 2=yellow / 3=pink
	 * bonus : 	1=enclosure / 2=fertilizer / 3=pool / 0=void
	 * size  : 	size of the bamboo
	 */
	private int number;
	private int color;
	private int size;
	private int bonus;
	
	public GoalsGardener(int n, int c, int s, int b, int p) {
		super(3);
		this.number = n;
		this.color = c;
		this.size = s;
		this.bonus = b;
		this.points = p;
	}
	
	public boolean isValid(Player p) {
		
		int nbValid = 0;
		
		for (int i=0;i<Board.BSIZE;i++)
			for (int j=0;j<Board.BSIZE;j++)
				if(Board.getBoard()[i][j].getOwner() == p.getNumPlayer() 
				&& Board.getBoard()[i][j].getType() == color
				&& Board.getBoard()[i][j].getSize() == size
				&& Board.getBoard()[i][j].getBonus() == bonus) {
					nbValid++;
				}
		
		if(nbValid == number)
			this.isValid = true;

		return isValid;
	}
}
