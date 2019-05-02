package fr.jose.plateformeArtisan.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//r�cup�rer l'urlDemandee
		String requestedURI = request.getRequestURI();
		//si l'url demand�e est /client alors
		if(requestedURI.contains("/client/") || requestedURI.contains("/admin/") ) {
			//r�cup�rer la session
			HttpSession session = request.getSession();
			//tester la présence de isConnected
			if(session.getAttribute("utilisateur_id")==null) {
				request.getRequestDispatcher("authenticate").forward(request, response);
			}
		
		} //fin si
		
		return true;
	}

	
	
}
