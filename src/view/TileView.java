package view;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import model.Tile;
import model.Character;

public class TileView
{
	private static final String path = "img/";
	
	private static int h=Frame.HEXSIZE;	// height. Distance between centres of two adjacent hexes. Distance between two opposite sides in a hex.
	private static int r=h/2;	// radius of inscribed circle (centre to middle of each side). r= h/2
	private static int s=(int) (h / 1.73205);	// length of one side : s = (h/2)/cos(30)= (h/2) / (sqrt(3)/2) = h / sqrt(3)
	private static int t=(int) (r / 1.73205);	// short side of 30o triangle outside of each hex : t = (h/2) tan30 = (h/2) 1/sqrt(3) = h / (2 sqrt(3)) = r / sqrt(3)
		
	//constants and global variables
	public final static Color COLOURONE  = new Color(255,255,255,50);
	public final static Color COLOURBACK = Color.WHITE;

	public static Polygon hex (int x0, int y0) {

		int y = y0 + Frame.BORDERS;
		int x = x0 + Frame.BORDERS;
		if (s == 0  || h == 0) {
			System.out.println("ERROR: size of hex has not been set");
			return new Polygon();
		}

		int[] xHex, yHex;
		xHex = new int[] {x,		x+t,		x+t+s,	x+t+s+t,	x+t+s,	x+t		};
		yHex = new int[] {y+r,	y,		y,		y+r,		y+r+r,	y+r+r	};
		
		return new Polygon(xHex, yHex, 6);
	}
	
	public static void drawRoads(int i, int j, Tile p, Graphics2D g2) {
		int x = i * (s+t) + Frame.BORDERS;
		int y = j * h + (i%2) * h/2 + Frame.BORDERS;
		
		if(p.isRoaded()) {
			g2.setStroke(new BasicStroke(4));
			g2.setColor(Color.darkGray);
			List<Boolean> irrigations = p.getRoads();
			
			for(int a=0; a<irrigations.size(); a++) {
				if(irrigations.get(a)) {
					switch(a + 1) {
						case 1:
							g2.drawLine(x, y+r, x+t, y);
							break;
						case 2:
							g2.drawLine(x+t, y, x+t+s, y);
							break;
						case 3:
							g2.drawLine(x+t+s, y, x+t+s+t, y+r);
							break;
						case 4:
							g2.drawLine(x+t+s+t, y+r, x+t+s, y+r+r);
							break;
						case 5:
							g2.drawLine(x+t+s, y+r+r, x+t, y+r+r);
							break;
						case 6:
							g2.drawLine(x+t, y+r+r, x, y+r);
							break;
					}
				}
			}
		}
	}

	public static void drawTile(int i, int j, Tile p, Graphics2D g2, BoardView bv) {
		
		Image img = null;
		int x = i * (s+t);
		int y = j * h + (i%2) * h/2;
		
		Polygon poly = hex(x,y);
		g2.setColor(p.getColor());
		
		try {
			
			if(p.getType() != 4)
				img = ImageIO.read(new File(path+"tile_S"+p.getSize()+"_C"+p.getType()+".png"));
			else
				img = ImageIO.read(new File(path+"tile_start.png"));
		}
    	catch (IOException e) {
    		e.printStackTrace();
		}
		
		if(p.isSelected())
			g2.fillPolygon(poly);
		else
			g2.drawImage(img, x+Frame.BORDERS, y+Frame.BORDERS, 102, 90, bv);
		
		if(p.getBonus() > 0)
		{
			try {
				
				if(p.getBonus() == 1)
					img = ImageIO.read(new File(path+"bonusTools.png"));
				else
					img = ImageIO.read(new File(path+"bonusDefense.png"));
			}
	    	catch (IOException e) {
	    		e.printStackTrace();
			}
			
			g2.drawImage(img, x+Frame.BORDERS + 25, y+Frame.BORDERS + 3, 20, 20, bv);
		}
		
		
		for(Tile tile : p.getValidTiles())
		{
			x = tile.getX() * (s+t);
			y = tile.getY() * h + (tile.getX()%2) * h/2;
			
			poly = hex(x,y);
			g2.setColor(Color.WHITE);
			g2.fillPolygon(poly);
			g2.setColor(Color.LIGHT_GRAY);
			g2.drawPolygon(poly);
		}
	}
	
	public static void drawCharacter(Character c, Graphics2D g2, BoardView bv) {
		Image img = null;
		int x = c.getPosX() * (s+t);
		int y = c.getPosY() * h + (c.getPosX()%2) * h/2;
		
		try {
			if(c.getName() == "P")
			{
				img = ImageIO.read(new File(path+"Onix.png"));
				g2.drawImage(img, x+Frame.BORDERS + 50, y+Frame.BORDERS + 5, 50, 80, bv);
			}
			else
			{
				img = ImageIO.read(new File(path+"architecte.png"));
				g2.drawImage(img, x+Frame.BORDERS + 10, y+Frame.BORDERS + 10, 35, 75, bv);
			}
		}
    	catch (IOException e) {
    		e.printStackTrace();
		}
	}

	public static Point pxtoHex(int mx, int my) {
		Point p = new Point(-1,-1);

		//correction for BORDERS
		mx -= Frame.BORDERS;
		my -= Frame.BORDERS;

		int x = (int) (mx / (s+t)); //this gives a quick value for x. It works only on odd cols and doesn't handle the triangle sections. It assumes that the hexagon is a rectangle with width s+t (=1.5*s).
		int y = (int) ((my - (x%2)*r)/h); //this gives the row easily. It needs to be offset by h/2 (=r)if it is in an even column

		//dx,dy are the number of pixels from the hex boundary. (ie. relative to the hex clicked in)
		int dx = mx - x*(s+t);
		int dy = my - y*h;

		if (my - (x%2)*r < 0) return p; // prevent clicking in the open halfhexes at the top of the screen

		//even columns
		if (x%2==0) {
			if (dy > r) {	//bottom half of hexes
				if (dx * r /t < dy - r) {
					x--;
				}
			}
			if (dy < r) {	//top half of hexes
				if ((t - dx)*r/t > dy ) {
					x--;
					y--;
				}
			}
		} 
		else 
		{  // odd columns
			if (dy > h) {	//bottom half of hexes
				if (dx * r/t < dy - h) {
					x--;
					y++;
				}
			}
			if (dy < h) {	//top half of hexes
				if ((t - dx)*r/t > dy - r) {
					x--;
				}
			}
		}
		
		p.x=x;
		p.y=y;
		return p;
	}
}
