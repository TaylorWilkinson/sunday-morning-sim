package interactive.items;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;

import main.AppPanel;
import main.ImageLoader;

/*
 * Jam:
 * A class which extends MoveableObject.
 * Pertains to the jar of jam on the second simulation screen.
 * Creator: Taylor Wilkinson
 */

public class Kettle extends KeyControlledObject {
	
	protected int width, height;
	
	public Image kettleImg;
	public Image fullKettleImg;
	public Image currentImg;
	
	private Rectangle bound;
	
	public boolean fullKettle;
	
	public Kettle(double x, double y, double dx, double dy, double sca, String fileName1, String fileName2){
		super(x, y, dx, dy, sca);
		
		kettleImg = ImageLoader.loadImage(fileName1);
		fullKettleImg = ImageLoader.loadImage(fileName2);
		Graphics g = kettleImg.getGraphics();
		g.setClip(0, 0, kettleImg.getWidth(null), kettleImg.getHeight(null));
		bound = g.getClipBounds();
		setLocation(x, y);
		
		currentImg = kettleImg;
	}
	
	public void drawObjects(Graphics2D g2d) {
		AffineTransform at = g2d.getTransform();
			g2d.translate(bound.getX(), bound.getY());
			g2d.scale(scale, scale);
			g2d.drawImage(currentImg, 0, 0, null);
	
			//change image when kettle is filled
			if (checkKettleUnderTap() == true) {
				currentImg = fullKettleImg;
			}
		g2d.setTransform(at);
	}
	
	private void setLocation(double x, double y) { // set location of fish
		bound.setFrame(x, y, bound.getWidth(), bound.getHeight());
	}
	
	public void move(double dx) {
		double nextX = bound.getX() + dx;
		double nextY = bound.getY();
		this.setLocation(nextX,  nextY);
		detectWalls();
	}
	
	public void detectWalls() {
		if (bound.getX() <= 0) {
			this.setLocation(0, bound.getY());
		}
		if (bound.getX() >= AppPanel.panW - width) {
			this.setLocation(AppPanel.panW - width, bound.getY());
		}
	}
	
	public boolean checkKettleUnderTap() {
		boolean underTap = false;
		if (bound.getX() == 750) {
			underTap = true;
		}
		return underTap;
	}
}
