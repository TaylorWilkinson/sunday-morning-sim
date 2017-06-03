package interactive.items;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import main.ImageLoader;
import main.Util;

/*
 * Toaster:
 * A subclass of MoveableObject. Provides the multiple toaster images and how to load them.
 * Creator: Taylor Wilkinson
 */

public class Toaster extends MoveableObject {
	
	public Image currentImg;
	public Image startingToaster;
	public Image cookingToaster;
	public Image finishedToaster;
	private Rectangle bound;
	
	public Toaster(double x, double y, double sca, String fileName0, String fileName1, String fileName2) {
		super(x, y, sca);
		
		startingToaster = ImageLoader.loadImage(fileName0);
		cookingToaster = ImageLoader.loadImage(fileName1);
		finishedToaster = ImageLoader.loadImage(fileName2);
		
		currentImg = startingToaster;
		
		Graphics g = currentImg.getGraphics();
		g.setClip(0, 0, currentImg.getWidth(null), currentImg.getHeight(null));
		bound = g.getClipBounds();
		setLocation(x, y);
	}
	
	private void setLocation(double x, double y){
		bound.setFrame(x, y, bound.getWidth(), bound.getHeight());
	}
	
	public void drawObjects(Graphics2D g2d) {
		AffineTransform at = g2d.getTransform();
			g2d.translate(bound.getX(), bound.getY());
			g2d.scale(scale, scale);
			g2d.drawImage(currentImg, 0, 0, null);
		g2d.setTransform(at);
	}
	
	public boolean cursorOnObject(int mouseX, int mouseY){
		return Util.dist(mouseX, bound.getX()) <= (scale*currentImg.getWidth(null)) && Util.dist(mouseY, bound.getY()) <= (scale*currentImg.getHeight(null));
	}

}
