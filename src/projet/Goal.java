package projet;

public class Goal {

	private boolean isValid;
	private int type;
	private int points;
	
	public Goal() {
		this.setValid(false);
		this.type = 0;
		this.points = 0;
	}
	
	public Goal(int type, int points) {
		this.setValid(false);
		this.type = type;
		this.points = points;
	}

	public boolean isValid() {
		return isValid;
	}

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
}
