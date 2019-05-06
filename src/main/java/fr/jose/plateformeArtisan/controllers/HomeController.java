package fr.jose.plateformeArtisan.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.jose.plateformeArtisan.beans.Categorie;
import fr.jose.plateformeArtisan.dao.CategorieDao;


@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private CategorieDao cat;
	
	

//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String home(Locale locale, Model model) {
//		System.out.println("home");
//
//		return "home";
//	}
	
	@Transactional
	@RequestMapping(value = {"/", "/categories", "/client/accueil", "/admin/categories"}, method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest request, @RequestParam(name = "messageErreur", required = false) String messageErreur,
			@RequestParam(name = "messageSuccess", required = false) String messageSuccess) {
		List<Categorie> categories = new ArrayList<Categorie>();
		
		try {
			categories = cat.findAll() ;
			model.addAttribute("categories", categories);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg","Erreur de connexion à la base de données pour la raison suivante : ");
			model.addAttribute("msgErreur", e.getMessage());
			return "pageErreurs";
			
		}


	if(request.getSession().getAttribute("user_id")!=null) {
		model.addAttribute("messageErreur", messageErreur);
		model.addAttribute("messageSuccess", messageSuccess);
		
		return "client/accueil";
	}
	if( request.getSession().getAttribute("user_admin")!=null) {
		return "admin/accueil";
	}
		
		return "home";
	}
	
//	@Transactional
//	@RequestMapping(value = {"/",  "/client/accueil", }, method = RequestMethod.GET)
//	public String accueil(Model model, HttpServletRequest request, @RequestParam(name = "messageErreur", required = false) String messageErreur,
//			@RequestParam(name = "messageSuccess", required = false) String messageSuccess) {
//	System.out.println("lsqjdfljfl");
//		
//		return "accueil";
//	}
	
}