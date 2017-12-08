package controller;

import java.util.ArrayList;
import java.util.List;
//import java.util.Scanner;
import model.Player;

public class GameManager extends Thread {
	
	public static List<Player> players = new ArrayList<Player>();
	private int nbPlayers;
	private static boolean gameOver;
	private static boolean canUpdate;
	private static GUI gui;

	public GameManager() {
		GameManager.setGameOver(false);
		GameManager.setCanUpdate(false);
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
		p.setRoundCompleted(false);
		p.randWeather();
	}

	public static boolean isGameOver() {
		return gameOver;
	}

	public static void setGameOver(boolean gameOver) {
		GameManager.gameOver = gameOver;
	}
	
	public static boolean isCanUpdate() {
		return canUpdate;
	}

	public static void setCanUpdate(boolean canUpdate) {
		GameManager.canUpdate = canUpdate;
	}
	
	public int getNbPlayers() {
		return nbPlayers;
	}

	public void setNbPlayers(int nbPlayers) {
		this.nbPlayers = nbPlayers;
	}
}
