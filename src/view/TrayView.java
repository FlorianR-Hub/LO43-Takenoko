package view;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Player;

public class TrayView extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private GraphicsMeteo tray = new GraphicsMeteo("tray.jpg", 20, 0);
	private GraphicsMeteo soleil = new GraphicsMeteo("bouton_soleil.png", 32, 325);
	private GraphicsMeteo pluie = new GraphicsMeteo("bouton_pluie.png", 128, 326);
	private GraphicsMeteo vent = new GraphicsMeteo("bouton_vent.png", 224, 328);
	private GraphicsMeteo orage = new GraphicsMeteo("bouton_orage.png", 320, 326);
	private GraphicsMeteo nuages = new GraphicsMeteo("bouton_nuages.png", 419, 326);
	private GraphicsMeteo choix = new GraphicsMeteo("bouton_choix.png", 517, 326);
	
	private ActionButton bouton1 = new ActionButton("button", 80, 50, 65, 65);
	private ActionButton actionRoad = new ActionButton("road", 160, 50, 65, 65);
	private ActionButton actionPanda = new ActionButton("panda", 240, 50, 65, 65);
	private ActionButton actionGardener = new ActionButton("gardener", 320, 50, 65, 65);
	private ActionButton bouton5 = new ActionButton("button", 400, 50, 65, 65);
	private ActionButton bouton6 = new ActionButton("button", 480, 50, 65, 65);
	
	private ActionButton irrigation = new ActionButton("irrigation", 500, 500, 81, 65);
	
	private ActionButton endTurn = new ActionButton("endTurn", 220, 670, 54, 194);
	
	private JLabel text = new JLabel("Player ");
	private JLabel nbIrrigations = new JLabel("Routes : 0");
	
	public TrayView() {
		this.setLayout(null);
					
		nbIrrigations.setBounds(495, 470, 120, 20);
		nbIrrigations.setFont(new Font("Arial", Font.BOLD, 16));
		this.add(nbIrrigations);
		
		text.setBounds(275, 10, 100, 20);
		text.setFont(new Font("Arial", Font.BOLD, 20));
		this.add(text);
		
		
		nbIrrigations.setBounds(495, 470, 120, 20);
		nbIrrigations.setFont(new Font("Arial", Font.BOLD, 16));
		this.add(nbIrrigations);
		
		this.add(soleil);
		this.add(pluie);
		this.add(vent);
		this.add(orage);
		this.add(nuages);
		this.add(choix);
		
		this.add(bouton1);
		this.add(actionRoad);
		this.add(actionPanda);
		this.add(actionGardener);
		this.add(bouton5);
		this.add(bouton6);
		
		this.add(irrigation);
		this.add(endTurn);
		
		this.add(tray);
		this.setBackground(TileView.COLOURBACK);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	public void updateTray(Player p) {
		this.text.setText("Player "+ p.getNumPlayer());
		this.nbIrrigations.setText("Routes : "+ p.getnbIrrig());
		
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
}
