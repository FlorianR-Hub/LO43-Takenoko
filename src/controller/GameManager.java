package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Goal;
import model.GoalsArchitect;
import model.GoalsMonster;
import model.GoalsTile;
//import java.util.Scanner;
import model.Player;
import model.Tile;
import view.TrayView;

public class GameManager extends Thread {
	
	private static List<Player> players = new ArrayList<Player>();
	private static List<Tile> draw = new ArrayList<Tile>();
	private static List<Goal> goalsTile = new ArrayList<Goal>();
	private static List<Goal> goalsMonster = new ArrayList<Goal>();
	private static List<Goal> goalsArchitect = new ArrayList<Goal>();
	private static int nbTurn = 0;
	private int nbGoalsToEndGame;
	private int nbPlayers;
	private static boolean gameOver;
	private static GUI gui;
	
	
	public static boolean devMode = true;
	

	public GameManager() {
		GameManager.setGameOver(false);
		gameStart();
	}
	
	// MAIN METHOD
	public static void main(String[] args) {
		GameManager gm = new GameManager();
		gui = new GUI();

		gm.start();
		gui.start();
		
	}
	
	public void gameStart() {
		/*/Scanner keyboard = new Scanner(System.in);
		System.out.println("Entrez le nombre de joueurs : ");
		this.nbPlayers = keyboard.nextInt();
		keyboard.close();*/
		
		this.setNbPlayers(4);
		
		switch(this.getNbPlayers())
		{
			case 2:
				this.setNbGoalsToEndGame(9);
				break;
			case 3:
				this.setNbGoalsToEndGame(8);
				break;
			case 4:
				this.setNbGoalsToEndGame(7);
				break;
			default:
				this.setNbGoalsToEndGame(7);
				break;
		}
		
		
		for(int i=0; i<8; i++) {
			draw.add(new Tile(1,0));
		}
		draw.add(new Tile(1,1));
		draw.add(new Tile(1,2));
		draw.add(new Tile(1,2));
		
		for(int i=0; i<7; i++) {
			draw.add(new Tile(2,0));
		}
		draw.add(new Tile(2,1));
		draw.add(new Tile(2,2));
		
		for(int i=0; i<5; i++) {
			draw.add(new Tile(3,0));
		}
		draw.add(new Tile(3,1));
		draw.add(new Tile(3,2));
		
		Collections.shuffle(draw);
		
		// Initialize Goals' Lists
		goalsMonster.addAll(GoalsMonster.initGoals());
		Collections.shuffle(goalsMonster);
		goalsArchitect.addAll(GoalsArchitect.initGoals());
		Collections.shuffle(goalsArchitect);
		goalsTile.addAll(GoalsTile.initGoals());
		Collections.shuffle(goalsTile);		
				
		for(int i=0; i<this.getNbPlayers(); i++)
			players.add(new Player(i+1));
	}
	
	
	public void run() {
		while(!GameManager.isGameOver())
		{
			for(Player p : players)
			{
				playerTurn(p);
				gui.update(p, gameOver);
				
				while(!p.isRoundCompleted())
				{
					try {
						sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				if(p.getNbGoalsCompleted() >= this.getNbGoalsToEndGame())
					setGameOver(true);
			}
			
			setNbTurn(getNbTurn() + 1);
		}
					
		System.out.println("Le gagnant est le joueur "+ getWinner().getNumPlayer());
	}
	
	public void playerTurn(Player p)
	{
		p.clearActions();
		TrayView.deselectAllButtons();
		p.setRoundCompleted(false);
		if(getNbTurn() > 0)
			p.randWeather();
	}
	
	public static Player getWinner() {
		
		Player winner = new Player(0);
		for(Player p : players)
			if(p.getScore() > winner.getScore())
				winner = p;
		
		return winner;
	}

	public static boolean isGameOver() {
		return gameOver;
	}

	public static void setGameOver(boolean gameOver) {
		GameManager.gameOver = gameOver;
	}
	
	public int getNbPlayers() {
		return nbPlayers;
	}

	public void setNbPlayers(int nbPlayers) {
		this.nbPlayers = nbPlayers;
	}
	
	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		GameManager.players = players;
	}

	public static List<Tile> getDraw() {
		return draw;
	}

	public static void setDraw(List<Tile> draw) {
		GameManager.draw = draw;
	}
	
	public static List<Goal> getGoalsMonster(){
		return goalsMonster;
	}
	
	public static List<Goal> getGoalsArchitect(){
		return goalsArchitect;
	}
	
	public static List<Goal> getGoalsTile(){
		return goalsTile;
	}
	
	public static void setGoalsTile(List<Goal> goals) {
		GameManager.goalsTile = goals;
	}
	
	public static void setGoalsArchitect(List<Goal> goals) {
		GameManager.goalsArchitect = goals;
	}
	
	public static void setGoalsMonster(List<Goal> goals) {
		GameManager.goalsMonster = goals;
	}

	public static int getNbTurn() {
		return nbTurn;
	}

	public static void setNbTurn(int nbTurn) {
		GameManager.nbTurn = nbTurn;
	}

	public int getNbGoalsToEndGame() {
		return nbGoalsToEndGame;
	}

	public void setNbGoalsToEndGame(int nbGoalsToEndGame) {
		this.nbGoalsToEndGame = nbGoalsToEndGame;
	}
}
