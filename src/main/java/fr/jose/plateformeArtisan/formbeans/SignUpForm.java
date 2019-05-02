package fr.jose.plateformeArtisan.formbeans;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;


public class SignUpForm {
	
	@NotEmpty
	private String prenom;

	@NotEmpty
	private String nom;

	@NotEmpty
	private String genre;

	@NotEmpty
	@Pattern(regexp = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b", message = "Invalid Email")
	private String email;

	@NotEmpty
	@Size(min = 4, max = 50)
	private String mdp;

	

	@NotEmpty
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private String dateDeNaissance;
	
	@NotEmpty
	private String numero;
	
	private String complement;
	
	@NotEmpty
	private String codePostal;
	
	@NotEmpty
	private String voie;
	
	@NotEmpty
	private String ville;
	
	private long id;
	
	private boolean client;
	private boolean admin;
	private boolean artisan;
	
	//pour l'artisan on définit sa société
	private long societeId;
	

	public SignUpForm() {

	}

	public SignUpForm(String prenom, String nom, String genre, String email, String mdp, String dateDeNaissance) {
		super();
		this.prenom = prenom;
		this.nom = nom;
		this.genre = genre;
		this.email = email;
		this.mdp = mdp;
		this.dateDeNaissance = dateDeNaissance;
	}
	
	
	

	public long getSocieteId() {
		return societeId;
	}

	public void setSocieteId(long societeId) {
		this.societeId = societeId;
	}

	public boolean isClient() {
		return client;
	}

	public void setClient(boolean client) {
		this.client = client;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean isArtisan() {
		return artisan;
	}

	public void setArtisan(boolean artisan) {
		this.artisan = artisan;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
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

	public String getDateDeNaissance() {
		return dateDeNaissance;
	}

	public void setDateDeNaissance(String dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVoie() {
		return voie;
	}

	public void setVoie(String voie) {
		this.voie = voie;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

		
}
