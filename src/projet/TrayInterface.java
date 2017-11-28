package projet;

import java.awt.Graphics;
import javax.swing.JPanel;

public class TrayInterface extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private GraphicsMeteo tray = new GraphicsMeteo("tray.jpg", 20, 0);
	private GraphicsMeteo soleil = new GraphicsMeteo("bouton_soleil.png", 32, 325);
	private GraphicsMeteo pluie = new GraphicsMeteo("bouton_pluie.png", 128, 326);
	private GraphicsMeteo vent = new GraphicsMeteo("bouton_vent.png", 224, 328);
	private GraphicsMeteo orage = new GraphicsMeteo("bouton_orage.png", 320, 326);
	private GraphicsMeteo nuages = new GraphicsMeteo("bouton_nuages.png", 419, 326);
	private GraphicsMeteo choix = new GraphicsMeteo("bouton_choix.png", 517, 326);
	
	private ActionButton bouton1 = new ActionButton("button", 80, 50, 65, 65);
	private ActionButton bouton2 = new ActionButton("button", 160, 50, 65, 65);
	private ActionButton bouton3 = new ActionButton("button", 240, 50, 65, 65);
	private ActionButton bouton4 = new ActionButton("button", 320, 50, 65, 65);
	private ActionButton bouton5 = new ActionButton("button", 400, 50, 65, 65);
	private ActionButton bouton6 = new ActionButton("button", 480, 50, 65, 65);
	
	public TrayInterface() {
		this.setLayout(null);
			
		this.add(soleil);
		this.add(pluie);
		this.add(vent);
		this.add(orage);
		this.add(nuages);
		this.add(choix);
		
		this.add(bouton1);
		this.add(bouton2);
		this.add(bouton3);
		this.add(bouton4);
		this.add(bouton5);
		this.add(bouton6);
		
		this.add(tray);
		this.setBackground(Hexmech.COLOURBACK);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
