package interactive.items;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import main.ImageLoader;
import main.Util;

/*
 * Mugs:
 * A class which extends MoveableObject.
 * Pertains to the mug objects that appear on all three simulation screens.
 * Creator: Taylor Wilkinson
 */

public class Mugs extends MoveableObject{
	
	public Image mugImg;
	public Image teaMug;
	public Image coffeeMug;
	
	public Image currentMug;
	
	private Rectangle bound;
	
	protected int width, height;
	
	//113 x 78
	public Mugs(double x, double y, double sca, String fileName, String fileName1, String fileName2){
		super(x, y, sca);

		mugImg = ImageLoader.loadImage(fileName);
		teaMug = ImageLoader.loadImage(fileName1);
		coffeeMug = ImageLoader.loadImage(fileName2);
		
		currentMug = mugImg;
		Graphics g = currentMug.getGraphics();
		g.setClip(0, 0, currentMug.getWidth(null), currentMug.getHeight(null));
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
			g2d.drawImage(currentMug, 0, 0, null);
		g2d.setTransform(at);
	}
	
	public boolean cursorOnObject(int mouseX, int mouseY){
		return Util.dist(mouseX, xPos) <= (scale*currentMug.getWidth(null)) && Util.dist(mouseY, yPos) <= (scale*currentMug.getHeight(null));
	}
	
	public boolean objectOnCounter(){
		return yPos + (scale*currentMug.getHeight(null)) >= 650;
	}

}
