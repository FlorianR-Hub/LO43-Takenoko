package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import view.TileView;

public class Tile {

	/**
	 * type: 1=green / 2=yellow / 3=pink / 4=pond / 0=void
	 * bonus: 1=enclosure / 2=fertilizer / 3=pool / 0=void
	 * size: size of the bamboo placed on the tile
	 */
	private int posX;
	private int posY;
	private int type;
	private int bonus;
	private int size;
	private boolean isSelected;
	private boolean isIrrigated;
	private List<Boolean> irrigations;
	private boolean isValid;
	
	// CONSTRUCTORS -------------------------
	
	/* Constructor without positions: 
	 * used for Tiles not used by the player.
	 */
	public Tile(int type, int bonus){
		this.posX = 0;
		this.posY = 0;
		this.type = type;
		this.setBonus(bonus);
		this.setSize(0);
		this.setSelected(false);
		this.setIrrigated(false);
		this.setValid(false);
		irrigations = new ArrayList<Boolean>();
		for(int i=0; i<6; i++) {
			irrigations.add(false);
		}
	}
	
	/* Constructor with positions: 
	 * the Tile is placed on the Board.
	 */
	public Tile(int x, int y, int type, int bonus, boolean irrigated){
		this.posX = x;
		this.posY = y;
		this.setType(type);
		this.setBonus(bonus);
		this.setSize(0);
		this.setSelected(false);
		this.setIrrigated(irrigated);
		this.setValid(false);
		irrigations = new ArrayList<Boolean>();
		for(int i=0; i<6; i++) {
			irrigations.add(false);
		}
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
	
	public void increase(){
		int i = this.getBonus() == 2 ? 2 : 1;
		if(this.getType() != 4){
			if(this.getSize() + i < 4)
				this.setSize(this.getSize()+i);
			else
				this.setSize(4);
		}
	}
	
	public void decrease(){
		if(this.getSize() - 1 <= 0)
			this.setSize(0);
		else
			this.setSize(this.getSize() - 1);
	}
	
	public void reinitialize(){
		this.setType(0);
		this.setBonus(0);
		this.setSize(0);
		this.setIrrigated(false);
		this.setValid(false);
		this.setSelected(false);
		for(int i=0; i<6; i++) {
			irrigations.set(i, false);
		}
	}
	
	public boolean isSelectionable(){
		return (this.type > 0 && this.type < 4);
	}
	
	public boolean isAdjacent(Tile adj){
		if(this.posX%2 == 0)
			return (this.posX == adj.posX && Math.abs(this.posY - adj.posY) == 1) 
					|| (Math.abs(this.posX - adj.posX) == 1 && (this.posY - adj.posY == 1 || this.posY - adj.posY == 0));
		else
			return (this.posX == adj.posX && Math.abs(this.posY - adj.posY) == 1) 
					|| (Math.abs(this.posX - adj.posX) == 1 && (this.posY - adj.posY == -1 || this.posY - adj.posY == 0));
	}
	
	public List<Tile> getAdjacentTiles() {
		List<Tile> adjTiles = new ArrayList<Tile>();
		if(this.posX%2 == 0) {
			adjTiles.add(Board.getBoard()[this.posX-1][this.posY-1]);
			adjTiles.add(Board.getBoard()[this.posX][this.posY-1]);
			adjTiles.add(Board.getBoard()[this.posX+1][this.posY-1]);
			adjTiles.add(Board.getBoard()[this.posX+1][this.posY]);
			adjTiles.add(Board.getBoard()[this.posX][this.posY+1]);
			adjTiles.add(Board.getBoard()[this.posX-1][this.posY]);
		}
		else if(this.posX%2 == 1) {
			adjTiles.add(Board.getBoard()[this.posX-1][this.posY]);
			adjTiles.add(Board.getBoard()[this.posX][this.posY-1]);
			adjTiles.add(Board.getBoard()[this.posX+1][this.posY]);
			adjTiles.add(Board.getBoard()[this.posX+1][this.posY+1]);
			adjTiles.add(Board.getBoard()[this.posX][this.posY+1]);
			adjTiles.add(Board.getBoard()[this.posX-1][this.posY+1]);
		}
		return adjTiles;
	}
	
	public List<Tile> getValidTiles(){
		List<Tile> validTiles = new ArrayList<Tile>();
		for(Tile tile : this.getAdjacentTiles()){
			if(tile.getType() == 0){
				List<Tile> adjTiles = tile.getAdjacentTiles();
				int nbPlacedTiles = 0;
				for(Tile t : adjTiles)
					if(t.getType() > 0)
						nbPlacedTiles++;						
				if(nbPlacedTiles > 1 || this.getType() == 4){
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
	
	public List<Boolean> getIrrigations() {
		return irrigations;
	}
	
	public int getX() {
		return posX;
	}

	public int getY() {
		return posY;
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
	
	public void setIrrigations(Tile adj) {
		
		this.setIrrigated(true);
		List<Tile> adjTiles = new ArrayList<Tile>();
		adjTiles = this.getAdjacentTiles();
		int position = 0;
		
		for(Tile t : adjTiles) {
			if(t.posX == adj.posX && t.posY == adj.posY) {
				this.irrigations.set(position, true);
			}
			position++;
		}
	}
	
	public void setX(int posX) {
		this.posX = posX;
	}
	
	public void setY(int posY) {
		this.posY = posY;
	}

}