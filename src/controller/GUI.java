package controller;

import model.Player;
import view.DrawGoalView;
import view.DrawHandView;
import view.DrawTileView;
import view.Frame;

public class GUI extends Thread {
	
	private boolean gameOver;
	private static Player player;
	private static Frame frame;
	private static DrawTileView drawTileView;
	private static DrawGoalView drawGoalView;
	private static DrawHandView drawHandView;

	public GUI() {
		this.gameOver = false;
		drawTileView = new DrawTileView("Draw Tile", 512, 200);
		drawGoalView = new DrawGoalView("Draw Goal", 680, 360);
		drawHandView = new DrawHandView("Hand", 1000, 360);
	}
	
	public void run() {
		frame = new Frame();
		while(!this.gameOver)
		{
			frame.getTray().updateTray(player);	 // Mise à jour régulière du plateau de jeu pendant le tour d'un joueur
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

	public static DrawTileView getDrawTileView() {
		return drawTileView;
	}

	public static void setDrawTileView(DrawTileView drawTileView) {
		GUI.drawTileView = drawTileView;
	}

	public static DrawGoalView getDrawGoalView() {
		return drawGoalView;
	}

	public static void setDrawGoalView(DrawGoalView drawGoalView) {
		GUI.drawGoalView = drawGoalView;
	}

	public static DrawHandView getDrawHandView() {
		return drawHandView;
	}

	public static void setDrawHandView(DrawHandView drawHandView) {
		GUI.drawHandView = drawHandView;
	}
}
