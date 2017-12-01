package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException; 
import javax.imageio.ImageIO;
import javax.swing.JButton;

public class ActionButton extends JButton implements MouseListener {
	
	private static final long serialVersionUID = 1L;
	private static final String path = "img/";
	
	private Image img;
	private String imgName;
	private boolean isSelected;
	
	public ActionButton(String imgName, int x, int y, int height, int width) {
		
		this.setBorderPainted(false);
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);
		this.imgName = imgName;
		
		try {
			 img = ImageIO.read(new File(path + this.imgName + ".png"));
		}
		catch (IOException e) {
			 e.printStackTrace();
		}
		
		this.setBounds(x, y, width, height);
		this.addMouseListener(this);
		this.isSelected = false;
    }
		
	public void paintComponent(Graphics g) {
		
	    g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
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
			 img = ImageIO.read(new File(path + this.imgName + "_over.png"));
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
			 img = ImageIO.read(new File(path + this.imgName + ".png"));
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
		
		if(!this.isSelected)
		{
			this.isSelected = true;
			System.out.println(this.imgName);
			
			try {
			 img = ImageIO.read(new File(path + this.imgName + "_selected.png"));
			}
			catch (IOException e) {
				 e.printStackTrace();
			}
		}
		else
		{
			this.isSelected = false;
			
			try {
			 img = ImageIO.read(new File(path + this.imgName + ".png"));
			}
			catch (IOException e) {
				 e.printStackTrace();
			}
		}
	}
	
	/* -----------------------------------------------------
	   When the click is released:
	 */
	public void mouseReleased(MouseEvent event) { 
		
	}       
}

