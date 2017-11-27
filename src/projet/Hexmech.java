package projet;

import java.awt.*;

/* This is a companion class to hexgame.java. It handles all of the mechanics related to hexagon grids. */

public class Hexmech
{
	private static int h=Hexgame.HEXSIZE;	// height. Distance between centres of two adjacent hexes. Distance between two opposite sides in a hex.
	private static int r=h/2;	// radius of inscribed circle (centre to middle of each side). r= h/2
	private static int s=(int) (h / 1.73205);	// length of one side : s = (h/2)/cos(30)= (h/2) / (sqrt(3)/2) = h / sqrt(3)
	private static int t=(int) (r / 1.73205);	// short side of 30o triangle outside of each hex : t = (h/2) tan30 = (h/2) 1/sqrt(3) = h / (2 sqrt(3)) = r / sqrt(3)
		

/*********************************************************
Name: hex()
Parameters: (x0,y0) This point is the top left corner 
    of the rectangle enclosing the hexagon. 
Returns: a polygon containing the six points.
Called from: drawHex(), fillhex()
Purpose: This function takes two points that describe a hexagon
and calculates all six of the points in the hexagon.
*********************************************************/
	public static Polygon hex (int x0, int y0) {

		int y = y0 + Hexgame.BORDERS;
		int x = x0 + Hexgame.BORDERS;
		if (s == 0  || h == 0) {
			System.out.println("ERROR: size of hex has not been set");
			return new Polygon();
		}

		int[] cx,cy;

		cx = new int[] {x+t,x+s+t,x+s+t+t,x+s+t,x+t,x};
		cy = new int[] {y,y,y+r,y+r+r,y+r+r,y+r};
		
		return new Polygon(cx,cy,6);
	}

/********************************************************************
Name: drawHex()
Parameters: (i,j) : the x,y coordinates of the inital point of the hexagon
	    g2: the Graphics2D object to draw on.
Returns: void
Calls: hex() 
Purpose: This function draws a hexagon based on the initial point (x,y).
The hexagon is drawn in the colour specified in hexgame.COLOURELL.
*********************************************************************/
	public static void drawHex(int i, int j, Graphics2D g2) {
		int x = i * (s+t);
		int y = j * h + (i%2) * h/2;
		Polygon poly = hex(x,y);
		g2.setColor(Hexgame.COLOURCELL);
		g2.fillPolygon(poly);
		g2.setColor(Hexgame.COLOURGRID);
		g2.drawPolygon(poly);
	}

/***************************************************************************
* Name: fillHex()
* Parameters: (i,j) : the x,y coordinates of the initial point of the hexagon
		n   : an integer number to indicate a letter to draw in the hex
		g2  : the graphics context to draw on
* Return: void
* Called from:
* Calls: hex()
*Purpose: This draws a filled in polygon based on the coordinates of the hexagon.
	  The colour depends on whether n is negative or positive.
	  The colour is set by hexgame.COLOURONE and hexgame.COLOURTWO.
	  The value of n is converted to letter and drawn in the hexagon.
*****************************************************************************/
	public static void fillHex(int i, int j, Tile p, Graphics2D g2) {
		int x = i * (s+t);
		int y = j * h + (i%2) * h/2;
		
		g2.setColor(p.getColor());
		g2.fillPolygon(hex(x,y));
		g2.setColor(Hexgame.COLOURTXT);
		g2.drawString(p.getX()+","+p.getY(), x+r/2+Hexgame.BORDERS, y+r+Hexgame.BORDERS+4);
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
		mx -= Hexgame.BORDERS;
		my -= Hexgame.BORDERS;

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