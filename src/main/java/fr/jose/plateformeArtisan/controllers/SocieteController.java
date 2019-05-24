package fr.jose.plateformeArtisan.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import fr.jose.plateformeArtisan.beans.Adresse;
import fr.jose.plateformeArtisan.beans.Categorie;
import fr.jose.plateformeArtisan.beans.Contact;
import fr.jose.plateformeArtisan.beans.Image;
import fr.jose.plateformeArtisan.beans.Metier;
import fr.jose.plateformeArtisan.beans.Note;
import fr.jose.plateformeArtisan.beans.Prestation;
import fr.jose.plateformeArtisan.beans.Societe;
import fr.jose.plateformeArtisan.dao.AdresseDao;
import fr.jose.plateformeArtisan.dao.CategorieDao;
import fr.jose.plateformeArtisan.dao.ContactDao;
import fr.jose.plateformeArtisan.dao.ImageDao;
import fr.jose.plateformeArtisan.dao.MetierDao;
import fr.jose.plateformeArtisan.dao.NoteDao;
import fr.jose.plateformeArtisan.dao.PrestationDao;
import fr.jose.plateformeArtisan.dao.SocieteDao;
import fr.jose.plateformeArtisan.formbeans.AjouterSocieteForm;
import fr.jose.plateformeArtisan.formbeans.ChoixCategorieForm;
import fr.jose.plateformeArtisan.formbeans.ChoixMetiersForm;
import fr.jose.plateformeArtisan.formbeans.FiltreMetiersVilleForm;
import fr.jose.plateformeArtisan.tools.FormatImageName;
import fr.jose.plateformeArtisans.services.SocieteServices;

@Controller
public class SocieteController {

	@Autowired
	private CategorieDao categorieDao;

	@Autowired(required = true)
	private SocieteDao societeDao;

	@Autowired(required = true)
	private NoteDao noteDao;

	@Autowired(required = true)
	private AdresseDao adresseDao;

	@Autowired(required = true)
	private MetierDao metierDao;

	@Autowired(required = true)
	private PrestationDao prestationDao;

	@Autowired(required = true)
	private ContactDao contactDao;

	@Autowired(required = true)
	private ImageDao imageDao;

