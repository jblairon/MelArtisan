package fr.jose.plateformeArtisan.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.jose.plateformeArtisan.beans.Horaire;
import fr.jose.plateformeArtisan.beans.Newsletter;
import fr.jose.plateformeArtisan.beans.Societe;
import fr.jose.plateformeArtisan.beans.SocieteDateVacances;
import fr.jose.plateformeArtisan.beans.Utilisateur;
import fr.jose.plateformeArtisan.dao.HoraireDao;
import fr.jose.plateformeArtisan.dao.NewsletterDao;
import fr.jose.plateformeArtisan.dao.SocieteDao;
import fr.jose.plateformeArtisan.dao.SocieteDateVacancesDao;
import fr.jose.plateformeArtisan.dao.UtilisateurDao;
import fr.jose.plateformeArtisan.formbeans.ContactForm;
import fr.jose.plateformeArtisan.formbeans.HorairesForm;
import fr.jose.plateformeArtisan.formbeans.SocieteDateVacancesForm;
import fr.jose.plateformeArtisan.tools.DateUtils;
import fr.jose.plateformeArtisan.tools.EmailTools;
import fr.jose.plateformeArtisan.tools.FormatNombre;
import fr.jose.plateformeArtisans.services.SocieteServices;

@Controller
public class ArtisanController {

	@Autowired
	private SocieteDao societeDao;

	@Autowired
	private NewsletterDao newsletterDao;

	@Autowired
	private HoraireDao horaireDao;

	@Autowired
	private SocieteDateVacancesDao societeDateVacancesDao;

	@Autowired
	private UtilisateurDao utilisateurDao;

	@Transactional
	@RequestMapping(value = "/artisan/societe/mes-horaires", method = RequestMethod.GET)
	public String societeHoraires(HttpServletRequest request,
			@RequestParam(name = "id", required = true) long societeId,
			@RequestParam(name = "msgEnvoiMail", required = false) String msgEnvoiMail, Model model) {
		
		System.out.println("msgEnvoiMail = " + msgEnvoiMail);

		// Si l'id de la société est différent de 0 donc il s'agit d'une modification de
		if (societeId != 0) { // c'est une modification de société
			Societe s = societeDao.findById(societeId);
			
			if(msgEnvoiMail != null && msgEnvoiMail != "") {
				String classMsgEnvoiMail= "";
				if(msgEnvoiMail.contains("Erreur")) {
					classMsgEnvoiMail = "alert alert-block alert-danger";
				}
				else {
					classMsgEnvoiMail = "alert alert-block alert-info";
				}
				model.addAttribute("msgEnvoiMail", msgEnvoiMail);
				model.addAttribute("classMsgEnvoiMail", classMsgEnvoiMail);
			}

			model.addAttribute("horaires", s.getHoraires());
			model.addAttribute("societe", s);
		}

		return "artisan/mesHoraires";
	}

	@RequestMapping(value = { "/artisan/societe/modifier-horaires",
			"/admin/societe/modifier-horaires" }, method = RequestMethod.GET)
	public ModelAndView modifierHorairesSociete(HttpServletRequest request,
			@RequestParam(name = "id", required = true) long id,
			@RequestParam(name = "societe_id", required = true) long societe_id) {
		Map<String, Object> model = new HashMap<>();

		Horaire h = horaireDao.findById(id);

		HorairesForm form = new HorairesForm();
		form.setHoraireId(h.getId());

		String amOpenHeure = (h.getAmOpen().equals("Fermé") ? h.getAmOpen() : h.getAmOpen().substring(0, 2));
		String amOpenMinutes = (h.getAmOpen().equals("Fermé") ? h.getAmOpen() : h.getAmOpen().substring(3));
		String amCloseHeure = (h.getAmClose().equals("Fermé") ? h.getAmClose() : h.getAmClose().substring(0, 2));
		String amCloseMinutes = (h.getAmClose().equals("Fermé") ? h.getAmClose() : h.getAmClose().substring(3));

		String pmOpenHeure = (h.getPmOpen().equals("Fermé") ? h.getPmOpen() : h.getPmOpen().substring(0, 2));
		String pmOpenMinutes = (h.getPmOpen().equals("Fermé") ? h.getPmOpen() : h.getPmOpen().substring(3));
		String pmCloseHeure = (h.getPmClose().equals("Fermé") ? h.getPmClose() : h.getPmClose().substring(0, 2));
		String pmCloseMinutes = (h.getPmClose().equals("Fermé") ? h.getPmClose() : h.getPmClose().substring(3));

		form.setAmCloseHeure(amCloseHeure);
		form.setAmCloseMinutes(amCloseMinutes);
		form.setAmOpenHeure(amOpenHeure);
		form.setAmOpenMinutes(amOpenMinutes);

		form.setPmCloseHeure(pmCloseHeure);
		form.setPmCloseMinutes(pmCloseMinutes);
		form.setPmOpenHeure(pmOpenHeure);
		form.setPmOpenMinutes(pmOpenMinutes);

		List<String> heures = SocieteServices.tableauDesHeures();
		heures.add("Fermé");

		List<String> minutes = SocieteServices.tableauDesMinutes();
		minutes.add("Fermé");

		model.put("heures", heures);
		model.put("minutes", minutes);
		model.put("jour", h.getJour());
		model.put("societe_id", societe_id);

		model.put("horairesForm", form);

		return new ModelAndView("artisan/modifierHoraires", model);

	}

