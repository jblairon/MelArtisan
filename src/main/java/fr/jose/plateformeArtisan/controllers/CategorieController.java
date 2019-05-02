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
import fr.jose.plateformeArtisan.dao.CategorieDao;
import fr.jose.plateformeArtisan.dao.MetierDao;
import fr.jose.plateformeArtisan.formbeans.CreationCategorieForm;
import fr.jose.plateformeArtisan.tools.FormatImageName;

@Controller
public class CategorieController {

	@Autowired
	private CategorieDao categorieDao;

	@Autowired(required = true)
	private MetierDao metierDao;


	@RequestMapping(value = {"/admin/categorie/lister"}, method = RequestMethod.GET)
	public String listerCategories(@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "max", required = false) Integer max, Model model) {

		if (max == null || max == 0)
			max = 15;
		if (page == null || page == 0)
			page = 1;

		int start = (page - 1) * max;
		List<Categorie> categories = categorieDao.findAll();

		long nb = categorieDao.nbCategories();
		boolean suivExist = (page * max) < nb;

		model.addAttribute("categories", categories);
		model.addAttribute("suivExist", suivExist);
		model.addAttribute("page", page);

		return "admin/categories";
	}

	@RequestMapping(value = "/admin/categorie/creation-categorie")
	public ModelAndView creerCategorie(HttpServletRequest request) {
		Map<String, Object> model = new HashMap<>();

		CreationCategorieForm form = new CreationCategorieForm();
		model.put("categorieForm", form);

		return new ModelAndView("admin/creationCategorie", model);
	}

	// Sauvegarde d'une nouvelle catégorie
	@RequestMapping(value = "/admin/categorie/sauvegarder-nouvelle-categorie", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ModelAndView sauvegarderNouvelleCategorie(HttpServletRequest request,
			@Valid @ModelAttribute("categorieForm") CreationCategorieForm form, BindingResult result)
			throws java.text.ParseException {
		Map<String, Object> model = new HashMap<>();

		if (result.hasErrors()) {
			model.put("errors", result);
			model.put("categorieForm", form);

			return new ModelAndView("admin/creationCategorie", model);
		}

		Categorie cat = new Categorie();
		
		cat.setLibelle(form.getCategorie());
		
		String image = FormatImageName.removeAccents(cat.getLibelle().toLowerCase().replaceAll("\\s", ""));
		image = image.replaceAll("'", "");
		cat.setImage(image);
		
		categorieDao.save(cat);

		// Récupération des nouveaux métiers de cette categorie
		String[] metiersForm = form.getMetiers();
		List<Metier> lm = new ArrayList<>();

		// Création des objets Metier
		for (int i = 0; i < metiersForm.length; i++) {
			Metier m = new Metier();
			m.setLibelle(metiersForm[i]);
			m.setCategorie(cat);
			lm.add(m);
			metierDao.save(m);
		}

		cat.setMetiers(lm);
		categorieDao.update(cat);

		return new ModelAndView("redirect:/admin/categorie/lister");

	}

	// modifier une catégorie
	@RequestMapping(value = "/admin/categorie/modifier-categorie")
	public ModelAndView modifierCategorie(HttpServletRequest request,
			@RequestParam(name = "id", required = true) long id) {
		Map<String, Object> model = new HashMap<>();

		CreationCategorieForm form = new CreationCategorieForm();
		form.setCategorieId(id);

		Categorie cat = new Categorie();
		cat = categorieDao.findById(id);
		form.setCategorie(cat.getLibelle());
		form.setCategorieId(id);
		
		List<Metier> m = cat.getMetiers();
		long metiersIds[] = new long[cat.getMetiers().size()];
		int i = 0;
		for (Metier metier : m) {
			metiersIds[i] = metier.getId();
			i++;
		}
		form.setMetiersId(metiersIds);

		model.put("categorie", cat);
		model.put("categorieForm", form);

		return new ModelAndView("admin/modificationCategorie", model);
	}

	// Sauvegarde d'une nouvelle catégorie
	@RequestMapping(value = "/admin/categorie/sauvegarder-categorie", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ModelAndView sauvegarderCategorie(HttpServletRequest request,
			@Valid @ModelAttribute("categorieForm") CreationCategorieForm form, BindingResult result)
			throws java.text.ParseException {
		Map<String, Object> model = new HashMap<>();

		if (result.hasErrors()) {
			model.put("errors", result);
			model.put("categorieForm", form);

			return new ModelAndView("admin/creationCategorie", model);
		}

		Categorie cat = new Categorie();
		cat = categorieDao.findById(form.getCategorieId());

		cat.setLibelle(form.getCategorie());
		List<Metier> lm = new ArrayList<>();

		// Récupération des métiers de cette categorie
		if (form.getMetiersId() != null) {
			long[] metiersForm = form.getMetiersId();
			System.out.println("metiersId = " + form.getMetiersId().length);
			
			// Création des objets Metier
			for (int i = 0; i < metiersForm.length; i++) {
				Metier m = new Metier();
				m = metierDao.findById(metiersForm[i]);
				lm.add(m);
			}
			System.out.println("lm = " + lm.size());
		}

		// Récupération des nouveaux métiers créés pour cette catégorie
		if (form.getNouveauxMetiers() != null) {
			String[] nouveauxMetiersForm = form.getNouveauxMetiers();

			// Création des objets nouveaux Metier
			for (int i = 0; i < nouveauxMetiersForm.length; i++) {
				Metier m = new Metier();
				m.setLibelle(nouveauxMetiersForm[i]);
				m.setCategorie(cat);
				metierDao.save(m);
				lm.add(m);
			}
		}

		cat.setMetiers(lm);
		categorieDao.update(cat);

		return new ModelAndView("redirect:/admin/categorie/lister");

	}
	
	@RequestMapping(value = "/admin/supprimer-categorie", method = RequestMethod.GET)
	public String deleteCategorie(@RequestParam(name = "id", required = false) long id, Model model) {
		try {
			categorieDao.delete(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return "redirect:/admin/categorie/lister";
	}

}