	// Liste les sociétés par catégorie de métiers
	@RequestMapping(value = { "/societe/listeParCategorie",
			"/client/societe/listeParCategorie" }, method = RequestMethod.GET)
	public ModelAndView listerSocietesParCategorie(HttpServletRequest request,
			@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "max", required = false) Integer max,
			@RequestParam(name = "categorieId", required = true) long categorieId) throws Exception {
		Map<String, Object> model = new HashMap<>();

		if (max == null || max == 0)
			max = 15;
		if (page == null || page == 0)
			page = 1;


		FiltreMetiersVilleForm form = new FiltreMetiersVilleForm();
		model.put("filtreMetiersVilleForm", form);

		int start = (page - 1) * max;
		List<Societe> societes = societeDao.findByCategorie(start, max, categorieId);
		Categorie cat = new Categorie();
		
		try {
			cat = categorieDao.findById(categorieId);
		} catch (Exception e) {
			e.printStackTrace();
			model.put("msg","Erreur de connexion à la base de données pour la raison suivante : ");
			model.put("msgErreur", e.getMessage());
			if(request.getSession().getAttribute("user_id") == null) {
				return new ModelAndView("pageErreurs", model);
			}
			else {
				return new ModelAndView("client/pageErreurs", model);
			}
			
		}

		long nb = societeDao.nbSocietes();
		boolean suivExist = (page * max) < nb;

		List<String> villes= SocieteServices.listeDesVilles(societes);
		Set<Metier> setMetiers;

		try {
			setMetiers = new HashSet<>(cat.getMetiers());
		} catch (Exception e) {
			e.printStackTrace();
			model.put("msg","La catétorie sélectionnée est introuvable");
			model.put("msgErreur", e.getMessage());
			if(request.getSession().getAttribute("user_id") == null) {
				return new ModelAndView("pageErreurs", model);
			}
			else {
				return new ModelAndView("client/pageErreurs", model);
			}
			
		}
			
		List<Metier> metiers = new ArrayList<>(setMetiers);
		List<Categorie> categories = categorieDao.findAll();

		model.put("categories", categories);
		model.put("villes", villes);
		model.put("metiers", metiers);
		model.put("societes", societes);
		model.put("categorie", cat);
		model.put("suivExist", suivExist);
		model.put("page", page);

		if (request.getSession().getAttribute("user_id") != null) {
			return new ModelAndView("client/listeParCategorie", model);
		}

		return new ModelAndView("listeParCategorie", model);
	}

	// Filtre les sociétés par métier
	@RequestMapping(value = "societe/filtrer-par-metiers-ville", method = RequestMethod.POST)
	public ModelAndView filtrerParMetiers(HttpServletRequest request,
			@Valid @ModelAttribute("choixMetiersForm") ChoixMetiersForm form, BindingResult result)
			throws java.text.ParseException {
		Map<String, Object> model = new HashMap<>();

		if (result.hasErrors()) {
			model.put("errors", result);
			model.put("choixCategorieForm", form);

			return new ModelAndView("listeParCategorie", model);
		}

		Categorie cat = categorieDao.findById(form.getCategorieId());
		Set<Metier> setMetiers = new HashSet<>(cat.getMetiers());
		List<Metier> catMetiers = new ArrayList<>(setMetiers);

		if (form.getMetiersId().length == 0) {
			System.out.println("=0");

			int start = 0, max = 0;
			List<Societe> societes = societeDao.findByCategorie(start, max, form.getCategorieId());

			model.put("societes", societes);
			model.put("metiers", catMetiers);
			model.put("categorie", cat);

			return new ModelAndView("listeParCategorie", model);
		}

		long[] idMetiers = form.getMetiersId();
		List<Metier> metiers = new ArrayList<>();
		List<Societe> alSocietes = new ArrayList<>();
		for (int i = 0; i < idMetiers.length; i++) {
			Metier m = metierDao.findById(idMetiers[i]);
			metiers.add(m);
		}

		for (Metier metier : metiers) {
			for (Societe societe : metier.getSocietes()) {
				Societe s = societeDao.findById(societe.getId());
				alSocietes.add(s);
			}
		}

		model.put("societes", alSocietes);
		model.put("metiers", catMetiers);
		model.put("categorie", cat);

		return new ModelAndView("listeParCategorie", model);
	}

	// pour l'admin affiche un tableau des sociétés
	@RequestMapping(value = "/admin/societe/lister", method = RequestMethod.GET)
	public String listerSocietes(HttpServletRequest request ,@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "max", required = false) Integer max, Model model) throws Exception{

		if (max == null || max == 0)
			max = 15;
		if (page == null || page == 0)
			page = 1;

		int start = (page - 1) * max;
		List<Societe> societes;
		try {
			societes = societeDao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg","Erreur de connexion à la base de données");
			if(request.getSession().getAttribute("user_id") == null) {
				return "pageErreurs";
			}
			else {
				return "client/pageErreurs";
			}
			
		}

		long nb = societeDao.nbSocietes();
		boolean suivExist = (page * max) < nb;

		model.addAttribute("societes", societes);
		model.addAttribute("suivExist", suivExist);
		model.addAttribute("page", page);

		return "admin/societes";
	}

	/*
	 * Méthodes pour la création ou la modification d'une société
	 */

	@Transactional
	@RequestMapping(value = "/admin/societe/choixCategorie", method = RequestMethod.GET)
	public ModelAndView choisirCategorie(HttpServletRequest request,
			@RequestParam(name = "id", required = true) long societeId) {
		Map<String, Object> model = new HashMap<>();

		// Récupération des id des catégories
		List<Categorie> categories = categorieDao.findAll();

		// récupération de l'objet ChoixCategorieForm
		ChoixCategorieForm form = new ChoixCategorieForm();


		// Si l'id de la société est différent de 0 donc il s'agit d'une modification de
		// société
		if (societeId != 0) { // c'est une modification de société
			Societe s = societeDao.findById(societeId);
			long id = s.getCategorie().getId();
			form.setCategorieId(id);
			form.setCategorie(s.getCategorie());
			form.setSocieteId(societeId);
			model.put("societe", s);
			request.getSession().setAttribute("societe", s);
		}

		model.put("categories", categories);
		model.put("choixCategorieForm", form);

		return new ModelAndView("admin/creationSociete/choixCategorie", model);
	}

	// Validation de la catégorie
	@RequestMapping(value = "admin/societe/validerCategorie", method = RequestMethod.POST)
	public ModelAndView validerCategorie(HttpServletRequest request,
			@Valid @ModelAttribute("choixCategorieForm") ChoixCategorieForm form, BindingResult result)
			throws java.text.ParseException {
		Map<String, Object> model = new HashMap<>();

		if (result.hasErrors()) {
			model.put("errors", result);
			model.put("choixCategorieForm", form);

			return new ModelAndView("admin/creationSociete/choixCategorie", model);
		}

		// Vérification si nouvelle catégorie é créer
		if (form.getNouvelleCategorie() != null && !(form.getNouvelleCategorie().isEmpty())
				&& form.getNouvelleCategorie() != "") {
			Categorie nouvelleCategorie = new Categorie();
			nouvelleCategorie.setLibelle(form.getNouvelleCategorie());
			
			//Création du nom de l'image avec le libellé de la catétorie 
			nouvelleCategorie.setImage(
					FormatImageName.removeAccents(nouvelleCategorie.getLibelle().toLowerCase().replaceAll("\\s", "")));

			categorieDao.save(nouvelleCategorie);
			form.setNouvelleCategorie(null);

			return new ModelAndView("redirect:/admin/societe/choixCategorie?id=" + form.getSocieteId(), model);
		}

		// Cargement des métiers suivant la catégorie sélectionnée
		Categorie cat = categorieDao.findById(form.getCategorieId());

		List<Metier> metiers = metierDao.findByCategorie(cat.getId());

		ChoixMetiersForm choixMetiersForm = new ChoixMetiersForm();
		choixMetiersForm.setCategorieId(cat.getId());

		// S'il s'agit d'une modification de société
		if (request.getSession().getAttribute("societe") != null) {
			Societe s = (Societe) request.getSession().getAttribute("societe");
			if (s.getCategorie().getId() == form.getCategorieId()) {
				long tabMetiersId[] = new long[10000];
				int i = 0;
				long id = s.getId();

				// élimination des doublons de métiers
				Set<Metier> setMetiers = new HashSet<Metier>(s.getMetiers());
				List<Metier> alMetiers = new ArrayList<Metier>(setMetiers);

				for (Metier metier2 : alMetiers) {
					tabMetiersId[i] = metier2.getId();
					i++;
				}
				choixMetiersForm.setMetiersId(tabMetiersId);
			} else {
				s.setCategorie(cat);
				societeDao.update(s);
			}
			model.put("societe", s);
			request.getSession().setAttribute("societe", s);
		}

		model.put("categorie", cat);
		model.put("metiers", metiers);
		model.put("choixMetiersForm", choixMetiersForm);

		return new ModelAndView("admin/creationSociete/choixMetiers", model);

	}

	// Validation des métiers
	@RequestMapping(value = "/admin/societe/validerMetiers", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ModelAndView validerMetiers(HttpServletRequest request,
			@Valid @ModelAttribute("choixMetiersForm") ChoixMetiersForm form, BindingResult result)
			throws java.text.ParseException {
		Map<String, Object> model = new HashMap<>();

		if (result.hasErrors() && (form.getNouveauMetier() == null || form.getNouveauMetier() == ""
				|| form.getNouveauMetier().isEmpty())) {
			model.put("errors", result);
			model.put("choixMetiersForm", form);

			return new ModelAndView("admin/creationSociete/choixMetiers", model);
		}

		Categorie cat = categorieDao.findById(form.getCategorieId());
		List<Metier> metiers = new ArrayList<>();
		String tabNouveauxMetiers[] = form.getMetiersForm();
		
		// on vérifie si un nouveau métier a été créé
		if (tabNouveauxMetiers != null && tabNouveauxMetiers.length > 0) {
			ArrayList<String> listeNouveauxMetiers = new ArrayList<>();
			// on récupére les nouveaux métiers issus du formulaire
			for (int i = 1; i < tabNouveauxMetiers.length; i++) {
				listeNouveauxMetiers.add(tabNouveauxMetiers[i]);
			}

			// on sauvegarde les nouveaux métiers et on les ajoute é la catégorie
			for (String libelle : listeNouveauxMetiers) {

				Metier metier = new Metier();
				metier.setLibelle(libelle);
				metier.setCategorie(cat);
				metierDao.save(metier);
				Metier m = metierDao.findByLibelle(metier.getLibelle());
				metiers.add(m);
				cat.getMetiers().add(m);
			}
			form.setMetiersForm(null);

		}

		// on récupére tous les métiers issus du formulaire et on les ajoute é la atégorie
		long[] tabMetiers = form.getMetiersId();

		for (int i = 0; i < tabMetiers.length; i++) {
			Metier metier = metierDao.findById(tabMetiers[i]);
			metiers.add(metier);
		}
		categorieDao.update(cat);

		request.getSession().setAttribute("metiers", metiers);

		AjouterSocieteForm ajouterSocieteForm = new AjouterSocieteForm(cat.getId(), cat, metiers);

		// S'il s'agit d'une modification de société on carge le formulaire
		if (request.getSession().getAttribute("societe") != null) {

			Societe s = (Societe) request.getSession().getAttribute("societe");
			s.setMetiers(metiers);

			ajouterSocieteForm.setCodePostal(Integer.toString(s.getAdresse().getCodePostal()));
			ajouterSocieteForm.setComplement(s.getAdresse().getComplement());
			ajouterSocieteForm.setDescription(s.getDescription());
			ajouterSocieteForm.setEmail(s.getEmail());
			ajouterSocieteForm.setEmailContact(s.getContact().getEmail());
			ajouterSocieteForm.setFonctionContact(s.getContact().getFonction());
			ajouterSocieteForm.setNom(s.getNom());
			ajouterSocieteForm.setNomContact(s.getContact().getNom());
			ajouterSocieteForm.setNumero(Integer.toString(s.getAdresse().getNumero()));
			ajouterSocieteForm.setPrenomContact(s.getContact().getPrenom());
			ajouterSocieteForm.setTel(s.getTel());
			ajouterSocieteForm.setTelContact(s.getContact().getTel());
			ajouterSocieteForm.setVille(s.getAdresse().getVille());
			ajouterSocieteForm.setVoie(s.getAdresse().getVoie());

			if (s.getImages().size() > 0) {
				ajouterSocieteForm.setImageBackground(s.getImages().get(0).getLibelle());
				ajouterSocieteForm.setImageVignette(s.getImages().get(1).getLibelle());
				ajouterSocieteForm.setImage1(s.getImages().get(2).getLibelle());
				ajouterSocieteForm.setImage2(s.getImages().get(3).getLibelle());
				ajouterSocieteForm.setImage3(s.getImages().get(4).getLibelle());
			}

			// Ajout des prestations de la société dans le form
			long[] tabPrestations = new long[10000];
			int i = 0;

			for (Prestation p : s.getPrestations()) {
				tabPrestations[i] = p.getId();
				i++;
			}
			ajouterSocieteForm.setPrestationsId(tabPrestations);
			societeDao.update(s);
			request.getSession().setAttribute("societe", s);

		}

		model.put("categorie", cat);
		model.put("categorieId", cat.getId());
		model.put("metiers", metiers);
		model.put("societe", request.getSession().getAttribute("societe"));
		model.put("ajouterSocieteForm", ajouterSocieteForm);

		return new ModelAndView("admin/creationSociete/creerSociete", model);

	}

	// Sauvegarde d'une société (création ou modification)
	@RequestMapping(value = "/societe/sauvegarderSociete", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ModelAndView sauvegarderSociete(HttpServletRequest request,
			@Valid @ModelAttribute("ajouterSocieteForm") AjouterSocieteForm form, BindingResult result)
			throws java.text.ParseException {
		Map<String, Object> model = new HashMap<>();

		if (result.hasErrors()) {
			model.put("errors", result);
			model.put("ajoutSocieterForm", form);

			return new ModelAndView("admin/creationSociete/creerSociete", model);
		}
		Societe s;

		// pour la modification d'une société
		if (request.getSession().getAttribute("societe") != null) {
			s = (Societe) request.getSession().getAttribute("societe");
		} 
		// pour la création d'une nouvelle société
		else {
			s = new Societe();
		}


		/*
		 * Gestion des prestations 1) prestations existantes en base de données
		 */
		long tabPrestations[] = form.getPrestationsId();

		// on récupére les prestaions issues du formulaire
		if (form.getPrestationsId() != null || form.getMetiersId() != null) {
			List<Prestation> prestations = new ArrayList<Prestation>();
			if (form.getPrestationsId() != null) {
				for (int i = 0; i < form.getPrestationsId().length; i++) {
					Prestation p = new Prestation();
					p = prestationDao.findById(tabPrestations[i]);
					prestations.add(p);
				}
			}

			/*
			 * 2) nouvelles prestations créées
			 */
			if (form.getMetiersId() != null) {
				long metiersId[] = form.getMetiersId();
				String nouvellesPrestations[] = null;
				String param = "prestations";
				String strMetiersId = null;
				for (int i = 0; i < metiersId.length; i++) {
					strMetiersId = String.valueOf(metiersId[i]);
					nouvellesPrestations = request.getParameterValues(param.concat(strMetiersId));
					Metier m = metierDao.findById(metiersId[i]);

					// si nouvelles prestations créées
					if (nouvellesPrestations != null) {
						for (int j = 0; j < nouvellesPrestations.length; j++) {
							Prestation p = new Prestation();
							p.setLibelle(nouvellesPrestations[j]);
							p.setMetier(m);
							prestationDao.save(p);
							prestations.add(p);
						}
						m.setPrestations(prestations);
						metierDao.update(m);
					}

				}

			}

			s.setPrestations(prestations);
		}
		if (s.getAdresse() == null) { // c'est une création de société

			Adresse a = new Adresse();
			a.setNumero(Integer.parseInt(form.getNumero()));
			a.setCodePostal(Integer.parseInt(form.getCodePostal()));
			a.setComplement(form.getComplement());

			a.setVille(form.getVille());
			a.setVoie(form.getVoie());
			adresseDao.save(a);

			s.setAdresse(a);
			// Date de création de la société en base de données
			s.setDateCreation(new Date());
		}

		else { // sinon c'est une modification de société
			s.getAdresse().setCodePostal(Integer.parseInt(form.getCodePostal()));
			s.getAdresse().setComplement(form.getComplement());
			s.getAdresse().setNumero(Integer.parseInt(form.getNumero()));
			s.getAdresse().setVille(form.getVille());
			s.getAdresse().setVoie(form.getVoie());
		}

		/**
		 * Début du traitement des images
		 */

		// si pas d'images dans la société on les crée
		List<Image> images;
		boolean saveImages = false;
		if (s.getImages().size() == 0) {
			saveImages = true;
			// Formattage du nom des images avec le nom de la société
			images = FormatImageName.formatImages(form.getNom().toLowerCase().replaceAll("\\s", ""), s.getImages());
			s.setImages(images);
		}
		// sinon on les modifie
		else {
			images = FormatImageName.formatImages(form.getNom().toLowerCase().replaceAll("\\s", ""), s.getImages());
			s.setImages(images);
		}

		// Création du nom et libellé des images
		if (request.getSession().getAttribute("societe") != null) {
			if (form.getImageBackground() != null) {
				s.getImages().get(0).setLibelle(form.getImageBackground());
			}
			if (form.getImageVignette() != null) {
				s.getImages().get(1).setLibelle(form.getImageVignette());
			}
			if (form.getImage1() != null) {
				s.getImages().get(2).setLibelle(form.getImage1());
			}
			if (form.getImage2() != null) {
				s.getImages().get(3).setLibelle(form.getImage2());
			}
			if (form.getImage3() != null) {
				s.getImages().get(4).setLibelle(form.getImage3());
			}
		}

		/**
		 * Fin du traitement des images
		 */

		Categorie cat = categorieDao.findById(form.getCategorieId());
		s.setCategorie(cat);

		s.setDescription(form.getDescription());
		s.setEmail(form.getEmail());

		s.setNom(form.getNom());
		s.setTel(form.getTel());

		// si pas encore de contact on le crée
		if (form.getPrenomContact() != null || form.getNomContact() != null || form.getTelContact() != null
				|| form.getEmailContact() != null || form.getFonctionContact() != null) {
			if (s.getContact() == null) {
				Contact contact = new Contact();

				contact.setEmail(form.getEmailContact() != null ? form.getEmailContact() : null);
				contact.setFonction(form.getFonctionContact() != null ? form.getFonctionContact() : null);
				contact.setNom(form.getNomContact() != null ? form.getNomContact() : null);
				contact.setPrenom(form.getPrenomContact() != null ? form.getPrenomContact() : null);
				contact.setTel(form.getTelContact() != null ? form.getTelContact() : null);

				contactDao.save(contact);
				s.setContact(contact);
				// sinon on le modifie
			} else {
				s.getContact().setEmail(form.getEmailContact());
				s.getContact().setFonction(form.getFonctionContact());
				s.getContact().setNom(form.getNomContact());
				s.getContact().setPrenom(form.getPrenomContact());
				s.getContact().setTel(form.getTelContact());

			}
		}
		
		// si c'est une modification on update la société
		if (request.getSession().getAttribute("societe") != null) {
			societeDao.update(s);
			// Enre'gistrement des images en base de données
			if (saveImages) {
				for (int i = 0; i < images.size(); i++) {
					System.out.println("saveImages");
					images.get(i).setSociete(s);
					imageDao.save(images.get(i));
				}
			}
			// sinon on update les images
			else {
				for (int i = 0; i < images.size(); i++) {
					System.out.println("updateImages");
					images.get(i).setSociete(s);
					imageDao.update(images.get(i));
				}
			}

		} else { // sonon on sauvegarde la société
			societeDao.save(s);
			images = new ArrayList<Image>();
			images = FormatImageName.formatImages(form.getNom().toLowerCase().replaceAll("\\s", ""), s.getImages());
			images.get(0).setLibelle(form.getImageBackground());
			images.get(1).setLibelle(form.getImageVignette());
			images.get(2).setLibelle(form.getImage1());
			images.get(3).setLibelle(form.getImage2());
			images.get(4).setLibelle(form.getImage3());

			// Enregistrement des images en base de données
			for (int i = 0; i < images.size(); i++) {
				images.get(i).setSociete(s);
				imageDao.save(images.get(i));
			}
		}

		return new ModelAndView("redirect:/admin/societe/lister");

	}

	/*
	 * Pour la modification d'une catégorie
	 */
	@RequestMapping("/admin/societe/modifierCategorie")
	public ModelAndView modifierCategorie(HttpServletRequest request,
			@RequestParam(name = "id", required = true) long id) {
		Map<String, Object> model = new HashMap<>();

		// Récupération des id des catégories
		List<Categorie> categories = categorieDao.findAll();

		// récupération de l'objet AjouterSocieteForm
		ChoixCategorieForm form = new ChoixCategorieForm();
		form.setCategorieId(id);

		request.getSession().setAttribute("categories", categories);

		model.put("categories", categories);
		model.put("choixCategorieForm", form);

		return new ModelAndView("admin/creationSociete/choixCategorie", model);
	}

	@RequestMapping(value = "/societe/lister-front", method = RequestMethod.GET)
	public String listerSocietesFront(HttpServletRequest request ,@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "max", required = false) Integer max, Model model) {

		if (max == null || max == 0)
			max = 15;
		if (page == null || page == 0)
			page = 1;

		int start = (page - 1) * max;
		List<Societe> societes;
		try {
			societes = societeDao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg","Erreur d'accès à la base de données");
			if(request.getSession().getAttribute("user_id") == null) {
				return "pageErreurs";
			}
			else {
				return "client/pageErreurs";
			}
			
		}

		long nb = societeDao.nbSocietes();
		boolean suivExist = (page * max) < nb;

		model.addAttribute("societes", societes);
		model.addAttribute("suivExist", suivExist);
		model.addAttribute("page", page);

		return "societes";
	}

	@RequestMapping(value = "/admin/supprimer-societe", method = RequestMethod.GET)
	public String deleteSociete(@RequestParam(name = "id", required = false) long id, Model model) {
		try {
			societeDao.delete(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return "redirect:/admin/societe/lister";
	}

	// Pour l'affichage du détail d'une société
	@RequestMapping(value = { "/societe/societe-detail", "/client/societe/societe-detail",
			"/artisan/ma-societe" }, method = RequestMethod.GET)
	public ModelAndView afficherDetailSociete(HttpServletRequest request,
			@RequestParam(name = "id", required = true) long id,
			@RequestParam(name = "ajouteAuxFavoris", required = false) String ajouteAuxFavoris,
			@RequestParam(name = "msgNote", required = false) String msgNote) throws Exception{
		Map<String, Object> model = new HashMap<>();
		
		List<Categorie> categories;

		try {
			categories = categorieDao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			model.put("msg","Erreur de connexion à la base de données pour la raison suivante : ");
			model.put("msgErreur", e.getMessage());
			if(request.getSession().getAttribute("user_id") == null) {
				return new ModelAndView("pageErreurs", model);
			}
			else {
				return new ModelAndView("client/pageErreurs", model);
			}
			
		}
		model.put("categories", categories);

		String messageOpenClose = null;
		String divClass = null;
		String msgFavori = null;
		String messageVacances = null;
		String divClassVacances = null;

		try {
			Societe societe = societeDao.findById(id);
			System.out.println("image promo = " + societe.getPromotions().get(0).getImage());

			// pour l'ajout dans les favoris
			if (ajouteAuxFavoris != null) {
				if (ajouteAuxFavoris.equals("oui")) {
					msgFavori = societe.getNom() + " a été ajouté à vos favoris";
				} else if (ajouteAuxFavoris.equals("non")) {
					msgFavori = societe.getNom() + " est déjà dans vos favoris";
				}

				model.put("msgFavori", msgFavori);
			}

			if (msgNote != null) {
				model.put("msgNote", msgNote);
			}

			if (societe.getHoraires().size() > 0) {

				if (societe.getHoraires().get(0).getAmOpen().equals("Fermé")
						&& societe.getHoraires().get(0).getPmOpen().equals("Fermé")) {
					messageOpenClose = "Aujourd'hui fermé toute la journée";
					divClass = "alert alert-block alert-danger div-class";
				}
			}

			if (societe.isAmCloseToDay() && societe.isPmCloseToDay()) {
				messageOpenClose = "Aujourd'hui fermé toute la journée";
				divClass = "alert alert-block alert-danger div-class";
			} else if (societe.isAmCloseToDay() && !(societe.isPmCloseToDay())) {
				messageOpenClose = "Aujourd'hui ouvert cet après-midi";
				divClass = "alert alert-block alert-danger div-class";
			} else if (!(societe.isAmCloseToDay()) && societe.isPmCloseToDay()) {
				messageOpenClose = "Aujourd'hui ouvet ce matin mais fermé cet après-midi";
				divClass = "alert alert-block alert-danger div-class";
			} else {
				messageOpenClose = "Aujourd'hui ouvert toute la journée";
				divClass = "alert alert-block alert-success div-class";
			}

			// pour le message des prochaines vacances
			if (societe.getSocietedatevacances() != null) {
				messageVacances = SocieteServices.messageProchainesVacances(societe.getSocietedatevacances());
				model.put("messageVacances", messageVacances);
				if (messageVacances.contains("!!!")) {
					divClassVacances = "alert alert-warning";
				} else if (messageVacances.contains("Nous sommes fermés")) {
					divClassVacances = "alert alert-danger";
					messageOpenClose = null;
					divClass = "div-class-idsplay-none";
				}
				model.put("divClassVacances", divClassVacances);
			}

			Double moyenne = SocieteServices.calculerNoteMoyenne(societe.getNotes());
			model.put("moyenne", moyenne);

			model.put("divClass", divClass);
			model.put("messageOpenClose", messageOpenClose);
			model.put("societe", societe);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (request.getSession().getAttribute("user_id") != null
				&& (boolean) request.getSession().getAttribute("user_client")) {
			System.out.println("je suis client");
			return new ModelAndView("client/detailSociete", model);
		}

		if (request.getSession().getAttribute("user_id") != null
				&& (boolean) request.getSession().getAttribute("user_artisan")) {
			System.out.println("je suis artisan");
			return new ModelAndView("artisan/detailSociete", model);
		}

		return new ModelAndView("detailSociete", model);
	}

	@RequestMapping(value = { "/client/societe/societe-voter" }, method = RequestMethod.GET)
	public String sauvegarderNote(HttpServletRequest request, @RequestParam(name = "id", required = true) long id,
			@RequestParam(name = "note", required = true) int note) {
		Map<String, Object> model = new HashMap<>();

		if ((boolean) request.getSession().getAttribute("user_artisan")) {
			String msgNote = "Vous ne pouvez pas voter pour vous-même !!!";
			return ("redirect:/artisan/societe/societe-detail?id=" + id + "&msgNote=" + msgNote);
		}

		Note n = new Note();
		n.setDate(new Date());
		n.setNote(note);

		Societe s = societeDao.findById(id);
		n.setSociete(s);
		noteDao.save(n);
		s.getNotes().add(n);
		societeDao.update(s);

		return "redirect:/client/societe/societe-detail?id=" + id;
	}

}