	// Sauevgarde des modifications sur les horaires de la societe
	@RequestMapping(value = "/artisan/societe/valider-horaires", method = RequestMethod.POST)
	public String sauvegarderHoraires(HttpServletRequest request,
			@Valid @ModelAttribute("horairesForm") HorairesForm form, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			if (result.getFieldError().getField().toString().equals("email")) {
				model.addAttribute("msg", "Errreur : le format de l'adresse email n'est pas correct !");
			} else {
				model.addAttribute("msg", "Errreur : au moins un des champs n'a pas été correctement rempli !");
			}
			model.addAttribute("errors", result);

			model.addAttribute("horairesForm", form);
			return ("redirect:artisan/modifier-horaires");
		}
		Societe s = societeDao.findById((long) request.getSession().getAttribute("societeId"));

		Horaire h = horaireDao.findById(form.getHoraireId());
		System.out.println("form.getAmOpenHeure() = " + form.getAmOpenHeure());

		if (form.getAmOpenHeure().equals("Fermé")) {
			System.out.println("C'est bien fermé");
			h.setAmOpen("Fermé");
			h.setAmClose("Fermé");
			
		} else {
			h.setAmOpen(form.getAmOpenHeure() + ":" + form.getAmOpenMinutes());
			h.setAmClose(form.getAmCloseHeure() + ":" + form.getAmCloseMinutes());
		}

		if (form.getPmOpenHeure().equals("Fermé")) {
			h.setPmOpen("Fermé");
			h.setPmClose("Fermé");
			s.setPmCloseToDay(true);
		} else {
			h.setPmOpen(form.getPmOpenHeure() + ":" + form.getPmOpenMinutes());
			h.setPmClose(form.getPmCloseHeure() + ":" + form.getPmCloseMinutes());
			s.setPmCloseToDay(false);
			
		}
		try {
			societeDao.update(s);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			horaireDao.update(h);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Pour l'envoi de mails à tous le sutilisateurs ayant accepté la nexsletter
		// on récupère la liste de newsletters de la société
		
		
		List<Newsletter> news = new ArrayList<Newsletter>();
		List<String> emails = new ArrayList<>();

		news = newsletterDao.findBySociete_id(s.getId());
		for (Newsletter n : news) {
			Utilisateur utilisateur = utilisateurDao.findById(n.getUtilisateur_id());
			if (utilisateur != null)
				emails.add(utilisateur.getEmail());
		}
		// on envoie les mails
		String msgMail = "La société " + s.getNom() + " a modifié ses horaires pour le " +
		h.getJour() + ". Désormais les horaires seront : ";
		if(h.getAmOpen().equals("Fermé") && h.getPmOpen().equals("Fermé")) {
			msgMail += "  << Fermé toute la journée >>";
		}
		else if (!h.getAmOpen().equals("Fermé") && h.getPmOpen().equals("Fermé")) {
			msgMail += " << Ouvert le matin de " + h.getAmOpen() + " à " + h.getAmClose() + " mais fermé l'après-midi >>";
		}
		else if(h.getAmOpen().equals("Fermé") && !h.getPmOpen().equals("Fermé")) {
			msgMail += " << Fermé le matin mais ouvert l'après-midi de " + h.getPmOpen() + " à " + h.getPmClose() + ">>";
		}
		else {
			msgMail += " << le matin de " + h.getAmOpen() + " à " + h.getAmClose();
			msgMail += " et l'après-midi de " + h.getPmOpen() + " à " + h.getPmClose() + " >> ";
		}
		
		Utilisateur u = new Utilisateur();

		if(request.getSession().getAttribute("user_id")!= null) {
			u = utilisateurDao.findById((long) request.getSession().getAttribute("user_id"));
		}
		else {
			return "login";
		}
		String sujet = s.getNom() + " vient de modifier ses horaires pour " + h.getJour() + " !!!";
		String msgEnvoiMail = "";
		for (String email : emails) {
			try {
				EmailTools.sendEmailToClient(u.getEmail(), sujet, msgMail, email);
				msgEnvoiMail = "Un email a été envoyé à tous vos clients ayant accepté la newsletter";
			} catch (Exception e) {
				System.out.println("Erreur = " + e.getMessage());
				msgEnvoiMail = "L'envoi de mail a échoué, veuillez contacter l'administrateur du site";
				e.printStackTrace();
			}
			model.addAttribute("msgEnvoiMail", msgEnvoiMail);
		}
		// Fin Envoi mails

		if (request.getSession().getAttribute("user_id") != null) {
			return "redirect:/artisan/societe/mes-horaires?id=" + request.getSession().getAttribute("societeId");
		}
		return "login";
	}

