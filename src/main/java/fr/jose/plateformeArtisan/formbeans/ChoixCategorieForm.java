package fr.jose.plateformeArtisan.formbeans;

import fr.jose.plateformeArtisan.beans.Categorie;


public class ChoixCategorieForm {


//	@NotEmpty
	private long categorieId;
	private Categorie categorie;
	
	private String nouvelleCategorie;
	
	private long societeId;
		

	public ChoixCategorieForm() {

	}

	public ChoixCategorieForm(long categorieId) {
		super();
		
		this.categorieId = categorieId;
		
	}
	
	public ChoixCategorieForm(String nouvelleCategorie) {
		super();
		this.nouvelleCategorie = nouvelleCategorie;
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

	public String getNouvelleCategorie() {
		return nouvelleCategorie;
	}

	public void setNouvelleCategorie(String nouvelleCategorie) {
		this.nouvelleCategorie = nouvelleCategorie;
	}

	public long getSocieteId() {
		return societeId;
	}

	public void setSocieteId(long societeId) {
		this.societeId = societeId;
	}

		
}
