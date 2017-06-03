package interactive.items;

/*
 * KeyControlledObject:
 * an abstract class to provide a base for all interactive objects
 * that can be moved with the use of the keys
 * Creator: Taylor Wilkinson
 */

public abstract class KeyControlledObject {
	
	public double xPos, yPos, xSpeed, ySpeed, scale;
	
	KeyControlledObject(double x, double y, double dx, double dy, double sca){
		xPos = x;
		yPos = y;
		xSpeed = dx;
		ySpeed = dy;
		scale = sca;
	}
}