	@RequestMapping(value = { "/artisan/societe/mes-prochaines-vacances",
			"/admin/societe/mes-prochaines-vacances" }, method = RequestMethod.GET)
	public ModelAndView mesProchainesVacances(HttpServletRequest request,
			@RequestParam(name = "id", required = true) long id) {
		Map<String, Object> model = new HashMap<>();

		SocieteDateVacancesForm form = new SocieteDateVacancesForm();

		Societe s = societeDao.findById(id);
		if (s.getSocietedatevacances() != null) {
			form.setDateDebut(DateUtils.stringSqlToLocalDate_FR(s.getSocietedatevacances().getDateDebut().toString()));
			form.setDateFin(DateUtils.stringSqlToLocalDate_FR(s.getSocietedatevacances().getDateFin().toString()));
			form.setRaison(s.getSocietedatevacances().getRaison());
		}

		model.put("vacances-form", form);
		model.put("societe", s);

		return new ModelAndView("artisan/mesProchainesVacances", model);
	}

	// enregistrement des dates de vacances
	@RequestMapping(value = "/artisan/societe/valider-vacances", method = RequestMethod.POST)
	public ModelAndView validerVacances(HttpServletRequest request,
			@Valid @ModelAttribute("vacances-form") SocieteDateVacancesForm form, BindingResult result)
			throws java.text.ParseException {
		Map<String, Object> model = new HashMap<String, Object>();

		if (result.hasErrors()) {
			model.put("errors", result);
			model.put("vacances-form", form);

			if (result.getFieldError().getField().toString().equals("email")) {
				model.put("msg", "Errreur : le format de l'adresse email n'est pas correct !");
			} else {
				model.put("msg", "Errreur : au moins un des champs n'a pas été correctement rempli !");
			}
			return new ModelAndView("artisan/mesProchainesVacances", model);
		}

		SocieteDateVacances sdv = null;
		Societe s = societeDao.findById((long) request.getSession().getAttribute("societeId"));
		boolean sauv = false;
		if (s.getSocietedatevacances() == null) {
			sdv = new SocieteDateVacances();
			sauv = true;
		} else {
			sdv = societeDateVacancesDao.findById(s.getSocietedatevacances().getId());
		}
		sdv.setDateDebut(DateUtils.stringToSqlDate(form.getDateDebut()));
		sdv.setDateFin(DateUtils.stringToSqlDate(form.getDateFin()));
		sdv.setRaison(form.getRaison());

		if (request.getSession().getAttribute("societeId") != null) {
			System.out.println("dans sauvegarde");

			sdv.setSociete(s);
			if (sauv) {
				societeDateVacancesDao.save(sdv);
				s.setSocietedatevacances(sdv);
				societeDao.update(s);
			} else
				societeDateVacancesDao.update(sdv);
			
			
			
			// Pour l'envoi de mails à tous le sutilisateurs ayant accepté la nexsletter
			// on récupère la liste de newsletters de la société
			
			List<Newsletter> news = new ArrayList<Newsletter>();
			List<String> emails = new ArrayList<>();

			news = newsletterDao.findBySociete_id(s.getId());
			for (Newsletter n : news) {
				Utilisateur utilisateur = utilisateurDao.findById(n.getUtilisateur_id());
				if (utilisateur != null)
					emails.add(utilisateur.getEmail());
			}
			// on envoie les mails
			String msgMail = "La société " + s.getNom() + " a modifié ses dates pour ses prochaines vacances, elle sera donc fermée du  " 
			+ DateUtils.stringSqlToLocalDate_FR(sdv.getDateDebut().toString()) +
			" au " + DateUtils.stringSqlToLocalDate_FR(sdv.getDateFin().toString()) + " inclus";
			
			Utilisateur u = new Utilisateur();

			if(request.getSession().getAttribute("user_id")!= null) {
				u = utilisateurDao.findById((long) request.getSession().getAttribute("user_id"));
			}
			else {
				return new ModelAndView("login");
			}
			String sujet = s.getNom() + " vient de modifier ses dates de prochaines vacances !!!";
			String msgEnvoiMail = "";
			for (String email : emails) {
				try {
					EmailTools.sendEmailToClient(u.getEmail(), sujet, msgMail, email);
					msgEnvoiMail = "Un email a été envoyé à tous vos clients ayant accepté la newsletter";
				} catch (Exception e) {
					System.out.println("Erreur = " + e.getMessage());
					msgEnvoiMail = "L'envoi de mail a échoué, veuillez contacter l'administrateur du site";
					e.printStackTrace();
				}
				model.put("msgEnvoiMail", msgEnvoiMail);
			}
			// Fin Envoi mails

		}
		
		

		return new ModelAndView("redirect:/artisan/ma-societe?id=" + s.getId(), model);

	}

