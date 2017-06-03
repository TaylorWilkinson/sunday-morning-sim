package interactive.items;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import main.ImageLoader;
import main.Util;

/*
 * Peanut Butter
 * A subclass of Canisters, which extends MoveableObject.
 * Pertains to the jar of peanut butter on the second simulation screen.
 * Creator: Taylor Wilkinson
 */

public class PeanutButter extends Canisters {
	
	public Image pbImg;
	public Image pbOpenImg;
	public Image currentImg;
	
	private Rectangle bound;
	
	public PeanutButter(double x, double y, double sca, String fileName1, String fileName2) {
		super(x, y, sca);
		
		pbImg = ImageLoader.loadImage(fileName1);
		pbOpenImg = ImageLoader.loadImage(fileName2);
		currentImg = pbImg;
		
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
			g2d.translate(xPos, yPos);
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
