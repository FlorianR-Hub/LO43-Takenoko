package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Goal;
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
		
		for(int i=0; i<12; i++)
			draw.add(new Tile(1,0));
		
		for(int i=0; i<10; i++)
			draw.add(new Tile(2,0));
		
		for(int i=0; i<8; i++)
			draw.add(new Tile(3,0));
		
		Collections.shuffle(draw);
		
		for(int i=0; i<15; i++)
		{
			goalsTile.add(new Goal(1, 10));
			goalsPanda.add(new Goal(2, 10));
			goalsGardener.add(new Goal(3, 10));
		}
		
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
		TrayView.deselectAllActions();
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
}
