package interactive.items;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import main.ImageLoader;
import main.Util;

/*
 * Jam:
 * A subclass of Canisters, which extends MoveableObject.
 * Pertains to the jar of jam on the second simulation screen.
 * Creator: Taylor Wilkinson
 */

public class Jam extends Canisters {
	
	public Image jamImg;
	public Image jamOpenImg;
	public Image currentImg;
	private Rectangle bound;
	
	public Jam(double x, double y, double sca, String fileName1, String fileName2) {
		super(x, y, sca);
		
		jamImg = ImageLoader.loadImage(fileName1);
		jamOpenImg = ImageLoader.loadImage(fileName2);
		currentImg = jamImg;
		
		Graphics g = currentImg.getGraphics();
		g.setClip(0, 0, currentImg.getWidth(null), currentImg.getHeight(null));
		bound = g.getClipBounds();
		setLocation(x, y);
	}
	
	private void setLocation(double x, double y) {
		bound.setFrame(xPos, yPos, bound.getWidth(), bound.getHeight());
	}
	
	public void drawObjects(Graphics2D g2d) {
		AffineTransform at = g2d.getTransform();
			g2d.translate(xPos,  yPos);
			g2d.scale(scale, scale);
			g2d.drawImage(currentImg, 0, 0, null);
		g2d.setTransform(at);
	}
	
	public boolean cursorOnObject(int mouseX, int mouseY){
		return Util.dist(mouseX, xPos) <= (scale*currentImg.getWidth(null)) && Util.dist(mouseY, yPos) <= (scale*currentImg.getHeight(null));
	}
	
	public boolean objectOnCounter(){
		return yPos + (scale*currentImg.getHeight(null)) >= 650;
	}
}
