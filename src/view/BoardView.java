package view;

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

import model.Board;
import model.Tile;

public class BoardView extends JPanel {

	private static final long serialVersionUID = 1L;

	private static Vector<Tile> v = new Vector<Tile>(); 
	private static boolean actionRoute = false;
	
	public BoardView()
	{	
		this.setBackground(TileView.COLOURBACK);

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
		for (int i=0;i<Board.BSIZE;i++)
			for (int j=0;j<Board.BSIZE;j++)
				if(Board.getBoard()[i][j].getType() > 0)
					TileView.drawTile(i, j, Board.getBoard()[i][j], g2);
	}

	public void clearVector(Vector<Tile> v) 
	{
		Iterator<Tile> itr = v.iterator();
        while(itr.hasNext()){
			itr.next().setSelected(false);
        }
		v.clear();
	}
	
	class MyMouseListener extends MouseAdapter	{	//inner class inside DrawingPanel 
		public void mouseClicked(MouseEvent e) { 
			Point p = new Point( TileView.pxtoHex(e.getX(),e.getY()) );
			if (p.x < 0 || p.y < 0 || p.x >= Board.BSIZE || p.y >= Board.BSIZE) return;

			if(actionRoute)
			{
				if(e.getButton() == MouseEvent.BUTTON1){
					if(v.size() < 2) {
						v.add(Board.getBoard()[p.x][p.y]);
						Board.getBoard()[p.x][p.y].setSelected(true);
					}
					if(v.size() == 2) {	
						if( v.firstElement().isAdjacent(v.lastElement()) && ( v.firstElement().isIrrigated() ^ v.lastElement().isIrrigated() )) {
							v.firstElement().setIrrigated(true);
							v.lastElement().setIrrigated(true);
						}
						else {
							clearVector(v);
						}
					}
				}
				else if(e.getButton() == MouseEvent.BUTTON3) {
					clearVector(v);
				}
			}
			else
			{
				if(e.getButton() == MouseEvent.BUTTON1)
				{
					if(Board.getBoard()[p.x][p.y].isValid())
					{
						if(Board.getBoard()[p.x][p.y].getType() == 0)
						{
							int rand = (int) ((Math.random()*(4-1)) + 1);
							Board.getBoard()[p.x][p.y].setType(rand);
							Board.getBoard()[p.x][p.y].setBonus(1);
						}
						else
							Board.getBoard()[p.x][p.y].increase();
					}
				}
				else if(e.getButton() == MouseEvent.BUTTON3)
				{
					if(Board.getBoard()[p.x][p.y].getType() != 4)
						Board.getBoard()[p.x][p.y].reinitialize();
				}
			}
			
			repaint();
		}	
	}
}
