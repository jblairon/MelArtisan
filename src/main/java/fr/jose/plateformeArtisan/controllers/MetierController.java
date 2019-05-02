package fr.jose.plateformeArtisan.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.jose.plateformeArtisan.beans.Categorie;
import fr.jose.plateformeArtisan.beans.Metier;
import fr.jose.plateformeArtisan.beans.Prestation;
import fr.jose.plateformeArtisan.dao.CategorieDao;
import fr.jose.plateformeArtisan.dao.MetierDao;
import fr.jose.plateformeArtisan.dao.PrestationDao;
import fr.jose.plateformeArtisan.formbeans.CreationMetierForm;

@Controller
public class MetierController {

	@Autowired(required = true)
	private MetierDao metierDao;
		
	@Autowired(required = true)
	private CategorieDao categorieDao;
	
	@Autowired(required = true)
	private PrestationDao prestationDao;
	
	@RequestMapping(value = "/admin/metier/lister", method = RequestMethod.GET)
	public String listerMetiers(@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "max", required = false) Integer max, Model model) {

		if (max == null || max == 0)
			max = 15;
		if (page == null || page == 0)
			page = 1;

		int start = (page - 1) * max;
		List<Metier> metiers = metierDao.findAll();

		long nb = metierDao.nbMetiers();
		boolean suivExist = (page * max) < nb;

		model.addAttribute("metiers", metiers);
		model.addAttribute("suivExist", suivExist);
		model.addAttribute("page", page);

		return "admin/metiers";
	}
	
	@RequestMapping(value = "/admin/metier/creation-metier")
	public ModelAndView creerMetier(HttpServletRequest request) {
		Map<String, Object> model = new HashMap<>();

		CreationMetierForm form = new CreationMetierForm();
		List<Categorie> categories = new ArrayList<>();
		categories = categorieDao.findAll();

		
		model.put("categories", categories);
		model.put("metierForm", form);

		return new ModelAndView("admin/creationMetier", model);
	}
	
	// Sauvegarde d'un nouveau métier
	@RequestMapping(value = "/admin/metier/sauvegarder-nouveau-metier", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ModelAndView sauvegarderNouveauMetier(HttpServletRequest request,
			@Valid @ModelAttribute("metierForm") CreationMetierForm form, BindingResult result)
			throws java.text.ParseException {
		Map<String, Object> model = new HashMap<>();

		if (result.hasErrors()) {
			model.put("errors", result);
			model.put("metierForm", form);

			return new ModelAndView("admin/creationMetier", model);
		}
		
		

		Metier metier = new Metier();
		metier.setLibelle(form.getMetier());
		
		Categorie cat = categorieDao.findById(form.getCategorieId());
		metier.setCategorie(cat);
		metierDao.save(metier);


		// Récupération des nouvelles prestations de ce metier
		String[] prestationsForm = form.getPrestations();
		List<Prestation> lp = new ArrayList<>();
		Metier m = metierDao.findByLibelle(metier.getLibelle());
		System.out.println("prestationsForm = " + prestationsForm.length);

		// Création des objets Metier
		for (int i = 0; i < prestationsForm.length; i++) {
			Prestation p = new Prestation();
			p.setLibelle(prestationsForm[i]);
			p.setMetier(m);
			prestationDao.save(p);
		}

		return new ModelAndView("redirect:/admin/metier/lister");

	}
}
