package fr.jose.plateformeArtisan.controllers;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

//import org.eclipse.jdt.internal.compiler.ast.ForeachStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.jose.plateformeArtisan.beans.Societe;
import fr.jose.plateformeArtisan.beans.Utilisateur;
import fr.jose.plateformeArtisan.dao.SocieteDao;
import fr.jose.plateformeArtisan.dao.UtilisateurDao;
import fr.jose.plateformeArtisan.formbeans.ContactForm;
import fr.jose.plateformeArtisan.formbeans.EditUtilisateurForm;
import fr.jose.plateformeArtisan.tools.EmailTools;

@Controller
public class ClientController {

	@Autowired
	private UtilisateurDao utilisateurDao;
	
	@Autowired
	private SocieteDao societeDao;

	public void setUtilisateurDao(UtilisateurDao utilisateurDao) {
		this.utilisateurDao = utilisateurDao;
	}

	@RequestMapping(value = "/client/account", method = RequestMethod.GET)
	public String showAccount(HttpServletRequest request, Model model) {

		Long utilisateur_id = (Long) request.getSession().getAttribute("utilisateur_id");
		Utilisateur u = utilisateurDao.findById(utilisateur_id);

		return "client/account";
	}

	@RequestMapping(value = {"/client/disconnect", "/artisan/disconnect"}, method = RequestMethod.GET)
	public String disconnect(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/";
	}

	// Affichage du formulaire des coordonnées rempli
	@RequestMapping("/client/coordonnees-utilisateur")
	public String displayUtilisateurDetails(@RequestParam("id") long id, Model model) {
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

		model.addAttribute("utilisateur-form", form);

		return "client/details";
	}

//Sauevgarde des modifications sur les coordonnées
	@RequestMapping(value = "/client/sauvegarde-coordonnees", method = RequestMethod.POST)
	public String saveUtilisateurDetails(HttpServletRequest request,
			@Valid @ModelAttribute("utilisateur-form") EditUtilisateurForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			if (result.getFieldError().getField().toString().equals("email")) {
				model.addAttribute("msg", "Errreur : le format de l'adresse email n'est pas correct !");
			} else {
				model.addAttribute("msg", "Errreur : au moins un des champs n'a pas été correctement rempli !");
			}
			model.addAttribute("errors", result);

			model.addAttribute("utilisateur-form", form);
			return "client/details";
		}

		long id = (Long) request.getSession().getAttribute("utilisateur_id");
		Utilisateur utilisateur = utilisateurDao.findById(id);
		utilisateur.setGenre(form.getGenre());
		utilisateur.setPrenom(form.getPrenom());
		utilisateur.setNom(form.getNom());
		utilisateur.setEmail(form.getEmail());
		utilisateur.setMdp(form.getMdp());

		utilisateur.getAdresse().setCodePostal(form.getCodePostal());
		utilisateur.getAdresse().setNumero(form.getNumero());
		utilisateur.getAdresse().setVille(form.getVille());
		utilisateur.getAdresse().setVoie(form.getVoie());

		utilisateur.setSocietes(form.getSocietes());
		utilisateur.setAdmin(form.isAdmin());
		utilisateur.setClient(form.isClient());

		// R�up�ration et transformtion de la date de naissance au format sql
		String date = form.getAnnee() + "-" + form.getMois() + "-" + form.getJour();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String msgMail = null;

		try {
			utilisateur.setDateCreation(sdf.parse(date));
			// sauvegarde des modifs en Bdd
			utilisateurDao.update(utilisateur);

		} catch (Exception e) {
			msgMail = "Cette adresse mail est déjà utilisée";
			model.addAttribute("msgMail", msgMail);
//			return "client/details";
			displayUtilisateurDetails(form.getId(), model);
		}

		return "redirect:/client/account";
	}

	@RequestMapping(value = "/client/ajouter-aux-favoris", method = RequestMethod.GET)
	public String ajouterSociete(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("id") long id, Model model) {


		Societe societe = societeDao.findById(id);
		String ajouteAuxFavoris = null;
		boolean exist;

		Utilisateur u = utilisateurDao.findById((long) request.getSession().getAttribute("user_id"));
		for(int j = 0; j < u.getSocietes().size(); j++) {
			System.out.println("societe id = " + u.getSocietes().get(j).getId());
		}
		
		System.out.println("u.getSocietes() = " + u.getSocietes().size());
		for (int i = 0; i < u.getSocietes().size(); i++) {
			if(u.getSocietes().get(i).getId() == societe.getId()) {
				ajouteAuxFavoris = "non";
			}
		}
		
		if(ajouteAuxFavoris == null) {
			u.getSocietes().add(societe);
			utilisateurDao.update(u);
			ajouteAuxFavoris = "oui";
		}
		
		System.out.println("dans client ajouteAuxFavoris = " + ajouteAuxFavoris);
		

		return "redirect:/client/societe/societe-detail?id="+id+"&ajouteAuxFavoris="+ajouteAuxFavoris;
	}

//	@RequestMapping(value = { "/client/accueil", "/admin/accueil" }, method = RequestMethod.GET)
//	public String accueil(Model model) {
//
//		return "client/accueil";
//	}

	@RequestMapping("/client/contact") 
	public ModelAndView showContact(HttpServletRequest request, @RequestParam(name="id", required=false) long id) {
		Map<String, Object> model = new HashMap<>();

		String email = request.getSession().getAttribute("user_email").toString();

		ContactForm cf = new ContactForm(email, "", "");
		if(id != 0) {
			Societe s = societeDao.findById(id);
			cf.setEmailTo(s.getEmail());
			model.put("societeNom", s.getNom());
		}
		
		
		model.put("contact-form", cf);
		return new ModelAndView("client/contact", model);
	}

	@RequestMapping(value = "/client/envoyer-message", method = RequestMethod.POST)
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
		
		if(form.getEmailTo() != null) {
			messageErreur = EmailTools.sendEmailToArtisan(form.getEmail(), form.getSubject(), form.getMessage(), form.getEmailTo());
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
	
	@RequestMapping(value = "/client/mes-favoris", method = RequestMethod.GET)
	public String listerSocietesFavorites(HttpServletRequest request, @RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "max", required = false) Integer max, Model model) {

		if (max == null || max == 0)
			max = 15;
		if (page == null || page == 0)
			page = 1;

		String msgTitre = null;
		int start = (page - 1) * max;
		if(request.getSession().getAttribute("user_id")!= null){
			Utilisateur u = utilisateurDao.findById((long) request.getSession().getAttribute("user_id"));
			msgTitre = " favorites";
			model.addAttribute("msgTitre", msgTitre);
			model.addAttribute("societes", u.getSocietes());
			
		}

		long nb = societeDao.nbSocietes();
		boolean suivExist = (page * max) < nb;

		model.addAttribute("suivExist", suivExist);
		model.addAttribute("page", page);

		return "client/societes";
	}

}
