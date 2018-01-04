package view;

import java.awt.*;
import java.util.List;

import model.Tile;
import model.Character;

public class TileView
{
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
	
	public static void drawIrrigations(int i, int j, Tile p, Graphics2D g2) {
		int x = i * (s+t) + Frame.BORDERS;
		int y = j * h + (i%2) * h/2 + Frame.BORDERS;
		
		if(p.isIrrigated()) {
			g2.setStroke(new BasicStroke(3));
			g2.setColor(Color.BLUE);
			List<Boolean> irrigations = p.getIrrigations();
			
			for(int a=0; a<irrigations.size(); a++) {
				if(irrigations.get(a)) {
					switch(a) {
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

	public static void drawTile(int i, int j, Tile p, Graphics2D g2) {
		int x = i * (s+t);
		int y = j * h + (i%2) * h/2;
		Polygon poly = hex(x,y);
		
		g2.setFont(new Font("Arial", Font.PLAIN, 20));
		g2.setColor(p.getColor());
		g2.fillPolygon(poly);
		
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(1));
		if(p.getType() != 4)
			g2.drawString(""+p.getSize(), x+r/2+Frame.BORDERS+12, y+r+Frame.BORDERS+4);			
		
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
	
	public static void drawCharacter(Character c, Graphics2D g2) {
		
		int x = c.getPosX() * (s+t);
		int y = c.getPosY() * h + (c.getPosX()%2) * h/2;
		
		g2.setColor(Color.BLACK);
		
		if(c.getName() == "P")
			g2.drawString(""+c.getName(), x+r/2+Frame.BORDERS + 25, y+r+Frame.BORDERS+20);
		else
			g2.drawString(""+c.getName(), x+r/2+Frame.BORDERS, y+r+Frame.BORDERS+20);
	}

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
