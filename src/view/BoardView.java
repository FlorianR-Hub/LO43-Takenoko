package view;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JPanel;

import controller.GUI;
import model.Board;
import model.Gardener;
import model.Panda;
import model.Tile;

public class BoardView extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;

	private static Vector<Tile> v = new Vector<Tile>(); 
	private static int action = 0;
	
	private static Panda panda = new Panda(5,5,"P");
	private static Gardener gardener = new Gardener(5,5,"G");

	public BoardView()
	{	
		this.setBackground(TileView.COLOURBACK);

		this.addMouseListener(this);
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
		
		for (int i=0;i<Board.BSIZE;i++)
			for (int j=0;j<Board.BSIZE;j++)
				if(Board.getBoard()[i][j].getType() > 0)
					TileView.drawIrrigations(i, j, Board.getBoard()[i][j], g2);
		
		TileView.drawCharacter(panda, g2);
		TileView.drawCharacter(gardener, g2);
	}

	public void clearVector(Vector<Tile> v) 
	{
		Iterator<Tile> itr = v.iterator();
        while(itr.hasNext()){
			itr.next().setSelected(false);
        }
		v.clear();
	}
		
	@Override
	public void mouseClicked(MouseEvent e) {
		Point p = new Point( TileView.pxtoHex(e.getX(),e.getY()) );
		if (p.x < 0 || p.y < 0 || p.x >= Board.BSIZE || p.y >= Board.BSIZE) 
			return;
		
		applyWeather(e, Board.getBoard()[p.x][p.y]);
		
		applyAction(e, Board.getBoard()[p.x][p.y]);
	
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void applyWeather(MouseEvent e, Tile t) {
		switch(GUI.getPlayer().getWeather())
		{
			case 2: // rain
				if(e.getButton() == MouseEvent.BUTTON1)
				{
					if(t.isValid() && t.isIrrigated() && t.getType() != 0)
					{
						t.increase();
						GUI.getPlayer().setWeather(0);
					}
				}
				break;
			case 4: // storm
				if(e.getButton() == MouseEvent.BUTTON1)
				{
					if(panda.isMoveAllowed(t))
					{
						panda.move(t);
						GUI.getPlayer().setWeather(0);
					}
				}
				break;
			default:
				break;
		}
	}
	
	public void applyAction(MouseEvent e, Tile t) {
		
		switch(action)
		{
			case 1: // Action Tile
				if(e.getButton() == MouseEvent.BUTTON1)
				{
					if(t.isValid())
					{
						if(t.getType() == 0)
						{
							if(GUI.getPlayer().getTile().getType() != 0)
							{
								t.setType(GUI.getPlayer().getTile().getType());
								t.setBonus(GUI.getPlayer().getTile().getBonus());
								action = 0;
							}
						}
					}
				}
				break;
			case 3: // Action Panda
				if(panda.isMoveAllowed(t))
				{
					panda.move(t);
					action = 0;
				}
				break;
			case 4: // Action Gardener
				if(gardener.isMoveAllowed(t))
				{
					gardener.move(t);
					action = 0;
				}
				break;
			case 6: // Action Irrigation
				if(e.getButton() == MouseEvent.BUTTON1) {
					if(v.size() < 2) {
						v.add(t);
						t.setSelected(true);
					}
					
					if(v.size() == 2) {	
						if(GUI.getPlayer().getnbIrrig() > 0)
						{
							if( v.firstElement().isAdjacent(v.lastElement()) ) {
								v.firstElement().setIrrigations(v.lastElement());
								v.lastElement().setIrrigations(v.firstElement());
								GUI.getPlayer().setnbIrrig(GUI.getPlayer().getnbIrrig() - 1);
								clearVector(v);
								TrayView.deselectAction("irrigation");
								action = 0;
							}
						}
						else {
							clearVector(v);
						}
					}
				}
				else if(e.getButton() == MouseEvent.BUTTON3) {
					clearVector(v);
					TrayView.deselectAction("irrigation");
					action = 0;
				}
				break;
			default: // Dev Tool
				if(e.getButton() == MouseEvent.BUTTON2) // middle click to add a Tile
				{
					if(t.isValid())
					{
						if(t.getType() == 0)
						{
							int rand = (int) ((Math.random()*(4-1)) + 1);
							t.setType(rand);
						}
						else
							t.increase();
					}
				}
				else if(e.getButton() == MouseEvent.BUTTON3) // right click to remove a Tile
				{
					if(t.getType() != 4)
						t.reinitialize();
				}
				break;
		}
	}
	
	
	public static Panda getPanda() {
		return panda;
	}

	public static void setPanda(Panda panda) {
		BoardView.panda = panda;
	}

	public static Gardener getGardener() {
		return gardener;
	}

	public static void setGardener(Gardener gardener) {
		BoardView.gardener = gardener;
	}
	
	public static int getAction() {
		return action;
	}

	public static void setAction(int action) {
		BoardView.action = action;
	}
}
