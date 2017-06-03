package interactive.items;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

import main.ImageLoader;
import main.Util;

/*
 * Tea Can:
 * A subclass of Canisters, which extends MoveableObject.
 * Pertains to the box of tea.
 * Creator: Taylor Wilkinson
 */

public class TeaBox extends Canisters{
	
	private Image teaImg;
	
	public TeaBox(double x, double y, double sca, String fileName) {
		super(x, y, sca);
		
		//scale = 0.25;
		
		teaImg = ImageLoader.loadImage(fileName);
		Graphics g = teaImg.getGraphics();
		g.setClip(0, 0, teaImg.getWidth(null)/2, teaImg.getHeight(null)/2);
		bound = g.getClipBounds();
		setLocation(x, y);
	}
	
	private void setLocation(double x, double y) { // set location of fish
		bound.setFrame(xPos, yPos, bound.getWidth(), bound.getHeight());
	}

	public void drawObjects(Graphics2D g2d) {
		AffineTransform at = g2d.getTransform();
			g2d.translate(xPos,  yPos);
			g2d.scale(scale, scale);
			g2d.drawImage(teaImg, 0, 0, null);
		g2d.setTransform(at);
	}
	
	public boolean cursorOnObject(int mouseX, int mouseY){
		return Util.dist(mouseX, xPos) <= (3*scale*teaImg.getWidth(null))/4 && Util.dist(mouseY, yPos) <= (scale*teaImg.getHeight(null));
	}
	public boolean objectOnCounter(){
		return yPos + (scale*teaImg.getHeight(null)) >= 650;
	}
}
