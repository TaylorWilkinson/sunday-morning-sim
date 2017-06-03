package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/*
 * ImageLoader:
 * A class made to help with loading in Buffered Images
 * Provided by: Eric Yang
 */

public class ImageLoader {
	
	public static BufferedImage loadImage (String imgFile) {
		BufferedImage img = null;
		
		try {
			img = ImageIO.read(new File(imgFile));
		} catch (IOException e) {
		}
		return img;
	}
}
