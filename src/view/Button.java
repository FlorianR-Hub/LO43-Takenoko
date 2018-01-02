package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public abstract class Button extends JButton implements MouseListener {

	private static final long serialVersionUID = 1L;
	protected static final String path = "img/";
	
	protected Image img;

	public Button(int x, int y, int height, int width) {
		this.setBorderPainted(false);
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);
		
		this.setBounds(x, y, width, height);
		this.addMouseListener(this);
	}
	
	public abstract void paintComponent(Graphics g);
}
