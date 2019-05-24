package fr.jose.plateformeArtisan.controllers;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import fr.jose.plateformeArtisan.beans.Promotion;
import fr.jose.plateformeArtisan.beans.Societe;
import fr.jose.plateformeArtisan.dao.PromotionDao;
import fr.jose.plateformeArtisan.dao.SocieteDao;
import fr.jose.plateformeArtisan.tools.FormatImageName;
import fr.jose.plateformeArtisans.services.ResizeImage;

@Controller
public class FileUploadController {

	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

	@Autowired
	private PromotionDao promotionDao;

	@Autowired
	private SocieteDao societeDao;

	/**
	 * Upload single file using Spring Controller
	 */
	@RequestMapping(value = "/artisan/promotion/uploadFile", method = RequestMethod.POST)
	public String uploadFileHandler(@RequestParam("fichier") MultipartFile fichier,
			@RequestParam(name = "promo_id", required = false) long promo_id,
			@RequestParam("modificationPromotion") boolean modificationPromotion) {
		
		

		// Création du nom de l'image avec le libellé de la catétorie
		Promotion promo = promotionDao.findById(promo_id);
		System.out.println("après promo.findById");
		Societe s = societeDao.findById(promo.getSociete().getId());
		String nomFichier  = null;
		if (!modificationPromotion) {
			nomFichier = FormatImageName
					.removeAccents(promo.getSociete().getNom().toLowerCase().replaceAll("\\s", "")) + "_promo_"
					+ (s.getPromotions().size() + 1) + ".png";
		}
		else {
			nomFichier = promo.getImage();
		}
		

		String messageUpload = null;
		if (!fichier.isEmpty()) {
			try {
				byte[] bytes = fichier.getBytes();

				// Création du répertoire où sera stocké le fichier
				String rootPath = "F:\\workspace_artisan\\plateformeArtisan\\src\\main\\webapp\\resources\\images\\societes\\promotions";
//				String rootPath = System.getProperty("catalina.home");
				System.out.println("rootpath = " + rootPath);
				File dir = new File(rootPath);
//				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Création du fichier sur le server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + nomFichier);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				System.out.println("courant = " + serverFile.getAbsolutePath());
				logger.debug("Server File Location=" + serverFile.getAbsolutePath());
				
				BufferedImage image = ImageIO.read(serverFile);				
				BufferedImage resized = ResizeImage.resize(image, 500, 500);
				
//				File output = new File("/tmp/duke-resized-500x500.png");
		        ImageIO.write(resized, "png", serverFile);

				messageUpload = "Votre fichiere " + nomFichier + " a bien été transféré";
				return "redirect:/artisan/promotion/modifier-promotion?messageUpload=" + messageUpload + "&nomFichier="
						+ nomFichier + "&promo_id=" + promo_id;
			} catch (org.springframework.web.multipart.MaxUploadSizeExceededException f) {
//				messageUpload = "Erreur de transfert de " + nomFichier + " => " + e.getMessage();
				messageUpload = "Erreur !!! le fichier " + nomFichier + " est trop volumineux !";
				return "redirect:admin/promotion/modifier-promotion?messageUpload=" + messageUpload;
			} catch (Exception e) {
//				messageUpload = "Erreur de transfert de " + nomFichier + " => " + e.getMessage();
				messageUpload = "Erreur de transfert de " + nomFichier;
				return "redirect:admin/promotion/modifier-promotion?messageUpload=" + messageUpload;
			}
		} else

		{
			messageUpload = "Erreur de trasfert de " + nomFichier + " car ce fichier est vide.";
			return "redirect:admin/promotion/modifier-promotion?messageUpload=" + messageUpload;
		}
	}

	/**
	 * Upload multiple file using Spring Controller
	 */
	@RequestMapping(value = "/uploadMultipleFile", method = RequestMethod.POST)
	public @ResponseBody String uploadMultipleFileHandler(@RequestParam("name") String[] names,
			@RequestParam("file") MultipartFile[] files) {

		if (files.length != names.length)
			return "Mandatory information missing";

		String message = "";
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			String name = names[i];
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				logger.info("Server File Location=" + serverFile.getAbsolutePath());

				message = message + "You successfully uploaded file=" + name + "<br />";
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		}
		return message;
	}
}