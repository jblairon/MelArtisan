package fr.jose.plateformeArtisan.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import fr.jose.plateformeArtisan.beans.Adresse;
import fr.jose.plateformeArtisan.beans.Categorie;
import fr.jose.plateformeArtisan.beans.Contact;
import fr.jose.plateformeArtisan.beans.Metier;
import fr.jose.plateformeArtisan.beans.Prestation;
import fr.jose.plateformeArtisan.beans.Societe;
import fr.jose.plateformeArtisan.beans.Utilisateur;
import fr.jose.plateformeArtisan.dao.AdresseDao;
import fr.jose.plateformeArtisan.dao.CategorieDao;
import fr.jose.plateformeArtisan.dao.ContactDao;
import fr.jose.plateformeArtisan.dao.MetierDao;
import fr.jose.plateformeArtisan.dao.PrestationDao;
import fr.jose.plateformeArtisan.dao.SocieteDao;
import fr.jose.plateformeArtisan.dao.UtilisateurDao;
import fr.jose.plateformeArtisan.formbeans.AjouterSocieteForm;
import fr.jose.plateformeArtisan.formbeans.ChoixCategorieForm;
import fr.jose.plateformeArtisan.formbeans.ChoixMetiersForm;
import fr.jose.plateformeArtisan.formbeans.EditUtilisateurForm;

@Controller
public class AdminController {

	@Autowired
	private UtilisateurDao utilisateurDao;

	@Autowired(required = true)
	private SocieteDao societeDao;

	@Autowired(required = true)
	private AdresseDao adresseDao;

	@Autowired(required = true)
	private CategorieDao categorieDao;

	@Autowired(required = true)
	private MetierDao metierDao;

	@Autowired(required = true)
	private PrestationDao prestationDao;

	@Autowired(required = true)
	private ContactDao contactDao;

	public void setUtilisateurDao(UtilisateurDao utilisateurDao) {
		this.utilisateurDao = utilisateurDao;
	}

	@RequestMapping(value = "/admin/dashboard", method = RequestMethod.GET)
	public String showAdminDashboard() {
		return "admin/dashboard";
	}

	@RequestMapping(value = "/admin/liste-utilisateurs", method = RequestMethod.GET)
	public String listerUtilisateurs(@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "max", required = false) Integer max, Model model) {
		if (max == null || max == 0)
			max = 15;
		if (page == null || page == 0)
			page = 1;

		int start = (page - 1) * max;
		List<Utilisateur> utilisateurs = utilisateurDao.findAll(start, max);

		long nb = utilisateurDao.nbUtilisateurs();
		boolean suivExist = (page * max) < nb;

		model.addAttribute("utilisateurs", utilisateurs);
		model.addAttribute("suivExist", suivExist);
		model.addAttribute("page", page);

		return "admin/utilisateurs";
	}

	@RequestMapping(value = "/admin/disconnect", method = RequestMethod.GET)
	public String disconnect(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/";
	}

	@RequestMapping(value = "/admin/supprimer-utilisateur", method = RequestMethod.GET)
	public String deleteUtilisateur(@RequestParam(name = "id", required = false) long id, Model model) {
		try {
			utilisateurDao.delete(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "redirect:/admin/liste-utilisateurs";
	}

	// Préparation du model et redirection vers le formulaire de modification
	@RequestMapping(value = "/admin/modifier-utilisateur", method = RequestMethod.GET)
	public ModelAndView modifierUtilisateur(@RequestParam(name = "id", required = false) long id) {
		Map<String, Object> model = new HashMap<>();

		Utilisateur utilisateur = utilisateurDao.findById(id);

		String dateCreationStr = new SimpleDateFormat("dd/MM/yyyy").format(utilisateur.getDateCreation());

		// Récupération du jour, mois et année pour le formulaire
		String[] dateSplit = dateCreationStr.split("/");
		int jour = Integer.parseInt(dateSplit[0]);
		int mois = Integer.parseInt(dateSplit[1]);
		int annee = Integer.parseInt(dateSplit[2]);

		EditUtilisateurForm form = new EditUtilisateurForm(utilisateur.getGenre(), utilisateur.getPrenom(),
				utilisateur.getNom(), utilisateur.getAdresse().getNumero(), utilisateur.getAdresse().getVoie(),
				utilisateur.getAdresse().getCodePostal(), utilisateur.getAdresse().getVille(), utilisateur.getEmail(),
				utilisateur.getMdp(), utilisateur.isAdmin(), utilisateur.isClient(), utilisateur.getSocietes(), jour,
				mois, annee);

		form.setId(utilisateur.getId());
		model.put("edit-utilisateur-form", form);
		model.put("utilisateur_prenom", utilisateur.getPrenom());
		model.put("utilisateur_nom", utilisateur.getNom());

		return new ModelAndView("admin/editutilisateur", model);
	}

	// Controle des éléments issus du formulaire
	@RequestMapping(value = "/admin/save-utilisateur", method = RequestMethod.POST)
	public ModelAndView updateUtilisateur(HttpServletRequest request,
			@Valid @ModelAttribute("edit-utilisateur-form") EditUtilisateurForm form, BindingResult result)
			throws java.text.ParseException {
		Map<String, Object> model = new HashMap<String, Object>();

		if (result.hasErrors()) {
			model.put("errors", result);
			model.put("edit-utilisateur-form", form);

			if (result.getFieldError().getField().toString().equals("email")) {
				model.put("msg", "Errreur : le format de l'adresse email n'est pas correct !");
			} else {
				model.put("msg", "Errreur : au moins un des champs n'a pas été correctement rempli !");
			}
			return new ModelAndView("admin/editutilisateur", model);
		}

		// enregistrement des données dans la BDD
		// long id =
		Utilisateur utilisateur = utilisateurDao.findById(form.getId());
		utilisateur.setPrenom(form.getPrenom());
		utilisateur.setNom(form.getNom());

		utilisateur.getAdresse().setCodePostal(form.getCodePostal());
		utilisateur.getAdresse().setNumero(form.getNumero());
		utilisateur.getAdresse().setVille(form.getVille());
		utilisateur.getAdresse().setVoie(form.getVoie());

		utilisateur.setAdmin(form.isAdmin());
		utilisateur.setClient(form.isClient());
		utilisateur.setEmail(form.getEmail());
		utilisateur.setMdp(form.getMdp());
		utilisateur.setSocietes(form.getSocietes());

		String msg = null;
		try {
			// sauvegarde des modifs en Bdd
			utilisateurDao.update(utilisateur);

		} catch (ParseException e) {
			e.printStackTrace();
			msg = "Cette adresse mail est déjà utilisée";
			model.put("msg", msg);
			model.put("errors", result);
			model.put("utilisateur-form", form);
			return new ModelAndView("admin/editutilisateur", model);
		}

		utilisateurDao.update(utilisateur);

		return new ModelAndView("admin/dashboard", model);

	}



	

	



	

}
