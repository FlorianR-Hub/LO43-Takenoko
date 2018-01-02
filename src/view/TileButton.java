package view;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Collections;

import javax.imageio.ImageIO;
import controller.GUI;
import controller.GameManager;
import model.Tile;

public class TileButton extends Button {

	private static final long serialVersionUID = 1L;
	
	private Tile tile;
	
	public TileButton(Tile t, int x, int y, int height, int width) {
		super(x,y,height,width);
		
		this.tile = t;
		
		try {
			 img = ImageIO.read(new File(path + "tile" + this.tile.getType() + ".png"));
		}
		catch (IOException e) {
			 e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		try {
		 img = ImageIO.read(new File(path + "tile" + this.tile.getType() + "_over.png"));
		}
		catch (IOException e) {
			 e.printStackTrace();
		}
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		try {
		 img = ImageIO.read(new File(path + "tile" + this.tile.getType() + ".png"));
		}
		catch (IOException e) {
			 e.printStackTrace();
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		if(GameManager.getDraw().remove(tile))
		{
			GUI.getPlayer().setTile(tile);			
			Collections.rotate(GameManager.getDraw(), -1);
			Collections.rotate(GameManager.getDraw(), -1);
			GUI.getFrame().setEnabled(true);
			GUI.getDrawView().setVisible(false);
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
		
	}

}