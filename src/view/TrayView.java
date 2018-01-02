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
	
	private static List<ActionButton> actionButton = new ArrayList<ActionButton>();
	private List<Picture> images = new ArrayList<Picture>();
	
	private Picture tray = new Picture("tray.jpg", 20, 0);
	private Picture draw = new Picture("draw.png", 30, 500);
	
	private Picture sun = new Picture("weather_sun.png", 32, 325);
	private Picture rain = new Picture("weather_rain.png", 128, 326);
	private Picture wind = new Picture("weather_wind.png", 224, 328);
	private Picture storm = new Picture("weather_storm.png", 320, 326);
	private Picture cloudy = new Picture("weather_cloudy.png", 419, 326);
	private Picture choice = new Picture("weather_choice.png", 517, 326);
	
	private ActionButton actionTiles = new ActionButton("tile", 120, 50, 65, 65);
	private ActionButton actionRoad = new ActionButton("road", 200, 50, 65, 65);
	private ActionButton actionPanda = new ActionButton("panda", 280, 50, 65, 65);
	private ActionButton actionGardener = new ActionButton("gardener", 360, 50, 65, 65);
	private ActionButton actionGoals = new ActionButton("goal", 440, 50, 65, 65);
	
	private ActionButton irrigation = new ActionButton("irrigation", 500, 500, 81, 65);
	
	private ActionButton endTurn = new ActionButton("endTurn", 220, 670, 54, 194);
	
	private JLabel numPlayer = new JLabel("Player ");
	private JLabel nbIrrigations = new JLabel("Routes : 0");
	private JLabel nbCardsLeft = new JLabel(""+GameManager.getDraw().size());
	
	public TrayView() {
		this.setLayout(null);
							
		numPlayer.setBounds(275, 10, 100, 20);
		numPlayer.setFont(new Font("Arial", Font.BOLD, 20));
		this.add(numPlayer);
		
		
		nbIrrigations.setBounds(495, 470, 120, 20);
		nbIrrigations.setFont(new Font("Arial", Font.BOLD, 16));
		this.add(nbIrrigations);
		
		nbCardsLeft.setBounds(55, 525, 120, 20);
		nbCardsLeft.setFont(new Font("Arial", Font.BOLD, 16));
		this.add(nbCardsLeft);
		
		images.add(sun);
		images.add(rain);
		images.add(wind);
		images.add(storm);
		images.add(cloudy);
		images.add(choice);
		
		actionButton.add(actionTiles);
		actionButton.add(actionRoad);
		actionButton.add(actionPanda);
		actionButton.add(actionGardener);
		actionButton.add(actionGoals);
		
		actionButton.add(irrigation);
		actionButton.add(endTurn);
		
		images.add(draw);
		images.add(tray);
		
		for(ActionButton action : actionButton)
			this.add(action);
		
		for(Picture w : images)
			this.add(w);
		
		
		this.setBackground(TileView.COLOURBACK);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	public void updateTray(Player p) {
		this.numPlayer.setText("Player "+ p.getNumPlayer());
		this.nbIrrigations.setText("Routes : "+ p.getnbIrrig());
		this.nbCardsLeft.setText(""+GameManager.getDraw().size());
		
		this.sun.setBounds(32, 325, 80, 111);
		this.rain.setBounds(128, 326, 80, 111);
		this.wind.setBounds(224, 328, 80, 111);
		this.storm.setBounds(320, 326, 80, 111);
		this.cloudy.setBounds(419, 326, 80, 111);
		this.choice.setBounds(517, 326, 80, 111);
		
		switch(p.getWeather())
		{
			case 1:
				this.sun.setBounds(29, 322, 87, 121);
				break;
			case 2:
				this.rain.setBounds(125, 323, 87, 121);
				break;
			case 3:
				this.wind.setBounds(221, 325, 87, 121);
				break;
			case 4:
				this.storm.setBounds(317, 323, 87, 121);
				break;
			case 5:
				this.cloudy.setBounds(416, 323, 87, 121);
				break;
			case 6:
				this.choice.setBounds(514, 323, 87, 121);
				break;
			default:
				break;
		}
		
		this.repaint();
	}
	
	public static void deselectAllActions() {
		for(ActionButton action : actionButton)
			action.setSelected(false);
	}
	
	public static void deselectAction(String name) {
		for(ActionButton action : actionButton)
			if(action.getImgName() == name)
				action.setSelected(false);
	}
}
