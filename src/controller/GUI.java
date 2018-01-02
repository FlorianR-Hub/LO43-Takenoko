package controller;

import java.util.List;

import model.Player;
import model.Tile;
import view.DrawView;
import view.Frame;

public class GUI extends Thread {
	
	private boolean gameOver;
	private static Player player;
	private static Frame frame;
	private static DrawView drawView;

	public GUI(List<Tile> draw) {
		this.gameOver = false;
		setDrawView(new DrawView(draw));
	}
	
	public void run() {
		frame = new Frame();
		while(!this.gameOver)
		{
			frame.getTray().updateTray(player);	
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

	public static Frame getFrame() {
		return frame;
	}

	public static DrawView getDrawView() {
		return drawView;
	}

	public static void setDrawView(DrawView drawView) {
		GUI.drawView = drawView;
	}
}
