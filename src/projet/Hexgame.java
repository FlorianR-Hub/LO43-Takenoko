package projet;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Iterator;
import java.util.Vector; 

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
	public final static Color COLOURBACK =  Color.WHITE;
	public final static Color COLOURCELL =  Color.WHITE;	 
	public final static Color COLOURGRID =  Color.BLACK;	 
	public final static Color COLOURONE = new Color(255,255,255,50);
	public final static Color COLOURTXT = Color.BLACK;
	public final static Color COLOURTWO = new Color(0,0,0,200);
	public final static int HEXSIZE = 60;	//hex size in pixels
	public final static int BORDERS = 15;  
	public final static int SCRSIZE = HEXSIZE * (GameBoard.BSIZE + 1) + BORDERS*3; //screen size (vertical dimension).
	
	private static Vector<Tile> v = new Vector<Tile>(); 
	private static boolean actionRoute = true;

	private void createAndShowGUI()
	{
		DrawingPanel panel = new DrawingPanel();
		IndividualTray tray = new IndividualTray();

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
		content.add(panel);
		content.add(tray);
		this.setVisible(true);
	}
	
	class DrawingPanel extends JPanel
	{		
		private static final long serialVersionUID = 1L;

		public DrawingPanel()
		{	
			this.setBackground(COLOURBACK);

			MyMouseListener ml = new MyMouseListener();            
			addMouseListener(ml);
		}

		public void paintComponent(Graphics g)
		{
			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.setFont(new Font("Arial", Font.PLAIN, 17));
			super.paintComponent(g2);
			
			//draw grid
			for (int i=0;i<GameBoard.BSIZE;i++)
				for (int j=0;j<GameBoard.BSIZE;j++)
					Hexmech.drawHex(i,j,g2);
			
			//fill in hexes
			for (int i=0;i<GameBoard.BSIZE;i++)
				for (int j=0;j<GameBoard.BSIZE;j++)					
					Hexmech.fillHex(i,j,GameBoard.getBoard()[i][j],g2);
			
		}

		class MyMouseListener extends MouseAdapter	{	//inner class inside DrawingPanel 
			public void mouseClicked(MouseEvent e) { 
				Point p = new Point( Hexmech.pxtoHex(e.getX(),e.getY()) );
				if (p.x < 0 || p.y < 0 || p.x >= GameBoard.BSIZE || p.y >= GameBoard.BSIZE) return;

				if(actionRoute)
				{
					if(e.getButton() == MouseEvent.BUTTON1 /*&& board[p.x][p.y].isSelectionable()*/)
					{
						if(v.size() < 2)
						{
							v.add(GameBoard.getBoard()[p.x][p.y]);
							GameBoard.getBoard()[p.x][p.y].setSelected(true);
						}
						
						if(v.size() == 2)
						{
							if(v.firstElement().isAdjacent(v.lastElement()))
								System.out.println("adjacent !\n");
							else
							{
								System.out.println("non adjacent !\n");
								Iterator<Tile> itr = v.iterator();
						        while(itr.hasNext())
						        {
									itr.next().setSelected(false);
						        }
						        
						        v.clear();
							}
						}
					}
					else if(e.getButton() == MouseEvent.BUTTON3)
					{
						//actionRoute = false;
						Iterator<Tile> itr = v.iterator();
				        while(itr.hasNext())
				        {
							itr.next().setSelected(false);
				        }
				        
				        v.clear();
					}
				}
				else
				{
					if(e.getButton() == MouseEvent.BUTTON1)
					{
						if(GameBoard.getBoard()[p.x][p.y].getType() == 0)
						{
							int rand = (int) ((Math.random()*(4-1)) + 1);
							GameBoard.getBoard()[p.x][p.y].setType(rand);
							GameBoard.getBoard()[p.x][p.y].setBonus(1);
						}
						else
							GameBoard.getBoard()[p.x][p.y].increase();
					}
					else if(e.getButton() == MouseEvent.BUTTON3)
					{
						if(GameBoard.getBoard()[p.x][p.y].getType() != 4)
							GameBoard.getBoard()[p.x][p.y].reinitialiser();
					}
				}
				
				repaint();
			}		 
		} //end of MyMouseListener class 
	} // end of DrawingPanel class
}