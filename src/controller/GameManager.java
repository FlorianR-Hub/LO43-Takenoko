package controller;

import java.util.List;
import javax.swing.SwingUtilities;

import model.Player;

public class GameManager {

	private List<Player> players;

	public GameManager() {
		
	}
	
	// MAIN METHOD
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				new Frame();
			}
		});
	}
}
