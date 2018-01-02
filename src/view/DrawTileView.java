package view;

import javax.swing.JFrame;

import controller.GUI;
import controller.GameManager;

public class DrawTileView extends DrawView {
		
	private static final long serialVersionUID = 1L;
	
	private TileButton tile1;
	private TileButton tile2;
	private TileButton tile3;
	
	public DrawTileView(String name, int width, int height) {
		super(name, width, height);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	
	public void display() {	
		GUI.getFrame().setEnabled(false);
		
		tile1 = new TileButton(GameManager.getDraw().get(0), 20, 20, 146, 146);
		tile2 = new TileButton(GameManager.getDraw().get(1), 180, 20, 146, 146);
		tile3 = new TileButton(GameManager.getDraw().get(2), 340, 20, 146, 146);
		
		content.removeAll();
		content.add(tile1);
		content.add(tile2);
		content.add(tile3);
		
		this.setVisible(true);
	}
}
