package view;

import java.awt.Container;

import javax.swing.JFrame;

public abstract class DrawView extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	protected Container content = this.getContentPane();
	
	public DrawView(String name, int width, int height) {	
		
		this.setTitle(name);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setSize(width,height);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		
		this.setVisible(false);
	}
	
	public abstract void display();
}
