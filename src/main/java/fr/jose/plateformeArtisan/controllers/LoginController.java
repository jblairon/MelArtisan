package fr.jose.plateformeArtisan.controllers;

import java.util.HashMap;
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

import fr.jose.plateformeArtisan.beans.Utilisateur;
import fr.jose.plateformeArtisan.dao.UtilisateurDao;
import fr.jose.plateformeArtisan.formbeans.LoginForm;

@Controller
public class LoginController {

	@Autowired
	private UtilisateurDao utilisateurDao;

	public void setUtilisateurDao(UtilisateurDao utilisateurDao) {
		this.utilisateurDao = utilisateurDao;
	}

	@RequestMapping("/authenticate")
	public ModelAndView showLogin(@RequestParam(name = "contact", required = false) boolean contact,
			@RequestParam(name = "sessionExpiree", required = false) boolean sessionExpiree) {
		Map<String, Object> model = new HashMap<String, Object>();
		
		
		LoginForm lf = new LoginForm("", "", contact);
		model.put("login-form", lf);
		return new ModelAndView("login", model);
	}

	@RequestMapping(value = "/check-login", method = RequestMethod.POST)
	public String checkLogin(HttpServletRequest request, @Valid @ModelAttribute("login-form") LoginForm form,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("errors", result);
			model.addAttribute("login-form", form);
			return "login";
		}
		Utilisateur u;

		try {
			u = utilisateurDao.findByEmail(form.getUsername());
			model.addAttribute("user_id", u.getId());
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "Erreur d'accès à la base de données");
			return "pageErreurs";

		}

		if (u != null && u.getMdp().equals(form.getMdp())) {
			request.getSession().setAttribute("user_prenom", u.getPrenom());
			request.getSession().setAttribute("user_nom", u.getNom());
			request.getSession().setAttribute("user_id", u.getId());
			request.getSession().setAttribute("user_email", u.getEmail());
			request.getSession().setAttribute("user_admin", u.isAdmin());
			request.getSession().setAttribute("user_client", u.isClient());
			request.getSession().setAttribute("user_artisan", u.isArtisan());
			
			model.addAttribute("user_id", u.getId());
				
			if (u.isArtisan()) {
				request.getSession().setAttribute("societeId", u.getMaSociete().getId());
			}

			if (u.isAdmin()) {
				return "redirect:/admin/dashboard";
			}

			else if (form.isContact()) {
				return "redirect:/client/contact?id=0";
			} else if (u.isClient()) {
				return "redirect:/client/accueil";
			} else if (u.isArtisan()) {
				request.getSession().setMaxInactiveInterval(60);
				return "redirect:/artisan/ma-societe?id=" + u.getMaSociete().getId();
			}

			else
				return "redirect:/client/account";
		} else {
			model.addAttribute("login-form", form);
			model.addAttribute("msg", "Erreur : incorrect email et/ou mot de passe incorrecte !");
			return "login";
		}
	}

}
