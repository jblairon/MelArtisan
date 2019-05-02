package fr.jose.plateformeArtisan.formbeans;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import fr.jose.plateformeArtisan.beans.Societe;
import fr.jose.plateformeArtisan.beans.Utilisateur.Genre;

public class EditUtilisateurForm {

	private Long id;

	@NotEmpty
	private Genre genre;
	
	@NotEmpty
	private String prenom;

	@NotEmpty
	private String nom;

	@NotEmpty
	private int numero;
	
	@NotEmpty
	private String voie;
	
	@NotEmpty
	private int codePostal;
	
	@NotEmpty
	private String  ville;
	
	@NotEmpty
	private int  jour;
	
	@NotEmpty
	private int  mois;
	
	@NotEmpty
	private int  annee;

	@NotEmpty
	@Pattern(regexp = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b", message = "Invalid Email")
	private String email;

	@NotEmpty
	@Size(min = 4, max = 50)
	private String mdp;

	private boolean admin;
	private boolean client;
	private boolean artisan;
	
	private List<Societe> societes;

	public EditUtilisateurForm() {

	}

	public EditUtilisateurForm(Genre genre, String prenom, String nom, int numero, String voie, int codePostal, String ville , String email, 
			String mdp, boolean admin, boolean client, List<Societe> societes, int jour, int mois, int annee) {
		super();
		this.genre = genre;
		this.prenom = prenom;
		this.nom = nom;
		this.numero = numero;
		this.voie = voie;
		this.codePostal = codePostal;
		this.ville = ville;
		this.email = email;
		this.mdp = mdp;
		this.admin = admin;
		this.client = client;
		this.societes = societes;
		this.jour = jour;
		this.mois = mois;
		this.annee = annee;
	}
	
	

	public boolean isArtisan() {
		return artisan;
	}

	public void setArtisan(boolean artisan) {
		this.artisan = artisan;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public int getJour() {
		return jour;
	}

	public void setJour(int jour) {
		this.jour = jour;
	}

	public int getMois() {
		return mois;
	}

	public void setMois(int mois) {
		this.mois = mois;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public List<Societe> getSocietes() {
		return societes;
	}

	public void setSocietes(List<Societe> societes) {
		this.societes = societes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getVoie() {
		return voie;
	}

	public void setVoie(String voie) {
		this.voie = voie;
	}

	public int getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean isClient() {
		return client;
	}

	public void setClient(boolean client) {
		this.client = client;
	}
	
	

}
