package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import view.TileView;

public class Tile extends GameObject{

	/**
	 * type: 1=green / 2=yellow / 3=pink / 4=pond / 0=void
	 * bonus: 1=enclosure / 2=fertilizer / 3=pool / 0=void
	 * size: size of the bamboo placed on the tile
	 */
	private int type;
	private int bonus;
	private int size;
	private boolean isSelected;
	private boolean isIrrigated;
	private boolean isValid;
	
	// CONSTRUCTORS -------------------------
	
	/* Constructor without positions: 
	 * used for Tiles not used by the player.
	 */
	public Tile(int type, int bonus){
		super();
		this.type = type;
		this.setBonus(bonus);
		this.setSize(0);
		this.setSelected(false);
		this.setIrrigated(false);
		this.setValid(false);
	}
	
	/* Constructor with positions: 
	 * the Tile is placed on the Board.
	 */
	public Tile(int x, int y, int type, int bonus, boolean irrigated){
		super(x, y);
		this.setType(type);
		this.setBonus(bonus);
		this.setSize(0);
		this.setSelected(false);
		this.setIrrigated(irrigated);
		this.setValid(false);
	}
	

	public Color getColor() {
		Color c = TileView.COLOURONE;
		
		if(this.isSelected){
			c = new Color(80,80,80,100);
		}
		else{
			switch(this.type){
				case 1:
					c = Color.GREEN;
					break;
				case 2:
					c = Color.YELLOW;
					break;
				case 3:
					c = Color.PINK;
					break;
				case 4:
					c = new Color(0,0,125,120);
					break;
			}
		}
		return c;
	}
	
	
	public void increase()
	{
		int i = this.getType() == 2 ? 2 : 1;
		if(this.getType() != 4)
		{
			if(this.getSize() + i < 4)
				this.setSize(this.getSize()+i);
			else
				this.setSize(4);
		}
	}
	
	
	public void decrease()
	{
		this.setSize(this.getSize() - 1);
	}

	
	public void reinitialize()
	{
		this.setType(0);
		this.setBonus(0);
		this.setSize(0);
		this.setIrrigated(false);
		this.setPlaced(false);
		this.setValid(false);
		this.setSelected(false);
	}
	
	
	public boolean isSelectionable()
	{
		return this.getType() > 0 && this.getType() < 4;
	}
	
	
	public boolean isAdjacent(Tile p)
	{	
		if(this.getX()%2 == 0)
			return ((this.getX() == p.getX() && Math.abs(this.getY() - p.getY()) == 1) 
					|| (Math.abs(this.getX() - p.getX()) == 1 && (this.getY() - p.getY() == 1 || this.getY() - p.getY() == 0)));
		else
			return ((this.getX() == p.getX() && Math.abs(this.getY() - p.getY()) == 1) 
					|| (Math.abs(this.getX() - p.getX()) == 1 && (this.getY() - p.getY() == -1 || this.getY() - p.getY() == 0)));
	}
	
	
	public List<Tile> getAdjacentTiles()
	{
		List<Tile> adjTiles = new ArrayList<Tile>();
		
		for (int i=0;i<Board.BSIZE;i++) 
			for (int j=0;j<Board.BSIZE;j++) 
				if(this.isAdjacent(Board.getBoard()[i][j]))
					adjTiles.add(Board.getBoard()[i][j]);

		return adjTiles;
	}
	
	public List<Tile> getValidTiles()
	{
		List<Tile> validTiles = new ArrayList<Tile>();
		
		for(Tile tile : this.getAdjacentTiles())
		{
			if(tile.getType() == 0)
			{
				List<Tile> adjTiles = tile.getAdjacentTiles();
				int nbPlacedTiles = 0;
				
				for(Tile t : adjTiles)
					if(t.getType() > 0)
						nbPlacedTiles++;						

				if(nbPlacedTiles > 1 || this.getType() == 4)
				{
					tile.setValid(true);
					validTiles.add(tile);
				}		
			}
		}
	
		return validTiles;
	}

	// GETTERS ------------------------------
	
	public int getType() {
		return type;
	}
	
	public int getBonus() {
		return bonus;
	}

	public int getSize() {
		return size;
	}
	
	public boolean isSelected() {
		return isSelected;
	}
	
	public boolean isIrrigated() {
		return isIrrigated;
	}
	
	public boolean isValid() {
		return isValid;
	}
	
	
	// SETTERS ------------------------------
	
	public void setType(int type) {
		this.type = type;
	}
	
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public void setIrrigated(boolean isIrrigated) {
		this.isIrrigated = isIrrigated;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
}