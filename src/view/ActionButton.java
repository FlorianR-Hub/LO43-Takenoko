package view;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException; 
import javax.imageio.ImageIO;
import controller.GUI;
import controller.GameManager;

public class ActionButton extends Button {
	
	private static final long serialVersionUID = 1L;
	
	private String imgName;
	private boolean isSelected;
	
	public ActionButton(String imgName, int x, int y, int height, int width) {
		
		super(x, y, height, width);

		this.imgName = imgName;
		this.isSelected = false;
		
		try {
			this.img = ImageIO.read(new File(path + this.imgName + ".png"));
		}
		catch (IOException e) {
			 e.printStackTrace();
		}
    }
		
	public void paintComponent(Graphics g) {
		
	    g.drawImage(this.img, 0, 0, this.getWidth(), this.getHeight(), this);
	}
	
	/* -----------------------------------------------------
	   When a click is performed on a button: 
	 */
	public void mouseClicked(MouseEvent event) { 
		
	}
	
	/* -----------------------------------------------------
	   When the mouse goes over a button: 
	 */
	public void mouseEntered(MouseEvent event) { 
		
		if(!this.isSelected){
			try {
				this.img = ImageIO.read(new File(path + this.imgName + "_over.png"));
			}
			catch (IOException e) {
				 e.printStackTrace();
			}
		}
	}
	
	/* -----------------------------------------------------
	   When the mouse goes out of the button area: 
	 */
	public void mouseExited(MouseEvent event) { 
		if(!this.isSelected)
		{
			try {
				this.img = ImageIO.read(new File(path + this.imgName + ".png"));
			}
			catch (IOException e) {
				 e.printStackTrace();
			}
		}
	}
	
	/* -----------------------------------------------------
	   When left button is pressed on the mouse:
	 */
	public void mousePressed(MouseEvent event) { 
				
		
		switch(imgName)
		{
			case "endTurn":
				GUI.getPlayer().setRoundCompleted(true);
				break;
			case "roads":
				if(GUI.getPlayer().getNbRoad() > 0)
				{
					if(!this.isSelected) 
					{
						this.setSelected(true);	
						BoardView.setAction(6);
					}
					else
					{
						this.setSelected(false);	
						BoardView.setAction(0);
					}
				}
				break;
			case "bonusTools":
			case "bonusDefense":
				if(GUI.getPlayer().getWeather() == 5)
				{
					if(imgName == "bonusTools")
						GUI.getPlayer().setNbBonus(0, GUI.getPlayer().getNbBonus(0) + 1);
					else
						GUI.getPlayer().setNbBonus(1, GUI.getPlayer().getNbBonus(1) + 1);
					
					GUI.getPlayer().setWeather(0);
				}
				else if(GUI.getPlayer().getNbBonus((imgName == "bonusTools") ? 0 : 1) > 0)
				{
					if(!this.isSelected) 
					{
						this.setSelected(true);
						if(imgName == "bonusTools")
							BoardView.setAction(7);
						else
							BoardView.setAction(8);
					}
					else
					{
						this.setSelected(false);	
						BoardView.setAction(0);
					}
				}
				break;
			default:
				if(GUI.getPlayer().getActions().size() >= GUI.getPlayer().getNbActionsAllowed() && !GameManager.devMode)
					return;
				
				if(!this.isSelected)
				{
					this.setSelected(true);	
					
					switch(imgName)
					{
						case "tile":
							GUI.getDrawTileView().display();
							BoardView.setAction(1);
							GUI.getPlayer().addAction(1);
							break;
						case "road":
							GUI.getPlayer().setNbRoad(GUI.getPlayer().getNbRoad() + 1);
							GUI.getPlayer().addAction(2);
							break;
						case "monster":
							BoardView.setAction(3);
							GUI.getPlayer().addAction(3);
							break;
						case "architect":
							BoardView.setAction(4);
							GUI.getPlayer().addAction(4);
							break;
						case "goal":
							GUI.getDrawGoalView().display();
							GUI.getPlayer().addAction(5);
							break;
						default:
							break;
					}
					
					if(!GameManager.devMode)
					{
						if(GUI.getPlayer().getWeather() != 3)
							this.setVisible(false);
						else
						if(GUI.getPlayer().getActions().size() > 1)
							this.setVisible(false);
					}
					
				}
				else
					this.setSelected(false);
				break;
		}
	}

	/* -----------------------------------------------------
	   When the click is released:
	 */
	public void mouseReleased(MouseEvent event) { 
		
	}      
	
	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		
		this.isSelected = isSelected;
		
		if(this.isSelected)
		{
			try {
				this.img = ImageIO.read(new File(path + this.imgName + "_selected.png"));
			}
			catch (IOException e) {
				 e.printStackTrace();
			}
		}
		else
		{
			try {
				this.img = ImageIO.read(new File(path + this.imgName + ".png"));
			}
			catch (IOException e) {
				 e.printStackTrace();
			}
			
			this.setVisible(true);
		}
		
	}
	
	public String getImgName() {
		return imgName;
	}
}

