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
import controller.GameManager;
import model.Board;
import model.Architect;
import model.Monster;
import model.Tile;

public class BoardView extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;

	private static Vector<Tile> v = new Vector<Tile>(); 
	private static int action = 0;
	
	private static Monster monster = new Monster(3,3,"P");
	private static Architect architect = new Architect(3,3,"G");

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
					TileView.drawTile(i, j, Board.getBoard()[i][j], g2, this);
				
		
		for (int i=0;i<Board.BSIZE;i++)
			for (int j=0;j<Board.BSIZE;j++)
				if(Board.getBoard()[i][j].getType() > 0)
					TileView.drawRoads(i, j, Board.getBoard()[i][j], g2);
		
		TileView.drawCharacter(monster, g2, this);
		TileView.drawCharacter(architect, g2, this);
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
					if(t.isValid() && t.isRoaded() && t.getType() != 0)
					{
						t.increase();
						GUI.getPlayer().setWeather(0);
					}
				}
				break;
			case 4: // storm
				if(e.getButton() == MouseEvent.BUTTON1)
				{
					if(monster.isMoveAllowed(t))
					{
						monster.move(t);
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
			case 3: // Action Monster
				if(monster.isMoveAllowed(t))
				{
					monster.move(t);
					action = 0;
				}
				break;
			case 4: // Action Architect
				if(architect.isMoveAllowed(t))
				{
					architect.move(t);
					action = 0;
				}
				break;
			case 6: // Action Road
				if(e.getButton() == MouseEvent.BUTTON1) {
					if(v.size() < 2) {
						v.add(t);
						t.setSelected(true);
					}
					
					if(v.size() == 2) {	
						if(GUI.getPlayer().getNbRoad() > 0)
						{
							if( v.firstElement().isAdjacent(v.lastElement()) ) {
								v.firstElement().setRoads(v.lastElement());
								v.lastElement().setRoads(v.firstElement());
								GUI.getPlayer().setNbRoad(GUI.getPlayer().getNbRoad() - 1);
								clearVector(v);
								TrayView.deselectAction("roads");
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
					TrayView.deselectAction("roads");
					action = 0;
				}
				break;
			case 7: // Action Bonus Tools
				if(t.getBonus() == 0 && t.getSize() == 0)
				{
					t.setBonus(1);
					GUI.getPlayer().setNbBonus(0, GUI.getPlayer().getNbBonus(0) - 1);
					TrayView.deselectAction("bonusTools");
					action = 0;
				}
				break;
			case 8: // Action Bonus Defense
				if(t.getBonus() == 0 && t.getSize() == 0)
				{
					t.setBonus(2);
					GUI.getPlayer().setNbBonus(1, GUI.getPlayer().getNbBonus(1) - 1);
					TrayView.deselectAction("bonusDefense");
					action = 0;
				}
				break;
			default: // Dev Tool
				if(GameManager.devMode)
				{
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
				}
				break;
		}
	}
	
	
	public static Monster getMonster() {
		return monster;
	}

	public static void setMonster(Monster monster) {
		BoardView.monster = monster;
	}

	public static Architect getArchitect() {
		return architect;
	}

	public static void setArchitect(Architect architect) {
		BoardView.architect = architect;
	}
	
	public static int getAction() {
		return action;
	}

	public static void setAction(int action) {
		BoardView.action = action;
	}
}
