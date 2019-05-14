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

import fr.jose.plateformeArtisan.beans.Categorie;
import fr.jose.plateformeArtisan.beans.Metier;
import fr.jose.plateformeArtisan.beans.Promotion;
import fr.jose.plateformeArtisan.beans.Societe;
import fr.jose.plateformeArtisan.beans.Utilisateur;
import fr.jose.plateformeArtisan.dao.PromotionDao;
import fr.jose.plateformeArtisan.dao.SocieteDao;
import fr.jose.plateformeArtisan.dao.UtilisateurDao;
import fr.jose.plateformeArtisan.formbeans.CreationCategorieForm;
import fr.jose.plateformeArtisan.formbeans.PromotionForm;
import fr.jose.plateformeArtisan.tools.DateUtils;
import fr.jose.plateformeArtisan.tools.FormatImageName;

@Controller
public class PromotionController {

	@Autowired
	private PromotionDao promotionDao;

	@Autowired
	private SocieteDao societeDao;

	@Autowired
	private UtilisateurDao utilisateurDao;

	@Transactional
	@RequestMapping(value = { "/artisan/societe/mes-promotions",
			"/admin/societe/mes-promotions" }, method = RequestMethod.GET)
	public String societePromotions(HttpServletRequest request,
			@RequestParam(name = "id", required = true) long societeId, Model model) throws Exception {

		Utilisateur u = new Utilisateur();
		try {
			u = utilisateurDao.findById((long) request.getSession().getAttribute("user_id"));
		}catch (NullPointerException np) {
			String messageSessionExpiree = "Nous avons rencontré un problème lors de l'accès aux données";
			model.addAttribute("messageSessionExpiree", messageSessionExpiree);
			return "login";
		}

		if (societeId != 0) { // c'est une modification de société
			Societe s = societeDao.findById(societeId);

			model.addAttribute("promotions", s.getPromotions());
			model.addAttribute("societe", s);
		}
		if (u.isAdmin()) {
			return "admin/mesPromotions";
		}

		return "artisan/mesPromotions";
	}

