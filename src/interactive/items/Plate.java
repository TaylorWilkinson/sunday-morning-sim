package interactive.items;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import main.ImageLoader;
import main.Util;

/*
 * Plate:
 * A subclass of MoveableObject. Pertains to the plate object on screens 2 and 3.
 * Provides the multiple image information for the different plate image states
 * Creator: Taylor Wilkinson
 */

public class Plate extends MoveableObject {
	
	public Image currentImg;
	public Image emptyPlate;
	public Image toastOnPlate;
	public Image toastWithJam;
	public Image toastWithPB;
	private Rectangle bound;
	
	public Plate(double x, double y, double sca, String fileName0, String fileName1, String fileName2, String fileName3) {
		super(x, y, sca);
		
		emptyPlate = ImageLoader.loadImage(fileName0);
		toastOnPlate = ImageLoader.loadImage(fileName1);
		toastWithJam = ImageLoader.loadImage(fileName2);
		toastWithPB = ImageLoader.loadImage(fileName3);
		
		currentImg = emptyPlate;
		
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
}
