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
	private List<GraphicsMeteo> images = new ArrayList<GraphicsMeteo>();
	
	private GraphicsMeteo tray = new GraphicsMeteo("tray.jpg", 20, 0);
	private GraphicsMeteo draw = new GraphicsMeteo("draw.png", 30, 500);
	
	private GraphicsMeteo soleil = new GraphicsMeteo("bouton_soleil.png", 32, 325);
	private GraphicsMeteo pluie = new GraphicsMeteo("bouton_pluie.png", 128, 326);
	private GraphicsMeteo vent = new GraphicsMeteo("bouton_vent.png", 224, 328);
	private GraphicsMeteo orage = new GraphicsMeteo("bouton_orage.png", 320, 326);
	private GraphicsMeteo nuages = new GraphicsMeteo("bouton_nuages.png", 419, 326);
	private GraphicsMeteo choix = new GraphicsMeteo("bouton_choix.png", 517, 326);
	
	private ActionButton actionTiles = new ActionButton("tile", 120, 50, 65, 65);
	private ActionButton actionRoad = new ActionButton("road", 200, 50, 65, 65);
	private ActionButton actionPanda = new ActionButton("panda", 280, 50, 65, 65);
	private ActionButton actionGardener = new ActionButton("gardener", 360, 50, 65, 65);
	private ActionButton actionGoals = new ActionButton("button", 440, 50, 65, 65);
	
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
		
		images.add(soleil);
		images.add(pluie);
		images.add(vent);
		images.add(orage);
		images.add(nuages);
		images.add(choix);
		
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
		
		for(GraphicsMeteo w : images)
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
		
		this.soleil.setBounds(32, 325, 80, 111);
		this.pluie.setBounds(128, 326, 80, 111);
		this.vent.setBounds(224, 328, 80, 111);
		this.orage.setBounds(320, 326, 80, 111);
		this.nuages.setBounds(419, 326, 80, 111);
		this.choix.setBounds(517, 326, 80, 111);
		
		switch(p.getWeather())
		{
			case 1:
				this.soleil.setBounds(29, 322, 87, 121);
				break;
			case 2:
				this.pluie.setBounds(125, 323, 87, 121);
				break;
			case 3:
				this.vent.setBounds(221, 325, 87, 121);
				break;
			case 4:
				this.orage.setBounds(317, 323, 87, 121);
				break;
			case 5:
				this.nuages.setBounds(416, 323, 87, 121);
				break;
			case 6:
				this.choix.setBounds(514, 323, 87, 121);
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
