package background.items;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import main.ImageLoader;
import main.ImageLoader;

/*
 * TV Screen:
 * A static class meant to display the images on the TV screen.
 * Would allow for image-based animation by using an array of TV images to run through.
 * Creator: Taylor Wilkinson
 */

public class TVScreen {
	
	private Image[] tvAnimation = new Image[6];
	private int tvIndex = 0;
	protected double xPos, yPos, scale;
	
	public TVScreen(double x, double y, double sca) {
		xPos = x;
		yPos = y;
		scale = sca;

		for (int i=0; i<tvAnimation.length; i++) {
			tvAnimation[i] = ImageLoader.loadImage("assents/tvScreen"+i+".png");
		}
	}
	
	public void display(Graphics2D g2d){
		AffineTransform at = g2d.getTransform();
			g2d.translate(xPos, yPos);
			g2d.scale(scale, scale);
			
			g2d.drawImage(tvAnimation[tvIndex], 0, 0, null);
			tvIndex++;
			if (tvIndex > 6) {
				tvIndex = 0;
			}
		g2d.setTransform(at);
	}
}
