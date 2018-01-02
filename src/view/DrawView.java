package view;

import java.awt.Container;
import java.util.List;
import javax.swing.JFrame;

import controller.GameManager;
import model.Tile;

public class DrawView extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private Container content = this.getContentPane();
	
	private TileButton tile1;
	private TileButton tile2;
	private TileButton tile3;
	
	public DrawView(List<Tile> draw) {
		
		this.setTitle("Draw");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setSize(512,200);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		
		this.setVisible(false);
	}
	
	public void display(List<Tile> draw) {	
		tile1 = new TileButton(GameManager.getDraw().get(0), 20, 14, 146, 146);
		tile2 = new TileButton(GameManager.getDraw().get(1), 180, 14, 146, 146);
		tile3 = new TileButton(GameManager.getDraw().get(2), 340, 14, 146, 146);
		
		content.removeAll();
		content.add(tile1);
		content.add(tile2);
		content.add(tile3);
		
		repaint();
		this.setVisible(true);
	}
}
