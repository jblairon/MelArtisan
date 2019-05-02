package fr.jose.plateformeArtisan.controllers;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.jose.plateformeArtisan.beans.Adresse;
import fr.jose.plateformeArtisan.beans.Societe;
import fr.jose.plateformeArtisan.beans.Utilisateur;
import fr.jose.plateformeArtisan.beans.Utilisateur.Genre;
import fr.jose.plateformeArtisan.dao.AdresseDao;
import fr.jose.plateformeArtisan.dao.SocieteDao;
import fr.jose.plateformeArtisan.dao.UtilisateurDao;
import fr.jose.plateformeArtisan.formbeans.SignUpForm;
import fr.jose.plateformeArtisan.tools.DateUtils;

@Controller
public class SignupController {

	@Autowired
	private UtilisateurDao utilisateurDao;
	
	@Autowired
	private AdresseDao adresseDao;
	
	@Autowired
	private SocieteDao societeDao;

	@RequestMapping(value = {"/signup", "/admin/ajouter-utilisateur"})
	public ModelAndView signup(HttpServletRequest request) {
		Map<String, Object> model = new HashMap<>();
		// récupération de l'objet SignUpForm
		SignUpForm form = new SignUpForm("", "", "Monsieur", "", "", "01/01/1970");
		model.put("signup-form", form);

		if(request.getSession().getAttribute("user_admin")!=null && (boolean) request.getSession().getAttribute("user_admin")) {
			List<Societe> societes = societeDao.findAll();
			model.put("societes", societes);
			return new ModelAndView("admin/inscrireUtilisateur", model);
		}
		return new ModelAndView("signup", model);
	}

	@RequestMapping(value = "/validate-signup", method = RequestMethod.POST)
	public ModelAndView checkLogin(HttpServletRequest request, @Valid @ModelAttribute("signup-form") SignUpForm form,
			BindingResult result) {
		Map<String, Object> model = new HashMap<>();

		if (result.hasErrors()) {

			if (result.getFieldError().getField().toString().equals("email")) {
				model.put("msg", "Errreur : le format de l'adresse email n'est pas correct !");
			} else {
				model.put("msg", "Errreur : le mot de passe n'est pas correct !");
			}

			model.put("errors", result);
			model.put("signup-form", form);
			model.put("msg", "Assurez-vous que les champs soient correctement remplis");
			return new ModelAndView("signup", model);
		}

		Utilisateur u = new Utilisateur();
		u.setPrenom(form.getPrenom());
		u.setNom(form.getNom());
		u.setEmail(form.getEmail());
		u.setMdp(form.getMdp());
		u.setGenre(Enum.valueOf(Genre.class, form.getGenre()));
		
		if((boolean) request.getSession().getAttribute("user_admin")) {
			u.setAdmin(form.isAdmin());
			u.setArtisan(form.isArtisan());
			u.setClient(form.isClient());

		}
		

		// R�up�ration et transformtion de la date de naissance au format sql
		String dateDeNaissance = form.getDateDeNaissance();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Date utilDate = new Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		u.setDateCreation(sqlDate);

		LocalDate ld = DateUtils.stringToSqlDate(dateDeNaissance);
		u.setDateDeNaissance(ld);
		
		//Pour l'adresse
		Adresse a = new Adresse();
		a.setNumero(Integer.parseInt(form.getNumero()));
		a.setCodePostal(Integer.parseInt(form.getCodePostal()));
		a.setComplement(form.getComplement());

		a.setVille(form.getVille());
		a.setVoie(form.getVoie());
		adresseDao.save(a);
		
		u.setAdresse(a);


		// on traite l'erreur retournée si l'adresse mail existe déjà en base de données
		String msgMail = null;
		try {
			utilisateurDao.save(u);
			if(form.isArtisan()) {
				Societe s = societeDao.findById(form.getSocieteId());
				s.setUtilisateur(u);
				societeDao.update(s);
				u.setMaSociete(s);
				
			}
			
			
		} catch (Exception e) {
			msgMail = "Cette adresse mail est déjà utilisée";
			model.put("msgMail", msgMail);
			return new ModelAndView("signup", model);
		}
		
		if((boolean) request.getSession().getAttribute("user_admin")) {
			return new ModelAndView("redirect:admin/liste-utilisateurs", model);
		}


		return new ModelAndView("redirect:client/accueil", model);
	}

}
