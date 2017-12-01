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
	
	private Image img;
	private String imgName;
	private boolean isSelected;
	
	public ActionButton(String imgName, int x, int y, int height, int width) {
		this.setBorderPainted(false);
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);
		
		this.imgName = imgName;
		
		try {
			 img = ImageIO.read(new File(imgName+".png"));
		}catch (IOException e) {
			 e.printStackTrace();
		}
		
		this.setBounds(x, y, width, height);
		this.addMouseListener(this);
		
		this.isSelected = false;
    }
		
	public void paintComponent(Graphics g) {
	    g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}
		
	//M�thode appel�e lors du clic de souris
	public void mouseClicked(MouseEvent event) { 
		
	}
	
	//M�thode appel�e lors du survol de la souris
	public void mouseEntered(MouseEvent event) { 
		if(!this.isSelected)
		{
			try {
			 img = ImageIO.read(new File(this.imgName+"_mouseover.png"));
			}catch (IOException e) {
				 e.printStackTrace();
			}
		}
	}
	
	//M�thode appel�e lorsque la souris sort de la zone du bouton
	public void mouseExited(MouseEvent event) { 
		if(!this.isSelected)
		{
			try {
			 img = ImageIO.read(new File(this.imgName+".png"));
			}catch (IOException e) {
				 e.printStackTrace();
			}
		}
	}
	
	//M�thode appel�e lorsque l'on presse le bouton gauche de la souris
	public void mousePressed(MouseEvent event) { 
		if(!this.isSelected)
		{
			this.isSelected = true;
			System.out.println(this.imgName);
			
			try {
			 img = ImageIO.read(new File(this.imgName+"_selected.png"));
			}catch (IOException e) {
				 e.printStackTrace();
			}
		}
		else
		{
			this.isSelected = false;
			
			try {
			 img = ImageIO.read(new File(this.imgName+".png"));
			}catch (IOException e) {
				 e.printStackTrace();
			}
		}
	}
	
	//M�thode appel�e lorsque l'on rel�che le clic de souris
	public void mouseReleased(MouseEvent event) { 
		
	}       
}