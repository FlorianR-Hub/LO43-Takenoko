package view;

import java.awt.*;
import javax.swing.*;

import model.Board; 

public class Frame extends JFrame {
	private static final long serialVersionUID = 1L;
	private BoardView board;
	private TrayView tray;

	public Frame() {
		Board.initBoard();
		createAndShowGUI();
	}

	//constants and global variables
	public final static int HEXSIZE = 60;	//hex size in pixels
	public final static int BORDERS = 15;  
	public final static int SCRSIZE = HEXSIZE * (Board.BSIZE + 1) + BORDERS*3; //screen size (vertical dimension).
	
	private void createAndShowGUI()
	{
		board = new BoardView();
		tray = new TrayView();

		this.setTitle("LO43 Projet");
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		//for hexes in the FLAT orientation, the height of a 10x10 grid is 1.1764 * the width. (from h / (s+t))
		this.setSize( (int)(SCRSIZE/1.23 + 600), SCRSIZE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		GridLayout gl = new GridLayout(1,2);
		this.setLayout(gl);
		
		Container content = this.getContentPane();
		content.add(board);
		content.add(tray);
		this.setVisible(true);
	}
	
	public TrayView getTray() {
		return this.tray;
	}
}