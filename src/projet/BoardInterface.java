package projet;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JPanel;

public class BoardInterface extends JPanel {

	private static final long serialVersionUID = 1L;

	private static Vector<Tile> v = new Vector<Tile>(); 
	private static boolean actionRoute = false;
	
	public BoardInterface()
	{	
		this.setBackground(Hexmech.COLOURBACK);

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
				if(GameBoard.getBoard()[i][j].getType() > 0)
					Hexmech.drawHex(i,j,g2);
		
		//fill in hexes
		for (int i=0;i<GameBoard.BSIZE;i++)
			for (int j=0;j<GameBoard.BSIZE;j++)	
				if(GameBoard.getBoard()[i][j].getType() > 0)
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
					if(GameBoard.getBoard()[p.x][p.y].isValid())
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
	}
}
