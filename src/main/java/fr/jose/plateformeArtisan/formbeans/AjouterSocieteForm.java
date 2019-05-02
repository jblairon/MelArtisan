package fr.jose.plateformeArtisan.formbeans;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import fr.jose.plateformeArtisan.beans.Categorie;
import fr.jose.plateformeArtisan.beans.Contact;
import fr.jose.plateformeArtisan.beans.Metier;


public class AjouterSocieteForm {

	@NotEmpty
	private String nom;

	@NotEmpty
	private String description;

	@NotEmpty
	@Pattern(regexp = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b", message = "Invalid Email")
	private String email;

	@NotEmpty
	private String tel;

	@NotEmpty
	private String numero;
	
	private String complement;
	
	@NotEmpty
	private String codePostal;
	
	@NotEmpty
	private String voie;
	
	@NotEmpty
	private String ville;

//	@NotEmpty
	private long categorieId;
	
	private Categorie categorie;

	private List<Metier> metiers;
	
	private long prestationsId[];
	private String nouvellesPrestations[];
	private String nouvellePrestation;
	private long metiersId[];
	private String imageBackground;
	private String imageVignette;
	private String image1;
	private String image2;
	private String image3;
	
	private String prenomContact;
	private String nomContact;
	private String telContact;
		
	@Pattern(regexp = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b", message = "Invalid Email")
	private String emailContact;
	
	private String fonctionContact;


	public AjouterSocieteForm() {

	}

	public AjouterSocieteForm(long categorieId, Categorie categorie, String nom, String description, String tel, String email, 
			String numero, String complement, String voie, String codePostal,
			String ville, List<Metier> metiers, long[] prestations, boolean admin, 
			String prenomContact, String nomContact,String telContact, String emailContact, String fonctionContact) {
		super();
		
		this.categorieId = categorieId;
		this.categorie = categorie;
		this.codePostal = codePostal;
		this.complement = complement;
		this.description = description;
		this.email = email;
		this.metiers = metiers;
		this.prestationsId = prestations;
		this.nom = nom;
		this.numero = numero;
		this.tel = tel;
		this.ville = ville;
		this.voie = voie;
		this.prenomContact = prenomContact;
		this.nomContact = nomContact;
		this.emailContact = emailContact;
		this.telContact = telContact;
		this.fonctionContact = fonctionContact;
		
	}
	
	

	public AjouterSocieteForm(long categorieId, Categorie categorie, List<Metier> metiers) {
		super();
		this.categorieId = categorieId;
		this.categorie = categorie;
		this.metiers = metiers;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
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

	public long getCategorieId() {
		return categorieId;
	}

	public void setCategorieId(long categorieId) {
		this.categorieId = categorieId;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public List<Metier> getMetiers() {
		return metiers;
	}

	public void setMetiers(List<Metier> metiers) {
		this.metiers = metiers;
	}

	public long[] getPrestationsId() {
		return prestationsId;
	}

	public void setPrestationsId(long[] prestationsId) {
		this.prestationsId = prestationsId;
	}

	public String getPrenomContact() {
		return prenomContact;
	}

	public void setPrenomContact(String prenomContact) {
		this.prenomContact = prenomContact;
	}

	public String getNomContact() {
		return nomContact;
	}

	public void setNomContact(String nomContact) {
		this.nomContact = nomContact;
	}

	public String getTelContact() {
		return telContact;
	}

	public void setTelContact(String telContact) {
		this.telContact = telContact;
	}

	public String getEmailContact() {
		return emailContact;
	}

	public void setEmailContact(String emailContact) {
		this.emailContact = emailContact;
	}

	public String getFonctionContact() {
		return fonctionContact;
	}

	public void setFonctionContact(String fonctionContact) {
		this.fonctionContact = fonctionContact;
	}

	public String[] getNouvellesPrestations() {
		return nouvellesPrestations;
	}

	public void setNouvellesPrestations(String[] nouvellesPrestations) {
		this.nouvellesPrestations = nouvellesPrestations;
	}

	public String getNouvellePrestation() {
		return nouvellePrestation;
	}

	public void setNouvellePrestation(String nouvellePrestation) {
		this.nouvellePrestation = nouvellePrestation;
	}

	public long[] getMetiersId() {
		return metiersId;
	}

	public void setMetiersId(long[] metiersId) {
		this.metiersId = metiersId;
	}

	public String getImageBackground() {
		return imageBackground;
	}

	public void setImageBackground(String imageBackground) {
		this.imageBackground = imageBackground;
	}

	public String getImageVignette() {
		return imageVignette;
	}

	public void setImageVignette(String imageVignette) {
		this.imageVignette = imageVignette;
	}

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public String getImage3() {
		return image3;
	}

	public void setImage3(String image3) {
		this.image3 = image3;
	}


	
	

	
}
