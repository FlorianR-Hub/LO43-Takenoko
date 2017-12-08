package model;

import java.util.List;

public class Player {

	/**
	 * nbBamboos: Array with amount of bamboos of each type;
	 * nbBonus: Array with amount of bonus of each type;
	 * weather: 1=sun / 2=rain / 3=wind / 4=storm / 5=cloudy / 6=? / 0=1st round;
	 */
	private int score;
	private int nbBamboos[] = new int[3];
	private int nbBonus[] = new int[3];
	private int nbIrrig;
	private int weather;
	private int numPlayer;
	private List<Integer> actions;	
	private boolean roundCompleted;
	
	public Player(int numPlayer) {
		this.setScore(0);
		for(int i=0; i<3; i++)
		{
			this.setNbBamboos(i, 0);
			this.setNbBonus(i, 0);
		}
			
		this.setnbIrrig(0);
		this.setWeather(0);
		this.setNumPlayer(numPlayer);
		this.setRoundCompleted(false);
	}
	
	public Player(int score, int bambousVert, int bambousJaune, int bambousRose, int effetMeteo, int numPlayer)
	{
		this.setScore(score);
		this.nbBamboos[0] = bambousVert;
		this.nbBamboos[1] = bambousJaune;
		this.nbBamboos[2] = bambousRose;
		this.setWeather(effetMeteo);
		this.setNumPlayer(numPlayer);
		this.setRoundCompleted(false);
	}

	public int getWeather() {
		return weather;
	}

	public void setWeather(int weather) {
		this.weather = weather;
	}
	
	public void randWeather() {
		this.weather = 1 + (int)(Math.random() * 6);
	}

	public int getnbIrrig() {
		return nbIrrig;
	}

	public void setnbIrrig(int nbIrrig) {
		this.nbIrrig = nbIrrig;
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
		if(this.nbBonus[type] > 0){
			t.setBonus(type);
			this.setNbBonus(type, this.getNbBonus(type) - 1);
		}
		else{
			System.out.println("Action Impossible ! Vous n'avez pas de bonus de ce type.\n");
		}
	}

	public int getNumPlayer() {
		return numPlayer;
	}

	public void setNumPlayer(int numPlayer) {
		this.numPlayer = numPlayer;
	}

	public boolean isRoundCompleted() {
		return roundCompleted;
	}

	public void setRoundCompleted(boolean roundCompleted) {
		this.roundCompleted = roundCompleted;
	}
}
