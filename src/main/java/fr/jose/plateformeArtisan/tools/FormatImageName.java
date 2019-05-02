package fr.jose.plateformeArtisan.tools;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.List;

import fr.jose.plateformeArtisan.beans.Image;

public class FormatImageName {

	// Supprime les accents
	public static List<Image> formatImages(String nom, List<Image> imagesSociete) {

		if (nom != null) {

			nom.replaceAll("'", "");
			String image = Normalizer.normalize(nom, Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

			if (imagesSociete.size() == 0) {
				Image imgBackground = new Image();
				imgBackground.setNom(image.concat("_Background.png"));

				Image imgVignette = new Image();
				imgVignette.setNom(image.concat("_Vignette.png"));

				Image img1 = new Image();
				img1.setNom(image.concat("_1.png"));

				Image img2 = new Image();
				img2.setNom(image.concat("_2.png"));

				Image img3 = new Image();
				img3.setNom(image.concat("_3.png"));

				List<Image> images = new ArrayList<Image>();
				images.add(imgBackground);
				images.add(imgVignette);
				images.add(img1);
				images.add(img2);
				images.add(img3);
				return images;
			}

			else {

				imagesSociete.get(0).setNom(image.concat("_Background.png"));
				imagesSociete.get(1).setNom(image.concat("_Vignette.png"));
				imagesSociete.get(2).setNom(image.concat("_1.png"));
				imagesSociete.get(3).setNom(image.concat("_2.png"));
				imagesSociete.get(4).setNom(image.concat("_3.png"));

				return imagesSociete;
			}

		}
		return null;
	}

	public static String removeAccents(String nom) {
		if (nom != null) {
			nom.replaceAll("'", "");
			String image = Normalizer.normalize(nom, Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
			return image;
		}
		return null;
	}

}
