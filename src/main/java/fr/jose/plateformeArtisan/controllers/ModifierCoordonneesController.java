package fr.jose.plateformeArtisan.controllers;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.jose.plateformeArtisan.beans.Adresse;
import fr.jose.plateformeArtisan.beans.Utilisateur;
import fr.jose.plateformeArtisan.beans.Utilisateur.Genre;
import fr.jose.plateformeArtisan.dao.AdresseDao;
import fr.jose.plateformeArtisan.dao.UtilisateurDao;
import fr.jose.plateformeArtisan.formbeans.SignUpForm;
import fr.jose.plateformeArtisan.tools.DateUtils;

@Controller
public class ModifierCoordonneesController {

	@Autowired
	private UtilisateurDao utilisateurDao;

	@Autowired
	private AdresseDao adresseDao;

	@RequestMapping(value = { "/client/modifier-coordonnees",
			"/admin/modifier-coordonnees" }, method = RequestMethod.GET)
	public ModelAndView modifierCoordonnees(HttpServletRequest request,
			@RequestParam(name = "id", required = true) long id) {
		Map<String, Object> model = new HashMap<>();

		if (id != 0) {
			Utilisateur u = null;

			try {
				u = utilisateurDao.findById(id);
			} catch (Exception e) {
				e.printStackTrace();
				model.put("msg", "Erreur de connexion à la base de données pour la raison suivante : ");
				model.put("msgErreur", e.getMessage());
				return new ModelAndView("pageErreurs", model);

			}

			// récupération de l'objet SignUpForm
			SignUpForm form = new SignUpForm();

			// on remplit les champs du formulaire par les données de l'utilisatur
			form.setCodePostal(String.valueOf(u.getAdresse().getCodePostal()));
			form.setComplement(u.getAdresse().getComplement());
			form.setEmail(u.getEmail());

			String ddn = DateUtils.stringSqlToLocalDate_FR(u.getDateDeNaissance().toString());
			form.setDateDeNaissance(ddn);

			form.setGenre(u.getGenre().toString());
			form.setMdp(u.getMdp());
			form.setNom(u.getNom());
			form.setNumero(String.valueOf(u.getAdresse().getNumero()));
			form.setPrenom(u.getPrenom());
			form.setVille(u.getAdresse().getVille());
			form.setVoie(u.getAdresse().getVoie());
			form.setId(u.getId());

			if (u.isAdmin()) {
				form.setAdmin(u.isAdmin());
				form.setArtisan(u.isArtisan());
				form.setClient(u.isClient());
			}

			model.put("signup-form", form);

			if (u.isAdmin()) {
				return new ModelAndView("admin/modifierCoordonnees", model);
			}
			return new ModelAndView("client/modifierCoordonnees", model);
		}
		return null;

	}

	@RequestMapping(value = "/valider-coordonnees", method = RequestMethod.POST)
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
			return new ModelAndView("modifierCoordonnees", model);
		}

		Utilisateur u = utilisateurDao.findById((long) form.getId());

		u.getAdresse().setCodePostal(Integer.parseInt(form.getCodePostal()));
		u.getAdresse().setComplement(form.getComplement());
		u.getAdresse().setNumero(Integer.parseInt(form.getNumero()));
		u.getAdresse().setVille(form.getVille());
		u.getAdresse().setVoie(form.getVoie());
		adresseDao.update(u.getAdresse());

		LocalDate ld = DateUtils.stringToSqlDate(form.getDateDeNaissance());
		u.setDateDeNaissance(ld);

		u.setEmail(form.getEmail());
		u.setGenre(Enum.valueOf(Genre.class, form.getGenre()));
		u.setMdp(form.getMdp());
		u.setNom(form.getNom());
		u.setPrenom(form.getPrenom());

		if ((boolean) request.getSession().getAttribute("user_admin")) {
			u.setAdmin(form.isAdmin());
			u.setArtisan(form.isArtisan());
			u.setClient(form.isClient());
			System.out.println("Artisan = " + u.isArtisan());
			System.out.println("Admin = " + u.isAdmin());
			System.out.println("Client = " + u.isClient());
		}

		// on traite l'erreur retournée si l'adresse mail existe déjà en base de données
		String msgMail = null;
		try {
			utilisateurDao.update(u);
		} catch (Exception e) {
			msgMail = "Cette adresse mail est déjà utilisée";
			model.put("msgMail", msgMail);
			return new ModelAndView("client/modifier-coordonnees", model);
		}
		if ((boolean) request.getSession().getAttribute("user_admin")) {
			System.out.println("je suis admin");
			return new ModelAndView("redirect:/admin/liste-utilisateurs");
		}

		return new ModelAndView("redirect:/authenticate");
	}

}
