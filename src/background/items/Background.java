package background.items;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import main.ImageLoader;

/*
 * InfoBoard:
 * A static image-based class that helps call and draw the backgrounds for all screens.
 * Creator: Taylor Wilkinson
 */

public class Background {
	private Image bgImg;
	private Rectangle bound;
	
	public Background(double x, double y, double w, double h, String fileName) {
		bgImg = ImageLoader.loadImage(fileName);
		Graphics g = bgImg.getGraphics();
		g.setClip(0, 0, bgImg.getWidth(null), bgImg.getHeight(null));
		bound = g.getClipBounds();
		setLocation(x, y);
	}
	
	public void drawBackground(Graphics2D g2d){
		AffineTransform at = g2d.getTransform();
			g2d.translate(bound.getX(), bound.getY());
			g2d.scale(0.24, 0.24);
			g2d.drawImage(bgImg, 0, 0, null);
		g2d.setTransform(at);
	}
	
	private void setLocation(double x, double y) { // set location of fish
		bound.setFrame(x, y, bound.getWidth(), bound.getHeight());
	}

}
