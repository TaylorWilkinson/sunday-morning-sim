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

import main.ImageLoader;
import main.Util;

/*
 * Coffee Can:
 * A subclass of Canisters, which extends MoveableObject.
 * Pertains to the can of coffee grounds.
 * Creator: Taylor Wilkinson
 */

public class CoffeeCan extends Canisters {
	
	private Image coffeeImg;
	
	public CoffeeCan(double x, double y, double sca, String fileName) {
		super(x, y, sca);
		
		coffeeImg = ImageLoader.loadImage(fileName);
		Graphics g = coffeeImg.getGraphics();
		g.setClip(0, 0, coffeeImg.getWidth(null)/2, coffeeImg.getHeight(null)/2);
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
			g2d.drawImage(coffeeImg, 0, 0, null);
		g2d.setTransform(at);
	}
	
	public boolean cursorOnObject(int mouseX, int mouseY){
		return Util.dist(mouseX, xPos) <= (scale*coffeeImg.getWidth(null)) && Util.dist(mouseY, yPos) <= (scale*coffeeImg.getHeight(null));
	}
	
	public boolean objectOnCounter(){
		return yPos + (scale*coffeeImg.getHeight(null)) >= 650;
	}
}
