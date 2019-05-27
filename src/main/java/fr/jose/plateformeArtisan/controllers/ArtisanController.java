package fr.jose.plateformeArtisan.controllers;

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
import fr.jose.plateformeArtisan.beans.Societe;
import fr.jose.plateformeArtisan.beans.SocieteDateVacances;
import fr.jose.plateformeArtisan.beans.Utilisateur;
import fr.jose.plateformeArtisan.dao.HoraireDao;
import fr.jose.plateformeArtisan.dao.SocieteDao;
import fr.jose.plateformeArtisan.dao.SocieteDateVacancesDao;
import fr.jose.plateformeArtisan.dao.UtilisateurDao;
import fr.jose.plateformeArtisan.formbeans.ContactForm;
import fr.jose.plateformeArtisan.formbeans.HorairesForm;
import fr.jose.plateformeArtisan.formbeans.SocieteDateVacancesForm;
import fr.jose.plateformeArtisan.tools.DateUtils;
import fr.jose.plateformeArtisan.tools.EmailTools;
import fr.jose.plateformeArtisans.services.SocieteServices;

@Controller
public class ArtisanController {

	@Autowired
	private SocieteDao societeDao;

	@Autowired
	private HoraireDao horaireDao;

	@Autowired
	private SocieteDateVacancesDao societeDateVacancesDao;

	@Autowired
	private UtilisateurDao utilisateurDao;

	@Transactional
	@RequestMapping(value = "/artisan/societe/mes-horaires", method = RequestMethod.GET)
	public String societeHoraires(HttpServletRequest request,
			@RequestParam(name = "id", required = true) long societeId, Model model) {

		// Si l'id de la société est différent de 0 donc il s'agit d'une modification de
		if (societeId != 0) { // c'est une modification de société
			Societe s = societeDao.findById(societeId);

			model.addAttribute("horaires", s.getHoraires());
			model.addAttribute("societe", s);
		}

		return "artisan/mesHoraires";
	}

	@RequestMapping(value = { "/artisan/societe/modifier-horaires",
			"/admin/societe/modifier-horaires" }, method = RequestMethod.GET)
	public ModelAndView modifierHorairesSociete(HttpServletRequest request,
			@RequestParam(name = "id", required = true) long id) {
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

		Horaire h = horaireDao.findById(form.getHoraireId());

		if (form.getAmOpenHeure().equals("Fermé")) {
			h.setAmOpen("Fermé");
			h.setAmClose("Fermé");
		} else {
			h.setAmOpen(form.getAmOpenHeure() + ":" + form.getAmOpenMinutes());
			h.setAmClose(form.getAmCloseHeure() + ":" + form.getAmCloseMinutes());
		}

		if (form.getPmOpenHeure().equals("Fermé")) {
			h.setPmOpen("Fermé");
			h.setPmClose("Fermé");
		} else {
			h.setPmOpen(form.getPmOpenHeure() + ":" + form.getPmOpenMinutes());
			h.setPmClose(form.getPmCloseHeure() + ":" + form.getPmCloseMinutes());
		}

		try {
			horaireDao.update(h);
		} catch (Exception e) {
			e.printStackTrace();
		}

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

		}

		return new ModelAndView("redirect:/artisan/ma-societe?id=" + s.getId(), model);

	}

	@Transactional
	@RequestMapping("/artisan/contact")
	public ModelAndView showContact(HttpServletRequest request, @RequestParam(name = "id", required = false) long id,
			@RequestParam(name = "user_id", required = false) long user_id) {
		Map<String, Object> model = new HashMap<>();

		System.out.println("session = " + request.getSession().getAttribute("user_id"));
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
