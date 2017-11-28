package projet;

import java.awt.Graphics;
import javax.swing.JPanel;

public class TrayInterface extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Bouton bouton1 = new Bouton("button", 120, 20, 65);
	private Bouton bouton2 = new Bouton("button", 200, 20, 65);
	private Bouton bouton3 = new Bouton("button", 280, 20, 65);
	private Bouton bouton4 = new Bouton("button", 360, 20, 65);
	private Bouton bouton5 = new Bouton("button", 440, 20, 65);
	private Bouton bouton6 = new Bouton("button", 520, 20, 65);
	
	public TrayInterface() {
		this.setLayout(null);
		this.add(bouton1);
		this.add(bouton2);
		this.add(bouton3);
		this.add(bouton4);
		this.add(bouton5);
		this.add(bouton6);
		this.setBackground(Hexmech.COLOURBACK);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
