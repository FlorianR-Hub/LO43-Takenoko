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
	
	private List<Player> players = new ArrayList<Player>();
	private static List<Tile> draw = new ArrayList<Tile>();
	private static List<Goal> goalsTile = new ArrayList<Goal>();
	private static List<Goal> goalsPanda = new ArrayList<Goal>();
	private static List<Goal> goalsGardener = new ArrayList<Goal>();
	private int nbPlayers;
	private static boolean gameOver;
	private static GUI gui;
	
	public static boolean devMode = true;

	public GameManager() {
		GameManager.setGameOver(false);
		game();
	}
	
	// MAIN METHOD
	public static void main(String[] args) {
		GameManager gm = new GameManager();
		gui = new GUI();

		gm.start();
		gui.start();
		
	}
	
	public void game() {
		/*/Scanner keyboard = new Scanner(System.in);
		System.out.println("Entrez le nombre de joueurs : ");
		this.nbPlayers = keyboard.nextInt();
		keyboard.close();*/
		
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
		goalsPanda.addAll(GoalsMonster.initGoals());
		Collections.shuffle(goalsPanda);
		goalsGardener.addAll(GoalsArchitect.initGoals());
		Collections.shuffle(goalsGardener);
		goalsTile.addAll(GoalsTile.initGoals());
		Collections.shuffle(goalsTile);
		
		
		System.out.println(goalsGardener.size());
		System.out.println(goalsPanda.size());
		System.out.println(goalsTile.size());
		
		
		this.setNbPlayers(4);
		
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
			}
		}
	}
	
	public void playerTurn(Player p)
	{
		p.clearActions();
		TrayView.deselectAllButtons();
		p.setRoundCompleted(false);
		p.randWeather();
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
		this.players = players;
	}

	public static List<Tile> getDraw() {
		return draw;
	}

	public static void setDraw(List<Tile> draw) {
		GameManager.draw = draw;
	}
	
	public static List<Goal> getGoalsPanda(){
		return goalsPanda;
	}
	
	public static List<Goal> getGoalsGardener(){
		return goalsGardener;
	}
	
	public static List<Goal> getGoalsTile(){
		return goalsTile;
	}
	
	public static void setGoalsTile(List<Goal> goals) {
		GameManager.goalsTile = goals;
	}
	
	public static void setGoalsGardener(List<Goal> goals) {
		GameManager.goalsGardener = goals;
	}
	
	public static void setGoalsPanda(List<Goal> goals) {
		GameManager.goalsPanda = goals;
	}
}