	@RequestMapping(value = { "/admin/promotion/lister", "/artisan/promotion/lister" }, method = RequestMethod.GET)
	public String listerPromotions(@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "id", required = true) Long societeId,
			@RequestParam(name = "max", required = false) Integer max, Model model) {

		if (max == null || max == 0)
			max = 15;
		if (page == null || page == 0)
			page = 1;

		int start = (page - 1) * max;

		Societe s = societeDao.findById(societeId);
		List<Promotion> promotions = s.getPromotions();

		model.addAttribute("promotions", promotions);

		return "admin/categories";
	}

	@RequestMapping(value = { "/admin/promotion/creation-promotion", "/artisan/promotion/creation-promotion" })
	public ModelAndView creerPromotion(HttpServletRequest request,
			@RequestParam(name = "id", required = true) Long societeId) {
		Map<String, Object> model = new HashMap<>();

		Societe societe = societeDao.findById(societeId);
		PromotionForm form = new PromotionForm();
		form.setSocieteId(societeId);
		model.put("societe", societe);
		model.put("promotionForm", form);

		return new ModelAndView("artisan/creerPromotion", model);
	}

	// Sauvegarde d'une nouvelle promotion
	@RequestMapping(value = { "/admin/promotion/sauvegarder-nouvelle-promotion",
			"/artisan/promotion/sauvegarder-nouvelle-promotion" }, method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ModelAndView sauvegarderNouvelleCategorie(HttpServletRequest request,
			@Valid @ModelAttribute("promotionForm") PromotionForm form, BindingResult result)
			throws java.text.ParseException {
		Map<String, Object> model = new HashMap<>();

		Utilisateur u = utilisateurDao.findById((long) request.getSession().getAttribute("user_id"));

		if (result.hasErrors()) {
			model.put("errors", result);
			model.put("promotionForm", form);

			if (u.isAdmin()) {
				return new ModelAndView("admin/creationPromotion", model);
			} else if (u.isArtisan()) {
				return new ModelAndView("artisan/creationPromotion", model);
			}
		}

		Promotion promo = new Promotion();

		promo.setDescription(form.getDescription());
		promo.setDateDebut(DateUtils.stringToSqlDate(form.getDateDebut()));
		if (form.getDateFin() != null) {
			promo.setDateFin(DateUtils.stringToSqlDate(form.getDateFin()));
		}
		if (form.getRemise() > 0) {
			promo.setRemise(form.getRemise());
		}
		if (form.getTauxReduction() > 0) {
			promo.setTauxReduction(form.getTauxReduction());
		}

		Societe s = societeDao.findById(form.getSocieteId());
		promo.setSociete(s);

		promotionDao.save(promo);
		s.getPromotions().add(promo);
		societeDao.update(s);

		if (u.isAdmin()) {
			return new ModelAndView("redirect:/admin/promotion/lister?id=" + s.getId());
		} else if (u.isArtisan()) {
			return new ModelAndView("redirect:/artisan/ma-societe?id=" + s.getId());
		}
		return new ModelAndView("redirect:/login");

	}

	// modifier une promotion
	@RequestMapping(value = { "/admin/promotion/modifier-promotion", "/artisan/promotion/modifier-promotion" })
	public ModelAndView modifierCategorie(HttpServletRequest request,
			@RequestParam(name = "id", required = true) long id) {
		Map<String, Object> model = new HashMap<>();

		Utilisateur u = new Utilisateur();
		;
		if (request.getSession().getAttribute("user_id") != null) {
			u = utilisateurDao.findById((long) request.getSession().getAttribute("user_id"));
		}

		PromotionForm form = new PromotionForm();
		form.setPromotionId(id);

		Promotion promo = new Promotion();
		promo = promotionDao.findById(id);

		form.setDateDebut(DateUtils.stringSqlToLocalDate_FR(promo.getDateDebut().toString()));
		form.setDateFin(DateUtils.stringSqlToLocalDate_FR(promo.getDateFin().toString()));
		form.setDescription(promo.getDescription());
		form.setPromotionId(promo.getId());
		form.setRemise(promo.getRemise());
		form.setTauxReduction(promo.getTauxReduction());

		model.put("promotion", promo);
		model.put("promotionForm", form);

		if (u.isAdmin()) {
			return new ModelAndView("admin/modificationPromotion", model);
		}
		return new ModelAndView("artisan/modificationPromotion", model);
	}

	// Sauvegarde d'une promotion modifiée
	@RequestMapping(value = { "/admin/promotion/sauvegarder-promotion",
			"/artisan/promotion/sauvegarder-promotion" }, method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ModelAndView sauvegarderPromotion(HttpServletRequest request,
			@Valid @ModelAttribute("promotionForm") PromotionForm form, BindingResult result)
			throws java.text.ParseException {
		Map<String, Object> model = new HashMap<>();

		Utilisateur u = new Utilisateur();
		if (request.getSession().getAttribute("user_id") != null) {
			utilisateurDao.findById((long) request.getSession().getAttribute("user_id"));
		}

		if (result.hasErrors()) {
			model.put("errors", result);
			model.put("promotionForm", form);

			if (u.isAdmin()) {
				return new ModelAndView("admin/modificationPromotion", model);
			}
			return new ModelAndView("artisan/modificationPromotion", model);
		}

		System.out.println("description = " + form.getDescription());
		Promotion promo = new Promotion();
		promo = promotionDao.findById(form.getPromotionId());

		promo.setDescription(form.getDescription());
		promo.setDateDebut(DateUtils.stringToSqlDate(form.getDateDebut()));
		if (form.getDateFin() != null) {
			promo.setDateFin(DateUtils.stringToSqlDate(form.getDateFin()));
		}
		if (form.getRemise() > 0) {
			promo.setRemise(form.getRemise());
		}
		if (form.getTauxReduction() > 0) {
			promo.setTauxReduction(form.getTauxReduction());
		}

		promotionDao.update(promo);

		if (u.isAdmin()) {
			return new ModelAndView("redirect:/admin/promotion/lister?id="+promo.getId());
		}
		return new ModelAndView("redirect:/artisan/ma-societe?id=" + promo.getSociete().getId());

	}

	@RequestMapping(value = "/admin/supprimer-promotion", method = RequestMethod.GET)
	public String deleteCategorie(@RequestParam(name = "id", required = false) long id, Model model) {
		try {
			promotionDao.delete(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return "redirect:/admin/promotion/lister";
	}

}
