package model;

import java.util.List;

public class Player {

	private int score;
	private int nbBamboos[] = new int[3];
	private int nbBonus[] = new int[3];
	private int irrigations;
	private int meteo; // 1=soleil / 2=pluie / 3=vent / 4=orage / 5=nuages / 6=? / 0=1er tour
	private List<Integer> actions;
	
	
	public Player() {
		this.setScore(0);
		for(int i=0; i<3; i++)
		{
			this.setNbBamboos(i, 0);
			this.setNbBonus(i, 0);
		}
			
		this.setIrrigations(0);
		this.setMeteo(0);
	}
	
	public Player(int score, int bambousVert, int bambousJaune, int bambousRose, int effetMeteo)
	{
		this.setScore(score);
		this.nbBamboos[0] = bambousVert;
		this.nbBamboos[1] = bambousJaune;
		this.nbBamboos[2] = bambousRose;
		this.setMeteo(effetMeteo);
	}

	public int getMeteo() {
		return meteo;
	}

	public void setMeteo(int meteo) {
		this.meteo = meteo;
	}

	public int getIrrigations() {
		return irrigations;
	}

	public void setIrrigations(int irrigations) {
		this.irrigations = irrigations;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getNbBamboos(int type) {
		return nbBamboos[type];
	}

	public void setNbBamboos(int type, int value) {
		this.nbBamboos[type] = value;
	}
	
	public int getNbBonus(int type) {
		return nbBonus[type];
	}

	public void setNbBonus(int type, int value) {
		this.nbBonus[type] = value;
	}

	public void addAction(int type) {
		this.actions.add(type);
	}
	
	public void applyBonus(Tile t, int type)
	{
		if(this.nbBonus[type] > 0)
		{
			t.setBonus(type);
			this.setNbBonus(type, this.getNbBonus(type) - 1);
		}
		else
		{
			System.out.println("Action Impossible ! Vous n'avez pas de bonus de ce type.\n");
		}
		
		
	}
	
	
}
