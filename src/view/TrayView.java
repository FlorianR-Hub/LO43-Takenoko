package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.GameManager;
import model.Player;

public class TrayView extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private static List<ActionButton> buttons = new ArrayList<ActionButton>();
	private List<Picture> bonus = new ArrayList<Picture>();
	private List<Picture> images = new ArrayList<Picture>();
	
	private Picture tray = new Picture("tray.png", 20, 0, 740, 590);
	private Picture draw = new Picture("draw.png", 50, 660);
	
	private Picture bonus_one_more_action = new Picture("bonus_one_more_action.png", 41, 253, 121, 86);
	private Picture bonus_upgrade_house = new Picture("bonus_upgrade_house.png", 133, 253, 121, 86);
	private Picture bonus_same_action = new Picture("bonus_same_action.png", 226, 253, 121, 86);
	private Picture bonus_move_monster = new Picture("bonus_move_monster.png", 319, 253, 121, 86);
	private Picture bonus_help = new Picture("bonus_help.png", 412, 253, 121, 86);
	
	private ActionButton actionTiles = new ActionButton("tile", 85, 408, 90, 90);
	private ActionButton actionRoad = new ActionButton("road", 185, 408, 90, 90);
	private ActionButton actionMonster = new ActionButton("monster", 285, 408, 90, 90);
	private ActionButton actionArchitect = new ActionButton("architect", 385, 408, 90, 90);
	private ActionButton actionGoals = new ActionButton("goal", 485, 408, 90, 90);
	
	private Picture stoneGreen = new Picture("stoneGreen.png", 50, 565, 55, 55);
	private Picture stoneBlue = new Picture("stoneBlue.png", 95, 565, 55, 55);
	private Picture stoneOrange = new Picture("stoneOrange.png", 140, 565, 55, 55);
	private JLabel nbStoneGreen = new JLabel("0");
	private JLabel nbStoneBlue = new JLabel("0");
	private JLabel nbStoneOrange = new JLabel("0");
	
	private ActionButton bonusTools = new ActionButton("bonusTools", 210, 565, 55, 55);
	private ActionButton bonusDefense = new ActionButton("bonusDefense", 275, 565, 55, 55);
	private JLabel nbBonusTools = new JLabel("0");
	private JLabel nbBonusDefense = new JLabel("0");
	
	private ActionButton hand = new ActionButton("hand", 422, 550, 125, 125);
	
	private ActionButton roads = new ActionButton("roads", 340, 560, 60, 60);
	
	private ActionButton endTurn = new ActionButton("endTurn", 220, 670, 54, 194);
	
	private JLabel numPlayer = new JLabel("Player ");
	private JLabel winner = new JLabel("Player 0 wins !");
	private JLabel nbTurn = new JLabel ("Tour 0");
	private JLabel score = new JLabel("Score : ");
	private JLabel nbRoad = new JLabel("0");
	private JLabel nbCardsLeft = new JLabel(""+GameManager.getDraw().size());
	
	public TrayView() {
		this.setLayout(null);
							
		numPlayer.setBounds(275, 10, 100, 20);
		numPlayer.setFont(new Font("Arial", Font.BOLD, 20));
		this.add(numPlayer);
		
		winner.setBounds(185, 100, 400, 60);
		winner.setFont(new Font("Arial", Font.BOLD, 40));
		winner.setForeground(Color.white);
		winner.setVisible(false);
		this.add(winner);
		
		nbTurn.setBounds(530, 12, 100, 18);
		nbTurn.setFont(new Font("Arial", Font.BOLD, 18));
		this.add(nbTurn);
		
		score.setBounds(505, 258, 100, 17);
		score.setFont(new Font("Arial", Font.BOLD, 17));
		this.add(score);
		
		nbRoad.setBounds(358, 623, 120, 20);
		nbRoad.setFont(new Font("Arial", Font.BOLD, 16));
		this.add(nbRoad);
		
		nbCardsLeft.setBounds(75, 685, 20, 20);
		nbCardsLeft.setFont(new Font("Arial", Font.BOLD, 16));
		this.add(nbCardsLeft);
		
		nbStoneGreen.setBounds(72, 623, 20, 20);
		nbStoneGreen.setFont(new Font("Arial", Font.BOLD, 16));
		this.add(nbStoneGreen);
		
		nbStoneBlue.setBounds(117, 623, 20, 20);
		nbStoneBlue.setFont(new Font("Arial", Font.BOLD, 16));
		this.add(nbStoneBlue);
		
		nbStoneOrange.setBounds(162, 623, 20, 20);
		nbStoneOrange.setFont(new Font("Arial", Font.BOLD, 16));
		this.add(nbStoneOrange);
		
		nbBonusTools.setBounds(233, 623, 20, 20);
		nbBonusTools.setFont(new Font("Arial", Font.BOLD, 16));
		this.add(nbBonusTools);
		
		nbBonusDefense.setBounds(298, 623, 20, 20);
		nbBonusDefense.setFont(new Font("Arial", Font.BOLD, 16));
		this.add(nbBonusDefense);
		
		
		bonus.add(bonus_one_more_action);
		bonus.add(bonus_upgrade_house);
		bonus.add(bonus_same_action);
		bonus.add(bonus_move_monster);
		bonus.add(bonus_help);
		
		images.add(stoneGreen);
		images.add(stoneBlue);
		images.add(stoneOrange);
		
		buttons.add(actionTiles);
		buttons.add(actionRoad);
		buttons.add(actionMonster);
		buttons.add(actionArchitect);
		buttons.add(actionGoals);
		
		buttons.add(bonusTools);
		buttons.add(bonusDefense);
		buttons.add(hand);
		buttons.add(roads);
		buttons.add(endTurn);
		
		images.add(draw);
		images.add(tray);
		
		for(ActionButton button : buttons)
			this.add(button);
		
		for(Picture b : bonus)
		{
			this.add(b);
			b.setVisible(false);
		}
			
		for(Picture i : images)
			this.add(i);
		
		this.setBackground(TileView.COLOURBACK);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	public void updateTray(Player p) {
		this.numPlayer.setText("Player "+ p.getNumPlayer());
		this.nbTurn.setText("Turn "+ GameManager.getNbTurn());
		this.score.setText("Score : "+ p.getScore());
		this.nbRoad.setText(""+ p.getNbRoad());
		this.nbCardsLeft.setText(""+GameManager.getDraw().size());
		
		this.nbStoneGreen.setText(""+ p.getNbStones(0));
		this.nbStoneBlue.setText(""+ p.getNbStones(1));
		this.nbStoneOrange.setText(""+ p.getNbStones(2));
		
		this.nbBonusTools.setText(""+ p.getNbBonus(0));
		this.nbBonusDefense.setText(""+ p.getNbBonus(1));
				
		for(Picture b : bonus)
			b.setVisible(false);
		
		if(p.getDiceBonus() > 0)
			bonus.get(p.getDiceBonus() - 1).setVisible(true);
		
		if(GameManager.isGameOver())
		{
			this.winner.setText("Player " +  GameManager.getWinner().getNumPlayer()  + " wins !");
			this.winner.setVisible(true);
		}
		
		this.repaint();
	}
	
	public static void deselectAllButtons() {
		for(ActionButton button : buttons)
			button.setSelected(false);
	}
	
	public static void deselectAction(String name) {
		for(ActionButton action : buttons)
			if(action.getImgName() == name)
				action.setSelected(false);
	}
}
