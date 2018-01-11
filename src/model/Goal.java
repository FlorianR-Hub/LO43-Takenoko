package model;


public abstract class Goal {

	/**
	 * isValid: goal valid or not
	 * type   : 	1=tile / 2=panda / 3=gardener
	 * points : points given if goal validated  
	 * owner  : 	player who takes goal
	 */
	protected boolean isValid;
	protected int type;
	protected int points;
	protected int owner;
	
	public Goal() {
		this.isValid = false;
		this.type = 0;
		this.points = 0;
		this.owner = 0;
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

	public void setOwner(int owner) {
		this.owner = owner;
	}
	
	public int getOwner() {
		return this.owner;
	}
}
