package projet;

import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JPanel;

public class IndividualTray extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JButton bouton = new JButton("Bouton");
	
	public IndividualTray() {
		this.add(bouton);
		this.setBackground(Hexgame.COLOURBACK);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
