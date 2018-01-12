package model;

import java.awt.Image;

public abstract class Goal {

	protected static final String path = "img/";
	
	/**
	 * isValid: goal valid or not
	 * type   : 	1=tile / 2=monster / 3=architect
	 * points : points given if goal validated  
	 */
	protected boolean isValid;
	protected int type;
	protected int points;
	protected Image img;
	
	public Goal() {
		this.isValid = false;
		this.type = 0;
		this.points = 0;
	}
	
	public Goal(int type) {
		this.isValid = false;
		this.type = type;
	}

	public abstract boolean isValid(Player p);
	
	public int getPoints() {
		return points;
	}

	public int getType() {
		return type;
	}
	
	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}
}
