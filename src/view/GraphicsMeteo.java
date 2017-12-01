package view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GraphicsMeteo extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private static final String path = "img/";
	
    private Image img;

    public GraphicsMeteo (String imgName, int x, int y)
    {
    	try {
    		img = ImageIO.read(new File(path + imgName));
		}
    	catch (IOException e) {
		 e.printStackTrace();
		}
        
        this.setBounds(x, y, img.getWidth(this), img.getHeight(this));
    }
    
    public void paintComponent(Graphics g)
    {
    	 g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}