package view;

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
		this.setTile(t);
		
		try {
			this.img = ImageIO.read(new File(path + "tile_C" + this.tile.getType() + ".png"));
		}
		catch (IOException e) {
			 e.printStackTrace();
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		try {
			this.img = ImageIO.read(new File(path + "tile_C" + this.tile.getType() + "_over.png"));
		}
		catch (IOException e) {
			 e.printStackTrace();
		}
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		try {
			this.img = ImageIO.read(new File(path + "tile_C" + this.tile.getType() + ".png"));
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
			GUI.getDrawTileView().setVisible(false);
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
	
	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}

}
