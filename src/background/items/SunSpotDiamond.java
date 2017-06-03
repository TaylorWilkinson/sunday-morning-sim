package background.items;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/*
 * SunSpotDiamond:
 * A subclass of SunSpotShape, and one of the elements drawn by the SunSpotFactory.
 * Creates diamond-shaped sun spots that appear on the introduction screen.
 * Creator: Taylor Wilkinson
 */

public class SunSpotDiamond extends SunSpotShape {
	
	private Rectangle2D.Double square;

	public SunSpotDiamond(double x, double y, double w, double h, Color c) {
		super(x, y, w, h, c);
		square = new Rectangle2D.Double();
	}

	// implement the abstract method as required
	public void display(Graphics2D g2d) {
		g2d.setColor(color);
		g2d.rotate(45);
		square.setFrame(xPos-sW/2, yPos-sH/2, sW, sH);
		g2d.fill(square);
		g2d.rotate(-45);
	}

}
