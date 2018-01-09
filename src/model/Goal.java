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
		this.setValid(false);
		this.type = 0;
		this.points = 0;
		this.owner = 0;
	}
	
	public Goal(int type) {
		this.setValid(false);
		this.type = type;
	}

	public abstract boolean isValid(Player p);

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public void setOwner(int owner) {
		this.owner = owner;
	}
}
