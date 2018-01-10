package view;

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
	private List<Picture> images = new ArrayList<Picture>();
	
	private Picture tray = new Picture("tray.png", 20, 0, 740, 590);
	private Picture draw = new Picture("draw.png", 50, 660);
	
	private Picture sun = new Picture("weather_sun.png", 40, 255);
	private Picture rain = new Picture("weather_rain.png", 132, 255);
	private Picture wind = new Picture("weather_wind.png", 224, 255);
	private Picture storm = new Picture("weather_storm.png", 320, 255);
	private Picture cloudy = new Picture("weather_cloudy.png", 412, 255);
	private Picture choice = new Picture("weather_choice.png", 508, 255);
	
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

	
	private ActionButton roads = new ActionButton("roads", 340, 560, 60, 60);
	
	private ActionButton endTurn = new ActionButton("endTurn", 220, 670, 54, 194);
	
	private JLabel numPlayer = new JLabel("Player ");
	private JLabel nbRoad = new JLabel("0");
	private JLabel nbCardsLeft = new JLabel(""+GameManager.getDraw().size());
	
	public TrayView() {
		this.setLayout(null);
							
		numPlayer.setBounds(275, 10, 100, 20);
		numPlayer.setFont(new Font("Arial", Font.BOLD, 20));
		this.add(numPlayer);
		
		
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
		
		
		images.add(sun);
		images.add(rain);
		images.add(wind);
		images.add(storm);
		images.add(cloudy);
		images.add(choice);
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
		buttons.add(roads);
		buttons.add(endTurn);
		
		images.add(draw);
		images.add(tray);
		
		for(ActionButton button : buttons)
			this.add(button);
		
		for(Picture w : images)
			this.add(w);
		
		
		this.setBackground(TileView.COLOURBACK);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	public void updateTray(Player p) {
		this.numPlayer.setText("Player "+ p.getNumPlayer());
		this.nbRoad.setText(""+ p.getNbRoad());
		this.nbCardsLeft.setText(""+GameManager.getDraw().size());
		
		this.nbStoneGreen.setText(""+ p.getNbStones(0));
		this.nbStoneBlue.setText(""+ p.getNbStones(1));
		this.nbStoneOrange.setText(""+ p.getNbStones(2));
		
		this.nbBonusTools.setText(""+ p.getNbBonus(0));
		this.nbBonusDefense.setText(""+ p.getNbBonus(1));
		
		this.sun.setBounds(40, 255, 80, 111);
		this.rain.setBounds(132, 255, 80, 111);
		this.wind.setBounds(224, 255, 80, 111);
		this.storm.setBounds(320, 255, 80, 111);
		this.cloudy.setBounds(412, 255, 80, 111);
		this.choice.setBounds(508, 255, 80, 111);
		
		switch(p.getWeather())
		{
			case 1:
				this.sun.setBounds(37, 255, 87, 121);
				break;
			case 2:
				this.rain.setBounds(129, 255, 87, 121);
				break;
			case 3:
				this.wind.setBounds(221, 255, 87, 121);
				break;
			case 4:
				this.storm.setBounds(317, 255, 87, 121);
				break;
			case 5:
				this.cloudy.setBounds(409, 255, 87, 121);
				break;
			case 6:
				this.choice.setBounds(505, 255, 87, 121);
				break;
			default:
				break;
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
