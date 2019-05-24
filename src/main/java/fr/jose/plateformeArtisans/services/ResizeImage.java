package fr.jose.plateformeArtisans.services;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class ResizeImage {

	 public static BufferedImage resize(BufferedImage img, int height, int width) {
		 	int w = img.getWidth();
		 	//calcul de la hauteur pour garder la proportion
		 	float coeff = (float) width/w;
		 	int h = (int) (img.getHeight()*coeff);
		 	System.out.println("Hauteur = " + h + "--- coeff = " + coeff);
		 	
	        Image tmp = img.getScaledInstance(width, h, Image.SCALE_SMOOTH);
	        BufferedImage resized = new BufferedImage(width, h, BufferedImage.TYPE_INT_ARGB);
	        Graphics2D g2d = resized.createGraphics();
	        g2d.drawImage(tmp, 0, 0, null);
	        g2d.dispose();
	        return resized;
	    }
}
