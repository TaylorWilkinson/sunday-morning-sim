package background.items;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import static main.Util.*;

/*
 * SunSpotCircle:
 * A subclass of SunSpotShape, and one of the elements used by the SunSpotFactory.
 * Creates circular sun spots that appear on the introduction screen.
 * Creator: Taylor Wilkinson
 */

public class SunSpotCircle extends SunSpotShape {
	
	private double radius;
	private Ellipse2D.Double circle;
	
	
	public SunSpotCircle(double x, double y, double rad, Color c) {
		super(x, y, 2*rad, 2*rad, c);
		// TODO Auto-generated constructor stub
		radius = rad;
		circle = new Ellipse2D.Double();
	}
	
	//make the sun spots shake a little bit
	public void move() {
		super.move();
		radius += random(-1.5, 1.5);
	}

	@Override
	public void display(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.setColor(color);
		circle.setFrame(xPos-radius, yPos-radius, 2 * radius, 2 * radius);
		g2d.fill(circle);
		
	}
	
}
