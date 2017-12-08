package controller;

import model.Player;
import view.Frame;

public class GUI extends Thread {
	
	private boolean gameOver;
	private static Player player;
		
	public GUI() {
		this.gameOver = false;
	}
	
	public void run() {
		Frame f = new Frame();
		
		while(!this.gameOver)
		{
			f.getTray().updateTray(player);	
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	synchronized void update(Player player, boolean gameOver) {
		this.setGameOver(gameOver);
		this.setPlayer(player);
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public static Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		GUI.player = player;
	}

}