	@Transactional
	@RequestMapping("/artisan/contact")
	public ModelAndView showContact(HttpServletRequest request, @RequestParam(name = "id", required = false) long id,
			@RequestParam(name = "user_id", required = false) long user_id) {
		Map<String, Object> model = new HashMap<>();

		ContactForm cf = new ContactForm();
		if (request.getSession().getAttribute("user_email") != null) {
			String email = request.getSession().getAttribute("user_email").toString();
			cf.setEmail(email);
		}

		if (id != 0) {
			Societe s = societeDao.findById(id);
			cf.setEmailTo(s.getEmail());
			model.put("societeNom", s.getNom());
			model.put("societe", s);
		}

		Societe societeArtisan = societeDao.findById(user_id);
		model.put("societe", societeArtisan);

		model.put("contact-form", cf);
		return new ModelAndView("artisan/contact", model);
	}

	@RequestMapping(value = "/artisan/envoyer-message", method = RequestMethod.POST)
	public String sendMessage(HttpServletRequest request, @Valid @ModelAttribute("contact-form") ContactForm form,
			BindingResult result, Model model) {
		String messageErreur = null;
		String messageSuccess = null;

		if (result.hasErrors()) {
			model.addAttribute("errors", result);
			model.addAttribute("contact-form", form);
			model.addAttribute("msg", "Assurez-vous que les champs soient correctement remplis");

			return "client/contact";
		}

		String from = form.getEmail();
		String subject = form.getSubject();
		String message = form.getMessage();

		if (form.getEmailTo() != null) {
			messageErreur = EmailTools.sendEmailToArtisan(form.getEmail(), form.getSubject(), form.getMessage(),
					form.getEmailTo());
		}

		try {
			messageErreur = EmailTools.sendEmailToAdmin(from, subject, message);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (messageErreur != null) {
			model.addAttribute("messageErreur", messageErreur);
			return "client/contact";
		} else {
			messageSuccess = "Votre message a bien été envoyé";
			model.addAttribute("messageSuccess", messageSuccess);
		}

		return "redirect:/client/accueil";

	}
	/*
	 * Partie consacrée aux promotions
	 */

}
