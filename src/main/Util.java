package main;

public class Util {
	
	/*
	 * Util:
	 * A class used to state formulas and equations used across the program.
	 */
	
	public static double dist(double low, double high){
		double distance = Math.abs(high-low);
		return distance;
	}
	
	public static double random(double low, double high) {
		return low + Math.random() * (high - low);
	}

}
