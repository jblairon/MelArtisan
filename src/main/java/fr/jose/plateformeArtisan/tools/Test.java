package fr.jose.plateformeArtisan.tools;

import org.springframework.beans.factory.annotation.Autowired;

import fr.jose.plateformeArtisan.beans.Societe;
import fr.jose.plateformeArtisan.dao.SocieteDao;

public class Test {

	@Autowired
	private static SocieteDao societeDao;
	
	private static Societe s;

	public static void main(String[] args) {

		s = societeDao.findById(27); 
		
		System.out.println("taille images = " + s.getImages().size());
		

	}

}
