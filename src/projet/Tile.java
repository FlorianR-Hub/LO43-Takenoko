package projet;

import java.awt.Color;

public class Tile extends GameObject{

	private int type; // 1=vert / 2=jaune / 3=rose / 4=etang / 0=vide
	private int bonus; // 1=enclos / 2=engrais / 3=bassin / 0=aucun
	private int size; // Taille de la pousse de 0 a 4
	private boolean isSelected;
	private boolean isIrrigated;
	
	public Tile(int type, int bonus){
		super();
		this.type = type;
		this.setBonus(bonus);
		this.setSize(0);
		this.setSelected(false);
		this.setIrrigated(false);
	}
	
	public Tile(int x, int y, int type, int bonus, boolean irrigated){
		super(x, y);
		this.setType(type);
		this.setBonus(bonus);
		this.setSize(0);
		this.setSelected(false);
		this.setIrrigated(irrigated);
	}
	
	public Color getColor() {
		Color c = Hexgame.COLOURONE;
		
		if(this.isSelected)
		{
			c = new Color(80,80,80,100);
		}
		else
		{
			switch(this.type)
			{
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

	public void reinitialiser()
	{
		this.setType(0);
		this.setBonus(0);
		this.setSize(0);
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
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public boolean isIrrigated() {
		return isIrrigated;
	}

	public void setIrrigated(boolean isIrrigated) {
		this.isIrrigated = isIrrigated;
	}
}