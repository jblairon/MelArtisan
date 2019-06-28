package fr.jose.plateformeArtisans.services;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import fr.jose.plateformeArtisan.beans.Newsletter;
import fr.jose.plateformeArtisan.beans.Note;
import fr.jose.plateformeArtisan.beans.Societe;
import fr.jose.plateformeArtisan.beans.SocieteDateVacances;
import fr.jose.plateformeArtisan.beans.Utilisateur;
import fr.jose.plateformeArtisan.dao.NewsletterDao;
import fr.jose.plateformeArtisan.dao.SocieteDao;
import fr.jose.plateformeArtisan.dao.UtilisateurDao;
import fr.jose.plateformeArtisan.tools.DateUtils;

public class SocieteServices {

	@Autowired
	private SocieteDao societeDao;

	@Autowired(required = true)
	private NewsletterDao newsletterDao;

	@Autowired
	private static UtilisateurDao utilisateurDao;

	public static Double calculerNoteMoyenne(List<Note> notes) {
		int total = 0;
		Double moyenne;
		for (Note note : notes) {
			total += note.getNote();
		}

		moyenne = (double) total / (double) notes.size();

		return (double) Math.round(moyenne * 10) / 10;
	}

	public static List<String> tableauDesHeures() {
		List<String> heures = new ArrayList<String>();
		NumberFormat nf = new DecimalFormat("00");

		for (int i = 0; i <= 23; i++) {
			heures.add(nf.format(i));
		}

		return heures;
	}

	public static List<String> tableauDesMinutes() {
		List<String> minutes = new ArrayList<String>();
		NumberFormat nf = new DecimalFormat("00");

		for (int i = 0; i <= 55; i += 5) {
			minutes.add(nf.format(i));
		}

		return minutes;
	}

	// retourne un message si la période de vacances est au max une semaine avant la
	// date en cours
	public static String messageProchainesVacances(SocieteDateVacances v) {
		String msg = null;
		LocalDate ld = LocalDate.now();

		int jours = v.getDateDebut().getDayOfYear() - ld.getDayOfYear();
		if (jours <= 7 && jours > 0) {
			msg = "Attention !!! Nous serons fermés du "
					+ DateUtils.stringSqlToLocalDate_FR(v.getDateDebut().toString()) + " au "
					+ DateUtils.stringSqlToLocalDate_FR(v.getDateFin().toString()) + " inclus";
		} else if ((jours <= 0 && ld.getDayOfYear() <= v.getDateFin().getDayOfYear())) {
			msg = "Nous sommes fermés du " + DateUtils.stringSqlToLocalDate_FR(v.getDateDebut().toString()) + " au "
					+ DateUtils.stringSqlToLocalDate_FR(v.getDateFin().toString());
		}

		System.out.println("msg = " + msg);
		return msg;
	}

	// retourne la liste des villes de toutes les sociétés
	public static List<String> listeDesVilles(List<Societe> societes) {

		List<String> villes = new ArrayList<>();

		for (Societe societe : societes) {
			villes.add(societe.getAdresse().getVille());
		}
		Set<String> setVilles = new HashSet<>(villes);

		return new ArrayList<>(setVilles);
	}

	// Envoi de newsletters après chaque ajout d'une société ou une modification
	public static void envoiNewsletters(List<Newsletter> news) {


		// On récupère la liste d'adresse mail
		List<Utilisateur> us = new ArrayList<Utilisateur>();
		List<String> emails = new ArrayList<String>();

		for (Newsletter n : news) {
			Utilisateur u = utilisateurDao.findById(n.getUtilisateur_id());
			if (u != null)
				emails.add(u.getEmail());
		}

		System.out.println("email.size() = " + emails.size());

	}

}
