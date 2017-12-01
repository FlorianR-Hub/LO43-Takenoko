package controller;

import java.awt.*;
import javax.swing.*;

import model.Board;
import view.BoardView;
import view.TrayView; 

public class Frame extends JFrame
{
	private static final long serialVersionUID = 1L;

	public Frame(){
		
		Board.initBoard();
		createAndShowGUI();
	}

	//constants and global variables
	public final static int HEXSIZE = 60;	//hex size in pixels
	public final static int BORDERS = 15;  
	public final static int SCRSIZE = HEXSIZE * (Board.BSIZE + 1) + BORDERS*3; //screen size (vertical dimension).
	
	private void createAndShowGUI()
	{
		BoardView board = new BoardView();
		TrayView tray = new TrayView();

		this.setTitle("LO43 Projet");
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		//for hexes in the FLAT orientation, the height of a 10x10 grid is 1.1764 * the width. (from h / (s+t))
		this.setSize( (int)(SCRSIZE/1.23 + 600), SCRSIZE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		GridLayout gl = new GridLayout(1,2);
		//gl.setHgap(-100);
		this.setLayout(gl);
		
		Container content = this.getContentPane();
		content.add(board);
		content.add(tray);
		this.setVisible(true);
	}
}