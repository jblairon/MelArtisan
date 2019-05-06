package fr.jose.plateformeArtisan.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//récupérer l'urlDemandee
		String requestedURI = request.getRequestURI();
		//si l'url demandée est /client alors
		if(requestedURI.contains("/client/") || requestedURI.contains("/admin/") ) {
			//récupérer la session
			HttpSession session = request.getSession();
			String messageSessionExpiree = null;
			//tester la présence de isConnected
			if(session.getAttribute("user_id")==null) {
				messageSessionExpiree = "Session expirée !!! ";
				request.setAttribute("messageSessionExpiree", messageSessionExpiree);
				request.getRequestDispatcher("/authenticate?user_id=0").forward(request, response);
			}
		
		} //fin si
		
		return true;
	}

	
	
}
