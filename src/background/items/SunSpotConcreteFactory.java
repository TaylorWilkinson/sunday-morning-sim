package background.items;

import java.awt.Color;

import main.AppPanel;
import static main.Util.*;

/*
 * SunSpotConcreteFactory:
 * A subclass of SunSpotFactory.
 * returns which of the two types will be drawn to the SunSpotFactory.
 */

public class SunSpotConcreteFactory extends SunSpotFactory {
	
	private Color sunspotColor = new Color(255, 253, 206, 100);

	@Override
	public SunSpotShape createSunspot(int type) {
		// TODO Auto-generated method stub
		SunSpotShape sunspot = null;
		
		if (type == 1)
			sunspot = new SunSpotCircle(random(0, AppPanel.panW), random(0, AppPanel.panH), random(2, 10), sunspotColor);
		else if (type == 2)
			sunspot = new SunSpotDiamond(random(0, AppPanel.panW), random(0, AppPanel.panH), 8, 8, sunspotColor);
		
		return sunspot;
	}

}
