package interactive.items;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import main.Util;

/*
 * Canisters:
 * A subclass of MoveableObject, which is a superclass for the two canister objects: CoffeeCan and TeaBox
 * to simplify shared elements
 * Creator: Taylor Wilkinson
 */

public class Canisters extends MoveableObject {
	
	protected int width, height;
	
	public Canisters(double x, double y, double sca){
		super(x, y, sca);
	}
	
	public boolean cursorOnObject(int mouseX, int mouseY){
		return Util.dist(mouseX, xPos) <= width/2 && Util.dist(mouseY, yPos) <= height/2;
	}

}
