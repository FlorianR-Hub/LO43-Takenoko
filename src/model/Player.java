package model;

import java.util.ArrayList;
import java.util.List;

import controller.GameManager;

public class Player {

	/**
	 * nbStones: Array with amount of stones of each type;
	 * nbBonus: Array with amount of bonus of each type;
	 */
	private int score;
	private int nbStones[] = new int[3];
	private int nbBonus[] = new int[3];
	private int nbRoad;
	private int nbGoalsCompleted;
	private int diceBonus;
	private int numPlayer;
	private List<Integer> actions;	
	private List<Goal> goals;
	private Tile tile;
	private boolean roundCompleted;
	
	public Player(int numPlayer) {
		this.setScore(0);
		for(int i=0; i<3; i++)
		{
			this.setNbStones(i, 0);
			this.setNbBonus(i, 0);
		}
			
		this.setNbRoad(0);
		this.setNbGoalsCompleted(0);
		this.setDiceBonus(0);
		this.setNumPlayer(numPlayer);
		this.setRoundCompleted(false);
		
		this.actions = new ArrayList<Integer>();
		this.goals = new ArrayList<Goal>();
				
		this.tile = new Tile(0,0);
		
		this.setHand();
	}
		
	public void setHand() {
		
		int random = (int) ( Math.random() * GameManager.getGoalsTile().size() );
		Goal goal;
		
		goal = GameManager.getGoalsTile().get(random);
		this.goals.add(goal);
		GameManager.getGoalsTile().remove(goal);
		
		goal = GameManager.getGoalsMonster().get(random);
		this.goals.add(GameManager.getGoalsMonster().get(random));
		GameManager.getGoalsMonster().remove(goal);
		
		goal = GameManager.getGoalsArchitect().get(random);
		this.goals.add(GameManager.getGoalsArchitect().get(random));
		GameManager.getGoalsArchitect().remove(goal);
	}

	public int getDiceBonus() {
		return diceBonus;
	}

	public void setDiceBonus(int diceBonus) {
		this.diceBonus = diceBonus;
	}
	
	public void randWeather() {
		this.diceBonus = 1 + (int)(Math.random() * 5);
	}

	public int getNbRoad() {
		return nbRoad;
	}

	public void setNbRoad(int nbRoad) {
		this.nbRoad = nbRoad;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getNbStones(int type) {
		return nbStones[type];
	}

	public void setNbStones(int type, int value) {
		this.nbStones[type] = value;
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
	
	public void clearActions() {
		this.actions.clear();
	}
	
	public List<Integer> getActions() {
		return actions;
	}
	
	public int getNbActionsAllowed() {
		return this.diceBonus == 1 ? 3 : 2;
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

	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}
	
	public List<Goal> getGoals(){
		return goals;
	}
	
	public void addGoal(Goal g) {
		goals.add(g);
	}

	public int getNbGoalsCompleted() {
		return nbGoalsCompleted;
	}

	public void setNbGoalsCompleted(int nbGoalsCompleted) {
		this.nbGoalsCompleted = nbGoalsCompleted;
	}
}
