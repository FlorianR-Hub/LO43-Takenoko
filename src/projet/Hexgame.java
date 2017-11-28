package projet;

import java.awt.*;
import javax.swing.*; 

/**********************************
  This is the main class of a Java program to play a game based on hexagonal tiles.
  The mechanism of handling hexes is in the file hexmech.java.
  
 ***********************************/

public class Hexgame extends JFrame
{
	private static final long serialVersionUID = 1L;

	private Hexgame() {
    	GameBoard.initBoard();
		createAndShowGUI();
	}

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() {
				public void run() {
				new Hexgame();
				}
				});
	}

	//constants and global variables
	public final static int HEXSIZE = 60;	//hex size in pixels
	public final static int BORDERS = 15;  
	public final static int SCRSIZE = HEXSIZE * (GameBoard.BSIZE + 1) + BORDERS*3; //screen size (vertical dimension).
	
	private void createAndShowGUI()
	{
		BoardInterface board = new BoardInterface();
		TrayInterface tray = new TrayInterface();

		this.setTitle("LO43 Projet");
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		//for hexes in the FLAT orientation, the height of a 10x10 grid is 1.1764 * the width. (from h / (s+t))
		this.setSize( (int)(SCRSIZE/1.23 + 500), SCRSIZE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		GridLayout gl = new GridLayout(1,2);
		gl.setHgap(-100);
		this.setLayout(gl);
		
		Container content = this.getContentPane();
		content.add(board);
		content.add(tray);
		this.setVisible(true);
	}
}