package background.items;

/*
 * SunSpotFactory:
 * A superclass to SunSpotFactory.
 * Is used to call the SunSpotConcreteFactory in order to randomly draw circular and diamond sunspots
 */

public abstract class SunSpotFactory {
	
	public abstract SunSpotShape createSunspot(int type);

}
