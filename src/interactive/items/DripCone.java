package interactive.items;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

import main.ImageLoader;
import main.Util;

/*
 * Drip Cone:
 * A subclass of MoveableObject that pertains to the coffee's drip cone filter.
 * Creator: Taylor Wilkinson
 */

public class DripCone extends MoveableObject {
	
	public Image dripConeImg;

	public DripCone(double x, double y, double sca, String fileName) {
		super(x, y, sca);
		
		dripConeImg = ImageLoader.loadImage(fileName);
		Graphics g = dripConeImg.getGraphics();
		g.setClip(0, 0, dripConeImg.getWidth(null)/2, dripConeImg.getHeight(null)/2);
		bound = g.getClipBounds();
		setLocation(x, y);
	}

	private void setLocation(double x, double y) {
		// TODO Auto-generated method stub
		bound.setFrame(xPos, yPos, bound.getWidth(), bound.getHeight());
	}
	
	public void drawObjects(Graphics2D g2d) {
		AffineTransform at = g2d.getTransform();
			g2d.translate(xPos,  yPos);
			g2d.scale(scale, scale);
			g2d.drawImage(dripConeImg, 0, 0, null);
		g2d.setTransform(at);
	}
	
	public boolean cursorOnObject(int mouseX, int mouseY){
		return Util.dist(mouseX, xPos) <= (scale*dripConeImg.getWidth(null)) && Util.dist(mouseY, yPos) <= (scale*dripConeImg.getHeight(null));
	}
	
	public boolean objectOnCounter(){
		return yPos + (scale*dripConeImg.getHeight(null)) >= 650;
	}

}
