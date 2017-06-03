package interactive.items;

import java.awt.Rectangle;

/*
 * MoveableObject:
 * an abstract class to provide a base for all interactive objects
 * that can be moved with the mouse.
 * Creator: Taylor Wilkinson
 */

public abstract class MoveableObject {
	protected double xPos, yPos, scale;
	protected Rectangle bound;
	
	MoveableObject(double x, double y, double sca){
		xPos = x;
		yPos = y;
		this.scale = sca;
	}
	
	public void resetLocation (int x, int y){
		xPos = x;
		yPos = y;
	}
}