package view;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import model.Tile;

public class TileView
{
	private static int h=Frame.HEXSIZE;	// height. Distance between centres of two adjacent hexes. Distance between two opposite sides in a hex.
	private static int r=h/2;	// radius of inscribed circle (centre to middle of each side). r= h/2
	private static int s=(int) (h / 1.73205);	// length of one side : s = (h/2)/cos(30)= (h/2) / (sqrt(3)/2) = h / sqrt(3)
	private static int t=(int) (r / 1.73205);	// short side of 30o triangle outside of each hex : t = (h/2) tan30 = (h/2) 1/sqrt(3) = h / (2 sqrt(3)) = r / sqrt(3)
		
	//constants and global variables
	public final static Color COLOURONE  = new Color(255,255,255,50);
	public final static Color COLOURTWO  = new Color(0,0,0,40);
	public final static Color COLOURBACK = Color.WHITE;
	public final static Color COLOURCELL = Color.WHITE;	 
	public final static Color COLOURGRID = Color.BLACK;	
	public final static Color COLOURTXT  = Color.BLACK;


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


	public static void drawTile(int i, int j, Tile p, Graphics2D g2) {
		int x = i * (s+t);
		int y = j * h + (i%2) * h/2;
		Polygon poly = hex(x,y);
		
		g2.setColor(p.getColor());
		g2.fillPolygon(poly);
		
		if(p.isIrrigated()) {
			g2.setStroke(new BasicStroke(3));
			g2.setColor(Color.BLUE);
		}
		else {
			g2.setStroke(new BasicStroke(1));
			g2.setColor(Color.BLACK);
		}
		g2.drawPolygon(poly);
		
		g2.setColor(Color.BLACK);
		g2.drawString(""+p.getSize(), x+r/2+Frame.BORDERS, y+r+Frame.BORDERS+4);
		g2.setStroke(new BasicStroke(1));
		
		List<Tile> adjTiles = new ArrayList<Tile>();
		adjTiles = p.getValidTiles();
		
		for(Tile tile : adjTiles)
		{
			x = tile.getX() * (s+t);
			y = tile.getY() * h + (tile.getX()%2) * h/2;
			
			poly = hex(x,y);
			g2.setColor(COLOURCELL);
			g2.fillPolygon(poly);
			g2.setColor(COLOURTWO);	
			g2.drawPolygon(poly);
		}
	}

	//This function changes pixel location from a mouse click to a hex grid location
/*****************************************************************************
* Name: pxtoHex (pixel to hex)
* Parameters: mx, my. These are the co-ordinates of mouse click.
* Returns: point. A point containing the coordinates of the hex that is clicked in.
           If the point clicked is not a valid hex (ie. on the borders of the board, (-1,-1) is returned.
* Function: This only works for hexes in the FLAT orientation. The POINTY orientation would require
            a whole other function (different math).
            It takes into account the size of borders.
*****************************************************************************/
	public static Point pxtoHex(int mx, int my) {
		Point p = new Point(-1,-1);

		//correction for BORDERS
		mx -= Frame.BORDERS;
		my -= Frame.BORDERS;

		int x = (int) (mx / (s+t)); //this gives a quick value for x. It works only on odd cols and doesn't handle the triangle sections. It assumes that the hexagon is a rectangle with width s+t (=1.5*s).
		int y = (int) ((my - (x%2)*r)/h); //this gives the row easily. It needs to be offset by h/2 (=r)if it is in an even column

		/******FIX for clicking in the triangle spaces (on the left side only)*******/
		//dx,dy are the number of pixels from the hex boundary. (ie. relative to the hex clicked in)
		int dx = mx - x*(s+t);
		int dy = my - y*h;

		if (my - (x%2)*r < 0) return p; // prevent clicking in the open halfhexes at the top of the screen

		//System.out.println("dx=" + dx + " dy=" + dy + "  > " + dx*r/t + " <");
		
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
		} else {  // odd columns
			if (dy > h) {	//bottom half of hexes
				if (dx * r/t < dy - h) {
					x--;
					y++;
				}
			}
			if (dy < h) {	//top half of hexes
				//System.out.println("" + (t- dx)*r/t +  " " + (dy - r));
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
