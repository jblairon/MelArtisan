package fr.jose.plateformeArtisan.controllers;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.jose.plateformeArtisan.beans.Horaire;
import fr.jose.plateformeArtisan.beans.Jour;
import fr.jose.plateformeArtisan.beans.Societe;
import fr.jose.plateformeArtisan.dao.AdresseDao;
import fr.jose.plateformeArtisan.dao.HoraireDao;
import fr.jose.plateformeArtisan.dao.MetierDao;
import fr.jose.plateformeArtisan.dao.PrestationDao;
import fr.jose.plateformeArtisan.dao.SocieteDao;
import fr.jose.plateformeArtisan.dao.UtilisateurDao;

@Controller
public class TestDataController {

	@Autowired(required=true)
	private UtilisateurDao utilisateurDao;

	@Autowired(required=true)
	private AdresseDao adresseDao;

	@Autowired(required = true)
	private MetierDao metierDao;

	@Autowired(required = true)
	private PrestationDao prestationDao;

	@Autowired
	private SocieteDao societeDao;

	@Autowired(required = true)
	private HoraireDao horaireDao;

	@RequestMapping(value = "/test-data", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
//		Utilisateur u = new Utilisateur();
//		Adresse a = new Adresse();
//		a = adresseDao.findById(2);
//		
//		u.setAdresse(a);
//		u.setClient(true);
//		
//		Date date = new Date();
//		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//		u.setDateCreation(sqlDate);
//		
//		u.setEmail("mero-59 @hotmail.fr");
//		u.setGenre(Genre.Madame);
//		u.setMdp("Foxuno59");
//		u.setNom("Langlant");
//		u.setPrenom("Fabienne");
//		
//		System.out.println("date = " + u.getDateCreation());
//		
//		utilisateurDao.save(u);
		

		Societe s = societeDao.findById(46);
		Horaire h = new Horaire();

		h.setAmOpen("07:00");
		h.setAmClose("12:00");

		h.setPmOpen("13:00");
		h.setPmClose("19:30");

		h.setJour(Jour.Samedi);
		horaireDao.save(h);

		// -----------------
//		Horaire h2 = new Horaire();
//
//		h2.setAmOpen("07:00");
//		h2.setAmClose("12:00");
//
//		h2.setPmOpen("13:00");
//		h2.setPmClose("18:00");
//
//		h2.setJour(Jour.Jeudi);
//		horaireDao.save(h2);
		
		// --------------------------------
		s.getHoraires().add(h);
//		s.getHoraires().add(h2);
		societeDao.update(s);

		return "home";
	}

//	public UtilisateurDao getUtilisateurDao() {
//		return utilisateurDao;
//	}
//
//	public void setUtilisateurDao(UtilisateurDao utilisateurDao) {
//		this.utilisateurDao = utilisateurDao;
//	}

}
