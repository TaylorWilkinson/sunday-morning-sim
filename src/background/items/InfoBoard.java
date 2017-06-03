package background.items;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import main.ImageLoader;

/*
 * InfoBoard:
 * A static image-based class that provides a text box.
 * Creator: Taylor Wilkinson
 */

public class InfoBoard {
	private Image infoBoardImg;
	private Rectangle bound;
	private double scaleW, scaleH;
	
	public InfoBoard(double x, double y, double scaW, double scaH, String fileName) {
		infoBoardImg = ImageLoader.loadImage(fileName);
		Graphics g = infoBoardImg.getGraphics();
		g.setClip(0, 0, infoBoardImg.getWidth(null), infoBoardImg.getHeight(null));
		bound = g.getClipBounds();
		setLocation(x, y);
		scaleW = scaW;
		scaleH = scaH;
	}
	
	public void drawBox(Graphics2D g2d){
		AffineTransform at = g2d.getTransform();
			g2d.translate(bound.getX(), bound.getY());
			g2d.scale(scaleW, scaleH);
			g2d.drawImage(infoBoardImg, 0, 0, null);
		g2d.setTransform(at);
	}
	
	private void setLocation(double x, double y) {
		bound.setFrame(x, y, bound.getWidth(), bound.getHeight());
	}

}
