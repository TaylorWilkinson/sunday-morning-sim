package background.items;

import java.awt.Color;
import java.awt.Graphics2D;

/*
 * SunSpotShape:
 * The superclass to the SunSpotCircle and SunSpotDiamond.
 * Provides the shared parameters and functions used by both shape types.
 */

public abstract class SunSpotShape {
	protected double xPos, yPos;
	protected double sW, sH;
	protected Color color;

	public SunSpotShape(double x, double y, double w, double h, Color c) {
		xPos = x;
		yPos = y;
		sW = w;
		sH = h;
		color = c;
	}

	public void move() {
		xPos += 0;
		yPos += 0;
	}

	// A generic shape does not really know
	// how to be displayed so declare it as
	// an abstract method
	public abstract void display(Graphics2D g2d);
}
